package com.example.demo.dto;


import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class LoginRequestDTO {
    private String email, password;
}
