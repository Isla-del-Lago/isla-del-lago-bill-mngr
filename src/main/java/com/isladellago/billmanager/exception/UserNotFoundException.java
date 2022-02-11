package com.isladellago.billmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class UserNotFoundException extends RuntimeException {

    private final String email;
}
