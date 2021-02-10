package com.example.myauth;

public class LoginRequest {

    private String phone;
    private String otp;

    public LoginRequest(String phone, String otp) {
        this.phone = phone;
        this.otp = otp;
    }

    public LoginRequest(String phone) {
        this.phone = phone;
    }
}
