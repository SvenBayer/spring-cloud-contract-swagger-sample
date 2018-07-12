package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.net.URI;

import static com.example.config.CoffeeProducerSettings.COFFEE_PRODUCER_PREFIX;

/**
 * @author Sven Bayer
 */
@Validated
@Data
@Configuration
@ConfigurationProperties(prefix = COFFEE_PRODUCER_PREFIX)
public class CoffeeProducerSettings {

    static final String COFFEE_PRODUCER_PREFIX = "coffee.producer";

    @NotBlank(message = "Url of producer must be set!")
    private String url;

    @Positive(message = "Port must be set!")
    private int port;

    public URI getTakeoffURI(boolean withWormhole, boolean viaHyperLoop) {
        return UriComponentsBuilder
                .fromUriString(url + ":" + port + "/coffee-rocket-service/v1.0/takeoff")
                .queryParam("withWormhole", withWormhole)
                .queryParam("viaHyperLoop", viaHyperLoop)
                .build()
                .toUri();
    }
}
