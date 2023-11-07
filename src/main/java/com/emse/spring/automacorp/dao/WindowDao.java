package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.WindowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WindowDao extends JpaRepository<WindowEntity,Long >, WindowDaoCustom{
    @Query("select c from WindowEntity c where c.name =: name")
    WindowEntity findByName(@Param("name") String name);
}
