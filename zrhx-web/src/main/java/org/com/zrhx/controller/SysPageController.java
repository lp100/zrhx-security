package org.com.zrhx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: SysPageController
 * @Description: 系统页面视图
 * @author: gs
 * @date: 2017/6/8 17:42
 */
@Controller
public class SysPageController {
	
	@RequestMapping("sys/{url}.html")
	public String page(@PathVariable("url") String url){
		return "sys/" + url + ".html";
	}

	@RequestMapping("{package}/{url}.html")
	public String generator(@PathVariable("url") String url,@PathVariable("package") String packageName){
		return packageName+"/" + url + ".html";
	}
}
