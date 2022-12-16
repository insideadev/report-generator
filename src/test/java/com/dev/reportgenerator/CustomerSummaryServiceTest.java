package com.dev.reportgenerator;

import com.dev.reportgenerator.dto.request.ReportRequest;
import com.dev.reportgenerator.dto.response.ReportResponse;
import com.dev.reportgenerator.service.CustomerSummaryService;
import com.dev.reportgenerator.service.impl.CustomerSummaryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class CustomerSummaryServiceTest {

    @InjectMocks
    private CustomerSummaryServiceImpl customerSummaryService;

    @Test
    void testGenerateReport() {
        ReportRequest request = ReportRequest.builder().build();
        ReportResponse response = customerSummaryService.generateReport(request);
        assertNull(response);
    }
}
