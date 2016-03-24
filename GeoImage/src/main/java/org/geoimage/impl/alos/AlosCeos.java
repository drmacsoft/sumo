/*
 * 
 */
package org.geoimage.impl.alos;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferUShort;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileFilter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.geoimage.factory.GeoTransformFactory;
import org.geoimage.impl.Gcp;
import org.geoimage.impl.imgreader.TIFF;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeocentricCRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vividsolutions.jts.geom.Coordinate;

public class AlosCeos extends Alos {
	private Logger logger= LoggerFactory.getLogger(AlosCeos.class);
	
	public AlosCeos(File manifest){
		super(manifest);
	}
	

	@Override
	public String[] getFilesList() {
		return new String[]{manifestFile.getAbsolutePath()};
	}

	@Override
	public boolean initialise() {
		try {
			File mainFolder=manifestFile.getParentFile();
			
        	polarizations=props.getPolarizations();
        	
        	//set image properties
        	alosImages=new HashMap<>();
        	List<String> imgNames=props.getImageNames();
        	
        	for(int i=0;i<imgNames.size();i++){
        		String img=imgNames.get(i);
        		File imgFile=new File(mainFolder.getAbsolutePath()+File.separator+img);
        		TIFF t=new TIFF(imgFile,0);
        		alosImages.put(img.substring(4,6),t);
        	}
            
        	super.pixelsize[0]=props.getPixelSpacing();
        	super.pixelsize[1]=props.getPixelSpacing();

			
        	//read and set the metadata from the manifest and the annotation
			setXMLMetaData();
			
			Coordinate[] corners=props.getCorners();
			int lines=props.getNumberOfLines();
			int pix=props.getNumberOfPixels();
            //we have only the corners
            gcps = new ArrayList<>();
            gcps.add(new Gcp(corners[0].x,corners[0].y,0,0));
            gcps.add(new Gcp(corners[1].x,corners[1].y,pix,0));
            gcps.add(new Gcp(corners[2].x,corners[2].y,pix,lines));
            gcps.add(new Gcp(corners[3].x,corners[3].y,0,lines));
            
           	String epsg = "EPSG:4326";
           	geotransform = GeoTransformFactory.createFromGcps(gcps, epsg);
            
            double[] latlon = geotransform.getGeoFromPixel(0, 0);
            double[] position = new double[3];
            MathTransform convert = CRS.findMathTransform(DefaultGeographicCRS.WGS84, DefaultGeocentricCRS.CARTESIAN);
            convert.transform(latlon, 0, position, 0, 1);
            

            // get incidence angles from gcps
            float firstIncidenceangle = (float) (this.gcps.get(0).getAngle());
            float lastIncidenceAngle = (float) (this.gcps.get(this.gcps.size() - 1).getAngle());
            setIncidenceNear(firstIncidenceangle < lastIncidenceAngle ? firstIncidenceangle : lastIncidenceAngle);
            setIncidenceFar(firstIncidenceangle > lastIncidenceAngle ? firstIncidenceangle : lastIncidenceAngle);

            return true;
        } catch (TransformException ex) {
            logger.error(ex.getMessage(), ex);
        } catch (FactoryException ex) {
        	logger.error(ex.getMessage(), ex);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        
        return false;
	}
	

	
	@Override
	public int getWidth() {
		return getImage(0).xSize;
	}


	@Override
	public int getHeight() {
		return getImage(0).ySize;
	}

	@Override
	public double getPRF(int x, int y) {
		return 0;
	}

	@Override
	public File getOverviewFile() {
		File folder=manifestFile.getParentFile();
		File[] files=folder.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if(pathname.getName().startsWith("BRS")&&pathname.getName().endsWith("jpg"))
					return true;
				return false;	
			}
		});
		return files[0];
	}

	@Override
	public String getSensor() {
		return "ALOS";
	}

	
	public TIFF getImage(int band){
		TIFF img=null;
		try{
			img =(TIFF) alosImages.get(getBandName(band));
		}catch(Exception e){ 
			logger.error(this.getClass().getName()+":getImage function  "+e.getMessage());
		}
		return img;
	}
	
	
	//----------------------------------------------
	
 /**
  * 
  * @param x
  * @param y
  * @param width
  * @param height
  * @param band
  * @return
  */
  public int[] read(int x, int y,int w,int h, int band) {
      Rectangle rect = new Rectangle(x, y, w, h);
      rect = rect.intersection(getImage(band).getBounds());
      int data[]=null;

       TIFF tiff=getImage(band);
       try {
       	BufferedImage bi=null;
   		bi=tiff.read(0, rect);
   		DataBufferUShort raster=(DataBufferUShort)bi.getRaster().getDataBuffer();
   		short[] b=raster.getData();
   		data=new int[b.length];
       	for(int i=0;i<b.length;i++)
       		data[i]=b[i];
   		
       } catch (Exception ex) {
           logger.warn(ex.getMessage());
       }finally{
       }
      
      return data;
  }


	@Override
	public int readPixel(int x, int y, int band) {
			return read(x,y,1,1,band)[0];
	}
	
	@Override
	public int[] readTile(int x, int y, int width, int height, int band) {
		Rectangle rect = new Rectangle(x, y, width, height);
        rect = rect.intersection(getImage(band).getBounds());
        
        int[] tile = new int[height * width];        
        if (rect.isEmpty()) {
            return tile;
        }

        if (rect.y != preloadedInterval[0] || rect.y + rect.height != preloadedInterval[1]||preloadedData.length<(rect.y*rect.height-1)) {
            preloadLineTile(rect.y, rect.height,band);
        }else{
        	logger.debug("using preloaded data");
        }

        int yOffset = getImage(band).xSize;
        int xinit = rect.x - x;
        int yinit = rect.y - y;
        for (int i = 0; i < rect.height; i++) {
            for (int j = 0; j < rect.width; j++) {
                int temp = i * yOffset + j + rect.x;
                try{
                	tile[(i + yinit) * width + j + xinit] = preloadedData[temp];
                }catch(ArrayIndexOutOfBoundsException e ){
                	logger.warn("readTile function:"+e.getMessage());
                }	
            }
            }
        return tile;
	}

	@Override
	public void preloadLineTile(int y, int length, int band) {
		if (y < 0) {
            return;
        }
        preloadedInterval = new int[]{y, y + length};
        Rectangle rect = new Rectangle(0, y, getImage(band).xSize, length);
        
        TIFF tiff=getImage(band);
        rect=tiff.getBounds().intersection(rect);

        try {
        	BufferedImage bi=null;
        	try{
        			bi=tiff.read(0, rect);
        	}catch(Exception e){
        		logger.warn("Problem reading image POS x:"+0+ "  y: "+y +"   try to read again");
        		try {
    			    Thread.sleep(100);                 
    			} catch(InterruptedException exx) {
    			    Thread.currentThread().interrupt();
    			}
        		bi=tiff.read(0, rect);
        	}	
        	WritableRaster raster=bi.getRaster();
        	preloadedData=(short[])raster.getDataElements(0, 0, raster.getWidth(), raster.getHeight(), null);//tSamples(0, 0, raster.getWidth(), raster.getHeight(), 0, (short[]) null);
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
        }finally{
        	//tiff.reader.addIIOReadProgressListener(this);
        	//readComplete=false;
        }
	}

	@Override
	public String getInternalImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public Date getImageDate(){
	 //TODO check this
    	Timestamp t=Timestamp.valueOf(getTimeStampStart());
    	return new Date(t.getTime());
    }
}
