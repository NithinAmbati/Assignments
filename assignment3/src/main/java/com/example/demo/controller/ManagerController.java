package com.example.demo.controller;

import com.example.demo.model.LeaveApplication;
import com.example.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @GetMapping("/get-all-leaves")
    public ResponseEntity<Map<String, Object>> getAllLeaves() {
        return managerService.getAllLeaves();
    }

    @PutMapping("/update-leave/{leaveId}")
    public ResponseEntity<Map<String, Object>> updateLeave(@PathVariable String leaveId, @RequestBody Map<String, String> request) {
        String status=request.get("status");
        return managerService.updateLeave(leaveId, status);

    }

//    @GetMapping("/get-leaves-overview")
//    public ResponseEntity<List<Object>> getLeavesOverview(@RequestParam Map<String, String> queryParams) {
//        String startDate= queryParams.getOrDefault("startDate", null);
//        String applicationDate=queryParams.getOrDefault("applicationDate", null);
//        String employeeName=queryParams.getOrDefault("username")
//    }

}
