package com.dev.reportgenerator.service.impl;

import com.dev.reportgenerator.dto.request.ReportRequest;
import com.dev.reportgenerator.dto.response.LineChartItem;
import com.dev.reportgenerator.dto.response.ProductItem;
import com.dev.reportgenerator.dto.response.ReportResponse;
import com.dev.reportgenerator.entity.report.AccountSummary;
import com.dev.reportgenerator.reporsitory.CustomerSummaryDailyRepository;
import com.dev.reportgenerator.reporsitory.CustomerSummaryMonthlyRepository;
import com.dev.reportgenerator.service.CustomerSummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.dev.reportgenerator.constants.Constant.PERCENTAGE_CALCULATION_SCALE;
import static com.dev.reportgenerator.constants.Constant.PERCENTAGE_MULTIPLIER;
import static com.dev.reportgenerator.enums.ReportType.DAILY;
import static com.dev.reportgenerator.utils.DateTimeUtils.*;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerSummaryServiceImpl implements CustomerSummaryService {

    private static final int DEPOSIT_INDEX = 0;
    private static final int INSURANCE_INDEX = 1;
    private static final int OFFSHORE_BOND_INDEX = 2;

    private final CustomerSummaryDailyRepository dailyRepository;
    private final CustomerSummaryMonthlyRepository monthlyRepository;

    @Override
    public ReportResponse generateReport(ReportRequest request) {
        List<? extends AccountSummary> entities;

        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        ChronoUnit unit;

        if (DAILY.name().equals(request.getReportType())) {
            entities = dailyRepository.findAll(request.getCustomerId(), startDate, endDate);
            unit = DAYS;
        } else {
            startDate = firstDayOfMonth(startDate);
            endDate = lastDayOfMonth(endDate);
            entities = monthlyRepository.findAll(request.getCustomerId(), startDate, endDate);
            unit = MONTHS;
        }

        Map<LocalDate, ? extends AccountSummary> map = calculateMap(entities, startDate, endDate, unit);

        List<List<ProductItem>> productItems = new ArrayList<>();
        List<LineChartItem> lineChartItems = new ArrayList<>();

        aggregateData(unit, map, productItems, lineChartItems);

        return ReportResponse.builder()
                .lineChartItems(lineChartItems)
                .depositList(getProductItemList(productItems, DEPOSIT_INDEX))
                .insuranceList(getProductItemList(productItems, INSURANCE_INDEX))
                .offshoreBondList(getProductItemList(productItems, OFFSHORE_BOND_INDEX))
                .build();
    }

    private void aggregateData(ChronoUnit unit, Map<LocalDate, ? extends AccountSummary> map, List<List<ProductItem>> productItems, List<LineChartItem> lineChartItems) {
        for (Map.Entry<LocalDate, ? extends AccountSummary> entry : map.entrySet()) {
            LocalDate asOfDate = entry.getKey();
            AccountSummary entity = entry.getValue();

            LineChartItem lineChartItem = new LineChartItem();
            lineChartItem.setDate(unit == DAYS ? toMMMDDString(asOfDate) : toMMMYYYYString(asOfDate));
            BigDecimal total = null;

            if (entity != null
                    && entity.getDepositAmt() == null
                    && entity.getInsuranceAmt() == null
                    && entity.getOffshoreBondAmt() == null) {
                // do nothing
            } else if (entity != null) {
                total = resolveBigDecimalValue(entity, AccountSummary::getDepositAmt)
                        .add(resolveBigDecimalValue(entity, AccountSummary::getInsuranceAmt))
                        .add(resolveBigDecimalValue(entity, AccountSummary::getOffshoreBondAmt));

                lineChartItem.setValue(total.doubleValue());
            }
            lineChartItems.add(lineChartItem);

            ProductItem depositItem = getProductItem(entity, AccountSummary::getDepositAmt, total);
            ProductItem insuranceItem = getProductItem(entity, AccountSummary::getInsuranceAmt, total);
            ProductItem offshoreBondItem = getProductItem(entity, AccountSummary::getOffshoreBondAmt, total);

            productItems.add(Arrays.asList(depositItem, insuranceItem, offshoreBondItem));
        }
    }

    private <T> BigDecimal resolveBigDecimalValue(T t, Function<T, BigDecimal> func) {
        BigDecimal value = func.apply(t);
        return value == null
                ? ZERO
                : value.setScale(0, HALF_UP);
    }

    private <T extends AccountSummary> Map<LocalDate, T> calculateMap(List<T> entities,
                                                                      LocalDate startDate,
                                                                      LocalDate endDate,
                                                                      ChronoUnit unit) {
        Map<LocalDate, T> map = new TreeMap<>();

        LocalDate dateCnt = startDate;
        LocalDate comparedEndDate = unit == DAYS ? endDate : lastDayOfMonth(endDate);

        while (dateCnt.compareTo(comparedEndDate) <= 0) {
            if (unit == MONTHS) {
                dateCnt = lastDayOfMonth(dateCnt);
            }
            map.put(dateCnt, null);
            dateCnt = dateCnt.plus(1, unit);
        }

        if (unit == DAYS) {
            entities.stream()
                    .filter(e -> map.containsKey(e.getAsOfDate()))
                    .forEach(e -> map.put(e.getAsOfDate(), e));
        } else {
            entities.forEach(e -> {
                LocalDate asOfDate = e.getAsOfDate();
                LocalDate lastDateOfMonth = lastDayOfMonth(asOfDate);

                if (map.containsKey(lastDateOfMonth)) {
                    map.remove(lastDateOfMonth);
                    map.put(asOfDate, e);
                }
            });
        }

        return map;
    }

    private ProductItem getProductItem(AccountSummary accountSummary,
                                       Function<AccountSummary, BigDecimal> func,
                                       BigDecimal total) {
        if (accountSummary == null || func.apply(accountSummary) == null) {
            return new ProductItem();
        }

        BigDecimal value = resolveBigDecimalValue(accountSummary, func);
        BigDecimal percentage = value.divide(total, PERCENTAGE_CALCULATION_SCALE, HALF_UP)
                .multiply(BigDecimal.valueOf(PERCENTAGE_MULTIPLIER))
                .setScale(2, HALF_UP);

        return ProductItem.builder()
                .value(value.doubleValue())
                .percentage(percentage.doubleValue())
                .build();
    }

    private List<ProductItem> getProductItemList(List<List<ProductItem>> productItems, int index) {
        return productItems.stream()
                .map(s -> s.get(index))
                .collect(Collectors.toList());
    }
}
