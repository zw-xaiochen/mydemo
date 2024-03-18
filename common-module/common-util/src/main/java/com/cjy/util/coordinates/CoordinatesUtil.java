package com.cjy.util.coordinates;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * 坐标系工具类
 */
public class CoordinatesUtil {

    private static final double PI = 3.1415926535897932384626;

    // 地球半径
    public static double EARTH_RADIUS = 6378245.0;

    // 椭圆偏心率
    public static double ECCENTRICITY_OF_ELLIPSOID = 0.00669342162296594323;

    private static final double CHAIN_MIN_LONGITUDE = 73.33;
    private static final double CHAIN_MAX_LONGITUDE = 135.05;
    private static final double CHAIN_MIN_LATITUDE = 3.51;
    private static final double CHAIN_MAX_LATITUDE = 53.33;

    /**
     * BD09II转火星坐标系
     */
    public static Coordinates bd092ToGCJ02(Double baiduLon, Double baiduLat) {
        double xpi = PI * 3000.0 / 180.0;
        //百度坐标转成火星坐标
        double x = baiduLon - 0.0065;
        double y = baiduLat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * xpi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * xpi);
        double jgcLat = z * Math.sin(theta);
        double jgcLon = z * Math.cos(theta);
        if (gcj02OutOfChina(jgcLon, jgcLat)) {
            return null;
        }
        return new Coordinates(getStandardValue(jgcLon), getStandardValue(jgcLat));
    }

    /**
     * BD09转火星坐标系
     */
    public static Coordinates bd09ToGCJ02(Double baiduLon, Double baiduLat) {
        double x = baiduLon - 0.0065;
        double y = baiduLat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * PI);
        double jgcLon = z * Math.cos(theta);
        double jgcLat = z * Math.sin(theta);
        if (gcj02OutOfChina(jgcLon, jgcLat)) {
            return null;
        }
        return new Coordinates(getStandardValue(jgcLon), getStandardValue(jgcLat));
    }

    /**
     * WGS84坐标系转换为火星坐标系
     */
    public static Coordinates wgs84ToGcj02(double lon, double lat) {
        if (wgs84OutOfChina(lat, lon)) {
            return null;
        }
        double dhLat = transformLat(lon - 105.0, lat - 35.0);
        double dhLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * PI;
        double magic = Math.sin(radLat);
        magic = 1 - ECCENTRICITY_OF_ELLIPSOID * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dhLat = (dhLat * 180.0) / ((EARTH_RADIUS * (1 - ECCENTRICITY_OF_ELLIPSOID) / (magic * sqrtMagic) * PI));
        dhLon = (dhLon * 180.0) / ((EARTH_RADIUS / sqrtMagic) * Math.cos(radLat) * PI);
        double jgcLat = lat + dhLat;
        double jgcLon = lon + dhLon;
        return new Coordinates(getHalfStandardValue(jgcLon), getHalfStandardValue(jgcLat));
    }

    public static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * PI) + 20.0 * Math.sin(2.0 * x * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * PI) + 40.0 * Math.sin(y / 3.0 * PI)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * PI) + 320 * Math.sin(y * PI / 30.0)) * 2.0 / 3.0;
        return ret;
    }


    public static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * PI) + 20.0 * Math.sin(2.0 * x * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * PI) + 40.0 * Math.sin(x / 3.0 * PI)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * PI) + 300.0 * Math.sin(x / 30.0 * PI)) * 2.0 / 3.0;
        return ret;
    }

    /**
     * 判断是否是中国境内的经纬度
     * @return true:不在中国境内
     */
    public static boolean gcj02OutOfChina(double lon, double lat) {
        return lon < CHAIN_MIN_LONGITUDE || lon > CHAIN_MAX_LONGITUDE
                || lat < CHAIN_MIN_LATITUDE || lat > CHAIN_MAX_LATITUDE;
    }


    /**
     * 判断是否是中国境内的经纬度
     * @return true:不在中国境内
     */
    public static boolean wgs84OutOfChina(double lat, double lon) {
        return lon < 72.004 || lon > 137.8347 || lat < 0.8293 || lat > 55.8271;
    }

    /**
     * 转换标准的经纬度值（保留小数后6位）
     */
    public static double getStandardValue(double value) {
        return new BigDecimal(String.valueOf(value)).setScale(6, RoundingMode.DOWN).doubleValue();
    }

    /**
     * 转换标准的经纬度值（四舍五入后保留小数后6位）
     */
    public static double getHalfStandardValue(double value) {
        return new BigDecimal(String.valueOf(value)).setScale(6, RoundingMode.HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        /*System.out.println(bd09ToGCJ02(106.521399, 29.515751));
        System.out.println(bd092ToGCJ02(33.4095079222662, 115.083394394713));*/
        System.out.println(wgs84ToGcj02(116.098098, 39.860249));
        System.out.println(Arrays.toString(GpsDataTranslate.transform(39.860249d, 116.098098d)));
    }
}
