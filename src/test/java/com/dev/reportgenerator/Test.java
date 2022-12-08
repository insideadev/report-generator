package com.dev.reportgenerator;

import com.dev.reportgenerator.entity.SummaryDaily;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    private static LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private static Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public static void main(String[] args) throws ParseException {


        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-11-25");
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("20222-12-03");


        LocalDate startDate1 = convertToLocalDateViaMilisecond(startDate);
        LocalDate endDate1 = convertToLocalDateViaMilisecond(endDate);

        long numOfDays = ChronoUnit.DAYS.between(startDate1, endDate1);

        List<LocalDate> listOfDates = Stream.iterate(startDate1, date -> date.plusDays(1))
                .limit(numOfDays)
                .collect(Collectors.toList());


        HashMap<Integer, LocalDate> localDateHashMap = new HashMap<>();




        ArrayList<TestDate> dailies = new ArrayList<>();
        dailies.add(new TestDate(1, new SimpleDateFormat("yyyy-MM-dd").parse("2022-11-25")));
        dailies.add(new TestDate(1, new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-08")));
        dailies.add(new TestDate(1, new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-12")));
        dailies.add(new TestDate(1, new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-15")));


        for (int i = 0; i < dailies.size(); i++) {

            if (i == dailies.size() - 1) {
                break;
            }
            if (!(convertToLocalDateViaMilisecond(dailies.get(i).getDate())
                    .plus(1, ChronoUnit.DAYS)
                    .isEqual(convertToLocalDateViaMilisecond(dailies.get(i + 1).getDate())))) {
                dailies.add(i + 1, new TestDate(i + 1, convertToDateViaSqlDate(convertToLocalDateViaMilisecond(dailies.get(i).getDate())
                        .plus(1, ChronoUnit.DAYS))));
            }
        }


        for (TestDate t : dailies) {
            System.out.println(t.getDate());
        }


    }

}
