package com.dev.reportgenerator.service;

import com.dev.reportgenerator.dto.request.ReportRequest;
import com.dev.reportgenerator.dto.response.ReportResponse;

public interface CustomerSummaryService {

    ReportResponse generateReport(ReportRequest request);
}
