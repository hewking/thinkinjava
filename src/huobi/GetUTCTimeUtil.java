package huobi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class GetUTCTimeUtil {

    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * 得到UTC时间，类型为字符串，格式为"yyyy-MM-dd HH:mm"<br />
     * 如果获取失败，返回null
     *
     * @return
     */
    public static String getUTCTimeStr() {
        StringBuffer UTCTimeBuffer = new StringBuffer();
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance();
        // 2、取得时间偏移量：
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
        // 3、取得夏令时差：
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH) + 1;
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        int hour = cal.get(Calendar.HOUR_OF_DAY);
//        int minute = cal.get(Calendar.MINUTE);
//        UTCTimeBuffer.append(year).append("-").append(month).append("-").append(day);
//        UTCTimeBuffer.append(" ").append(hour).append(":").append(minute);
//        try {

        return format.format(cal.getTime());

//            format.parse(UTCTimeBuffer.toString()) ;
//            return UTCTimeBuffer.toString() ;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return "";
    }

    /**
     * 将UTC时间转换为东八区时间
     *
     * @param UTCTime
     * @return
     */
    public static String getLocalTimeFromUTC(String UTCTime) {
        java.util.Date UTCDate = null;
        String localTimeStr = null;
        try {
            UTCDate = format.parse(UTCTime);
            format.setTimeZone(TimeZone.getTimeZone("GMT-8"));
            localTimeStr = format.format(UTCDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return localTimeStr;
    }

    public static String getCurrentTimeZone() {
        TimeZone tz = TimeZone.getDefault();
        return createGmtOffsetString(true, true, tz.getRawOffset());
    }

    public static String createGmtOffsetString(boolean includeGmt,
                                               boolean includeMinuteSeparator, int offsetMillis) {
        int offsetMinutes = offsetMillis / 60000;
        char sign = '+';
        if (offsetMinutes < 0) {
            sign = '-';
            offsetMinutes = -offsetMinutes;
        }
        StringBuilder builder = new StringBuilder(9);
        if (includeGmt) {
            builder.append("GMT");
        }
        builder.append(sign);
        appendNumber(builder, 2, offsetMinutes / 60);
        if (includeMinuteSeparator) {
            builder.append(':');
        }
        appendNumber(builder, 2, offsetMinutes % 60);
        return builder.toString();
    }

    private static void appendNumber(StringBuilder builder, int count, int value) {
        String string = Integer.toString(value);
        for (int i = 0; i < count - string.length(); i++) {
            builder.append('0');
        }
        builder.append(string);
    }

    public static long time2TimeZone(long millis) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT-08:00"));
        try {
            return format.parse(format.format(new Date(millis))).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static void main(String[] args) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String UTCTimeStr = getUTCTimeStr();
        System.out.println(UTCTimeStr);
        System.out.println(getLocalTimeFromUTC(UTCTimeStr));
        System.out.println("currennt timezone : " + getCurrentTimeZone());
        System.out.println("currennt timezone2 : " + TimeZone.getDefault().getDisplayName(Locale.CHINA));
        System.out.println("currennt timezone2 : " + TimeZone.getDefault().getID());
        System.out.println("current time : " + format.format(time2TimeZone(System.currentTimeMillis())));

        TimeZone zone = TimeZone.getTimeZone("GMT+08:00");
        long offset = zone.getRawOffset();

        System.out.println("current offset time : " + format.format(System.currentTimeMillis() - offset));
    }




    public static String getChinaTime(long millis) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8:00");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(new Date(millis));
    }

}