package org.com.zrhx.mapper;
import org.com.zrhx.annotation.MyBatisDao;
import java.util.List;


/**
 * @Title: BaseDao
 * @Description:  基础Dao(还需在XML文件里，有对应的SQL语句)
 * @author: gs
 * @date: 2017/5/25 17:23
 */

public interface BaseDao<T> {

	/**
	 * 根据主建删除
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Object id);

	/**
	 * 添加
	 * @param record
	 * @return
	 */
	int insert(T record);

	/**
	 * 添加  传空的不插入
	 * @param record
	 * @return
	 */
	int insertSelective(T record);

	/**
	 * 根据主键修改
	 * @param id
	 * @return
	 */
	T selectByPrimaryKey(Object id);

	/**
	 *修改 传空的不修改
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(T record);

	/**
	 *修改
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(T record);

	/**
	 * 批量删除
 	 * @param list
	 * @return
	 */
	int deleteBatch(List<T> list);

	/**
	 * 批量保存，返回保存的条数
	 * @param list
	 * @return
	 */
	 int insertList(List<T> list);
	/**
	 * 条件查询
	 * @param record
	 * @return
	 */
	List<T> queryList(T record);

	/**
	 * 查询记录数
	 * @param record
	 * @return
	 */
	int queryTotal(T record);

}
