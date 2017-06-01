package org.com.zrhx.mapper;

import org.com.zrhx.annotation.MyBatisDao;
import org.com.zrhx.model.SysUserRole;
@MyBatisDao
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String userroleID);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(String userroleID);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
}