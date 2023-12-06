package com.emse.spring.automacorp.REST;

import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.Sensor;
import com.emse.spring.automacorp.model.SensorType;
import com.emse.spring.automacorp.model.WindowEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class WindowMapperTest {
    @Test
    void shouldMapWindow(){
        ///Arrange
        Sensor currentTemperature = new Sensor("Room Temperature", SensorType.TEMPERATURE);
        currentTemperature.setId(1L);
        currentTemperature.setValue(24.2);

        RoomEntity roomEntity = new RoomEntity(1L, 1, "Room", currentTemperature, 22.0);

        Sensor windowStatus = new Sensor("Window Status", SensorType.STATUS);
        windowStatus.setId(2L);
        windowStatus.setValue(0.0);

        WindowEntity windowEntity = new WindowEntity("Window 1", windowStatus, roomEntity);
        windowEntity.setId(1L);
        roomEntity.setWindowsList(Set.of(windowEntity));

        //Act
        WindowRecord windowRecord = WindowMapper.of(windowEntity);

        //Assert
        WindowRecord expectedWindowRecord = new WindowRecord(
            1L,
                "Window 1",
                windowStatus,
                1L
        );
        Assertions.assertThat(windowRecord).usingRecursiveAssertion().isEqualTo(expectedWindowRecord);

    }
}
