package com.emse.spring.automacorp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SP_WINDOW")
public class WindowEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private Sensor windowStatus;

    @ManyToOne
    private RoomEntity room;


    public WindowEntity() {
    }

    public WindowEntity(String name, Sensor windowStatus, RoomEntity room) {
        this.name = name;
        this.windowStatus = windowStatus;
        this.room = room;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Sensor getWindowStatus() {
        return windowStatus;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWindowStatus(Sensor windowStatus) {
        this.windowStatus = windowStatus;
    }
}
