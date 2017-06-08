package org.com.zrhx.controller;


import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.com.zrhx.service.SysGeneratorService;
import org.com.zrhx.utill.PageUtils;
import org.com.zrhx.utill.Query;
import org.com.zrhx.utill.R;
import org.com.zrhx.xss.XssHttpServletRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午9:12:58
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {


	@Autowired
	private SysGeneratorService sysGeneratorService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sys:generator:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<Map<String, Object>> list = sysGeneratorService.queryList(query);
		int total = sysGeneratorService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 生成代码
	 */
	@RequestMapping("/code")
	@RequiresPermissions("sys:generator:code")
	public void code(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String[] tableNames = new String[]{};
		//获取表名，不进行xss过滤
		HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);
		String tables = orgRequest.getParameter("tables");
		String packAge = orgRequest.getParameter("package");
		tableNames = JSON.parseArray(tables).toArray(tableNames);
		
		byte[] data = sysGeneratorService.generatorCode(tableNames,packAge);
		
		response.reset();  
        response.setHeader("Content-Disposition", "attachment; filename=\"template.zip\"");
        response.addHeader("Content-Length", "" + data.length);  
        response.setContentType("application/octet-stream; charset=UTF-8");  
  
        IOUtils.write(data, response.getOutputStream());
	}
}
