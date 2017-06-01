package org.com.zrhx.utill.excel;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class ExportExcelUtil {

	private HttpServletResponse response;
	private String savepath;
	private String excelName;
	private String bigTitle;
	private String[][] titleMap;
	private List<Map<String, Object>> tableData;
	private File file;
	private int index = 0;
	private WriteExcelUtil excelUtil;

	/**
	 * 初始化参数,excelUtil赋值
	 * @param response
	 * @param savepath
	 * @param excelName
	 * @param bigTitle
	 * @param titleMap
	 * @param tableData
	 */
	public ExportExcelUtil(HttpServletResponse response, String savepath,
			String excelName, String bigTitle, String[][] titleMap,
			List<Map<String, Object>> tableData) {
		super();
		this.response = response;
		this.savepath = savepath;
		this.excelName = excelName;
		this.titleMap = titleMap;
		this.tableData = tableData;
		this.bigTitle = bigTitle;
		try {
			excelUtil = new WriteExcelUtil(response, 0, 0, "Sheet1", savepath,
					excelName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 初始化参数,excelUtil赋值
	 * @param response
	 * @param savepath
	 * @param excelName
	 */
	public ExportExcelUtil(HttpServletResponse response, String savepath,
			String excelName) {
		this.response = response;
		this.savepath = savepath;
		this.excelName = excelName;
		try {
			excelUtil = new WriteExcelUtil(response, 0, 0, "Sheet1", savepath,
					excelName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getBigTitle() {
		return bigTitle;
	}

	public void setBigTitle(String bigTitle) {
		this.bigTitle = bigTitle;
	}

	public String[][] getTitleMap() {
		return titleMap;
	}

	public void setTitleMap(String[][] titleMap) {
		this.titleMap = titleMap;
	}

	public List<Map<String, Object>> getTableData() {
		return tableData;
	}

	public void setTableData(List<Map<String, Object>> tableData) {
		this.tableData = tableData;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * 常用一次生成excel
	 * @return
	 */
	public String exportFile() {
		try {
			excelUtil = new WriteExcelUtil(response, 0, 0, "Sheet1", savepath,
					excelName);
			excelUtil.startWriteHead(0, index, titleMap[0].length - 1, index,
					bigTitle);// 生成表头
			index++;
			String[] titleKey = titleMap[0];
			String[] titleVal = titleMap[1];
			excelUtil.startWrite(0, index, titleVal, true);
			index++;
			for (Map<String, Object> tempMap : tableData) {
				String[] date = new String[titleKey.length];
				for (int i = 0; i < titleKey.length; i++) {
					date[i] = ((tempMap.get(titleKey[i]) == null) ? ""
							: tempMap.get(titleKey[i])) + "";
				}

				excelUtil.startWrite(0, index, date, false);
				index++;
			}

			if (file != null) {
				excelUtil.addPictureToExcel(file, index + 5, 0);
			}

			excelUtil.saveFile();
			return excelUtil.getFilePath();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 初始化,excelUtil赋值
	 * @Title: initExcelUtil
	 * @Description: 
	 * @param:
	 * @return:
	 * @author 张鑫磊
	 * @date 2016年3月7日 上午11:37:08
	 */
	public void initExcelUtil() {
		try {
			excelUtil = new WriteExcelUtil(response, 0, 0, "Sheet1", savepath,
					excelName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * excel写入数据
	 * @Title: writeTable
	 * @Description: 
	 * @param:
	 * @return:
	 * @author 张鑫磊
	 * @date 2016年3月7日 上午11:37:27
	 */
	public void writeTable() {
		try {
			if(excelUtil == null){
				initExcelUtil();
			}
			excelUtil.startWriteHead(0, index, titleMap[0].length - 1, index,
					bigTitle);
			index++;
			String[] titleKey = titleMap[0];
			String[] titleVal = titleMap[1];
			excelUtil.startWrite(0, index, titleVal, true);
			index++;
			
			if(tableData != null){
				for (Map<String, Object> tempMap : tableData) {
					String[] date = new String[titleKey.length];
					for (int i = 0; i < titleKey.length; i++) {
						date[i] = ((tempMap.get(titleKey[i]) == null) ? ""
								: tempMap.get(titleKey[i])) + "";
					}

					excelUtil.startWrite(0, index, date, false);
					index++;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 写入图片,空白5行
	 * @Title: writePic
	 * @Description: 
	 * @param:
	 * @return:
	 * @author 张鑫磊
	 * @date 2016年3月7日 上午11:38:16
	 */
	public void writePic() {
		if (file != null) {
			try {
				index = index + 5;
				excelUtil.addPictureToExcel(file, index, 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 文件保存
	 * @Title: saveFile
	 * @Description: 
	 * @param:
	 * @return:
	 * @author 张鑫磊
	 * @date 2016年3月7日 上午11:38:41
	 */
	public void saveFile() {
		excelUtil.saveFile();
	}
}
