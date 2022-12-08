package com.dev.reportgenerator.dto;

import com.dev.reportgenerator.entity.SummaryDaily;
import lombok.Data;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
@Data
public class SummaryDailyConvert {


    private Long id;

    private String custommerId;

    private Long isuranceAmt;

    private Long depositAmt;

    private Long offshoreBondAmt;

    private LocalDate asOfDate;

    private boolean check;

    public SummaryDailyConvert(String custommerId, LocalDate asOfDate,boolean check) {
        this.custommerId = custommerId;
        this.asOfDate = asOfDate;
        this.check=check;
    }

    public SummaryDailyConvert(SummaryDaily summaryDaily) {
        this.id = summaryDaily.getId();
        this.custommerId = summaryDaily.getCustommerId();
        this.isuranceAmt = summaryDaily.getIsuranceAmt();
        this.depositAmt = summaryDaily.getDepositAmt();
        this.offshoreBondAmt = summaryDaily.getOffshoreBondAmt();
        this.asOfDate =convertToLocalDateViaMilisecond(summaryDaily.getAsOfDate());
        this.check= true;
    }
    private static Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }
    private LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static SummaryDaily    getSummary(SummaryDailyConvert  summaryDailyConvert) {

        SummaryDaily summaryDaily= new SummaryDaily();
        summaryDaily.setId(summaryDailyConvert.getId());
        summaryDaily.setCustommerId(summaryDailyConvert.getCustommerId());
        summaryDaily.setDepositAmt(summaryDailyConvert.getDepositAmt());
        summaryDaily.setIsuranceAmt(summaryDailyConvert.getIsuranceAmt());
        summaryDaily.setOffshoreBondAmt(summaryDailyConvert.getOffshoreBondAmt());
        summaryDaily.setAsOfDate(convertToDateViaSqlDate(summaryDailyConvert.getAsOfDate()));


        return summaryDaily;
    }


}
