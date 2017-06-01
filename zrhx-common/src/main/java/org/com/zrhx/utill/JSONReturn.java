package org.com.zrhx.utill;

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 返回json数据的封装类
 * @author gs
 *
 */
public class JSONReturn {
	
	private JSONReturn(){
		dataMap= new HashMap<String, Object>();
	}
	
	private Map<String,Object> dataMap;
	
	
	
	public Map<String,Object> getDataMap(){
		return dataMap;
	}

	public static JSONReturn newInstance(){
		return new JSONReturn();
	}
	/**
	 * 
	 * @Title: toPermissionMenu
	 *
	 * @Description: 权限菜单
	 *
	 * @param: @param page
	 * @param: @return   
	 *
	 * @return: String   
	 *
	 * @throws
	 */
	public Map<String,Object> toPermissionMenu(List<? extends Object> list){
		dataMap.clear();
		dataMap.put(Constants.MENU, list);
		dataMap.put(Constants.SUCCESS, true);
		return dataMap;
	}
	
	/**
	 * 返回分页包装的数据
	 * @param page
	 * @return
	 */
	public Map<String,Object> page(PageController<? extends Object> page){
		dataMap.clear();
		dataMap.put(Constants.TOTALCOUNT, page.getTotalCount());
		dataMap.put(Constants.DATA, page.getData());
		dataMap.put(Constants.SUCCESS, true);
		return dataMap;
	}
	/**
	 * 返回字符串
	 * @param str
	 * @return
	 */
	public String toStr(String str){
		
		return Constants.JSON;
	}
	
	/**
	 * 返回分页封装的数据（数据 为map）
	 * @param page
	 * @return
	 */
	public Map<String,Object> page2(PageController<? extends Object> page){
		dataMap.clear();
		dataMap.put(Constants.TOTALCOUNT, page.getTotalCount());
		dataMap.put(Constants.DATA, page.getMapData());
		dataMap.put(Constants.SUCCESS, true);
		return dataMap;
	}
	
	

	/**
	 * 返回 list数据 并携带list长度
	 * @param data
	 * @return
	 */
	public Map<String, Object> page(Collection<? extends Object> data){
		dataMap.clear();
		dataMap.put(Constants.TOTALCOUNT, data.size());
		dataMap.put(Constants.DATA, data);
		dataMap.put(Constants.SUCCESS, true);
		return dataMap;
	}
	
	/** 
	 * @Title: jsonO 
	 *
	 * @Description: 原始数据不经过json的封装
	 *
	 * @param @param data
	 * @param @return
	 *
	 * @return String    返回类型 
	 *
	 * @throws 
	 *
	*/ 
	public Map<String, Object> jsonO(Collection<? extends Object> data){
		dataMap.clear();
		dataMap.put(Constants.DATA, data);
		dataMap.put(Constants.SUCCESS, true);
		return dataMap;
	}
	/***
	 * 
	 * @param data  集合对象
	 * @param flag 标志
	 * @return
	 */
	public Map<String, Object> jsonO(Collection<? extends Object> data ,boolean flag){
		dataMap.clear();
		dataMap.put(Constants.DATA, data);
		dataMap.put(Constants.SUCCESS, flag);
		return dataMap;
	}
	/**
	 * 
	 * @param data 返回的对象
	 * @return
	 */
	public Map<String, Object> json(Object data){
		dataMap.clear();
		dataMap.put(Constants.DATA, data);
		dataMap.put(Constants.SUCCESS, true);
		dataMap.put(Constants.MESSAGE, "");
		return dataMap;
	}
	/**
	 * 
	 * @param data 返回的对象
	 * @return
	 */
	public Map<String, Object> jsonListObject(Object data){
		dataMap.clear();
		List<Object> list = new ArrayList<Object>();
		list.add(data);
		dataMap.put(Constants.DATA, list);
		dataMap.put(Constants.SUCCESS, true);
		dataMap.put(Constants.MESSAGE, "");
		return dataMap;
	}
	/**
	 * 
	 * @param data 返回的对象
	 * @return
	 */
	public Map<String, Object> jsonList(boolean data){
		dataMap.clear();
		List<Object> list = new ArrayList<Object>();
//		list.add(data);
		dataMap.put(Constants.DATA, list);
		dataMap.put(Constants.SUCCESS, data);
		dataMap.put(Constants.MESSAGE, "");
		return dataMap;
	}
	
	/**
	 * 返回分页封装的数据（数据 为map）
	 * @param page
	 * @return
	 */
	public Map<String,Object> pageList(PageController<? extends Object> page){
		dataMap.clear();
		dataMap.put(Constants.DATA, page);
		dataMap.put(Constants.SUCCESS, true);
		dataMap.put(Constants.MESSAGE, "");
		dataMap.put(Constants.TOTALCOUNT, page.getTotalCount());
		return dataMap;
	}
	/**
	 * 返回json字字符串 封装的map
	 * @param data
	 * @return
	 */
	public Map<String, Object> jsonstr(Collection<? extends Object> data){
		dataMap.clear();
		String jsonDataString = JSONArray.fromObject(data).toString();
		dataMap.put(Constants.DATA, jsonDataString);
		dataMap.put(Constants.SUCCESS, true);
		return dataMap;
	}
	
	/**
	 * 返回成功
	 * @return
	 */
	public Map<String, Object> json(){
		dataMap.clear();
		dataMap.put(Constants.SUCCESS, true);
		return dataMap;
	}
	
	/**
	 * 
	 * @param boo  传入成功/失败
	 * @return
	 */
	public Map<String, Object> json(boolean boo){
		dataMap.clear();
		dataMap.put(Constants.SUCCESS, boo);
		return dataMap;
	}
	
	/**
	 * 
	 * @param str  返回的字符串
	 * @return
	 */
	public Map<String, Object> json(String str){
		dataMap.clear();
		dataMap.put(Constants.MESSAGE, str);
		dataMap.put(Constants.SUCCESS, true);
		return dataMap;
	}
	/**
	 * 
	 * @Title: json
	 *
	 * @Description: TODO
	 *
	 * @param: @param i
	 * @param: @return   
	 *
	 * @return: String   
	 *
	 * @throws
	 */
	public Map<String, Object> json(int i){
		dataMap.clear();
		dataMap.put(Constants.TOTALCOUNT, i);
		dataMap.put(Constants.SUCCESS, true);
		return dataMap;
	}
	/**
	 *
	 * @param msg  返回错的提示字符串
	 * @return
	 */
	public Map<String, Object> error(String msg){
		dataMap.clear();
		dataMap.put(Constants.MESSAGE, msg);
		dataMap.put(Constants.SUCCESS, false);
		return dataMap;
	}
}
