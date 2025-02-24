package com.example.demo.controller;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/employee")
    public ResponseEntity<Map<String, Object>> loginEmployee(@RequestBody  LoginRequestDTO loginRequestDTO) {
        System.out.println(loginRequestDTO.toString());
        return authService.loginService("EMPLOYEE", loginRequestDTO);
    }

    @PostMapping("/manager")
    public ResponseEntity<Map<String, Object>> loginManager(@RequestBody LoginRequestDTO loginRequestDTO) {
        System.out.println(loginRequestDTO.toString());
        return authService.loginService("MANAGER", loginRequestDTO);
    }
}
