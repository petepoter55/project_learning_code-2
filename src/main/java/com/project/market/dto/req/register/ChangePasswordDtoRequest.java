package com.project.market.dto.req.register;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordDtoRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;
    @NotBlank
    private String confirmNewPassword;
}
