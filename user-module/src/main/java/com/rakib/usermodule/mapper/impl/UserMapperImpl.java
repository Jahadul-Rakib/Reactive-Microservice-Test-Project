package com.rakib.usermodule.mapper.impl;

import com.rakib.usermodule.domail.UserInfo;
import com.rakib.usermodule.domail.UserRole;
import com.rakib.usermodule.mapper.UserMapper;
import com.rakib.usermodule.model.UserInfoDTO;
import com.rakib.usermodule.user_services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapperImpl implements UserMapper {
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    @Override
    public UserInfo dtoToEntity(UserInfoDTO infoDTO) {

        List<UserRole> roleList = infoDTO.getRoles().stream()
                .map(roleService::getRoleByRoleName)
                .collect(Collectors.toList());
        roleService.getRole();

        return UserInfo.builder()
                .userId(infoDTO.getUserId())
                .firstName(infoDTO.getFirstName())
                .lastName(infoDTO.getLastName())
                .userEmail(infoDTO.getUserEmail())
                .phoneNumber(infoDTO.getPhoneNumber())
                .userPassword(passwordEncoder.encode(infoDTO.getUserPassword()))
                .nonExpired(infoDTO.isNonExpired())
                .roles(roleList)
                .build();
    }

    @Override
    public UserInfoDTO entityToDto(UserInfo userInfo) {
        return UserInfoDTO.builder()
                .userId(userInfo.getUserId())
                .firstName(userInfo.getFirstName())
                .lastName(userInfo.getLastName())
                .userEmail(userInfo.getUserEmail())
                .phoneNumber(userInfo.getPhoneNumber())
                .nonExpired(userInfo.isNonExpired())
                .roles(userInfo.getRoles().stream().map(UserRole::getRoleName).collect(Collectors.toList()))
                .build();
    }
}
