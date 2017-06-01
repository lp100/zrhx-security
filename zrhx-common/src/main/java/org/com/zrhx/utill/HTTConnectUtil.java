package org.com.zrhx.utill;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 *请求
 * @author gs
 *
 */
public class HTTConnectUtil {
	
	private static Logger logger = Logger.getLogger(HTTConnectUtil.class);
	/**
	 * 以流的方式发送Post请求
	 * @return response文本
	 */
	public static String postByStream(String pathUrl,String requestParam){
		try{ 
			// 建立连接 
			URL url = new URL(pathUrl); 
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection(); 
			 
			// //设置连接属性 
			httpConn.setDoOutput(true);// 使用 URL 连接进行输出 
			httpConn.setDoInput(true);// 使用 URL 连接进行输入 
			httpConn.setUseCaches(false);// 忽略缓存 
			httpConn.setRequestMethod("POST");// 设置URL请求方法 
			 
			// 设置请求属性 
			// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致 
			byte[] requestStringBytes = requestParam.getBytes("UTF-8"); 
			httpConn.setRequestProperty("Content-length", "" + requestStringBytes.length); 
			httpConn.setRequestProperty("Content-Type", "application/octet-stream"); 
			httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接 
			httpConn.setRequestProperty("Charset", "UTF-8"); 
			 
			// 建立输出流，并写入数据 
			OutputStream outputStream = httpConn.getOutputStream(); 
			outputStream.write(requestStringBytes); 
			outputStream.close(); 
			// 获得响应状态 
			int responseCode = httpConn.getResponseCode(); 
			StringBuffer sb = new StringBuffer(); 
			if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功 
				// 当正确响应时处理数据 
				String readLine; 
				BufferedReader responseReader; 
				// 处理响应流，必须与服务器响应流输出的编码一致 
				 responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8")); 
				while ((readLine = responseReader.readLine()) != null) { 
					sb.append(readLine); 
				} 
				responseReader.close(); 
			} else{
				return "";
			}
			return sb.toString();
		} catch (Exception ex) { 
			ex.printStackTrace();
			logger.error(ex.getMessage(), ex);
			return "";
		} 
	}
	public static void main(String[] args) {
		 String pathUrl = "http://192.168.2.88:8080/SCS/appdata/getParameters";
		 String s= postByStream(pathUrl,"<getParameters ip='192.168.2.51' terminalType='24'></getParameters>");//机场结构测试
		 
		 
			System.out.println(s);
	}
}
