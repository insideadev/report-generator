package com.dev.reportgenerator.mock;

import com.dev.reportgenerator.controller.ReportController;
import com.dev.reportgenerator.dto.request.ReportRequest;
import com.dev.reportgenerator.dto.response.ReportResponse;
import com.dev.reportgenerator.service.CustomerSummaryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReportController.class)
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerSummaryService customerSummaryService;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        when(customerSummaryService.generateReport(any())).thenReturn(ReportResponse.builder().build());

        File file = new ClassPathResource("mockData/createReportRequest.json").getFile();
        ReportRequest request = objectMapper.readValue(file, ReportRequest.class);

        mockMvc.perform(post("/reports/generate")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
