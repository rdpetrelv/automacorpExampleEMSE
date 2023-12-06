package com.emse.spring.automacorp.REST;

import com.emse.spring.automacorp.model.Sensor;

public record WindowRecord(Long id, String name, Sensor windowStatus, Long roomId) {
}
