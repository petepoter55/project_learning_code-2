package com.project.market.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SearchResponseTest {
    private int id;
    private String referenceNo;
    private String firstName;
    private String lastName;
    private String type;
    private String address1;
    private String address2;
    private String road;
    private String districtName;
    private String subProvinceName;
    private String provinceName;
    private String postcode;
    private String email;
    private String telephone;
    private String status;
    private Date createDateTime;
    private Date updateDateTime;
}
