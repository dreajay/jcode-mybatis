package com.jcode.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Desc Spring ApplicationContext工具，需要在bean配置文件最开头位置进行声明
 *
 * @Author daijunjie
 * @DateTime 2015-10-10 下午05:40:46
 * 
 */
public class SpringApplicationContextHolder implements ApplicationContextAware {
	public static ApplicationContext applicationContext = null;
	private static Log log = LogFactory.getLog(SpringApplicationContextHolder.class);

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		log.debug("启动Spring容器..." + applicationContext);
		SpringApplicationContextHolder.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}
	
	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException("ApplicaitonContext未注入,请在applicationContext.xml中定义SpringApplicationContextHolder");
		}
	}
	
	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBean(Class<T> requiredType) {
		checkApplicationContext();
		return applicationContext.getBean(requiredType);
	}

	public static boolean containsBean(String beanName) {
		checkApplicationContext();
		return applicationContext.containsBean(beanName);
	}

}
