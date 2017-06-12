package org.com.zrhx.service;
import com.github.pagehelper.PageInfo;
import org.com.zrhx.enity.BaseEntity;

import java.util.List;

/**
 * Created by gs on 2017/5/25.
 */

public interface   BaseService<T extends BaseEntity<T>> {

    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    public T findById(Object id);

    /**
     * 根据条件查询数据列表
     *
     * @param record
     * @return
     */
    public List<T> findListByWhere(T record);

    /**
     * 根据条件查询数据列表
     *
     * @param record
     * @return
     */
    public Integer findCount(T record);
    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param record
     * @return
     */
    public PageInfo<T>  findPageListByWhere(Integer pageNum, Integer pageSize, T record);

    /**
     * 新增数据，返回成功的条数
     * @param record
     * @param selectiveFlag ( 选择性)
     * @return
     */
    public boolean insert(T record,boolean selectiveFlag);


    /**
     * 修改数据，返回成功的条数
     *
     * @param record
     * @param selectiveFlag ( 选择性  为true时 修改数据，使用不为null的字段)
     * @return
     */
    public boolean update(T record ,boolean selectiveFlag);

    /**
     * 根据id删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteById(Object id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public boolean deleteByIds( List<T> ids);

    /**
     *  批量修改可用状态
     * @param ids 记录id
     * @param enabledFlag
     * @return
     */
   public boolean updateBatchFlag(T t,List ids, String enabledFlag);


}

