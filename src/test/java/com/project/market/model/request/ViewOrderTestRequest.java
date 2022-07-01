package com.project.market.model.request;

import com.project.market.dto.req.payment.PaymentDtoRequest;
import com.project.market.entity.product.ProductDiscount;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ViewOrderTestRequest {
    private PaymentDtoRequest paymentDtoRequest;
    private ProductDiscount productDiscount;
}
