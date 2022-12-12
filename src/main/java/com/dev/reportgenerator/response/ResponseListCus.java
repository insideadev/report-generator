package com.dev.reportgenerator.response;
import com.dev.reportgenerator.response.daily.LineChartItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseListCus {
    private LineChartItems lineChartItems;
    private DepositList depositList;
    private InsuranceList insuranceList;
    private OffshoreBondList offshoreBondList;
}
