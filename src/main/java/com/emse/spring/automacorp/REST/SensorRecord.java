package com.emse.spring.automacorp.REST;

import com.emse.spring.automacorp.model.SensorType;

public record SensorRecord(Long id, String name, Double value, SensorType sensorType) {
}
