package com.rakib.usermodule.domail;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@Document
public class UserInfo implements Serializable, UserDetails {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";
    @Id
    private Long userId;
    private String firstName;
    private String lastName;
    private String userEmail;
    private String phoneNumber;
    private String userPassword;
    private boolean nonExpired;
    private List<UserRole> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthority = new ArrayList<>();
        for (UserRole value : this.getRoles()) {
            grantedAuthority.add(new SimpleGrantedAuthority(value.getRoleName().toString()));
        }
        return grantedAuthority;
    }

    @Override
    public String getPassword() {
        return this.getUserPassword();
    }

    @Override
    public String getUsername() {
        return this.getUserEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isNonExpired();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.isNonExpired();
    }
}
