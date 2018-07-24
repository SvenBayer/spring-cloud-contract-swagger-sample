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
        beanPlanet.setSize(3474);

        List<BeanAsteroid> asteroids = new ArrayList<>();
        BeanAsteroid asteroid = new BeanAsteroid();
        asteroid.setName("Asteroid");
        asteroid.setShape(BeanAsteroid.Shape.BEAN);
        asteroid.setSpeed(2000);
        asteroids.add(asteroid);
        beanPlanet.setAsteroids(asteroids);

        CoffeeRocket coffeeRocket = new CoffeeRocket();
        coffeeRocket.setRocketName("Beanpollo 13");

        BeanItinerary beanItinerary = new BeanItinerary();
        beanItinerary.setDeparture("Earth");
        beanItinerary.setDestination("Moon");
        coffeeRocket.setItinerary(beanItinerary);

        coffeeRocket.setFuel(300000.5);
        coffeeRocket.setWeight(45931.0);
        coffeeRocket.setBeanonauts(Collections.singletonList(new Beanonaut("Tom Hanks", 62)));

        given(coffeeverseService.takeoff(eq(coffeeRocket), eq(false), eq(false), eq("123456"))).willReturn(beanPlanet);
        given(rateLimitService.getRateLimit()).willReturn(50);
        RestAssuredMockMvc.standaloneSetup(coffeeverseController);
    }
}
