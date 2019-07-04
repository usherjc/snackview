package com.usher.snack.util;

public class CommonUtils {

    /**
     * 格式化时间戳
     */
    public static String initFormatMusicTime(int time) {
        int minute = time / 1000 / 60;
        String min;
        if (minute < 10) {
            min = "0" + minute;
        } else {
            min = String.valueOf(minute);
        }
        int second = time / 1000 % 60;
        String sec;
        if (second < 10) {
            sec = "0" + second;
        } else {
            sec = String.valueOf(second);
        }
        return min + ":" + sec;
    }

}
