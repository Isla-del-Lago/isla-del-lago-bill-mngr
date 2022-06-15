package com.isladellago.billmanager.service.client;

import com.isladellago.billmanager.domain.dto.ValidateTokenReq;
import com.isladellago.billmanager.domain.dto.ValidateTokenRes;
import com.isladellago.billmanager.util.PathUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "securityManagerClient", url = "${security.manager.root.url}")
public interface SecurityManagerClient {

    @RequestMapping(method = RequestMethod.POST, value = PathUtils.SecurityManagerClient.VALIDATE_TOKEN_PATH)
    ValidateTokenRes validateToken(@RequestBody ValidateTokenReq validateTokenBody);
}
