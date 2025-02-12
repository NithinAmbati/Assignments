package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.LeaveApplication;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.repository.LeaveApplicationRepo;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    LeaveApplicationRepo leaveApplicationRepo;

    public ResponseEntity<Map<String, Object>> addEmployee(Employee employee) {
        System.out.println(employee);
        Map<String, Object> response=new HashMap<>();

        // Initially while creating a user there will be no leaves. so keeping leave applications as empty list.
        if(employee.getLeaveApplications()==null) {
            employee.setLeaveApplications(new ArrayList<>());
        }

        if(employee.getUsername()==null || employee.getPassword()==null || employee.getEmail()==null) {
            response.put("message", "Invalid Input");
            return ResponseEntity.status(400).body(response);
        }

        try {
            employeeRepo.save(employee);
        }catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        response.put("message", "Employee Added successfully");
        return ResponseEntity.status(201).body(response);
    }

    public ResponseEntity<Map<String, Object>> applyForLeave(String employeeId, LeaveApplication leaveApplication) {
        System.out.println(leaveApplication);
        Map<String, Object> response=new HashMap<>();
        
        if(leaveApplication.getStatus()==null) {
            leaveApplication.setStatus("PENDING");
        }

        if(leaveApplication.getStartDate()==null || leaveApplication.getEndDate()==null || leaveApplication.getLeaveType()==null || leaveApplication.getReason()==null) {
            response.put("message", "Invalid Input");
            return ResponseEntity.status(400).body(response);
        }
        
        try{
            Employee employee = employeeRepo.findById(employeeId).orElse(null);

            if(employee==null) {
                response.put("message", "Employee Not found");
                return ResponseEntity.status(404).body(response);
            }

            if(employee.getLeavesLimit()==0) {
                response.put("message", "Your Leaves has be completed.");
                return ResponseEntity.status(200).body(response);
            }

            leaveApplication.setEmployee(employee);
            leaveApplicationRepo.save(leaveApplication);
            response.put("message", "Leave applied successfully");
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
        
        return ResponseEntity.status(201).body(response);
    }

    public ResponseEntity<Map<String , Object>> getLeavesByEmployeeId(String employeeId) {
        System.out.println(employeeId);
        Map<String, Object> response=new HashMap<>();
        try{
            List<LeaveApplication> data= leaveApplicationRepo.findByEmployeeId(employeeId);
            response.put("message", "Successfully retrieved Leaves data of employee with employeeId : " + employeeId);
            response.put("data", data);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

}
