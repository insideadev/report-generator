package com.dev.reportgenerator.service.Ipmlservice;

import com.dev.reportgenerator.entity.SummaryDaily;
import com.dev.reportgenerator.entity.SummaryMonthly;
import com.dev.reportgenerator.exceptionhandler.CustomExceptionNotFound;
import com.dev.reportgenerator.repository.ISummaryMonthlyRepo;

import com.dev.reportgenerator.response.DepositList;
import com.dev.reportgenerator.response.InsuranceList;
import com.dev.reportgenerator.response.OffshoreBondList;
import com.dev.reportgenerator.response.monthly.LineChartItems;
import com.dev.reportgenerator.response.monthly.ReportResponse;
import com.dev.reportgenerator.service.ISummaryMonthlyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SummaryMonthlyServiceIplm implements ISummaryMonthlyService {
    @Autowired
    private ISummaryMonthlyRepo iSummaryMonthlyRepo;
    private final Logger log = LogManager.getLogger(this.getClass().getName());

    @Override
    public ReportResponse getMonthly(String startDate, String endDate, String customerId) throws CustomExceptionNotFound {


        LocalDate startDate1 = LocalDate.parse(startDate).minusDays(LocalDate.parse(startDate).getDayOfMonth() - 1);

        LocalDate endDate1 = lastDateOfMonth(LocalDate.parse(endDate));

        List<SummaryMonthly> listMonthly = iSummaryMonthlyRepo.getMonthly(startDate1, endDate1, customerId);
        if (startDate1.compareTo(endDate1) > 0) {
            throw new CustomExceptionNotFound("StartDate : '" + startDate1 + "' must be less than or equal endDate : '" + endDate1 + "'");
        }
        if (listMonthly.size() == 0) {
            throw new CustomExceptionNotFound("Not found data with customer Id:  " + customerId);
        }

        Stream.iterate(startDate1, date -> date.plusMonths(1))
                .limit(ChronoUnit.MONTHS.between(startDate1, endDate1.plusMonths(1))).forEach(
                        t ->
                                log.warn(t.getDayOfMonth())

                );


        List<SummaryMonthly> monthList = Stream.iterate(startDate1, date -> date.plusMonths(1))
                .limit(ChronoUnit.MONTHS.between(startDate1, endDate1.plusMonths(1))).map(t -> {
                    SummaryMonthly summaryMonthly = new SummaryMonthly();
                    summaryMonthly.setAsOfDate(t);
                    summaryMonthly.setCustommerId(customerId);
                    if (listMonthly.contains(summaryMonthly)) {
                        summaryMonthly.setAsOfDate(listMonthly.get(listMonthly.indexOf(summaryMonthly)).getAsOfDate());
                        summaryMonthly.setId(listMonthly.get(listMonthly.indexOf(summaryMonthly)).getId());
                        summaryMonthly.setDepositAmt(listMonthly.get(listMonthly.indexOf(summaryMonthly)).getDepositAmt());
                        summaryMonthly.setIsuranceAmt(listMonthly.get(listMonthly.indexOf(summaryMonthly)).getIsuranceAmt());
                        summaryMonthly.setOffshoreBondAmt(listMonthly.get(listMonthly.indexOf(summaryMonthly)).getOffshoreBondAmt());
                    }
                    return summaryMonthly;
                }).collect(Collectors.toList());


        List<LineChartItems> lineChartItems = new ArrayList<>();
        List<DepositList> depositList = new ArrayList<>();
        List<InsuranceList> insuranceList = new ArrayList<>();
        List<OffshoreBondList> offshoreBondList = new ArrayList<>();
        AtomicReference<ReportResponse> responseList =
                new AtomicReference<>(new ReportResponse(lineChartItems, depositList, insuranceList, offshoreBondList));


        monthList.forEach(monthly -> {
            lineChartItems.add(new LineChartItems(monthly.getAsOfDate(), (
                    (monthly.getIsuranceAmt() == null && monthly.getDepositAmt() == null && monthly.getOffshoreBondAmt() == null) ? null :
                            (
                                    (monthly.getIsuranceAmt() != null ? monthly.getIsuranceAmt() : 0)
                                            + (monthly.getDepositAmt() != null ? monthly.getDepositAmt() : 0)
                                            + (monthly.getOffshoreBondAmt() != null ? monthly.getOffshoreBondAmt() : 0)))
            ));
            depositList.add(
                    (monthly.getDepositAmt() != null ?
                            new DepositList(monthly.getDepositAmt(),
                                    (float) ((monthly.getDepositAmt() * 100.0)
                                            / ((monthly.getIsuranceAmt() != null ? monthly.getIsuranceAmt() : 0)
                                            + (monthly.getDepositAmt())
                                            + (monthly.getOffshoreBondAmt() != null ? monthly.getOffshoreBondAmt() : 0))))
                            : new DepositList()));
            insuranceList.add(
                    (monthly.getIsuranceAmt() != null ?
                            new InsuranceList(monthly.getIsuranceAmt(),
                                    (float) ((monthly.getIsuranceAmt() * 100.0)
                                            / (monthly.getIsuranceAmt()
                                            + (monthly.getDepositAmt() != null ? monthly.getDepositAmt() : 0)
                                            + (monthly.getOffshoreBondAmt() != null ? monthly.getOffshoreBondAmt() : 0))))
                            : new InsuranceList()));
            offshoreBondList.add(
                    (monthly.getOffshoreBondAmt() != null ?
                            new OffshoreBondList(monthly.getOffshoreBondAmt(),
                                    (float) ((monthly.getOffshoreBondAmt() * 100.0)
                                            / ((monthly.getIsuranceAmt() != null ? monthly.getIsuranceAmt() : 0)
                                            + (monthly.getDepositAmt() != null ? monthly.getDepositAmt() : 0)
                                            + (monthly.getOffshoreBondAmt()))))
                            : new OffshoreBondList()));
        });


        return responseList.get();
    }

    public static LocalDate lastDateOfMonth(LocalDate date) {
        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 31)
                .appendPattern("uuuu-MM")
                .toFormatter(Locale.ENGLISH);
        return LocalDate.parse(YearMonth.from(date).toString(), dtf);
    }
}
