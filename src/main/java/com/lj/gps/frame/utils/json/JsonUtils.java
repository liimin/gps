/**
 * 
 */
package com.lj.gps.frame.utils.json;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.*;

/**
 * @data 2012-7-10 下午04:52:21
 * @
 */
public class JsonUtils {

	public static final ObjectMapper MAPPER = new ObjectMapper();

    public static String toJsonString(Object obj) {
        String retVal = "";
        try {
            retVal = MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }
    
    public static <T> T toJavaBean(String json, Class<T> valueType) {
        T retObj = null;
        try {
            retObj = MAPPER.readValue(json,  valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retObj;
    }
    
    public static JsonNode toJsonNode(String json) {
        JsonNode jn = null;
        try {
            jn = MAPPER.readTree(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jn;
    }
    
    /**
     * 过滤json不需要使用属性,解决hibernate延迟加载问题
     * @param obj 对象
     * @param excludes 过滤的属性名数组
     * @return
     */
    public static JSONArray jsonPropertyFilter(Object obj, String[] excludes){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        JSONArray jsonArray = JSONArray.fromObject(obj,jsonConfig);
        return jsonArray;
    }
    /**
     * 获取JSON返加的数据值
     * @param jsonString JSON字符窜
     * @param property 属性名称
     * @return
     */
    public static Object getProperty(String jsonString, String property)
    {
        JSONObject jsonObject = null;
        try
        {
            jsonObject = JSONObject.fromObject(jsonString);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return jsonObject.get(property);
    }
    
	public static String parseResponseJosn(String jsonString,
                                           int getCarFactoryErrorCode) {
		if (StringUtils.isEmpty(jsonString))
			return null;
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		String retCode = jsonObject.getString("retCode");
		if ("0".equals(retCode)) {
			String retVal = jsonObject.getString("retVal");
			return retVal;
		}
		return null;
	}

	public static String parseResponseJosn(String jsonString) {
		if (StringUtils.isEmpty(jsonString))
			return null;
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		String retCode = jsonObject.getString("retCode");
		if ("0".equals(retCode)) {
			String retVal = jsonObject.getString("retVal");
			return retVal;
		}
		return null;
	}

	private static void setDataFormat2JAVA() {
		// 设定日期转换格式
		JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpher(new String[] { "yyyy-MM-dd",
						"yyyy-MM-dd HH:mm:ss" }));
	}



	/**
	 * 从一个JSON 对象字符格式中得到一个java对象，形如： {"id" : idValue, "name" : nameValue,
	 * "aBean" : {"aBeanId" : aBeanIdValue, ...}}
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object getDTO(String jsonString, Class clazz) {
		JSONObject jsonObject = null;
		try {
			setDataFormat2JAVA();
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toBean(jsonObject, clazz);
	}

	/**
	 * 从一个JSON 对象字符格式中得到一个java对象，其中beansList是一类的集合，形如： {"id" : idValue, "name" :
	 * nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...}, beansList:[{}, {},
	 * ...]}
	 * 
	 * @param jsonString
	 * @param clazz
	 * @param map
	 *            集合属性的类型 (key : 集合属性名, value : 集合属性类型class) eg: ("beansList" :
	 *            Bean.class)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object getDTO(String jsonString, Class clazz, Map map) {
		JSONObject jsonObject = null;
		try {
			setDataFormat2JAVA();
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toBean(jsonObject, clazz, map);
	}

	/**
	 * 从一个JSON数组得到一个java对象数组，形如： [{"id" : idValue, "name" : nameValue}, {"id" :
	 * idValue, "name" : nameValue}, ...]
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object[] getDTOArray(String jsonString, Class clazz) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		Object[] obj = new Object[array.size()];
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			obj[i] = JSONObject.toBean(jsonObject, clazz);
		}
		return obj;
	}

	/**
	 * 从一个JSON数组得到一个java对象数组，形如： [{"id" : idValue, "name" : nameValue}, {"id" :
	 * idValue, "name" : nameValue}, ...]
	 * 
	 * @param object
	 * @param clazz
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object[] getDTOArray(String jsonString, Class clazz, Map map) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		Object[] obj = new Object[array.size()];
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			obj[i] = JSONObject.toBean(jsonObject, clazz, map);
		}
		return obj;
	}

	/**
	 * 从一个JSON数组得到一个java对象集合
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List getDTOList(String jsonString, Class clazz) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		List list = new ArrayList();
		for (Iterator iter = array.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, clazz));
		}
		return list;
	}

	/**
	 * 从一个JSON数组得到一个java对象集合，其中对象中包含有集合属性
	 * 
	 * @param object
	 * @param clazz
	 * @param map
	 *            集合属性的类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List getDTOList(String jsonString, Class clazz, Map map) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		List list = new ArrayList();
		for (Iterator iter = array.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, clazz, map));
		}
		return list;
	}

	/**
	 * 从json HASH表达式中获取一个map，该map支持嵌套功能 形如：{"id" : "johncon", "name" : "小强"}
	 * 注意commons
	 * -collections版本，必须包含org.apache.commons.collections.map.MultiKeyMap
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map getMapFromJson(String jsonString) {
		setDataFormat2JAVA();
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Map map = new HashMap();
		for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			map.put(key, jsonObject.get(key));
		}
		return map;
	}

	/**
	 * 从json数组中得到相应java数组 json形如：["123", "456"]
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Object[] getObjectArrayFromJson(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return jsonArray.toArray();
	}

	public static String toJson(Object v) {
		return toJson(v, "");
	}

	/**
	 * 将java对象转成JSON字符串，形如： [{"id" : idValue, "name" : nameValue}, {"id" :
	 * idValue, "name" : nameValue}, ...]
	 * 
	 * @param v
	 *            java对象
	 * @param datePattern
	 *            默认格式为："yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String toJson(Object v, String datePattern) {
		JsonConfig jsonConfig = new JsonConfig();
		if (!"".equals(datePattern) && null != datePattern) {
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor(datePattern));
		} else {
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
		}
		if ((v instanceof Object[]) || (v instanceof int[])
				|| (v instanceof short[]) || (v instanceof boolean[])
				|| (v instanceof long[]) || (v instanceof float[])
				|| (v instanceof Collection) || (v instanceof Vector)) {
			JSONArray jsonArray = JSONArray.fromObject(v, jsonConfig);
			return jsonArray.toString();
		}
		JSONObject json = JSONObject.fromObject(v, jsonConfig);
		return json.toString();
	}
	
	/**
	  * @Title: getValueInArrayFromJSONString
	  * @Description: 从一个JSON字符串获取指定数组的指定的值
	  * 如(json字符串):
	  * 	{"retVal":0,
			"newCardInfos":[["新的用户id","sn","验证码"],["2222","tttt","ggggggggg"]]}
	  		其中:arrayName为	newCardInfos
	  * 
	  * @return String    返回类型
	  * @throws
	 */
	public static String getValueInArrayFromJSONString(String jsonString, String arrayName, int arrayIndex, int fieldIndex){
		JSONObject jsonObject= JSONObject.fromObject(jsonString);
		String value="";
		if(jsonObject!=null){
			JSONArray jsonArray=jsonObject.getJSONArray(arrayName);
			value=getValueInArrayFromJSONArray(jsonArray,arrayIndex,fieldIndex);
		}
		return value;
	}
	
	/**
	 * 
	  * @Title: getValueInArrayFromJSONArray
	  * @Description: 从一个JSON数组获取指定数组的指定的值
	  * * 如(json字符串):
	  * 	{"retVal":0,
			"newCardInfos":[["新的用户id","sn","验证码"],["2222","tttt","ggggggggg"]]}
	  		其中:arrayName为	newCardInfos
	  * 
	  * @return String    返回类型
	  * @throws
	 */
	public static String getValueInArrayFromJSONArray(JSONArray jsonArray,
                                                      int arrayIndex, int fieldIndex) {
		String value = "";
		if (jsonArray != null && jsonArray.size() >= arrayIndex + 1) {
			JSONArray jArray = jsonArray.getJSONArray(arrayIndex);
			if (jArray != null && jArray.size() >= fieldIndex + 1) {
				value = jArray.getString(fieldIndex);
			}
		}
		return value;
	}
	
	public static JSONArray getJsonArrayFromJsonStrng(String jsonString, String arrayName){
		JSONArray jsonArray=null;
		JSONObject jsonObject= JSONObject.fromObject(jsonString);
		if(jsonObject!=null){
			jsonArray=jsonObject.getJSONArray(arrayName);
		}
		return jsonArray;
	}
	
	private static String createWarnType(int median, String warnType) {
		StringBuilder str = new StringBuilder();
		if (StringUtils.isEmpty(warnType)) {
			for (int i = 0; i < median - 1; i++) {
				str.append("0");
			}
			str.append("1");
			for (int i = median ; i < 10; i++) {
				str.append("0");
			}
		} else {
			String warnTypeTop = warnType.substring(0, median - 1);
			str.append(warnTypeTop);
			str.append("1");
			if (median <= 9) {
				str.append(warnType.substring(median, 10));
			}
		}
		return str.toString();
	}
	public static String getJsonString4JavaPOJO(Object javaObj) {

		JSONObject json;
		json = JSONObject.fromObject(javaObj);
		return json.toString();
	}

//	public static void main(String[] args) {
//		System.out.println(createWarnType(10, "0000000100"));
//	}
}
