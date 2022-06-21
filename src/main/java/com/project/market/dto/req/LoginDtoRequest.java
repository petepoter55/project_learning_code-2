package com.project.market.dto.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDtoRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
