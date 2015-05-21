//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.24 at 04:43:09 PM CEST 
//


package safe.annotation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}adsHeader"/>
 *         &lt;element ref="{}qualityInformation"/>
 *         &lt;element ref="{}generalAnnotation"/>
 *         &lt;element ref="{}imageAnnotation"/>
 *         &lt;element ref="{}dopplerCentroid"/>
 *         &lt;element ref="{}antennaPattern"/>
 *         &lt;element ref="{}swathTiming"/>
 *         &lt;element ref="{}geolocationGrid"/>
 *         &lt;element ref="{}coordinateConversion"/>
 *         &lt;element ref="{}swathMerging"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "adsHeader",
    "qualityInformation",
    "generalAnnotation",
    "imageAnnotation",
    "dopplerCentroid",
    "antennaPattern",
    "swathTiming",
    "geolocationGrid",
    "coordinateConversion",
    "swathMerging"
})
@XmlRootElement(name = "product")
public class Product {

    @XmlElement(required = true)
    protected AdsHeader adsHeader;
    @XmlElement(required = true)
    protected QualityInformation qualityInformation;
    @XmlElement(required = true)
    protected GeneralAnnotation generalAnnotation;
    @XmlElement(required = true)
    protected ImageAnnotation imageAnnotation;
    @XmlElement(required = true)
    protected DopplerCentroid dopplerCentroid;
    @XmlElement(required = true)
    protected AntennaPattern antennaPattern;
    @XmlElement(required = true)
    protected SwathTiming swathTiming;
    @XmlElement(required = true)
    protected GeolocationGrid geolocationGrid;
    @XmlElement(required = true)
    protected CoordinateConversion coordinateConversion;
    @XmlElement(required = true)
    protected SwathMerging swathMerging;

    /**
     * Gets the value of the adsHeader property.
     * 
     * @return
     *     possible object is
     *     {@link AdsHeader }
     *     
     */
    public AdsHeader getAdsHeader() {
        return adsHeader;
    }

    /**
     * Sets the value of the adsHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdsHeader }
     *     
     */
    public void setAdsHeader(AdsHeader value) {
        this.adsHeader = value;
    }

    /**
     * Gets the value of the qualityInformation property.
     * 
     * @return
     *     possible object is
     *     {@link QualityInformation }
     *     
     */
    public QualityInformation getQualityInformation() {
        return qualityInformation;
    }

    /**
     * Sets the value of the qualityInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link QualityInformation }
     *     
     */
    public void setQualityInformation(QualityInformation value) {
        this.qualityInformation = value;
    }

    /**
     * Gets the value of the generalAnnotation property.
     * 
     * @return
     *     possible object is
     *     {@link GeneralAnnotation }
     *     
     */
    public GeneralAnnotation getGeneralAnnotation() {
        return generalAnnotation;
    }

    /**
     * Sets the value of the generalAnnotation property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeneralAnnotation }
     *     
     */
    public void setGeneralAnnotation(GeneralAnnotation value) {
        this.generalAnnotation = value;
    }

    /**
     * Gets the value of the imageAnnotation property.
     * 
     * @return
     *     possible object is
     *     {@link ImageAnnotation }
     *     
     */
    public ImageAnnotation getImageAnnotation() {
        return imageAnnotation;
    }

    /**
     * Sets the value of the imageAnnotation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImageAnnotation }
     *     
     */
    public void setImageAnnotation(ImageAnnotation value) {
        this.imageAnnotation = value;
    }

    /**
     * Gets the value of the dopplerCentroid property.
     * 
     * @return
     *     possible object is
     *     {@link DopplerCentroid }
     *     
     */
    public DopplerCentroid getDopplerCentroid() {
        return dopplerCentroid;
    }

    /**
     * Sets the value of the dopplerCentroid property.
     * 
     * @param value
     *     allowed object is
     *     {@link DopplerCentroid }
     *     
     */
    public void setDopplerCentroid(DopplerCentroid value) {
        this.dopplerCentroid = value;
    }

    /**
     * Gets the value of the antennaPattern property.
     * 
     * @return
     *     possible object is
     *     {@link AntennaPattern }
     *     
     */
    public AntennaPattern getAntennaPattern() {
        return antennaPattern;
    }

    /**
     * Sets the value of the antennaPattern property.
     * 
     * @param value
     *     allowed object is
     *     {@link AntennaPattern }
     *     
     */
    public void setAntennaPattern(AntennaPattern value) {
        this.antennaPattern = value;
    }

    /**
     * Gets the value of the swathTiming property.
     * 
     * @return
     *     possible object is
     *     {@link SwathTiming }
     *     
     */
    public SwathTiming getSwathTiming() {
        return swathTiming;
    }

    /**
     * Sets the value of the swathTiming property.
     * 
     * @param value
     *     allowed object is
     *     {@link SwathTiming }
     *     
     */
    public void setSwathTiming(SwathTiming value) {
        this.swathTiming = value;
    }

    /**
     * Gets the value of the geolocationGrid property.
     * 
     * @return
     *     possible object is
     *     {@link GeolocationGrid }
     *     
     */
    public GeolocationGrid getGeolocationGrid() {
        return geolocationGrid;
    }

    /**
     * Sets the value of the geolocationGrid property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeolocationGrid }
     *     
     */
    public void setGeolocationGrid(GeolocationGrid value) {
        this.geolocationGrid = value;
    }

    /**
     * Gets the value of the coordinateConversion property.
     * 
     * @return
     *     possible object is
     *     {@link CoordinateConversion }
     *     
     */
    public CoordinateConversion getCoordinateConversion() {
        return coordinateConversion;
    }

    /**
     * Sets the value of the coordinateConversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoordinateConversion }
     *     
     */
    public void setCoordinateConversion(CoordinateConversion value) {
        this.coordinateConversion = value;
    }

    /**
     * Gets the value of the swathMerging property.
     * 
     * @return
     *     possible object is
     *     {@link SwathMerging }
     *     
     */
    public SwathMerging getSwathMerging() {
        return swathMerging;
    }

    /**
     * Sets the value of the swathMerging property.
     * 
     * @param value
     *     allowed object is
     *     {@link SwathMerging }
     *     
     */
    public void setSwathMerging(SwathMerging value) {
        this.swathMerging = value;
    }

}