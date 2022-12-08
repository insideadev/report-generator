package com.dev.reportgenerator.repository;

import com.dev.reportgenerator.entity.SummaryMonthly;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISummaryMonthlyRepo extends JpaRepository<SummaryMonthly,Long> {
}
