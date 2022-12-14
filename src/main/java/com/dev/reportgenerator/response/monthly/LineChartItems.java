package com.dev.reportgenerator.response.monthly;

import com.dev.reportgenerator.custom.CustomWriteDouble;
import com.dev.reportgenerator.custom.CustomWriteMonthly;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineChartItems {
    @JsonSerialize(using = CustomWriteMonthly.class)
    private LocalDate date;
    @JsonSerialize(using = CustomWriteDouble.class)
    private Double value;


}
