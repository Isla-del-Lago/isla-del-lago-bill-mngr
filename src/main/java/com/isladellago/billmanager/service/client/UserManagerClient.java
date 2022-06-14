package com.isladellago.billmanager.service.client;

import com.isladellago.billmanager.util.CustomHttpHeaders;
import com.isladellago.billmanager.util.PathUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "userManagerClient", url = "${user.manager.root.url}")
public interface UserManagerClient {

    @RequestMapping(method = RequestMethod.POST, value = PathUtils.UserManagerClient.VALIDATE_JWT_PATH)
    boolean validateJwt(@RequestHeader(CustomHttpHeaders.UUID_HEADER) String uuidHeader,
                        @PathVariable(PathUtils.UserManagerClient.JWT_TOKEN_PATH_PARAM) String jwtToken);
}
