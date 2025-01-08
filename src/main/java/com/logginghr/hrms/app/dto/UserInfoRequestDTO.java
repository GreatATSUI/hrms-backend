package com.logginghr.hrms.app.dto;

import com.logginghr.hrms.app.models.Role;
import lombok.Data;
import java.util.Set;

@Data
public class UserInfoRequestDTO {
    private String username;
    private String password;
    private Set<Role> roles; // Roles will be linked by their IDs
}
