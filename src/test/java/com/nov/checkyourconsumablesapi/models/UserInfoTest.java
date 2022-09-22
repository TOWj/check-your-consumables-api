package com.nov.checkyourconsumablesapi.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoTest {

    @Test
    public void createTest() {
        UserInfo userInfo = new UserInfo(11, "TOW");
        assertTrue(userInfo.getId() == 11);
        assertTrue(userInfo.getLogin() == "TOW");
    }

}