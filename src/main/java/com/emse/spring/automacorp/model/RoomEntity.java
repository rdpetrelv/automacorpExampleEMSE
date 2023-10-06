package com.emse.spring.automacorp.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name= "ROOM")
public class RoomEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, name = "FLOOR")
    private int floor;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private Sensor temperature;

    @Column(name = "TARGET_TEMPERATURE")
    private double target_temperature;

    @OneToMany(mappedBy = "room")
    private Set<WindowEntity> windowsList = Set.of();

    public RoomEntity() {
    }


    public RoomEntity(long id, int floor, String name, Sensor current_temperature_id, double targetTemperature){//Set<WindowEntity> windowsList//) {
        this.id = id;
        this.floor = floor;
        this.name = name;
        this.temperature = current_temperature_id;
        //this.target_temperature = targetTemperature;
        //this.windowsList = windowsList;
    }

    public long getId() {
        return id;
    }

    public int getFloor() {
        return floor;
    }

    public String getName() {
        return name;
    }

    public Sensor getTemperature() {
        return temperature;
    }

    public double getTargetTemperature() {
        return target_temperature;
    }

    public Set<WindowEntity> getWindowsList() {
        return windowsList;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTemperature(Sensor temperature) {
        this.temperature = temperature;
    }

    public void setTargetTemperature(double targetTemperature) {
        this.target_temperature = targetTemperature;
    }

    public void setWindowsList(Set<WindowEntity> windowsList) {
        this.windowsList = windowsList;
    }
}
