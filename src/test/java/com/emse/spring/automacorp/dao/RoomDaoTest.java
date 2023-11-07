package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.WindowEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RoomDaoTest {

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private WindowDao windowDao;

    @Test
    public void shouldFindARoomByID(){
        RoomEntity room = roomDao.getReferenceById(-9L);
        Assertions.assertThat(room.getName()).isEqualTo("Room2");
        Assertions.assertThat(room.getTemperature().getValue()).isEqualTo(21.3);
        //Assertions.assertThat(room.getWindowsList().)
    }

}
