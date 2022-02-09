package com.isladellago.billmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserNotFoundException extends RuntimeException {

    private final String email;
}
