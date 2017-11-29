package com.amagi.account.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.SimpleTimeZone;

public final class Utils {

    private static Calendar calendar;
    private static SimpleDateFormat sdf;
    static {
        calendar = Calendar.getInstance();
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
    }

    public static String getDateInUTCFormat(long time) {
        calendar.setTimeInMillis(time);
        return sdf.format(calendar.getTime());
    }
}
