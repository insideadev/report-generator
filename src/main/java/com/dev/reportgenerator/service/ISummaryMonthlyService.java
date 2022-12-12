package com.dev.reportgenerator.service;

import com.dev.reportgenerator.exceptionhandler.CustomExceptionNotFound;
import com.dev.reportgenerator.response.monthly.ReportResponse;

//@Service
public interface ISummaryMonthlyService {

    public ReportResponse getMonthly(String startDate, String endDate, String customerId) throws CustomExceptionNotFound;
}
