package com.project.market.dto.res.payment;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDetail {
    private String productCode;
    private String productName;
    private BigDecimal price;
}
