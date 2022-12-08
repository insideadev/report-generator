package com.dev.reportgenerator.repository;

import com.dev.reportgenerator.entity.SummaryDaily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface ISummaryDailyRepo extends JpaRepository<SummaryDaily, Long> {

    List<SummaryDaily> getAllByAsOfDateGreaterThanEqualAndAsOfDateIsLessThanEqualAndCustommerId(Date startDAte, Date endDate, String customerId);



    @Query("select s  from SummaryDaily s where s.asOfDate>= :startDAte and s.asOfDate<= :endDate and s.custommerId= :customerId order by s.asOfDate asc ")
    List<SummaryDaily> getDaily(Date startDAte, Date endDate, String customerId);



}
