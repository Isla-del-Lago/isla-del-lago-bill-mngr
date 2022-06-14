package com.isladellago.billmanager.config;

import com.isladellago.billmanager.service.client.UserManagerClient;
import com.isladellago.billmanager.util.CustomHttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    private UserManagerClient userManagerClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {

        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String uuidHeader = request.getHeader(CustomHttpHeaders.UUID_HEADER);

        if (ObjectUtils.isEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            return false;
        }

        final String token = authorizationHeader.split(" ")[1].trim();

        return userManagerClient.validateJwt(uuidHeader, token);
    }

    @Autowired
    public void setUserManagerClient(UserManagerClient userManagerClient) {
        this.userManagerClient = userManagerClient;
    }
}
