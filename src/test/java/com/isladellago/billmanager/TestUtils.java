package com.isladellago.billmanager;

import com.isladellago.billmanager.domain.dto.CreateBillBodyDTO;
import com.isladellago.billmanager.domain.model.Bill;

import java.time.LocalDateTime;
import java.util.UUID;

public final class TestUtils {

    public static final String AUTH_TOKEN = UUID.randomUUID().toString();
    public static final UUID AUTH_UUID = UUID.randomUUID();

    public static final Integer BILL_ID_1 = 1;
    public static final LocalDateTime START_DATE_1 = LocalDateTime.parse("2018-10-03T10:00:00");
    public static final LocalDateTime END_DATE_1 = LocalDateTime.parse("2018-12-03T10:00:00");
    public static final LocalDateTime START_DATE_2 = LocalDateTime.parse("2019-10-03T10:00:00");
    public static final LocalDateTime END_DATE_2 = LocalDateTime.parse("2018-12-03T10:00:00");
    public static final LocalDateTime START_DATE_3 = LocalDateTime.parse("2019-12-03T10:00:00");
    public static final LocalDateTime END_DATE_3 = LocalDateTime.parse("2019-12-03T10:00:00");
    private static final Integer RESIDENTIAL_BASIC_CUBIC_METERS = 10;
    private static final Integer RESIDENTIAL_BASIC_SUPERIOR_CUBIC_METERS = 20;
    private static final Integer DISCOUNTS = 12020;
    private static final Double RESIDENTIAL_FIXED_AQUEDUCT = 2131d;
    private static final Double RESIDENTIAL_BASIC_AQUEDUCT = 2214d;
    private static final Double RESIDENTIAL_BASIC_SUPERIOR_AQUEDUCT = 31221d;
    private static final Double RESIDENTIAL_FIXED_SEWERAGE = 123d;
    private static final Double RESIDENTIAL_BASIC_SEWERAGE = 12312d;
    private static final Double RESIDENTIAL_BASIC_SUPERIOR_SEWERAGE = 3123d;
    private static final Integer CLEANING = 31223;

    public static final String USER_EMAIL = "user-admin@isladellago.com";

    public static Bill getBill_1() {
        return Bill.builder()
                .billId(BILL_ID_1)
                .startDate(START_DATE_1)
                .endDate(END_DATE_1)
                .residentialBasicCubicMeters(RESIDENTIAL_BASIC_CUBIC_METERS)
                .residentialBasicSuperiorCubicMeters(RESIDENTIAL_BASIC_SUPERIOR_CUBIC_METERS)
                .discounts(DISCOUNTS)
                .residentialFixedAqueduct(RESIDENTIAL_FIXED_AQUEDUCT)
                .residentialBasicAqueduct(RESIDENTIAL_BASIC_AQUEDUCT)
                .residentialBasicSuperiorAqueduct(RESIDENTIAL_BASIC_SUPERIOR_AQUEDUCT)
                .residentialFixedSewerage(RESIDENTIAL_FIXED_SEWERAGE)
                .residentialBasicSewerage(RESIDENTIAL_BASIC_SEWERAGE)
                .residentialBasicSuperiorSewerage(RESIDENTIAL_BASIC_SUPERIOR_SEWERAGE)
                .cleaning(CLEANING)
                .build();
    }

    public static CreateBillBodyDTO getCreateBillPayload_1() {
        return CreateBillBodyDTO.builder()
                .startDate(START_DATE_1)
                .endDate(END_DATE_1)
                .residentialBasicCubicMeters(RESIDENTIAL_BASIC_CUBIC_METERS)
                .residentialBasicSuperiorCubicMeters(RESIDENTIAL_BASIC_SUPERIOR_CUBIC_METERS)
                .discounts(DISCOUNTS)
                .residentialFixedAqueduct(RESIDENTIAL_FIXED_AQUEDUCT)
                .residentialBasicAqueduct(RESIDENTIAL_BASIC_AQUEDUCT)
                .residentialBasicSuperiorAqueduct(RESIDENTIAL_BASIC_SUPERIOR_AQUEDUCT)
                .residentialFixedSewerage(RESIDENTIAL_FIXED_SEWERAGE)
                .residentialBasicSewerage(RESIDENTIAL_BASIC_SEWERAGE)
                .residentialBasicSuperiorSewerage(RESIDENTIAL_BASIC_SUPERIOR_SEWERAGE)
                .cleaning(CLEANING)
                .build();
    }

    public static CreateBillBodyDTO getCreateBillPayload_2() {
        return CreateBillBodyDTO.builder()
                .startDate(START_DATE_2)
                .endDate(END_DATE_2)
                .residentialBasicCubicMeters(RESIDENTIAL_BASIC_CUBIC_METERS)
                .residentialBasicSuperiorCubicMeters(RESIDENTIAL_BASIC_SUPERIOR_CUBIC_METERS)
                .discounts(DISCOUNTS)
                .residentialFixedAqueduct(RESIDENTIAL_FIXED_AQUEDUCT)
                .residentialBasicAqueduct(RESIDENTIAL_BASIC_AQUEDUCT)
                .residentialBasicSuperiorAqueduct(RESIDENTIAL_BASIC_SUPERIOR_AQUEDUCT)
                .residentialFixedSewerage(RESIDENTIAL_FIXED_SEWERAGE)
                .residentialBasicSewerage(RESIDENTIAL_BASIC_SEWERAGE)
                .residentialBasicSuperiorSewerage(RESIDENTIAL_BASIC_SUPERIOR_SEWERAGE)
                .cleaning(CLEANING)
                .build();
    }

    public static CreateBillBodyDTO getCreateBillPayload_3() {
        return CreateBillBodyDTO.builder()
                .startDate(START_DATE_3)
                .endDate(END_DATE_3)
                .residentialBasicCubicMeters(RESIDENTIAL_BASIC_CUBIC_METERS)
                .residentialBasicSuperiorCubicMeters(RESIDENTIAL_BASIC_SUPERIOR_CUBIC_METERS)
                .discounts(DISCOUNTS)
                .residentialFixedAqueduct(RESIDENTIAL_FIXED_AQUEDUCT)
                .residentialBasicAqueduct(RESIDENTIAL_BASIC_AQUEDUCT)
                .residentialBasicSuperiorAqueduct(RESIDENTIAL_BASIC_SUPERIOR_AQUEDUCT)
                .residentialFixedSewerage(RESIDENTIAL_FIXED_SEWERAGE)
                .residentialBasicSewerage(RESIDENTIAL_BASIC_SEWERAGE)
                .residentialBasicSuperiorSewerage(RESIDENTIAL_BASIC_SUPERIOR_SEWERAGE)
                .cleaning(CLEANING)
                .build();
    }
}
