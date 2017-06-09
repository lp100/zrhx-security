package org.com.zrhx.utill;

import com.alibaba.druid.support.json.JSONUtils;
import net.sf.json.JSON;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: RRExceptionHandler
 * @Description:  异常处理器
 * @author: gs
 * @date: 2017/5/25 9:02
 */
@Component
public class RRExceptionHandler implements HandlerExceptionResolver {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
		if (null != request.getHeader("X-Requested-With") &&
				request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
		    //说明该请求为异步请求

			try {
				R r = new R();
				response.setContentType("application/json;charset=utf-8");
				response.setCharacterEncoding("utf-8");

				if (ex instanceof RRException) {
					r.put("code", ((RRException) ex).getCode());
					r.put("msg", ((RRException) ex).getMessage());
				}else if(ex instanceof DuplicateKeyException){
					r = R.error("数据库中已存在该记录");
				}else if(ex instanceof AuthorizationException){
					r = R.error("没有权限，请联系管理员授权");
				}else{
					r = R.error();
				}

				//记录异常日志
				logger.error(ex.getMessage(), ex);

				String json = JSONUtils.toJSONString(r);
				response.getWriter().print(json);
			} catch (Exception e) {
				logger.error("RRExceptionHandler 异常处理失败", e);
			}
		}else{
			ModelAndView modelAndView = new ModelAndView();
			try {
				if (ex instanceof RRException) {
					modelAndView.addObject("message", ((RRException) ex).getMessage());
					modelAndView.setViewName("error/400.jsp");
				}else if(ex instanceof DuplicateKeyException){
					modelAndView.addObject("message", "数据库中已存在该记录");
					modelAndView.setViewName("error/400.jsp");
				}else if(ex instanceof AuthorizationException){
					modelAndView.addObject("message", "没有权限，请联系管理员授权");
					modelAndView.setViewName("error/400.jsp");
				}else{
					modelAndView.addObject("message", "未知异常，请联系管理员");
					modelAndView.setViewName("error/400.jsp");
				}
				//记录异常日志
				logger.error(ex.getMessage(), ex);
			} catch (Exception e) {
				logger.error("RRExceptionHandler 异常处理失败", e);
			}
			return modelAndView;
		}
		return new ModelAndView();
	}
}
