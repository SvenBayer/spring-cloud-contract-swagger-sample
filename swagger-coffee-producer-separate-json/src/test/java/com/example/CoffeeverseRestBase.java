package com.example;

import com.example.controller.CoffeeverseController;
import com.example.model.*;
import com.example.service.CoffeeverseService;
import com.example.service.RateLimitService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.BDDMockito.given;

/**
 * @author Sven Bayer
 */
@RunWith(MockitoJUnitRunner.class)
public class CoffeeverseRestBase {

    @Mock private CoffeeverseService coffeeverseService;
    @Mock private RateLimitService rateLimitService;
    @InjectMocks private CoffeeverseController coffeeverseController;

    @Before
    public void setup() {
        BeanPlanet beanPlanet = new BeanPlanet();
        beanPlanet.setName("Moon");
        beanPlanet.setSize(20);

        List<BeanAsteroid> asteroids = new ArrayList<>();
        BeanAsteroid asteroid = new BeanAsteroid();
        asteroid.setName("Beansteroid");
        asteroid.setShape(BeanAsteroid.Shape.BEAN);
        asteroid.setSpeed(200);
        asteroids.add(asteroid);
        beanPlanet.setAsteroids(asteroids);

        CoffeeRocket coffeeRocket = new CoffeeRocket();
        coffeeRocket.setRocketName("Apollo ll");

        BeanItinerary beanItinerary = new BeanItinerary();
        beanItinerary.setDeparture("Earth");
        beanItinerary.setDestination("Moon");
        coffeeRocket.setItinerary(beanItinerary);

        coffeeRocket.setFuel(1.1);
        coffeeRocket.setWeight(1.1);
        coffeeRocket.setBeanonauts(Collections.singletonList(new Beanonaut("Bean Beanos", 30)));

        given(coffeeverseService.takeoff(eq(coffeeRocket), eq("true"), eq(true), eq("123456"))).willReturn(beanPlanet);
        given(rateLimitService.getRateLimit()).willReturn(1);
        RestAssuredMockMvc.standaloneSetup(coffeeverseController);
    }
}
