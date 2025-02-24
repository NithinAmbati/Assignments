package com.example.demo.service;

import com.example.demo.dto.LoginRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Service
public interface AuthService {
    ResponseEntity<Map<String, Object>> loginService(String userRole, LoginRequestDTO loginRequestDTO);
}
