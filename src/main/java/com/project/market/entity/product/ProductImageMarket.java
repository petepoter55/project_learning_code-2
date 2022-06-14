package com.project.market.entity.product;

import com.project.market.constant.DatabaseSchema;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "IMAGE_PRODUCT_MARKETS", schema= DatabaseSchema.ImageProductMarket)
public class ProductImageMarket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGEID")
    private int imageId;
    @Column(name = "PRODUCTCODE")
    private String productCode;
    @Column(name = "IMAGE")
    private byte[] image;
    @Column(name = "ACTIVE")
    private String active;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "CREATEDATETIME")
    private Date createDateTime;
    @Column(name = "UPDATEDATETIME")
    private Date updateDateTime;
}
