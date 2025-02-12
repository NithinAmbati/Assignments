package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ManagerService {
    ResponseEntity<Map<String, Object>> getAllLeaves();

    ResponseEntity<Map<String, Object>> updateLeave(String leaveId, String status);
}
