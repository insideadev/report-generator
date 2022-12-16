package com.dev.reportgenerator.reporsitory;

import com.dev.reportgenerator.entity.report.CustomerSummaryDaily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CustomerSummaryDailyRepository extends JpaRepository<CustomerSummaryDaily, Long> {

    @Query(value = "SELECT csd FROM CustomerSummaryDaily csd " +
            " WHERE csd.customerId = :customerId AND csd.asOfDate BETWEEN :startDate AND :endDate")
    List<CustomerSummaryDaily> findAll(Long customerId, LocalDate startDate, LocalDate endDate);
}
