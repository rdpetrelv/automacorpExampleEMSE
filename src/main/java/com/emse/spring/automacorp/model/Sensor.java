package com.emse.spring.automacorp.model;

import com.emse.spring.automacorp.REST.SensorRecord;
import jakarta.persistence.*;

@Entity
@Table(name = "SP_SENSOR")
public class Sensor {

    @Id
    @GeneratedValue
    public long id;

    @Column(nullable = false, length = 255)
    public String name;

    @Column(name = "sensor_value")
    private Double value;

    @Column(name = "sensor_type")
    @Enumerated(EnumType.STRING)
    private SensorType sensorType;

    public Sensor() {
    }

    public Sensor(String name, SensorType sensorType) {
        this.name = name;
        this.sensorType = sensorType;
    }

    public Sensor(long id, String name, double value,  SensorType sensorType) {
        this.name = name;
        this.value = value;
        this.sensorType = sensorType;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

}
