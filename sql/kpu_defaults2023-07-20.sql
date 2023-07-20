/*
 Navicat Premium Data Transfer

 Source Server         : mac
 Source Server Type    : MySQL
 Source Server Version : 80027 (8.0.27)
 Source Host           : 127.0.0.1:3306
 Source Schema         : kpu_defaults

 Target Server Type    : MySQL
 Target Server Version : 80027 (8.0.27)
 File Encoding         : 65001

 Date: 20/07/2023 17:55:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_appendix
-- ----------------------------
DROP TABLE IF EXISTS `c_appendix`;
CREATE TABLE `c_appendix`
(
    `id`                 bigint       NOT NULL COMMENT 'ID',
    `biz_id`             bigint       NOT NULL DEFAULT '0' COMMENT '业务id',
    `biz_type`           varchar(255) NOT NULL DEFAULT '' COMMENT '业务类型',
    `file_type`          varchar(255)          DEFAULT NULL COMMENT '文件类型',
    `bucket`             varchar(255)          DEFAULT '' COMMENT '桶',
    `path`               varchar(255)          DEFAULT '' COMMENT '文件相对地址',
    `original_file_name` varchar(255)          DEFAULT '' COMMENT '原始文件名',
    `content_type`       varchar(255)          DEFAULT '' COMMENT '文件类型',
    `size_`              bigint                DEFAULT '0' COMMENT '大小',
    `create_time`        datetime     NOT NULL COMMENT '创建时间',
    `created_by`         bigint       NOT NULL COMMENT '创建人',
    `update_time`        datetime     NOT NULL COMMENT '最后修改时间',
    `updated_by`         bigint       NOT NULL COMMENT '最后修改人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='业务附件';

-- ----------------------------
-- Records of c_appendix
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_application
-- ----------------------------
DROP TABLE IF EXISTS `c_application`;
CREATE TABLE `c_application`
(
    `id`            bigint       NOT NULL COMMENT 'ID',
    `client_id`     varchar(24)                                                   DEFAULT '' COMMENT '客户端ID',
    `client_secret` varchar(32)                                                   DEFAULT '' COMMENT '客户端密码',
    `website`       varchar(100)                                                  DEFAULT '' COMMENT '官网',
    `name`          varchar(255) NOT NULL                                         DEFAULT '' COMMENT '应用名称',
    `icon`          varchar(255)                                                  DEFAULT '' COMMENT '应用图标',
    `app_type`      varchar(10)                                                   DEFAULT '' COMMENT '类型 \n#{SERVER:服务应用;APP:手机应用;PC:PC网页应用;WAP:手机网页应用}',
    `remarks`       varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '备注',
    `state`         bit(1)                                                        DEFAULT b'1' COMMENT '状态',
    `created_by`    bigint                                                        DEFAULT NULL COMMENT '创建人id',
    `create_time`   datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `updated_by`    bigint                                                        DEFAULT NULL COMMENT '更新人id',
    `update_time`   datetime                                                      DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_client_id` (`client_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='应用';

-- ----------------------------
-- Records of c_application
-- ----------------------------
BEGIN;
INSERT INTO `c_application` (`id`, `client_id`, `client_secret`, `website`, `name`, `icon`, `app_type`, `remarks`,
                             `state`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1, 'kpu_web', 'kpu_web_secret', 'https://lmx.top', 'kpu快速开发平台', NULL, 'PC', '内置', b'1', 1,
        '2020-04-02 15:05:14', 1, '2020-04-02 15:05:17');
COMMIT;

-- ----------------------------
-- Table structure for c_area
-- ----------------------------
DROP TABLE IF EXISTS `c_area`;
CREATE TABLE `c_area`
(
    `id`          bigint       NOT NULL COMMENT 'id',
    `code`        varchar(64)  NOT NULL COMMENT '编码',
    `label`       varchar(255) NOT NULL COMMENT '名称',
    `full_name`   varchar(255) DEFAULT '' COMMENT '全名',
    `sort_value`  int          DEFAULT '1' COMMENT '排序',
    `longitude`   varchar(255) DEFAULT '' COMMENT '经度',
    `latitude`    varchar(255) DEFAULT '' COMMENT '维度',
    `level`       varchar(10)  DEFAULT '' COMMENT '行政区级 \n@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.AREA_LEVEL)',
    `source_`     varchar(255) DEFAULT '' COMMENT '数据来源',
    `state`       bit(1)       DEFAULT b'0' COMMENT '状态',
    `parent_id`   bigint       DEFAULT '0' COMMENT '父ID',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `created_by`  bigint       DEFAULT NULL COMMENT '创建人',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `updated_by`  bigint       DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_code` (`code`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='地区表';

-- ----------------------------
-- Records of c_area
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_datasource_config
-- ----------------------------
DROP TABLE IF EXISTS `c_datasource_config`;
CREATE TABLE `c_datasource_config`
(
    `id`                bigint       NOT NULL COMMENT '主键ID',
    `name`              varchar(255) NOT NULL COMMENT '名称',
    `username`          varchar(255) NOT NULL COMMENT '账号',
    `password`          varchar(255) NOT NULL COMMENT '密码',
    `url`               varchar(255) NOT NULL COMMENT '链接',
    `driver_class_name` varchar(255) NOT NULL COMMENT '驱动',
    `create_time`       datetime DEFAULT NULL COMMENT '创建时间',
    `created_by`        bigint   DEFAULT NULL COMMENT '创建人',
    `update_time`       datetime DEFAULT NULL COMMENT '修改时间',
    `updated_by`        bigint   DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='数据源';

-- ----------------------------
-- Records of c_datasource_config
-- ----------------------------
BEGIN;
INSERT INTO `c_datasource_config` (`id`, `name`, `username`, `password`, `url`, `driver_class_name`, `create_time`,
                                   `created_by`, `update_time`, `updated_by`)
VALUES (1339508377687949312, '1111-基础服务', 'root', 'root',
        'jdbc:mysql://127.0.0.1:3306/lamp_base_ddddd?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&nullCatalogMeansCurrent=true',
        'com.mysql.cj.jdbc.Driver', '2020-12-17 17:50:59', 3, '2021-01-03 20:21:30', 3);
INSERT INTO `c_datasource_config` (`id`, `name`, `username`, `password`, `url`, `driver_class_name`, `create_time`,
                                   `created_by`, `update_time`, `updated_by`)
VALUES (1345706960036560896, '1111-扩展服务', 'root', 'root',
        'jdbc:mysql://127.0.0.1:3306/lamp_extend_ddddd?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true',
        'com.mysql.cj.jdbc.Driver', '2021-01-03 20:21:56', 3, '2021-01-03 20:21:56', 3);
COMMIT;

-- ----------------------------
-- Table structure for c_dict
-- ----------------------------
DROP TABLE IF EXISTS `c_dict`;
CREATE TABLE `c_dict`
(
    `id`          bigint                                                        NOT NULL COMMENT 'ID',
    `parent_id`   bigint                                                                 DEFAULT NULL,
    `parent_key`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT '',
    `classify`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '类型标签',
    `key_`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型',
    `name`        varchar(64)                                                   NOT NULL COMMENT '名称',
    `state`       bit(1)                                                                 DEFAULT b'1' COMMENT '状态',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT '' COMMENT '描述',
    `sort_value`  int                                                                    DEFAULT '1' COMMENT '排序',
    `icon`        varchar(255)                                                           DEFAULT '' COMMENT '图标',
    `css_style`   varchar(255)                                                           DEFAULT '' COMMENT 'css样式',
    `css_class`   varchar(255)                                                           DEFAULT '' COMMENT 'css class',
    `created_by`  bigint                                                                 DEFAULT NULL COMMENT '创建人id',
    `create_time` datetime                                                               DEFAULT NULL COMMENT '创建时间',
    `updated_by`  bigint                                                                 DEFAULT NULL COMMENT '更新人id',
    `update_time` datetime                                                               DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='字典项';

-- ----------------------------
-- Records of c_dict
-- ----------------------------
BEGIN;
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1, 0, '', '20', 'AREA_LEVEL', '行政区划', b'1', '', 1, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (2, 1, 'AREA_LEVEL', '20', '01', '国家', b'1', '', 1, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (3, 1, 'AREA_LEVEL', '20', '02', '省份/直辖市', b'1', '', 2, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (4, 1, 'AREA_LEVEL', '20', '03', '地市', b'1', '', 3, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (5, 1, 'AREA_LEVEL', '20', '04', '区县', b'1', '', 4, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (6, 1, 'AREA_LEVEL', '20', '05', '乡镇', b'1', '', 5, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (10, 0, '', '20', 'EDUCATION', '学历', b'1', '', 1, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (11, 10, 'EDUCATION', '20', '01', '小学', b'1', '', 1, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (12, 10, 'EDUCATION', '20', '02', '中学', b'1', '', 2, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (13, 10, 'EDUCATION', '20', '03', '高中', b'1', '', 3, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (14, 10, 'EDUCATION', '20', '04', '专科', b'1', '', 4, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (15, 10, 'EDUCATION', '20', '05', '本科', b'1', '', 5, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (16, 10, 'EDUCATION', '20', '06', '硕士', b'1', '', 6, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (17, 10, 'EDUCATION', '20', '07', '博士', b'1', '', 7, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (18, 10, 'EDUCATION', '20', '08', '博士后', b'1', '', 8, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (19, 10, 'EDUCATION', '20', '20', '其他', b'1', '', 20, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (20, 0, '', '20', 'SEX', '性别', b'1', '', 1, '', '', '', 1, '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (21, 20, 'SEX', '20', '1', '男', b'1', '', 1, '', '', '', 1, '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (22, 20, 'SEX', '20', '2', '女', b'1', '', 2, '', '', '', 1, '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (23, 20, 'SEX', '20', '3', '未知', b'1', '', 3, '', '', '', 1, '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (43, 0, '', '20', 'ORG_TYPE', '机构类型', b'1', '', 1, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (44, 43, 'ORG_TYPE', '20', '01', '单位', b'1', '', 1, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (45, 43, 'ORG_TYPE', '20', '02', '部门', b'1', '', 2, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (46, 0, '', '20', 'NATION', '民族', b'1', '', 1, '', '', '', 1, '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (47, 46, 'NATION', '20', '01', '汉族', b'1', '', 0, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (48, 46, 'NATION', '20', '02', '壮族', b'1', '', 1, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (49, 46, 'NATION', '20', '03', '满族', b'1', '', 2, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (50, 46, 'NATION', '20', '04', '回族', b'1', '', 3, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (51, 46, 'NATION', '20', '05', '苗族', b'1', '', 4, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (52, 46, 'NATION', '20', '06', '维吾尔族', b'1', '', 5, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (53, 46, 'NATION', '20', '07', '土家族', b'1', '', 6, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (54, 46, 'NATION', '20', '08', '彝族', b'1', '', 7, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (55, 46, 'NATION', '20', '09', '蒙古族', b'1', '', 8, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (56, 46, 'NATION', '20', '10', '藏族', b'1', '', 9, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (57, 46, 'NATION', '20', '11', '布依族', b'1', '', 10, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (58, 46, 'NATION', '20', '12', '侗族', b'1', '', 11, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (59, 46, 'NATION', '20', '13', '瑶族', b'1', '', 12, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (60, 46, 'NATION', '20', '14', '朝鲜族', b'1', '', 13, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (61, 46, 'NATION', '20', '15', '白族', b'1', '', 14, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (62, 46, 'NATION', '20', '16', '哈尼族', b'1', '', 15, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (63, 46, 'NATION', '20', '17', '哈萨克族', b'1', '', 16, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (64, 46, 'NATION', '20', '18', '黎族', b'1', '', 17, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (65, 46, 'NATION', '20', '19', '傣族', b'1', '', 18, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (66, 46, 'NATION', '20', '20', '畲族', b'1', '', 19, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (67, 46, 'NATION', '20', '21', '傈僳族', b'1', '', 20, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (68, 46, 'NATION', '20', '22', '仡佬族', b'1', '', 21, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (69, 46, 'NATION', '20', '23', '东乡族', b'1', '', 22, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (70, 46, 'NATION', '20', '24', '高山族', b'1', '', 23, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (71, 46, 'NATION', '20', '25', '拉祜族', b'1', '', 24, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (72, 46, 'NATION', '20', '26', '水族', b'1', '', 25, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (73, 46, 'NATION', '20', '27', '佤族', b'1', '', 26, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (74, 46, 'NATION', '20', '28', '纳西族', b'1', '', 27, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (75, 46, 'NATION', '20', '29', '羌族', b'1', '', 28, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (76, 46, 'NATION', '20', '30', '土族', b'1', '', 29, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (77, 46, 'NATION', '20', '31', '仫佬族', b'1', '', 30, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (78, 46, 'NATION', '20', '32', '锡伯族', b'1', '', 31, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (79, 46, 'NATION', '20', '33', '柯尔克孜族', b'1', '', 32, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (80, 46, 'NATION', '20', '34', '达斡尔族', b'1', '', 33, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (81, 46, 'NATION', '20', '35', '景颇族', b'1', '', 34, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (82, 46, 'NATION', '20', '36', '毛南族', b'1', '', 35, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (83, 46, 'NATION', '20', '37', '撒拉族', b'1', '', 36, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (84, 46, 'NATION', '20', '38', '塔吉克族', b'1', '', 37, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (85, 46, 'NATION', '20', '39', '阿昌族', b'1', '', 38, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (86, 46, 'NATION', '20', '40', '普米族', b'1', '', 39, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (87, 46, 'NATION', '20', '41', '鄂温克族', b'1', '', 40, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (88, 46, 'NATION', '20', '42', '怒族', b'1', '', 41, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (89, 46, 'NATION', '20', '43', '京族', b'1', '', 42, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (90, 46, 'NATION', '20', '44', '基诺族', b'1', '', 43, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (91, 46, 'NATION', '20', '45', '德昂族', b'1', '', 44, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (92, 46, 'NATION', '20', '46', '保安族', b'1', '', 45, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (93, 46, 'NATION', '20', '47', '俄罗斯族', b'1', '', 46, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (94, 46, 'NATION', '20', '48', '裕固族', b'1', '', 47, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (95, 46, 'NATION', '20', '49', '乌兹别克族', b'1', '', 48, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (96, 46, 'NATION', '20', '50', '门巴族', b'1', '', 49, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (97, 46, 'NATION', '20', '51', '鄂伦春族', b'1', '', 50, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (98, 46, 'NATION', '20', '52', '独龙族', b'1', '', 51, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (99, 46, 'NATION', '20', '53', '塔塔尔族', b'1', '', 52, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (100, 46, 'NATION', '20', '54', '赫哲族', b'1', '', 53, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (101, 46, 'NATION', '20', '55', '珞巴族', b'1', '', 54, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (102, 46, 'NATION', '20', '56', '布朗族', b'1', '', 55, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (103, 46, 'NATION', '20', '57', '其他', b'1', '', 100, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (104, 0, '', '20', 'POSITION_STATUS', '职位状态', b'1', '', 1, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (105, 104, 'POSITION_STATUS', '20', '01', '在职', b'1', '', 1, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (106, 104, 'POSITION_STATUS', '20', '02', '请假', b'1', '', 2, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (107, 104, 'POSITION_STATUS', '20', '03', '离职', b'1', '', 3, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (108, 0, '', '20', 'RESOURCE_DATA_SCOPE', '数据范围', b'1', '', 1, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (109, 0, '', '20', 'RESOURCE_TYPE', '资源类型', b'1', '', 1, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (110, 0, '', '20', 'ROLE_CATEGORY', '角色类别', b'1', '', 1, '', '', '', 1, '2023-07-13 17:42:03', 1,
        '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1486198107023605760, 109, 'RESOURCE_TYPE', '20', '10', '菜单', b'1', '', 1, '', '', '', 1,
        '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1486198623417925632, 108, 'RESOURCE_DATA_SCOPE', '20', '01', '全部', b'1', '', 1, '', '', '', 1,
        '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1486198858865180672, 110, 'ROLE_CATEGORY', '20', '10', '功能角色', b'1', '', 1, '', '', '', 1,
        '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1486198996287356921, 109, 'RESOURCE_TYPE', '20', '40', '字段', b'1', '', 4, '', '', '', 1,
        '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1486198996287356922, 109, 'RESOURCE_TYPE', '20', '30', '功能', b'1', '', 3, '', '', '', 1,
        '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1486198996287356923, 109, 'RESOURCE_TYPE', '20', '20', '视图', b'1', '', 2, '', '', '', 1,
        '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1486198996287356928, 109, 'RESOURCE_TYPE', '20', '50', '数据', b'1', '', 5, '', '', '', 1,
        '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1486199651718660096, 108, 'RESOURCE_DATA_SCOPE', '20', '02', '本单位及子级', b'1', '', 2, '', '', '', 1,
        '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1486199679237488640, 108, 'RESOURCE_DATA_SCOPE', '20', '03', '本单位', b'1', '', 3, '', '', '', 1,
        '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1486199726792507392, 108, 'RESOURCE_DATA_SCOPE', '20', '04', '本部门及子级', b'1', '', 4, '', '', '', 1,
        '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1486199753644441600, 108, 'RESOURCE_DATA_SCOPE', '20', '05', '本部门', b'1', '', 5, '', '', '', 1,
        '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1486199809227358208, 108, 'RESOURCE_DATA_SCOPE', '20', '06', '个人', b'1', '', 6, '', '', '', 1,
        '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1486199838218387456, 108, 'RESOURCE_DATA_SCOPE', '20', '07', '自定义', b'1', '', 7, '', '', '', 1,
        '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1486200233623814144, 110, 'ROLE_CATEGORY', '20', '20', '桌面角色', b'1', '', 2, '', '', '', 1,
        '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1486200358744096768, 110, 'ROLE_CATEGORY', '20', '30', '数据角色', b'1', '', 3, '', '', '', 1,
        '2023-07-13 17:42:03', 1, '2023-07-13 17:42:03');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1681934380567625728, 0, '', '', 'LoginStatusEnum', '登录状态', b'1',
        '[01-登录成功 02-验证码错误 03-密码错误 04-账号锁定 05-切换租户 06-短信验证码错误]', 1, '', '', '', 2,
        '2023-07-20 15:49:40', 2, '2023-07-20 15:51:16');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1681935022518435840, 1681934380567625728, 'LoginStatusEnum', '', '01', '登录成功', b'1', '', 1, '', '', '', 2,
        '2023-07-20 15:52:13', 2, '2023-07-20 15:52:13');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1681935121462067200, 1681934380567625728, 'LoginStatusEnum', '', '02', '验证码错误', b'1', '', 2, '', '', '', 2,
        '2023-07-20 15:52:36', 2, '2023-07-20 15:53:52');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1681935384126160896, 1681934380567625728, 'LoginStatusEnum', '', '03', '密码错误', b'1', '', 3, '', '', '', 2,
        '2023-07-20 15:53:39', 2, '2023-07-20 15:53:39');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1681935517454696448, 1681934380567625728, 'LoginStatusEnum', '', '04', '账号锁定', b'1', '', 4, '', '', '', 2,
        '2023-07-20 15:54:11', 2, '2023-07-20 15:54:11');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1681935605170176000, 1681934380567625728, 'LoginStatusEnum', '', '05', '切换租户', b'1', '', 5, '', '', '', 2,
        '2023-07-20 15:54:32', 2, '2023-07-20 15:54:32');
INSERT INTO `c_dict` (`id`, `parent_id`, `parent_key`, `classify`, `key_`, `name`, `state`, `remark`, `sort_value`,
                      `icon`, `css_style`, `css_class`, `created_by`, `create_time`, `updated_by`, `update_time`)
VALUES (1681935700552843264, 1681934380567625728, 'LoginStatusEnum', '', '06', '短信验证码错误', b'1', '', 6, '', '',
        '', 2, '2023-07-20 15:54:55', 2, '2023-07-20 15:54:55');
COMMIT;

-- ----------------------------
-- Table structure for c_file
-- ----------------------------
DROP TABLE IF EXISTS `c_file`;
CREATE TABLE `c_file`
(
    `id`                 bigint       NOT NULL COMMENT 'ID',
    `biz_type`           varchar(255) NOT NULL DEFAULT '' COMMENT '业务类型',
    `file_type`          varchar(255)          DEFAULT NULL COMMENT '文件类型',
    `storage_type`       varchar(255)          DEFAULT NULL COMMENT '存储类型\nLOCAL FAST_DFS MIN_IO ALI \n',
    `bucket`             varchar(255)          DEFAULT '' COMMENT '桶',
    `path`               varchar(255)          DEFAULT '' COMMENT '文件相对地址',
    `url`                varchar(255)          DEFAULT NULL COMMENT '文件访问地址',
    `unique_file_name`   varchar(255)          DEFAULT '' COMMENT '唯一文件名',
    `file_md5`           varchar(255)          DEFAULT NULL COMMENT '文件md5',
    `original_file_name` varchar(255)          DEFAULT '' COMMENT '原始文件名',
    `content_type`       varchar(255)          DEFAULT '' COMMENT '文件类型',
    `suffix`             varchar(255)          DEFAULT '' COMMENT '后缀',
    `size_`              bigint                DEFAULT '0' COMMENT '大小',
    `create_time`        datetime     NOT NULL COMMENT '创建时间',
    `created_by`         bigint       NOT NULL COMMENT '创建人',
    `update_time`        datetime     NOT NULL COMMENT '最后修改时间',
    `updated_by`         bigint       NOT NULL COMMENT '最后修改人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='增量文件上传日志';

-- ----------------------------
-- Records of c_file
-- ----------------------------
BEGIN;
INSERT INTO `c_file` (`id`, `biz_type`, `file_type`, `storage_type`, `bucket`, `path`, `url`, `unique_file_name`,
                      `file_md5`, `original_file_name`, `content_type`, `suffix`, `size_`, `create_time`, `created_by`,
                      `update_time`, `updated_by`)
VALUES (1531859537911349248, 'BASE_FILE', 'DOC', 'LOCAL', 'dev',
        '0000/BASE_FILE/2022/06/01/0bd3aee387bb49f9b17bd429145cda87.xlsx',
        'http://127.0.0.1/file/dev/0000/BASE_FILE/2022/06/01/0bd3aee387bb49f9b17bd429145cda87.xlsx',
        '0bd3aee387bb49f9b17bd429145cda87.xlsx', NULL, '用户列表.xlsx',
        'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', 'xlsx', 13663, '2022-06-01 12:45:49', 2,
        '2022-06-01 12:45:49', 2);
COMMIT;

-- ----------------------------
-- Table structure for c_login_log
-- ----------------------------
DROP TABLE IF EXISTS `c_login_log`;
CREATE TABLE `c_login_log`
(
    `id`               bigint NOT NULL COMMENT '主键',
    `request_ip`       varchar(50)                                                  DEFAULT '' COMMENT '登录IP',
    `user_id`          bigint                                                       DEFAULT NULL COMMENT '登录人ID',
    `nick_name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '登录人姓名',
    `username`         varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '登录人账号',
    `description`      varchar(255)                                                 DEFAULT '' COMMENT '登录描述',
    `login_date`       char(10)                                                     DEFAULT '' COMMENT '登录时间',
    `ua`               varchar(500)                                                 DEFAULT '' COMMENT '浏览器请求头',
    `browser`          varchar(255)                                                 DEFAULT '' COMMENT '浏览器名称',
    `browser_version`  varchar(255)                                                 DEFAULT '' COMMENT '浏览器版本',
    `operating_system` varchar(255)                                                 DEFAULT '' COMMENT '操作系统',
    `location`         varchar(50)                                                  DEFAULT '' COMMENT '登录地点',
    `status`           varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT '' COMMENT '登录状态',
    `create_time`      datetime                                                     DEFAULT NULL COMMENT '创建时间',
    `created_by`       bigint                                                       DEFAULT NULL COMMENT '创建人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='登录日志';

-- ----------------------------
-- Records of c_login_log
-- ----------------------------
BEGIN;
INSERT INTO `c_login_log` (`id`, `request_ip`, `user_id`, `nick_name`, `username`, `description`, `login_date`, `ua`,
                           `browser`, `browser_version`, `operating_system`, `location`, `status`, `create_time`,
                           `created_by`)
VALUES (1681957274165182464, '127.0.0.1', 2, '超级管理员', 'kpu', '登录成功', '2023-07-20',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        'Chrome', '114.0.0.0', 'OSX', '0|0|0|内网IP|内网IP', '01', '2023-07-20 17:20:38', 2);
COMMIT;

-- ----------------------------
-- Table structure for c_menu
-- ----------------------------
DROP TABLE IF EXISTS `c_menu`;
CREATE TABLE `c_menu`
(
    `id`            bigint NOT NULL COMMENT 'ID',
    `code`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT '' COMMENT '编码',
    `title`         varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT '' COMMENT '标题',
    `name`          varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT '' COMMENT '名称',
    `resource_type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT NULL COMMENT '[10-菜单 20-视图 30-按钮 40-字段 50-数据];\n@Echo(api = DICTIONARY_ITEM_FEIGN_CLASS,dictType = EchoDictType.RESOURCE_TYPE)',
    `remarks`       varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '描述',
    `is_general`    bit(1)                                                        DEFAULT b'0' COMMENT '通用菜单 \nTrue表示无需分配所有人就可以访问的',
    `redirect`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '重定向',
    `path`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '路径',
    `component`     varchar(255)                                                  DEFAULT '' COMMENT '组件',
    `state`         bit(1)                                                        DEFAULT b'1' COMMENT '状态',
    `sort_value`    int                                                           DEFAULT '1' COMMENT '排序',
    `icon`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '菜单图标',
    `active_icon`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '激活菜单图标',
    `group_`        varchar(20)                                                   DEFAULT '' COMMENT '分组',
    `data_scope`    char(2)                                                       DEFAULT NULL COMMENT '数据范围;[01-全部 02-本单位及子级 03-本单位 04-本部门 05-本部门及子级 06-个人 07-自定义]',
    `custom_class`  varchar(255)                                                  DEFAULT NULL COMMENT '实现类',
    `is_def`        bit(1)                                                        DEFAULT b'0' COMMENT '是否默认',
    `parent_id`     bigint                                                        DEFAULT '0' COMMENT '父级菜单ID',
    `tree_grade`    int                                                           DEFAULT NULL COMMENT '树层级',
    `tree_path`     varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '树路径',
    `meta_json`     varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '路由信息配置',
    `created_by`    bigint                                                        DEFAULT NULL COMMENT '创建人id',
    `create_time`   datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `updated_by`    bigint                                                        DEFAULT NULL COMMENT '更新人id',
    `update_time`   datetime                                                      DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='菜单';

-- ----------------------------
-- Records of c_menu
-- ----------------------------
BEGIN;
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (10, NULL, '演示', '', '10', '', b'0', NULL, '', '', b'1', 1, 'uim:box', '', '', NULL, NULL, NULL, 0, 0, '/',
        '{}', 1, '2020-11-23 11:47:31', 1, '2020-11-23 11:47:31');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (20, NULL, '系统管理', '', '10', '', b'0', NULL, '/system', 'Layout', b'1', 1, 'ri:shield-keyhole-line',
        'ri:shield-keyhole-line', '', NULL, NULL, NULL, 10, 1, '/10/',
        '{\"defaultOpened\":false,\"permanent\":false,\"auth\":[],\"sidebar\":true,\"breadcrumb\":true,\"activeMenu\":\"\",\"cache\":[],\"noCache\":[],\"badge\":\"\",\"link\":\"\",\"iframe\":\"\",\"copyright\":false,\"paddingBottom\":\"0px\"}',
        1, '2020-11-23 11:47:31', 2, '2023-07-19 21:11:00');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (30, '', '菜单管理', '', '10', '', b'0', '/system/menu', 'menu', '', b'1', 1, 'ri:shield-keyhole-line',
        'ri:shield-keyhole-line', '', NULL, NULL, NULL, 20, 2, '/20/10/',
        '{\"defaultOpened\":false,\"permanent\":false,\"auth\":[],\"sidebar\":true,\"breadcrumb\":true,\"activeMenu\":\"\",\"cache\":[],\"noCache\":[],\"badge\":\"new\",\"link\":\"\",\"iframe\":\"\",\"copyright\":false,\"paddingBottom\":\"0px\"}',
        1, '2020-11-23 11:47:31', 2, '2023-07-19 20:52:00');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (31, '', '菜单列表', 'SystemMenuList', '20', '', b'0', '', '', 'system/menu/index.vue', b'1', 1, '', '', '',
        NULL, NULL, NULL, 30, 3, '/30/20/10/', '{\"sidebar\":false,\"cache\":[\"menuCreate\",\"menuEdit\"]}', 1,
        '2020-11-23 11:47:31', 1, '2020-11-23 11:47:31');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (32, '', '新增菜单', 'SystemMenuCreate', '20', '', b'0', '', 'detail', 'system/menu/detail.vue', b'1', 2, '', '',
        '', NULL, NULL, NULL, 30, 3, '/30/20/10/',
        '{\"defaultOpened\":false,\"permanent\":false,\"auth\":[],\"sidebar\":false,\"breadcrumb\":true,\"activeMenu\":\"/system/menu\",\"cache\":true,\"noCache\":[\"SystemMenuList\"],\"badge\":\"\",\"link\":\"\",\"iframe\":\"\",\"copyright\":false,\"paddingBottom\":\"80px\"}',
        1, '2020-11-23 11:47:31', 2, '2023-07-19 21:01:50');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (33, '', '修改菜单', 'SystemMenuEdit', '20', '', b'0', '', 'detail/:id', 'system/menu/detail.vue', b'1', 3, '',
        '', '', NULL, NULL, NULL, 30, 3, '/30/20/10/',
        '{\"sidebar\":false,\"activeMenu\":\"/system/menu\",\"cache\":true,\"noCache\":[\"menuList\"],\"copyright\":false,\"paddingBottom\":\"80px\"}',
        1, '2020-11-23 11:47:31', 1, '2020-11-23 11:47:31');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (34, 'authority:menu:view', '显示', '', '30', '', b'0', NULL, NULL, '', b'1', 1, '', NULL, '', NULL, NULL, NULL,
        31, 4, '/31/30/20/10/', '', 1, '2020-11-23 11:47:31', 1, '2020-11-23 11:47:31');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (35, 'authority:menu:add', '添加', '', '30', '', b'0', NULL, NULL, '', b'1', 1, '', NULL, '', NULL, NULL, NULL,
        31, 4, '/31/30/20/10/', '', 1, '2020-11-23 11:47:31', 1, '2020-11-23 11:47:31');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (36, 'authority:menu:edit', '修改', '', '30', '', b'0', NULL, NULL, '', b'1', 1, '', NULL, '', NULL, NULL, NULL,
        31, 4, '/31/30/20/10/', '', 1, '2020-11-23 11:47:31', 1, '2020-11-23 11:47:31');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (37, 'authority:menu:delete', '删除', '', '30', '', b'0', NULL, NULL, '', b'1', 1, '', NULL, '', NULL, NULL,
        NULL, 31, 4, '/31/30/20/10/', '', 1, '2020-11-23 11:47:31', 1, '2020-11-23 11:47:31');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (40, NULL, '角色管理', 'SystemRoleList', '20', '', b'0', NULL, 'role', 'system/role/index.vue', b'1', 2,
        'ri:shield-keyhole-line', 'ri:shield-keyhole-line', '', NULL, NULL, NULL, 20, 2, '/20/10/',
        '{\"defaultOpened\":false,\"permanent\":false,\"auth\":[],\"sidebar\":true,\"breadcrumb\":true,\"activeMenu\":\"\",\"cache\":[],\"noCache\":[],\"badge\":\"\",\"link\":\"\",\"iframe\":\"\",\"copyright\":false,\"paddingBottom\":\"0px\"}',
        1, '2020-11-23 11:47:31', 2, '2023-07-18 17:41:49');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (1677509487545548800, '', '用户管理', 'SystemUserList', '20', '', b'0', '', 'user', 'system/user/index.vue',
        b'1', 3, 'ep:user', 'ep:user-filled', '', NULL, NULL, b'0', 20, 2, '/20/10/',
        '{\"defaultOpened\":false,\"permanent\":false,\"auth\":[],\"sidebar\":true,\"breadcrumb\":true,\"activeMenu\":\"/system/user\",\"cache\":[\"test\",\"testAdd\"],\"noCache\":[\"test\"],\"badge\":\"new\",\"link\":\"\",\"iframe\":\"\",\"copyright\":true,\"paddingBottom\":\"20px\"}',
        1, '2023-07-08 10:46:43', 2, '2023-07-17 13:19:42');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (1677509537856225280, 'authority:user:edit', '修改', '', '30', '', b'0', '', '', '', b'1', 3, '', NULL, '', NULL,
        NULL, b'0', 1677509487545548800, 3, '/1677509487545548800/20/10/', '', 1, '2023-07-08 10:46:55', 2,
        '2023-07-17 13:19:42');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (1677585709487095808, 'authority:user:add', '新增', '', '30', '', b'0', '', '', '', b'1', 2, '', NULL, '', NULL,
        NULL, b'0', 1677509487545548800, 3, '/1677509487545548800/20/10/', '', 1, '2023-07-08 15:49:36', 2,
        '2023-07-17 13:19:42');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (1677590913037107200, 'authority:user:view', '显示', '', '30', '', b'0', '', '', '', b'1', 1, '', NULL, '', NULL,
        NULL, b'0', 1677509487545548800, 3, '/1677509487545548800/20/10/', '', 1, '2023-07-08 16:10:16', 2,
        '2023-07-17 13:19:42');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (1681147781336530944, '', '字典管理', 'SystemDict', '20', '', b'0', '', 'dict', 'system/dict/list.vue', b'1', 4,
        'ep:reading', '', '', NULL, NULL, b'0', 20, 2, '/20/10/',
        '{\"defaultOpened\":false,\"permanent\":false,\"auth\":[],\"sidebar\":true,\"breadcrumb\":true,\"activeMenu\":\"\",\"cache\":[],\"noCache\":[],\"badge\":\"new\",\"link\":\"\",\"iframe\":\"\",\"copyright\":true,\"paddingBottom\":\"0px\"}',
        2, '2023-07-18 11:44:00', 2, '2023-07-19 14:28:31');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (1681237640319336448, 'system:dict:add', '新增', '', '30', '', b'0', '', '', '', b'1', 1, '', '', '', NULL, NULL,
        b'0', 1681147781336530944, 3, '/1681147781336530944/20/10/', '', 2, '2023-07-18 17:41:04', 2,
        '2023-07-19 14:28:31');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (1681237640373862400, 'system:dict:edit', '修改', '', '30', '', b'0', '', '', '', b'1', 2, '', '', '', NULL,
        NULL, b'0', 1681147781336530944, 3, '/1681147781336530944/20/10/', '', 2, '2023-07-18 17:41:04', 2,
        '2023-07-19 14:28:31');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (1681237640386445312, 'system:dict:del', '删除', '', '30', '', b'0', '', '', '', b'1', 3, '', '', '', NULL, NULL,
        b'0', 1681147781336530944, 3, '/1681147781336530944/20/10/', '', 2, '2023-07-18 17:41:04', 2,
        '2023-07-19 14:28:31');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (1681558362933690368, '', '参数配置', 'SystemParameterList', '20', '', b'0', '', 'parameter',
        'system/parameter/list.vue', b'1', 5, 'cil:settings', '', '', NULL, NULL, b'0', 20, 2, '/20/10/',
        '{\"defaultOpened\":false,\"permanent\":false,\"auth\":[],\"sidebar\":true,\"breadcrumb\":true,\"activeMenu\":\"\",\"cache\":[],\"noCache\":[],\"badge\":\"\",\"link\":\"\",\"iframe\":\"\",\"copyright\":false,\"paddingBottom\":\"0px\"}',
        2, '2023-07-19 14:55:30', 2, '2023-07-19 14:55:30');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (1681647656843083776, '', '登录日志', '', '10', '', b'0', '', 'loginLog', '', b'1', 6, 'cil:notes', '', '', NULL,
        NULL, b'0', 20, 2, '/20/10/',
        '{\"defaultOpened\":false,\"permanent\":false,\"auth\":[],\"sidebar\":true,\"breadcrumb\":true,\"activeMenu\":\"\",\"cache\":[],\"noCache\":[],\"badge\":\"\",\"link\":\"\",\"iframe\":\"\",\"copyright\":false,\"paddingBottom\":\"0px\"}',
        2, '2023-07-19 20:50:20', 2, '2023-07-20 07:48:56');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (1681648876672516096, '', '登录日志列表', 'SystemLoginLogList', '20', '', b'0', '', '',
        'system/loginLog/list.vue', b'1', 1, '', '', '', NULL, NULL, b'0', 1681647656843083776, 3,
        '/1681647656843083776/20/10/',
        '{\"defaultOpened\":false,\"permanent\":false,\"auth\":[],\"sidebar\":false,\"breadcrumb\":false,\"activeMenu\":\"\",\"cache\":[\"SystemLoginLogDetail\",\"SystemLoginLogCreate\"],\"noCache\":[],\"badge\":\"\",\"link\":\"\",\"iframe\":\"\",\"copyright\":false,\"paddingBottom\":\"0px\"}',
        2, '2023-07-19 20:55:10', 2, '2023-07-20 07:47:25');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (1681650303931252736, '', '新增登录日志', 'SystemLoginLogCreate', '20', '', b'0', '', 'detail',
        'system/loginLog/detail.vue', b'1', 2, '', '', '', NULL, NULL, b'0', 1681647656843083776, 3,
        '/1681647656843083776/20/10/',
        '{\"defaultOpened\":false,\"permanent\":false,\"auth\":[],\"sidebar\":false,\"breadcrumb\":true,\"activeMenu\":\"\",\"cache\":true,\"noCache\":[\"SystemLoginLogList\"],\"badge\":\"\",\"link\":\"\",\"iframe\":\"\",\"copyright\":true,\"paddingBottom\":\"80px\"}',
        2, '2023-07-19 21:00:51', 2, '2023-07-19 21:37:09');
INSERT INTO `c_menu` (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`,
                      `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`,
                      `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (1681651894805594112, '', '查看登录日志', 'SystemLoginLogDetail', '20', '', b'0', '', 'detail/:id',
        'system/loginLog/detail.vue', b'1', 3, '', '', '', NULL, NULL, b'0', 1681647656843083776, 3,
        '/1681647656843083776/20/10/',
        '{\"defaultOpened\":false,\"permanent\":false,\"auth\":[],\"sidebar\":false,\"breadcrumb\":true,\"activeMenu\":\"\",\"cache\":true,\"noCache\":[\"SystemLoginLogList\"],\"badge\":\"\",\"link\":\"\",\"iframe\":\"\",\"copyright\":true,\"paddingBottom\":\"80px\"}',
        2, '2023-07-19 21:07:10', 2, '2023-07-19 21:37:30');
COMMIT;

-- ----------------------------
-- Table structure for c_opt_log
-- ----------------------------
DROP TABLE IF EXISTS `c_opt_log`;
CREATE TABLE `c_opt_log`
(
    `id`             bigint    NOT NULL COMMENT '主键',
    `request_ip`     varchar(50)                                                  DEFAULT '' COMMENT '操作IP',
    `type`           varchar(5)                                                   DEFAULT '' COMMENT '日志类型 \n#LogType{OPT:操作类型;EX:异常类型}',
    `nick_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '操作人',
    `description`    varchar(255)                                                 DEFAULT '' COMMENT '操作描述',
    `class_path`     varchar(255)                                                 DEFAULT '' COMMENT '类路径',
    `action_method`  varchar(50)                                                  DEFAULT '' COMMENT '请求方法',
    `request_uri`    varchar(50)                                                  DEFAULT '' COMMENT '请求地址',
    `http_method`    varchar(10)                                                  DEFAULT '' COMMENT '请求类型 \n#HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}',
    `start_time`     timestamp NULL                                               DEFAULT NULL COMMENT '开始时间',
    `finish_time`    timestamp NULL                                               DEFAULT NULL COMMENT '完成时间',
    `consuming_time` bigint                                                       DEFAULT NULL COMMENT '消耗时间',
    `ua`             varchar(500)                                                 DEFAULT '' COMMENT '浏览器',
    `create_time`    datetime                                                     DEFAULT NULL COMMENT '创建时间',
    `created_by`     bigint                                                       DEFAULT NULL COMMENT '创建人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='系统日志';

-- ----------------------------
-- Records of c_opt_log
-- ----------------------------
BEGIN;
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681661975005757440, '127.0.0.1', 'OPT', '', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-19 21:47:02', '2023-07-19 21:47:02', 299,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-19 21:47:13', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681663789948207104, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-19 21:54:19', '2023-07-19 21:54:19', 314,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-19 21:54:26', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681666867501989888, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-19 22:05:04', '2023-07-19 22:06:40', 95280,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-19 22:06:40', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681666983004733440, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-19 22:06:43', '2023-07-19 22:07:07', 24723,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-19 22:07:07', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681667996096921600, '127.0.0.1', 'OPT', '超级管理员', '菜单-查询系统所有的菜单和视图',
        'cn.lmx.kpu.authority.controller.auth.MenuController', 'allTree', '/menu/treeMenuAndView', 'POST',
        '2023-07-19 22:10:51', '2023-07-19 22:11:09', 17935,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-19 22:11:09', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681674428380872704, '127.0.0.1', 'OPT', '超级管理员', '菜单-查询系统所有的菜单和视图',
        'cn.lmx.kpu.authority.controller.auth.MenuController', 'allTree', '/menu/treeMenuAndView', 'POST',
        '2023-07-19 22:36:42', '2023-07-19 22:36:42', 129,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-19 22:36:42', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681674428611559424, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-19 22:36:42', '2023-07-19 22:36:42', 268,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-19 22:36:42', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681675755601264640, '127.0.0.1', 'OPT', '超级管理员', '菜单-查询系统所有的菜单和视图',
        'cn.lmx.kpu.authority.controller.auth.MenuController', 'allTree', '/menu/treeMenuAndView', 'POST',
        '2023-07-19 22:41:59', '2023-07-19 22:41:59', 20,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-19 22:41:59', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681675756150718464, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-19 22:41:59', '2023-07-19 22:41:59', 21,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-19 22:41:59', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681812898826944512, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 07:46:56', '2023-07-20 07:46:56', 27,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 07:46:56', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681812923325874176, '127.0.0.1', 'OPT', '超级管理员', '菜单-查询系统所有的菜单和视图',
        'cn.lmx.kpu.authority.controller.auth.MenuController', 'allTree', '/menu/treeMenuAndView', 'POST',
        '2023-07-20 07:47:02', '2023-07-20 07:47:02', 16,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 07:47:02', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681812971354849280, '127.0.0.1', 'OPT', '超级管理员', '菜单-查询:1681648876672516096',
        'cn.lmx.kpu.authority.controller.auth.MenuController', 'get', '/menu/1681648876672516096', 'GET',
        '2023-07-20 07:47:14', '2023-07-20 07:47:14', 30,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 07:47:14', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681813019815837696, '127.0.0.1', 'OPT', '超级管理员', '菜单-修改:null',
        'cn.lmx.kpu.authority.controller.auth.MenuController', 'update', '/menu', 'PUT', '2023-07-20 07:47:25',
        '2023-07-20 07:47:25', 67,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 07:47:25', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681813021787160576, '127.0.0.1', 'OPT', '超级管理员', '菜单-查询系统所有的菜单和视图',
        'cn.lmx.kpu.authority.controller.auth.MenuController', 'allTree', '/menu/treeMenuAndView', 'POST',
        '2023-07-20 07:47:26', '2023-07-20 07:47:26', 11,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 07:47:26', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681813072869588992, '127.0.0.1', 'OPT', '超级管理员', '菜单-查询:1681647656843083776',
        'cn.lmx.kpu.authority.controller.auth.MenuController', 'get', '/menu/1681647656843083776', 'GET',
        '2023-07-20 07:47:38', '2023-07-20 07:47:38', 15,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 07:47:38', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681813400872550400, '127.0.0.1', 'OPT', '超级管理员', '菜单-修改:null',
        'cn.lmx.kpu.authority.controller.auth.MenuController', 'update', '/menu', 'PUT', '2023-07-20 07:48:56',
        '2023-07-20 07:48:56', 46,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 07:48:56', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681813402676101120, '127.0.0.1', 'OPT', '超级管理员', '菜单-查询系统所有的菜单和视图',
        'cn.lmx.kpu.authority.controller.auth.MenuController', 'allTree', '/menu/treeMenuAndView', 'POST',
        '2023-07-20 07:48:56', '2023-07-20 07:48:56', 12,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 07:48:56', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681813432279498752, '127.0.0.1', 'OPT', '超级管理员', '菜单-查询系统所有的菜单和视图',
        'cn.lmx.kpu.authority.controller.auth.MenuController', 'allTree', '/menu/treeMenuAndView', 'POST',
        '2023-07-20 07:49:03', '2023-07-20 07:49:03', 9,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 07:49:03', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681813443209854976, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 07:49:06', '2023-07-20 07:49:06', 22,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 07:49:06', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681813506355101696, '127.0.0.1', 'OPT', '超级管理员', '登录日志-查询:1681812886797680640',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'getDetail', '/loginLog/detail', 'GET',
        '2023-07-20 07:49:21', '2023-07-20 07:49:21', 7,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 07:49:21', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681814712502714368, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 07:54:09', '2023-07-20 07:54:09', 47,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 07:54:09', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681816359215824896, '127.0.0.1', 'OPT', '超级管理员', '参数配置-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.ParameterController', 'page', '/parameter/page', 'POST',
        '2023-07-20 08:00:41', '2023-07-20 08:00:41', 35,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:00:41', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681816427511676928, '127.0.0.1', 'OPT', '超级管理员', '字典-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.DictController', 'page', '/dict/page', 'POST', '2023-07-20 08:00:58',
        '2023-07-20 08:00:58', 60,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:00:58', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681816441860390912, '127.0.0.1', 'OPT', '超级管理员', '用户-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.auth.UserController', 'page', '/user/page', 'POST', '2023-07-20 08:01:01',
        '2023-07-20 08:01:01', 174,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:01:01', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681816470197108736, '127.0.0.1', 'OPT', '超级管理员', '字典-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.DictController', 'page', '/dict/page', 'POST', '2023-07-20 08:01:08',
        '2023-07-20 08:01:08', 27,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:01:08', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681816486315819008, '127.0.0.1', 'OPT', '超级管理员', '参数配置-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.ParameterController', 'page', '/parameter/page', 'POST',
        '2023-07-20 08:01:12', '2023-07-20 08:01:12', 16,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:01:12', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681816503701209088, '127.0.0.1', 'OPT', '超级管理员', '菜单-查询系统所有的菜单和资源树',
        'cn.lmx.kpu.authority.controller.auth.MenuController', 'menuResourceTree', '/menu/menuResourceTree', 'POST',
        '2023-07-20 08:01:16', '2023-07-20 08:01:16', 47,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:01:16', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681816503755735040, '127.0.0.1', 'OPT', '超级管理员', '角色-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.auth.RoleController', 'page', '/role/page', 'POST', '2023-07-20 08:01:16',
        '2023-07-20 08:01:16', 46,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:01:16', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681816530406342656, '127.0.0.1', 'OPT', '超级管理员', '角色-查询角色拥有的资源',
        'cn.lmx.kpu.authority.controller.auth.RoleController', 'findResourceListByRoleId', '/role/resourceList', 'GET',
        '2023-07-20 08:01:22', '2023-07-20 08:01:22', 25,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:01:22', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681817031994769408, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 08:03:22', '2023-07-20 08:03:22', 35,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:03:22', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681817353421062144, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 08:04:38', '2023-07-20 08:04:38', 20,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:04:38', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681817420303433728, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 08:04:54', '2023-07-20 08:04:54', 22,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:04:54', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681817890312945664, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 08:06:46', '2023-07-20 08:06:46', 36,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:06:46', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681818419134988288, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 08:08:52', '2023-07-20 08:08:52', 20,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:08:52', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681818759678918656, '127.0.0.1', 'OPT', '超级管理员', '登录日志-查询:1681654828767379456',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'getDetail', '/loginLog/detail', 'GET',
        '2023-07-20 08:10:14', '2023-07-20 08:10:14', 16,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:10:14', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681820181900296192, '127.0.0.1', 'OPT', '超级管理员', '用户-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.auth.UserController', 'page', '/user/page', 'POST', '2023-07-20 08:15:53',
        '2023-07-20 08:15:53', 79,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:15:53', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681820203807145984, '127.0.0.1', 'OPT', '超级管理员', '角色-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.auth.RoleController', 'page', '/role/page', 'POST', '2023-07-20 08:15:58',
        '2023-07-20 08:15:58', 35,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:15:58', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681820203861671936, '127.0.0.1', 'OPT', '超级管理员', '菜单-查询系统所有的菜单和资源树',
        'cn.lmx.kpu.authority.controller.auth.MenuController', 'menuResourceTree', '/menu/menuResourceTree', 'POST',
        '2023-07-20 08:15:58', '2023-07-20 08:15:58', 47,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:15:58', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681820217111478272, '127.0.0.1', 'OPT', '超级管理员', '用户-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.auth.UserController', 'page', '/user/page', 'POST', '2023-07-20 08:16:01',
        '2023-07-20 08:16:01', 62,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:16:01', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681823519874220032, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 08:29:09', '2023-07-20 08:29:09', 32,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:29:09', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681824391744192512, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 08:32:36', '2023-07-20 08:32:36', 31,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:32:36', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681824415836274688, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 08:32:42', '2023-07-20 08:32:42', 29,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:32:42', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681824755314851840, '127.0.0.1', 'OPT', '超级管理员', '登录日志-查询:1681812886797680640',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'getDetail', '/loginLog/detail', 'GET',
        '2023-07-20 08:34:03', '2023-07-20 08:34:03', 18,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:34:03', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681824955395735552, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 08:34:51', '2023-07-20 08:34:51', 20,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:34:51', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681826688595394560, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 08:41:44', '2023-07-20 08:41:44', 22,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:41:44', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681826688721223680, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 08:41:44', '2023-07-20 08:41:44', 22,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 08:41:44', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681850120322154496, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:14:51', '2023-07-20 10:14:51', 19,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:14:51', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681850120552841216, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:14:51', '2023-07-20 10:14:51', 17,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:14:51', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681850785186447360, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:17:29', '2023-07-20 10:17:29', 35,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:17:29', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681850788818714624, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:17:30', '2023-07-20 10:17:30', 34,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:17:30', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851012157014016, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:18:23', '2023-07-20 10:18:23', 20,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:18:23', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851015986413568, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:18:24', '2023-07-20 10:18:24', 19,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:18:24', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851057463885824, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:18:34', '2023-07-20 10:18:34', 21,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:18:34', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851057891704832, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:18:34', '2023-07-20 10:18:34', 21,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:18:34', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851061419114496, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:18:35', '2023-07-20 10:18:35', 19,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:18:35', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851066188038144, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:18:36', '2023-07-20 10:18:36', 19,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:18:36', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851290063208448, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:19:29', '2023-07-20 10:19:29', 36,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:19:29', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851290495221760, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:19:30', '2023-07-20 10:19:30', 23,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:19:30', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851290923040768, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:19:30', '2023-07-20 10:19:30', 18,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:19:30', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851294144266240, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:19:30', '2023-07-20 10:19:30', 17,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:19:30', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851299059990528, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:19:32', '2023-07-20 10:19:32', 19,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:19:32', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851304030240768, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:19:33', '2023-07-20 10:19:33', 18,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:19:33', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851354236059648, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:19:45', '2023-07-20 10:19:45', 17,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:19:45', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851354693238784, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:19:45', '2023-07-20 10:19:45', 18,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:19:45', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851358170316800, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:19:46', '2023-07-20 10:19:46', 17,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:19:46', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851363316727808, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:19:47', '2023-07-20 10:19:47', 17,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:19:47', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851407172370432, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:19:57', '2023-07-20 10:19:57', 18,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:19:57', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851411219873792, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:19:58', '2023-07-20 10:19:58', 19,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:19:58', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851538558943232, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:20:29', '2023-07-20 10:20:29', 22,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:20:29', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851542593863680, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:20:30', '2023-07-20 10:20:30', 18,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:20:30', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851614714920960, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:20:47', '2023-07-20 10:20:47', 19,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:20:47', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851618573680640, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:20:48', '2023-07-20 10:20:48', 19,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:20:48', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681851810366619648, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:21:34', '2023-07-20 10:21:34', 21,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:21:34', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681852232066138112, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:23:14', '2023-07-20 10:23:14', 29,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:23:14', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681852236231081984, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:23:15', '2023-07-20 10:23:15', 32,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:23:15', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681852566142451712, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:24:34', '2023-07-20 10:24:34', 39,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:24:34', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681852570093486080, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:24:35', '2023-07-20 10:24:35', 19,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:24:35', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681852759302733824, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:25:20', '2023-07-20 10:25:20', 22,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:25:20', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681852763123744768, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:25:21', '2023-07-20 10:25:21', 17,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:25:21', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681852893822451712, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:25:52', '2023-07-20 10:25:52', 22,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:25:52', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681852897651851264, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:25:53', '2023-07-20 10:25:53', 16,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:25:53', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681853481704488960, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:28:12', '2023-07-20 10:28:12', 28,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:28:12', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681853485638746112, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:28:13', '2023-07-20 10:28:13', 21,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:28:13', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681853546867195904, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:28:27', '2023-07-20 10:28:28', 101,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:28:28', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681853547370512384, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:28:28', '2023-07-20 10:28:28', 68,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:28:28', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681853551195717632, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:28:29', '2023-07-20 10:28:29', 20,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:28:29', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681853772168429568, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:29:21', '2023-07-20 10:29:21', 19,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:29:21', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681853775851028480, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:29:22', '2023-07-20 10:29:22', 20,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:29:22', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681853791684526080, '127.0.0.1', 'OPT', '超级管理员', '登录日志-清空日志',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'clear', '/loginLog/clear', 'DELETE',
        '2023-07-20 10:29:26', '2023-07-20 10:29:26', 11,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:29:26', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681853799154581504, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:29:28', '2023-07-20 10:29:28', 19,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:29:28', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681853821631856640, '127.0.0.1', 'OPT', '超级管理员', '登录日志-清空日志',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'clear', '/loginLog/clear', 'DELETE',
        '2023-07-20 10:29:33', '2023-07-20 10:29:33', 5,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:29:33', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681853828615372800, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:29:35', '2023-07-20 10:29:35', 9,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:29:35', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681855238698434560, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:35:11', '2023-07-20 10:35:11', 22,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:35:11', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681855245862305792, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:35:13', '2023-07-20 10:35:13', 9,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:35:13', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681855308978192384, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:35:28', '2023-07-20 10:35:28', 9,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:35:28', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681855316293058560, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:35:29', '2023-07-20 10:35:29', 11,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:35:29', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681855504780886016, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:36:14', '2023-07-20 10:36:14', 9,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:36:14', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681855512313856000, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 10:36:16', '2023-07-20 10:36:16', 11,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 10:36:16', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681868354006024192, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 11:27:18', '2023-07-20 11:27:18', 21,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 11:27:18', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681868354098298880, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 11:27:18', '2023-07-20 11:27:18', 14,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 11:27:18', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681886942972608512, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 12:41:10', '2023-07-20 12:41:10', 35,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 12:41:10', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681886942976802816, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 12:41:10', '2023-07-20 12:41:10', 36,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 12:41:10', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681906045439442944, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 13:57:04', '2023-07-20 13:57:04', 13,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 13:57:04', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681906045623992320, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 13:57:04', '2023-07-20 13:57:04', 9,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 13:57:04', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681932717974880256, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 15:43:03', '2023-07-20 15:43:03', 50,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:43:03', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681932724371193856, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 15:43:05', '2023-07-20 15:43:05', 10,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:43:05', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681932746433232896, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 15:43:10', '2023-07-20 15:43:10', 10,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:43:10', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681932753320280064, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 15:43:12', '2023-07-20 15:43:12', 9,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:43:12', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681932773729763328, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 15:43:17', '2023-07-20 15:43:17', 10,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:43:17', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681932780507758592, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 15:43:18', '2023-07-20 15:43:18', 9,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:43:18', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681932806533414912, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 15:43:25', '2023-07-20 15:43:25', 11,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:43:25', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681932813420462080, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 15:43:26', '2023-07-20 15:43:26', 9,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:43:26', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681934298208272384, '127.0.0.1', 'OPT', '超级管理员', '字典-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.DictController', 'page', '/dict/page', 'POST', '2023-07-20 15:49:20',
        '2023-07-20 15:49:20', 29,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:49:20', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681934380626345984, '127.0.0.1', 'OPT', '超级管理员', '字典-新增',
        'cn.lmx.kpu.authority.controller.common.DictController', 'save', '/dict', 'POST', '2023-07-20 15:49:40',
        '2023-07-20 15:49:40', 25,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:49:40', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681934381020610560, '127.0.0.1', 'OPT', '超级管理员', '字典-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.DictController', 'page', '/dict/page', 'POST', '2023-07-20 15:49:40',
        '2023-07-20 15:49:40', 39,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:49:40', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681934774333079552, '127.0.0.1', 'OPT', '超级管理员', '字典-查询:1681934380567625728',
        'cn.lmx.kpu.authority.controller.common.DictController', 'getDetail', '/dict/detail', 'GET',
        '2023-07-20 15:51:14', '2023-07-20 15:51:14', 14,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:51:14', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681934786060353536, '127.0.0.1', 'OPT', '超级管理员', '字典-修改:null',
        'cn.lmx.kpu.authority.controller.common.DictController', 'update', '/dict', 'PUT', '2023-07-20 15:51:16',
        '2023-07-20 15:51:16', 22,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:51:16', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681934786324594688, '127.0.0.1', 'OPT', '超级管理员', '字典-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.DictController', 'page', '/dict/page', 'POST', '2023-07-20 15:51:17',
        '2023-07-20 15:51:17', 19,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:51:17', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681934805492563968, '127.0.0.1', 'OPT', '超级管理员', '字典项-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.DictItemController', 'page', '/dictItem/page', 'POST',
        '2023-07-20 15:51:21', '2023-07-20 15:51:21', 23,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:51:21', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681935022564573184, '127.0.0.1', 'OPT', '超级管理员', '字典项-新增',
        'cn.lmx.kpu.authority.controller.common.DictItemController', 'save', '/dictItem', 'POST', '2023-07-20 15:52:13',
        '2023-07-20 15:52:13', 18,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:52:13', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681935022845591552, '127.0.0.1', 'OPT', '超级管理员', '字典项-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.DictItemController', 'page', '/dictItem/page', 'POST',
        '2023-07-20 15:52:13', '2023-07-20 15:52:13', 21,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:52:13', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681935121504010240, '127.0.0.1', 'OPT', '超级管理员', '字典项-新增',
        'cn.lmx.kpu.authority.controller.common.DictItemController', 'save', '/dictItem', 'POST', '2023-07-20 15:52:36',
        '2023-07-20 15:52:36', 16,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:52:36', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681935121768251392, '127.0.0.1', 'OPT', '超级管理员', '字典项-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.DictItemController', 'page', '/dictItem/page', 'POST',
        '2023-07-20 15:52:37', '2023-07-20 15:52:37', 18,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:52:37', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681935384168103936, '127.0.0.1', 'OPT', '超级管理员', '字典项-新增',
        'cn.lmx.kpu.authority.controller.common.DictItemController', 'save', '/dictItem', 'POST', '2023-07-20 15:53:39',
        '2023-07-20 15:53:39', 26,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:53:39', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681935384453316608, '127.0.0.1', 'OPT', '超级管理员', '字典项-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.DictItemController', 'page', '/dictItem/page', 'POST',
        '2023-07-20 15:53:39', '2023-07-20 15:53:39', 18,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:53:39', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681935418318127104, '127.0.0.1', 'OPT', '超级管理员', '字典项-查询:1681935121462067200',
        'cn.lmx.kpu.authority.controller.common.DictItemController', 'getDetail', '/dictItem/detail', 'GET',
        '2023-07-20 15:53:47', '2023-07-20 15:53:47', 5,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:53:47', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681935436508823552, '127.0.0.1', 'OPT', '超级管理员', '字典项-修改:null',
        'cn.lmx.kpu.authority.controller.common.DictItemController', 'update', '/dictItem', 'PUT',
        '2023-07-20 15:53:52', '2023-07-20 15:53:52', 20,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:53:52', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681935436789841920, '127.0.0.1', 'OPT', '超级管理员', '字典项-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.DictItemController', 'page', '/dictItem/page', 'POST',
        '2023-07-20 15:53:52', '2023-07-20 15:53:52', 19,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:53:52', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681935517500833792, '127.0.0.1', 'OPT', '超级管理员', '字典项-新增',
        'cn.lmx.kpu.authority.controller.common.DictItemController', 'save', '/dictItem', 'POST', '2023-07-20 15:54:11',
        '2023-07-20 15:54:11', 16,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:54:11', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681935517781852160, '127.0.0.1', 'OPT', '超级管理员', '字典项-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.DictItemController', 'page', '/dictItem/page', 'POST',
        '2023-07-20 15:54:11', '2023-07-20 15:54:11', 18,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:54:11', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681935605212119040, '127.0.0.1', 'OPT', '超级管理员', '字典项-新增',
        'cn.lmx.kpu.authority.controller.common.DictItemController', 'save', '/dictItem', 'POST', '2023-07-20 15:54:32',
        '2023-07-20 15:54:32', 14,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:54:32', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681935605556051968, '127.0.0.1', 'OPT', '超级管理员', '字典项-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.DictItemController', 'page', '/dictItem/page', 'POST',
        '2023-07-20 15:54:32', '2023-07-20 15:54:32', 29,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:54:32', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681935700594786304, '127.0.0.1', 'OPT', '超级管理员', '字典项-新增',
        'cn.lmx.kpu.authority.controller.common.DictItemController', 'save', '/dictItem', 'POST', '2023-07-20 15:54:55',
        '2023-07-20 15:54:55', 17,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:54:55', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681935700888387584, '127.0.0.1', 'OPT', '超级管理员', '字典项-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.DictItemController', 'page', '/dictItem/page', 'POST',
        '2023-07-20 15:54:55', '2023-07-20 15:54:55', 19,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:54:55', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681935736321867776, '127.0.0.1', 'OPT', '超级管理员', '字典-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.DictController', 'page', '/dict/page', 'POST', '2023-07-20 15:55:03',
        '2023-07-20 15:55:03', 21,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:55:03', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681936030162223104, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 15:56:13', '2023-07-20 15:56:13', 19,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:56:13', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681936030665539584, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 15:56:13', '2023-07-20 15:56:13', 9,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:56:13', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681936696746180608, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 15:58:52', '2023-07-20 15:58:52', 19,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:58:52', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681936697404686336, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 15:58:52', '2023-07-20 15:58:52', 33,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:58:52', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681936738932490240, '127.0.0.1', 'OPT', '超级管理员', '字典-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.DictController', 'page', '/dict/page', 'POST', '2023-07-20 15:59:02',
        '2023-07-20 15:59:02', 28,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:59:02', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681936777352314880, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 15:59:11', '2023-07-20 15:59:11', 8,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:59:11', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681936795060666368, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 15:59:15', '2023-07-20 15:59:15', 9,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:59:15', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681936810227269632, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 15:59:19', '2023-07-20 15:59:19', 9,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:59:19', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681936820406845440, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 15:59:22', '2023-07-20 15:59:22', 8,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 15:59:22', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681957278065885184, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 17:20:39', '2023-07-20 17:20:39', 311,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 17:20:39', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681957292955664384, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 17:20:43', '2023-07-20 17:20:43', 27,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 17:20:43', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681957317026775040, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 17:20:48', '2023-07-20 17:20:48', 20,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 17:20:48', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681961742394982400, '127.0.0.1', 'OPT', '超级管理员', '登录日志-查询:1681957274165182464',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'getDetail', '/loginLog/detail', 'GET',
        '2023-07-20 17:38:23', '2023-07-20 17:38:23', 29,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 17:38:23', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681961860582080512, '127.0.0.1', 'OPT', '超级管理员', '登录日志-查询:1681957274165182464',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'getDetail', '/loginLog/detail', 'GET',
        '2023-07-20 17:38:52', '2023-07-20 17:38:52', 10,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 17:38:52', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681962069223538688, '127.0.0.1', 'OPT', '超级管理员', '登录日志-查询:1681957274165182464',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'getDetail', '/loginLog/detail', 'GET',
        '2023-07-20 17:39:41', '2023-07-20 17:39:41', 6,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 17:39:41', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681962148382638080, '127.0.0.1', 'OPT', '超级管理员', '登录日志-查询:1681957274165182464',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'getDetail', '/loginLog/detail', 'GET',
        '2023-07-20 17:40:00', '2023-07-20 17:40:00', 4,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 17:40:00', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681962189239353344, '127.0.0.1', 'OPT', '超级管理员', '登录日志-查询:1681957274165182464',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'getDetail', '/loginLog/detail', 'GET',
        '2023-07-20 17:40:10', '2023-07-20 17:40:10', 6,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 17:40:10', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681962370601058304, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 17:40:53', '2023-07-20 17:40:53', 23,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 17:40:53', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681962830653292544, '127.0.0.1', 'OPT', '超级管理员', '登录日志-查询:1681957274165182464',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'getDetail', '/loginLog/detail', 'GET',
        '2023-07-20 17:42:43', '2023-07-20 17:42:43', 33,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 17:42:43', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681964549793644544, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 17:49:33', '2023-07-20 17:49:33', 27,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 17:49:33', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681964708409638912, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 17:50:11', '2023-07-20 17:50:11', 28,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 17:50:11', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681964866589425664, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 17:50:48', '2023-07-20 17:50:48', 23,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 17:50:48', 2);
INSERT INTO `c_opt_log` (`id`, `request_ip`, `type`, `nick_name`, `description`, `class_path`, `action_method`,
                         `request_uri`, `http_method`, `start_time`, `finish_time`, `consuming_time`, `ua`,
                         `create_time`, `created_by`)
VALUES (1681964942170783744, '127.0.0.1', 'OPT', '超级管理员', '登录日志-分页列表查询:第1页, 显示10行',
        'cn.lmx.kpu.authority.controller.common.LoginLogController', 'page', '/loginLog/page', 'POST',
        '2023-07-20 17:51:06', '2023-07-20 17:51:06', 22,
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
        '2023-07-20 17:51:06', 2);
COMMIT;

-- ----------------------------
-- Table structure for c_opt_log_ext
-- ----------------------------
DROP TABLE IF EXISTS `c_opt_log_ext`;
CREATE TABLE `c_opt_log_ext`
(
    `id`          bigint NOT NULL COMMENT '主键',
    `params`      longtext COMMENT '请求参数',
    `result`      longtext COMMENT '返回值',
    `ex_detail`   longtext COMMENT '异常描述',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `created_by`  bigint   DEFAULT NULL COMMENT '创建人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='系统日志扩展';

-- ----------------------------
-- Records of c_opt_log_ext
-- ----------------------------
BEGIN;
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681674428380872704, '[]', NULL, NULL, '2023-07-19 22:36:42', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681674428611559424,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-19 22:36:42', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681675755601264640, '[]', NULL, NULL, '2023-07-19 22:41:59', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681675756150718464,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-19 22:41:59', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681812898826944512,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 07:46:56', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681812923325874176, '[]', NULL, NULL, '2023-07-20 07:47:02', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681812971354849280, '[1681648876672516096]',
        '{\"code\":0,\"data\":{\"id\":\"1681648876672516096\",\"createTime\":\"2023-07-19 20:55:10\",\"createdBy\":\"2\",\"updateTime\":\"2023-07-19 21:42:35\",\"updatedBy\":\"2\",\"parentId\":\"1681647656843083776\",\"sortValue\":1,\"children\":null,\"title\":\"登录日志列表\",\"echoMap\":{},\"code\":\"\",\"name\":\"SystemLoginLogList\",\"remarks\":\"\",\"resourceType\":\"20\",\"isGeneral\":false,\"redirect\":\"\",\"path\":\"\",\"component\":\"system/loginLog/list.vue\",\"state\":true,\"icon\":\"ep:collection\",\"activeIcon\":\"\",\"group\":\"\",\"dataScope\":null,\"customClass\":null,\"isDef\":false,\"treeGrade\":3,\"treePath\":\"/1681647656843083776/20/10/\",\"metaJson\":\"{\\\"defaultOpened\\\":false,\\\"permanent\\\":false,\\\"auth\\\":[],\\\"sidebar\\\":false,\\\"breadcrumb\\\":false,\\\"activeMenu\\\":\\\"\\\",\\\"cache\\\":[\\\"SystemLoginLogDetail\\\",\\\"SystemLoginLogCreate\\\"],\\\"noCache\\\":[],\\\"badge\\\":\\\"\\\",\\\"link\\\":\\\"\\\",\\\"iframe\\\":\\\"\\\",\\\"copyright\\\":false,\\\"paddingBottom\\\":\\\"0px\\\"}\",\"auths\":[]},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689810433595\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 07:47:14', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681813019815837696, NULL,
        '{\"code\":0,\"data\":{\"id\":\"1681648876672516096\",\"createTime\":null,\"createdBy\":null,\"updateTime\":\"2023-07-20 07:47:25\",\"updatedBy\":\"2\",\"parentId\":\"1681647656843083776\",\"sortValue\":1,\"children\":null,\"title\":\"登录日志列表\",\"code\":null,\"name\":\"SystemLoginLogList\",\"remarks\":\"\",\"resourceType\":\"20\",\"isGeneral\":false,\"redirect\":null,\"path\":\"\",\"component\":\"system/loginLog/list.vue\",\"state\":true,\"icon\":\"\",\"activeIcon\":\"\",\"group\":\"\",\"dataScope\":null,\"customClass\":null,\"isDef\":false,\"treeGrade\":3,\"treePath\":\"/1681647656843083776/20/10/\",\"metaJson\":\"{\\\"defaultOpened\\\":false,\\\"permanent\\\":false,\\\"auth\\\":[],\\\"sidebar\\\":false,\\\"breadcrumb\\\":false,\\\"activeMenu\\\":\\\"\\\",\\\"cache\\\":[\\\"SystemLoginLogDetail\\\",\\\"SystemLoginLogCreate\\\"],\\\"noCache\\\":[],\\\"badge\\\":\\\"\\\",\\\"link\\\":\\\"\\\",\\\"iframe\\\":\\\"\\\",\\\"copyright\\\":false,\\\"paddingBottom\\\":\\\"0px\\\"}\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689810445151\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 07:47:25', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681813021787160576, '[]', NULL, NULL, '2023-07-20 07:47:26', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681813072869588992, '[1681647656843083776]',
        '{\"code\":0,\"data\":{\"id\":\"1681647656843083776\",\"createTime\":\"2023-07-19 20:50:20\",\"createdBy\":\"2\",\"updateTime\":\"2023-07-19 21:12:44\",\"updatedBy\":\"2\",\"parentId\":\"20\",\"sortValue\":6,\"children\":null,\"title\":\"登录日志\",\"echoMap\":{},\"code\":\"\",\"name\":\"\",\"remarks\":\"\",\"resourceType\":\"10\",\"isGeneral\":false,\"redirect\":\"\",\"path\":\"loginLog\",\"component\":\"\",\"state\":true,\"icon\":\"\",\"activeIcon\":\"\",\"group\":\"\",\"dataScope\":null,\"customClass\":null,\"isDef\":false,\"treeGrade\":2,\"treePath\":\"/20/10/\",\"metaJson\":\"{\\\"defaultOpened\\\":false,\\\"permanent\\\":false,\\\"auth\\\":[],\\\"sidebar\\\":true,\\\"breadcrumb\\\":true,\\\"activeMenu\\\":\\\"\\\",\\\"cache\\\":[],\\\"noCache\\\":[],\\\"badge\\\":\\\"\\\",\\\"link\\\":\\\"\\\",\\\"iframe\\\":\\\"\\\",\\\"copyright\\\":false,\\\"paddingBottom\\\":\\\"0px\\\"}\",\"auths\":[]},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689810457802\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 07:47:38', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681813400872550400, NULL,
        '{\"code\":0,\"data\":{\"id\":\"1681647656843083776\",\"createTime\":null,\"createdBy\":null,\"updateTime\":\"2023-07-20 07:48:55\",\"updatedBy\":\"2\",\"parentId\":\"20\",\"sortValue\":6,\"children\":null,\"title\":\"登录日志\",\"code\":null,\"name\":\"\",\"remarks\":\"\",\"resourceType\":\"10\",\"isGeneral\":false,\"redirect\":null,\"path\":\"loginLog\",\"component\":\"\",\"state\":true,\"icon\":\"cil:notes\",\"activeIcon\":\"\",\"group\":\"\",\"dataScope\":null,\"customClass\":null,\"isDef\":false,\"treeGrade\":2,\"treePath\":\"/20/10/\",\"metaJson\":\"{\\\"defaultOpened\\\":false,\\\"permanent\\\":false,\\\"auth\\\":[],\\\"sidebar\\\":true,\\\"breadcrumb\\\":true,\\\"activeMenu\\\":\\\"\\\",\\\"cache\\\":[],\\\"noCache\\\":[],\\\"badge\\\":\\\"\\\",\\\"link\\\":\\\"\\\",\\\"iframe\\\":\\\"\\\",\\\"copyright\\\":false,\\\"paddingBottom\\\":\\\"0px\\\"}\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689810536004\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 07:48:56', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681813402676101120, '[]', NULL, NULL, '2023-07-20 07:48:56', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681813432279498752, '[]', NULL, NULL, '2023-07-20 07:49:04', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681813443209854976,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 07:49:06', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681813506355101696, '[1681812886797680640]',
        '{\"code\":0,\"data\":{\"id\":\"1681812886797680640\",\"createTime\":\"2023-07-20 07:46:53\",\"createdBy\":\"2\",\"requestIp\":\"127.0.0.1\",\"userId\":\"2\",\"nickName\":\"超级管理员\",\"username\":\"kpu\",\"description\":\"登录成功\",\"loginDate\":\"2023-07-20\",\"ua\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36\",\"browser\":\"Chrome\",\"browserVersion\":\"114.0.0.0\",\"operatingSystem\":\"OSX\",\"location\":\"0|0|0|内网IP|内网IP\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689810561152\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 07:49:21', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681814712502714368,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 07:54:09', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681816359215824896,
        '[{\"model\":{\"key\":null,\"value\":null,\"name\":null,\"remarks\":null,\"state\":null,\"readonly\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:00:41', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681816427511676928,
        '[{\"model\":{\"classify\":null,\"key\":null,\"name\":null,\"state\":null,\"remark\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:00:58', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681816441860390912,
        '[{\"model\":{\"username\":null,\"nickName\":null,\"orgId\":null,\"stationId\":null,\"readonly\":null,\"email\":null,\"mobile\":null,\"sex\":null,\"state\":null,\"avatar\":null,\"nation\":null,\"education\":null,\"positionStatus\":null,\"workDescribe\":null,\"passwordErrorLastTime\":null,\"passwordErrorNum\":null,\"passwordExpireTime\":null,\"password\":null,\"salt\":null,\"lastLoginTime\":null,\"createdOrgId\":null,\"scope\":null,\"roleId\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:01:01', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681816470197108736,
        '[{\"model\":{\"classify\":null,\"key\":null,\"name\":null,\"state\":null,\"remark\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:01:08', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681816486315819008,
        '[{\"model\":{\"key\":null,\"value\":null,\"name\":null,\"remarks\":null,\"state\":null,\"readonly\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:01:12', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681816503701209088, '[false]', NULL, NULL, '2023-07-20 08:01:16', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681816503755735040,
        '[{\"model\":{\"name\":null,\"code\":null,\"remarks\":null,\"state\":null,\"readonly\":null,\"category\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:01:16', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681816530406342656, '[1486215142227050496]',
        '{\"code\":0,\"data\":[\"20\",\"1677590913037107200\",\"1677585709487095808\",\"10\",\"1677509487545548800\",\"1677509537856225280\"],\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689811282143\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 08:01:22', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681817031994769408,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:03:22', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681817353421062144,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:04:38', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681817420303433728,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:04:54', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681817890312945664,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:06:46', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681818419134988288,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:08:52', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681818759678918656, '[1681654828767379456]',
        '{\"code\":0,\"data\":{\"id\":\"1681654828767379456\",\"createTime\":\"2023-07-19 21:18:49\",\"createdBy\":\"2\",\"requestIp\":\"127.0.0.1\",\"userId\":\"2\",\"nickName\":\"超级管理员\",\"username\":\"kpu\",\"description\":\"登录成功\",\"loginDate\":\"2023-07-19\",\"ua\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36\",\"browser\":\"Chrome\",\"browserVersion\":\"114.0.0.0\",\"operatingSystem\":\"OSX\",\"location\":\"0|0|0|内网IP|内网IP\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689811813642\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 08:10:14', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681820181900296192,
        '[{\"model\":{\"username\":null,\"nickName\":null,\"orgId\":null,\"stationId\":null,\"readonly\":null,\"email\":null,\"mobile\":null,\"sex\":null,\"state\":null,\"avatar\":null,\"nation\":null,\"education\":null,\"positionStatus\":null,\"workDescribe\":null,\"passwordErrorLastTime\":null,\"passwordErrorNum\":null,\"passwordExpireTime\":null,\"password\":null,\"salt\":null,\"lastLoginTime\":null,\"createdOrgId\":null,\"scope\":null,\"roleId\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:15:53', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681820203807145984,
        '[{\"model\":{\"name\":null,\"code\":null,\"remarks\":null,\"state\":null,\"readonly\":null,\"category\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:15:58', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681820203861671936, '[false]', NULL, NULL, '2023-07-20 08:15:58', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681820217111478272,
        '[{\"model\":{\"username\":null,\"nickName\":null,\"orgId\":null,\"stationId\":null,\"readonly\":null,\"email\":null,\"mobile\":null,\"sex\":null,\"state\":null,\"avatar\":null,\"nation\":null,\"education\":null,\"positionStatus\":null,\"workDescribe\":null,\"passwordErrorLastTime\":null,\"passwordErrorNum\":null,\"passwordExpireTime\":null,\"password\":null,\"salt\":null,\"lastLoginTime\":null,\"createdOrgId\":null,\"scope\":null,\"roleId\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:16:01', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681823519874220032,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:29:09', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681824391744192512,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:32:36', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681824415836274688,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:32:42', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681824755314851840, '[1681812886797680640]',
        '{\"code\":0,\"data\":{\"id\":\"1681812886797680640\",\"createTime\":\"2023-07-20 07:46:53\",\"createdBy\":\"2\",\"requestIp\":\"127.0.0.1\",\"userId\":\"2\",\"nickName\":\"超级管理员\",\"username\":\"kpu\",\"description\":\"登录成功\",\"loginDate\":\"2023-07-20\",\"ua\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36\",\"browser\":\"Chrome\",\"browserVersion\":\"114.0.0.0\",\"operatingSystem\":\"OSX\",\"location\":\"0|0|0|内网IP|内网IP\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689813243071\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 08:34:03', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681824955395735552,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:34:51', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681826688595394560,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:41:44', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681826688721223680,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 08:41:44', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681850120322154496,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:14:51', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681850120552841216,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:14:51', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681850785186447360,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:17:29', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681850788818714624,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:17:30', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851012157014016,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:18:23', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851015986413568,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:18:24', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851057463885824,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:18:34', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851057891704832,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:18:34', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851061419114496,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:18:35', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851066188038144,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:18:36', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851290063208448,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:19:29', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851290495221760,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:19:30', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851290923040768,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:19:30', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851294144266240,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:19:30', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851299059990528,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:19:32', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851304030240768,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:19:33', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851354236059648,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:19:45', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851354693238784,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:19:45', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851358170316800,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:19:46', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851363316727808,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:19:47', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851407172370432,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:19:57', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851411219873792,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:19:58', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851538558943232,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:20:29', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851542593863680,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:20:30', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851614714920960,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:20:47', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851618573680640,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:20:48', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681851810366619648,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:21:34', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681852232066138112,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:23:14', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681852236231081984,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:23:15', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681852566142451712,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:24:34', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681852570093486080,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:24:35', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681852759302733824,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:25:20', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681852763123744768,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:25:21', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681852893822451712,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:25:52', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681852897651851264,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:25:53', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681853481704488960,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:28:12', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681853485638746112,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:28:13', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681853546867195904,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:28:28', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681853547370512384,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:28:28', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681853551195717632,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:28:29', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681853772168429568,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:29:21', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681853775851028480,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:29:22', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681853791684526080, '[1]',
        '{\"code\":0,\"data\":false,\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689820165924\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 10:29:26', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681853799154581504,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:29:28', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681853821631856640, '[9]',
        '{\"code\":0,\"data\":true,\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689820173064\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 10:29:33', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681853828615372800,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:29:35', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681855238698434560,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:35:11', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681855245862305792,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:35:13', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681855308978192384,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:35:28', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681855316293058560,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:35:29', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681855504780886016,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:36:14', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681855512313856000,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 10:36:16', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681868354006024192,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 11:27:18', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681868354098298880,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 11:27:18', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681886942972608512,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 12:41:10', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681886942976802816,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 12:41:10', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681906045439442944,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 13:57:04', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681906045623992320,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 13:57:04', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681932717974880256,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:43:03', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681932724371193856,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:43:05', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681932746433232896,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:43:10', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681932753320280064,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:43:12', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681932773729763328,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:43:17', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681932780507758592,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:43:18', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681932806533414912,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:43:25', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681932813420462080,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:43:26', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681934298208272384,
        '[{\"model\":{\"classify\":null,\"key\":null,\"name\":null,\"state\":null,\"remark\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:49:20', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681934380626345984, NULL,
        '{\"code\":0,\"data\":{\"id\":\"1681934380567625728\",\"createTime\":\"2023-07-20 15:49:39\",\"createdBy\":\"2\",\"updateTime\":\"2023-07-20 15:49:39\",\"updatedBy\":\"2\",\"parentId\":\"0\",\"parentKey\":null,\"classify\":null,\"key\":\"LoginStatusEnum\",\"name\":\"登录状态\",\"state\":true,\"remark\":\"\",\"sortValue\":null,\"icon\":null,\"cssStyle\":null,\"cssClass\":null},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689839379802\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 15:49:40', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681934381020610560,
        '[{\"model\":{\"classify\":null,\"key\":\"LoginStatusEnum\",\"name\":null,\"state\":null,\"remark\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:49:40', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681934774333079552, '[1681934380567625728]',
        '{\"code\":0,\"data\":{\"id\":\"1681934380567625728\",\"createTime\":\"2023-07-20 15:49:40\",\"createdBy\":\"2\",\"updateTime\":\"2023-07-20 15:49:40\",\"updatedBy\":\"2\",\"echoMap\":{},\"key\":\"LoginStatusEnum\",\"classify\":\"\",\"name\":\"登录状态\",\"state\":true,\"remark\":\"\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689839473692\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 15:51:14', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681934786060353536, NULL,
        '{\"code\":0,\"data\":{\"id\":\"1681934380567625728\",\"createTime\":null,\"createdBy\":null,\"updateTime\":\"2023-07-20 15:51:16\",\"updatedBy\":\"2\",\"parentId\":null,\"parentKey\":null,\"classify\":null,\"key\":\"LoginStatusEnum\",\"name\":\"登录状态\",\"state\":true,\"remark\":\"[01-登录成功 02-验证码错误 03-密码错误 04-账号锁定 05-切换租户 06-短信验证码错误]\",\"sortValue\":null,\"icon\":null,\"cssStyle\":null,\"cssClass\":null},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689839476467\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 15:51:16', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681934786324594688,
        '[{\"model\":{\"classify\":null,\"key\":\"LoginStatusEnum\",\"name\":null,\"state\":null,\"remark\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:51:17', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681934805492563968,
        '[{\"model\":{\"parentId\":\"1681934380567625728\",\"classify\":null,\"key\":null,\"name\":null,\"state\":null,\"remark\":null,\"sortValue\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"sortValue\",\"order\":\"ascend\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:51:21', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681935022564573184, NULL,
        '{\"code\":0,\"data\":{\"id\":\"1681935022518435840\",\"createTime\":\"2023-07-20 15:52:12\",\"createdBy\":\"2\",\"updateTime\":\"2023-07-20 15:52:12\",\"updatedBy\":\"2\",\"parentId\":\"1681934380567625728\",\"parentKey\":\"LoginStatusEnum\",\"classify\":null,\"key\":\"01\",\"name\":\"登录成功\",\"state\":true,\"remark\":\"\",\"sortValue\":1,\"icon\":\"\",\"cssStyle\":\"\",\"cssClass\":\"\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689839532858\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 15:52:13', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681935022845591552,
        '[{\"model\":{\"parentId\":\"1681934380567625728\",\"classify\":null,\"key\":null,\"name\":null,\"state\":null,\"remark\":null,\"sortValue\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"sortValue\",\"order\":\"ascend\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:52:13', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681935121504010240, NULL,
        '{\"code\":0,\"data\":{\"id\":\"1681935121462067200\",\"createTime\":\"2023-07-20 15:52:36\",\"createdBy\":\"2\",\"updateTime\":\"2023-07-20 15:52:36\",\"updatedBy\":\"2\",\"parentId\":\"1681934380567625728\",\"parentKey\":\"LoginStatusEnum\",\"classify\":null,\"key\":\"02\",\"name\":\"验证码错误\",\"state\":true,\"remark\":\"\",\"sortValue\":1,\"icon\":\"\",\"cssStyle\":\"\",\"cssClass\":\"\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689839556449\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 15:52:36', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681935121768251392,
        '[{\"model\":{\"parentId\":\"1681934380567625728\",\"classify\":null,\"key\":null,\"name\":null,\"state\":null,\"remark\":null,\"sortValue\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"sortValue\",\"order\":\"ascend\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:52:37', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681935384168103936, NULL,
        '{\"code\":0,\"data\":{\"id\":\"1681935384126160896\",\"createTime\":\"2023-07-20 15:53:39\",\"createdBy\":\"2\",\"updateTime\":\"2023-07-20 15:53:39\",\"updatedBy\":\"2\",\"parentId\":\"1681934380567625728\",\"parentKey\":\"LoginStatusEnum\",\"classify\":null,\"key\":\"03\",\"name\":\"密码错误\",\"state\":true,\"remark\":\"\",\"sortValue\":3,\"icon\":\"\",\"cssStyle\":\"\",\"cssClass\":\"\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689839619063\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 15:53:39', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681935384453316608,
        '[{\"model\":{\"parentId\":\"1681934380567625728\",\"classify\":null,\"key\":null,\"name\":null,\"state\":null,\"remark\":null,\"sortValue\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"sortValue\",\"order\":\"ascend\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:53:39', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681935418318127104, '[1681935121462067200]',
        '{\"code\":0,\"data\":{\"id\":\"1681935121462067200\",\"createTime\":\"2023-07-20 15:52:36\",\"createdBy\":\"2\",\"updateTime\":\"2023-07-20 15:52:36\",\"updatedBy\":\"2\",\"parentId\":\"1681934380567625728\",\"sortValue\":1,\"children\":null,\"echoMap\":{},\"key\":\"02\",\"name\":\"验证码错误\",\"state\":true,\"remark\":\"\",\"icon\":\"\",\"cssStyle\":\"\",\"cssClass\":\"\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689839627229\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 15:53:47', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681935436508823552, NULL,
        '{\"code\":0,\"data\":{\"id\":\"1681935121462067200\",\"createTime\":null,\"createdBy\":null,\"updateTime\":\"2023-07-20 15:53:51\",\"updatedBy\":\"2\",\"parentId\":\"1681934380567625728\",\"parentKey\":\"LoginStatusEnum\",\"classify\":null,\"key\":\"02\",\"name\":\"验证码错误\",\"state\":true,\"remark\":\"\",\"sortValue\":2,\"icon\":\"\",\"cssStyle\":\"\",\"cssClass\":\"\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689839631549\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 15:53:52', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681935436789841920,
        '[{\"model\":{\"parentId\":\"1681934380567625728\",\"classify\":null,\"key\":null,\"name\":null,\"state\":null,\"remark\":null,\"sortValue\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"sortValue\",\"order\":\"ascend\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:53:52', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681935517500833792, NULL,
        '{\"code\":0,\"data\":{\"id\":\"1681935517454696448\",\"createTime\":\"2023-07-20 15:54:10\",\"createdBy\":\"2\",\"updateTime\":\"2023-07-20 15:54:10\",\"updatedBy\":\"2\",\"parentId\":\"1681934380567625728\",\"parentKey\":\"LoginStatusEnum\",\"classify\":null,\"key\":\"04\",\"name\":\"账号锁定\",\"state\":true,\"remark\":\"\",\"sortValue\":4,\"icon\":\"\",\"cssStyle\":\"\",\"cssClass\":\"\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689839650861\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 15:54:11', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681935517781852160,
        '[{\"model\":{\"parentId\":\"1681934380567625728\",\"classify\":null,\"key\":null,\"name\":null,\"state\":null,\"remark\":null,\"sortValue\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"sortValue\",\"order\":\"ascend\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:54:11', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681935605212119040, NULL,
        '{\"code\":0,\"data\":{\"id\":\"1681935605170176000\",\"createTime\":\"2023-07-20 15:54:31\",\"createdBy\":\"2\",\"updateTime\":\"2023-07-20 15:54:31\",\"updatedBy\":\"2\",\"parentId\":\"1681934380567625728\",\"parentKey\":\"LoginStatusEnum\",\"classify\":null,\"key\":\"05\",\"name\":\"切换租户\",\"state\":true,\"remark\":\"\",\"sortValue\":5,\"icon\":\"\",\"cssStyle\":\"\",\"cssClass\":\"\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689839671775\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 15:54:32', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681935605556051968,
        '[{\"model\":{\"parentId\":\"1681934380567625728\",\"classify\":null,\"key\":null,\"name\":null,\"state\":null,\"remark\":null,\"sortValue\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"sortValue\",\"order\":\"ascend\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:54:32', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681935700594786304, NULL,
        '{\"code\":0,\"data\":{\"id\":\"1681935700552843264\",\"createTime\":\"2023-07-20 15:54:54\",\"createdBy\":\"2\",\"updateTime\":\"2023-07-20 15:54:54\",\"updatedBy\":\"2\",\"parentId\":\"1681934380567625728\",\"parentKey\":\"LoginStatusEnum\",\"classify\":null,\"key\":\"06\",\"name\":\"短信验证码错误\",\"state\":true,\"remark\":\"\",\"sortValue\":6,\"icon\":\"\",\"cssStyle\":\"\",\"cssClass\":\"\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689839694513\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 15:54:55', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681935700888387584,
        '[{\"model\":{\"parentId\":\"1681934380567625728\",\"classify\":null,\"key\":null,\"name\":null,\"state\":null,\"remark\":null,\"sortValue\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"sortValue\",\"order\":\"ascend\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:54:55', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681935736321867776,
        '[{\"model\":{\"classify\":null,\"key\":null,\"name\":null,\"state\":null,\"remark\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:55:03', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681936030162223104,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:56:13', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681936030665539584,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:56:13', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681936696746180608,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:58:52', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681936697404686336,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:58:52', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681936738932490240,
        '[{\"model\":{\"classify\":null,\"key\":null,\"name\":null,\"state\":null,\"remark\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:59:02', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681936777352314880,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:59:11', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681936795060666368,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:59:15', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681936810227269632,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:59:19', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681936820406845440,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 15:59:22', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681957278065885184,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null,\"status\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 17:20:39', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681957292955664384,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null,\"status\":\"01\"},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 17:20:43', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681957317026775040,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null,\"status\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 17:20:48', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681961742394982400, '[1681957274165182464]',
        '{\"code\":0,\"data\":{\"id\":\"1681957274165182464\",\"createTime\":\"2023-07-20 17:20:38\",\"createdBy\":\"2\",\"requestIp\":\"127.0.0.1\",\"userId\":\"2\",\"nickName\":\"超级管理员\",\"username\":\"kpu\",\"description\":\"登录成功\",\"loginDate\":\"2023-07-20\",\"ua\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36\",\"browser\":\"Chrome\",\"browserVersion\":\"114.0.0.0\",\"operatingSystem\":\"OSX\",\"location\":\"0|0|0|内网IP|内网IP\",\"status\":\"01\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689845903376\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 17:38:23', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681961860582080512, '[1681957274165182464]',
        '{\"code\":0,\"data\":{\"id\":\"1681957274165182464\",\"createTime\":\"2023-07-20 17:20:38\",\"createdBy\":\"2\",\"requestIp\":\"127.0.0.1\",\"userId\":\"2\",\"nickName\":\"超级管理员\",\"username\":\"kpu\",\"description\":\"登录成功\",\"loginDate\":\"2023-07-20\",\"ua\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36\",\"browser\":\"Chrome\",\"browserVersion\":\"114.0.0.0\",\"operatingSystem\":\"OSX\",\"location\":\"0|0|0|内网IP|内网IP\",\"status\":\"01\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689845931555\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 17:38:52', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681962069223538688, '[1681957274165182464]',
        '{\"code\":0,\"data\":{\"id\":\"1681957274165182464\",\"createTime\":\"2023-07-20 17:20:38\",\"createdBy\":\"2\",\"requestIp\":\"127.0.0.1\",\"userId\":\"2\",\"nickName\":\"超级管理员\",\"username\":\"kpu\",\"description\":\"登录成功\",\"loginDate\":\"2023-07-20\",\"ua\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36\",\"browser\":\"Chrome\",\"browserVersion\":\"114.0.0.0\",\"operatingSystem\":\"OSX\",\"location\":\"0|0|0|内网IP|内网IP\",\"status\":\"01\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689845981297\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 17:39:41', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681962148382638080, '[1681957274165182464]',
        '{\"code\":0,\"data\":{\"id\":\"1681957274165182464\",\"createTime\":\"2023-07-20 17:20:38\",\"createdBy\":\"2\",\"requestIp\":\"127.0.0.1\",\"userId\":\"2\",\"nickName\":\"超级管理员\",\"username\":\"kpu\",\"description\":\"登录成功\",\"loginDate\":\"2023-07-20\",\"ua\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36\",\"browser\":\"Chrome\",\"browserVersion\":\"114.0.0.0\",\"operatingSystem\":\"OSX\",\"location\":\"0|0|0|内网IP|内网IP\",\"status\":\"01\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689846000149\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 17:40:00', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681962189239353344, '[1681957274165182464]',
        '{\"code\":0,\"data\":{\"id\":\"1681957274165182464\",\"createTime\":\"2023-07-20 17:20:38\",\"createdBy\":\"2\",\"requestIp\":\"127.0.0.1\",\"userId\":\"2\",\"nickName\":\"超级管理员\",\"username\":\"kpu\",\"description\":\"登录成功\",\"loginDate\":\"2023-07-20\",\"ua\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36\",\"browser\":\"Chrome\",\"browserVersion\":\"114.0.0.0\",\"operatingSystem\":\"OSX\",\"location\":\"0|0|0|内网IP|内网IP\",\"status\":\"01\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689846009913\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 17:40:10', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681962370601058304,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null,\"status\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 17:40:53', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681962830653292544, '[1681957274165182464]',
        '{\"code\":0,\"data\":{\"id\":\"1681957274165182464\",\"createTime\":\"2023-07-20 17:20:38\",\"createdBy\":\"2\",\"requestIp\":\"127.0.0.1\",\"userId\":\"2\",\"nickName\":\"超级管理员\",\"username\":\"kpu\",\"description\":\"登录成功\",\"loginDate\":\"2023-07-20\",\"ua\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36\",\"browser\":\"Chrome\",\"browserVersion\":\"114.0.0.0\",\"operatingSystem\":\"OSX\",\"location\":\"0|0|0|内网IP|内网IP\",\"status\":\"01\"},\"msg\":\"ok\",\"path\":null,\"extra\":null,\"timestamp\":\"1689846162838\",\"errorMsg\":\"\",\"isSuccess\":true}',
        NULL, '2023-07-20 17:42:43', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681964549793644544,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null,\"status\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 17:49:33', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681964708409638912,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null,\"status\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 17:50:11', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681964866589425664,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null,\"status\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 17:50:48', 2);
INSERT INTO `c_opt_log_ext` (`id`, `params`, `result`, `ex_detail`, `create_time`, `created_by`)
VALUES (1681964942170783744,
        '[{\"model\":{\"id\":null,\"createTime\":null,\"createdBy\":null,\"requestIp\":null,\"userId\":null,\"nickName\":null,\"username\":null,\"description\":null,\"loginDate\":null,\"ua\":null,\"browser\":null,\"browserVersion\":null,\"operatingSystem\":null,\"location\":null,\"status\":null},\"size\":\"10\",\"current\":\"1\",\"sort\":\"id\",\"order\":\"descending\",\"extra\":{}}]',
        NULL, NULL, '2023-07-20 17:51:06', 2);
COMMIT;

-- ----------------------------
-- Table structure for c_org
-- ----------------------------
DROP TABLE IF EXISTS `c_org`;
CREATE TABLE `c_org`
(
    `id`           bigint       NOT NULL COMMENT 'ID',
    `label`        varchar(255) NOT NULL COMMENT '名称',
    `type_`        char(2)                                                       DEFAULT '' COMMENT '类型 \n@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.ORG_TYPE)',
    `abbreviation` varchar(255)                                                  DEFAULT '' COMMENT '简称',
    `parent_id`    bigint                                                        DEFAULT '0' COMMENT '父ID',
    `tree_path`    varchar(255)                                                  DEFAULT '' COMMENT '树结构',
    `sort_value`   int                                                           DEFAULT '1' COMMENT '排序',
    `state`        bit(1)                                                        DEFAULT b'1' COMMENT '状态',
    `remarks`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '描述',
    `create_time`  datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `created_by`   bigint                                                        DEFAULT NULL COMMENT '创建人',
    `update_time`  datetime                                                      DEFAULT NULL COMMENT '修改时间',
    `updated_by`   bigint                                                        DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_name` (`label`),
    FULLTEXT KEY `fu_path` (`tree_path`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='组织';

-- ----------------------------
-- Records of c_org
-- ----------------------------
BEGIN;
INSERT INTO `c_org` (`id`, `label`, `type_`, `abbreviation`, `parent_id`, `tree_path`, `sort_value`, `state`, `remarks`,
                     `create_time`, `created_by`, `update_time`, `updated_by`)
VALUES (1, '最后集团', '01', '', 0, ',', 1, b'1', '', '2020-11-23 00:05:55', 1, '2020-11-23 00:05:59', 1);
INSERT INTO `c_org` (`id`, `label`, `type_`, `abbreviation`, `parent_id`, `tree_path`, `sort_value`, `state`, `remarks`,
                     `create_time`, `created_by`, `update_time`, `updated_by`)
VALUES (1415864548283383808, '1', '01', '1', 0, ',', 1, b'1', '', '2021-07-16 10:43:08', 2, '2021-07-16 10:43:08', 2);
INSERT INTO `c_org` (`id`, `label`, `type_`, `abbreviation`, `parent_id`, `tree_path`, `sort_value`, `state`, `remarks`,
                     `create_time`, `created_by`, `update_time`, `updated_by`)
VALUES (1415864576410386432, '1-1', '02', '', 1415864548283383808, ',1415864548283383808,', 1, b'1', '',
        '2021-07-16 10:43:15', 2, '2021-07-16 10:43:15', 2);
INSERT INTO `c_org` (`id`, `label`, `type_`, `abbreviation`, `parent_id`, `tree_path`, `sort_value`, `state`, `remarks`,
                     `create_time`, `created_by`, `update_time`, `updated_by`)
VALUES (1415864608656195584, '1-2', '02', '', 1415864548283383808, ',1415864548283383808,', 1, b'1', '',
        '2021-07-16 10:43:23', 2, '2021-07-16 10:43:23', 2);
INSERT INTO `c_org` (`id`, `label`, `type_`, `abbreviation`, `parent_id`, `tree_path`, `sort_value`, `state`, `remarks`,
                     `create_time`, `created_by`, `update_time`, `updated_by`)
VALUES (1415864637190045696, '1-1-1', '02', '', 1415864576410386432, ',1415864548283383808,1415864576410386432,', 1,
        b'1', '', '2021-07-16 10:43:30', 2, '2021-07-16 10:43:30', 2);
INSERT INTO `c_org` (`id`, `label`, `type_`, `abbreviation`, `parent_id`, `tree_path`, `sort_value`, `state`, `remarks`,
                     `create_time`, `created_by`, `update_time`, `updated_by`)
VALUES (1533406176778125312, '办公室', '02', '', 1, ',1,', 1, b'1', '', '2022-06-05 19:11:37', 2, '2022-06-05 19:11:37',
        2);
INSERT INTO `c_org` (`id`, `label`, `type_`, `abbreviation`, `parent_id`, `tree_path`, `sort_value`, `state`, `remarks`,
                     `create_time`, `created_by`, `update_time`, `updated_by`)
VALUES (1533406241466875904, '综合部', '02', '', 1, ',1,', 2, b'1', '', '2022-06-05 19:11:52', 2, '2022-06-05 19:11:52',
        2);
INSERT INTO `c_org` (`id`, `label`, `type_`, `abbreviation`, `parent_id`, `tree_path`, `sort_value`, `state`, `remarks`,
                     `create_time`, `created_by`, `update_time`, `updated_by`)
VALUES (1533406367992250368, '最后集团贵阳分公司', '01', '', 1, ',1,', 10, b'1', '', '2022-06-05 19:12:22', 2,
        '2022-06-05 21:41:33', 2);
INSERT INTO `c_org` (`id`, `label`, `type_`, `abbreviation`, `parent_id`, `tree_path`, `sort_value`, `state`, `remarks`,
                     `create_time`, `created_by`, `update_time`, `updated_by`)
VALUES (1533438004188676096, '研发部', '02', '', 1533406367992250368, ',1533406367992250368,1,', 1, b'1', '',
        '2022-06-05 21:18:05', 2, '2022-06-05 21:18:05', 2);
INSERT INTO `c_org` (`id`, `label`, `type_`, `abbreviation`, `parent_id`, `tree_path`, `sort_value`, `state`, `remarks`,
                     `create_time`, `created_by`, `update_time`, `updated_by`)
VALUES (1533438039630544896, '产品部', '02', '', 1533406367992250368, ',1533406367992250368,1,', 2, b'1', '',
        '2022-06-05 21:18:13', 2, '2022-06-05 21:18:13', 2);
COMMIT;

-- ----------------------------
-- Table structure for c_parameter
-- ----------------------------
DROP TABLE IF EXISTS `c_parameter`;
CREATE TABLE `c_parameter`
(
    `id`          bigint       NOT NULL COMMENT 'ID',
    `key_`        varchar(255) NOT NULL COMMENT '参数键',
    `value`       varchar(255) NOT NULL COMMENT '参数值',
    `name`        varchar(255) NOT NULL COMMENT '参数名称',
    `remarks`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '描述',
    `state`       bit(1)                                                        DEFAULT b'1' COMMENT '状态',
    `readonly_`   bit(1)                                                        DEFAULT b'0' COMMENT '内置',
    `created_by`  bigint                                                        DEFAULT NULL COMMENT '创建人id',
    `create_time` datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `updated_by`  bigint                                                        DEFAULT NULL COMMENT '更新人id',
    `update_time` datetime                                                      DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_key` (`key_`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='参数配置';

-- ----------------------------
-- Records of c_parameter
-- ----------------------------
BEGIN;
INSERT INTO `c_parameter` (`id`, `key_`, `value`, `name`, `remarks`, `state`, `readonly_`, `created_by`, `create_time`,
                           `updated_by`, `update_time`)
VALUES (1, 'LoginPolicy', 'MANY', '登录策略',
        'ONLY_ONE:一个用户只能登录一次; MANY:用户可以任意登录; ONLY_ONE_CLIENT:一个用户在一个应用只能登录一次', b'1',
        b'1', 1, '2020-04-02 21:56:19', 2, '2020-11-30 09:14:44');
INSERT INTO `c_parameter` (`id`, `key_`, `value`, `name`, `remarks`, `state`, `readonly_`, `created_by`, `create_time`,
                           `updated_by`, `update_time`)
VALUES (1681588749147832320, 'test', 'test', 'test', '', b'1', b'0', 2, '2023-07-19 16:56:15', 2,
        '2023-07-19 16:56:15');
COMMIT;

-- ----------------------------
-- Table structure for c_role
-- ----------------------------
DROP TABLE IF EXISTS `c_role`;
CREATE TABLE `c_role`
(
    `id`          bigint      NOT NULL COMMENT 'ID',
    `category`    char(2)                                                       DEFAULT NULL COMMENT '角色类别;[10-功能角色 20-桌面角色 30-数据角色]',
    `name`        varchar(30) NOT NULL                                          DEFAULT '' COMMENT '名称',
    `code`        varchar(20)                                                   DEFAULT '' COMMENT '编码',
    `remarks`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '描述',
    `state`       bit(1)                                                        DEFAULT b'1' COMMENT '状态',
    `readonly_`   bit(1)                                                        DEFAULT b'0' COMMENT '内置角色',
    `created_by`  bigint                                                        DEFAULT NULL COMMENT '创建人id',
    `create_time` datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `updated_by`  bigint                                                        DEFAULT NULL COMMENT '更新人id',
    `update_time` datetime                                                      DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_code` (`code`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='角色';

-- ----------------------------
-- Records of c_role
-- ----------------------------
BEGIN;
INSERT INTO `c_role` (`id`, `category`, `name`, `code`, `remarks`, `state`, `readonly_`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (1, '10', '超级管理员', 'SUPER_ADMIN', '内置管理员(二次开发必须保留)', b'1', b'1', 1, '2020-11-22 23:46:00', 1,
        '2020-11-22 23:46:00');
INSERT INTO `c_role` (`id`, `category`, `name`, `code`, `remarks`, `state`, `readonly_`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (2, '10', '平台管理员', 'PT_ADMIN', '内置运营专用平台管理员(二次开发必须保留)', b'1', b'1', 1,
        '2020-11-22 23:46:00', 1, '2020-11-22 23:46:00');
INSERT INTO `c_role` (`id`, `category`, `name`, `code`, `remarks`, `state`, `readonly_`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (3, '10', '普通管理员', 'GENERAL_ADMIN', '演示用的', b'1', b'1', 1, '2020-11-22 23:46:00', 1,
        '2020-11-22 23:46:00');
INSERT INTO `c_role` (`id`, `category`, `name`, `code`, `remarks`, `state`, `readonly_`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (4, '10', '普通用户', 'NORMAL', '演示用的', b'1', b'1', 1, '2020-11-22 23:46:00', 2, '2023-07-17 22:54:01');
INSERT INTO `c_role` (`id`, `category`, `name`, `code`, `remarks`, `state`, `readonly_`, `created_by`, `create_time`,
                      `updated_by`, `update_time`)
VALUES (1486215142227050496, '30', 'TEST', 'TEST', '', b'1', b'0', 2, '2022-01-26 13:51:17', 2, '2023-07-19 18:36:39');
COMMIT;

-- ----------------------------
-- Table structure for c_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `c_role_authority`;
CREATE TABLE `c_role_authority`
(
    `id`           bigint NOT NULL COMMENT '主键',
    `authority_id` bigint NOT NULL COMMENT '资源id \n#c_resource #c_menu',
    `role_id`      bigint NOT NULL COMMENT '角色id \n#c_role',
    `create_time`  datetime DEFAULT NULL COMMENT '创建时间',
    `created_by`   bigint   DEFAULT NULL COMMENT '创建人',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_role_authority` (`authority_id`, `role_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='角色的资源';

-- ----------------------------
-- Records of c_role_authority
-- ----------------------------
BEGIN;
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043094646456320, 32, 2, '2023-07-15 10:34:22', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043094646456321, 33, 2, '2023-07-15 10:34:22', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043094650650624, 34, 2, '2023-07-15 10:34:22', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043094650650625, 35, 2, '2023-07-15 10:34:22', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043094650650626, 36, 2, '2023-07-15 10:34:22', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043094650650627, 1677590913037107200, 2, '2023-07-15 10:34:22', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043094654844928, 37, 2, '2023-07-15 10:34:22', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043094654844929, 40, 2, '2023-07-15 10:34:22', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043094654844930, 10, 2, '2023-07-15 10:34:22', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043094659039232, 1677509537856225280, 2, '2023-07-15 10:34:22', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043094659039233, 20, 2, '2023-07-15 10:34:22', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043094659039234, 1677585709487095808, 2, '2023-07-15 10:34:22', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043094659039235, 1677509487545548800, 2, '2023-07-15 10:34:22', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043094663233536, 30, 2, '2023-07-15 10:34:22', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043094663233537, 31, 2, '2023-07-15 10:34:22', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043120210739200, 32, 3, '2023-07-15 10:34:28', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043120214933504, 33, 3, '2023-07-15 10:34:28', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043120214933505, 34, 3, '2023-07-15 10:34:28', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043120219127808, 35, 3, '2023-07-15 10:34:28', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043120219127809, 36, 3, '2023-07-15 10:34:28', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043120219127810, 1677590913037107200, 3, '2023-07-15 10:34:28', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043120219127811, 37, 3, '2023-07-15 10:34:28', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043120223322112, 40, 3, '2023-07-15 10:34:28', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043120223322113, 10, 3, '2023-07-15 10:34:28', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043120223322114, 1677509537856225280, 3, '2023-07-15 10:34:28', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043120223322115, 20, 3, '2023-07-15 10:34:28', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043120227516416, 1677585709487095808, 3, '2023-07-15 10:34:28', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043120227516417, 1677509487545548800, 3, '2023-07-15 10:34:28', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043120227516418, 30, 3, '2023-07-15 10:34:28', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043120227516419, 31, 3, '2023-07-15 10:34:28', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043177047752704, 20, 4, '2023-07-15 10:34:42', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043177051947008, 1677590913037107200, 4, '2023-07-15 10:34:42', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043177051947009, 1677585709487095808, 4, '2023-07-15 10:34:42', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043177051947010, 40, 4, '2023-07-15 10:34:42', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043177056141312, 10, 4, '2023-07-15 10:34:42', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043177056141313, 1677509487545548800, 4, '2023-07-15 10:34:42', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043177056141314, 1677509537856225280, 4, '2023-07-15 10:34:42', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043213047463936, 20, 1486215142227050496, '2023-07-15 10:34:50', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043213051658240, 1677590913037107200, 1486215142227050496, '2023-07-15 10:34:50', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043213055852544, 1677585709487095808, 1486215142227050496, '2023-07-15 10:34:50', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043213055852545, 10, 1486215142227050496, '2023-07-15 10:34:50', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043213055852546, 1677509487545548800, 1486215142227050496, '2023-07-15 10:34:50', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1680043213060046848, 1677509537856225280, 1486215142227050496, '2023-07-15 10:34:50', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056751865856, 1677590913037107200, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056756060160, 10, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056760254464, 1677509537856225280, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056760254465, 20, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056760254466, 1681147781336530944, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056764448768, 1677585709487095808, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056764448769, 1681650303931252736, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056768643072, 30, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056768643073, 31, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056768643074, 32, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056772837376, 33, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056772837377, 34, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056772837378, 35, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056777031680, 36, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056777031681, 37, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056777031682, 40, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056781225984, 1681648876672516096, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056781225985, 1681651894805594112, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056781225986, 1681237640319336448, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056785420288, 1681237640373862400, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056785420289, 1681237640386445312, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056785420290, 1681647656843083776, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056785420291, 1677509487545548800, 1, '2023-07-19 21:07:49', 2);
INSERT INTO `c_role_authority` (`id`, `authority_id`, `role_id`, `create_time`, `created_by`)
VALUES (1681652056789614592, 1681558362933690368, 1, '2023-07-19 21:07:49', 2);
COMMIT;

-- ----------------------------
-- Table structure for c_role_org
-- ----------------------------
DROP TABLE IF EXISTS `c_role_org`;
CREATE TABLE `c_role_org`
(
    `id`          bigint NOT NULL COMMENT 'ID',
    `role_id`     bigint NOT NULL COMMENT '角色\n#c_role',
    `org_id`      bigint NOT NULL COMMENT '部门\n#c_org',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `created_by`  bigint   DEFAULT NULL COMMENT '创建人',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_role_org` (`org_id`, `role_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='角色组织关系';

-- ----------------------------
-- Records of c_role_org
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_station
-- ----------------------------
DROP TABLE IF EXISTS `c_station`;
CREATE TABLE `c_station`
(
    `id`             bigint       NOT NULL COMMENT 'ID',
    `name`           varchar(255) NOT NULL                                         DEFAULT '' COMMENT '名称',
    `org_id`         bigint                                                        DEFAULT NULL COMMENT '组织\n#c_org\n@Echo(api = ORG_ID_CLASS,  beanClass = Org.class)',
    `state`          bit(1)                                                        DEFAULT b'1' COMMENT '状态',
    `remarks`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '描述',
    `create_time`    datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `created_by`     bigint                                                        DEFAULT NULL COMMENT '创建人',
    `update_time`    datetime                                                      DEFAULT NULL COMMENT '修改时间',
    `updated_by`     bigint                                                        DEFAULT NULL COMMENT '修改人',
    `created_org_id` bigint                                                        DEFAULT NULL COMMENT '创建者所属机构',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='岗位';

-- ----------------------------
-- Records of c_station
-- ----------------------------
BEGIN;
INSERT INTO `c_station` (`id`, `name`, `org_id`, `state`, `remarks`, `create_time`, `created_by`, `update_time`,
                         `updated_by`, `created_org_id`)
VALUES (1, '总经理', 1, b'1', '', '2020-11-23 00:06:20', 1, '2020-11-23 00:06:25', 1, 1);
INSERT INTO `c_station` (`id`, `name`, `org_id`, `state`, `remarks`, `create_time`, `created_by`, `update_time`,
                         `updated_by`, `created_org_id`)
VALUES (1415864686808662016, '1-1', 1415864576410386432, b'1', '', '2021-07-16 10:43:41', 2, '2021-07-16 10:43:41', 2,
        1);
INSERT INTO `c_station` (`id`, `name`, `org_id`, `state`, `remarks`, `create_time`, `created_by`, `update_time`,
                         `updated_by`, `created_org_id`)
VALUES (1533445633120141312, 'general创建的', 1415864608656195584, b'1', '', '2022-06-05 21:48:24', 4,
        '2022-06-05 21:48:24', 4, 1415864608656195584);
INSERT INTO `c_station` (`id`, `name`, `org_id`, `state`, `remarks`, `create_time`, `created_by`, `update_time`,
                         `updated_by`, `created_org_id`)
VALUES (1533445687008559104, 'general创建的2', 1, b'1', '', '2022-06-05 21:48:36', 4, '2022-06-05 21:48:36', 4,
        1415864608656195584);
COMMIT;

-- ----------------------------
-- Table structure for c_tenant
-- ----------------------------
DROP TABLE IF EXISTS `c_tenant`;
CREATE TABLE `c_tenant`
(
    `id`              bigint      NOT NULL COMMENT '主键ID',
    `code`            varchar(20) NOT NULL COMMENT '企业编码',
    `name`            varchar(255)                                                  DEFAULT '' COMMENT '企业名称',
    `type`            varchar(10)                                                   DEFAULT '' COMMENT '类型 \n#{CREATE:创建;REGISTER:注册}',
    `connect_type`    varchar(10)                                                   DEFAULT '' COMMENT '链接类型 \n#TenantConnectTypeEnum{LOCAL:本地;REMOTE:远程}',
    `status`          varchar(10)                                                   DEFAULT '' COMMENT '状态 \n#{NORMAL:正常;WAIT_INIT:待初始化;FORBIDDEN:禁用;WAITING:待审核;REFUSE:拒绝;DELETE:已删除}',
    `readonly_`       bit(1)                                                        DEFAULT b'0' COMMENT '内置',
    `duty`            varchar(50)                                                   DEFAULT '' COMMENT '责任人',
    `expiration_time` datetime                                                      DEFAULT NULL COMMENT '有效期 \n为空表示永久',
    `logo`            varchar(255)                                                  DEFAULT '' COMMENT 'logo地址',
    `remarks`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '企业简介',
    `create_time`     datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `created_by`      bigint                                                        DEFAULT NULL COMMENT '创建人',
    `update_time`     datetime                                                      DEFAULT NULL COMMENT '修改时间',
    `updated_by`      bigint                                                        DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_code` (`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='企业';

-- ----------------------------
-- Records of c_tenant
-- ----------------------------
BEGIN;
INSERT INTO `c_tenant` (`id`, `code`, `name`, `type`, `connect_type`, `status`, `readonly_`, `duty`, `expiration_time`,
                        `logo`, `remarks`, `create_time`, `created_by`, `update_time`, `updated_by`)
VALUES (1, '0000', '最后内置的运营&超级租户', 'CREATE', 'LOCAL', 'NORMAL', b'1', '最后', NULL, NULL,
        '内置租户,用于测试租户系统所有功能, 用于管理整个系统.请勿删除', '2019-08-29 16:50:35', 1, '2019-08-29 16:50:35',
        1);
COMMIT;

-- ----------------------------
-- Table structure for c_tenant_datasource_config
-- ----------------------------
DROP TABLE IF EXISTS `c_tenant_datasource_config`;
CREATE TABLE `c_tenant_datasource_config`
(
    `id`                   bigint       NOT NULL COMMENT 'ID',
    `tenant_id`            bigint       NOT NULL COMMENT '租户id',
    `datasource_config_id` bigint       NOT NULL COMMENT '数据源id',
    `db_prefix`            varchar(100) NOT NULL DEFAULT '' COMMENT '服务',
    `create_time`          datetime              DEFAULT NULL COMMENT '创建时间',
    `created_by`           bigint                DEFAULT NULL COMMENT '创建人',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_tenan_application` (`tenant_id`, `datasource_config_id`, `db_prefix`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='租户数据源关系';

-- ----------------------------
-- Records of c_tenant_datasource_config
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_user
-- ----------------------------
DROP TABLE IF EXISTS `c_user`;
CREATE TABLE `c_user`
(
    `id`                       bigint                                                       NOT NULL COMMENT 'ID',
    `username`                 varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '账号',
    `nick_name`                varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '姓名',
    `org_id`                   bigint                                                                DEFAULT NULL COMMENT '组织\n#c_org\n@Echo(api = ORG_ID_CLASS,  beanClass = Org.class)',
    `station_id`               bigint                                                                DEFAULT NULL COMMENT '岗位\n#c_station\n@Echo(api = STATION_ID_CLASS)',
    `readonly`                 bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '内置',
    `email`                    varchar(255)                                                          DEFAULT '' COMMENT '邮箱',
    `mobile`                   varchar(20)                                                           DEFAULT '' COMMENT '手机',
    `sex`                      varchar(1)                                                            DEFAULT 'M' COMMENT '性别 \n#Sex{W:女;M:男;N:未知}',
    `state`                    bit(1)                                                                DEFAULT b'1' COMMENT '状态',
    `avatar`                   varchar(255)                                                          DEFAULT '' COMMENT '头像',
    `nation`                   char(2)                                                               DEFAULT '' COMMENT '民族 \n@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.NATION)',
    `education`                char(2)                                                               DEFAULT '' COMMENT '学历 \n@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.EDUCATION)',
    `position_status`          char(2)                                                               DEFAULT '' COMMENT '职位状态 \n@Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.POSITION_STATUS)',
    `work_describe`            varchar(255)                                                          DEFAULT '' COMMENT '工作描述',
    `password_error_last_time` datetime                                                              DEFAULT NULL COMMENT '最后一次输错密码时间',
    `password_error_num`       int                                                                   DEFAULT '0' COMMENT '密码错误次数',
    `password_expire_time`     datetime                                                              DEFAULT NULL COMMENT '密码过期时间',
    `password`                 varchar(64)                                                  NOT NULL DEFAULT '' COMMENT '密码',
    `salt`                     varchar(20)                                                  NOT NULL DEFAULT '' COMMENT '密码盐',
    `last_login_time`          datetime                                                              DEFAULT NULL COMMENT '最后登录时间',
    `created_by`               bigint                                                                DEFAULT NULL COMMENT '创建人id',
    `create_time`              datetime                                                              DEFAULT NULL COMMENT '创建时间',
    `updated_by`               bigint                                                                DEFAULT NULL COMMENT '更新人id',
    `update_time`              datetime                                                              DEFAULT NULL COMMENT '更新时间',
    `created_org_id`           bigint                                                                DEFAULT NULL COMMENT '创建者所属机构',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_account` (`username`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户';

-- ----------------------------
-- Records of c_user
-- ----------------------------
BEGIN;
INSERT INTO `c_user` (`id`, `username`, `nick_name`, `org_id`, `station_id`, `readonly`, `email`, `mobile`, `sex`,
                      `state`, `avatar`, `nation`, `education`, `position_status`, `work_describe`,
                      `password_error_last_time`, `password_error_num`, `password_expire_time`, `password`, `salt`,
                      `last_login_time`, `created_by`, `create_time`, `updated_by`, `update_time`, `created_org_id`)
VALUES (1, 'kpuAdmin', '内置管理员', 1, 1, b'1', '15217781234@qq.com', '15217781234', '1', b'1',
        '17e420c250804efe904a09a33796d5a10.jpg', '01', '01', '01', '不想上班!', '2020-11-24 19:08:45', 0, NULL,
        'fdb3102d8daa60b14c91ee10f307382bf5dc227c7a5ebe8529ef28d6a7557edc', 'ki5pj8dv44i14yu4nbhh',
        '2020-11-24 19:08:45', 1, '2020-11-22 23:03:15', 1, '2020-11-22 23:03:15', 1);
INSERT INTO `c_user` (`id`, `username`, `nick_name`, `org_id`, `station_id`, `readonly`, `email`, `mobile`, `sex`,
                      `state`, `avatar`, `nation`, `education`, `position_status`, `work_describe`,
                      `password_error_last_time`, `password_error_num`, `password_expire_time`, `password`, `salt`,
                      `last_login_time`, `created_by`, `create_time`, `updated_by`, `update_time`, `created_org_id`)
VALUES (2, 'kpu', '超级管理员', 1533406176778125312, 1, b'0', '5', '15217781235', '1', b'1', '20180414165815.jpg', '02',
        '04', '03', '不想上班!', NULL, 0, NULL, 'fdb3102d8daa60b14c91ee10f307382bf5dc227c7a5ebe8529ef28d6a7557edc',
        'ki5pj8dv44i14yu4nbhh', '2023-07-20 17:20:38', 1, '2020-11-22 23:03:15', 2, '2022-06-05 23:02:13', 1);
INSERT INTO `c_user` (`id`, `username`, `nick_name`, `org_id`, `station_id`, `readonly`, `email`, `mobile`, `sex`,
                      `state`, `avatar`, `nation`, `education`, `position_status`, `work_describe`,
                      `password_error_last_time`, `password_error_num`, `password_expire_time`, `password`, `salt`,
                      `last_login_time`, `created_by`, `create_time`, `updated_by`, `update_time`, `created_org_id`)
VALUES (3, 'kpu_pt', '平台管理员', 1, 1, b'0', '2', '15217781237', '1', b'1', 'a3b10296862e40edb811418d64455d00.jpeg',
        '05', '06', '02', '不想上班!', '2022-06-01 12:46:59', 0, NULL,
        'fdb3102d8daa60b14c91ee10f307382bf5dc227c7a5ebe8529ef28d6a7557edc', 'ki5pj8dv44i14yu4nbhh',
        '2022-06-01 12:46:59', 1, '2020-11-22 23:03:15', 2, '2022-06-05 21:46:27', 1);
INSERT INTO `c_user` (`id`, `username`, `nick_name`, `org_id`, `station_id`, `readonly`, `email`, `mobile`, `sex`,
                      `state`, `avatar`, `nation`, `education`, `position_status`, `work_describe`,
                      `password_error_last_time`, `password_error_num`, `password_expire_time`, `password`, `salt`,
                      `last_login_time`, `created_by`, `create_time`, `updated_by`, `update_time`, `created_org_id`)
VALUES (4, 'general', '普通管理员', 1415864608656195584, 1, b'0', '', '15217781239', '1', b'1', '', '01', '01', '01',
        '不想上班!', '2022-06-05 21:47:47', 0, NULL, 'fdb3102d8daa60b14c91ee10f307382bf5dc227c7a5ebe8529ef28d6a7557edc',
        'ki5pj8dv44i14yu4nbhh', '2022-06-05 21:47:47', 1, '2020-11-22 23:03:15', 2, '2022-06-05 21:46:08', 1);
INSERT INTO `c_user` (`id`, `username`, `nick_name`, `org_id`, `station_id`, `readonly`, `email`, `mobile`, `sex`,
                      `state`, `avatar`, `nation`, `education`, `position_status`, `work_describe`,
                      `password_error_last_time`, `password_error_num`, `password_expire_time`, `password`, `salt`,
                      `last_login_time`, `created_by`, `create_time`, `updated_by`, `update_time`, `created_org_id`)
VALUES (5, 'normal', '普通用户', 1533438004188676096, 1, b'0', '', '15217781231', '1', b'1', '', '02', '02', '02',
        '不想上班!', '2022-01-27 12:21:39', 0, NULL, 'fdb3102d8daa60b14c91ee10f307382bf5dc227c7a5ebe8529ef28d6a7557edc',
        'ki5pj8dv44i14yu4nbhh', '2022-01-27 12:21:39', 1, '2020-11-22 23:03:15', 2, '2022-06-05 21:46:20', 1);
INSERT INTO `c_user` (`id`, `username`, `nick_name`, `org_id`, `station_id`, `readonly`, `email`, `mobile`, `sex`,
                      `state`, `avatar`, `nation`, `education`, `position_status`, `work_describe`,
                      `password_error_last_time`, `password_error_num`, `password_expire_time`, `password`, `salt`,
                      `last_login_time`, `created_by`, `create_time`, `updated_by`, `update_time`, `created_org_id`)
VALUES (1680960554002284544, 'test', 'test', NULL, NULL, b'0', '', '176777777777', '1', b'1', '', '01', '08', '02', '',
        NULL, 0, NULL, 'c61d5c173ad47c99989967df0257d9844f9ed7a82f6afc890182d52c86ad1bb8', 'ridpfyf0z4nns6fkdii7', NULL,
        2, '2023-07-17 23:20:01', 2, '2023-07-17 23:25:28', NULL);
COMMIT;

-- ----------------------------
-- Table structure for c_user_role
-- ----------------------------
DROP TABLE IF EXISTS `c_user_role`;
CREATE TABLE `c_user_role`
(
    `id`          bigint NOT NULL COMMENT 'ID',
    `role_id`     bigint NOT NULL COMMENT '角色\n#c_role',
    `user_id`     bigint NOT NULL COMMENT '用户\n#c_user',
    `created_by`  bigint   DEFAULT NULL COMMENT '创建人ID',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_user_role` (`role_id`, `user_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='角色分配\n账号角色绑定';

-- ----------------------------
-- Records of c_user_role
-- ----------------------------
BEGIN;
INSERT INTO `c_user_role` (`id`, `role_id`, `user_id`, `created_by`, `create_time`)
VALUES (1, 1, 1, 1, '2020-11-23 14:19:09');
INSERT INTO `c_user_role` (`id`, `role_id`, `user_id`, `created_by`, `create_time`)
VALUES (2, 1, 2, 1, '2020-11-23 14:19:11');
INSERT INTO `c_user_role` (`id`, `role_id`, `user_id`, `created_by`, `create_time`)
VALUES (3, 2, 3, 1, '2020-11-23 14:19:14');
INSERT INTO `c_user_role` (`id`, `role_id`, `user_id`, `created_by`, `create_time`)
VALUES (4, 3, 4, 1, '2020-11-23 14:19:14');
INSERT INTO `c_user_role` (`id`, `role_id`, `user_id`, `created_by`, `create_time`)
VALUES (1680145582141734912, 1486215142227050496, 4, 2, '2023-07-15 17:21:37');
INSERT INTO `c_user_role` (`id`, `role_id`, `user_id`, `created_by`, `create_time`)
VALUES (1680145582145929216, 1486215142227050496, 3, 2, '2023-07-15 17:21:37');
INSERT INTO `c_user_role` (`id`, `role_id`, `user_id`, `created_by`, `create_time`)
VALUES (1680145582145929218, 1486215142227050496, 1, 2, '2023-07-15 17:21:37');
INSERT INTO `c_user_role` (`id`, `role_id`, `user_id`, `created_by`, `create_time`)
VALUES (1680883060016414720, 1486215142227050496, 5, 2, '2023-07-17 18:12:05');
INSERT INTO `c_user_role` (`id`, `role_id`, `user_id`, `created_by`, `create_time`)
VALUES (1680883060062552064, 4, 5, 2, '2023-07-17 18:12:05');
INSERT INTO `c_user_role` (`id`, `role_id`, `user_id`, `created_by`, `create_time`)
VALUES (1680883060062552065, 3, 5, 2, '2023-07-17 18:12:05');
INSERT INTO `c_user_role` (`id`, `role_id`, `user_id`, `created_by`, `create_time`)
VALUES (1680883060066746368, 2, 5, 2, '2023-07-17 18:12:05');
INSERT INTO `c_user_role` (`id`, `role_id`, `user_id`, `created_by`, `create_time`)
VALUES (1680883060066746369, 1, 5, 2, '2023-07-17 18:12:05');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
