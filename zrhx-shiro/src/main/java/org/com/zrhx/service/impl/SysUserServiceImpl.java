package org.com.zrhx.service.impl;

import org.com.zrhx.mapper.SysUserMapper;
import org.com.zrhx.model.SysUser;
import org.com.zrhx.service.Impl.BaseServiceImpl;
import org.com.zrhx.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**  
 * @Title: SysUserServiceImpl
 * @Description:  用户
 * @author: gs  
 * @date: 2017/5/26 11:00 
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser,SysUserMapper> implements SysUserService  {

    @Override
    public List<String> queryAllPerms(String userID) {
        return mapper.queryAllPerms(userID);
    }

    @Override
    public List<String> queryAllMenuId(String userID) {
        return mapper.queryAllMenuId(userID);
    }

    @Override
    public SysUser queryByLoginName(String loginName) {
        return mapper.findByLoginName(loginName);
    }

    @Override
    public int updatePassword(String userID, String password, String newPassword) {
        SysUser sysUser = new SysUser();
        sysUser.setUserID(userID);
        sysUser.setPassword(password);
        sysUser.setNewPassword(newPassword);
        return mapper.updatePassword(sysUser);
    }
}
