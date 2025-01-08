package com.logginghr.hrms.lms.models;


import com.logginghr.hrms.app.models.UserInfo;
import com.logginghr.hrms.lms.enums.LeaveStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class LeaveApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private UserInfo user;

    @ManyToOne
    private LeaveType leaveType;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    private String comment;

    @ManyToOne
    private UserInfo approvedBy;

    private LocalDate appliedDate;

    // Getters and Setters
}