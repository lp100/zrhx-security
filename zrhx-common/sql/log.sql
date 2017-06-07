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
  `EnabledFlag` varchar(1) DEFAULT '0' COMMENT '可用状态',
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




INSERT INTO `sys_permission` (`PermissionID`, `ParentID`,`PermissionCode`, `PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`)
VALUES ('1', '0','sysManager', '系统管理', NULL, NULL, '0', 'fa fa-cog', 0);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`,`PermissionCode`, `PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('2', '1', 'userManager','管理员列表', 'sys/user.html', NULL, '1', 'fa fa-user', 1);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`,`PermissionCode`, `PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('3', '1', 'roleManager','角色管理', 'sys/role.html', NULL, '1', 'fa fa-user-secret', 2);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`,`PermissionCode`, `PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('4', '1', 'menuManager','菜单管理', 'sys/menu.html', NULL, '1', 'fa fa-th-list', 4);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('5', '1', 'SQL','SQL监控', 'druid/sql.html', NULL, '1', 'fa fa-bug', 5);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('15', '2', 'userSelect','查看', NULL, 'sys:user:list,sys:user:info', '2', NULL, 6);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('16', '2', 'useradd','新增', NULL, 'sys:user:save,sys:role:select', '2', NULL, 0);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`,`PermissionCode`, `PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('17', '2', 'userUpdate','修改', NULL, 'sys:user:update,sys:role:select', '2', NULL, 0);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`,`PermissionCode`, `PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('18', '2',  'userDelete','删除', NULL, 'sys:user:delete', '2', NULL, 0);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('19', '3', 'roleSelect','查看', NULL, 'sys:role:list,sys:role:info', '2', NULL, 0);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('20', '3', 'roleAdd','新增', NULL, 'sys:role:save,sys:menu:Description', '2', NULL, 0);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`,`PermissionCode`, `PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('21', '3', 'roleUpdate','修改', NULL, 'sys:role:update,sys:menu:Description', '2', NULL, 0);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('22', '3', 'roleDelete','删除', NULL, 'sys:role:delete', '2', NULL, 0);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('23', '4', 'menuSelect','查看', NULL, 'sys:menu:list,sys:menu:info', '2', NULL, 0);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('24', '4', 'menuAdd','新增', NULL, 'sys:menu:save,sys:menu:select', '2', NULL, 0);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('25', '4', 'menuUpdate','修改', NULL, 'sys:menu:update,sys:menu:select', '2', NULL, 0);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('26', '4', 'menuDelete','删除', NULL, 'sys:menu:delete', '2', NULL, 0);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('27', '1', 'configManager','参数管理', 'sys/config.html', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', 'fa fa-sun-o', 6);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('29', '1', 'logManager','系统日志', 'sys/log.html', 'sys:log:list', '1', 'fa fa-file-text-o', 7);
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('30', '1', 'oss','文件上传', 'sys/oss.html', 'sys:oss:all', '1', 'fa fa-file-image-o', 8);

INSERT INTO `sys_permission` (`PermissionID`, `ParentID`,`PermissionCode`, `PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('6', '1', 'scheduleManager','定时任务', 'sys/schedule.html', NULL, '1', 'fa fa-tasks', '5');
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('7', '6', 'scheduleSelect','查看', NULL, 'sys:schedule:list,sys:schedule:info', '2', NULL, '0');
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('8', '6', 'scheduleAdd','新增', NULL, 'sys:schedule:save', '2', NULL, '0');
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`,`PermissionCode`, `PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('9', '6', 'scheduleUpdate','修改', NULL, 'sys:schedule:update', '2', NULL, '0');
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('10', '6', 'scheduleDelete','删除', NULL, 'sys:schedule:delete', '2', NULL, '0');
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('11', '6', 'schedulePause', '暂停', NULL, 'sys:schedule:pause', '2', NULL, '0');
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`, `PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('12', '6', 'scheduleResume', '恢复', NULL, 'sys:schedule:resume', '2', NULL, '0');
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('13', '6', 'scheduleRun','立即执行', NULL, 'sys:schedule:run', '2', NULL, '0');
INSERT INTO `sys_permission` (`PermissionID`, `ParentID`, `PermissionCode`,`PermissionName`, `url`, `Description`, `PermissionType`, `Memo`, `PermissionSN`) VALUES ('14', '6', 'scheduleLogManager', '日志列表', NULL, 'sys:schedule:log', '2', NULL, '0');