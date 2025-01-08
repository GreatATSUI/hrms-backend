package com.logginghr.hrms.app.controller;

import com.logginghr.hrms.app.dto.UserInfoRequestDTO;
import com.logginghr.hrms.app.dto.UserInfoResponseDTO;
import com.logginghr.hrms.app.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserInfoController {
    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping
    public ResponseEntity<UserInfoResponseDTO> createUser(@RequestBody UserInfoRequestDTO request) {
        UserInfoResponseDTO response = userInfoService.createUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoResponseDTO> getUser(@PathVariable UUID id) {
        UserInfoResponseDTO response = userInfoService.getUser(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserInfoResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userInfoService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserInfoResponseDTO> updateUser(
            @PathVariable UUID id,
            @RequestBody UserInfoRequestDTO request) {
        UserInfoResponseDTO response = userInfoService.updateUser(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userInfoService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
