package com.logginghr.hrms.app.dto;

import lombok.Data;
import java.util.Set;
import java.util.UUID;

@Data
public class UserInfoResponseDTO {
    private UUID userId;
    private String username;
    private Set<RoleDTO> roles;
}
