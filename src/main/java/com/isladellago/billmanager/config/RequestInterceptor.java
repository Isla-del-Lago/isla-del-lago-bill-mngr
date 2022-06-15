package com.isladellago.billmanager.config;

import com.isladellago.billmanager.domain.dto.ValidateTokenReq;
import com.isladellago.billmanager.service.client.SecurityManagerClient;
import com.isladellago.billmanager.util.CustomHttpHeaders;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Log4j2
public class RequestInterceptor implements HandlerInterceptor {

    private SecurityManagerClient securityManagerClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (ObjectUtils.isEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return false;
        }

        final String token = authorizationHeader.split(" ")[1].trim();

        final Boolean tokenIsValid = securityManagerClient.validateToken(
                ValidateTokenReq.builder().token(token).build()
        ).getIsValid();

        if (!tokenIsValid) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        return true;
    }

    @Autowired
    public void setUserManagerClient(SecurityManagerClient securityManagerClient) {
        this.securityManagerClient = securityManagerClient;
    }
}
