package com.project.market.entity.register;

import com.project.market.constant.DatabaseSchema;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "USER", schema = DatabaseSchema.User)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "JWT_DATA")
    private String jwt_data;
    @Column(name = "AUTHENTICATE")
    private String authenticate;
    @Column(name = "STATUSRENEWPASSWORD")
    private String statusRenewPassword;
    @Column(name = "CREATEDATETIME")
    private Date createDateTime;
    @Column(name = "UPDATEDATETIME")
    private Date updateDateTime;
}
