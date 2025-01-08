package com.logginghr.hrms.lms.repository;

import com.logginghr.hrms.lms.models.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, UUID> {}
