package com.dev.reportgenerator.reporsitory;

import com.dev.reportgenerator.entity.report.CustomerSummaryMonthly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CustomerSummaryMonthlyRepository extends JpaRepository<CustomerSummaryMonthly, Long> {

    @Query(value = "SELECT csm FROM CustomerSummaryMonthly csm " +
            " WHERE csm.customerId = :customerId AND csm.asOfDate BETWEEN :startDate AND :endDate")
    List<CustomerSummaryMonthly> findAll(String customerId, LocalDate startDate, LocalDate endDate);
}
