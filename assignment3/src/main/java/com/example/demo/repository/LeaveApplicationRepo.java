package com.example.demo.repository;

import com.example.demo.model.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface LeaveApplicationRepo extends JpaRepository<LeaveApplication, String> {

    @Query(value = "select * from leave_applications where employee_id=?1", nativeQuery = true)
    List<LeaveApplication> findByEmployeeId(String employeeID);

    List<LeaveApplication> findByStatus(String pending);


    @Query(value = "select * from leave_applications where start_date<=?1 and end_date>=?1 and status='APPROVED'", nativeQuery = true)
    List<LeaveApplication> activeLeaves(Date todayDate);

}
