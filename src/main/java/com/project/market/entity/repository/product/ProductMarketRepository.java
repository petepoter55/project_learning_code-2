package com.project.market.entity.repository.product;

import com.project.market.entity.product.ProductMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductMarketRepository extends JpaRepository<ProductMarket,Integer> {
    ProductMarket findByProductName(String productName);

    @Query("SELECT m FROM ProductMarket m WHERE m.productCode LIKE %:productCode%")
    List<ProductMarket> findByProductCode(
            @Param("productCode") String productCode
    );
}
