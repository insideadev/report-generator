package com.dev.reportgenerator.controller;

import com.dev.reportgenerator.exceptionhandler.CustomExceptionNotFound;
import com.dev.reportgenerator.service.ISummaryDailyService;
import com.dev.reportgenerator.service.ISummaryMonthlyService;
import com.dev.reportgenerator.service.Ipmlservice.SummaryDailyServiceIplm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;


@RestController
@RequestMapping("/api/report-generator")
public class ReportGeneratorController {
    @Autowired
    private ISummaryDailyService iSummaryDailyService;
    @Autowired
    private ISummaryMonthlyService iSummaryMonthlyService;
    @Autowired
    private SummaryDailyServiceIplm summaryDailyServiceIplm;


    @GetMapping("/get-data")
    public ResponseEntity<?> getDataDaily(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("customerId") String customerId,
            @RequestParam("reportType") String reportType
    ) throws ParseException, CustomExceptionNotFound {
        if (reportType.equalsIgnoreCase("DAILY")) {
            return ResponseEntity.ok().body(iSummaryDailyService.getListDaily(startDate, endDate, customerId));



//            return ResponseEntity.ok().body(summaryDailyServiceIplm.checkTime(startDate, endDate, customerId));

        } else if (reportType.equalsIgnoreCase("MONTHLY")) {

            return ResponseEntity.ok().body(" Case MONTHLY is processing ...");

        } else
            throw new CustomExceptionNotFound("ReportType: '" + reportType + "' is wrong !");

    }


}
