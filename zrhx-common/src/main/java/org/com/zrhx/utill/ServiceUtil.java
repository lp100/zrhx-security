package org.com.zrhx.utill;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
/**
 * 服务类工具
 * @author gs
 *
 */
public class ServiceUtil {
	
	private static Logger LOG = Logger.getLogger(ServiceUtil.class);
	/**
	 * 
	 * @Title: shortToBytes 
	 *
	 * @Description: TODO(short转Bytes) 
	 *
	 * @param @param n
	 * @param @return
	 *
	 * @return byte[]    返回类型 
	 *
	 * @throws 
	 *
	 */
	public static byte[] shortToBytes(short n) {

		byte[] b = new byte[2];
	
		b[1] = (byte) (n & 0xff);
	
		b[0] = (byte) ((n >> 8) & 0xff);

		return b;

	}
   /**
    * 
    * @Title: bytesToShort 
    *
    * @Description: TODO(bytes转化为Short) 
    *
    * @param @param b
    * @param @return
    *
    * @return short    返回类型 
    *
    * @throws 
    *
    */
	public static short bytesToShort(byte[] b) {

		return (short) (b[1] & 0xff
	
		| (b[0] & 0xff) << 8);

	}
   /**
    * 
    * @Title: doubleByte 
    *
    * @Description: TODO(double转化为Byte) 
    *
    * @param @param d
    * @param @return
    *
    * @return byte[]    返回类型 
    *
    * @throws 
    *
    */
	public static byte[] doubleByte(double d) {

		byte[] b = new byte[8];
		long l = Double.doubleToLongBits(d);
		for (int i = b.length-1; i >=0 ; i--) {
			b[i] = new Long(l).byteValue();
			l = l >> 8;
		}
		return b;
	
	}
  /**
   * 
   * @Title: byteDouble 
   *
   * @Description: TODO(byte转化为Double) 
   *
   * @param @param b
   * @param @return
   *
   * @return double    返回类型 
   *
   * @throws 
   *
   */
	public static double byteDouble(byte[] b) {
		long l;

		l = b[7];
		l &= 0xff;
		l |= ((long) b[6] << 8);
		l &= 0xffff;
		l |= ((long) b[5] << 16);
		l &= 0xffffff;
		l |= ((long) b[4] << 24);
		l &= 0xffffffffl;
		l |= ((long) b[3] << 32);
		l &= 0xffffffffffl;
		l |= ((long) b[2] << 40);
		l &= 0xffffffffffffl;
		l |= ((long) b[1] << 48);
		l &= 0xffffffffffffffl;
		l |= ((long) b[0] << 56);
		double dd = Double.longBitsToDouble(l);
		// 小数格式化，引号中的0.000表示保留小数点后三位（第四位四舍五入）
		DecimalFormat df = new DecimalFormat("0.00000000000000");
		String num = df.format(dd);
		return Double.parseDouble(num);
	}
    /**
     * 
     * @Title: intBytes 
     *
     * @Description: TODO(int转化为Bytes) 
     *
     * @param @param num
     * @param @return
     *
     * @return byte[]    返回类型 
     *
     * @throws 
     *
     */
	public static byte[] intBytes(int num) {
		byte[] result = new byte[4];
		// 由高位到低位
		result[0] = (byte) ((num >> 24) & 0xFF);
		result[1] = (byte) ((num >> 16) & 0xFF);
		result[2] = (byte) ((num >> 8) & 0xFF);
		result[3] = (byte) (num & 0xFF);
		return result;
	}

	public static int bytesInt(byte[] byteNum) {
		int value = 0;
		// 由高位到低位
		for (int i = 0; i < 4; i++) {
			int shift = (4 - 1 - i) * 8;
			value += (byteNum[i] & 0x000000FF) << shift;// 往高位游
		}
		return value;
	}

	public static long bytesLong(byte[] byteNum) {
		long num = 0;
		for (int ix = 0; ix < 8; ++ix) {
			num <<= 8;
			num |= (byteNum[ix] & 0xff);
		}
		return num;
	}

	public static byte[] longBytes(long num) {
		byte[] byteNum = new byte[8];
		for (int ix = 0; ix < 8; ++ix) {
			int offset = 64 - (ix + 1) * 8;
			byteNum[ix] = (byte) ((num >> offset) & 0xff);
		}
		return byteNum;
	}

