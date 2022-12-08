package com.dev.reportgenerator.service;
import com.dev.reportgenerator.exceptionhandler.CustomExceptionNotFound;
import com.dev.reportgenerator.response.ResponseList;

import java.text.ParseException;
import java.util.Date;


//@Service
public interface ISummaryDailyService {


     ResponseList getListDaily(String startDate, String endDate, String customerId) throws CustomExceptionNotFound, ParseException;
}
