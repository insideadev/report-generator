package com.dev.reportgenerator.dto.response;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductItem {
    private Double value;
    private Double percentage;
}
