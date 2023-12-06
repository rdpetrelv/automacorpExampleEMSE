package com.emse.spring.automacorp.REST;

import com.emse.spring.automacorp.model.WindowEntity;

public class WindowMapper {
    public static WindowRecord of (WindowEntity window){
        return new WindowRecord(
                window.getId(),
                window.getName(),
                window.getWindowStatus(),
                window.getRoom().getId()
        );
    }
}
