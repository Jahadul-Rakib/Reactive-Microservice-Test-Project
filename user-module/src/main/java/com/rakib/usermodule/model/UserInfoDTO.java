package com.rakib.usermodule.model;

import com.rakib.usermodule.domail.UserRole;
import com.rakib.usermodule.mapper.enums.RoleType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class UserInfoDTO implements Serializable {

    private Long userId;
    private String firstName;
    private String lastName;
    private String userEmail;
    private String phoneNumber;
    private String userPassword;
    private boolean nonExpired;
    private List<RoleType> roles;

}
