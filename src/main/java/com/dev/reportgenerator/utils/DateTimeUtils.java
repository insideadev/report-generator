package com.dev.reportgenerator.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateTimeUtils {

    private static final String MMM_DD_FORMAT = "MMM dd";
    private static final String MMM_YYYY_FORMAT = "MMM yyyy";

    private DateTimeUtils() {}

    public static LocalDate firstDayOfMonth(LocalDate date) {
        return date.withDayOfMonth(1);
    }

    public static LocalDate lastDayOfMonth(LocalDate date) {
        int lengthOfMonth = date.lengthOfMonth();
        return date.withDayOfMonth(lengthOfMonth);
    }

    public static String toMMMDDString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(MMM_DD_FORMAT);
        return date.format(formatter);
    }

    public static String toMMMYYYYString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(MMM_YYYY_FORMAT);
        return date.format(formatter);
    }
}
