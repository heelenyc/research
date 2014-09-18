package com.heelenyc.research.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具
 */
public class DateUtils {
    // 标准日期时间格式
    /**
     * yyyy-MM
     */
    public static final String FORMAT_MONTH = "yyyy-MM";
    /**
     * yyyy-MM-dd
     */
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    /**
     * yyyy-MM-dd HH:mm
     */
    public static final String FORMAT_DATE_MINUTE = "yyyy-MM-dd HH:mm";
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * HH:mm:ss
     */
    public static final String FORMAT_TIME = "HH:mm:ss";
    /**
     * HH:mm
     */
    public static final String FORMAT_MINUTE = "HH:mm";

    // 无符号格式
    /**
     * yyyyMM
     */
    public static final String FORMAT_MONTH_UNSIGNED = "yyyyMM";
    /**
     * yyyyMMdd
     */
    public static final String FORMAT_DATE_UNSIGNED = "yyyyMMdd";
    /**
     * yyyyMMddHHmm
     */
    public static final String FORMAT_DATE_MINUTE_UNSIGNED = "yyyyMMddHHmm";
    /**
     * yyyyMMddHHmmss
     */
    public static final String FORMAT_DATE_TIME_UNSIGNED = "yyyyMMddHHmmss";

    /**
     * HHmmss
     */
    public static final String FORMAT_TIME_UNSIGNED = "HHmmss";
    /**
     * HHmm
     */
    public static final String FORMAT_MINUTE_UNSIGNED = "HHmm";

    /**
     * 美东时区
     */
    public static final TimeZone EST_TIME_ZONE = TimeZone.getTimeZone("America/New_York");

    /**
     * 将标准时间转成时间格式
     * 
     * @param date 标准时间
     * @return 时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static String format(Date date) {
        return format(date, FORMAT_DATE_TIME);
    }

    /**
     * 按指定格式格式化时期时间
     * 
     * @param date date
     * @param format format
     * @return string.
     */
    public static String format(Date date, String format) {
        return format(date, format, null);
    }

    /**
     * 将标准时间转成美东时间格式
     * 
     * @param date 标准时间
     * @return 美东时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static String formatToEst(Date date) {
        return formatToEst(date, FORMAT_DATE_TIME);
    }

    /**
     * 将标准时间转成美东时间格式
     * 
     * @param date date
     * @param format format
     * @return string
     */
    public static String formatToEst(Date date, String format) {
        return format(date, format, EST_TIME_ZONE);
    }

    private static String format(Date date, String format, TimeZone zone) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(format);
            if (zone != null) {
                df.setTimeZone(zone);
            }
            return df.format(date);
        } else {
            return "";
        }
    }

    /**
     * 将时间的字符串格式转成Date
     * 
     * @param str str
     * @return Date
     */
    public static Date parse(String str) {
        return parse(str, FORMAT_DATE_TIME);
    }

    /**
     * 按指定格式解析字符串，将字符串转为日期时间格式
     * 
     * @param str string
     * @param format format
     * @return date
     */
    public static Date parse(String str, String format) {
        return parse(str, format, null);
    }

    /**
     * 将美东时间的字符串格式转成Date
     * 
     * @param str string
     * @return Date
     */
    public static Date parseFromEst(String str) {
        return parseFromEst(str, FORMAT_DATE_TIME);
    }

    /**
     * 将美东时间的字符串格式转成Date
     * 
     * @param str string
     * @param format format
     * @return Date
     */
    public static Date parseFromEst(String str, String format) {
        return parse(str, format, EST_TIME_ZONE);
    }

    private static Date parse(String str, String format, TimeZone zone) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        if (zone != null) {
            df.setTimeZone(zone);
        }
        try {
            return df.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将时间毫秒数转成标准时间格式
     * 
     * @param time 时间毫秒数
     * @return Date
     */
    public static Date toDate(double time) {
        return new Date(Math.round(time * 1000));
    }

    /**
     * 将时间毫秒数转成标准时间格式
     * 
     * @param time 时间毫秒数
     * @return Date
     */
    public static Date toDate(String time) {
        return toDate(Double.parseDouble(time));
    }

    /**
     * 将Date转换成分钟表示形式：2013-08-07 01:30:00 => 201308070090
     * 
     * @param date date
     * @return min
     */
    public static long dateToMin(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR) * 100000000L + (cal.get(Calendar.MONTH) + 1) * 1000000 + cal.get(Calendar.DATE)
                * 10000 + cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
    }

    /**
     * 将Data转换成分钟表示形式：2013-08-07 01:30:00 => 201308070090
     * 
     * @param date date
     * @param format format
     * @return min
     * @throws RuntimeException
     */
    public static long dateToMin(String date, String format) throws RuntimeException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return dateToMin(sdf.parse(date));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将分钟表示形式 201308070090 转换为Date
     * 
     * @param min min
     * @return Date
     */
    public static Date minToDate(long min) {
        long year = min / 100000000;
        long left = min - year * 100000000;
        long month = left / 1000000;
        left -= month * 1000000;
        long day = left / 10000;
        left -= day * 10000;
        long hour = left / 60;
        long minute = left - hour * 60;

        Calendar cal = Calendar.getInstance();
        cal.set((int) year, (int) month - 1, (int) day, (int) hour, (int) minute, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 将分钟表示形式 201308070090 转换为Date表示的format形式
     * 
     * @param min min
     * @param format format
     * @return
     */
    public static String minToDate(long min, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(minToDate(min));
    }
    
    /**
     * 显示指定时间距离当前的天分秒数据
     * @param comDate
     * @param format
     * @return
     */
    public static String recentFormat(Date comDate,String format) {
		Date now = new Date();
		if( comDate == null )
			return "";
		
		long range = now.getTime() - comDate.getTime();
		if (range > 0 && range < dayMillisecond * 2) {
			return toReadableTime(range)+ " ago";
		}
		else {
			return format(comDate,format);
		}
	}
    
    private static long minuteMillisecond = 60 * 1000;
    private static long hourMillisecond = minuteMillisecond * 60;
    private static long dayMillisecond = hourMillisecond * 24;
    
    private static String toReadableTime(long time) {
        long day = time / dayMillisecond;

        long left = time - day * dayMillisecond;
        long hour = left / hourMillisecond;

        left = left - hour * hourMillisecond;
        long minute = left / minuteMillisecond;

        left = left - minute * minuteMillisecond;
        long second = left / 1000;

        long millisecond = left - second * 1000;
        
        if (millisecond > 0) {
        	second ++;
		}

        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day).append("天");
        }
        if (hour > 0) {
            sb.append(hour).append("小时");
        }
        if (minute > 0) {
            sb.append(minute).append("分");
        }
        if (second > 0) {
            sb.append(second).append("秒");
        }
//        if (millisecond > 0) {
//            sb.append(millisecond).append("ms ");
//        }
        return sb.toString().trim();
    }
}
