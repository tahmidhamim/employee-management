package com.techlearn.employeemanagement.util;

import com.techlearn.employeemanagement.constant.Constant;
import com.techlearn.employeemanagement.exception.BadRequestException;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Converter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT)
            .withZone(ZoneOffset.UTC);

    public static String toString(Instant instant) {
        if (instant == null) {
            return null;
        }
        String dateTime = formatter.format(instant);
        return dateTime.substring(0, 10);
    }

    public static Instant toInstant(String date) throws BadRequestException {
        try {
            if (date == null) {
                return null;
            }
            String dateTime = date + "T00:00:00Z";
            return Instant.from(formatter.parse(dateTime));
        } catch (Exception e) {
            throw new BadRequestException("Invalid date format");
        }
    }
}
