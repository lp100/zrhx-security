package org.comm.zrhx.service;

import java.util.Map;
import org.comm.zrhx.vo.RspUediterVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件操作服务类接口
 * @author gs
 *
 */
public interface IFileOperateService {

	/**
	 * 上传文件到项目文件夹
	 * @param file 文件
	 * @param remotepath 存放的文件夹
	 * @param isRname 是否需要修改名称
	 * @return
	 */
	public Map<String, Object> uploadFile(MultipartFile file, HttpServletRequest request, String remotepath, boolean isRname);
	
	/**
	 * 上传apk文件到项目文件夹 并读取信息
	 * @param file 文件
	 * @param remotepath 存放的文件夹
	 * @param isRname 是否需呀修改名称
	 * @return
	 */
	public Map<String, Object> uploadApkFile(MultipartFile file,HttpServletRequest request,  String remotepath, boolean isRname);
	/**
	 *  传图片到项目文件夹（并压缩）
	 * @param file 上传的文件
	 * @param filename 指定的文件名
	 * @param remotepath 存放的文件夹
	 * @param width 压缩的图片宽度
	 * @param height 压缩的图片高度
	 * @return
	 */
	public Map<String, Object> uploadImage(MultipartFile file,HttpServletRequest request,  String filename, String remotepath, String width, String height);


	/**
	 * 上传图片到项目文件夹（并压缩） uediter上传文件方法
	 * @param file 上传的文件
	 * @param filename 指定的文件名
	 * @param remotepath 存放的文件夹
	 * @param width 指定压缩图片宽度
	 * @param height 指定压缩图片高度
	 * @return
	 */
	public RspUediterVo uediterUploadImage(MultipartFile file,HttpServletRequest request,
										   String filename, String remotepath, String width, String height);

	/**
	 * 删除文件
	 * @param filename
	 */
	public  void  deleteFile(String filename);
}
