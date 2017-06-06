package org.com.zrhx.controller;


import org.apache.log4j.Logger;
import org.com.zrhx.utill.JSONReturn;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.ParameterizedType;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * 公共控制类
 * @author liupeng
 *
 * @param <T>
 */
public class BaseController<T> {

	private   Class<T> cls;
	/**
	 * 日志
	 */
	protected Logger logger;
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private HttpSession session;
	/**
	 * 返回JSON工具类
	 */
	protected JSONReturn jsonReturn = JSONReturn.newInstance();
	/**当前页*/
	private String page;
	/**每页显示条数*/
	private String rows;
	/**
	 * 查询参数集合
	 */
	private List<Object> param=new ArrayList<Object>();
	
	@SuppressWarnings("unchecked")
	protected BaseController() {
		ParameterizedType type = (ParameterizedType) getClass() .getGenericSuperclass();
		cls = (Class<T>) type.getActualTypeArguments()[0];
		logger = Logger.getLogger(cls);
	}

	@ModelAttribute
	public void setReqAndResp(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.rows = request.getParameter("rows");
		this.page = request.getParameter("page");
	}

	/**
	 * 获取客户端ip
	 * @param request
	 * @return
	 */
	public String getRemoteHost(javax.servlet.http.HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}

	


	public HttpServletRequest getRequest() {
		return request;
	}


	public HttpServletResponse getResponse() {
		return response;
	}


	public HttpSession getSession() {
		return session;
	}


	public JSONReturn getJsonReturn() {
		return jsonReturn;
	}

	public String getPage() {
		return page;
	}

	public String getRows() {
		return rows;
	}

	public List<Object> getParam() {
		return param;
	}

	public void setParam(List<Object> param) {
		this.param = param;
	}

	
}
