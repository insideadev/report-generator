package com.dev.reportgenerator.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
public class LineChartItem {
    private String date;
    private Double value;
}