	/**
	 * 
	 * @Title: stringToArray
	 * @Description: 将字符串拆分成二维数组1,2,3,4;5,6,7,8;
	 * @param string
	 * @param regex1
	 *            第一次拆分规则
	 * @param regex2
	 *            第二次拆分规则
	 * @return String[][]
	 * @throws
	 */
	public static String[][] stringToArray(String string, String regex1,
			String regex2) {
		if (isEmpty(string)) {
			return null;
		}
		String[] temp = string.split(regex1);
		String[][] result = new String[temp.length][];
		for (int i = 0; i < result.length; i++) {
			result[i] = temp[i].split(regex2);
		}
		return result;
	}

	/**
	 * 
	 * @Title: StringToArray
	 * @Description: 将字符串拆分成一维数组
	 * @param string
	 * @param regex
	 * @return String[]
	 * @throws
	 */
	public static String[] stringToArray(String string, String regex) {
		if (isEmpty(string)) {
			return null;
		}
		return string.split(regex);
	}

	/**
	 * 字符串格式化成时间
	 * 
	 * @param dataString
	 * @param pattern
	 * @return
	 */
	public static Date stringTOdate(String dataString, String pattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Date date = sdf.parse(dataString);
			return date;
		} catch (ParseException e) {
			LOG.error(e);
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 字符串格式化成时间
	 * 
	 * @param dataString
	 * @return
	 */
	public static Date stringTOdate(String dataString) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(dataString);
			return date;
		} catch (ParseException e) {
			LOG.error(e);
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 时间格式化成字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateTOstring(Date date, String pattern) {
		try {
			if(null==date){
				return "";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			String str = sdf.format(date);
			return str;
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间格式化成字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateTOstring(Date date) {
		try {
			if(null==date){
				return "";
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str = sdf.format(date);
			return str;
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: isEmpty
	 * @Description: 判断字符串是否为空
	 * @param string
	 * @return boolean
	 * @throws
	 */
	public static boolean isEmpty(String string) {
		if (string == null) {
			return true;
		}
		if (string.trim().equals("")) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @Title: getidByUUID 
	 *
	 * @Description: TODO(用于生成主键ID) 
	 *
	 * @param @return
	 *
	 * @return String    返回类型 
	 *
	 * @throws 
	 *
	 */
    public static String  getidByUUID(){
    	 String id =UUID.randomUUID().toString();
         id=id.replaceAll("-", "");
    	 return id;
    }
    

	public static String getSuccessMsg(int flag) {

		if (flag == 0) {
			return "保存成功!";
		}
		if (flag == 1) {
			return "删除成功!";
		}
		if (flag == 2) {
			return "加载成功!";
		}
		return "";
	}

	public static String getFailuerMsg(int flag) {
		if (flag == 0) {
			return "保存失败!";
		}
		if (flag == 1) {
			return "删除失败!";
		}
		if (flag == 2) {
			return "加载失败!";
		}
		return "";
	}
	/**
	 * 打印控制台的封装方法
	 * @param cls 所在的类
	 * @param str 打印的信息
	 */
	public static <T>  void  print(Class<T> cls,String str){
		System.out.println("["+cls.getSimpleName()+"|"+new Date()+"]:"+str);
	}	
	/**
	 * 得到webapps的路径
	 * @return
	 */
	public static String   getwebapps(){
		String binp= System.getProperty("user.dir");
		String replaceAll = binp.replaceAll("bin$", "webapps");
		if(replaceAll.lastIndexOf("webapps")==-1){
			replaceAll=System.getProperty("catalina.home")+ System.getProperty("file.separator")+"webapps";
		}
		return replaceAll;
	}	
	/**
	 * 包装 html
	 * @param content 内容
	 * @param title 标题
	 * @return 完整的html
	 */
	public static String appendContent(String content,String title){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<!DOCTYPE html><html>")
		.append("<head><title>")
		.append(title)
		.append("</title>")
		.append("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'/></head><body>")
		.append(content)
		.append("</body></html>");
		return stringBuffer.toString();
		
	}
	/**
	 * 以流的方式发送Post请求
	 * @return response文本
	 */
	public static String postByStream(String pathUrl,String requestParam) throws Exception{
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
			} 
			return sb.toString();
		} catch (Exception ex) { 
			ex.printStackTrace();
			return "";
		} 
	}
	
}
