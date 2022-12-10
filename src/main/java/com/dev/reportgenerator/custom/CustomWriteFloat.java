package com.dev.reportgenerator.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CustomWriteFloat extends StdSerializer<Float> {
    public CustomWriteFloat(){
        this(null);
    }
    public CustomWriteFloat(Class<Float> t) {
        super(t);
    }
    @Override
    public void serialize(Float aFloat, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        BigDecimal s = new BigDecimal(aFloat).setScale(2, RoundingMode.HALF_UP);

        jsonGenerator.writeObject(s);

    }
}