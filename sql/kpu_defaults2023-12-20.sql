/*
 Navicat Premium Data Transfer

 Source Server         : mac
 Source Server Type    : MySQL
 Source Server Version : 80027 (8.0.27)
 Source Host           : localhost:3306
 Source Schema         : kpu_defaults

 Target Server Type    : MySQL
 Target Server Version : 80027 (8.0.27)
 File Encoding         : 65001

 Date: 20/12/2023 08:04:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_interface
-- ----------------------------
DROP TABLE IF EXISTS `c_interface`;
CREATE TABLE `c_interface` (
  `id` bigint NOT NULL,
  `code` varchar(255) NOT NULL COMMENT '接口编码',
  `name` varchar(255) NOT NULL COMMENT '接口名称',
  `exec_mode` char(2) DEFAULT '1' COMMENT '执行方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.INTERFACE_EXEC_MODE)[01-实现类 02-脚本]',
  `script` text COMMENT '实现脚本',
  `impl_class` varchar(255) DEFAULT NULL COMMENT '实现类',
  `state` bit(1) DEFAULT b'1' COMMENT '状态',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='接口';

-- ----------------------------
-- Records of c_interface
-- ----------------------------
BEGIN;
INSERT INTO `c_interface` (`id`, `code`, `name`, `exec_mode`, `script`, `impl_class`, `state`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734581845728690176, 'ALI_SMS', '阿里短信', '01', NULL, 'aliSmsMsgStrategyImpl', b'1', '2023-12-12 22:31:54', 2, '2023-12-12 22:31:54', 2);
INSERT INTO `c_interface` (`id`, `code`, `name`, `exec_mode`, `script`, `impl_class`, `state`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734582150713311232, 'TENCENT_MAIL', '腾讯邮件', '01', NULL, 'tencentMailMsgStrategyImpl', b'1', '2023-12-12 22:33:06', 2, '2023-12-12 22:33:06', 2);
INSERT INTO `c_interface` (`id`, `code`, `name`, `exec_mode`, `script`, `impl_class`, `state`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734584677886328832, 'NOTICE', '站内信', '01', NULL, 'noticeMsgStrategyImpl', b'1', '2023-12-12 22:43:09', 2, '2023-12-12 22:43:09', 2);
COMMIT;

-- ----------------------------
-- Table structure for c_interface_log
-- ----------------------------
DROP TABLE IF EXISTS `c_interface_log`;
CREATE TABLE `c_interface_log` (
  `id` bigint NOT NULL COMMENT 'ID',
  `interface_id` bigint NOT NULL COMMENT '接口ID',
  `name` varchar(255) NOT NULL COMMENT '接口名称',
  `success_count` int DEFAULT NULL COMMENT '成功次数',
  `fail_count` int DEFAULT NULL COMMENT '失败次数',
  `last_exec_time` datetime DEFAULT NULL COMMENT '最后执行时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='接口执行日志';

-- ----------------------------
-- Records of c_interface_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_interface_logging
-- ----------------------------
DROP TABLE IF EXISTS `c_interface_logging`;
CREATE TABLE `c_interface_logging` (
  `id` bigint NOT NULL COMMENT 'ID',
  `log_id` bigint NOT NULL COMMENT '接口日志ID',
  `biz_id` bigint DEFAULT NULL COMMENT '业务ID',
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求参数',
  `result` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '接口返回',
  `status` bit(1) DEFAULT NULL COMMENT '执行状态',
  `error_msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '异常信息',
  `exec_time` datetime DEFAULT NULL COMMENT '执行时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='接口执行日志记录';

-- ----------------------------
-- Records of c_interface_logging
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_interface_property
-- ----------------------------
DROP TABLE IF EXISTS `c_interface_property`;
CREATE TABLE `c_interface_property` (
  `id` bigint NOT NULL COMMENT 'ID',
  `interface_id` bigint NOT NULL COMMENT '接口ID',
  `name` varchar(255) NOT NULL COMMENT '参数名称',
  `key_` varchar(255) NOT NULL COMMENT '参数键',
  `value` varchar(255) NOT NULL COMMENT '参数值',
  `sort_value` int DEFAULT '0' COMMENT '排序号',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_time` datetime NOT NULL COMMENT '修改时间',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='接口属性';

-- ----------------------------
-- Records of c_interface_property
-- ----------------------------
BEGIN;
INSERT INTO `c_interface_property` (`id`, `interface_id`, `name`, `key_`, `value`, `sort_value`, `remarks`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734583523018276864, 1734581845728690176, '域名', 'endpoint', 'dysmsapi.aliyuncs.com', 0, '1', '2023-12-12 22:38:34', 2, '2023-12-12 22:38:34', 2);
INSERT INTO `c_interface_property` (`id`, `interface_id`, `name`, `key_`, `value`, `sort_value`, `remarks`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734583616408649728, 1734581845728690176, '地域ID', 'regionId', 'Dysmsapi', 0, 'https://help.aliyun.com/document_detail/419270.html 1', '2023-12-12 22:38:56', 2, '2023-12-12 22:38:56', 2);
INSERT INTO `c_interface_property` (`id`, `interface_id`, `name`, `key_`, `value`, `sort_value`, `remarks`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734583828833370112, 1734581845728690176, '发送使用签名', 'signName', '湖南交投物联', 0, '', '2023-12-12 22:39:46', 2, '2023-12-12 22:39:46', 2);
INSERT INTO `c_interface_property` (`id`, `interface_id`, `name`, `key_`, `value`, `sort_value`, `remarks`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734583894474227712, 1734581845728690176, 'Secret Access Key', 'accessKeySecret', 'W1u6jbLqhOJlCUWkhgyPPSYxdpRdOg', 0, '发送账号安全认证的Secret Access Key', '2023-12-12 22:40:02', 2, '2023-12-12 22:40:02', 2);
INSERT INTO `c_interface_property` (`id`, `interface_id`, `name`, `key_`, `value`, `sort_value`, `remarks`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734583946668146688, 1734581845728690176, 'Access Key ID', 'accessKeyId', 'LTAI5tCSgjrYqwoYv4GY7V9c', 0, '发送账号安全认证的Access Key ID', '2023-12-12 22:40:15', 2, '2023-12-12 22:40:15', 2);
INSERT INTO `c_interface_property` (`id`, `interface_id`, `name`, `key_`, `value`, `sort_value`, `remarks`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734584036589830144, 1734581845728690176, '是否调试', 'debug', '0', 0, '1-不发短信 0-发短信', '2023-12-12 22:40:36', 2, '2023-12-12 22:40:36', 2);
COMMIT;

-- ----------------------------
-- Table structure for c_msg
-- ----------------------------
DROP TABLE IF EXISTS `c_msg`;
CREATE TABLE `c_msg` (
  `id` bigint NOT NULL COMMENT 'ID',
  `biz_id` varchar(64) DEFAULT '' COMMENT '业务ID',
  `biz_type` varchar(64) DEFAULT '' COMMENT '业务类型 \n#MsgBizType{USER_LOCK:账号锁定;USER_REG:账号申请;WORK_APPROVAL:考勤审批;}',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消息类型 \n#MsgType{WAIT:待办;NOTIFY:通知;PUBLICITY:公告;WARN:预警;}',
  `template_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '消息模板',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '标题',
  `content` text COMMENT '内容',
  `author` varchar(50) DEFAULT '' COMMENT '发布人',
  `channel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '发送渠道,可用值:APP,SERVICE',
  `params` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '参数',
  `status` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '执行状态',
  `remind_model` char(2) DEFAULT NULL COMMENT '提醒方式',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `created_org_id` bigint DEFAULT NULL COMMENT '部门人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人id',
  `updated_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `updated_by` bigint DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='消息表';

-- ----------------------------
-- Records of c_msg
-- ----------------------------
BEGIN;
INSERT INTO `c_msg` (`id`, `biz_id`, `biz_type`, `type`, `template_code`, `title`, `content`, `author`, `channel`, `params`, `status`, `remind_model`, `send_time`, `created_org_id`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735956824080777216, '', '', '01', 'MOBILE_LOGIN', '', NULL, '', '', '[{\"key\":\"code\",\"value\":\"17674088798\"}]', '02', NULL, NULL, NULL, '2023-12-16 17:35:34', 2, '2023-12-16 17:35:34', 2);
INSERT INTO `c_msg` (`id`, `biz_id`, `biz_type`, `type`, `template_code`, `title`, `content`, `author`, `channel`, `params`, `status`, `remind_model`, `send_time`, `created_org_id`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735956973096009728, '', '', '01', 'MOBILE_LOGIN', '', NULL, '', '', '[{\"key\":\"code\",\"value\":\"17674088798\"}]', '02', NULL, NULL, NULL, '2023-12-16 17:36:10', 2, '2023-12-16 17:36:10', 2);
INSERT INTO `c_msg` (`id`, `biz_id`, `biz_type`, `type`, `template_code`, `title`, `content`, `author`, `channel`, `params`, `status`, `remind_model`, `send_time`, `created_org_id`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735960150709108736, '', '', '01', 'MOBILE_LOGIN', '', NULL, '', '', '[{\"key\":\"code\",\"value\":\"17674088798\"}]', '02', NULL, NULL, NULL, '2023-12-16 17:48:47', 2, '2023-12-16 17:48:47', 2);
INSERT INTO `c_msg` (`id`, `biz_id`, `biz_type`, `type`, `template_code`, `title`, `content`, `author`, `channel`, `params`, `status`, `remind_model`, `send_time`, `created_org_id`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735960763878604800, '', '', '01', 'MOBILE_LOGIN', '', NULL, '', '', '[{\"key\":\"code\",\"value\":\"17674088798\"}]', '02', NULL, NULL, NULL, '2023-12-16 17:51:13', 2, '2023-12-16 17:51:13', 2);
INSERT INTO `c_msg` (`id`, `biz_id`, `biz_type`, `type`, `template_code`, `title`, `content`, `author`, `channel`, `params`, `status`, `remind_model`, `send_time`, `created_org_id`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735960957932273664, '', '', '01', 'MOBILE_LOGIN', '', NULL, '', '', '[{\"key\":\"code\",\"value\":\"17674088798\"}]', '02', NULL, NULL, NULL, '2023-12-16 17:52:00', 2, '2023-12-16 17:52:00', 2);
INSERT INTO `c_msg` (`id`, `biz_id`, `biz_type`, `type`, `template_code`, `title`, `content`, `author`, `channel`, `params`, `status`, `remind_model`, `send_time`, `created_org_id`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735961688127045632, '', '', '01', 'MOBILE_LOGIN', '', NULL, '', '', '[{\"key\":\"code\",\"value\":\"17674088798\"}]', '02', NULL, NULL, NULL, '2023-12-16 17:54:54', 2, '2023-12-16 17:54:54', 2);
INSERT INTO `c_msg` (`id`, `biz_id`, `biz_type`, `type`, `template_code`, `title`, `content`, `author`, `channel`, `params`, `status`, `remind_model`, `send_time`, `created_org_id`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735962132903624704, '', '', '01', 'MOBILE_LOGIN', '', NULL, '', '', '[{\"key\":\"code\",\"value\":\"17674088798\"}]', '02', NULL, NULL, NULL, '2023-12-16 17:56:40', 2, '2023-12-16 17:56:40', 2);
INSERT INTO `c_msg` (`id`, `biz_id`, `biz_type`, `type`, `template_code`, `title`, `content`, `author`, `channel`, `params`, `status`, `remind_model`, `send_time`, `created_org_id`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735962303511134208, '', '', '01', 'MOBILE_LOGIN', '', NULL, '', '', '[{\"key\":\"code\",\"value\":\"176740173937\"}]', '02', NULL, NULL, NULL, '2023-12-16 17:57:20', 2, '2023-12-16 17:57:20', 2);
INSERT INTO `c_msg` (`id`, `biz_id`, `biz_type`, `type`, `template_code`, `title`, `content`, `author`, `channel`, `params`, `status`, `remind_model`, `send_time`, `created_org_id`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735962606759313408, '', '', '01', 'MOBILE_LOGIN', '', NULL, '', '', '[{\"key\":\"code\",\"value\":\"176740173937\"}]', '02', NULL, NULL, NULL, '2023-12-16 17:58:33', 2, '2023-12-16 17:58:33', 2);
INSERT INTO `c_msg` (`id`, `biz_id`, `biz_type`, `type`, `template_code`, `title`, `content`, `author`, `channel`, `params`, `status`, `remind_model`, `send_time`, `created_org_id`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735963025325686784, '', '', '01', 'MOBILE_LOGIN', '', NULL, '', '', '[{\"key\":\"code\",\"value\":\"17674017937\"}]', '02', NULL, NULL, NULL, '2023-12-16 18:00:13', 2, '2023-12-16 18:00:13', 2);
INSERT INTO `c_msg` (`id`, `biz_id`, `biz_type`, `type`, `template_code`, `title`, `content`, `author`, `channel`, `params`, `status`, `remind_model`, `send_time`, `created_org_id`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735963160348721152, '', '', '01', 'MOBILE_LOGIN', '', NULL, '', '', '[{\"key\":\"code\",\"value\":\"17674017937\"}]', '02', NULL, NULL, NULL, '2023-12-16 18:00:45', 2, '2023-12-16 18:00:45', 2);
INSERT INTO `c_msg` (`id`, `biz_id`, `biz_type`, `type`, `template_code`, `title`, `content`, `author`, `channel`, `params`, `status`, `remind_model`, `send_time`, `created_org_id`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735963454046470144, '', '', '01', 'MOBILE_LOGIN', '', NULL, '', '', '[{\"key\":\"code\",\"value\":\"17674017937\"}]', '02', NULL, NULL, NULL, '2023-12-16 18:01:55', 2, '2023-12-16 18:01:55', 2);
INSERT INTO `c_msg` (`id`, `biz_id`, `biz_type`, `type`, `template_code`, `title`, `content`, `author`, `channel`, `params`, `status`, `remind_model`, `send_time`, `created_org_id`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735963532526092288, '', '', '01', 'MOBILE_LOGIN', '', NULL, '', '', '[{\"key\":\"code\",\"value\":\"17674017937\"}]', '02', NULL, NULL, NULL, '2023-12-16 18:02:13', 2, '2023-12-16 18:02:13', 2);
INSERT INTO `c_msg` (`id`, `biz_id`, `biz_type`, `type`, `template_code`, `title`, `content`, `author`, `channel`, `params`, `status`, `remind_model`, `send_time`, `created_org_id`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735963809014611968, '', '', '01', 'MOBILE_LOGIN', '', NULL, '', '', '[{\"key\":\"code\",\"value\":\"17674017937\"}]', '02', NULL, NULL, NULL, '2023-12-16 18:03:19', 2, '2023-12-16 18:03:19', 2);
INSERT INTO `c_msg` (`id`, `biz_id`, `biz_type`, `type`, `template_code`, `title`, `content`, `author`, `channel`, `params`, `status`, `remind_model`, `send_time`, `created_org_id`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735964112996794368, '', '', '01', 'MOBILE_LOGIN', '', NULL, '', '', '[{\"key\":\"code\",\"value\":\"123125\"}]', '02', NULL, NULL, NULL, '2023-12-16 18:04:32', 2, '2023-12-16 18:04:32', 2);
COMMIT;

-- ----------------------------
-- Table structure for c_msg_recipient
-- ----------------------------
DROP TABLE IF EXISTS `c_msg_recipient`;
CREATE TABLE `c_msg_recipient` (
  `id` bigint NOT NULL COMMENT 'ID',
  `msg_id` bigint NOT NULL COMMENT '任务ID',
  `ext` varchar(255) DEFAULT NULL COMMENT '扩展信息',
  `recipient` varchar(255) DEFAULT NULL COMMENT '接收人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='消息接收人';

-- ----------------------------
-- Records of c_msg_recipient
-- ----------------------------
BEGIN;
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734599305706078208, 1734599051602558976, NULL, '17674088798', '2023-12-12 23:41:16', 2, '2023-12-12 23:41:16', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734600309574991872, 1734600309549826048, NULL, '17674088798', '2023-12-12 23:45:16', 2, '2023-12-12 23:45:16', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734600660487241728, 1734600660470464512, NULL, '17674088798', '2023-12-12 23:46:39', 2, '2023-12-12 23:46:39', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734601067536056320, 1734601067435393024, NULL, '17674088798', '2023-12-12 23:48:16', 2, '2023-12-12 23:48:16', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734601186578792448, 1734601186562015232, NULL, '17674088798', '2023-12-12 23:48:45', 2, '2023-12-12 23:48:45', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734601339217903616, 1734601339201126400, NULL, '17674088798', '2023-12-12 23:49:21', 2, '2023-12-12 23:49:21', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734605473627242496, 1734605473568522240, NULL, '17674088798', '2023-12-13 00:05:47', 2, '2023-12-13 00:05:47', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734605913026723840, 1734605913014140928, NULL, '17674088798', '2023-12-13 00:07:32', 2, '2023-12-13 00:07:32', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734606531040641024, 1734606531023863808, NULL, '17674088798', '2023-12-13 00:09:59', 2, '2023-12-13 00:09:59', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734606831059206144, 1734606831046623232, NULL, '17674088798', '2023-12-13 00:11:11', 2, '2023-12-13 00:11:11', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734606860150898688, 1734606860138315776, NULL, '17674088798', '2023-12-13 00:11:18', 2, '2023-12-13 00:11:18', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734606877439819776, 1734606877418848256, NULL, '17674088798', '2023-12-13 00:11:22', 2, '2023-12-13 00:11:22', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734607226976337920, 1734607226963755008, NULL, '17674088798', '2023-12-13 00:12:45', 2, '2023-12-13 00:12:45', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734607643948875776, 1734607643894349824, NULL, '17674088798', '2023-12-13 00:14:24', 2, '2023-12-13 00:14:24', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734607796982251520, 1734607796965474304, NULL, '17674088798', '2023-12-13 00:15:01', 2, '2023-12-13 00:15:01', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734608720014344192, 1734608720001761280, NULL, '17674088798', '2023-12-13 00:18:41', 2, '2023-12-13 00:18:41', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734608970296852480, 1734608970284269568, NULL, '17674088798', '2023-12-13 00:19:41', 2, '2023-12-13 00:19:41', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735925345170751488, 1735925345120419840, '', '17674017937', '2023-12-16 15:30:29', 2, '2023-12-16 15:30:29', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735925377978597376, 1735925377961820160, '', '17674017937', '2023-12-16 15:30:37', 2, '2023-12-16 15:30:37', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735926090704093184, 1735926090687315968, '', '17674017937', '2023-12-16 15:33:27', 2, '2023-12-16 15:33:27', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735937993027354624, 1735937992960245760, NULL, '17674088798', '2023-12-16 16:20:44', 2, '2023-12-16 16:20:44', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735938496683573248, 1735938496587104256, NULL, '17674088798', '2023-12-16 16:22:44', 2, '2023-12-16 16:22:44', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735956836219092992, 1735956824080777216, NULL, '17674088798', '2023-12-16 17:35:37', 2, '2023-12-16 17:35:37', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735956973112786944, 1735956973096009728, NULL, '17674088798', '2023-12-16 17:36:10', 2, '2023-12-16 17:36:10', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735960150805577728, 1735960150709108736, NULL, '17674088798', '2023-12-16 17:48:47', 2, '2023-12-16 17:48:47', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735960763895382016, 1735960763878604800, NULL, '17674088798', '2023-12-16 17:51:13', 2, '2023-12-16 17:51:13', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735960957949050880, 1735960957932273664, NULL, '17674088798', '2023-12-16 17:52:00', 2, '2023-12-16 17:52:00', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735961688139628544, 1735961688127045632, NULL, '17674088798', '2023-12-16 17:54:54', 2, '2023-12-16 17:54:54', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735962132962344960, 1735962132903624704, NULL, '17674088798', '2023-12-16 17:56:40', 2, '2023-12-16 17:56:40', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735962303527911424, 1735962303511134208, NULL, '176740173937', '2023-12-16 17:57:20', 2, '2023-12-16 17:57:20', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735962606771896320, 1735962606759313408, NULL, '176740173937', '2023-12-16 17:58:33', 2, '2023-12-16 17:58:33', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735963025338269696, 1735963025325686784, NULL, '17674017937', '2023-12-16 18:00:13', 2, '2023-12-16 18:00:13', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735963160365498368, 1735963160348721152, NULL, '17674017937', '2023-12-16 18:00:45', 2, '2023-12-16 18:00:45', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735963454059053056, 1735963454046470144, NULL, '17674017937', '2023-12-16 18:01:55', 2, '2023-12-16 18:01:55', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735963532538675200, 1735963532526092288, NULL, '17674017937', '2023-12-16 18:02:13', 2, '2023-12-16 18:02:13', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735963809035583488, 1735963809014611968, NULL, '17674017937', '2023-12-16 18:03:19', 2, '2023-12-16 18:03:19', 2);
INSERT INTO `c_msg_recipient` (`id`, `msg_id`, `ext`, `recipient`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1735964113009377280, 1735964112996794368, NULL, '17674017937', '2023-12-16 18:04:32', 2, '2023-12-16 18:04:32', 2);
COMMIT;

-- ----------------------------
-- Table structure for c_msg_template
-- ----------------------------
DROP TABLE IF EXISTS `c_msg_template`;
CREATE TABLE `c_msg_template` (
  `id` bigint NOT NULL COMMENT 'ID',
  `interface_id` bigint NOT NULL COMMENT '接口ID',
  `type` char(2) NOT NULL COMMENT '消息类型;[01-短信 02-邮件 03-站内信];@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_TEMPLATE_TYPE)',
  `code` varchar(255) NOT NULL COMMENT '模板标识',
  `name` varchar(255) DEFAULT NULL COMMENT '模板名称',
  `state` bit(1) DEFAULT NULL COMMENT '状态',
  `template_code` varchar(255) DEFAULT NULL COMMENT '模板编码',
  `sign` varchar(255) DEFAULT NULL COMMENT '签名',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` longtext NOT NULL COMMENT '模板内容',
  `script` varchar(255) DEFAULT NULL COMMENT '脚本',
  `param` varchar(255) DEFAULT NULL COMMENT '模板参数',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `target_` char(2) DEFAULT NULL COMMENT '打开方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_TARGET)[01-页面 02-弹窗 03-新开窗口]',
  `auto_read` bit(1) DEFAULT b'1' COMMENT '自动已读',
  `remind_mode` char(2) DEFAULT NULL COMMENT '提醒方式;@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_REMIND_MODE)[01-待办 02-预警 03-提醒]',
  `url` varchar(255) DEFAULT NULL COMMENT '跳转地址',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='消息模板';

-- ----------------------------
-- Records of c_msg_template
-- ----------------------------
BEGIN;
INSERT INTO `c_msg_template` (`id`, `interface_id`, `type`, `code`, `name`, `state`, `template_code`, `sign`, `title`, `content`, `script`, `param`, `remarks`, `target_`, `auto_read`, `remind_mode`, `url`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES (1734585313923170304, 1734581845728690176, '01', 'MOBILE_LOGIN', '手机登录短信', b'1', 'SMS_287710508', '湖南交投物联', '', '本次验证码为：${code}', '', NULL, '', NULL, b'1', NULL, NULL, '2023-12-12 22:45:41', 2, '2023-12-12 22:45:41', 2);
COMMIT;

-- ----------------------------
-- Table structure for c_notice
-- ----------------------------
DROP TABLE IF EXISTS `c_notice`;
CREATE TABLE `c_notice` (
  `id` bigint NOT NULL COMMENT 'ID',
  `author` bigint DEFAULT NULL COMMENT '发布人',
  `auto_read` bit(1) DEFAULT NULL COMMENT '自动已读',
  `biz_id` bigint DEFAULT NULL COMMENT '业务ID',
  `biz_type` varchar(255) DEFAULT NULL COMMENT '业务类型',
  `content` text COMMENT '内容',
  `url` varchar(255) DEFAULT NULL COMMENT '处理地址',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `target` char(2) DEFAULT NULL COMMENT '打开方式',
  `remind_mode` char(2) DEFAULT NULL COMMENT '提醒方式',
  `recipient_id` int DEFAULT NULL COMMENT '接收人',
  `msg_id` bigint DEFAULT NULL COMMENT '消息ID',
  `read_time` datetime DEFAULT NULL COMMENT '读取时间',
  `is_read` bit(1) DEFAULT NULL COMMENT '是否已读',
  `is_handle` bit(1) DEFAULT NULL COMMENT '是否处理',
  `handle_time` datetime DEFAULT NULL COMMENT '处理时间',
  `created_org_id` bigint DEFAULT NULL COMMENT '所属组织',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='通知表';

-- ----------------------------
-- Records of c_notice
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
