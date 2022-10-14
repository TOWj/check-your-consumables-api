package com.nov.checkyourconsumablesapi.services;

import com.nov.checkyourconsumablesapi.models.Consumables;
import com.nov.checkyourconsumablesapi.models.UserInfo;
import com.nov.checkyourconsumablesapi.repositories.ConsumablesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsumablesServiceTest {

    @Mock
    ConsumablesRepository consumablesRepository;

    @InjectMocks
    ConsumablesService consumablesService;

    UserInfo userInfo;

    Consumables consumables;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setLogin("login");
        userInfo.setPassword("password");
        userInfo.setConsumablesList(setConsumablesListForTest());

        consumables = new Consumables();
        consumables.setId(1);
        consumables.setName("testCons");
        consumables.setMileageNow(10000);
        consumables.setReplacementAfter(10000);
        consumables.setOwnerUser(userInfo);
    }

    @Test
    void findAllByPersonId() {
        when(consumablesRepository.findConsumablesByOwnerUser(userInfo)).thenReturn(setConsumablesListForTest());

        List<Consumables> list = consumablesService.findAllByPersonId(1);
        assertNotNull(list);
    }

    @Test
    void addConsumables() {
        //    @Transactional
//    public void addConsumables(Consumables consumables) {
//        consumablesRepository.save(consumables);
//    }
    }

    @Test
    void updateConsumables() {
        //    @Transactional
//    public void updateConsumables(int id, Consumables updatedConsumables) {
//        updatedConsumables.setId(id);
//        consumablesRepository.save(updatedConsumables);
//    }
    }

    private List<Consumables> setConsumablesListForTest() {
        List<Consumables> list = new ArrayList<>();
        Consumables consumables1 = new Consumables("test1", 10000, 10000);
        Consumables consumables2 = new Consumables("test2", 20000, 20000);
        Consumables consumables3 = new Consumables("test3", 30000, 30000);
        list.add(consumables1);
        list.add(consumables2);
        list.add(consumables3);
        return list;
    }

    private List<UserInfo> setUserInfoList() {
        List<UserInfo> list = new ArrayList<>();
        list.add(userInfo);
        list.add(userInfo);
        list.add(userInfo);
        list.add(userInfo);
        return list;
    }

}