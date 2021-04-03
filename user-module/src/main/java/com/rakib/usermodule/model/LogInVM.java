package com.rakib.usermodule.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogInVM {
    private String userEmail;
    private String userPassword;
}
