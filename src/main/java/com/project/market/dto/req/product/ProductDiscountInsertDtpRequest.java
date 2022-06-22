package com.project.market.dto.req.product;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductDiscountInsertDtpRequest {
    @NotNull
    private String productCode;
    @NotNull
    private BigDecimal discount;
}
