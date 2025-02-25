package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public interface ManagerService {
    ResponseEntity<Map<String, Object>> getAllLeaves();

    ResponseEntity<Map<String, Object>> getAllPendingLeaves();

    ResponseEntity<Map<String, Object>> updateLeave(String leaveId, String status, String comment);

    ResponseEntity<Map<String, Object>> getActiveLeaves(Date todayDate);
}
