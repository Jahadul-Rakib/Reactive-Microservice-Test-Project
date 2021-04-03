package com.rakib.usermodule.mapper.impl;

import com.rakib.usermodule.domail.UserRole;
import com.rakib.usermodule.mapper.RoleMapper;
import com.rakib.usermodule.model.UserRoleDTO;
import org.springframework.stereotype.Component;

@Component
public class RoleMapperImpl implements RoleMapper {
    @Override
    public UserRole dtoToEntity(UserRoleDTO roleDTO) {
        return UserRole.builder()
                .roleId(roleDTO.getRoleId())
                .roleName(roleDTO.getRoleName()).build();
    }

    @Override
    public UserRoleDTO entityToDto(UserRole role) {
        return UserRoleDTO.builder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .build();
    }
}
