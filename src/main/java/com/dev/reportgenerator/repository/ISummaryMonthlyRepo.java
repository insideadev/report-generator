package com.dev.reportgenerator.repository;


import com.dev.reportgenerator.entity.SummaryMonthly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ISummaryMonthlyRepo extends JpaRepository<SummaryMonthly,Long> {


    @Query("select s  from SummaryMonthly s where s.asOfDate>= :startDAte " +
            "and s.asOfDate<= :endDate and s.custommerId= :customerId order by s.asOfDate asc ")
    List<SummaryMonthly> getMonthly(LocalDate startDAte, LocalDate endDate, String customerId);



}
