package com.logginghr.hrms.app.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID roleId;

    private String roleName;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Privilege> privileges;
}
