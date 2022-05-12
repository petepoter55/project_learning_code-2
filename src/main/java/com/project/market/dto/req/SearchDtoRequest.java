package com.project.market.dto.req;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SearchDtoRequest {
    private String firstName;
    private String lastName;
    private String referenceNo;
    private Date createDateTime;
    private Date updateDateTime;
    private String email;
}
