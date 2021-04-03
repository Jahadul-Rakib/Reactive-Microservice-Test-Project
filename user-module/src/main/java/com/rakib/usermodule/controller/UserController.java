package com.rakib.usermodule.controller;

import com.rakib.usermodule.configuration.AppConstant;
import com.rakib.usermodule.configuration.MessageConfig;
import com.rakib.usermodule.model.LogInVM;
import com.rakib.usermodule.model.UserInfoDTO;
import com.rakib.usermodule.user_services.UserService;
import com.rakib.usermodule.user_utilities.response.ResponseData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(AppConstant.API_PREFIX + "/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public Mono<ResponseEntity<?>> createUser(@RequestBody UserInfoDTO infoDTO) {
        return ResponseData.responseEntityMono(HttpStatus.CREATED, MessageConfig.getMessage("user.create"),
                userService.createUser(infoDTO));
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<?>> getUserLogIN(@RequestBody LogInVM logInVM) throws Exception {
        return ResponseData.responseEntityMono(HttpStatus.OK, MessageConfig.getMessage("user.jwt.token"),
                userService.getJWTToken(logInVM));
    }
}
