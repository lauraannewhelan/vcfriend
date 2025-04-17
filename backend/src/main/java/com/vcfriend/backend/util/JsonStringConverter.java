package com.vcfriend.backend.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class JsonStringConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return attribute; // Already JSON string
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}
