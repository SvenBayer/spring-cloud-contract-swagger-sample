package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Sven Bayer
 */
@Data
public class CoffeeRocket {
    private String rocketName;
    private BeanItinerary itinerary;
    private List<Beanonaut> beanonauts;
    private double fuel;
    private double weight;
}
