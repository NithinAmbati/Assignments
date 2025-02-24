package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "employee")

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username, password, email;

    private int leavesLimit=5;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<LeaveApplication> leaveApplications;



}
