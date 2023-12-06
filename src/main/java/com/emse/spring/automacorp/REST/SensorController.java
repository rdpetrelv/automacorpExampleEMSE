package com.emse.spring.automacorp.REST;

import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.model.Sensor;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/sensors")
@Transactional
public class SensorController {
    private final SensorDao sensorDao;

    public SensorController(SensorDao sensorDao){
        this.sensorDao = sensorDao;
    }

    @GetMapping
    public List<SensorRecord> findAll(){
        return sensorDao.findAll()
                .stream()
                .map(SensorMapper::of)
                .sorted(Comparator.comparing(SensorRecord::name))
                .collect(Collectors.toList());
    }

    @GetMapping(path ="/{id}")
    public SensorRecord findById(@PathVariable Long id){
        return sensorDao.findById(id).map(SensorMapper::of).orElse(null);
    }

    @PostMapping
    public ResponseEntity<SensorRecord> create(@RequestBody SensorCommand sensorCommand){
        Sensor entity = new Sensor(sensorCommand.name(), sensorCommand.sensorType());
        entity.setValue(sensorCommand.value());
        Sensor saved = sensorDao.save(entity);
        return ResponseEntity.ok(SensorMapper.of(saved));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SensorRecord> update(@PathVariable Long id, @RequestBody SensorCommand sensorCommand){
        Sensor entity = sensorDao.findById(id).orElse(null);
        if (entity == null){
            return ResponseEntity.badRequest().build();
        }
        entity.setValue(sensorCommand.value());
        entity.setName(sensorCommand.name());
        entity.setSensorType(sensorCommand.sensorType());
        return ResponseEntity.ok(SensorMapper.of(entity));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id){
        sensorDao.deleteById(id);
    }


}
