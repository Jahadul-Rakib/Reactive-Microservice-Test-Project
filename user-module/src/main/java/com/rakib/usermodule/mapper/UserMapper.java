package com.rakib.usermodule.mapper;

import com.rakib.usermodule.domail.UserInfo;
import com.rakib.usermodule.model.UserInfoDTO;

public interface UserMapper {
    UserInfo dtoToEntity(UserInfoDTO infoDTO);
    UserInfoDTO entityToDto(UserInfo userInfo);
}
