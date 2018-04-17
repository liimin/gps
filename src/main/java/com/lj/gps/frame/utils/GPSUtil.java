package com.lj.gps.frame.utils;

import com.amap.api.maps2d.CoordinateConverter;
import com.amap.api.maps2d.model.LatLng;
import com.lj.gps.frame.exception.BusinessException;
import com.lj.gps.frame.exception.GlobalExceptionHandler;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的互转
 * Created by macremote on 16/5/3.
 * 1.WGS－84原始坐标系，一般用国际GPS纪录仪记录下来的经纬度，通过GPS定位拿到的原始经纬度，Google和高德地图定位的的经纬度（国外）都是基于WGS－84坐标系的；但是在国内是不允许直接用WGS84坐标系标注的，必须经过加密后才能使用；
 * 2.GCJ－02坐标系，又名“火星坐标系”，是我国国测局独创的坐标体系，由WGS－84加密而成，在国内，必须至少使用GCJ－02坐标系，或者使用在GCJ－02加密后再进行加密的坐标系，如百度坐标系。高德和Google在国内都是使用GCJ－02坐标系，可以说，GCJ－02是国内最广泛使用的坐标系；
 * 3.百度坐标系:bd-09，百度坐标系是在GCJ－02坐标系的基础上再次加密偏移后形成的坐标系，只适用于百度地图。(目前百度API提供了从其它坐标系转换为百度坐标系的API，但却没有从百度坐标系转为其他坐标系的API)
 */
public class GPSUtil {
    final static double pi = 3.14159265358979324;
    final static double a = 6378245.0;
    final static double ee = 0.00669342162296594323;
    private static final double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

    /**
     * 纠偏
     * gps-->google
     * @param lat
     * @param lng
     * @return
     */
    public static double[] gpsToGoogle(double lat ,double lng){
        double[] latlng = new double[2];
        if (outOfChina(lat, lng)) {
            latlng[0] = lat;
            latlng[1] = lng;
            return latlng;
        }
        double dLat = transformLat(lng - 105.0, lat - 35.0);
        double dLon = transformLon(lng - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        latlng[0] = lat + dLat;
        latlng[1] = lng + dLon;
        return latlng;
    }

    /**
     *  纠偏
     *  gps-->百度
     *  1.先 gps-->google
     *  2.google-->百度
     * @param lat
     * @param lng
     * @return
     */
    public static double[] gpsToBaidu(double lat ,double lng){
        double[] latlng = new double[2];
        double[] googleGps = gpsToGoogle(lat,lng);
        latlng = bd_encrypt(googleGps[0],googleGps[1]);
        return latlng;
    }


    /**
     * google 转 百度
     * @param gg_lat
     * @param gg_lon
     * @return
     */
    public static double[] bd_encrypt(double gg_lat, double gg_lon){
        double x = gg_lon, y = gg_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        double bd_lon = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        double[] tmp = new double[2];
        tmp[0] = bd_lat;
        tmp[1] = bd_lon;
        return tmp;
    }

    public static void transform(double wgLat, double wgLon, double[] latlng) {
        if (outOfChina(wgLat, wgLon)) {
            latlng[0] = wgLat;
            latlng[1] = wgLon;
            return;
        }
        double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
        double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
        double radLat = wgLat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        latlng[0] = wgLat + dLat;
        latlng[1] = wgLon + dLon;
    }

    private static boolean outOfChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347)
            return true;
        if (lat < 0.8293 || lat > 55.8271)
            return true;
        return false;
    }

    private static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    private static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0;
        return ret;
    }

}
