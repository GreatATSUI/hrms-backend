package com.logginghr.hrms.lms.service;

import com.logginghr.hrms.app.models.UserInfo;
import com.logginghr.hrms.app.repository.UserInfoRepository;
import com.logginghr.hrms.lms.enums.LeaveStatus;
import com.logginghr.hrms.lms.models.LeaveApplication;
import com.logginghr.hrms.lms.models.LeaveType;
import com.logginghr.hrms.lms.repository.LeaveApplicationRepository;
import com.logginghr.hrms.lms.repository.LeaveTypeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class LeaveService {

    private final UserInfoRepository userRepository;
    private final LeaveTypeRepository leaveTypeRepository;
    private final LeaveApplicationRepository leaveApplicationRepository;

    public LeaveService(UserInfoRepository userRepository,
                        LeaveTypeRepository leaveTypeRepository,
                        LeaveApplicationRepository leaveApplicationRepository) {
        this.userRepository = userRepository;
        this.leaveTypeRepository = leaveTypeRepository;
        this.leaveApplicationRepository = leaveApplicationRepository;
    }

    public List<LeaveType> getAllLeaveTypes() {
        return leaveTypeRepository.findAll();
    }

    public LeaveApplication applyForLeave(UUID userId, UUID leaveTypeId, LocalDate startDate, LocalDate endDate, String comment) {
        UserInfo user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LeaveType leaveType = leaveTypeRepository.findById(leaveTypeId)
                .orElseThrow(() -> new RuntimeException("LeaveType not found"));

        LeaveApplication leaveApplication = new LeaveApplication();
        leaveApplication.setUser(user);
        leaveApplication.setLeaveType(leaveType);
        leaveApplication.setStartDate(startDate);
        leaveApplication.setEndDate(endDate);
        leaveApplication.setStatus(LeaveStatus.PENDING);
        leaveApplication.setComment(comment);
        leaveApplication.setAppliedDate(LocalDate.now());

        return leaveApplicationRepository.save(leaveApplication);
    }

    public LeaveApplication approveLeave(UUID applicationId, UUID approverId) {
        LeaveApplication leaveApplication = leaveApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("LeaveApplication not found"));

        UserInfo approver = userRepository.findById(approverId)
                .orElseThrow(() -> new RuntimeException("Approver not found"));

        leaveApplication.setApprovedBy(approver);
        leaveApplication.setStatus(LeaveStatus.APPROVED);

        return leaveApplicationRepository.save(leaveApplication);
    }

    public LeaveApplication rejectLeave(UUID applicationId, String comment) {
        LeaveApplication leaveApplication = leaveApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("LeaveApplication not found"));

        leaveApplication.setComment(comment);
        leaveApplication.setStatus(LeaveStatus.REJECTED);

        return leaveApplicationRepository.save(leaveApplication);
    }
}
