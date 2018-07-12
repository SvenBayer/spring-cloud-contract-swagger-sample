package com.example.service;

import com.example.config.CoffeeProducerSettings;
import com.example.model.BeanPlanet;
import com.example.model.CoffeeRocket;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author Sven Bayer
 */
@Service
public class RocketLaunchService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CoffeeProducerSettings coffeeProducerSettings;

    public RocketLaunchService(RestTemplate restTemplate, CoffeeProducerSettings coffeeProducerSettings) {
        this.restTemplate = restTemplate;
        this.coffeeProducerSettings = coffeeProducerSettings;
    }

    public String launchRocket(CoffeeRocket coffeeRocket, String requestId) {
        ResponseEntity<byte[]> response = restTemplate.exchange(
                RequestEntity
                        .post(coffeeProducerSettings.getTakeoffURI(false, false))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Request-ID", requestId)
                        .body(coffeeRocket),
                byte[].class);
        if (HttpStatus.CREATED.equals(response.getStatusCode())) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.readValue(new String(response.getBody()), BeanPlanet.class).getName();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalStateException("Could not launch rocket!");
        }
        return null;
    }
}
