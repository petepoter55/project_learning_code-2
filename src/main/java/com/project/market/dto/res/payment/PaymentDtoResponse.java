package com.project.market.dto.res.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PaymentDtoResponse {
    private List<ProductDetail> productDetails;
    private BigDecimal sum;
    private BigDecimal discount;
    private BigDecimal netAmount;
}
