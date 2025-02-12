package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.LeaveApplication;
import com.example.demo.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/employee")

public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    @PostMapping("/add-employee")
    public ResponseEntity<Map<String, Object>> addNewEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }


    @PostMapping("/apply-for-leave/{employeeId}")
    public ResponseEntity<Map<String, Object>> applyForLeave(@PathVariable String employeeId, @RequestBody LeaveApplication leaveApplication) {
        return employeeService.applyForLeave(employeeId, leaveApplication);
    }

    @GetMapping("/get-all-leaves-by-employee/{employeeId}")
    public ResponseEntity<Map<String, Object>> getAllLeavesOfEmployee(@PathVariable String employeeId) {
        return employeeService.getLeavesByEmployeeId(employeeId);
    }




}
