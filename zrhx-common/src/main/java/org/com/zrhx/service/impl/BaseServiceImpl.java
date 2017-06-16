package org.com.zrhx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.shiro.SecurityUtils;
import org.com.zrhx.mapper.BaseDao;
import org.com.zrhx.enity.BaseEntity;
import org.com.zrhx.service.BaseService;
import org.com.zrhx.utill.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Transactional(readOnly = true)
@Service
public abstract class BaseServiceImpl<T extends BaseEntity<T> ,D extends BaseDao<T>>implements BaseService<T> {

	@Autowired
	protected D mapper;

	@Transactional(readOnly = true)
	@Override
	public T findById(Object id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<T> findListByWhere(T record) {
		return mapper.queryList(record);
	}


	@Override
	public PageInfo<T> findPageListByWhere(Integer pageNum, Integer pageSize, T record) {
		// 设置分页条件
		PageHelper.startPage(pageNum, pageSize);
		List<T> list = this.findListByWhere(record);
		return new PageInfo<T>(list);
	}

	@Transactional(readOnly = false)
	@Override
	public boolean insert(T record, boolean selectiveFlag) {
		record.setCreateDate(new Date());
		record.setUpdateDate(new Date());
		if (StringUtils.isEmpty(record.getEnabledFlag())){
			record.setEnabledFlag("0");
		}
	  if (selectiveFlag){
	  	return  returnResult(mapper.insertSelective(record));
	  }else{
		  return  returnResult(mapper.insert(record));
	  }
	}

	@Transactional(readOnly = false)
	@Override
	public boolean update(T record, boolean selectiveFlag) {
		if (StringUtils.isEmpty(record.getEnabledFlag())){
			record.setEnabledFlag("0");
		}
		if (selectiveFlag){
			record.setUpdateDate(new Date());
			return  returnResult(mapper.updateByPrimaryKeySelective(record));
		}else{
			record.setUpdateDate(new Date());
			return  returnResult(mapper.updateByPrimaryKey(record));
		}
	}

	@Transactional(readOnly = false)
	@Override
	public boolean deleteById(Object id) {
		return  returnResult(mapper.deleteByPrimaryKey(id));
	}

	@Transactional(readOnly = false)
	@Override
	public boolean deleteByIds( List<T> values) {
		return returnResult(mapper.deleteBatch(values));
	}

	@Override
	public Integer findCount(T record) {
		return mapper.queryTotal(record);
	}

	@Override
	public boolean updateBatchFlag(T t,List ids, String enabledFlag) {
		if (null==t){
			throw  new RRException("对象为空");
		}
		t.setEnabledFlag(enabledFlag);
		t.setList(ids);
		return returnResult(mapper.updateBatchFlag(t));
	}
	/**
	 *
	 * @Title: returnResult
	 *
	 * @Description: TODO(根据操作数据库返回的个 判断是否操作成功)
	 *
	 * @param @param count
	 * @param @return
	 *
	 * @return boolean 返回类型
	 *
	 * @throws
	 *
	 */
	protected boolean returnResult(int count) {
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}
}
