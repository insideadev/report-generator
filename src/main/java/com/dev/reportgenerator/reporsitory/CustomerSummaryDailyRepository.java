package com.dev.reportgenerator.reporsitory;

import com.dev.reportgenerator.entity.report.AccountSummaryDaily;
import com.dev.reportgenerator.entity.report.AccountSummaryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CustomerSummaryDailyRepository extends JpaRepository<AccountSummaryDaily, AccountSummaryId> {

    @Query(value = "SELECT csd FROM AccountSummaryDaily csd " +
            " WHERE csd.customerId = :customerId AND csd.asOfDate BETWEEN :startDate AND :endDate")
    List<AccountSummaryDaily> findAll(String customerId, LocalDate startDate, LocalDate endDate);
}
