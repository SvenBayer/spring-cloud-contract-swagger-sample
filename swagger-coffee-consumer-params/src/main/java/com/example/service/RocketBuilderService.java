package com.example.service;

import com.example.model.BeanItinerary;
import com.example.model.Beanonaut;
import com.example.model.CoffeeRocket;
import com.example.model.LaunchData;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Sven Bayer
 */
@Service
public class RocketBuilderService {

    private static final double FUEL = 300000.5d;
    private static final double WEIGHT = 45931.0d;
    private static final List<Beanonaut> BEANONAUTS = Collections.singletonList(new Beanonaut("Tom Hanks", 62));

    public CoffeeRocket buildRocket(LaunchData launchData, String requestId) {
        CoffeeRocket coffeeRocket = new CoffeeRocket();
        coffeeRocket.setRocketName(launchData.getRocketName());

        BeanItinerary beanItinerary = new BeanItinerary();
        beanItinerary.setDeparture(launchData.getDeparture());
        beanItinerary.setDestination(launchData.getDestination());

        coffeeRocket.setFuel(FUEL);
        coffeeRocket.setWeight(WEIGHT);
        coffeeRocket.setItinerary(beanItinerary);
        coffeeRocket.setBeanonauts(BEANONAUTS);
        return coffeeRocket;
    }
}
