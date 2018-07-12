package com.example.controller;

import com.example.model.CoffeeRocket;
import com.example.model.LaunchData;
import com.example.service.RocketBuilderService;
import com.example.service.RocketLaunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sven Bayer
 */
@RestController
@RequestMapping("/mission-launch-service/v1.0")
public class MissionLaunchController {

    @Autowired
    private RocketBuilderService rocketBuilderService;

    @Autowired
    private RocketLaunchService rocketLaunchService;

    public MissionLaunchController(RocketBuilderService rocketBuilderService, RocketLaunchService rocketLaunchService) {
        this.rocketBuilderService = rocketBuilderService;
        this.rocketLaunchService = rocketLaunchService;
    }

    @PostMapping(value = "/launch", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> launch(@RequestHeader(value = "X-Request-ID", required = false) String requestId,
                                         @RequestBody LaunchData launchData) {
        CoffeeRocket coffeeRocket = rocketBuilderService.buildRocket(launchData, requestId);
        String planetName = rocketLaunchService.launchRocket(coffeeRocket, requestId);
        return new ResponseEntity<>(planetName, HttpStatus.CREATED);
    }
}
