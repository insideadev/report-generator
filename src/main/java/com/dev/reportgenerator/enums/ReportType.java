package com.dev.reportgenerator.enums;

import java.util.HashMap;
import java.util.Map;

public enum ReportType {
    DAILY, MONTHLY;

    private static final Map<String, ReportType> ENUM_MAP = new HashMap<>();

    static {
        for (ReportType type : values()) {
            ENUM_MAP.put(type.name(), type);
        }
    }

    public static ReportType of(String input) {
        return ENUM_MAP.getOrDefault(input, null);
    }

}
