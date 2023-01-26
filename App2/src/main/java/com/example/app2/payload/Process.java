package com.example.app2.payload;


import lombok.Data;

@Data
public class Process {
    public String name;
    public int id;
    public Start start;
}
