package com.isladellago.billmanager.controller;

import com.isladellago.billmanager.util.LogUtils;
import com.isladellago.billmanager.util.PathUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathUtils.BASE_PATH)
@CrossOrigin("*") //NOSONAR
public class HealthController {

    @GetMapping(PathUtils.HEALTH_PATH)
    public final ResponseEntity<String> health() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(LogUtils.HEALTH_LOG);
    }
}
