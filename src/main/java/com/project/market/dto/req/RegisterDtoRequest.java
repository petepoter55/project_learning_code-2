package com.project.market.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDtoRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String telephone;

}
