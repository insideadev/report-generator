package com.dev.reportgenerator.service.impl;

import com.dev.reportgenerator.dto.request.ReportRequest;
import com.dev.reportgenerator.dto.response.ReportResponse;
import com.dev.reportgenerator.reporsitory.CustomerSummaryDailyRepository;
import com.dev.reportgenerator.service.CustomerSummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerSummaryServiceImpl implements CustomerSummaryService {

    @Override
    public ReportResponse generateReport(ReportRequest request) {
        return null;
    }
}
