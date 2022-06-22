package com.project.market.entity.repository.product;

import com.project.market.entity.product.ProductDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDiscountRepository extends JpaRepository<ProductDiscount, Integer> {
    ProductDiscount findByProductCode(String productCode);
}
