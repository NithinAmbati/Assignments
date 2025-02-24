package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Auth {
    public ResponseEntity<Map<String, Object>> checkForAuth(String authHeader, String requiredToken) {

        Map<String, Object> response=new HashMap<>();

        if(authHeader==null) {
            response.put("message", "Authentication Required.");
            return ResponseEntity.status(401).body(response);
        }

        if(!authHeader.equals(requiredToken)) {
            response.put("message", "UnAuthorized.");
            return ResponseEntity.status(401).body(response);
        }

        return null;
    }
}
