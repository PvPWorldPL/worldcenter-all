package pl.textr.boxpvp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public enum TimeUtil {
    TICK("TICK", 0, "TICK", 0, 50, 50),
    MILLISECOND("MILLISECOND", 1, "MILLISECOND", 1, 1, 1),
    SECOND("SECOND", 2, "SECOND", 2, 1000, 1000),
    MINUTE("MINUTE", 3, "MINUTE", 3, 60000, 60),
    HOUR("HOUR", 4, "HOUR", 4, 3600000, 60),
    DAY("DAY", 5, "DAY", 5, 86400000, 24),
    WEEK("WEEK", 6, "WEEK", 6, 604800000, 7);

    public static final int MPT = 50;
    public static String sec;
    public static String min;
    public static String hr;
    public static String day;
    static SimpleDateFormat timeFormat;

    static {
        TimeUtil.timeFormat = new SimpleDateFormat("HH:mm:ss");
        TimeUtil.sec = "sek";
        TimeUtil.min = "min";
        TimeUtil.hr = "godz";
        TimeUtil.day = "d";
    }

    private final int time;
    private final int timeMulti;

    TimeUtil(final String s2, final int n2, final String s, final int n, final int time, final int timeMulti) {
        this.time = time;
        this.timeMulti = timeMulti;
    }
    


    public static String formatTimeMillisToDate(long millis) {
        if (millis < 0) {
            return "nigdy";
        }

        return new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(new Date(millis));
    }

    


    public static String formatTimeMillis(long millis) {
        if (millis == -1) {
            return "nigdy";
        }

        long seconds = millis / 1000L;

        if (seconds <= 0) {
            return (int) TimeUnit.MILLISECONDS.toMillis(millis) + "ms";
        }

        long minutes = seconds / 60;
        seconds = seconds % 60;
        long hours = minutes / 60;
        minutes = minutes % 60;
        long day = hours / 24;
        hours = hours % 24;
        long years = day / 365;
        day = day % 365;

        StringBuilder time = new StringBuilder();

        if (years != 0) {
            time.append(years).append("r");
        }

        if (day != 0) {
            time.append(day).append("d");
        }

        if (hours != 0) {
            time.append(hours).append("h");
        }

        if (minutes != 0) {
            time.append(minutes).append("m");
        }

        if (seconds != 0) {
            time.append(seconds).append("s");
        }

        return time.toString().trim();
    }


    

    public static String getTime2(final long l) {
        if (l < 60L) {
            return l + TimeUtil.sec;
        }
        final int minutes = (int) (l / 60L);
        final int s = 60 * minutes;
        final int secondsLeft = (int) (l - s);
        if (minutes < 60) {
            if (secondsLeft > 0) {
                return minutes + TimeUtil.min + " " + secondsLeft + TimeUtil.sec;
            }
            return minutes + TimeUtil.min;
        } else {
            if (minutes < 1440) {
                String time = "";
                final int hours = minutes / 60;
                time = hours + TimeUtil.hr;
                final int inMins = 60 * hours;
                final int left = minutes - inMins;
                if (left >= 1) {
                    time = time + " " + left + TimeUtil.min;
                }
                if (secondsLeft > 0) {
                    time = time + " " + secondsLeft + TimeUtil.sec;
                }
                return time;
            }
            String time = "";
            final int days = minutes / 1440;
            time = days + TimeUtil.day;
            final int inMins = 1440 * days;
            final int leftOver = minutes - inMins;
            if (leftOver >= 1) {
                if (leftOver < 60) {
                    time = time + " " + leftOver + TimeUtil.min;
                } else {
                    final int hours2 = leftOver / 60;
                    time = time + " " + hours2 + TimeUtil.hr;
                    final int hoursInMins = 60 * hours2;
                    final int minsLeft = leftOver - hoursInMins;
                    if (leftOver >= 1) {
                        time = time + " " + minsLeft + TimeUtil.min;
                    }
                }
            }
            if (secondsLeft > 0) {
                time = time + " " + secondsLeft + TimeUtil.sec;
            }
            return time;
        }
    }

    public int getMulti() {
        return this.timeMulti;
    }

    public int getTime() {
        return this.time;
    }

    public int getTick() {
        return this.time / 50;
    }

    public int getTime(final int multi) {
        return this.time * multi;
    }

    public int getTick(final int multi) {
        return this.getTick() * multi;
    }
}
