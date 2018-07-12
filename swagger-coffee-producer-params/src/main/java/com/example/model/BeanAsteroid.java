package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sven Bayer
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeanAsteroid {

    @NoArgsConstructor
    public enum Shape {
        ROUND,
        SQUARE,
        BEAN;
    }
    private String name;
    private int speed;
    private Shape shape;
}
