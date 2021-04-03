package com.rakib.usermodule.domail;

import com.rakib.usermodule.mapper.enums.RoleType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Data
@Builder
@Document
public class UserRole implements Serializable, GrantedAuthority {
    @Transient
    public static final String SEQUENCE_NAME = "role_sequence";

    @Id
    private Long roleId;
    private RoleType roleName;

    @Override
    public String getAuthority() {
        return this.getRoleName().toString();
    }
}
