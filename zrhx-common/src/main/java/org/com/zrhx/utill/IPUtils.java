package org.com.zrhx.utill;

import com.alibaba.druid.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IP地址
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017年3月8日 下午12:57:02
 */
public class IPUtils {
	private static Logger logger = LoggerFactory.getLogger(IPUtils.class);

	/**
	 * 获取IP地址
	 * 
	 * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
    	String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
        	logger.error("IPUtils ERROR ", e);
        }
        
//        //使用代理，则获取第一个IP地址
//        if(StringUtils.isEmpty(ip) && ip.length() > 15) {
//			if(ip.indexOf(",") > 0) {
//				ip = ip.substring(0, ip.indexOf(","));
//			}
//		}
        
        return ip;
    }
    /**
     * 获取服务器ip
     * @param request
     * @return
     */
    public static  String  getLocalHost(javax.servlet.http.HttpServletRequest request){
        String reurl = request.getRequestURL().toString();
        if (reurl.indexOf("localhost") != -1
                || reurl.indexOf("127.0.0.1") != -1) {
            try {
                InetAddress addr = InetAddress.getLocalHost();
                return  addr.getHostAddress();
            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            String sServerIp = request.getLocalAddr();
            return  sServerIp;
        }
        return null;
    }

    /**
     * 获取上传的文件路径
     * @param request
     * @return
     */
    public static String  getFilePath(javax.servlet.http.HttpServletRequest request){
        String host = getLocalHost(request);
        int localPort = request.getLocalPort();

        return "http://"+host+":"+localPort;
    }

}
