package com.rakib.usermodule.model;

import com.rakib.usermodule.mapper.enums.RoleType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserRoleDTO implements Serializable {

    private Long roleId;
    private RoleType roleName;

}
