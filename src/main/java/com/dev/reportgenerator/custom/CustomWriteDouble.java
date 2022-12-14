package com.dev.reportgenerator.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CustomWriteDouble extends StdSerializer<Double> {
    public CustomWriteDouble(){
        this(null);
    }
    public CustomWriteDouble(Class<Double> t) {
        super(t);
    }
    @Override
    public void serialize(Double aFloat, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        BigDecimal s = new BigDecimal(aFloat).setScale(0, RoundingMode.HALF_UP);

        jsonGenerator.writeObject(s);

    }
}
