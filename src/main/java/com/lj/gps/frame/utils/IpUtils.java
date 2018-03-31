package com.lj.gps.frame.utils;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class IpUtils {



    /**
     * 获取请求IP
     *
     * @return
     */
    public static String getRemortIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * IP鉴权
     *
     * @param requestIp
     * @param authIPsKey
     * @return
     */
    public static boolean authIp(String requestIp, String authIPsKey) {
        String b1 = requestIp.substring(0, requestIp.lastIndexOf("."));
        int b2 = Integer.parseInt(requestIp.substring(requestIp.lastIndexOf(".") + 1));
        List list = new ArrayList();
        String ghIpStr = "";//PropertyConfigurer.getString(authIPsKey);
        if (null != ghIpStr) {
            String[] data = ghIpStr.split(",");
            if (null != data && data.length > 0) {
                for (int i = 0; i < data.length; i++) {
                    String ip = data[i];
                    list.add(ip);
                }
            }
        }

        if (null != list && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i).toString();
                String[] ss = str.split("-");
                String s1 = ss[0].substring(0, ss[0].lastIndexOf("."));
                int s2 = Integer.parseInt(ss[0].substring(ss[0].lastIndexOf(".") + 1));
                if (s1.equals(b1)) {
                    if (null != ss[0] && ss.length == 1) {
                        if (b2 == s2) {
                            return true;
                        }
                    } else {
                        int e2 = Integer.parseInt(ss[1]);
                        if (b1.equals(s1)) {
                            if (b2 >= s2 && b2 <= e2) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
