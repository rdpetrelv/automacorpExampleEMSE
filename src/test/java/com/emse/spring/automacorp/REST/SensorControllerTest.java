package com.emse.spring.automacorp.REST;

import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.model.Sensor;
import com.emse.spring.automacorp.model.SensorType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

@WebMvcTest(SensorController.class)
public class SensorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SensorDao sensorDao;

    Sensor createSensor(Long id, String name) {
        Sensor sensorEntity = new Sensor(name, SensorType.TEMPERATURE);
        sensorEntity.setId(id);
        sensorEntity.setValue(24.2);
        return sensorEntity;
    }

    @Test
    void shouldFindAll() throws Exception {
        Mockito.when(sensorDao.findAll()).thenReturn(List.of(
                createSensor(1L, "Temperature room 1"),
                createSensor(2L, "Temperature room 2")
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/sensors").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("[*].name")
                                .value(Matchers.containsInAnyOrder("Temperature room 1", "Temperature room 2"))
                );
    }

    @Test
    void shouldReturnNullWhenFindByUnknownId() throws Exception {
        Mockito.when(sensorDao.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/sensors/999").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @Test
    void shouldFindById() throws Exception {
            Sensor sensorEntity = createSensor(1L, "Temperature room 1");
            Mockito.when(sensorDao.findById(999L)).thenReturn(Optional.of(sensorEntity));

            mockMvc.perform(MockMvcRequestBuilders.get("/api/sensors/999").accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Temperature room 1"));
    }

    @Test
    void shouldNotUpdateUnknownEntity() throws Exception {
        Sensor sensorEntity = createSensor(1L, "Temperature room 1");
        SensorCommand expectedSensor = new SensorCommand(sensorEntity.getName(), sensorEntity.getValue(), sensorEntity.getSensorType());
        String json = objectMapper.writeValueAsString(expectedSensor);

        Mockito.when(sensorDao.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put("/api/sensors/1")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                // check the HTTP response
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldUpdate() throws Exception {
        Sensor sensorEntity = createSensor(1L, "Temperature room 1");
        SensorCommand expectedSensor = new SensorCommand(sensorEntity.getName(), sensorEntity.getValue(), sensorEntity.getSensorType());
        String json = objectMapper.writeValueAsString(expectedSensor);

        Mockito.when(sensorDao.findById(1L)).thenReturn(Optional.of(sensorEntity));

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put("/api/sensors/1")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                // check the HTTP response
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Temperature room 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    }

    @Test
    void shouldCreate() throws Exception {
        Sensor sensorEntity = createSensor(1L, "Temperature room 1");
        SensorCommand expectedSensor = new SensorCommand(sensorEntity.getName(), sensorEntity.getValue(), sensorEntity.getSensorType());
        String json = objectMapper.writeValueAsString(expectedSensor);

        Mockito.when(sensorDao.existsById(1L)).thenReturn(false);
        Mockito.when(sensorDao.save(Mockito.any(Sensor.class))).thenReturn(sensorEntity);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/sensors")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                // check the HTTP response
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Temperature room 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    }

    @Test
    void shouldDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/sensors/999"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
