package com.project.market.entity.form;

import com.project.market.constant.DatabaseSchema;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "FORM_MANAGEMENT_UPLOAD", schema= DatabaseSchema.FormManagementUpload)
public class FormManagementUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "FILENAME")
    private String fileName;
    @Column(name = "FILE")
    private byte[] file;
    @Column(name = "FILECONTENT")
    private String fileContent;
    @Column(name = "SEQ")
    private int seq;
    @Column(name = "CREATEDATETIME")
    private Date createDateTime;
    @Column(name = "UPDATEDATETIME")
    private Date updateDateTime;
}
