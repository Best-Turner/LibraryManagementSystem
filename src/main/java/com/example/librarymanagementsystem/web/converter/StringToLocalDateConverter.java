package com.example.librarymanagementsystem.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Component
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    private static final String FORMAT_FOR_DATE = "dd.MM.yyyy";
    public static final String DATE_BY_DEFAULT = "1800-01-01";
    @Override
    public LocalDate convert(String source) {
        if (source == null || source.isEmpty()) {
            source = DATE_BY_DEFAULT;
            System.out.println("Назначили свою дату");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_FOR_DATE);
        System.out.println("Конвертер даты");
        LocalDate date = LocalDate.parse(source, formatter);
        System.out.println("дата после парсинга " +  date);
        return date;
    }
}
