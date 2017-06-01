package org.com.zrhx.service.impl;

import org.com.zrhx.mapper.SysPermissionMapper;
import org.com.zrhx.model.SysPermission;
import org.com.zrhx.service.Impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gs on 2017/6/1.
 */

@Service("sysPermissionService")
public class SysPermissionService  extends BaseServiceImpl<SysPermission,SysPermissionMapper> implements org.com.zrhx.service.SysPermissionService {
    @Override
    public List<SysPermission> queryListParentId(Long parentId, List<Long> menuIdList) {
        return null;
    }

    @Override
    public List<SysPermission> queryNotButtonList() {
        return null;
    }

    @Override
    public List<SysPermission> queryUserList(Long userId) {
        return null;
    }
}
