package com.isladellago.billmanager;

import com.isladellago.billmanager.domain.dto.*;
import com.isladellago.billmanager.domain.model.Apartment;
import com.isladellago.billmanager.domain.model.Bill;
import com.isladellago.billmanager.domain.model.Consumption;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public final class TestUtils {

    public static final String AUTH_TOKEN = UUID.randomUUID().toString();
    public static final UUID AUTH_UUID = UUID.randomUUID();

    public static final Integer BILL_ID_1 = 1;
    public static final LocalDate START_DATE_1 = LocalDate.parse("2018-10-03");
    public static final LocalDate END_DATE_1 = LocalDate.parse("2018-12-03");
    public static final LocalDate START_DATE_2 = LocalDate.parse("2019-10-03");
    public static final LocalDate END_DATE_2 = LocalDate.parse("2018-12-03");
    public static final LocalDate START_DATE_3 = LocalDate.parse("2019-12-03");
    public static final LocalDate END_DATE_3 = LocalDate.parse("2019-12-03");
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
    public static final String APARTMENT_ID_201 = "Apartamento 201";
    public static final String APARTMENT_ID_202 = "Apartamento 202";

    public static final Integer CONSUMPTION_ID_1 = 1;
    public static final Integer CONSUMPTION_ID_2 = 2;

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

    public static CreateConsumptionBodyDTO getCreateConsumptionBodyDto_1() {
        return CreateConsumptionBodyDTO.builder()
                .apartmentId(APARTMENT_ID_201)
                .billId(BILL_ID_1)
                .value(10000d)
                .residentialBasicCubicMeters(12.1d)
                .residentialBasicSuperiorCubicMeters(23.1d)
                .build();
    }

    public static Consumption getConsumption_1() {
        return Consumption.builder()
                .consumptionId(CONSUMPTION_ID_1)
                .apartment(Apartment.builder()
                        .apartmentId(APARTMENT_ID_201)
                        .build()
                ).bill(Bill.builder()
                        .billId(BILL_ID_1)
                        .build()
                ).build();
    }

    public static CalculateConsumptionsPercentageBody getCalculateConsumptionsPercentageBody_1() {
        final ConsumptionInfo consumptionInfo1 = ConsumptionInfo.builder()
                .apartmentId(APARTMENT_ID_201)
                .value(10000d)
                .build();

        final ConsumptionInfo consumptionInfo2 = ConsumptionInfo.builder()
                .apartmentId(APARTMENT_ID_202)
                .value(15000d)
                .build();

        return CalculateConsumptionsPercentageBody.builder()
                .billId(BILL_ID_1)
                .consumptions(List.of(consumptionInfo1, consumptionInfo2))
                .build();
    }

    public static CalculateConsumptionsPercentageResponse getCalculateConsumptionsPerecentageResponse() {
        final ConsumptionResponse consumptionResponse1 = ConsumptionResponse.builder()
                .apartmentId(APARTMENT_ID_201)
                .consumptionId(CONSUMPTION_ID_1)
                .build();

        final ConsumptionResponse consumptionResponse2 = ConsumptionResponse.builder()
                .apartmentId(APARTMENT_ID_202)
                .consumptionId(CONSUMPTION_ID_2)
                .build();

        return CalculateConsumptionsPercentageResponse.builder()
                .consumptionIds(List.of(consumptionResponse1, consumptionResponse2))
                .build();
    }
}
