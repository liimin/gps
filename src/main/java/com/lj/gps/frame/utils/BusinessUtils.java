package com.lj.gps.frame.utils;

import com.lj.gps.frame.exception.BusinessException;
import com.lj.gps.frame.exception.GlobalExceptionHandler;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * <简述>业务工具类
 *
 * @author liuyf
 * @version V1.0
 * @date 2016/3/8
 */
public class BusinessUtils {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 将电压值转为剩余电量百分比
     *
     * @param voltage
     * @return
     */
    public static String getPercentVoltage(double voltage) {
        String percent = "";
        try {
            if (voltage < 3.4) {
                percent = "0";
            } else if (voltage > 4.1) {
                percent = "100";
            } else {
                BigDecimal b = new BigDecimal((voltage - 3.4) / 0.7 * 100);
                percent = b.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
            }
        } catch (Exception e) {
        }
        return percent;
    }

    /**
     * 将剩余电量百分比转为电压值
     *
     * @param voltage
     * @return
     */
    public static String getVoltage(double voltage) {
        String percent = "";
        try {
            if (voltage >= 0 && voltage <= 100) {
                BigDecimal b = new BigDecimal(voltage / 100 * 0.7 + 3.4);
                percent = b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            } else {
                logger.info("Voltage out of range!");
            }
        } catch (Exception e) {
        }
        return percent;
    }


    /**
     * 计算num1相比num2的环比增长率
     * 保留两位小数以%结尾
     * @param num1
     * @param num2
     * @return
     */
    public static String growthrate(int num1, int  num2){
        if(num2 > 0) {
            Double d1 = new Double(num1);
            Double d2 = new Double(num2);
            double percent = (d1-d2)/d2;
            NumberFormat nt = NumberFormat.getPercentInstance();
            nt.setMinimumFractionDigits(2);
            return nt.format(percent);
        }
        return "----";
    }

    /**
     *
     * 作者:sanri <br/>
     * 时间:2017-9-9下午12:04:55<br/>
     * 功能:断言身份证号码是正确的 <br/>
     *
     * @param idcard
     * @return
     * @throws BussinessException
     */
    public static void assertIdCardTrue(String idcard) throws BusinessException {
        if (StringUtils.isBlank(idcard)) {
            throw new BusinessException(500+"","身份证不能为空");
        }
        if (idcard.length() != 15 && idcard.length() != 18) {
            throw new BusinessException(500+"","大陆身份证必须是 15 位或 18 位");
        }
        // 对于 18 位身份证,校验最后一位
        if (idcard.length() == 18) {
            //加权因子列表
            int [] power = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
            //校验码列表
            char [] checkCodes = {'1','0','x','9','8','7','6','5','4','3','2'};
            // 获取前17位
            String idcard17 = idcard.substring(0, 17);
            // 前17位全部为数字
            if (!idcard17.matches("^[0-9]*$")) {
                throw new BusinessException(500+"","身份证前 17 位必须全为数字");
            }
            char chars[] = idcard17.toCharArray();
            int charInts[] = new int[chars.length];
            for (int i = 0; i < charInts.length; i++) {
                charInts[i] = NumberUtils.toInt(String.valueOf(chars[i]));
            }
            // 计算加权和
            int sum = 0;
            for (int i = 0; i < charInts.length; i++) {
                sum = sum + charInts[i] * power[i];
            }
            //获取检验码,和身份证第 18 位
            char idcard18 = idcard.charAt(17);
            char checkCode = checkCodes[sum % 11];
            if(idcard18 != checkCode && Character.toLowerCase(idcard18) != checkCode){
                throw new BusinessException(500+"","身份证验证码错误");
            }
        }
    }

    public static void main(String[] args) {
//        double voltage = 100;
//        System.out.println(getVoltage(voltage));
//
//        String sn = "7160033695";
//        boolean flag = false;
//        int year = Integer.valueOf(sn.substring(1, 3));
//        int month = Integer.valueOf(sn.substring(3, 5));
//        if (year == 15) {
//            if (month == 11 || month == 12) {
//                flag = true;
//            }
//        } else if (year > 15) {
//            if (month >= 1 && month <= 12) {
//                flag = true;
//            }
//        }
        System.out.println(growthrate(5,0));
    }
}
