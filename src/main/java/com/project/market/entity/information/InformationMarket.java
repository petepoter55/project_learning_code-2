package com.project.market.entity.information;

import com.project.market.constant.DatabaseSchema;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "INFORMATION_MARKET", schema= DatabaseSchema.InformationMarket)
public class InformationMarket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "REFERENCENO")
    private String referenceNo;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "ADDRESS1")
    private String address1;
    @Column(name = "ADDRESS2")
    private String address2;
    @Column(name = "ROAD")
    private String road;
    @Column(name = "DISTRICTNAME")
    private String districtName;
    @Column(name = "SUBPROVINCENAME")
    private String subProvinceName;
    @Column(name = "PROVINCENAME")
    private String provinceName;
    @Column(name = "POSTCODE")
    private String postcode;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TEL")
    private String telephone;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "CREATEDATETIME")
    private Date createDateTime;
    @Column(name = "UPDATEDATETIME")
    private Date updateDateTime;

}
