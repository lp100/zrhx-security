package org.com.zrhx.mapper;

import org.com.zrhx.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * @Title: SysGeneratorMapper
 * @Description: 代码生成器
 * @author: gs
 * @date: 2017/5/26 10:28
 */
@MyBatisDao
public interface SysGeneratorMapper {
	
	List<Map<String, Object>> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	Map<String, String> queryTable(String tableName);
	
	List<Map<String, String>> queryColumns(String tableName);
}
