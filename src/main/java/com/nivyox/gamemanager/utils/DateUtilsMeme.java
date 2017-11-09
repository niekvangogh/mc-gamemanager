package com.nivyox.gamemanager.utils;

/**
 * Created by Niek on 1-7-2017.
 * This is project (DragonMemescape) has been made my Nivyox (https://twitter.com/nivyox).
 * If you want to reuse the code or make something using this code, please inform me. Credits appreciated.
 */
public class DateUtilsMeme {

    public static String getTimeFromSeconds(int totalSecs) {
        int hours = totalSecs / 3600;
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;
        if (minutes == 0) {
            return String.format("00:%2d", seconds);
        } else if (hours == 0) {
            return String.format("%2d:%2d", minutes, seconds);
        } else {
            return String.format("%2d:%2d:%2d", hours, minutes, seconds);
        }
    }
}