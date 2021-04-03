package com.rakib.usermodule.user_services.impl;

import com.rakib.usermodule.configuration.AppConstant;
import com.rakib.usermodule.domail.UserInfo;
import com.rakib.usermodule.domail.UserRole;
import com.rakib.usermodule.mapper.UserMapper;
import com.rakib.usermodule.model.LogInVM;
import com.rakib.usermodule.model.TokenVM;
import com.rakib.usermodule.model.UserInfoDTO;
import com.rakib.usermodule.repository.UserInfoRepository;
import com.rakib.usermodule.user_services.UserService;
import com.rakib.usermodule.user_utilities.exception.classes.InvalidKeyException;
import com.rakib.usermodule.user_utilities.exception.classes.NotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserInfoRepository infoRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<UserInfo> createUser(UserInfoDTO infoDTO) {
        return infoRepository.save(mapper.dtoToEntity(infoDTO));
    }

    @Override
    public Mono<TokenVM> getJWTToken(LogInVM logInVM) throws Exception {
        UserInfo users = infoRepository.findByUserEmail(logInVM.getUserEmail())
                .orElseThrow(() -> new NotFoundException("User Not Found."));
        boolean checkPassword = passwordEncoder.matches(logInVM.getUserPassword(), users.getPassword());
        if (!checkPassword) {
            throw new InvalidKeyException("Credential is incorrect");
        }
        Collection<? extends GrantedAuthority> collection = users.getAuthorities();
        List<String> authorityList = collection.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        String accessToken = Jwts.builder()
                .setSubject("Token")
                .setIssuer(users.getUserEmail())
                .claim("authorities", authorityList.stream().map(String::valueOf).collect(Collectors.joining(",")))
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .signWith(Keys.hmacShaKeyFor(AppConstant.SECURITY_SECRET.getBytes()))
                .compact();
        return Mono.just(TokenVM.builder().token(accessToken).userEmail(users.getUserEmail())
                .userRole(users.getRoles().stream().map(UserRole::getRoleName).collect(Collectors.toList())).build());
    }
}
