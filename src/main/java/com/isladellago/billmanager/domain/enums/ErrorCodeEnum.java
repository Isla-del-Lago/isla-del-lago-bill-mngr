package com.isladellago.billmanager.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

    L300("L-300", "Invalid start date and end date range"),
    L002("L-002", "Invalid payload body, please review the values"),
    L301("L-301", "There is a bill with the given start or end date"),
    L302("L-302", "Bill not found"),
    L200("L-200", "Apartment not found"),
    L400("L-400", "Consumption not found"),
    L401("L-401", "Consumption with bill id and apartment id already exists"),
    L402("L-402", "Consumptions already calculated for this bill"),
    L403("L-403", "Consumptions are not calculated for the given bill");

    private final String errorCode;
    private final String errorMessage;
}
