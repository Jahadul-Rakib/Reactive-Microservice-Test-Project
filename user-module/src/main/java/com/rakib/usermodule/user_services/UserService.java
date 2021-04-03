package com.rakib.usermodule.user_services;

import com.rakib.usermodule.domail.UserInfo;
import com.rakib.usermodule.model.LogInVM;
import com.rakib.usermodule.model.TokenVM;
import com.rakib.usermodule.model.UserInfoDTO;
import com.rakib.usermodule.user_utilities.exception.classes.InvalidKeyException;
import com.rakib.usermodule.user_utilities.exception.classes.NotFoundException;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserInfo> createUser(UserInfoDTO infoDTO);

    Mono<TokenVM> getJWTToken(LogInVM logInVM) throws NotFoundException, InvalidKeyException, Exception;
}
