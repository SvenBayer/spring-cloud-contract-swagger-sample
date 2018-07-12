package com.example.controller;

import com.example.config.CoffeeProducerSettings;
import com.example.model.BeanItinerary;
import com.example.model.Beanonaut;
import com.example.model.CoffeeRocket;
import com.example.model.LaunchData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Sven Bayer
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "de.svenbayer.blog:swagger-coffee-producer-simple:+:stubs")
@DirtiesContext
public class MissionLaunchControllerTest extends AbstractContractTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MissionLaunchController missionLaunchController;

    @Autowired
    private CoffeeProducerSettings coffeeProducerSettings;

    @StubRunnerPort("swagger-coffee-producer-simple")
    int producerPort;

    @Before
    public void setupPort() {
        coffeeProducerSettings.setPort(producerPort);
    }

    @Test
    public void should_launch_rocket() throws Exception {
        LaunchData launchData = new LaunchData();
        launchData.setDeparture("departure");
        launchData.setDestination("destination");
        launchData.setRocketName("rocketName");

        mockMvc.perform(MockMvcRequestBuilders.post("/mission-launch-service/v1.0/launch")
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-REQUEST-ID", "123456")
                .content(coffeeRocketJson.write(launchData).getJson()))
                .andExpect(status().isCreated())
                .andExpect(content().string("name"));
    }
}