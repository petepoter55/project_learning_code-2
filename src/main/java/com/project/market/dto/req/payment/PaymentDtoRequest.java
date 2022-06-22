package com.project.market.dto.req.payment;

import com.project.market.entity.product.ProductMarket;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaymentDtoRequest {
    private String username;
    private List<ProductMarket> productMarketList;
}
