package com.nov.checkyourconsumablesapi.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsumablesTest {

    @Test
    public void createTest() {
        Consumables consumables = new Consumables("oil", 10000, 10000);
        assertTrue(consumables.getName() == "oil");
        assertTrue(consumables.getMileageNow() == 10000);
        assertTrue(consumables.getReplacementAfter() == 10000);

    }

}