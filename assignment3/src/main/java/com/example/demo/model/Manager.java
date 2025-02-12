package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "manager")

public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username, password, email;

}
