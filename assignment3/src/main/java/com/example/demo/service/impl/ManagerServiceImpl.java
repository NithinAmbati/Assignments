package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.LeaveApplication;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.repository.LeaveApplicationRepo;
import com.example.demo.repository.ManagerRepo;
import com.example.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerRepo managerRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    LeaveApplicationRepo leaveApplicationRepo;

    public ResponseEntity<Map<String, Object>> getAllLeaves() {
        Map<String, Object> response=new HashMap<>();
        try{
            List<LeaveApplication> data=leaveApplicationRepo.findAll();
            response.put("message", "Leaves of all the employees retrieved successfully.");
            response.put("data", data);

        } catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.status(200).body(response);
    }

    public ResponseEntity<Map<String, Object>> getAllPendingLeaves() {
        Map<String, Object> response=new HashMap<>();
        try{
            List<LeaveApplication> data=leaveApplicationRepo.findByStatus("PENDING");
            response.put("message", "All the pending leaves retrieved successfully.");
            response.put("data", data);

        } catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.status(200).body(response);
    }

    public ResponseEntity<Map<String, Object>> updateLeave(String leaveId, String status, String comment) {
        System.out.println(status);
        Map<String, Object> response=new HashMap<>();
        try{
            LeaveApplication leaveApplication=leaveApplicationRepo.findById(leaveId).orElse(null);
            if(leaveApplication==null) {
                response.put("message", "Leave application not found");
                return ResponseEntity.status(404).body(response);
            }


            String employeeId=leaveApplication.getEmployeeId();
            Employee employee=employeeRepo.findById(employeeId).orElse(null);

            if(employee==null) {
                response.put("message", "Employee not Found");
                return ResponseEntity.status(404).body(response);
            }

            if(leaveApplication.getStatus().equals("APPROVED")) {
                response.put("message", "Already updated");
                return ResponseEntity.status(200).body(response);
            }

            if(status.equals("REJECT")) {
                leaveApplication.setComment(comment);
                leaveApplication.setStatus("REJECTED");
                response.put("message", "Leave Rejected succesfully");
                leaveApplicationRepo.save(leaveApplication);
                return ResponseEntity.status(200).body(response);
            }

            if(employee.getLeavesLimit()==0) {
                response.put("message", "Leaves limit of the employee has been exhaused");
                return ResponseEntity.status(200).body(response);
            }


            leaveApplication.setStatus("APPROVED");
            employee.setLeavesLimit(employee.getLeavesLimit()-1);


            leaveApplicationRepo.save(leaveApplication);
            response.put("message", "Leave updated successfully for employee with employeeId : " + leaveApplication.getEmployeeId());

        } catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.status(200).body(response);
    }

    public ResponseEntity<Map<String, Object>> getActiveLeaves(Date todayDate) {
        Map<String, Object> response=new HashMap<>();
        try{
            List<LeaveApplication> data=leaveApplicationRepo.activeLeaves(todayDate);
            response.put("data" , data);
            response.put("message", "Active leaves Data retrieved successfully.");
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }


    public ResponseEntity<Map<String, Object>> adf() {
        Map<String, Object> response=new HashMap<>();
        return ResponseEntity.status(200).body(response);
    }

}
