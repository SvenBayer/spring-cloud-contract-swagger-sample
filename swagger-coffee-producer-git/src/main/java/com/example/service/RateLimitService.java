package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class RateLimitService {

    public int getRateLimit() {
        return 10;
    }
}
