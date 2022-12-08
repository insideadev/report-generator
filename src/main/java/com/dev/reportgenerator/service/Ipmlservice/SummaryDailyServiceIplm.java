package com.dev.reportgenerator.service.Ipmlservice;

import com.dev.reportgenerator.dto.SummaryDailyConvert;
import com.dev.reportgenerator.entity.SummaryDaily;
import com.dev.reportgenerator.exceptionhandler.CustomExceptionNotFound;
import com.dev.reportgenerator.repository.ISummaryDailyRepo;
import com.dev.reportgenerator.response.*;
import com.dev.reportgenerator.service.ISummaryDailyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Service
public class SummaryDailyServiceIplm implements ISummaryDailyService {
    @Autowired
    private ISummaryDailyRepo iSummaryDailyRepo;
    private final Logger log = LogManager.getLogger(this.getClass().getName());

    public ResponseList getListDaily(String startDate, String endDate, String customerId) throws CustomExceptionNotFound, ParseException {


        Date startDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        Date endDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);

        if(startDate1.compareTo(endDate1)>0){
            throw new CustomExceptionNotFound("StartDate "+startDate1+" must be less than "+endDate1) ;
        }


//        List<SummaryDaily> dailies = iSummaryDailyRepo.getAllByAsOfDateGreaterThanEqualAndAsOfDateIsLessThanEqualAndCustommerId(startDate1, endDate1,customerId);

        List<SummaryDaily> dailies = iSummaryDailyRepo.getDaily(startDate1, endDate1,customerId);

        if(dailies.size()==0){
            throw new CustomExceptionNotFound("Not found data with customer Id:  "+customerId) ;
        }


        List<SummaryDailyConvert> dailiesConvert = dailies.stream().map(daily -> {
            return new SummaryDailyConvert(daily);

        }).collect(Collectors.toList());

        long start = System.nanoTime();

        for (int i = 0; i < dailiesConvert.size(); i++) {

            if (i == dailiesConvert.size() - 1) {
                break;
            }
            if (!(addOneDay(dailiesConvert.get(i).getAsOfDate()).equals(dailiesConvert.get(i + 1).getAsOfDate()))) {
                dailiesConvert.add(i + 1, new SummaryDailyConvert(customerId, addOneDay(dailiesConvert.get(i).getAsOfDate()), false));
            }
        }

        long end = System.nanoTime();

        log.info("The loop used up :" +(end-start)/1000+" milisec");

        List<LineChartItems> lineChartItems = new ArrayList<>();
        List<DepositList> depositList = new ArrayList<>();
        List<InsuranceList> insuranceList = new ArrayList<>();
        List<OffshoreBondList> offshoreBondList = new ArrayList<>();
        AtomicReference<ResponseList> responseList =
                new AtomicReference<>(new ResponseList(lineChartItems, depositList, insuranceList, offshoreBondList));
//        ResponseList responseList=new ResponseList(lineChartItems, depositList, insuranceList, offshoreBondList);

        dailiesConvert.stream().forEach(daily -> {
            if (daily.isCheck()) {

                lineChartItems.add(new LineChartItems(daily.getAsOfDate(),
                        (daily.getIsuranceAmt() != null ? daily.getIsuranceAmt() : 0)
                                + (daily.getDepositAmt() != null ? daily.getDepositAmt() : 0)
                                + (daily.getOffshoreBondAmt() != null ? daily.getOffshoreBondAmt() : 0)
                ));
                depositList.add(
                        (daily.getDepositAmt() != null ?
                                new DepositList(daily.getDepositAmt(),
                                        (float) ((daily.getDepositAmt()* 100.0)
                                         / ((daily.getIsuranceAmt() != null ? daily.getIsuranceAmt() : 0)
                                        + (daily.getDepositAmt())
                                        + (daily.getOffshoreBondAmt() != null ? daily.getOffshoreBondAmt() : 0))))
                                : new DepositList()));


                insuranceList.add(
                        (daily.getIsuranceAmt() != null ?
                                new InsuranceList(daily.getIsuranceAmt(),
                                        (float) ((daily.getIsuranceAmt()* 100.0)
                                         / (daily.getIsuranceAmt()
                                         + (daily.getDepositAmt() != null ? daily.getDepositAmt() : 0)
                                         + (daily.getOffshoreBondAmt() != null ? daily.getOffshoreBondAmt() : 0))))
                                : new InsuranceList()));


                offshoreBondList.add(
                        (daily.getOffshoreBondAmt() != null ?
                                new OffshoreBondList(daily.getOffshoreBondAmt(),
                                        (float) ((daily.getOffshoreBondAmt()* 100.0)
                                         / ((daily.getIsuranceAmt() != null ? daily.getIsuranceAmt() : 0)
                                         + (daily.getDepositAmt() != null ? daily.getDepositAmt() : 0)
                                         + (daily.getOffshoreBondAmt()))))

                                : new OffshoreBondList()));


            } else {
                lineChartItems.add(new LineChartItems(daily.getAsOfDate()));
                depositList.add(new DepositList());
                insuranceList.add(new InsuranceList());
                offshoreBondList.add(new OffshoreBondList());

            }

        });
        return responseList.get();
    }
    private LocalDate addOneDay(LocalDate date) {
        return date.plusDays(1);
    }

}

