package com.rakib.usermodule.user_services.impl;

import com.rakib.usermodule.repository.UserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserInfoRepository infoRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return infoRepository.findByUserEmail(s)
                .orElseThrow(() -> new UsernameNotFoundException("User not Found By Email Address "+s));
    }
}
