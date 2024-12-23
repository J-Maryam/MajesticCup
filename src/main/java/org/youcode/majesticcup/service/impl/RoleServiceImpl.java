package org.youcode.majesticcup.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.majesticcup.model.collections.Role;
import org.youcode.majesticcup.repository.RoleRepository;
import org.youcode.majesticcup.service.RoleService;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public Role addRole(Role role) {
//        if (role.getName() == null || role.getName().isBlank()) {
//            throw new IllegalArgumentException("Role's name cannot be null or blank.");
//        }
        return repository.save(role);
    }
}
