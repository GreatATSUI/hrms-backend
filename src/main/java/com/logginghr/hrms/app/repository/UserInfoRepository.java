package com.logginghr.hrms.app.repository;

import com.logginghr.hrms.app.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {
    Optional<UserInfo> findByUsername(String username);
}
