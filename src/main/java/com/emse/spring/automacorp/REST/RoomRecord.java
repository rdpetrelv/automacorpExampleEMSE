package com.emse.spring.automacorp.REST;

import com.emse.spring.automacorp.model.Sensor;

public record RoomRecord(Long id, int floor, String name, double temperature, double target_temperature) {
}
