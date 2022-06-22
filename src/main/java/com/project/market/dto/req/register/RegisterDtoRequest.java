package com.project.market.dto.req.register;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterDtoRequest {
    @NotNull
    @Size(min = 1,max = 100)
    private String username;
    @NotNull
    @Size(min = 1,max = 100)
    private String password;
    @NotNull
    @Size(min = 1,max = 50)
    private String email;
    @NotNull
    @Size(min = 1,max = 50)
    private String confirmEmail;
    @NotNull
    @Size(min = 1,max = 10)
    private String telephone;
    @NotNull
    @Size(min = 1,max = 10)
    private String refNoInformation;
}
