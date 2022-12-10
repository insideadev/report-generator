package com.dev.reportgenerator.response;

import com.dev.reportgenerator.custom.CustomWriteFloat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositList {
    private Double value;
    @JsonSerialize(using = CustomWriteFloat.class)
    private Float percentage;


}
