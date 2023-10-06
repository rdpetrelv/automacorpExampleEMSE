package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SensorDao extends JpaRepository<Sensor, Long> {
    @Query("select c from Sensor c where c.name =: name")
    Sensor findByName(@Param("name") String name);
}
