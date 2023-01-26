package com.example.app1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonData {
    private Integer recordCount;
    List<com.example.app1.payload.Data> data;
}
