package com.dev.reportgenerator.service;
import com.dev.reportgenerator.exceptionhandler.CustomExceptionNotFound;
import com.dev.reportgenerator.response.daily.ReportResponse;

import java.text.ParseException;


//@Service
public interface ISummaryDailyService {


     ReportResponse getListDaily(String startDate, String endDate, String customerId) throws CustomExceptionNotFound, ParseException;
}
