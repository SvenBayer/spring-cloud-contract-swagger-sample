package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sven Bayer
 */
@Data
@NoArgsConstructor
public class BeanAsteroid {

    @NoArgsConstructor
    public enum Shape {
        ROUND,
        SQUARE,
        BEAN;
    }
    private String name;
    private int speed;
    private String shape;
}
