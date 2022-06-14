package com.project.market.dto.req;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class InformationInsertDtoRequest {

    @Size(max = 255)
    private String firstname;
    @Size(max = 255)
    private String lastname;
    @Size(max = 50)
    private String email;
    @Size(max = 50)
    private String telephone;
    @Size(max = 90)
    private String address1;
    @Size(max = 150)
    private String address2;
    @Size(max = 150)
    private String road;
    @Size(max = 150)
    private String districtName;
    @Size(max = 150)
    private String subProvinceName;
    @Size(max = 150)
    private String provinceName;
    @Size(max = 5)
    private String postcode;
    @Size(max = 255)
    private String type;
}
