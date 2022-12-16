package com.dev.reportgenerator.validator;

import com.dev.reportgenerator.annotation.ReportType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.dev.reportgenerator.enums.ReportType.of;
public class ReportTypeValidator implements ConstraintValidator<ReportType, String> {

    @Override
    public boolean isValid(String input, ConstraintValidatorContext context) {
        return input == null || of(input) != null;
    }
}
