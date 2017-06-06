package org.comm.zrhx.controller;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.com.zrhx.controller.BaseController;
import org.com.zrhx.utill.Constants;
import org.comm.zrhx.service.IFileOperateService;
import org.comm.zrhx.vo.RspUediterVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件操作控制类
 * 
 * @author gs
 * 
 */
@Controller
@RequestMapping("fileOperate")
public class FileOperateController extends
		BaseController<FileOperateController> {
	@Resource
	private IFileOperateService fileOperateService;
	/**
	 * 上传文件
	 *
	 * @param file
	 * @return
	 */
	@RequestMapping("/uploadFile")
	@ResponseBody
	public Map<String, Object> uploadFile(MultipartFile file,
										  String remotepath, String filename) {
		Map<String, Object> dataMap;
		String isRname= super.getRequest().getParameter("isRname");
		if(StringUtils.isEmpty(isRname)){
			dataMap = fileOperateService.uploadFile(file,getRequest(), remotepath,true);
		}else{
			dataMap = fileOperateService.uploadFile(file,getRequest(), remotepath,false);
		}
		return dataMap;
	}
	/**
	 * 上传图片(压缩)
	 *
	 * @param file
	 * @return
	 */
	@RequestMapping("/uploadImage")
	@ResponseBody
	public Map<String, Object> uploadImage(MultipartFile file,
										   String remotepath, String filename) {
		Map<String, Object> dataMap;
		String width= super.getRequest().getParameter("width");
		String height= super.getRequest().getParameter("height");
		dataMap = fileOperateService.uploadImage(file,getRequest(), filename, remotepath, width, height);
		return dataMap;
	}
	/**
	 * 上传图片(压缩) uediter上传图片方法
	 *
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/uediterUploadImage", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String uediterUploadImage(MultipartFile file,
									 String remotePath, String fileName) {
		RspUediterVo dataMap;
		String width= super.getRequest().getParameter("width");
		String height= super.getRequest().getParameter("height");
		dataMap = fileOperateService.uediterUploadImage(file,getRequest(), fileName, remotePath, width, height);
		return JSONObject.fromObject(dataMap).toString();
	}

}
