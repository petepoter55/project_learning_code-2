package com.project.market.entity.payment;

import com.project.market.constant.DatabaseSchema;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "HISTORY_ORDER", schema = DatabaseSchema.HistoryOrder)
public class HistoryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDERID")
    private int orderId;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PRODUCTCODE")
    private String productCode;
    @Column(name = "PRODUCTNAME")
    private String productName;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "CREATEDATETIME")
    private Date createDateTime;
    @Column(name = "UPDATEDATETIME")
    private Date updateDateTime;
}
