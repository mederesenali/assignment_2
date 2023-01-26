package com.example.app1.payload;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement
@Data
public class Process {
    @XmlElement
    public String name;
    @XmlElement
    public int id;
    @XmlElement
    public Start start;
}
