package com.isladellago.billmanager.util;

public final class PathUtils {

    public static final String BASE_PATH = "/api/v1/bill";

    public static final String HEALTH_PATH = "/health";
    public static final String CREATE_BILL = "/create";
    public static final String GET_BILL_BY_ID = "/billId/{bill-id}";

    private PathUtils() {
        throw new AssertionError();
    }
}
