package com.manpower.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampUtil {
    public static String convertLocalDateToZatcaTimestamp(LocalDate date) {
        // Adjust the zone as per Saudi Arabia time (UTC+3)
        ZoneId saudiZone = ZoneId.of("Asia/Riyadh");

        // Convert to ZonedDateTime with default time 00:00:00
        ZonedDateTime zonedDateTime = ZonedDateTime.of(date, LocalTime.MIDNIGHT, saudiZone);

        // Format to ISO 8601 with offset
        return zonedDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
