package org.comm.zrhx.service;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.com.zrhx.utill.*;
import org.com.zrhx.utill.apk.ApkInfo;
import org.com.zrhx.utill.apk.ReadUtil;
import org.comm.zrhx.vo.RspUediterVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件操作服务类
 * @author gs
 *
 */
@Service
public class FileOperateService  implements
		IFileOperateService {
	
	private Logger logger =  Logger.getLogger(FileOperateService.class);

	/**
	 *
	 * @param file 文件
	 * @param request
	 * @param remotepath 存放的文件夹
	 * @param isRname 是否需要修改名称
	 * @return
	 */
	@Override
	public Map<String, Object> uploadFile(MultipartFile file, HttpServletRequest request, String remotepath, boolean isRname) {
		Map<String,Object> dataMap =  new HashMap<>();
		try {
			if ((file == null) || (null== file.getInputStream())) {
				throw new IllegalArgumentException("未获取到文件内容 !");
			}

			String separator= System.getProperty("file.separator");
			String filename="";
			if(isRname){//如果需要修改文件名称
				String[] image =  file.getOriginalFilename().split("\\.");
				filename = ServiceUtil.getidByUUID() +"."+ image[image.length-1];

			}else{
				filename =  file.getOriginalFilename();
			}
			StringBuilder[] stringBuilder = createFiles(remotepath);
			stringBuilder[0].append(separator)
					.append(filename);
			file.transferTo(new  File(stringBuilder.toString()));
			stringBuilder[1].append(separator)
					.append(filename);
			dataMap.put("error", Constants.SUCCESS_CODE);
			dataMap.put("headicon", filename);
			dataMap.put("url", stringBuilder[1].toString());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			dataMap.put("error", Constants.ERROR_CODE);
			dataMap.put("message", "上传文件失败！");
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			dataMap.put("error", Constants.ERROR_CODE);
			dataMap.put("message", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			dataMap.put("error", Constants.ERROR_CODE);
			dataMap.put("message", "系统异常！");

		}
		return dataMap;
	}

	/**
	 *
	 * @param file 文件
	 * @param request
	 * @param remotepath 存放的文件夹
	 * @param isRname 是否需呀修改名称
	 * @return
	 */
	@Override
	public Map<String, Object> uploadApkFile(MultipartFile file, HttpServletRequest request, String remotepath, boolean isRname) {
		Map<String,Object> dataMap =  new HashMap<String, Object>();
		try {
			if ((file == null) || (null== file.getInputStream())) {
			throw new IllegalArgumentException("未获取到文件内容 !");
			}
			String contentType = file.getContentType();
			if (StringUtils.isEmpty(contentType)) {
				throw new IllegalArgumentException("未获取到文件类型！");
			}
			if (!"application/x-zip-compressed".equals(contentType)){
				throw new IllegalArgumentException("请上传APK文件！");
			}
			String separator= System.getProperty("file.separator");
			String filename="";
			if(isRname){
				String[] image =  file.getOriginalFilename().split("\\.");
				filename = ServiceUtil.getidByUUID() +"."+ image[image.length-1];

			}else{
				filename =  file.getOriginalFilename();
			}

			StringBuilder[] stringBuilder = createFiles(remotepath);
			stringBuilder[0].append(separator)
					.append(filename);
			File apkUrl = new  File(stringBuilder[0].toString());
			file.transferTo(apkUrl);
			stringBuilder[1].append(separator)
					.append(filename);
			ApkInfo apkInfo = ReadUtil.readAPK(apkUrl);
			if(Constants.ERROR_CODE.equals(apkInfo.getCode())){
				dataMap.put("error", Constants.ERROR_CODE);
				dataMap.put("message", apkInfo.getError());
				return dataMap;
			}else{
				dataMap.put("versionName", apkInfo.getVersionName());
				dataMap.put("versionCode", apkInfo.getVersionCode());
			}
			dataMap.put("error", Constants.SUCCESS_CODE);
			dataMap.put("headicon", filename);
			dataMap.put("url",stringBuilder[1].toString());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			dataMap.put("error", Constants.ERROR_CODE);
			dataMap.put("message", "上传图片失败！");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			dataMap.put("error", Constants.ERROR_CODE);
			dataMap.put("message", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			dataMap.put("error", Constants.ERROR_CODE);
			dataMap.put("message", "系统异常！");

		}
		return dataMap;
	}

	/**
	 *
	 * @param file 上传的文件
	 * @param request
	 * @param filename 指定的文件名
	 * @param remotepath 存放的文件夹
	 * @param width 压缩的图片宽度
	 * @param height 压缩的图片高度
	 * @return
	 */
	@Override
	public Map<String, Object> uploadImage(MultipartFile file, HttpServletRequest request, String filename, String remotepath, String width, String height) {
		Map<String,Object> dataMap =  new HashMap<String, Object>();
		try {
			if ((file == null) || (null== file.getInputStream())) {
				throw new IllegalArgumentException("未获取到文件内容 !");
			}
			String contentType = file.getContentType();
			if (StringUtils.isEmpty(contentType)) {
				throw new IllegalArgumentException("未获取到文件类型！");
			}
			if (contentType.indexOf("image")==-1){
				throw new IllegalArgumentException("请上传图片！");
			}
			String separator= System.getProperty("file.separator");
			String imageName="";
			if(StringUtils.isEmpty(filename)){
				String[] image =  file.getOriginalFilename().split("\\.");
				imageName =ServiceUtil.getidByUUID() +"."+ image[image.length-1];
			}else{
				imageName = filename;
			}
			StringBuilder[] stringBuilder = createFiles(remotepath);
			stringBuilder[0].append(separator)
					.append(imageName);
			if(StringUtils.isEmpty(width)){
				ImageCompressUtil.saveMinPhoto(file.getInputStream(), stringBuilder[0].toString(), 800,  0.9d);
			}else{
				int h= Integer.parseInt(StringUtils.isEmpty(height)?"200":height);
				ImageCompressUtil.saveMinPhoto(file.getInputStream(),  stringBuilder[0].toString(), h,  0.9d);
			}
			stringBuilder[1].append(separator)
					.append(filename);
			dataMap.put("error", Constants.SUCCESS_CODE);
			dataMap.put("headicon", imageName);
			dataMap.put("url", stringBuilder[1].toString());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			dataMap.put("error", Constants.ERROR_CODE);
			dataMap.put("message", "上传图片失败！");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			dataMap.put("error", Constants.ERROR_CODE);
			dataMap.put("message", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			dataMap.put("error", Constants.ERROR_CODE);
			dataMap.put("message", "系统异常！");

		}

		return dataMap;
	}

	/**
	 *
	 * @param file 上传的文件
	 * @param request
	 * @param filename 指定的文件名
	 * @param remotepath 存放的文件夹
	 * @param width 指定压缩图片宽度
	 * @param height 指定压缩图片高度
	 * @return
	 */
	@Override
	public RspUediterVo uediterUploadImage(MultipartFile file, HttpServletRequest request, String filename, String remotepath, String width, String height) {

		try {
			if ((file == null) || (null== file.getInputStream())) {
				throw new IllegalArgumentException("未获取到文件内容 !");
			}
			String contentType = file.getContentType();
			if (StringUtils.isEmpty(contentType)) {
				throw new IllegalArgumentException("未获取到文件类型！");
			}
			if (contentType.indexOf("image")==-1){
				throw new IllegalArgumentException("请上传图片！");
			}
			String separator= System.getProperty("file.separator");
			String imageName="";
			if(StringUtils.isEmpty(filename)){
				String[] image =  file.getOriginalFilename().split("\\.");
				imageName =ServiceUtil.getidByUUID() +"."+ image[image.length-1];
			}else{
				imageName = filename;
			}
			StringBuilder[] stringBuilder = createFiles(remotepath);
			stringBuilder[0].append(separator)
					.append(imageName);
			if(!StringUtils.isEmpty(width)){//如果给定宽度则按照图片的宽度压缩
				int w= Integer.parseInt(width);
				ImageCompressUtil.saveMinPhoto(file.getInputStream(), stringBuilder[0].toString(), w,  0.9d);
			}else if(!StringUtils.isEmpty(height)){//如果给定高度则按照图片的宽度压缩
				int h= Integer.parseInt(height);
				ImageCompressUtil.saveMinPhoto(file.getInputStream(), stringBuilder[0].toString(), h,  1d);
			}else{//默认 800*1600进行等比例压缩
				ImageCompressUtil.saveMinPhoto(file.getInputStream(), stringBuilder[0].toString(), 0,  0);
			}
			stringBuilder[1].append(separator)
					.append(filename);
			return RspUediterVo.success( stringBuilder[1].toString(),
					file.getSize(),imageName,file.getContentType());
		}catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return RspUediterVo.error();
	}

	/**
	 *
	 * @param filename
	 */
	@Override
	public void deleteFile(String filename) {
       File file = new File(filename);
       file.delete();
	}

	/**
	 * 创建目录
	 * @param remotepath 所在的文件夹
	 * @return
	 */
	private  StringBuilder[]  createFiles( String remotepath) {
		StringBuilder[] stringBuilders = new  StringBuilder[2];
		String separator = System.getProperty("file.separator");
		String replace = ServiceUtil.getwebapps();
		stringBuilders[0] = new StringBuilder(replace);
		stringBuilders[0].append(separator)
				.append(Constants.UPLOAD_PATH)
				.append(separator)
				.append(remotepath);
		File parentfile = new File(stringBuilders[0].toString());
		if (!parentfile.exists()) {//创建根目录
			parentfile.mkdirs();
			parentfile.setExecutable(false);//设置不可执行操作
		}
		String fileDirectory = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);
		stringBuilders[0].append(separator);
		stringBuilders[0].append(remotepath);
		stringBuilders[0].append(separator);
		stringBuilders[0].append(fileDirectory);
		File remotepathfile = new File(stringBuilders[0].toString());

		if (!remotepathfile.exists()) {//创建文件夹
			remotepathfile.mkdirs();
			parentfile.setExecutable(false);//设置不可执行操作
		}
		stringBuilders[1] = new StringBuilder("/");
		stringBuilders[1].append(Constants.UPLOAD_PATH)
				.append("/")
				.append(remotepath)
				.append("/")
				.append(fileDirectory);
		return stringBuilders;
	}
}
