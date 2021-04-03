package com.rakib.usermodule.controller;

import com.rakib.usermodule.configuration.AppConstant;
import com.rakib.usermodule.configuration.MessageConfig;
import com.rakib.usermodule.model.UserRoleDTO;
import com.rakib.usermodule.user_services.RoleService;
import com.rakib.usermodule.user_utilities.response.ResponseData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(AppConstant.API_PREFIX + "/role")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public Mono<ResponseEntity<?>> createRole(@RequestBody UserRoleDTO roleDTO) {
        return ResponseData.responseEntityMono(HttpStatus.CREATED, MessageConfig.getMessage("role.create"),
                roleService.saveRole(roleDTO));
    }

    @GetMapping
    public Flux<ResponseEntity<?>> getRole() {
        return ResponseData.responseEntityFlux(HttpStatus.OK, MessageConfig.getMessage("role.get.all"),
                roleService.getRole());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<?>> getRoleByID(@PathVariable(value = "id") Long roleID) {
        return ResponseData.responseEntityMono(HttpStatus.OK, MessageConfig.getMessage("role.get.byID"),
                roleService.getRoleByID(roleID));
    }
}
