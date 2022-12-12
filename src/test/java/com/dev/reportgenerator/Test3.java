package com.dev.reportgenerator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test3 {

    public static void main(String[] args) {
//        double input = 15.63585464564;
//
//        System.out.println("salary : " + input);
//
//        double salary = Math.round(input *100.0) /100.0;
//
//        System.out.println("salary : " + salary);

        LocalDate startDate1 = LocalDate.parse("2022-12-05");
        LocalDate endDate1 = LocalDate.parse("2023-03-21");

          Stream.iterate(startDate1, date -> date.plusMonths(1))
                .limit(ChronoUnit.MONTHS.between(startDate1, endDate1.plusMonths(1))).forEach(t->

                                System.out.println(t.getDayOfMonth())

                        );




    }
}
