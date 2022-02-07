package com.isladellago.billmanager.service;

import com.isladellago.billmanager.domain.model.User;

public interface UserService {

    User getUserByEmail(String emailFromToken);
}
