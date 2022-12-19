package com.dev.reportgenerator.controller;

import com.dev.reportgenerator.dto.request.ReportRequest;
import com.dev.reportgenerator.service.CustomerSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final CustomerSummaryService customerSummaryService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateReport(@RequestBody @Valid ReportRequest request) {
        return ResponseEntity.ok(customerSummaryService.generateReport(request));
    }
}
