package com.logginghr.hrms.app.controller;

import com.logginghr.hrms.app.dto.UserInfoRequestDTO;
import com.logginghr.hrms.app.dto.UserInfoResponseDTO;
import com.logginghr.hrms.app.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/register")
public class UserRegistrationController {

    private final UserInfoService userInfoService;

    public UserRegistrationController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping
    public ResponseEntity<UserInfoResponseDTO> createUser(@RequestBody UserInfoRequestDTO request) {
        UserInfoResponseDTO response = userInfoService.createUser(request);
        return ResponseEntity.ok(response);
    }
}
