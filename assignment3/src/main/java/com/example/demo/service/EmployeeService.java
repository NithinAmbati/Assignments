package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.LeaveApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface EmployeeService {
    ResponseEntity<Map<String, Object>> addEmployee(Employee employee);
    ResponseEntity<Map<String, Object>> applyForLeave(String employeeId, LeaveApplication leaveApplication);
    ResponseEntity<Map<String, Object>> deleteLeaveById(String leaveId);
    ResponseEntity<Map<String, Object>> editLeave(String leaveID, LeaveApplication leave);
}
