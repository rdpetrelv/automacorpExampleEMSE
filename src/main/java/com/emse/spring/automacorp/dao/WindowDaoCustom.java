package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.WindowEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WindowDaoCustom {
    //@Query
    List<WindowEntity> findRoomWithOpenWindows(Long id);
}
