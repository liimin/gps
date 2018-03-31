/**
 * Copyright (c) 2005-2011 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: Fixtures.java 1593 2011-05-11 10:37:12Z calvinxiu $
 */
package com.lj.gps.frame.utils.idutil;

import com.lj.gps.frame.exception.BusinessException;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 与Spring Assert功能类似, 代码基本从org.springframework.util.Assert复制, 增加如下功能:
 * 
 * 1. 修改类名, 免得一天到晚和org.junit.Assert冲突.
 * 2. 可抛出指定的业务异常类, 而不是通用的IllegalArgumentException.
 * 
 * 代码示例:
 * <pre class="code">AssertUtils.hasText(uName, new IllegalBizArgumentsException(ErrorCode.USERNAME_ERROR));</pre>
 *   
 * @author badqiu
 * @author calvin
 */
@SuppressWarnings("unchecked")
public abstract class AssertUtils {

	/**
	 * Assert a boolean expression, throwing <code>IllegalArgumentException</code>
	 * if the test result is <code>false</code>.
	 * <pre class="code">Assert.isTrue(i &gt; 0);</pre>
	 * @param expression a boolean expression
	 * @throws IllegalArgumentException if expression is <code>false</code>
	 */
	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - this expression must be true");
	}

	/**
	 * Assert a boolean expression, throwing <code>IllegalArgumentException</code>
	 * if the test result is <code>false</code>.
	 * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
	 * @param expression a boolean expression
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if expression is <code>false</code>
	 */
	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void isTrue(boolean expression, RuntimeException throwIfAssertFail) {
		if (!expression) {
			throw throwIfAssertFail;
		}
	}

	/**
	 * Assert that an object is <code>null</code> .
	 * <pre class="code">Assert.isNull(value);</pre>
	 * @param object the object to check
	 * @throws IllegalArgumentException if the object is not <code>null</code>
	 */
	public static void isNull(Object object) {
		isNull(object, "[Assertion failed] - the object argument must be null");
	}

	/**
	 * Assert that an object is <code>null</code> .
	 * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
	 * @param object the object to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object is not <code>null</code>
	 */
	public static void isNull(Object object, String message) {
		if (object != null) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void isNull(Object object, RuntimeException throwIfAssertFail) {
		if (object != null) {
			throw throwIfAssertFail;
		}
	}

	/**
	 * Assert that an object is not <code>null</code> .
	 * <pre class="code">Assert.notNull(clazz);</pre>
	 * @param object the object to check
	 * @throws IllegalArgumentException if the object is <code>null</code>
	 */
	public static void notNull(Object object) {
		notNull(object, "[Assertion failed] - this argument is required; it must not be null");
	}

	/**
	 * Assert that an object is not <code>null</code> .
	 * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
	 * @param object the object to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object is <code>null</code>
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notNull(Object object, RuntimeException throwIfAssertFail) {
		if (object == null) {
			throw throwIfAssertFail;
		}
	}

	/**
	 * Assert that the given String is not empty; that is,
	 * it must not be <code>null</code> and not the empty String.
	 * <pre class="code">Assert.hasLength(name);</pre>
	 * @param text the String to check
	 * @see StringUtils#hasLength
	 */
	public static void hasLength(String text) {
		hasLength(text, "[Assertion failed] - this String argument must have length; it must not be null or empty");
	}

	/**
	 * Assert that the given String is not empty; that is,
	 * it must not be <code>null</code> and not the empty String.
	 * <pre class="code">Assert.hasLength(name, "Name must not be empty");</pre>
	 * @param text the String to check
	 * @param message the exception message to use if the assertion fails
	 * @see StringUtils#hasLength
	 */
	public static void hasLength(String text, String message) {
		if (!StringUtils.hasLength(text)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void hasLength(String text, RuntimeException throwIfAssertFail) {
		if (!StringUtils.hasLength(text)) {
			throw throwIfAssertFail;
		}
	}

	/**
	 * Assert that the given String has valid text content; that is, it must not
	 * be <code>null</code> and must contain at least one non-whitespace character.
	 * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
	 * @param text the String to check
	 * @see StringUtils#hasText
	 */
	public static void hasText(String text) {
		hasText(text, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
	}

	/**
	 * Assert that the given String has valid text content; that is, it must not
	 * be <code>null</code> and must contain at least one non-whitespace character.
	 * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
	 * @param text the String to check
	 * @param message the exception message to use if the assertion fails
	 * @see StringUtils#hasText
	 */
	public static void hasText(String text, String message) {
		if (!StringUtils.hasText(text)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void hasText(String text, RuntimeException throwIfAssertFail) {
		if (!StringUtils.hasText(text)) {
			throw throwIfAssertFail;
		}
	}

	/**
	 * Assert that the given text does not contain the given substring.
	 * <pre class="code">Assert.doesNotContain(name, "rod");</pre>
	 * @param textToSearch the text to search
	 * @param substring the substring to find within the text
	 */
	public static void doesNotContain(String textToSearch, String substring) {
		doesNotContain(textToSearch, substring,
				"[Assertion failed] - this String argument must not contain the substring [" + substring + "]");
	}

	/**
	 * Assert that the given text does not contain the given substring.
	 * <pre class="code">Assert.doesNotContain(name, "rod", "Name must not contain 'rod'");</pre>
	 * @param textToSearch the text to search
	 * @param substring the substring to find within the text
	 * @param message the exception message to use if the assertion fails
	 */
	public static void doesNotContain(String textToSearch, String substring, String message) {
		if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring)
				&& textToSearch.indexOf(substring) != -1) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void doesNotContain(String textToSearch, String substring, RuntimeException throwIfAssertFail) {
		if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring)
				&& textToSearch.indexOf(substring) != -1) {
			throw throwIfAssertFail;
		}
	}

	/**
	 * Assert that an array has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * <pre class="code">Assert.notEmpty(array);</pre>
	 * @param array the array to check
	 * @throws IllegalArgumentException if the object array is <code>null</code> or has no elements
	 */
	public static void notEmpty(Object[] array) {
		notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
	}

	/**
	 * Assert that an array has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * <pre class="code">Assert.notEmpty(array, "The array must have elements");</pre>
	 * @param array the array to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object array is <code>null</code> or has no elements
	 */
	public static void notEmpty(Object[] array, String message) {
		if (ObjectUtils.isEmpty(array)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notEmpty(Object[] array, RuntimeException throwIfAssertFail) {
		if (ObjectUtils.isEmpty(array)) {
			throw throwIfAssertFail;
		}
	}

	/**
	 * Assert that an array has no null elements.
	 * Note: Does not complain if the array is empty!
	 * <pre class="code">Assert.noNullElements(array, "The array must have non-null elements");</pre>
	 * @param array the array to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object array contains a <code>null</code> element
	 */
	public static void noNullElements(Object[] array, String message) {
		if (array != null) {
			for (Object element : array) {
				if (element == null) {
					throw new IllegalArgumentException(message);
				}
			}
		}
	}

	/**
	 * Assert that an array has no null elements.
	 * Note: Does not complain if the array is empty!
	 * <pre class="code">Assert.noNullElements(array);</pre>
	 * @param array the array to check
	 * @throws IllegalArgumentException if the object array contains a <code>null</code> element
	 */
	public static void noNullElements(Object[] array) {
		noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
	}

	public static void noNullElements(Object[] array, RuntimeException throwIfAssertFail) {
		if (array != null) {
			for (Object element : array) {
				if (element == null) {
					throw throwIfAssertFail;
				}
			}
		}
	}

	/**
	 * Assert that a collection has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * <pre class="code">Assert.notEmpty(collection, "Collection must have elements");</pre>
	 * @param collection the collection to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the collection is <code>null</code> or has no elements
	 */
	public static void notEmpty(Collection collection, String message) {
		if (CollectionUtils.isEmpty(collection)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that a collection has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * <pre class="code">Assert.notEmpty(collection, "Collection must have elements");</pre>
	 * @param collection the collection to check
	 * @throws IllegalArgumentException if the collection is <code>null</code> or has no elements
	 */
	public static void notEmpty(Collection collection) {
		notEmpty(collection,
				"[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
	}

	public static void notEmpty(Collection collection, RuntimeException throwIfAssertFail) {
		if (CollectionUtils.isEmpty(collection)) {
			throw throwIfAssertFail;
		}
	}

	/**
	 * Assert that a Map has entries; that is, it must not be <code>null</code>
	 * and must have at least one entry.
	 * <pre class="code">Assert.notEmpty(map, "Map must have entries");</pre>
	 * @param map the map to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the map is <code>null</code> or has no entries
	 */
	public static void notEmpty(Map map, String message) {
		if (CollectionUtils.isEmpty(map)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that a Map has entries; that is, it must not be <code>null</code>
	 * and must have at least one entry.
	 * <pre class="code">Assert.notEmpty(map);</pre>
	 * @param map the map to check
	 * @throws IllegalArgumentException if the map is <code>null</code> or has no entries
	 */
	public static void notEmpty(Map map) {
		notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
	}

	public static void notEmpty(Map map, RuntimeException throwIfAssertFail) {
		if (CollectionUtils.isEmpty(map)) {
			throw throwIfAssertFail;
		}
	}

	/**
	 * Assert that the provided object is an instance of the provided class.
	 * <pre class="code">Assert.instanceOf(Foo.class, foo);</pre>
	 * @param type the type to check against
	 * @param obj the object to check
	 * @param message a message which will be prepended to the message produced by
	 * the function itself, and which may be used to provide context. It should
	 * normally end in a ": " or ". " so that the function generate message looks
	 * ok when prepended to it.
	 * @throws IllegalArgumentException if the object is not an instance of clazz
	 * @see Class#isInstance
	 */
	public static void isInstanceOf(Class type, Object obj, String message) {
		notNull(type, "Type to check against must not be null");
		if (!type.isInstance(obj)) {
			throw new IllegalArgumentException(message + "Object of class ["
					+ (obj != null ? obj.getClass().getName() : "null") + "] must be an instance of " + type);
		}
	}

	/**
	 * Assert that the provided object is an instance of the provided class.
	 * <pre class="code">Assert.instanceOf(Foo.class, foo);</pre>
	 * @param clazz the required class
	 * @param obj the object to check
	 * @throws IllegalArgumentException if the object is not an instance of clazz
	 * @see Class#isInstance
	 */
	public static void isInstanceOf(Class clazz, Object obj) {
		isInstanceOf(clazz, obj, "");
	}

	public static void isInstanceOf(Class type, Object obj, RuntimeException throwIfAssertFail) {
		notNull(type, "Type to check against must not be null");
		if (!type.isInstance(obj)) {
			throw throwIfAssertFail;
		}
	}

	/**
	 * Assert that <code>superType.isAssignableFrom(subType)</code> is <code>true</code>.
	 * <pre class="code">Assert.isAssignable(Number.class, myClass);</pre>
	 * @param superType the super type to check against
	 * @param subType the sub type to check
	 * @param message a message which will be prepended to the message produced by
	 * the function itself, and which may be used to provide context. It should
	 * normally end in a ": " or ". " so that the function generate message looks
	 * ok when prepended to it.
	 * @throws IllegalArgumentException if the classes are not assignable
	 */
	public static void isAssignable(Class superType, Class subType, String message) {
		notNull(superType, "Type to check against must not be null");
		if (subType == null || !superType.isAssignableFrom(subType)) {
			throw new IllegalArgumentException(message + subType + " is not assignable to " + superType);
		}
	}

	/**
	 * Assert that <code>superType.isAssignableFrom(subType)</code> is <code>true</code>.
	 * <pre class="code">Assert.isAssignable(Number.class, myClass);</pre>
	 * @param superType the super type to check
	 * @param subType the sub type to check
	 * @throws IllegalArgumentException if the classes are not assignable
	 */
	public static void isAssignable(Class superType, Class subType) {
		isAssignable(superType, subType, "");
	}

	/**
	 * Assert a boolean expression, throwing <code>IllegalStateException</code>
	 * if the test result is <code>false</code>. Call isTrue if you wish to
	 * throw IllegalArgumentException on an assertion failure.
	 * <pre class="code">Assert.state(id == null, "The id property must not already be initialized");</pre>
	 * @param expression a boolean expression
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalStateException if expression is <code>false</code>
	 */
	public static void state(boolean expression, String message) {
		if (!expression) {
			throw new IllegalStateException(message);
		}
	}

	/**
	 * Assert a boolean expression, throwing {@link IllegalStateException}
	 * if the test result is <code>false</code>.
	 * <p>Call {@link #isTrue(boolean)} if you wish to
	 * throw {@link IllegalArgumentException} on an assertion failure.
	 * <pre class="code">Assert.state(id == null);</pre>
	 * @param expression a boolean expression
	 * @throws IllegalStateException if the supplied expression is <code>false</code>
	 */
	public static void state(boolean expression) {
		state(expression, "[Assertion failed] - this state invariant must be true");
	}

	/**
	 *
	 * 作者:sanri <br/>
	 * 时间:2017-8-28上午10:02:27<br/>
	 * 功能:验证参数是否有任何一个参数名是空值,如果有任何一个是空值,则抛出异常<br/>
	 * 对于 String 类型,规则为 StringUtils.isBlank<br/>
	 * <del>对于 原始型,规则为是否为原始值</del> 不验证<br/>
	 * 对于 原始型包装类和对象类型,验证是否为 null<br/>
	 * 数组不验证<br/>
	 * 方法名解释: 断言不会有任何一个参数名的值是空的,即所有的都不会为空,只要有任何一个参数是空值,则抛出异常
	 *  <br/>
	 * @param param 参数对象
	 * @param paramNames 参数名列表,如果参数名不传,则验证所有参数
	 */
	public static void assertNotAnyNull(Object param,String... paramNames) throws BusinessException {
		if(param == null){
			throw new BusinessException("400","参数为空");
		}
		try {
			List<PropertyDescriptor> validteProperty = findValidateProperty(param, paramNames);
			//验证需要验证的属性是否有空值
			for (PropertyDescriptor propertyDescriptor : validteProperty) {
				Method method = propertyDescriptor.getReadMethod();
				method.setAccessible(true);
				Object paramValue = method.invoke(param);
				if(paramValue == null){
					throw new BusinessException("400","参数为空:"+propertyDescriptor.getName());
				}
				Class<? extends Object> valueClazz = paramValue.getClass();
				if(valueClazz.isArray() || valueClazz.isPrimitive()){
					continue;
				}
				if(valueClazz == String.class){
					String trueValue = org.apache.commons.lang.ObjectUtils.toString(paramValue);
					if(org.apache.commons.lang.StringUtils.isBlank(trueValue)){
						throw new BusinessException("400","参数为空:"+propertyDescriptor.getName());
					}
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {			//调试阶段需要避免此错误
			e.printStackTrace();
		}
	}

	/**
	 *
	 * 作者:sanri <br/>
	 * 时间:2017-8-28下午12:15:50<br/>
	 * 功能:查找需要验证的参数 <br/>
	 * @param param
	 * @param paramNames
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private static List<PropertyDescriptor> findValidateProperty(Object param, String... paramNames) throws IntrospectionException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, IntrospectionException {
		List<PropertyDescriptor> validteProperty = new ArrayList<PropertyDescriptor>();
		//获取参数的所有 get 方法,判断指定参数名是否空
		Class<? extends Object> clazz = param.getClass();
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		//获取所有需要验证的 get 方法
		if(propertyDescriptors != null && propertyDescriptors.length > 0){
			//如果没有指定参数名数组,取全部参数
			if(paramNames == null || paramNames.length == 0){
				for (PropertyDescriptor propertyDescriptor : validteProperty) {
					validteProperty.add(propertyDescriptor);
				}
			}
			//否则取部分参数
			for (String paramName : paramNames) {
				PropertyDescriptor propertyDescriptor = PropertyUtils.getPropertyDescriptor(param, paramName);
				validteProperty.add(propertyDescriptor);
			}
		}
		return validteProperty;
	}

}
