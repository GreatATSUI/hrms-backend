package com.logginghr.hrms.app.service;

import com.logginghr.hrms.app.models.Privilege;
import com.logginghr.hrms.app.models.Role;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader {

    private final RoleService roleService;

    public DataLoader(RoleService roleService) {
        this.roleService = roleService;
    }

    @jakarta.annotation.PostConstruct
    public void loadData() {
        Set<Privilege> adminPrivileges = new HashSet<>();
        adminPrivileges.add(Privilege.READ_PRIVILEGE);
        adminPrivileges.add(Privilege.WRITE_PRIVILEGE);
        adminPrivileges.add(Privilege.EXECUTE_PRIVILEGE);

        Set<Privilege> userPrivileges = new HashSet<>();
        userPrivileges.add(Privilege.READ_PRIVILEGE);

        Set<Privilege> guestPrivileges = new HashSet<>();
        guestPrivileges.add(Privilege.READ_PRIVILEGE);

        // Create Roles
        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        adminRole.setPrivileges(adminPrivileges);
        roleService.saveRole(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("USER");
        userRole.setPrivileges(userPrivileges);
        roleService.saveRole(userRole);

        Role guestRole = new Role();
        guestRole.setRoleName("GUEST");
        guestRole.setPrivileges(guestPrivileges);
        roleService.saveRole(guestRole);

        System.out.println("Sample roles inserted successfully.");
    }
}
