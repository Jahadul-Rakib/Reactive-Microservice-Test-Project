package com.rakib.usermodule.user_services.impl;

import com.rakib.usermodule.domail.UserRole;
import com.rakib.usermodule.mapper.RoleMapper;
import com.rakib.usermodule.mapper.enums.RoleType;
import com.rakib.usermodule.model.UserRoleDTO;
import com.rakib.usermodule.repository.UserRoleRepository;
import com.rakib.usermodule.user_services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final UserRoleRepository roleRepository;
    private final RoleMapper mapper;

    @Override
    public Mono<UserRole> saveRole(UserRoleDTO roleDTO) {
        return roleRepository.save(mapper.dtoToEntity(roleDTO));
    }

    @Override
    public Flux<UserRole> getRole() {
        return roleRepository.findAll();
    }

    @Override
    public Mono<UserRole> getRoleByID(Long ID) {
        return roleRepository.findById(ID);
    }

    @Override
    public UserRole getRoleByRoleName(RoleType roleType) {
        return roleRepository.findByRoleName(roleType);
    }
}
