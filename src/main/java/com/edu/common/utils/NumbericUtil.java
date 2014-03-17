package com.edu.common.utils;


/**
 * @author yicheng
 * 
 */
public class NumbericUtil {

    /**
     * 将数值类型转换为human readable的数字,支持四种封装类型Integer Long Float Double
     * 
     * @param obj
     * @return
     */
    public static String getReadableNumberic(Object obj) {
        if (obj != null) {
            if (obj instanceof Integer)
                return getReadableNumberic((Integer) obj);
            else if (obj instanceof Long)
                return getReadableNumberic((Long) obj);
            else if (obj instanceof Float)
                return getReadableNumberic((Float) obj);
            else if (obj instanceof Double)
                return getReadableNumberic((Double) obj);
            else if (obj instanceof String)
                return getReadableNumberic((String) obj);
            else
                return null;
        } else {
            return null;
        }
    }

    public static String getReadableNumberic(Integer value) {

        if (value >= 1000000000)
            return String.format("%.2fG", value / 1000000000f);
        else if (value >= 1000000)
            return String.format("%.2fM", value / 1000000f);
        else if (value >= 1000)
            return String.format("%.1fK", value / 1000f);
        else
            return String.valueOf(value);
    }

    public static String getReadableNumberic(Long value) {

        if (value >= 1000000000)
            return String.format("%.2fG", value / 1000000000f);
        else if (value >= 1000000)
            return String.format("%.2fM", value / 1000000f);
        else if (value >= 1000)
            return String.format("%.1fK", value / 1000f);
        else
            return String.valueOf(value);
    }

    public static String getReadableNumberic(Float value) {

        if (value >= 1000000000)
            return String.format("%.2fG", value / 1000000000f);
        else if (value >= 1000000)
            return String.format("%.2fM", value / 1000000f);
        else if (value >= 1000)
            return String.format("%.1fK", value / 1000f);
        else
            return String.valueOf(value);
    }

    public static String getReadableNumberic(Double value) {

        if (value >= 1000000000)
            return String.format("%.2fG", value / 1000000000f);
        else if (value >= 1000000)
            return String.format("%.2fM", value / 1000000f);
        else if (value >= 1000)
            return String.format("%.1fK", value / 1000f);
        else
            return String.valueOf(value);
    }

    /**
     * test
     */
    public static void main(String[] args) {
        // Object l = 2123123123145.9273723d;
        // System.out.println(l instanceof Double);
        // System.out.println(getReadableNumberic(l));
        // //System.out.println(getReadableNumberic(l));
        Object obj = new String("1000000000000");
        System.out.println(obj instanceof String);
        System.out.println(getReadableNumberic(obj));
    }
    
    public static String getReadableNumberic(String value) {
        try {
            Double value_double = Double.parseDouble(value );
            return getReadableNumberic(value_double);
        } catch (Exception e) {
            return value;
        }
    }

    /**
     * 将1K转换为1000的字符串
     * @param value
     * @return
     */
    public static String parseReadableNumber(String value) {
        try {
            if (value != null) {
                Character multiRector = value.charAt(value.length() - 1);
                String numbString = value.substring(0, value.length() - 1);
                multiRector = Character.toUpperCase(multiRector);
                if (multiRector.equals('G')) {
                    return Long.valueOf(Long.parseLong(numbString) * 1000 * 1000 * 1000).toString();
                } else if (multiRector.equals('M')) {
                    return Long.valueOf(Long.parseLong(numbString) * 1000 * 1000).toString();
                } else if (multiRector.equals('K')) {
                    return Long.valueOf(Long.parseLong(numbString) * 1000).toString();
                } else
                    return value;
            } else
                return null;
        } catch (Exception e) {
            return value;
        }
        
    }
    
    private static long minuteMillisecond = 60 * 1000;
    private static long hourMillisecond = minuteMillisecond * 60;
    private static long dayMillisecond = hourMillisecond * 24;

    public static String toReadableTime(long time) {
        long day = time / dayMillisecond;

        long left = time - day * dayMillisecond;
        long hour = left / hourMillisecond;

        left = left - hour * hourMillisecond;
        long minute = left / minuteMillisecond;

        left = left - minute * minuteMillisecond;
        long second = left / 1000;

        long millisecond = left - second * 1000;

        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day).append("d ");
        }
        if (hour > 0) {
            sb.append(hour).append("h ");
        }
        if (minute > 0) {
            sb.append(minute).append("m ");
        }
        if (second > 0) {
            sb.append(second).append("s ");
        }
        if (millisecond > 0) {
            sb.append(millisecond).append("ms ");
        }
        return sb.toString().trim();
    }
    
    /**
     * 将 1s 转换为 1000 
     * @return
     */
    public static long parseReadableTimeToMS(String value) {
        try {
            if (value != null) {
                Character multiRector = value.charAt(value.length() - 1);
                String numbString = value.substring(0, value.length() - 1);
                multiRector = Character.toUpperCase(multiRector);
                if (multiRector.equals('S')) {
                    return Long.valueOf(Long.parseLong(numbString) * 1000);
                } else if (multiRector.equals('M')) {
                    return Long.valueOf(Long.parseLong(numbString) * minuteMillisecond);
                } else if (multiRector.equals('H')) {
                    return Long.valueOf(Long.parseLong(numbString) * hourMillisecond);
                } else if (multiRector.equals('D')) {
                    return Long.valueOf(Long.parseLong(numbString) * dayMillisecond);
                } else
                    return Long.valueOf(value);
            } else
                return 0;
        } catch (Exception e) {
            return 0;
        }
        
    }
}
