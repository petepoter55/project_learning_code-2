package com.project.market.dto.res;

import lombok.Data;

import java.util.Date;

@Data
public class JwtDtoResponse {
    private String username;
    private String email;
    private String referenceNo;
    private String password;
    private String telephone;
    private Date issueDate;
}
