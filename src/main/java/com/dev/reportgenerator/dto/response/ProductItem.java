package com.dev.reportgenerator.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
public class ProductItem {
    private Double value;
    private Double percentage;
}
