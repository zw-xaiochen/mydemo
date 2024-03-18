package com.cjy.util.date;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * @author Administrator
 */
public class MyDateUtil {

    public static final String DATE_FORMAT_SHORT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT = "yyyyMMddHHmmss";

    public static final String DATE_Y_M_D_FORMAT = "yyyy-MM-dd";

    public static final String DATE_YMD_FORMAT = "yyyyMMdd";

    public static final String DATE_YMD_FORMAT_STR = "yyyy年MM月dd日";

    public static final String MONTH_FORMAT = "yyyyMM";

    public static final String MONTH_FORMAT_STR = "yyyy年MM月";

    public static final String YEAR_FORMAT = "yyyy";

    public static final String MONTH_Y_M_FORMAT = "yyyy-MM";

    public static final String DATE_UTC_YY_FORMAT = "yyMMddHHmmss";

    private static final long THOUSAND_MILLISECOND = 1000;

    public static final String DATE_UTC_FORMAT_MILL = "yyyyMMddHHmmssSSS";

    public static String getDateFormatStr(long time) {
        return DateFormatUtils.format(time * 1000, DATE_FORMAT);
    }

    public static final ZoneOffset zoneOffset = ZoneOffset.ofHours(8);

    /**
     * 获取当前月份的第一天的0点的时间戳
     *
     * @return 时间戳-精度为秒
     */
    public static long getFirstDayOfCurrentMonth() {
        return ZonedDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).toEpochSecond();
    }

    /**
     * 获取当前月份的最后一天的23点59分59秒的时间戳
     *
     * @return 时间戳-精度为秒
     */
    public static long getLastDayOfCurrentMonth() {
        ZonedDateTime today = ZonedDateTime.now();
        return today.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59)
            .toEpochSecond();
    }

    /**
     * 获取当前月的上一个月的第一天的0点时间戳
     *
     * @return 时间戳-精确到秒
     */
    public static long getFirstDayOfLastMonth(Long second) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(second, 0, zoneOffset);
        // 当前时间月份
        int month = localDateTime.getMonthValue();
        long lastMonthFirstDayTime;
        // 如果是1月份,获取上一年12月份的第一天
        if (month == 1) {
            int year = localDateTime.getYear() - 1;
            lastMonthFirstDayTime =
                localDateTime.withYear(year).withMonth(12).with(TemporalAdjusters.firstDayOfMonth()).withHour(
                    0).withMinute(0).withSecond(0).toEpochSecond(zoneOffset);
        } else {
            lastMonthFirstDayTime =
                localDateTime.withMonth(month - 1).with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(
                    0).withSecond(0).toEpochSecond(zoneOffset);
        }
        return lastMonthFirstDayTime;
    }

    /**
     * 获取当前月的上一个月的最后一天0点的时间戳
     *
     * @return 时间戳-精确到秒
     */
    public static long getLastDayOfLastMonth(Long second) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(second, 0, zoneOffset);
        // 当前时间月份
        int month = localDateTime.getMonthValue();
        long lastMonthFirstDayTime;
        // 如果是1月份,获取上一年12月份的第一天
        if (month == 1) {
            int year = localDateTime.getYear() - 1;
            lastMonthFirstDayTime =
                localDateTime.withYear(year).withMonth(12).with(TemporalAdjusters.lastDayOfMonth()).withHour(
                    0).withMinute(0).withSecond(0).toEpochSecond(zoneOffset);
        } else {
            lastMonthFirstDayTime =
                localDateTime.withMonth(month - 1).with(TemporalAdjusters.lastDayOfMonth()).withHour(0).withMinute(
                    0).withSecond(0).toEpochSecond(zoneOffset);
        }
        return lastMonthFirstDayTime;
    }

    /**
     * 获取指定月份的第一天的时间戳(00:00:00)
     */
    public static Long getMonthFistDayTime(int year, int month) {
        return ZonedDateTime.now().withYear(year).withMonth(month).withDayOfMonth(1).withHour(0).withMinute(0)
            .withSecond(0).toEpochSecond();
    }

    /**
     * 根据时间戳获取月的第一天的0点的时间戳
     */
    public static Long getMonthFistDayTime(Long second) {
        // 指定时区为上海时区
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(second, 0, zoneOffset);
        return localDateTime.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withHour(0)
            .withMinute(0).withSecond(0).toEpochSecond(zoneOffset);
    }

    /**
     * 获取指定月份的最后一天的时间戳
     */
    public static Long getMonthLastDayTime(int year, int month) {
        return ZonedDateTime.now().withYear(year).withMonth(month).with(TemporalAdjusters.lastDayOfMonth()).withHour(0)
            .withMinute(0).withSecond(0).toEpochSecond();
    }

    /**
     * 根据时间戳获取月的最后一天的0点的时间戳
     */
    public static Long getMonthLastDayTime(Long second) {
        // 指定时区为上海时区
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(second, 0, zoneOffset);
        return localDateTime.with(TemporalAdjusters.lastDayOfMonth()).withHour(0).withHour(0)
            .withMinute(0).withSecond(0).toEpochSecond(zoneOffset);
    }

    /**
     * 获取指定年份的指定月的指定天的0点0分0秒时间戳(时间精度为毫秒)
     */
    public static Long getScheduleTime(int year, int month, int day) {
        return ZonedDateTime.now().withYear(year).withMonth(month).withDayOfMonth(day).withHour(0).withMinute(0)
            .withSecond(0).toEpochSecond();
    }

    /**
     * 时间戳是否正确
     *
     * @param time
     * @return
     */
    public static boolean timeDispose(Long time) {
        if (Objects.isNull(time) || time == 0) {
            return false;
        }
        String timeStr = time.toString();
        return timeStr.length() >= 10 && timeStr.length() <= 13;
    }

    /**
     * 获取两个时间戳相差的每个月份的每月的第一天和最后一天,主要解决大数据月表跨多月查询的问题 emmmmmm....
     */
    public static List<DateBean> getQueryTime(Long startTime, Long endTime) {
        List<DateBean> queryDates = new ArrayList<>();
        if (!timeDispose(startTime) || !timeDispose(endTime)) {
            return queryDates;
        }
        if (startTime.toString().length() == 10) {
            startTime = startTime * 1000;
        }
        if (endTime.toString().length() == 10) {
            endTime = endTime * 1000;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(startTime));
        // 开始时间的年
        int startYear = calendar.get(Calendar.YEAR);
        calendar.setTime(new Date(endTime));
        // 结束时间的年
        int endYear = calendar.get(Calendar.YEAR);
        // 年份相同
        if (startYear == endYear) {
            queryDates.addAll(getTwoDataEveryMonthFistDayAndLastDay(startTime, endTime));
        } else { // 年份不同
            // 获取开始年的最后一个月的最后一天的时间戳
            Long yearLastDay = getScheduleTime(startYear, 12, 31) * 1000;
            queryDates.addAll(getTwoDataEveryMonthFistDayAndLastDay(startTime, yearLastDay));

            // 获取结束时间的年份的第一个月的第一天的时间戳
            Long yearFistDay = getScheduleTime(endYear, 1, 1) * 1000;
            queryDates.addAll(getTwoDataEveryMonthFistDayAndLastDay(yearFistDay, endTime));
        }
        return queryDates;
    }

    /**
     * 计算两个时间戳之间每天日期
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 每天的日期
     */
    public static List<String> getTwoTimeMiddleEveryDayTime(Long startTime, Long endTime) {
        List<DateBean> everyMonthTime = getQueryTime(startTime, endTime);
        List<String> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(everyMonthTime)) {
            return result;
        }
        // 根据获取到的相差的月份的每个月份的第一天和最后一天的时间戳,解析出每一天的0点到23点59分59秒的时间戳
        for (DateBean dateBean : everyMonthTime) {
            // 月的开始时间
            Long start = dateBean.getStartTime();
            // 月的结束时间 两个时间为同一月
            Long end = dateBean.getEndTime();
            if (startTime != 0 && endTime != 0) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(start));
                int startYear = calendar.get(Calendar.YEAR);
                int startMonth = calendar.get(Calendar.MONTH) + 1;
                // 月开始的日期
                int startDay = calendar.get(Calendar.DAY_OF_MONTH);
                calendar.setTime(new Date(end));
                int endDay = calendar.get(Calendar.DAY_OF_MONTH);
                for (int index = startDay; index <= endDay; index++) {
                    String month = startMonth < 10 ? "0" + startMonth : String.valueOf(startMonth);
                    String day = index < 10 ? "0" + index : String.valueOf(index);
                    String onlineDate = startYear + "" + month + "" + day;
                    result.add(onlineDate);
                }
            }
        }
        return result;
    }

    private static List<DateBean> getTwoDataEveryMonthFistDayAndLastDay(Long startTime, Long endTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(startTime));
        // 开始时间的年
        int startYear = calendar.get(Calendar.YEAR);
        // 开始时间的月
        int startMonth = calendar.get(Calendar.MONTH) + 1;
        calendar.setTime(new Date(endTime));
        // 结束时间的年
        int endYear = calendar.get(Calendar.YEAR);
        // 结束时间的月
        int endMonth = calendar.get(Calendar.MONTH) + 1;
        List<DateBean> result = new ArrayList<>();
        // 同一月
        if (endMonth == startMonth) {
            DateBean data = new DateBean();
            data.setStartTime(startTime);
            data.setEndTime(endTime);
            result.add(data);
        } else {
            // 两个月份的差值
            int value = endMonth - startMonth;
            DateBean data;
            for (int index = 0; index <= value; index++) {
                data = new DateBean();
                int resultMonth = startMonth + index;
                Long resultMonthFistTime;
                Long resultMonthEndTime;
                // 第一个月
                if (resultMonth == startMonth) {
                    resultMonthFistTime = startTime;
                    resultMonthEndTime = getMonthLastDayTime(startYear, startMonth) * 1000;
                } else if (resultMonth == endMonth) {
                    // 最后一个月
                    resultMonthFistTime = getMonthFistDayTime(endYear, endMonth) * 1000;
                    resultMonthEndTime = endTime;
                } else {
                    // 中间的月
                    resultMonthFistTime = getMonthFistDayTime(startYear, resultMonth) * 1000;
                    resultMonthEndTime = getMonthLastDayTime(startYear, resultMonth) * 1000;
                }
                data.setStartTime(resultMonthFistTime);
                data.setEndTime(resultMonthEndTime);
                result.add(data);
            }
        }
        return result;
    }

    /**
     * 将毫秒值转换为指定日期格式
     *
     * @param formatTime 转换时间
     * @param dateFormat 日期格式
     * @return
     */
    public static String dateFormat(long formatTime, String dateFormat) {
        return DateFormatUtils.format(formatTime, dateFormat);
    }

    /**
     * 将前几天的毫秒值转换为指定日期格式
     *
     * @param formatTime 转换时间
     * @param beforeDay  前几天
     * @param dateFormat 日期格式
     * @return
     */
    public static String getBeforeDay(long formatTime, int beforeDay, String dateFormat) {
        formatTime = formatTime - beforeDay * 24 * 60 * 60 * 1000;
        return DateFormatUtils.format(formatTime, dateFormat);
    }

    /**
     * 取指定时间的小时值
     *
     * @param millisecond
     * @return
     */
    public static String formatHour(long millisecond) {
        BigDecimal bigDecimal = new BigDecimal(millisecond).divide(new BigDecimal(3600000), 1, RoundingMode.HALF_UP);
        double hours = bigDecimal.doubleValue();
        if (hours > 0) {
            return String.valueOf(hours);
        } else {
            return "0";
        }
    }

    /**
     * 转换指定时间为 X天X小时X分X秒 格式
     *
     * @param millisecond
     * @return
     */
    public static String formatDuring(long millisecond) {
        long days = millisecond / (1000 * 60 * 60 * 24);
        long hours = (millisecond % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (millisecond % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (millisecond % (1000 * 60)) / 1000;
        StringBuilder timeBuilder = new StringBuilder();
        if (days > 0) {
            timeBuilder.append(days).append("天");
        }
        if (hours > 0) {
            timeBuilder.append(hours).append("小时");
        }
        if (minutes > 0) {
            timeBuilder.append(minutes).append("分");
        }
        if (seconds > 0) {
            timeBuilder.append(seconds).append("秒");
        }
        String timeStr = timeBuilder.toString();
        return StringUtils.isNotEmpty(timeStr) ? timeStr : "0";
    }

    /**
     * 比较传进来的时间戳的日期是否与今天的日期相同
     */
    public static boolean judgeDateDay(String dateString, String dateFormat) throws Exception {
        if (StringUtils.isBlank(dateString)) {
            return false;
        }
        if (StringUtils.isBlank(dateFormat)) {
            dateFormat = DATE_FORMAT;
        }
        long timestamp = DateUtils.parseDate(dateString, dateFormat).getTime() / 1000;
        LocalDate now = LocalDate.now();
        LocalDate time = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(8)).toLocalDate();
        return now.equals(time);
    }

    /**
     * 获取传入的时间戳的当天的00:00:00的时间戳-精度为秒
     */
    public static Long getDayStartTimestamp(Long timestamp) {
        if (!timeDispose(timestamp)) {
            return 0L;
        }
        if (timestamp.toString().length() == 13) {
            // 毫秒转换为秒
            timestamp = timestamp / 1000;
        }
        // ZoneOffset.ofHours(8) 表示上海时区
        LocalDateTime localDateTime =
            LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(8)).withHour(0).withMinute(0).withSecond(0);
        return localDateTime.toEpochSecond(ZoneOffset.ofHours(8));
    }

    /**
     * 获取传入的时间戳的当天的23:59:59的时间戳-精度为秒
     */
    public static Long getDayEndTimestamp(Long timestamp) {
        if (!timeDispose(timestamp)) {
            return 0L;
        }
        if (timestamp.toString().length() == 13) {
            // 毫秒转换为秒
            timestamp = timestamp / 1000;
        }
        // ZoneOffset.ofHours(8) 表示上海时区
        LocalDateTime localDateTime =
            LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(8)).withHour(23).withMinute(59).withSecond(59);
        return localDateTime.toEpochSecond(ZoneOffset.ofHours(8));
    }

    /**
     * 根据传入的时间戳返回毫秒时间
     *
     * @return
     */
    public static Long getMillisecond(Long timestamp) {
        if (timestamp != null) {
            String a = timestamp.toString();
            if (10 == a.length()) {
                timestamp *= 1000;
            }
        }
        return timestamp;
    }

    /**
     * 计算两个时间戳之间的日期
     */
    public static int getTwoTimeDifference(Long starTime, Long endTime) {
        int differenceDay = 0;
        if (starTime != null && starTime != 0 && endTime != null && endTime != 0) {
            int stLength = String.valueOf(starTime).length();
            if (stLength == 13) { // 如果时间戳精确到毫秒
                starTime = starTime / 1000;
            }
            int edLength = String.valueOf(endTime).length();
            if (edLength == 13) {
                endTime = endTime / 1000;
            }
            differenceDay = (int) (endTime - starTime) / 86399;
        }
        return differenceDay;
    }

    /**
     * 分钟转换日期
     */
    public static String formatMinToString(long min) {
        int mi = 1;
        int hh = mi * 60;
        long hour = min / hh;
        long minute = (min - hour * hh) / mi;
        String strHour = hour + "";
        String strMinute = minute + "";
        return strHour + "小时" + strMinute + "分钟";
    }

    public static String getLongToDateStr(Long longTime, String dateFormat) {
        if (dateFormat == null) {
            dateFormat = "yyyy-MM-dd HH:mm:ss";
        }
        if (longTime != null && longTime > 0L) {
            SimpleDateFormat df = new SimpleDateFormat(dateFormat);
            String dateStr = df.format(longTime);
            return dateStr;
        }
        return "";
    }

    /**
     * 获取今天的23点59分59秒的时间戳
     */
    public static Long getTodayEndTime() {
        return Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59))).getTime()
            / THOUSAND_MILLISECOND;
    }

    /**
     * 获取今天的00点00分00秒的时间戳
     */
    public static Long getTodayStartTime() {
        return ZonedDateTime.now(ZoneOffset.ofHours(8)).withHour(0).withMinute(0).withSecond(0).toEpochSecond();
    }

    /**
     * 毫秒转换为 xx天xx小时xx分钟xx秒
     *
     * @param ms 毫秒值
     * @return
     */
    public static String formatTime(long ms) {
        if (ms == 0) {
            return "0秒";
        }
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        if (day == 0 && hour == 0 && minute == 0) {
            return second + "秒";
        }
        if (day == 0 && hour == 0) {
            return minute + "分" + second + "秒";
        }
        if (day == 0) {
            return hour + "小时" + minute + "分" + second + "秒";
        }
        return day + "天" + hour + "小时" + minute + "分" + second + "秒";
    }

    public static String formatTimeSec(long secondVal, boolean allFormat) {
        //int ss = 1000;
        int mi = 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = secondVal / dd;
        long hour = (secondVal - day * dd) / hh;
        long minute = (secondVal - day * dd - hour * hh) / mi;
        long second = secondVal - day * dd - hour * hh - minute * mi;
        // long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = Long.toString(day);
        String strHour = Long.toString(hour);
        String strMinute = Long.toString(minute);
        String strSecond = Long.toString(second);
        // String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;// 毫秒
        // strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
        if (allFormat) {
            return strDay + "天" + strHour + "小时" + strMinute + "分" + strSecond + "秒";
        }
        if (strDay.equals("0") && strHour.equals("0") && strMinute.equals("0")) {
            return strSecond + "秒";
        }
        if (strDay.equals("0") && strHour.equals("0")) {
            return strMinute + "分" + strSecond + "秒";
        }
        if (strDay.equals("0")) {
            return strHour + "小时" + strMinute + "分" + strSecond + "秒";
        }
        return strDay + "天" + strHour + "小时" + strMinute + "分" + strSecond + "秒";
    }

    /**
     * 毫秒转换为 xx小时xx分xx秒
     * @param ms 毫秒值
     * @return string
     */
    public static String formatTimeToHms(long ms) {
        if (ms == 0) {
            return "0秒";
        }
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;

        long hour = ms / hh;
        long minute = (ms - hour * hh) / mi;
        long second = (ms - hour * hh - minute * mi) / ss;
        if (hour == 0 && minute == 0) {
            return second + "秒";
        }
        if (hour == 0) {
            return minute + "分" + second + "秒";
        }
        return hour + "小时" + minute + "分" + second + "秒";
    }

    /**
     * 毫秒转换为 xx天xx小时xx分钟
     *
     * @param mill 毫秒
     */
    public static String formatMinuteTime(long mill) {
        if (mill == 0) {
            return "0分";
        }
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = mill / dd;
        long hour = (mill - day * dd) / hh;
        long minute = (mill - day * dd - hour * hh) / mi;

        if (day == 0 && hour == 0 && minute == 0) {
            return "0分";
        }
        if (day == 0 && hour == 0) {
            return minute + "分";
        }
        if (day == 0 && minute == 0) {
            return hour + "小时";
        }
        if (hour == 0 && minute == 0) {
            return day + "天";
        }
        if (day == 0) {
            return hour + "小时" + minute + "分";
        }
        return day + "天" + hour + "小时" + minute + "分";
    }

    /**
     * 将时间值转换为HH:mm:ss
     *
     * @param data
     * @return
     */

    public static String getToHhMmSs(double data) {
        double totalSeconds = data * 60 * 60;
        Double hour = Math.floor(totalSeconds / 60 / 60);
        Double minute = Math.floor(totalSeconds / 60 % 60);
        Double second = Math.floor(totalSeconds % 60);
        return hour.intValue() + "小时" + minute.intValue() + "分" + second.intValue() + "秒";
    }

    /**
     * 获取传入的时间戳当天的00:00:00的时间戳
     */
    public static long getTimestampIntraDayFistTimestamp(long timestamp) {
        if (!timeDispose(timestamp)) {
            return 0;
        }
        if (String.valueOf(timestamp).length() == 13) {
            timestamp = timestamp / 1000;
        }
        // 时区 ZoneOffset.ofHours(8) 表示上海时区
        ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
        // 凌晨两点
        LocalDateTime localDateTime =
            LocalDateTime.ofEpochSecond(timestamp, 0, zoneOffset).withHour(0).withMinute(0).withSecond(0);
        return localDateTime.toEpochSecond(zoneOffset);
    }

    /**
     * 获取两个时间戳相差的每个日期的00:00:00的时间戳
     *
     * @param startTime 开始时间(时间戳,精度秒和毫秒都行)
     * @param endTime   结束时间(时间戳,精度秒和毫秒都行)
     * @return list
     */
    public static List<Long> getBetweenTimestamp(long startTime, long endTime) {
        if (!timeDispose(startTime) || !timeDispose(endTime)) {
            return new ArrayList<>();
        }
        List<Long> betweenTimeStamp = new ArrayList<>();
        long startTimeIntraDayFistTimestamp = getTimestampIntraDayFistTimestamp(startTime);
        long endTimeIntraDayFistTimestamp = getTimestampIntraDayFistTimestamp(endTime);
        while (startTimeIntraDayFistTimestamp <= endTimeIntraDayFistTimestamp) {
            // 把日期添加到集合
            betweenTimeStamp.add(startTimeIntraDayFistTimestamp);
            // 获取增加后的日期
            startTimeIntraDayFistTimestamp = startTimeIntraDayFistTimestamp + 86400;
        }
        return betweenTimeStamp;
    }

    /**
     * 获取指定日期指定天数之前的时间戳(想获取什么时候(自己指定)多少天之前(自己指定)的日期)
     * @param secondTime 秒
     * 返回精确到毫秒的时间戳
     */
    public static Long getPreviousData(Long secondTime, int appointedNumber) {
        if (secondTime > 0) {
            Long expectResult = secondTime - ((long) appointedNumber * 24 * 60 * 60 * 1000);
            return getDayStartTimestamp(expectResult) * 1000;
        }
        return 0L;
    }

    /**
     * 根据传入的时间戳获取去年的同一月份的第一天的00:00:00和最后一天的23:59:59的时间戳
     */
    private static DateBean getLastYearMonthTime(Long appointedTime) {
        // 指定时区为上海时区
        ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(appointedTime, 0, zoneOffset);
        int year = localDateTime.getYear() - 1;
        int month = localDateTime.getMonthValue();
        // 获取去年月份的第一天的00点时间
        Long startTime =
            localDateTime.withYear(year).withMonth(month).with(TemporalAdjusters.firstDayOfMonth()).withHour(0)
                .withMinute(0).withSecond(0).toEpochSecond(zoneOffset);
        Long endTime =
            localDateTime.withYear(year).withMonth(month).with(TemporalAdjusters.lastDayOfMonth()).withHour(23)
                .withMinute(59).withSecond(59).toEpochSecond(zoneOffset);
        DateBean dateBean = new DateBean();
        dateBean.setStartTime(startTime);
        dateBean.setEndTime(endTime);
        return dateBean;
    }

    /**
     * 获取去年同一天的00:00:00的时间戳
     *
     * @param appointedTime 指定时间 格式:时间戳 精度:秒
     */
    public static Long getLastYearSameDayTime(Long appointedTime) {
        // 指定时区为上海时区
        ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(appointedTime, 0, zoneOffset);
        int year = localDateTime.getYear() - 1;
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();
        // 月的最后一天
        int monthLastDay = localDateTime.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        if (day == monthLastDay) {
            localDateTime = localDateTime.withYear(year).withMonth(month).with(TemporalAdjusters.lastDayOfMonth());
        } else {
            localDateTime = localDateTime.withYear(year).withMonth(month).withDayOfMonth(day);
        }
        return localDateTime.withHour(0).withMinute(0).withSecond(0).toEpochSecond(zoneOffset);
    }

    /**
     * 根据时间戳获取上一个月的最后一天的00:00:00的时间戳和 23:59:59的时间戳
     *
     * @param appointedTime 指定时间 格式:时间戳 精度:秒
     */
    public static DateBean getLastMonthLastDayTime(Long appointedTime) {
        // 指定时区为上海时区
        ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(appointedTime, 0, zoneOffset);
        int month = localDateTime.getMonthValue() - 1;
        if (month == 1) {
            int year = localDateTime.getYear() - 1;
            localDateTime = localDateTime.withYear(year).withMonth(month);
        }
        localDateTime = localDateTime.withMonth(month).with(TemporalAdjusters.lastDayOfMonth());
        int monthDay = localDateTime.getDayOfMonth();
        int startMonth = monthDay - 1;
        Long startTime =
            localDateTime.withDayOfMonth(startMonth).withHour(0).withMinute(0).withSecond(0).toEpochSecond(zoneOffset);
        Long endTime =
            localDateTime.withDayOfMonth(monthDay).withHour(23).withMinute(59).withSecond(59).toEpochSecond(zoneOffset);
        DateBean dateBean = new DateBean();
        dateBean.setStartTime(startTime);
        dateBean.setEndTime(endTime);
        return dateBean;
    }

    /**
     * 根据传入的时间获取月份的第一天的时间戳
     *
     * @param appointedTime 指定时间 格式:时间戳 精度:秒
     */
    public static Long getFirstDayTimeOfMonth(Long appointedTime) {
        // 指定时区为上海时区
        ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(appointedTime, 0, zoneOffset);
        return localDateTime.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0)
            .toEpochSecond(zoneOffset);
    }

    /**
     * 根据传入的时间Long值转换为时间
     *
     * @param datetime
     * @return
     */
    public static Date getLongToDate(Long datetime) {
        Date date = new Date();
        date.setTime(datetime);
        return date;
    }

    /**
     * 根据传入的时间字符串及格式化转换时间
     *
     * @param dateTime
     * @param dateFormat
     * @return
     */
    public static Date getStringToDate(String dateTime, String dateFormat) {
        if (dateFormat == null || "".equals(dateFormat)) {
            dateFormat = DATE_FORMAT_SHORT;
        }
        SimpleDateFormat sdfShort = new SimpleDateFormat(dateFormat);
        try {
            return sdfShort.parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 得到的是毫秒值
     *
     * @param time
     * @param format
     * @return
     */
    public static Long getStringToLong(String time, String format) {
        try {
            if (StringUtils.isEmpty(format)) {
                format = DATE_FORMAT_SHORT;
            }
            return DateUtils.parseDate(time, format).getTime();
        } catch (Exception e) {
            //logger.error("时间字符串转数字错误！", e);
            return 0L;
        }
    }

    /**
     * 比较传进来的日期是否与今天的日期相同
     *
     * @param dateString 格式化时间
     */
    public static boolean judgeDateIsNow(String dateString, String dateFormat) throws Exception {
        if (StringUtils.isBlank(dateString)) {
            return false;
        }
        if (StringUtils.isBlank(dateFormat)) {
            dateFormat = DATE_FORMAT;
        }
        long timestamp = DateUtils.parseDate(dateString, dateFormat).getTime() / 1000;
        LocalDate now = LocalDate.now();
        LocalDate time = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(8)).toLocalDate();
        return now.equals(time);
    }

    /**
     * 判断一个时间戳是否正好是235959秒
     */
    public static boolean judgeDateIsEnd(Long time) {
        try {
            int stLength = String.valueOf(time).length();
            if (stLength == 13) { // 如果时间戳精确到毫秒
                time = time / 1000;
            }
            long dayEndTime =
                LocalDateTime.ofEpochSecond(time, 0, zoneOffset).withHour(23)
                    .withMinute(59).withSecond(59).toEpochSecond(zoneOffset);
            return time == dayEndTime;
        } catch (Exception e) {
            //logger.error("时间错误");
        }
        return false;
    }

    /**
     * 判断一个时间戳是否是本月
     *
     * @param time 时间戳 秒/毫秒
     */
    public static boolean judgeDateIsCurrentMonth(Long time) {
        try {
            int stLength = String.valueOf(time).length();
            // 如果时间戳精确到毫秒
            if (stLength == 13) {
                time = time / 1000;
            }
            LocalDate nowLoc = LocalDate.now();
            LocalDate localTime = LocalDateTime.ofEpochSecond(time, 0, ZoneOffset.ofHours(8)).toLocalDate();
            return nowLoc.getMonthValue() == localTime.getMonthValue();
        } catch (Exception e) {
            // 不做处理
        }
        return false;
    }

    /**
     * 根据时间获取月的天数
     *
     * @param time 时间戳 秒/毫秒
     */
    public static Integer getMonthDayNumByTime(Long time) {
        try {
            int stLength = String.valueOf(time).length();
            // 判断是毫秒还是秒
            if (stLength == 13) {
                time = time / 1000;
            }
            LocalDate localDate =
                LocalDateTime.ofEpochSecond(time, 0, zoneOffset).toLocalDate();
            return localDate.lengthOfMonth();
        } catch (Exception e) {
            // 不做处理
        }
        return 0;
    }

    /**
     * 获取当月已经过去的天数
     */
    public static Integer getCurrentMonthPastDays() {
        try {
            LocalDateTime localTime = LocalDateTime.now();
            return localTime.getDayOfMonth() - 1;
        } catch (Exception e) {
            // 不做处理
        }
        return 0;
    }

    /**
     * 获取月的天数
     * 本月仅返回以及过去的自然天数,不包含今天
     *
     * @return
     */
    public static Integer getMonthPassDay(Long monthTime) {
        // 月的天数
        int monthday;
        // 判断是否是当月
        if (judgeDateIsCurrentMonth(monthTime)) {
            monthday = getCurrentMonthPastDays();
        } else {
            monthday = getMonthDayNumByTime(monthTime);
        }
        return monthday;
    }

    /**
     * 获取月的已过去的自然天的最后一天
     */
    public static Long getMonthPassLastDayTime(Long second) {
        Long monthLastDayTime;
        // 当月
        if (judgeDateIsCurrentMonth(second)) {
            // 指定时区为上海时区
            LocalDateTime localDateTime = LocalDateTime.now();
            int passDay = localDateTime.getDayOfMonth();
            if (passDay == 1) {
                monthLastDayTime = second;
            } else {
                monthLastDayTime =
                    localDateTime.withDayOfMonth(localDateTime.getDayOfMonth() - 1).withHour(0).withHour(0)
                        .withMinute(0).withSecond(0).toEpochSecond(ZoneOffset.ofHours(8));
            }
        } else {
            monthLastDayTime = getMonthLastDayTime(second);
        }
        return monthLastDayTime;
    }

    /**
     * 判断两个日期是否是同一天
     * 判断两个日期00:00:00的时间戳是否相同
     *
     * @return
     */
    public static boolean judgeIsItTheSameDay(Long timeOne, Long timeTwo) {
        try {
            Long startTimeMill = getDayStartTimestamp(timeOne);
            Long startTimeTwoMill = getDayStartTimestamp(timeTwo);
            return startTimeMill.equals(startTimeTwoMill);
        } catch (Exception e) {
            //logger.error("判断两个日期是否是同一天, 检查传入日期是否正确", e);
            return false;
        }
    }

    /**
     * 获取昨天的时间戳
     *
     * @param dayTime 时间戳,秒
     * @return 所传时间戳前一天的时间戳
     */
    public static Long getYesterdayTime(long dayTime) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(dayTime, 0, zoneOffset);
        int monthDay = localDateTime.getDayOfMonth();
        long yesterdayTime;
        // 如果是月的第一天 跨月
        if (monthDay == 1) {
            // 如果是第一个月
            int month = localDateTime.getMonthValue();
            if (month == 1) {
                // 跨年 上一年最后一天
                int year = localDateTime.getYear() - 1;
                yesterdayTime =
                    localDateTime.withYear(year).withMonth(12).with(TemporalAdjusters.lastDayOfMonth()).toEpochSecond(
                        zoneOffset);
            } else {
                // 上月最后一天
                yesterdayTime =
                    localDateTime.withMonth(month - 1).with(TemporalAdjusters.lastDayOfMonth()).toEpochSecond(
                        zoneOffset);
            }
        } else {
            int dayOfMonth = localDateTime.getDayOfMonth();
            yesterdayTime = localDateTime.withDayOfMonth(dayOfMonth - 1).toEpochSecond(zoneOffset);
        }
        return yesterdayTime;
    }

    /**
     * 获取上月同日的时间戳,无上月同日返回0
     *
     * @param dayTime 时间戳,秒
     * @return 所传日期的上月同日时间戳 秒
     */
    public static Long getLastMonthSameDayTime(long dayTime) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(dayTime, 0, zoneOffset);
        int monthDay = localDateTime.getDayOfMonth();
        long lastMonthSameDayTime;
        int month = localDateTime.getMonthValue();
        int lastMonthDay;
        if (month == 1) {
            // 跨年 上一年最后一月
            int year = localDateTime.getYear() - 1;
            localDateTime = localDateTime.withYear(year).withMonth(12);
            lastMonthDay = localDateTime.getDayOfMonth();
        } else {
            localDateTime = localDateTime.withMonth(month - 1);
            lastMonthDay = localDateTime.getDayOfMonth();
        }
        if (lastMonthDay < monthDay) {
            lastMonthSameDayTime = 0;
        } else {
            lastMonthSameDayTime = localDateTime.withDayOfMonth(lastMonthDay).toEpochSecond(zoneOffset);
        }
        return lastMonthSameDayTime;
    }

    public static List<Long> getYearEveryMonthTime(int year) {
        LocalDateTime localDateTime =
            LocalDateTime.now().withYear(year).withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);

        List<Long> dateList = new ArrayList<>();
        dateList.add(localDateTime.toEpochSecond(zoneOffset));
        for (int index = 2; index <= 12; index++) {
            dateList.add(localDateTime.withMonth(index).toEpochSecond(zoneOffset));
        }
        return dateList;
    }
}
