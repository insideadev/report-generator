package com.dev.reportgenerator.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Date;

public class CustomWriteMonthly extends StdSerializer<LocalDate> {
    public CustomWriteMonthly() {
        this(null);
    }

    public CustomWriteMonthly(Class<LocalDate> t) {
        super(t);
    }


    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        String s = null;
        String a = localDate.getMonth().toString().substring(0, 3)
                .replace(localDate.getMonth().toString()
                        .substring(1, 3), localDate.getMonth().toString().substring(1, 3).toLowerCase());
        if (localDate.getDayOfMonth() == 1) {


            s = localDate.getYear() + " " + a;
        } else
            s = localDate.getYear() + " " + a +" "+localDate.getDayOfMonth();



        jsonGenerator.writeObject(s);
    }
}
