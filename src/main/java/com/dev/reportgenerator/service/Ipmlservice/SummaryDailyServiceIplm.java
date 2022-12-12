package com.dev.reportgenerator.service.Ipmlservice;

import com.dev.reportgenerator.entity.SummaryDaily;
import com.dev.reportgenerator.exceptionhandler.CustomExceptionNotFound;
import com.dev.reportgenerator.repository.ISummaryDailyRepo;
import com.dev.reportgenerator.response.*;
import com.dev.reportgenerator.response.daily.LineChartItems;
import com.dev.reportgenerator.response.daily.ReportResponse;
import com.dev.reportgenerator.service.ISummaryDailyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class SummaryDailyServiceIplm implements ISummaryDailyService {
    @Autowired
    private ISummaryDailyRepo iSummaryDailyRepo;
    private final Logger log = LogManager.getLogger(this.getClass().getName());

    public ReportResponse checkTime(String startDate, String endDate, String customerId) throws CustomExceptionNotFound, ParseException {
        Long startTime = System.nanoTime();
        ReportResponse listDaily = getListDaily(startDate, endDate, customerId);

        Long endTime = System.nanoTime();
        log.info(" Time for method is: " + (endTime - startTime) / 1000 + " milisec");
        return listDaily;
    }

    @Cacheable("listDaily")
    public ReportResponse getListDaily(String startDate, String endDate, String customerId) throws CustomExceptionNotFound {
        log.info(" Get data from database !");
        LocalDate startDate1 = LocalDate.parse(startDate);
        LocalDate endDate1 = LocalDate.parse(endDate);

        if (startDate1.compareTo(endDate1) > 0) {
            throw new CustomExceptionNotFound("StartDate : '" + startDate1 + "' must be less than or equal endDate : '" + endDate1 + "'");
        }
        List<SummaryDaily> dailies = iSummaryDailyRepo.getDaily(startDate1, endDate1, customerId);
        if (dailies.size() == 0) {
            throw new CustomExceptionNotFound("Not found data with customer Id:  " + customerId);
        }


        long start = System.nanoTime();
        List<SummaryDaily> dailyList =
                Stream.iterate(startDate1, date -> date.plusDays(1))
                        .limit(ChronoUnit.DAYS.between(startDate1, endDate1.plusDays(1)))
                        .map(t -> {
                            SummaryDaily summaryDaily = new SummaryDaily();
                            summaryDaily.setAsOfDate(t);
                            summaryDaily.setCustommerId(customerId);
                            if (dailies.contains(summaryDaily)) {
                                summaryDaily.setId(dailies.get(dailies.indexOf(summaryDaily)).getId());
                                summaryDaily.setDepositAmt(dailies.get(dailies.indexOf(summaryDaily)).getDepositAmt());
                                summaryDaily.setIsuranceAmt(dailies.get(dailies.indexOf(summaryDaily)).getIsuranceAmt());
                                summaryDaily.setOffshoreBondAmt(dailies.get(dailies.indexOf(summaryDaily)).getOffshoreBondAmt());
                            }
                            return summaryDaily;
                        }).collect(Collectors.toList());
        long end = System.nanoTime();
        log.warn("The stream 1 used up :" + (end - start) / 1000 + " milisec");
        long start1 = System.nanoTime();
        List<LineChartItems> lineChartItems = new ArrayList<>();
        List<DepositList> depositList = new ArrayList<>();
        List<InsuranceList> insuranceList = new ArrayList<>();
        List<OffshoreBondList> offshoreBondList = new ArrayList<>();
        AtomicReference<ReportResponse> responseList =
                new AtomicReference<>(new ReportResponse(lineChartItems, depositList, insuranceList, offshoreBondList));
//        ResponseList responseList=new ResponseList(lineChartItems, depositList, insuranceList, offshoreBondList);

        dailyList.forEach(daily -> {

                lineChartItems.add(new LineChartItems(daily.getAsOfDate(),
                        (daily.getIsuranceAmt() != null ? daily.getIsuranceAmt() : 0)
                                + (daily.getDepositAmt() != null ? daily.getDepositAmt() : 0)
                                + (daily.getOffshoreBondAmt() != null ? daily.getOffshoreBondAmt() : 0)
                ));
                depositList.add(
                        (daily.getDepositAmt() != null ?
                                new DepositList(daily.getDepositAmt(),
                                        (float) ((daily.getDepositAmt() * 100.0)
                                                / ((daily.getIsuranceAmt() != null ? daily.getIsuranceAmt() : 0)
                                                + (daily.getDepositAmt())
                                                + (daily.getOffshoreBondAmt() != null ? daily.getOffshoreBondAmt() : 0))))
                                : new DepositList()));
                insuranceList.add(
                        (daily.getIsuranceAmt() != null ?
                                new InsuranceList(daily.getIsuranceAmt(),
                                        (float) ((daily.getIsuranceAmt() * 100.0)
                                                / (daily.getIsuranceAmt()
                                                + (daily.getDepositAmt() != null ? daily.getDepositAmt() : 0)
                                                + (daily.getOffshoreBondAmt() != null ? daily.getOffshoreBondAmt() : 0))))
                                : new InsuranceList()));
                offshoreBondList.add(
                        (daily.getOffshoreBondAmt() != null ?
                                new OffshoreBondList(daily.getOffshoreBondAmt(),
                                        (float) ((daily.getOffshoreBondAmt() * 100.0)
                                                / ((daily.getIsuranceAmt() != null ? daily.getIsuranceAmt() : 0)
                                                + (daily.getDepositAmt() != null ? daily.getDepositAmt() : 0)
                                                + (daily.getOffshoreBondAmt()))))
                                : new OffshoreBondList()));
        });
        long end1 = System.nanoTime();





        log.warn("The stream 2 used up :" + (end1 - start1) / 1000 + " milisec");
        return responseList.get();
    }

    private LocalDate addOneDay(LocalDate date) {
        return date.plusDays(1);
    }






}

