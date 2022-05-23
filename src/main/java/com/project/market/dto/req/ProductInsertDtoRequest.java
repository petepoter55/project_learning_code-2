package com.project.market.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductInsertDtoRequest {
    private String productCode;
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("productCount")
    private Integer productCount;
    @JsonProperty("active")
    private String active;
    @JsonProperty("type")
    private String type;
    @JsonProperty("price")
    private BigDecimal price;
}
