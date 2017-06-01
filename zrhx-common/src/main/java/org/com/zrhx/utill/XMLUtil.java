package org.com.zrhx.utill;
import com.thoughtworks.xstream.XStream;

import java.util.List;
/**
 * xml操作类
 * @author gs
 *
 */
public class XMLUtil {
	/**
	 * 
	 * @Title: ObjecttoXML 
	 *
	 * @Description: TODO(将对象转化为xml形式的String) 
	 *
	 * @param @param object 要转化的对象
	 * @param @param objects 对象中包含的类属性 以及集合属性存放的类
	 * @param @return
	 * @throws 
	 *
	 */
	public static  String ObjecttoXML(Object object,Class<?>...objects){
		XStream stream=new XStream();
		
		String objectname=object.getClass().getSimpleName();
		stream.alias(objectname, object.getClass());
		if(objects!=null){
		for (int i = 0; i < objects.length; i++) {
			String name=objects[i].getSimpleName();
			stream.alias(name, objects[i]);
		}
		}
		return stream.toXML(object);
	}
	/**
	 * 
	 * @Title: XMLtoObject 
	 *
	 * @Description: TODO(将xml形势的字符串转化为对象) 
	 *
	 * @param @param xml
	 * @param @param object 要转化的对象
	 * @param @param objects 对象中包含的对象类型的属性 以及集合属性存放的类
	 * @param @return
	 *
	 * @return Object    返回类型 
	 *
	 */
	public static <T> Object XMLtoObject(String xml,Class<?>...objects){
		XStream stream=new XStream();
		String objectname=objects[0].getSimpleName();
		stream.alias(objectname, objects[0]);

		if(objects.length>1){
			for (int i = 1; i < objects.length; i++) {
				String name=objects[i].getSimpleName();
				stream.alias(name, objects[i]);
			}
			}
		@SuppressWarnings("unchecked")
		T t= (T) stream.fromXML(xml);
		return t;
	}
	/**
	 * 
	 * @Title: XMLtoObjectByAnnotations 
	 *
	 * @Description: TODO(将xml形势的字符串转化为对象(通过注解的方式)) 
	 *
	 * @param @param xml
	 * @param @param cls 要转化的对象
	 * @param @return
	 *
	 * @return Object    返回类型 
	 *
	 */
	public static <T> Object XMLtoObjectByAnnotations(String xml,Class<T> cls){
		XStream stream=new XStream();
		stream.processAnnotations(cls);//通过注解的方式
		@SuppressWarnings("unchecked")
		T t= (T) stream.fromXML(xml);
		return t;
	}
	/**
	 * 
	 * @param <T>
	 * @Title: XMLtoObjectByAnnotations 
	 *
	 * @Description: TODO(将xml形势的字符串转化为对象(通过注解的方式)) 
	 *
	 * @param @param xml
	 * @param @param cls 要转化的对象
	 * @param @return
	 *
	 * @return Object    返回类型 
	 *
	 */
	public static <T> String  ObjecttoXMLByAnnotations(Class<T> cls,Object object){
		XStream stream=new XStream();
		stream.processAnnotations(cls);//通过注解的方式
		stream.alias("ss", List.class);
		return stream.toXML(object);
	}
	/**
	 * 
	 * @param <T>
	 * @Title: XMLtoObjectByAnnotations 
	 *
	 * @Description: TODO(将xml形势的字符串转化为对象(通过注解的方式)) 
	 *
	 * @param @param xml
	 * @param @param cls 要转化的对象
	 * @param @return
	 *
	 * @return Object    返回类型 
	 *
	 */
	public static <T> String  ObjecttoXMLByAnnotations(Class<T> cls,List<T> list,String listalias){
		XStream stream=new XStream();
		stream.processAnnotations(cls);//通过注解的方式
		stream.alias(listalias, List.class);
		return stream.toXML(list);
	}
	
}
