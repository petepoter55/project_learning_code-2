package com.project.market.entity.product;

import com.project.market.constant.DatabaseSchema;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "PRODUCT_DISCOUNT", schema = DatabaseSchema.ProductDiscount)
public class ProductDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDERID")
    private int orderId;
    @Column(name = "PRODUCTCODE")
    private String productCode;
    @Column(name = "DISCOUNT")
    private BigDecimal discount;
    @Column(name = "CREATEDATETIME")
    private Date createDateTime;
    @Column(name = "UPDATEDATETIME")
    private Date updateDateTime;
}
