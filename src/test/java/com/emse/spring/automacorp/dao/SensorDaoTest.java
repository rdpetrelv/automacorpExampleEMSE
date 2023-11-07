package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.Sensor;
import com.emse.spring.automacorp.model.SensorType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class SensorDaoTest {

    @Autowired
    private SensorDao sensorDao;

    @Test
    public void shouldFindASensorByID(){
        Sensor sensor = sensorDao.getReferenceById(-7L);
        //SensorType type =
        Assertions.assertThat(sensor.getName()).isEqualTo("Window 1 status room 2");
        //Assertions.assertThat(sensor.getSensorType()).isEqualTo()
    }
}
