package com.dev.reportgenerator.reporsitory;

import com.dev.reportgenerator.entity.report.AccountSummaryId;
import com.dev.reportgenerator.entity.report.AccountSummaryMonthly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CustomerSummaryMonthlyRepository extends JpaRepository<AccountSummaryMonthly, AccountSummaryId> {

    @Query(value = "SELECT csm FROM AccountSummaryMonthly csm " +
            " WHERE csm.customerId = :customerId AND csm.asOfDate BETWEEN :startDate AND :endDate")
    List<AccountSummaryMonthly> findAll(String customerId, LocalDate startDate, LocalDate endDate);
}
