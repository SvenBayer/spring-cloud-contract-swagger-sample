package com.example.controller;

import com.example.model.CoffeeRocket;
import com.example.model.LaunchData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.json.JacksonTester;

/**
 * @author Sven Bayer
 */
@AutoConfigureJsonTesters
public class AbstractContractTest {

    public JacksonTester<LaunchData> coffeeRocketJson;

    @Before
    public void setup() {
        ObjectMapper objectMappper = new ObjectMapper();
        JacksonTester.initFields(this, objectMappper);
    }
}
