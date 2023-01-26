package com.example.app1.payload;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;



@XmlRootElement(name = "method")
@Data
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Method {
    @XmlElement(name = "Name")
    public String name;
    @XmlElement(name = "Type")
    public String type;
    @XmlElement(name = "Assembly")
    public String assembly;
}
