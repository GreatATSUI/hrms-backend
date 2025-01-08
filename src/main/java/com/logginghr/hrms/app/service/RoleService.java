package com.logginghr.hrms.app.service;

import com.logginghr.hrms.app.models.Role;
import com.logginghr.hrms.app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Create or update a role
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    // Get all roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Get a role by ID
    public Optional<Role> getRoleById(UUID roleId) {
        return roleRepository.findById(roleId);
    }

    // Get a role by name
    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    // Delete a role by ID
    public void deleteRole(UUID roleId) {
        roleRepository.deleteById(roleId);
    }
}
