package com.ramitsuri.depressiontracker.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateHelper {
    private static final String FORMAT_FRIENDLY = "MMM dd";
    private static final long MILLI_SECONDS_IN_DAY = 86400000;
    private static final long SHEETS_DATE_OFFSET = 25569;

    public static String getFriendlyDate(long date) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_FRIENDLY, Locale.getDefault());
        return format.format(date);
    }

    public static long toSheetsDate(long date) {
        return date / MILLI_SECONDS_IN_DAY + SHEETS_DATE_OFFSET;
    }

    public static long fromSheetsDate(long sheetsDate) {
        return (sheetsDate - SHEETS_DATE_OFFSET) * MILLI_SECONDS_IN_DAY;
    }
}
