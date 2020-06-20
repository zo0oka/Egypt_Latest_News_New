package com.zookanews.egyptlatestnews.helper;

import java.util.Calendar;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 20 Jun, 2020.
 * Have a nice day!
 */
public class DateFormatter {

    public static String getFormattedDateAgo(long timeMillis) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        long timePeriod = currentTime - timeMillis;

        long millisInSecond = 1000;
        long secondsInMinute = 60;
        long minutesInHour = 60;
        long hoursInDay = 24;
        long daysInMonth = 30;
        long daysInYear = 365;

        long millisInYear = daysInYear * hoursInDay * minutesInHour * secondsInMinute * millisInSecond;
        long millisInMonth = daysInMonth * hoursInDay * minutesInHour * secondsInMinute * millisInSecond;
        long millisInDay = hoursInDay * minutesInHour * secondsInMinute * millisInSecond;
        long millisInHour = minutesInHour * secondsInMinute * millisInSecond;
        long millisInMinute = secondsInMinute * millisInSecond;
        return "";
    }
}
