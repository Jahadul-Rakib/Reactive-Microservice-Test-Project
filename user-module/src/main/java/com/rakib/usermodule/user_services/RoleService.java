package com.rakib.usermodule.user_services;

import com.rakib.usermodule.domail.UserRole;
import com.rakib.usermodule.mapper.enums.RoleType;
import com.rakib.usermodule.model.UserRoleDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoleService {
    Mono<UserRole> saveRole(UserRoleDTO roleDTO);
    Flux<UserRole> getRole();
    Mono<UserRole> getRoleByID(Long ID);
    UserRole getRoleByRoleName(RoleType roleType);
}
