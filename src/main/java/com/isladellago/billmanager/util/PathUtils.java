package com.isladellago.billmanager.util;

public final class PathUtils {

    public static final String BASE_PATH = "/api/v1/bill";

    public static final String HEALTH_PATH = "/health";
    public static final String CREATE_BILL = "/create";

    private PathUtils() {
        throw new AssertionError();
    }
}
