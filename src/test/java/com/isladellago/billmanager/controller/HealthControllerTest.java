package com.isladellago.billmanager.controller;

import com.isladellago.billmanager.util.LogUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HealthControllerTest {

    private HealthController healthController = new HealthController();

    @Test
    public final void testHealth() {
        final ResponseEntity<String> response =
                healthController.health();

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(LogUtils.HEALTH_LOG, response.getBody());
    }
}
