package com.dev.reportgenerator.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponse {
    private List<LineChartItem> lineChartItems;
    private List<ProductItem> depositList;
    private List<ProductItem> insuranceList;
    private List<ProductItem> offshoreBondList;
}
