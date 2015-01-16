//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.01.14 at 10:35:32 AM CET 
//


package org.geoimage.viewer.core.io.sumoxml;

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
 *         &lt;element ref="{}sat_image_metadata"/>
 *         &lt;element ref="{}vds_analysis"/>
 *         &lt;element ref="{}vds_target"/>
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
    "satImageMetadata",
    "vdsAnalysis",
    "vdsTarget"
})
@XmlRootElement(name = "analysis")
public class Analysis {

    @XmlElement(name = "sat_image_metadata", required = true)
    protected SatImageMetadata satImageMetadata;
    @XmlElement(name = "vds_analysis", required = true)
    protected VdsAnalysis vdsAnalysis;
    @XmlElement(name = "vds_target", required = true)
    protected VdsTarget vdsTarget;

    /**
     * Gets the value of the satImageMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link SatImageMetadata }
     *     
     */
    public SatImageMetadata getSatImageMetadata() {
        return satImageMetadata;
    }

    /**
     * Sets the value of the satImageMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link SatImageMetadata }
     *     
     */
    public void setSatImageMetadata(SatImageMetadata value) {
        this.satImageMetadata = value;
    }

    /**
     * Gets the value of the vdsAnalysis property.
     * 
     * @return
     *     possible object is
     *     {@link VdsAnalysis }
     *     
     */
    public VdsAnalysis getVdsAnalysis() {
        return vdsAnalysis;
    }

    /**
     * Sets the value of the vdsAnalysis property.
     * 
     * @param value
     *     allowed object is
     *     {@link VdsAnalysis }
     *     
     */
    public void setVdsAnalysis(VdsAnalysis value) {
        this.vdsAnalysis = value;
    }

    /**
     * Gets the value of the vdsTarget property.
     * 
     * @return
     *     possible object is
     *     {@link VdsTarget }
     *     
     */
    public VdsTarget getVdsTarget() {
        return vdsTarget;
    }

    /**
     * Sets the value of the vdsTarget property.
     * 
     * @param value
     *     allowed object is
     *     {@link VdsTarget }
     *     
     */
    public void setVdsTarget(VdsTarget value) {
        this.vdsTarget = value;
    }

}
