/**
 *
 */
package com.lj.gps.frame.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @data 2012-7-6 上午10:50:01
 * @
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

    /**
     * 工单验证码，工单id如果未达8位在前面补零，达到8位返回
     *
     * @return
     */
    public static String getWorkOrderIdStr(String workOrderId) {
        if (workOrderId.length() == 8) {
            return workOrderId;
        } else {
            int strNum = 8 - workOrderId.length();
            for (int i = 0; i < strNum; i++) {
                workOrderId = "0" + workOrderId;
            }
            return workOrderId;
        }
    }

    /**
     * 取两字符数组的合集
     *
     * @param originalList
     * @param newList
     * @return
     */
    public static String[] union(String[] originalList, String[] newList) {
        Set<String> set = new HashSet<String>();
        for (String str : originalList) {
            set.add(str);
        }
        for (String str : newList) {
            set.add(str);
        }
        String[] result = {};
        return set.toArray(result);
    }

    /**
     * 替换指定分隔符的字符串,注使用正则特殊分隔需转义
     * <pre>
     * StringUtils.replaceCharSplitStr("1,2,22,3,4", "2", ",")  = 1,22,3,4
     * StringUtils.replaceCharSplitStr("1#2#22#3#4", "2", "#")  = 1#22#3#4
     * </pre>
     *
     * @param oldStr        原字符串
     * @param replaceStr    替换字符串
     * @param separatorChar 分隔字符
     * @return
     */
    public static String replaceCharSplitStr(String oldStr, String replaceStr, String separatorChar) {
        if (isEmpty(oldStr) || isEmpty(replaceStr)) {
            return oldStr;
        }
        oldStr = separatorChar + oldStr + separatorChar;
        oldStr = oldStr.replaceAll(separatorChar + replaceStr + separatorChar, separatorChar);
        if (isNotEmpty(oldStr) && oldStr.length() > 1) {
            return oldStr.substring(1, oldStr.length() - 1);
        }
        return "";
    }

    public static boolean isNullOrEmpty(String str) {
        if (null == str || "".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字符串补0
     *
     * @param message
     * @param size
     * @return
     */
    public static String zeroFill(String message, int size) {
        int length = message.length();
        if (length >= size) {
            return message;
        } else {
            String zeroStr = "";
            int differLength = size - length;
            for (int i = 1; i <= differLength; i++) {
                zeroStr += "0";
            }
            return zeroStr + message;
        }
    }

    /**
     * 合并两个字符串，去重
     *
     * @param str1
     * @param str2
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static String mergeStr(String str1, String str2) {
        String str1_Array[] = str1.split(",");
        String str2_Array[] = str2.split(",");
        //Set是不允许重复的，所以将数组的值全部放在Set对象中
        Set set = new HashSet<String>();
        for (int i = 0; i < str1_Array.length; i++) {
            set.add(str1_Array[i]);
        }
        for (int i = 0; i < str2_Array.length; i++) {
            set.add(str2_Array[i]);
        }
        Iterator i = set.iterator();
        int[] arrays = new int[set.size()];
        int num = 0;
        while (i.hasNext()) {
            int a = Integer.parseInt(i.next().toString().trim());
            arrays[num] = a;
            num = num + 1;
        }
        //对结果进行排序
        Arrays.sort(arrays);
        return Arrays.toString(arrays).replace("[", "").replace("]", "");
    }

    /**
     * 创建指定数量的随机字符串
     *
     * @param numberFlag 是否是数字
     * @param length
     * @return
     */
    public static String generateRandomCode(boolean numberFlag, int length) {
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);
        return retStr;
    }

//    public static void main(String[] args) {
//        System.out.println("aaaaaaaaaaaaa:"+ StringUtils.replaceCharSplitStr("1,2", "2", ","));
//        System.out.println("aaaaaaaaaaaaa:"+ StringUtils.replaceCharSplitStr("2", "2", ","));
//    }
}
