package com.duett.auth.security;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoginAttemptService {

    private final int MAX_ATTEMPT = 5;

    private final ConcurrentHashMap<String, Integer> attempts = new ConcurrentHashMap<>();

    public void loginSucceeded(String key) {
        attempts.remove(key);
    }

    public void loginFailed(String key) {

        int attemptsCount = attempts.getOrDefault(key, 0);

        attempts.put(key, attemptsCount + 1);
    }

    public boolean isBlocked(String key) {

        return attempts.getOrDefault(key, 0) >= MAX_ATTEMPT;
    }
}