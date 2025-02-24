package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "leave_applications")

public class LeaveApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String leaveType, reason, status, comment;
    private Date startDate, endDate, applicationDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;

    @JsonProperty("employeeId")
    public String getEmployeeId() {
        return employee != null ? employee.getId() : null;
    }

    @JsonProperty("employeeName")
    public String getEmployeeName() {
        return employee != null ? employee.getUsername() : null;
    }

}
