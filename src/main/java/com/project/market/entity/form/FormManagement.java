package com.project.market.entity.form;

import com.project.market.constant.DatabaseSchema;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "FORM_MANAGEMENT", schema= DatabaseSchema.FormManagement)
public class FormManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "UPLOAD_ID")
    private int uploadId;
    @Column(name = "VERSION")
    private String version;
    @Column(name = "STARTDATE")
    private Date startDate;
    @Column(name = "ENDDATE")
    private Date endDate;
    @Column(name = "ISACTIVE")
    private String isActive;
    @Column(name = "CREATEDATETIME")
    private Date createDateTime;
    @Column(name = "UPDATEDATETIME")
    private Date updateDateTime;
}
