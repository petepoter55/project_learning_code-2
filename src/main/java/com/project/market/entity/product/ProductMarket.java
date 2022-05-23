package com.project.market.entity.product;

import com.project.market.constant.DatabaseSchema;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "PRODUCT_MARKET", schema= DatabaseSchema.ProductMarket)
public class ProductMarket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCTID")
    private int productId;
    @Column(name = "PRODUCTCODE")
    private String productCode;
    @Column(name = "PRODUCTNAME")
    private String productName;
    @Column(name = "PRODUCTCOUNT")
    private Integer productCount;
    @Column(name = "ACTIVE")
    private String active;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "CREATEDATETIME")
    private Date createDateTime;
    @Column(name = "UPDATEDATETIME")
    private Date updateDateTime;

}
