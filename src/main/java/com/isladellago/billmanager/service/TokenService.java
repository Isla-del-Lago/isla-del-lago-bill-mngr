package com.isladellago.billmanager.service;

public interface TokenService {

    boolean validate(String token);

    String getEmailFromToken(String token);
}
