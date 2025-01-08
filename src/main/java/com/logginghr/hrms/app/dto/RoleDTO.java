package com.logginghr.hrms.app.dto;

import com.logginghr.hrms.app.models.Privilege;
import lombok.Data;
import java.util.Set;

@Data
public class RoleDTO {
    private String roleName;
    private Set<Privilege> privileges;
}
