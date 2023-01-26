package com.example.app2.enums;

import lombok.Getter;

@Getter
public enum TypeEnum {
    Information("Information"),
    Trace("Trace");

    private String value;

    TypeEnum(String value) {
        this.value = value;
    }

}
