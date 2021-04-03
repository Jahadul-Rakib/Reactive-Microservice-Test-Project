package com.rakib.usermodule.mapper;

import com.rakib.usermodule.domail.UserRole;
import com.rakib.usermodule.model.UserRoleDTO;

public interface RoleMapper {
    UserRole dtoToEntity(UserRoleDTO roleDTO);
    UserRoleDTO entityToDto(UserRole role);
}
