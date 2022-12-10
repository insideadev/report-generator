package com.dev.reportgenerator.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {
    private List<LineChartItems> lineChartItems;
    private List<DepositList> depositList;
    private List<InsuranceList> insuranceList;
    private List<OffshoreBondList> offshoreBondList;

}
