package com.dev.reportgenerator.response.monthly;

import com.dev.reportgenerator.response.DepositList;
import com.dev.reportgenerator.response.InsuranceList;
import com.dev.reportgenerator.response.OffshoreBondList;
import com.dev.reportgenerator.response.monthly.LineChartItems;
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
