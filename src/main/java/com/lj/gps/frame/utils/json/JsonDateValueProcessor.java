/*
 * @fileName：JsonDateValueProcessor.java    2011-8-18 下午02:19:55
 *
 * Copyright (c) 2011 MSD Technologies, Inc. All rights reserved.
 * <P>Title：<P>
 * <P>Description：<P>
 * <P>Copyright: Copyright (c) 2011 <P>
 * <P>Company: MSD <P>
 * @
 * @version 1.0
 *
 */
package com.lj.gps.frame.utils.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * <P>
 * Description：
 * </P>
 * 
 * @
 * @version 1.0
 */
public class JsonDateValueProcessor implements JsonValueProcessor {

    /**
     * datePattern
     */
    private String datePattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * JsonDateValueProcessor
     */
    public JsonDateValueProcessor() {
	super();
    }

    /**
     * @param format
     */
    public JsonDateValueProcessor(String format) {
	super();
	this.datePattern = format;
    }

    /**
     * @param value
     * @param jsonConfig
     * @return Object
     */
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
	return process(value);
    }

    /**
     * @param key
     * @param value
     * @param jsonConfig
     * @return Object
     */
    public Object processObjectValue(String key, Object value,
                                     JsonConfig jsonConfig) {
	return process(value);
    }

    /**
     * process
     * 
     * @param value
     * @return
     */
    private Object process(Object value) {
	try {
	    if (value instanceof Date) {
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern,
			Locale.UK);
		return sdf.format((Date) value);
	    }
	    return value == null ? "" : value.toString();
	} catch (Exception e) {
	    return "";
	}

    }

    /**
     * @return the datePattern
     */
    public String getDatePattern() {
	return datePattern;
    }

    /**
     * @param pDatePattern
     *                the datePattern to set
     */
    public void setDatePattern(String pDatePattern) {
	datePattern = pDatePattern;
    }

}
