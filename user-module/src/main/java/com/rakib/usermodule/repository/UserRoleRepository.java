package com.rakib.usermodule.repository;

import com.rakib.usermodule.domail.UserRole;
import com.rakib.usermodule.mapper.enums.RoleType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UserRoleRepository extends ReactiveMongoRepository<UserRole, Long> {
    UserRole findByRoleName(RoleType roleName);
}
