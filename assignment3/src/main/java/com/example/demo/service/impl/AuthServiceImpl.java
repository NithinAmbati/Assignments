package com.example.demo.service.impl;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.model.Employee;
import com.example.demo.model.Manager;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.repository.ManagerRepo;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service

public class AuthServiceImpl implements AuthService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    ManagerRepo managerRepo;

    public ResponseEntity<Map<String, Object>> loginService(String userRole, LoginRequestDTO userDetails) {

        System.out.println(userDetails);
        Map<String, Object> response=new HashMap<>();

        if(userDetails==null) {
            response.put("message", "asdfsdf");
            return ResponseEntity.status(400).body(response);
        }

        if(userDetails.getEmail()==null || userDetails.getPassword()==null) {
            response.put("message", "Invalid input");
            return ResponseEntity.status(400).body(response);
        }

        if(userRole.equals("EMPLOYEE")) {
            Employee user=employeeRepo.findByEmail(userDetails.getEmail());
            if(user==null) {
                response.put("message", "Invalid Username");
                return ResponseEntity.status(401).body(response);
            }
            if(user.getPassword().equals(userDetails.getPassword())) {
                Map<String, Object> jwtToken=new HashMap<>();
                jwtToken.put("role", "EMPLOYEE");
                jwtToken.put("id", user.getId());
                jwtToken.put("username", user.getUsername());
                response.put("jwtToken", jwtToken);

                response.put("message", "Login success.");
                return ResponseEntity.status(200).body(response);
            }
            else {
                response.put("message", "Login failure");
                return ResponseEntity.status(401).body(response);
            }
        }
        else if(userRole.equals("MANAGER")) {
            Manager user=managerRepo.findByEmail(userDetails.getEmail());
            if(user==null) {
                response.put("message", "Invalid Username");
                return ResponseEntity.status(401).body(response);
            }
            if(user.getPassword().equals(userDetails.getPassword())) {
                Map<String, Object> jwtToken=new HashMap<>();
                jwtToken.put("role", "MANAGER");
                jwtToken.put("id", user.getId());
                jwtToken.put("username", user.getUsername());
                response.put("jwtToken", jwtToken);
                response.put("message", "Login success.");
                return ResponseEntity.status(200).body(response);
            }
            else {
                response.put("message", "Login failure");
                return ResponseEntity.status(401).body(response);
            }
        }

        response.put("message", "Role not found");
        return ResponseEntity.status(200).body(response);
    }
}
