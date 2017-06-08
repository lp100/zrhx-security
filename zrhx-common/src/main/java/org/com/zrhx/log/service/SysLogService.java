package org.com.zrhx.log.service;

import org.com.zrhx.log.mapper.SysLogMapper;
import org.com.zrhx.log.model.SysLog;
import org.com.zrhx.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by gs on 2017/5/27.
 */
@Service("sysLogService")
public class SysLogService extends BaseServiceImpl<SysLog,SysLogMapper>{
}
