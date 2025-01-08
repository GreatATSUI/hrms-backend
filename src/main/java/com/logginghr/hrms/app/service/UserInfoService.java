package com.logginghr.hrms.app.service;

import com.logginghr.hrms.app.dto.RoleDTO;
import com.logginghr.hrms.app.dto.UserInfoRequestDTO;
import com.logginghr.hrms.app.dto.UserInfoResponseDTO;
import com.logginghr.hrms.app.models.Role;
import com.logginghr.hrms.app.models.UserInfo;
import com.logginghr.hrms.app.repository.RoleRepository;
import com.logginghr.hrms.app.repository.UserInfoRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserInfoService(UserInfoRepository userInfoRepository, RoleRepository roleRepository) {
        this.userInfoRepository = userInfoRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserInfoResponseDTO createUser(UserInfoRequestDTO request) {
        Set<Role> roles = roleRepository.findAllById(request.getRoles().stream().map(Role::getRoleId).toList()).stream().collect(Collectors.toSet());

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(request.getUsername());
        userInfo.setPassword(passwordEncoder.encode(request.getPassword()));
        userInfo.setRoles(roles);

        UserInfo savedUser = userInfoRepository.save(userInfo);
        return mapToResponseDTO(savedUser);
    }

    public UserInfoResponseDTO getUser(UUID userId) {
        UserInfo userInfo = userInfoRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToResponseDTO(userInfo);
    }

    public List<UserInfoResponseDTO> getAllUsers() {
        return userInfoRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public UserInfoResponseDTO updateUser(UUID userId, UserInfoRequestDTO request) {
        UserInfo userInfo = userInfoRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<Role> roles = roleRepository.findAllById(request.getRoles().stream().map(Role::getRoleId).toList()).stream().collect(Collectors.toSet());
        userInfo.setUsername(request.getUsername());
        userInfo.setPassword(passwordEncoder.encode(request.getPassword()));
        userInfo.setRoles(roles);

        UserInfo updatedUser = userInfoRepository.save(userInfo);
        return mapToResponseDTO(updatedUser);
    }

    public void deleteUser(UUID userId) {
        if (!userInfoRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        userInfoRepository.deleteById(userId);
    }

    private UserInfoResponseDTO mapToResponseDTO(UserInfo userInfo) {
        UserInfoResponseDTO response = new UserInfoResponseDTO();
        response.setUserId(userInfo.getUserId());
        response.setUsername(userInfo.getUsername());
        response.setRoles(userInfo.getRoles().stream().map(this::mapToRoleDTO).collect(Collectors.toSet()));
        return response;
    }

    private RoleDTO mapToRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleName(role.getRoleName());
        roleDTO.setPrivileges(role.getPrivileges());
        return roleDTO;
    }
}
