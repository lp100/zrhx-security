package org.com.zrhx.utill;

import java.awt.AlphaComposite;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
public class ImageUtil {
	
	private static Logger logger = Logger.getLogger(ImageUtil.class);
	/**
	 *  图片添加水印
	 * @param pressText
	 * @param srcImageFile
	 * @param destImageFile
	 * @param x
	 * @param y
	 * @param alpha
	 * @param fontstyle
	 * @param fontsize
	 * @param colorr
	 * @param colorg
	 * @param colorb
	 * @return
	 */
	public static boolean  pressText(String pressText, String srcImageFile,
			String destImageFile, int x, int y, float alpha,String fontstyle,int fontsize,
			int colorr,int colorg,int colorb) {
		try {
			File img = new File(srcImageFile);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			// 开文字抗锯齿 去文字毛刺
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.drawImage(src, 0, 0, width, height, null);//设置图片的大小
			// 设置颜色
			g.setColor(new Color(colorr, colorg, colorb));
			// 设置 Font
			g.setFont(new Font(fontstyle, Font.BOLD, fontsize));//字体、字型、字号 
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			// 第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y) .
			g.drawString(pressText, x, y);
			g.dispose();
//			Thumbnails.of(image).scale(1f).outputQuality(0.25f).toFile(destImageFile);
			ImageIO.write((BufferedImage) image, "JPEG",
					new File(destImageFile));// 输出到文件流
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString(), e);
			return false;
		}
		
		return true;
	}
	/**
	 * 图片添加水印
	 * @param pressText 水印文字
	 * @param srcImageFile 源图片流
	 * @param x
	 * @param y
	 * @param alpha 透明度
	 * @param fontstyle 字体样式
	 * @param fontsize 字体大学
	 * @param colorr 字体颜色
	 * @param colorg
	 * @param colorb
	 * @return 图片流
	 */
	public static InputStream  pressText(String pressText, InputStream srcImageFile,
			int x, int y, float alpha,String fontstyle,int fontsize,
			int colorr,int colorg,int colorb) {
		try {
			Image src = ImageIO.read(srcImageFile);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			// 开文字抗锯齿 去文字毛刺
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.drawImage(src, 0, 0, width, height, null);//设置图片的大小
			// 设置颜色
			g.setColor(new Color(colorr, colorg, colorb));
			// 设置 Font
			g.setFont(new Font(fontstyle, Font.BOLD, fontsize));//字体、字型、字号 
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			// 第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y) .
			g.drawString(pressText, x, y);
			g.dispose();
//			BufferedImage asBufferedImage = Thumbnails.of(image).scale(1f).outputQuality(0.25f).asBufferedImage();
			ByteArrayOutputStream os = new ByteArrayOutputStream();  
			ImageIO.write(image, "gif", os);  
			InputStream is = new ByteArrayInputStream(os.toByteArray()); 
			return is;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString(), e);
			return null;
		}
		
		
	}
	
	
	public static void main(String[] args) {
		
		//字体大小  字体样式  字体颜色 x   y
//		ImageUtil.pressText("hell!\r\n你好！", "C:\\Users\\gs\\Desktop\\blue_glow1.png",  "C:\\Users\\gs\\Desktop\\blue_glow12.png", 300,400, 0.5f);
	}
}
