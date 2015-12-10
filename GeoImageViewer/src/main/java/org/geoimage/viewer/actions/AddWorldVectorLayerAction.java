/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geoimage.viewer.actions;

import java.io.File;
import java.util.List;

import org.geoimage.def.SarImageReader;
import org.geoimage.utils.IProgress;
import org.geoimage.viewer.core.SumoPlatform;
import org.geoimage.viewer.core.factory.FactoryLayer;
import org.geoimage.viewer.core.gui.manager.LayerManager;
import org.geoimage.viewer.core.io.SimpleShapefile;
import org.geoimage.viewer.core.layers.GeometricLayer;
import org.geoimage.viewer.core.layers.image.ImageLayer;
import org.jrc.sumo.configuration.PlatformConfiguration;
import org.jrc.sumo.core.api.Argument;
import org.jrc.sumo.core.api.iactions.AbstractAction;
import org.slf4j.LoggerFactory;

import com.vividsolutions.jts.geom.Polygon;

/**
 *
 * @author thoorfr.
 * this class is called when you want to load a coast line for an active image. The land mask is based on the GSHHS shapefile which is situated on /org/geoimage/viewer/core/resources/shapefile/.
 * 
 */
public class AddWorldVectorLayerAction extends AbstractAction implements IProgress {
	private static org.slf4j.Logger logger=LoggerFactory.getLogger(AddInterpolatedConsoleAction.class);

    protected boolean done = false;

    public AddWorldVectorLayerAction() {
    }

    public String getName() {
        return "world";
    }

    public String getDescription() {
        return " Add a land mask layer";
    }

    public boolean execute(final String[] args) {
        done = false;
        new Thread(new Runnable() {

            public void run() {
                SumoPlatform.setInfo("Importing land mask from GSHHS shapefile...",-1);
                try {
                	ImageLayer  l=LayerManager.getIstanceManager().getCurrentImageLayer();
                	if(l!=null){
                        try {
                        	File shape=new File(SumoPlatform.getApplication().getConfiguration().getDefaultLandMask());
                        	Polygon imageP=((SarImageReader)l.getImageReader()).getBbox(PlatformConfiguration.getConfigurationInstance().getLandMaskMargin(0));
                            GeometricLayer gl = SimpleShapefile.createIntersectedLayer(shape,imageP,((SarImageReader)l.getImageReader()).getGeoTransform());
                            //addLayerInThread(gl, (ImageLayer) l);
                            LayerManager.addLayerInThread(FactoryLayer.TYPE_NON_COMPLEX, gl, l);
                        } catch (Exception ex) {
                            logger.error(ex.getMessage(), ex);
                        }
                	}   
                } catch (Exception e) {
                }
                SumoPlatform.getApplication().setInfo(null);
            }
        }).start();
        return true;
    }
/*
    public void addLayerInThread(final GeometricLayer layer, final ImageLayer il) {
        if(layer != null)
        {
            new Thread(new Runnable() {

                public void run() {
                    Platform.getLayerManager().addLayer(new EditGeometryVectorLayer(Platform.getCurrentImageLayer(),layer.getName(), layer.getGeometryType(), layer));
                    done = true;
                }
            }).start();
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(null, "Empty layer, not added to layers", "Warning", JOptionPane.ERROR_MESSAGE);
                }
            });
            done = true;
        }
    }*/

    public String getPath() {
        return "Import/Land mask";
    }

    public boolean isIndeterminate() {
        return true;
    }

    public boolean isDone() {
        return done;
    }

    public int getMaximum() {
        return 1;
    }

    public int getCurrent() {
        return 1;
    }

    public String getMessage() {
        return "adding world layer...";
    }

    public List<Argument> getArgumentTypes() {
        return null;
    }

    public void setCurrent(int i) {
    }

    public void setMaximum(int size) {
    }

    public void setMessage(String string) {
    }

    public void setIndeterminate(boolean value) {
    }

    public void setDone(boolean value) {
    }
}
