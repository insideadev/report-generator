package com.dev.reportgenerator.response;

import com.dev.reportgenerator.custom.CustomWireFloat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositList {
    private Long value;
    @JsonSerialize(using = CustomWireFloat.class)
    private Float percentage;


}
