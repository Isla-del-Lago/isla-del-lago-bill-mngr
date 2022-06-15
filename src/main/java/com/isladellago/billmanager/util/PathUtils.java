package com.isladellago.billmanager.util;

public final class PathUtils {

    public static final String BASE_PATH_BILL = "/api/v1/bill";
    public static final String BASE_PATH_CONSUMPTION = BASE_PATH_BILL + "/consumption";

    public static final String HEALTH_PATH = "/health";
    public static final String CREATE_BILL = "/create";
    public static final String BILL_ID = "/billId/{bill-id}";
    public static final String CREATE_CONSUMPTION = "/create";
    public static final String GET_CONSUMPTION_BY_ID = "/consumptionId/{consumption-id}";
    public static final String CALCULATE_CONSUMPTIONS_PERCENTAGE = "/calculatePercentages";
    public static final String GET_CONSUMPTION_DETAILS_BY_APARTMENT_AND_BILL = "/consumptionDetail/apartmentId/{apartment-id}/billId/{bill-id}";
    public static final String GET_BILL_BY_START_AND_END_DATE = "/billDate";
    public static final String GET_ALL_CONSUMPTION_DETAILS_FROM_BILL_ID = "/consumptionDetail/billId/{bill-id}";
    public static final String GET_ALL_CONSUMPTION_DETAILS_FROM_APARTMENT_ID = "/consumptionDetail/apartmentId/{apartment-id}";

    private PathUtils() {
        throw new AssertionError();
    }

    public static final class SecurityManagerClient {

        public static final String ROOT_PATH = "/api/v1/token";

        public static final String VALIDATE_TOKEN_PATH = ROOT_PATH + "/validate";
    }
}
