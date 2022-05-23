package com.project.market.entity.product;

import com.project.market.constant.DatabaseSchema;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "PRODUCT_DESCRIPTION_MARKET", schema= DatabaseSchema.ProductMarketDescription)
public class ProductMarketDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "PRODUCTCODE")
    private String productCode;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRICE")
    private String price;
    @Column(name = "CREATEDATETIME")
    private Date createDateTime;
    @Column(name = "UPDATEDATETIME")
    private Date updateDateTime;
}
