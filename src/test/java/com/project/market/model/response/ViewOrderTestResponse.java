package com.project.market.model.response;

import com.project.market.dto.res.payment.ProductDetail;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ViewOrderTestResponse {
    private List<ProductDetail> productDetails;
    private BigDecimal sum;
    private BigDecimal discount;
    private BigDecimal netAmount;
}
