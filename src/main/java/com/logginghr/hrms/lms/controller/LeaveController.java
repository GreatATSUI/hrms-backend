package com.logginghr.hrms.lms.controller;

import com.logginghr.hrms.lms.dto.LeaveRequestDto;
import com.logginghr.hrms.lms.models.LeaveApplication;
import com.logginghr.hrms.lms.models.LeaveType;
import com.logginghr.hrms.lms.service.LeaveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/types")
    public List<LeaveType> getAllLeaveTypes() {
        return leaveService.getAllLeaveTypes();
    }

    @PostMapping("/apply")
    public LeaveApplication applyForLeave(@RequestBody LeaveRequestDto request) {
        return leaveService.applyForLeave(
                request.userId(),
                request.leaveTypeId(),
                request.startDate(),
                request.endDate(),
                request.comment()
        );
    }

    @PutMapping("/approve/{id}")
    public LeaveApplication approveLeave(@PathVariable UUID id, @RequestParam UUID approverId) {
        return leaveService.approveLeave(id, approverId);
    }

    @PutMapping("/reject/{id}")
    public LeaveApplication rejectLeave(@PathVariable UUID id, @RequestBody String comment) {
        return leaveService.rejectLeave(id, comment);
    }
}