package org.com.zrhx.utill;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: R
 * @Description:  返回数据
 * @author: gs
 * @date: 2017/6/9 9:39
 */
@ApiModel(value = "返回信息",description = "返回信息 ")
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("code", Constants.SUCCESS_CODE);
	}
	
	public static R error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(500, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	public static R page(Integer totalCount,Object data) {
		R r = new R();
		r.clear();
		r.put(Constants.TOTALCOUNT, totalCount);
		r.put(Constants.DATA, data);
		r.put("code", Constants.SUCCESS_CODE);
		return  r;
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
