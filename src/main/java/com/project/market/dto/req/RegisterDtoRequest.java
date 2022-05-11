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
    private String address1;
    private String address2;
    private String road;
    private String districtName;
    private String subProvinceName;
    private String provinceName;
    private String postcode;
    private String type;
}
