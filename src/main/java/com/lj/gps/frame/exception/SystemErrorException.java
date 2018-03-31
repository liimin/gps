package com.lj.gps.frame.exception;

/**
 * 
 * 时间:2017-7-17下午2:50:48<br/>
 * 功能:系统级别异常 <br/>
 */
public class SystemErrorException extends Exception {
	public SystemErrorException(String message){
		super(message);
	}
	public SystemErrorException(String message,Throwable cause){
		super(message,cause);
	}
}
