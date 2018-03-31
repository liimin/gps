/**
 * @Title: PatternUtil.java
 * @Package com.glsx.operating.cyb.util
 * @Description: TODO
 * @author: zhang xt
 * @date 2013-9-26 下午4:42:45
 * Copyright: Copyright (c) 2013
 * Company: GLSX.
 * @version V1.0
 */

package com.lj.gps.frame.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 正则表达式匹配工具
 * @author: zhang xt
 * @date 2017-9-26 下午4:42:45
 * Copyright: Copyright (c) 2013 
 * Company: GLSX.
 * @version V1.0
 */
public class PatternUtils {
    /**
     *
     * @Title: matches
     * @Description:
     * @author: zhang xt
     * @param @param regex
     * @param @param data
     * @param @return
     * @return boolean
     * @throws
     */
    public static boolean matches(String regex, String data) {
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(data).matches();
    }

    public static boolean isInteger(String data) {
        String regex = "^[1-9][0-9]{0,7}$";

        return matches(regex, data);
    }

    public static boolean isDecimal(String data) {
        String regex = "[0-9]+(.[0-9]+)?";

        return matches(regex, data);
    }

    public static boolean isInitial(String data) {
        String regex = "[a-zA-Z]";

        return matches(regex, data);
    }

    public static boolean isDisplacement(String data) {
        String regex = "([0-9].[0-9])([Ll]|[Tt])";

        return matches(regex, data);
    }

    public static boolean isValidCommaRoule(String data) {
        String regex = "^(\\s*\\d{1,8}\\s*)(\\,\\s*\\d{1,8}\\s*)*$";

        return matches(regex, data);
    }

    public static boolean isInValidChar(String data) {
        String regex = "\\S*[\\<\\>\\/\\?\\[\\^\\]\\\\]\\S*";

        return matches(regex, data);
    }

    public static boolean isSpace(String data) {
        String regex = "^\\s+$";

        return matches(regex, data);
    }

    public static boolean isZeroAndOne(String data) {
        String regex = "(0|1)";

        return matches(regex, data);
    }

    public static boolean isMobile(String data) {
        String regex = "^(13|15|18)[0-9]{9}$";

        return matches(regex, data);
    }

    public static boolean isValidWord(String data) {
        String regex = "^([^<>;]{1,10})$";

        return matches(regex, data);
    }

    public static boolean isWordLevel(String data) {
        String regex = "^(高|中|低)$";

        return matches(regex, data);
    }

    public static boolean isAdapted(String data) {
        String regex = "^(适配|不适配|待定)$";

        return matches(regex, data);
    }

    public static boolean isMatched(Set<String> patterns, String data) {
        if (patterns == null || patterns.isEmpty()) {
            return true;
        }

        Iterator<String> it = patterns.iterator();
        String pattern = null;

        while (it.hasNext()) {
            pattern = it.next();

            //System.out.println("pattern:" + pattern + ":" + matches(pattern,data));

            if (matches(pattern, data)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isDateYmd(String data) {
        String regex = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))$";
        return matches(regex, data);
    }

    public static boolean isDateYm(String data) {
        String regex = "^[1-9]+\\d{3}\\-(?:0[1-9]|1[0-2]|[1-9]{1})";
        return matches(regex, data);
    }

    /**
     * 检查整数  
     * @param num
     * @param type "0+":非负整数 "+":正整数 "-0":非正整数 "-":负整数 "":整数  
     * @return
     */
    public static boolean checkNumber(String num, String type) {
        String eL = "";
        if (type.equals("0+")) eL = "^\\d+$";//非负整数
        else if (type.equals("+")) eL = "^\\d*[1-9]\\d*$";//正整数
        else if (type.equals("-0")) eL = "^((-\\d+)|(0+))$";//非正整数
        else if (type.equals("-")) eL = "^-\\d*[1-9]\\d*$";//负整数
        else eL = "^-?\\d+$";//整数   
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(num);
        boolean b = m.matches();
        return b;
    }

    public static void testApi() {
        Set<String> apis = new HashSet<String>();

        apis.add("/api/.*");
        apis.add("/merchant/regMerchant.do");
        apis.add("/merchant/updateMerchantPwd.do");
        apis.add("/merchant/updateMerchant.do");
        apis.add("/merchant/logout.do");
        apis.add("/merchant/login.do");

        String data = "/merchant/regMerchant.do";

        System.out.println("api:" + data + ":" + isMatched(apis, data));

    }

    public static final void main(String[] args) {
		/*String role = "1.2T";
		
		System.out.println(isDisplacement(role));
		
		role = "测试sdfsdf/";
		System.out.println(isInValidChar(role));
		
		role = "    d";
		System.out.println(isSpace(role));
		
		role = "3";
		System.out.println(isZeroAndOne(role));
		
		role = "E";
		System.out.println(isInitial(role));
		
		Pattern p = Pattern.compile("([0-9].[0-9])([Ll]|[Tt])");   
		Matcher m = p.matcher("1.2T");  
		
		if(m.find())
		{
			System.out.println(m.group(1));
			System.out.println(m.group(2));
		}
		
		role = "-212";
		System.out.println(isDecimal(role));*/


        testApi();

//		System.out.println(isValidCommaRoule(role));
//		
//		String [] slices  = role.split("\\s*,\\s*");
//		
//		for(String slice:slices)
//		{
//			System.out.println(slice);
//		}


    }

}
