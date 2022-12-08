package com.dev.reportgenerator.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineChartItems {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Long value;


    public LineChartItems(LocalDate date) {
        this.date = date;
    }
}
