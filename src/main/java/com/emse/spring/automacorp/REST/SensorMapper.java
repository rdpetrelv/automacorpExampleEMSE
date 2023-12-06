package com.emse.spring.automacorp.REST;

import com.emse.spring.automacorp.model.Sensor;

public class SensorMapper {
    public static SensorRecord of(Sensor sensor) {
        return new SensorRecord(
                sensor.getId(),
                sensor.getName(),
                sensor.getValue(),
                sensor.getSensorType()
        );
    }
}
