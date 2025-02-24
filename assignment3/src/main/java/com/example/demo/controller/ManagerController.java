package com.example.demo.controller;

import com.example.demo.config.Auth;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")


@RestController

@RequestMapping("/api/manager")

public class ManagerController {

    @Autowired
    ManagerService managerService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    Auth auth;

    @PostMapping("/add-employee")
    public ResponseEntity<Map<String, Object>> addNewEmployee(@RequestBody Employee employee, @RequestHeader("Authorization") String authHeader) {
//        ResponseEntity<Map<String, Object>> response=auth.checkForAuth(authHeader, "employeeToken");
//        if(response!=null) return response;
        return employeeService.addEmployee(employee);
    }


    @GetMapping("/get-all-leaves")
    public ResponseEntity<Map<String, Object>> getAllLeaves(@RequestHeader("Authorization") String authHeader) {
        ResponseEntity<Map<String, Object>> response=auth.checkForAuth(authHeader, "managerToken");
        if(response!=null) return response;
        return managerService.getAllLeaves();
    }

    @GetMapping("/get-pending-leaves")
    public ResponseEntity<Map<String, Object>> getAllPendingLeaves(@RequestHeader("Authorization") String authHeader) {
        ResponseEntity<Map<String, Object>> response=auth.checkForAuth(authHeader, "managerToken");
        if(response!=null) return response;
        return managerService.getAllPendingLeaves();
    }

    @PutMapping("/update-leave/{leaveId}")
    public ResponseEntity<Map<String, Object>> updateLeave(@PathVariable String leaveId, @RequestBody Map<String, String> request, @RequestHeader("Authorization") String authHeader) {
        ResponseEntity<Map<String, Object>> response=auth.checkForAuth(authHeader, "managerToken");
        System.out.println(authHeader);
        if(response!=null) return response;
        String status=request.get("status");
        String comment=request.get("comment");
        System.out.println(comment);
        return managerService.updateLeave(leaveId, status, comment);

    }

//    @GetMapping("/get-leaves-overview")
//    public ResponseEntity<List<Object>> getLeavesOverview(@RequestParam Map<String, String> queryParams) {
//        String startDate= queryParams.getOrDefault("startDate", null);
//        String applicationDate=queryParams.getOrDefault("applicationDate", null);
//        String employeeName=queryParams.getOrDefault("username")
//    }

}
