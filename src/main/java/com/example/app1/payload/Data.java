package com.example.app1.payload;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@lombok.Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
    @XmlElement
    private Method method;
    @XmlElement(name = "Process")
    private Process process;
    @XmlElement(name = "Layer")
    private String layer;
    @XmlElement(name = "Creation")
    private Creation creation;
    @XmlElement(name = "Type")
    private String type;
}
