/**
 * 
 */
package org.com.zrhx.utill.excel;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class ExcelInfoBean implements Serializable {
	
	private static final long serialVersionUID = -4490309228600122638L;

	private String headerTitle;//Excel报表标题
	
	@SuppressWarnings("rawtypes")
	private List dataList;//除标题外的数据list，list里包含的还是一组list
	
	@SuppressWarnings("rawtypes")
	private List rangeList;//合并的单元格座标
	
	private int celSize;//最大列数

	
	private String titleSize;//标题大小
	
    private int headerCol;//表头所在行

	public int getHeaderCol() {
		return headerCol;
	}

	public void setHeaderCol(int headerCol) {
		this.headerCol = headerCol;
	}

	public String getTitleSize() {
		return titleSize;
	}

	public void setTitleSize(String titleSize) {
		this.titleSize = titleSize;
	}

	public int getCelSize() {
		return celSize;
	}

	public void setCelSize(int celSize) {
		this.celSize = celSize;
	}

	@SuppressWarnings("rawtypes")
	public List getDataList() {
		return dataList;
	}

	@SuppressWarnings("rawtypes")
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public String getHeaderTitle() {
		return headerTitle;
	}

	public void setHeaderTitle(String headerTitle) {
		this.headerTitle = headerTitle;
	}

	@SuppressWarnings("rawtypes")
	public List getRangeList() {
		return rangeList;
	}

	@SuppressWarnings("rawtypes")
	public void setRangeList(List rangeList) {
		this.rangeList = rangeList;
	}
	
}
