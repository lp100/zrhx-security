package org.com.zrhx.service.impl;

import org.com.zrhx.mapper.SysPermissionMapper;
import org.com.zrhx.model.SysPermission;
import org.com.zrhx.service.SysPermissionService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by gs on 2017/6/1.
 */

@Service("sysPermissionService")
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission,SysPermissionMapper> implements SysPermissionService {
    @Override
    public List<SysPermission> queryListParentId(String parentId, List<String> menuIdList) {
        return null;
    }

    @Override
    public List<SysPermission> queryNotButtonList() {
        return null;
    }

    @Override
    public List<SysPermission> queryUserList(String userId) {
        return mapper.queryUserList(userId);
    }
}
