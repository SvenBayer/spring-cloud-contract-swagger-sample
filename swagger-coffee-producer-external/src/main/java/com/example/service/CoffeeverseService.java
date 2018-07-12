package com.example.service;

import com.example.model.BeanAsteroid;
import com.example.model.BeanPlanet;
import com.example.model.CoffeeRocket;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static com.example.model.BeanAsteroid.Shape.BEAN;

@Service
public class CoffeeverseService {

    public BeanPlanet takeoff(CoffeeRocket coffeeRocket, boolean withWormhole, boolean viaHyperLoop, String requestId) {
        BeanPlanet beanPlanet = new BeanPlanet();
        beanPlanet.setName(coffeeRocket.getItinerary().getDestination());
        beanPlanet.setSize(new Random().nextInt());
        beanPlanet.setAsteroids(Arrays.asList(new BeanAsteroid("Phobos", 17, BEAN), new BeanAsteroid("Deimos", 14, BEAN)));
        return beanPlanet;
    }
}
