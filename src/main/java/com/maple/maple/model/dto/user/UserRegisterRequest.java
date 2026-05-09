package com.maple.maple.model.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -6000965795765646860L;
    private String userAccount;
    private String userPassword;
    private String checkPassword;

}
