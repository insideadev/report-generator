package com.dev.reportgenerator.entity.report;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CustomerSummaryId implements Serializable {
    private Long customerId;
    private LocalDate asOfDate;
}
