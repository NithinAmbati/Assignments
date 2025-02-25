package com.example.demo.dto;


import lombok.*;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class EditLeaveApplicationDTO {
    private String id;
    private String reason, leaveType, status, comment;
    private Date startDate, endDate;
    private String employeeId;
}
