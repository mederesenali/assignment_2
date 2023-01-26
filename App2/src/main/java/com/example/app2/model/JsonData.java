package com.example.app2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonData {
    private Integer recordCount;
    List<com.example.app2.payload.Data> data;
}
