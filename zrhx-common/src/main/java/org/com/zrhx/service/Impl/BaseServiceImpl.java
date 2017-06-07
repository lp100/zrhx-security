package org.com.zrhx.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.com.zrhx.mapper.BaseDao;
import org.com.zrhx.enity.BaseEntity;
import org.com.zrhx.service.BaseService;
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
	public Integer insert(T record, boolean selectiveFlag) {
		record.setCreateDate(new Date());
		record.setUpdateDate(new Date());
		if (StringUtils.isEmpty(record.getEnabledFlag())){
			record.setEnabledFlag("0");
		}
	  if (selectiveFlag){
	  	return  mapper.insertSelective(record);
	  }else{
		  return  mapper.insert(record);
	  }
	}

	@Transactional(readOnly = false)
	@Override
	public Integer update(T record, boolean selectiveFlag) {
		if (StringUtils.isEmpty(record.getEnabledFlag())){
			record.setEnabledFlag("0");
		}
		if (selectiveFlag){
			record.setUpdateDate(new Date());
			return  mapper.updateByPrimaryKeySelective(record);
		}else{
			record.setUpdateDate(new Date());
			return  mapper.updateByPrimaryKey(record);
		}
	}

	@Transactional(readOnly = false)
	@Override
	public Integer deleteById(Object id) {
		return  mapper.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = false)
	@Override
	public Integer deleteByIds( List<T> values) {
		return mapper.deleteBatch(values);
	}

	@Override
	public Integer findCount(T record) {
		return mapper.queryTotal(record);
	}
}
