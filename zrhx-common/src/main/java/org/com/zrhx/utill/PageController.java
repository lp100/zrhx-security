package org.com.zrhx.utill;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * 分页封装对象
 * @author gs
 *
 * @param <T>
 */
public class PageController<T>  implements Serializable{

	
	
	private static final long serialVersionUID = 1L;
	
	// 当前页中存放的记录,类型一般为List
	private Collection<? extends Object> data; 
	
	private Map mapData;

	// 总记录数
	private int totalCount;
	
	public PageController() {
		this(new ArrayList<T>(),0);
	}
	
	public PageController(Collection<? extends Object> data,int totalCount) {
		super();
		this.data = data;
		this.totalCount = totalCount;
	}
	
	public PageController(Map data,int totalCount) {
		super();
		this.mapData = data;
		this.totalCount = totalCount;
	}

	public Collection<? extends Object> getData() {
		return data;
	}

	public void setData(Collection<? extends Object> data) {
		this.data = data;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Map getMapData() {
		return mapData;
	}

	public void setMapData(Map mapData) {
		this.mapData = mapData;
	}
	

}
