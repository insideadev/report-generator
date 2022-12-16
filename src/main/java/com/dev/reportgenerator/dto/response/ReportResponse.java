package com.dev.reportgenerator.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@RequiredArgsConstructor
public class ReportResponse {
    private List<LineChartItem> lineChartItems;
    private List<ProductItem> depositList;
    private List<ProductItem> insuranceList;
    private List<ProductItem> offshoreBondList;
}
