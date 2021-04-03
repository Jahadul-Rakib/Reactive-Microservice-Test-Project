package com.rakib.usermodule.model;

import com.rakib.usermodule.mapper.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenVM implements Serializable {
    private static final long serialVersionUID = 1L;
    private String token;
    private String userEmail;
    private List<RoleType> userRole;
}
