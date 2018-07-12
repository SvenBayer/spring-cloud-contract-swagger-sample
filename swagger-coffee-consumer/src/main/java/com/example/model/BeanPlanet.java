package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Sven Bayer
 */
@Data
@NoArgsConstructor
public class BeanPlanet {
    private String name;
    private int size;
    private List<BeanAsteroid> asteroids;
}
