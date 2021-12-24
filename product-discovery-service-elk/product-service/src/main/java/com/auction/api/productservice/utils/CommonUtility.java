package com.auction.api.productservice.utils;

import java.time.LocalDate;

public class CommonUtility {

    public enum BEFORE_AFTER_ENUM {
        BEFORE, AFTER
    }

    public static boolean isTodayBeforeOrAfterTheValueDate(String valueToValidate, BEFORE_AFTER_ENUM enumValue) {
        String date[] = valueToValidate.split("[\\/-]");
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        LocalDate today = LocalDate.now();
        switch (enumValue) {
            case AFTER:
                return today.isAfter(LocalDate.of(year, month, day));
            case BEFORE:
                return today.isBefore(LocalDate.of(year, month, day));
                default:
                    return today.isAfter(LocalDate.of(year, month, day));
        }

    }
}
