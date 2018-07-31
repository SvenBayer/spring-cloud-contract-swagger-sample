package com.example.controller;

import com.example.model.BeanPlanet;
import com.example.model.CoffeeRocket;
import com.example.service.CoffeeverseService;
import com.example.service.RateLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sven Bayer
 */
@RestController
@RequestMapping("/coffee-rocket-service/v1.0")
public class CoffeeverseController {

    @Autowired
    private CoffeeverseService coffeeverseService;

    @Autowired
    private RateLimitService rateLimitService;

    public CoffeeverseController(CoffeeverseService coffeeverseService, RateLimitService rateLimitService) {
        this.coffeeverseService = coffeeverseService;
        this.rateLimitService = rateLimitService;
    }

    @PostMapping(value = "/takeoff", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BeanPlanet> takeoff(@RequestHeader(value = "X-Request-ID", required = false) String requestId,
                                              @RequestBody CoffeeRocket coffeeRocket,
                                              @RequestParam(required = false) String withWormhole,
                                              @RequestParam boolean viaHyperLoop) {
        HttpHeaders headers = new HttpHeaders();

        int rateLimit = rateLimitService.getRateLimit();
        headers.add("X-RateLimit-Limit", String.valueOf(rateLimit));
        BeanPlanet beanPlanet = coffeeverseService.takeoff(coffeeRocket, withWormhole, viaHyperLoop, requestId);
        return new ResponseEntity<>(beanPlanet, headers, HttpStatus.CREATED);
    }
}