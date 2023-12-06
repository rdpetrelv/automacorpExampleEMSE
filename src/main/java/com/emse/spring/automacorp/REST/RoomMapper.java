package com.emse.spring.automacorp.REST;

import com.emse.spring.automacorp.model.RoomEntity;

public class RoomMapper {
    public static RoomRecord of(RoomEntity room) {
        return new RoomRecord(
                room.getId(),
                room.getFloor(),
                room.getName(),
                room.getTemperature().getValue(),
                room.getTargetTemperature()
        );
    }
}
