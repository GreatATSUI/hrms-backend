package com.logginghr.hrms.lms.dto;

import java.time.LocalDate;
import java.util.UUID;

public record LeaveRequestDto(
        UUID userId,
        UUID leaveTypeId,
        LocalDate startDate,
        LocalDate endDate,
        String comment
) {}