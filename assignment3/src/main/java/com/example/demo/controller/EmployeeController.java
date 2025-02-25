package com.example.demo.controller;

import com.example.demo.config.Auth;
import com.example.demo.dto.EditLeaveApplicationDTO;
import com.example.demo.model.LeaveApplication;
import com.example.demo.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:4200")

public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    @Autowired
    Auth auth;

    @PostMapping("/apply-leave/{employeeId}")
    public ResponseEntity<Map<String, Object>> applyForLeave(@PathVariable String employeeId, @RequestBody LeaveApplication leaveApplication, @RequestHeader("Authorization") String authHeader) {
        ResponseEntity<Map<String, Object>> response=auth.checkForAuth(authHeader, "employeeToken");
        if(response!=null) return response;
        return employeeService.applyForLeave(employeeId, leaveApplication);

    }

    @GetMapping("/get-all-leaves-by-employee/{employeeId}")
    public ResponseEntity<Map<String, Object>> getAllLeavesOfEmployee(@PathVariable String employeeId, @RequestHeader("Authorization") String authHeader) {
        ResponseEntity<Map<String, Object>> response=auth.checkForAuth(authHeader, "employeeToken");
        if(response!=null) return response;
        return employeeService.getLeavesByEmployeeId(employeeId);
    }

    @DeleteMapping("/delete-leave/{leaveId}")
    public ResponseEntity<Map<String, Object>> deleteLeave(@PathVariable String leaveId, @RequestHeader("Authorization") String authHeader) {
        ResponseEntity<Map<String, Object>> response=auth.checkForAuth(authHeader, "employeeToken");
        if(response!=null) return response;
        System.out.println(leaveId);
        return employeeService.deleteLeaveById(leaveId);
    }

    @PutMapping("/edit-leave/{leaveId}")
    public ResponseEntity<Map<String, Object>> editLeave(@PathVariable String leaveId, @RequestBody EditLeaveApplicationDTO leave, @RequestHeader("Authorization") String authHeader) {
        ResponseEntity<Map<String, Object>> response=auth.checkForAuth(authHeader, "employeeToken");
        if(response!=null) return response;
        System.out.println(leave.toString());
        return employeeService.editLeave(leaveId, leave);
    }

}
