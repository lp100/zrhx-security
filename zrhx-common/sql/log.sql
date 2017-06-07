DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `LogID` varchar(32) NOT NULL  COMMENT '主键ID',
  `AppName` varchar(36) NOT NULL COMMENT '所属应用',
  `LogType` int(11) DEFAULT NULL COMMENT '日志类型，0为操作日志，1为异常日志',
  `UserID` varchar(100) DEFAULT NULL COMMENT '访问者/请求者',
  `operation` varchar(100) DEFAULT NULL COMMENT '方法描述',
  `MethodName` varchar(100) DEFAULT NULL COMMENT '请求方法名称(全路径)',
  `RequestMethod` varchar(20) DEFAULT NULL COMMENT '请求方式(GET,POST,DELETE,PUT)',
  `RequestParams` varchar(500) DEFAULT NULL COMMENT '请求参数',
  `RequestIp` varchar(50) DEFAULT NULL COMMENT '访问者IP',
  `RequestUri` varchar(200) DEFAULT NULL COMMENT '请求URI',
  `ExceptionCode` varchar(100) DEFAULT NULL COMMENT '异常码',
  `ExceptionDetail` varchar(2000) DEFAULT NULL COMMENT '异常描述',
  `TimeConsuming` bigint(20) DEFAULT NULL COMMENT '请求耗时',
  `UserAgent` varchar(500) DEFAULT NULL COMMENT '客户端信息',
  `CreateTime` datetime DEFAULT NULL COMMENT '创建时间',
  `ModifyTime` datetime DEFAULT NULL COMMENT '更新时间',
  'EnabledFlag' varchar(1) DEFAULT NULL COMMENT '可用状态',
  PRIMARY KEY (`LogID`)
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8 COMMENT='系统日志表';



-- 定时任务
CREATE TABLE `schedule_job_config` (
  `JobID` varchar(32) NOT NULL  COMMENT '任务id',
  `BeanName` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `MethodName` varchar(100) DEFAULT NULL COMMENT '方法名',
  `Params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `CronExpression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `Status` tinyint(4) DEFAULT NULL COMMENT '任务状态',
  `Memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `CreateDate` datetime DEFAULT NULL COMMENT '创建时间',
  `CreateBy` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UpdateDate` datetime DEFAULT NULL COMMENT '修改时间',
  `UpdateBy` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`JobID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- 定时任务日志
CREATE TABLE `schedule_job_log` (
  `LogID` varchar(32) NOT NULL  COMMENT '任务日志id',
  `JobID` varchar(32) NOT NULL COMMENT '任务id',
  `BeanName` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `MethodName` varchar(100) DEFAULT NULL COMMENT '方法名',
  `Params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `Satus` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `Error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `Times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `CreateDate` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`LogID`),
  KEY `job_id` (`JobID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务日志';