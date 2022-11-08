package com.nishiProductions.TestApplication.repository.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserDto {

    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private long phoneNo;
    private String userType;
}
