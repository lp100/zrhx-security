package org.com.zrhx.utill;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
/**
 * 获取当前web容器的工厂类
 * @author gs
 *
 */
public class MyBeanFactory {

	private static MyBeanFactory instance = null;
	private ApplicationContext atx;

	private MyBeanFactory() {
		atx =ContextLoader.getCurrentWebApplicationContext();
		
	}

	public synchronized static MyBeanFactory getInstance() {
		if (instance == null)
			instance = new MyBeanFactory();
		return instance;
	}

	public Object getObject(String objectId) {
		return atx.getBean(objectId);
	}
	
	public Object getObject(Class<?> clazz) {
		return atx.getBean(clazz.getSimpleName());
	}

	public WebApplicationContext getContext() {
		return (WebApplicationContext) atx;
	}
	

}
