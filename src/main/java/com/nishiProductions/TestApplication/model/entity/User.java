package com.nishiProductions.TestApplication.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private long userId;
    @Column(name = "first_name", length = 45)
    private String firstName;
    @Column(name = "last_name", length = 45)
    private String lastName;
    @Column(name = "email", length = 45)
    private String email;
    @Column(name = "phone_no", length = 45)
    private long phoneNo;
    @Column(name = "user_type", length = 45)
    private String userType;

}
