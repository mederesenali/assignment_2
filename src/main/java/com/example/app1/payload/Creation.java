package com.example.app1.payload;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.Date;
@XmlRootElement
@Data
public class Creation {
    @XmlElement(name = "Epoch")
    public Long epoch;
    @XmlElement(name = "Date")
    public Date date;
}
