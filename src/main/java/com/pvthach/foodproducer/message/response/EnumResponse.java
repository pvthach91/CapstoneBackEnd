package com.pvthach.foodproducer.message.response;

/**
 * Created by THACH-PC
 */
public enum EnumResponse {
    USERNAME_NOT_FOUND("Username not found"),
    USERNAME_INVALID("Username must be greater than 3 and less than 50 characters"),
    USERNAME_EXIST("Username is already taken"),
    PASSWORD_INVALID("Password must be greater than 3 and less than 100 characters"),
    EMAIL_INVALID("Email is invalid"),
    PHONE_INVALID("Phone is invalid"),
    EMAIL_EXIST("Email is already in use"),
    ROLE_NOT_FOUND("Role not found"),
    USER_REGISTERED_SUCCESS("User registered successfully"),
    USER_PROMOTE_SUCCESS("User promoted successfully"),
    UNAUTHORIZED("Unauthorized");

    private String description;

    private EnumResponse(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
