/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : liao_module

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 08/12/2021 17:03:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint NOT NULL COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表描述',
  `class_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `package_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `parent_menu_id` bigint NULL DEFAULT NULL COMMENT '上级菜单',
  `deleted` int NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int NULL DEFAULT 1 COMMENT '乐观锁',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (1452454622621081601, 'sys_admin', '管理员', 'SysAdmin', 'com.liao.system', 'system', 'admin', '管理员', 'LiAo', NULL, 0, 1, '2021-10-25 09:59:02', '2021-10-25 09:59:06');

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` bigint NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1452454623208284162 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (1452454622805630978, 1452454622621081601, 'admin_id', '管理员主键', 'bigint', 'Long', 'adminId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, '2021-10-25 09:59:02', '2021-10-25 09:59:06');
INSERT INTO `gen_table_column` VALUES (1452454622851768322, 1452454622621081601, 'admin_account', '账户', 'varchar(50)', 'String', 'adminAccount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, '2021-10-25 09:59:02', '2021-10-25 09:59:06');
INSERT INTO `gen_table_column` VALUES (1452454622881128450, 1452454622621081601, 'admin_password', '密码', 'varchar(50)', 'String', 'adminPassword', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, '2021-10-25 09:59:02', '2021-10-25 09:59:06');
INSERT INTO `gen_table_column` VALUES (1452454622923071489, 1452454622621081601, 'admin_name', '姓名', 'varchar(50)', 'String', 'adminName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, '2021-10-25 09:59:02', '2021-10-25 09:59:06');
INSERT INTO `gen_table_column` VALUES (1452454622948237314, 1452454622621081601, 'admin_sex', '性别', 'char(1)', 'String', 'adminSex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 5, '2021-10-25 09:59:02', '2021-10-25 09:59:06');
INSERT INTO `gen_table_column` VALUES (1452454622990180354, 1452454622621081601, 'admin_avatar', '头像连接', 'varchar(500)', 'String', 'adminAvatar', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 6, '2021-10-25 09:59:02', '2021-10-25 09:59:06');
INSERT INTO `gen_table_column` VALUES (1452454623023734785, 1452454622621081601, 'admin_email', '邮箱', 'varchar(50)', 'String', 'adminEmail', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, '2021-10-25 09:59:02', '2021-10-25 09:59:06');
INSERT INTO `gen_table_column` VALUES (1452454623057289218, 1452454622621081601, 'admin_nickname', '昵称', 'varchar(50)', 'String', 'adminNickname', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 8, '2021-10-25 09:59:02', '2021-10-25 09:59:06');
INSERT INTO `gen_table_column` VALUES (1452454623095037953, 1452454622621081601, 'admin_remarks', '备注', 'varchar(255)', 'String', 'adminRemarks', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, '2021-10-25 09:59:02', '2021-10-25 09:59:06');
INSERT INTO `gen_table_column` VALUES (1452454623116009474, 1452454622621081601, 'version', '乐观锁', 'int', 'Long', 'version', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 10, '2021-10-25 09:59:02', '2021-10-25 09:59:06');
INSERT INTO `gen_table_column` VALUES (1452454623153758210, 1452454622621081601, 'deleted', '逻辑删除', 'int', 'Long', 'deleted', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 11, '2021-10-25 09:59:02', '2021-10-25 09:59:06');
INSERT INTO `gen_table_column` VALUES (1452454623187312642, 1452454622621081601, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 12, '2021-10-25 09:59:02', '2021-10-25 09:59:06');
INSERT INTO `gen_table_column` VALUES (1452454623208284162, 1452454622621081601, 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 13, '2021-10-25 09:59:02', '2021-10-25 09:59:06');

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin`  (
  `admin_id` bigint NOT NULL AUTO_INCREMENT COMMENT '管理员主键',
  `admin_account` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账户',
  `admin_password` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `admin_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `admin_sex` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '性别',
  `admin_avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像连接',
  `admin_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `admin_nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称',
  `admin_remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `version` int NULL DEFAULT 1 COMMENT '乐观锁',
  `deleted` int NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1468110728340996098 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '管理员' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES (1339032578400731137, 'admin', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', 'Wick', '女', '', 'liao991224@gmail.com', 'Admin', NULL, 2, 0, '2020-12-16 10:20:19', '2021-10-22 09:08:46');
INSERT INTO `sys_admin` VALUES (1442004961175355394, 'test', 'test', 'User', '男', 'test', 'update@update.com', 'update', NULL, 1, 1, '2021-09-26 13:55:49', '2021-10-22 09:09:09');
INSERT INTO `sys_admin` VALUES (1468106344961994753, 'wick', '$2a$10$UlC9PkYNPr2qcSWWpUqN8ua6VpRsphAAcH6CYV/xgDoyLortJOfkK', 'Wick', NULL, NULL, 'wick@wick.com', 'wick', NULL, 1, 1, '2021-12-07 14:33:23', '2021-12-07 14:33:23');
INSERT INTO `sys_admin` VALUES (1468110728340996097, 'ack', '$2a$10$x/ajno3iLsaMhZj9I4WMYu3XKWJSYJ7w/WK6.eCTDtb7TFUnhHDTa', 'ack', '男', NULL, 'ack@ack.com', 'ack', NULL, 1, 1, '2021-12-07 14:50:48', '2021-12-07 14:51:12');

-- ----------------------------
-- Table structure for sys_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_role`;
CREATE TABLE `sys_admin_role`  (
  `admin_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '管理员角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_admin_role
-- ----------------------------
INSERT INTO `sys_admin_role` VALUES (1339032578400731137, 1, 1);
INSERT INTO `sys_admin_role` VALUES (1339032578400731137, 1, 2);
INSERT INTO `sys_admin_role` VALUES (1339032578400731137, 1, 3);
INSERT INTO `sys_admin_role` VALUES (1339032578400731137, 1, 4);
INSERT INTO `sys_admin_role` VALUES (1339032578400731137, 1, 5);
INSERT INTO `sys_admin_role` VALUES (2, 1, 6);
INSERT INTO `sys_admin_role` VALUES (1343475636072640513, 1, 7);
INSERT INTO `sys_admin_role` VALUES (1343477268881633281, 1, 8);
INSERT INTO `sys_admin_role` VALUES (1343485125261815809, 1, 9);
INSERT INTO `sys_admin_role` VALUES (1393751506804391937, 1, 10);
INSERT INTO `sys_admin_role` VALUES (1437984062990213122, 1343807643550027778, 11);
INSERT INTO `sys_admin_role` VALUES (1437984062990213122, 1343807643550027778, 12);
INSERT INTO `sys_admin_role` VALUES (1442004961175355394, 1343796202273001474, 13);
INSERT INTO `sys_admin_role` VALUES (1339032578400731137, 1, 14);
INSERT INTO `sys_admin_role` VALUES (1339032578400731137, 1, 15);
INSERT INTO `sys_admin_role` VALUES (1442004961175355394, 1343796202273001474, 16);
INSERT INTO `sys_admin_role` VALUES (1468106344961994753, 1343796202273001474, 17);
INSERT INTO `sys_admin_role` VALUES (1468110728340996097, 1343796202273001474, 18);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int NULL DEFAULT NULL COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字典类型',
  `dict_is_default` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否默认',
  `version` int NULL DEFAULT 1 COMMENT '乐观锁',
  `deleted` int NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1447843614225125378 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1413324339980578818, 1, '男', '0', 'sex_type', NULL, 1, 0, '2021-07-09 10:29:16', '2021-07-09 10:29:16');
INSERT INTO `sys_dict_data` VALUES (1413324387883724801, 2, '女', '1', 'sex_type', NULL, 1, 0, '2021-07-09 10:29:27', '2021-07-09 10:29:27');
INSERT INTO `sys_dict_data` VALUES (1414504537467916290, 1, '启用', '1', 'sys_stuent', NULL, 1, 0, '2021-07-12 16:38:57', '2021-07-12 16:43:24');
INSERT INTO `sys_dict_data` VALUES (1414504576294588417, 1, '停用', '0', 'sys_stuent', NULL, 1, 0, '2021-07-12 16:39:06', '2021-07-12 16:43:21');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典类型id',
  `dict_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字典类型名称',
  `dict_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字典类型',
  `version` int NULL DEFAULT 1 COMMENT '乐观锁',
  `deleted` int NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1447843491399127042 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1413324240789483521, '性别', 'sex_type', 1, 0, '2021-07-09 10:28:52', '2021-07-09 10:28:52');
INSERT INTO `sys_dict_type` VALUES (1414503278904086530, '状态', 'sys_stuent', 1, 0, '2021-07-12 16:33:57', '2021-07-12 16:33:57');

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `create_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1468481463802658819 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统访问记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (1465278265159860226, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-11-29 19:15:37');
INSERT INTO `sys_logininfor` VALUES (1465284132676620290, 'liao', '192.168.2.72', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-11-29 19:38:56');
INSERT INTO `sys_logininfor` VALUES (1465975716971835394, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-01 17:27:02');
INSERT INTO `sys_logininfor` VALUES (1466235182258651137, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-02 10:38:03');
INSERT INTO `sys_logininfor` VALUES (1466645358602153986, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', 'There is no PasswordEncoder mapped for the id \"null\"', '2021-12-03 13:47:57');
INSERT INTO `sys_logininfor` VALUES (1466645788803526658, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', 'There is no PasswordEncoder mapped for the id \"null\"', '2021-12-03 13:49:40');
INSERT INTO `sys_logininfor` VALUES (1466646165489774594, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', 'There is no PasswordEncoder mapped for the id \"null\"', '2021-12-03 13:51:09');
INSERT INTO `sys_logininfor` VALUES (1466649578382340098, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', 'There is no PasswordEncoder mapped for the id \"null\"', '2021-12-03 14:04:43');
INSERT INTO `sys_logininfor` VALUES (1466649768220733442, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', 'There is no PasswordEncoder mapped for the id \"null\"', '2021-12-03 14:05:28');
INSERT INTO `sys_logininfor` VALUES (1466650062396588033, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', 'There is no PasswordEncoder mapped for the id \"null\"', '2021-12-03 14:06:39');
INSERT INTO `sys_logininfor` VALUES (1466650281850974209, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', 'user.login.success', '2021-12-03 14:07:31');
INSERT INTO `sys_logininfor` VALUES (1466652570762358785, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', 'user.login.success', '2021-12-03 14:16:37');
INSERT INTO `sys_logininfor` VALUES (1466652907258785794, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', 'user.login.success', '2021-12-03 14:17:57');
INSERT INTO `sys_logininfor` VALUES (1466655068197785602, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', 'user.login.success', '2021-12-03 14:26:32');
INSERT INTO `sys_logininfor` VALUES (1466655562072858625, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', 'user.login.success', '2021-12-03 14:28:30');
INSERT INTO `sys_logininfor` VALUES (1466656364539682818, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', 'user.login.success', '2021-12-03 14:31:41');
INSERT INTO `sys_logininfor` VALUES (1466657476676165633, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', 'user.login.success', '2021-12-03 14:36:06');
INSERT INTO `sys_logininfor` VALUES (1466658129842544642, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', 'user.login.success', '2021-12-03 14:38:42');
INSERT INTO `sys_logininfor` VALUES (1466663548363264001, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码错误', '2021-12-03 15:00:14');
INSERT INTO `sys_logininfor` VALUES (1466664018590879745, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', 'user.login.success', '2021-12-03 15:02:06');
INSERT INTO `sys_logininfor` VALUES (1466671639251193857, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', 'user.login.success', '2021-12-03 15:32:23');
INSERT INTO `sys_logininfor` VALUES (1466671693273829378, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码失效', '2021-12-03 15:32:36');
INSERT INTO `sys_logininfor` VALUES (1466677951993286658, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', 'user.login.success', '2021-12-03 15:57:28');
INSERT INTO `sys_logininfor` VALUES (1466677976576102402, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码失效', '2021-12-03 15:57:34');
INSERT INTO `sys_logininfor` VALUES (1466678025884340226, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码失效', '2021-12-03 15:57:46');
INSERT INTO `sys_logininfor` VALUES (1466678133208190977, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码错误', '2021-12-03 15:58:11');
INSERT INTO `sys_logininfor` VALUES (1466678337177194497, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', 'user.login.success', '2021-12-03 15:59:00');
INSERT INTO `sys_logininfor` VALUES (1466679297249169410, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码失效', '2021-12-03 16:02:49');
INSERT INTO `sys_logininfor` VALUES (1466679431567560705, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', 'user.login.success', '2021-12-03 16:03:21');
INSERT INTO `sys_logininfor` VALUES (1466679758513618945, 'liao', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', 'user.login.success', '2021-12-03 16:04:39');
INSERT INTO `sys_logininfor` VALUES (1466682033478942721, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', 'user.login.success', '2021-12-03 16:13:41');
INSERT INTO `sys_logininfor` VALUES (1466682328862728194, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', 'user.login.success', '2021-12-03 16:14:52');
INSERT INTO `sys_logininfor` VALUES (1466688193292443650, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码错误', '2021-12-03 16:38:10');
INSERT INTO `sys_logininfor` VALUES (1466688285416136706, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', 'user.login.success', '2021-12-03 16:38:32');
INSERT INTO `sys_logininfor` VALUES (1466688419537395714, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', 'user.login.success', '2021-12-03 16:39:04');
INSERT INTO `sys_logininfor` VALUES (1466692387311230978, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-03 16:54:50');
INSERT INTO `sys_logininfor` VALUES (1466692550268329986, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-03 16:55:28');
INSERT INTO `sys_logininfor` VALUES (1467664182327767041, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码错误', '2021-12-06 09:16:24');
INSERT INTO `sys_logininfor` VALUES (1467664668854439938, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 09:18:20');
INSERT INTO `sys_logininfor` VALUES (1467664928402165762, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 09:19:21');
INSERT INTO `sys_logininfor` VALUES (1467668533188349953, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码已失效', '2021-12-06 09:33:41');
INSERT INTO `sys_logininfor` VALUES (1467668582190403585, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 09:33:53');
INSERT INTO `sys_logininfor` VALUES (1467738816297357314, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 14:12:58');
INSERT INTO `sys_logininfor` VALUES (1467740080850653186, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 14:17:59');
INSERT INTO `sys_logininfor` VALUES (1467740263277711361, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 14:18:43');
INSERT INTO `sys_logininfor` VALUES (1467746768798404609, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 14:44:34');
INSERT INTO `sys_logininfor` VALUES (1467774435425779713, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 16:34:30');
INSERT INTO `sys_logininfor` VALUES (1467775674557399041, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 16:39:25');
INSERT INTO `sys_logininfor` VALUES (1467775790752202753, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 16:39:53');
INSERT INTO `sys_logininfor` VALUES (1467776085527887874, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 16:41:03');
INSERT INTO `sys_logininfor` VALUES (1467776410871660546, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 16:42:21');
INSERT INTO `sys_logininfor` VALUES (1467776569638649858, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 16:42:59');
INSERT INTO `sys_logininfor` VALUES (1467777457769938945, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 16:46:31');
INSERT INTO `sys_logininfor` VALUES (1467778879467028481, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 16:52:10');
INSERT INTO `sys_logininfor` VALUES (1467780129864548353, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 16:57:08');
INSERT INTO `sys_logininfor` VALUES (1467780543833964545, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 16:58:46');
INSERT INTO `sys_logininfor` VALUES (1467780634514817026, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 16:59:08');
INSERT INTO `sys_logininfor` VALUES (1467781740414369793, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:03:32');
INSERT INTO `sys_logininfor` VALUES (1467782970427572226, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:08:25');
INSERT INTO `sys_logininfor` VALUES (1467783285033926657, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:09:40');
INSERT INTO `sys_logininfor` VALUES (1467784197496373249, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:13:17');
INSERT INTO `sys_logininfor` VALUES (1467784326714490881, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:13:48');
INSERT INTO `sys_logininfor` VALUES (1467785120608157698, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:16:58');
INSERT INTO `sys_logininfor` VALUES (1467785291496685569, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:17:38');
INSERT INTO `sys_logininfor` VALUES (1467785552776658945, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:18:41');
INSERT INTO `sys_logininfor` VALUES (1467786103597826049, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:20:52');
INSERT INTO `sys_logininfor` VALUES (1467786235441577985, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:21:23');
INSERT INTO `sys_logininfor` VALUES (1467786532863868930, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:22:34');
INSERT INTO `sys_logininfor` VALUES (1467786648538578946, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:23:02');
INSERT INTO `sys_logininfor` VALUES (1467787064240242690, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:24:41');
INSERT INTO `sys_logininfor` VALUES (1467787381912633345, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:25:57');
INSERT INTO `sys_logininfor` VALUES (1467787519200591874, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:26:29');
INSERT INTO `sys_logininfor` VALUES (1467787632715235329, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:26:56');
INSERT INTO `sys_logininfor` VALUES (1467787747962126337, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:27:24');
INSERT INTO `sys_logininfor` VALUES (1467787834998128642, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:27:45');
INSERT INTO `sys_logininfor` VALUES (1467787923602800642, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:28:06');
INSERT INTO `sys_logininfor` VALUES (1467787996654993410, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:28:23');
INSERT INTO `sys_logininfor` VALUES (1467788236825034753, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:29:21');
INSERT INTO `sys_logininfor` VALUES (1467788301606060033, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:29:36');
INSERT INTO `sys_logininfor` VALUES (1467788381801152513, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:29:55');
INSERT INTO `sys_logininfor` VALUES (1467788510629199874, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:30:26');
INSERT INTO `sys_logininfor` VALUES (1467788602081804289, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:30:48');
INSERT INTO `sys_logininfor` VALUES (1467788682486611970, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码错误', '2021-12-06 17:31:07');
INSERT INTO `sys_logininfor` VALUES (1467788723259441154, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:31:16');
INSERT INTO `sys_logininfor` VALUES (1467788887571300354, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:31:56');
INSERT INTO `sys_logininfor` VALUES (1467788968626225154, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:32:15');
INSERT INTO `sys_logininfor` VALUES (1467789744358600706, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:35:20');
INSERT INTO `sys_logininfor` VALUES (1467789971081703426, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:36:14');
INSERT INTO `sys_logininfor` VALUES (1467795491561676802, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:58:10');
INSERT INTO `sys_logininfor` VALUES (1467795744285270018, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 17:59:10');
INSERT INTO `sys_logininfor` VALUES (1467801569875730434, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 18:22:19');
INSERT INTO `sys_logininfor` VALUES (1467801887199993858, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 18:23:35');
INSERT INTO `sys_logininfor` VALUES (1467802039658749954, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 18:24:11');
INSERT INTO `sys_logininfor` VALUES (1467802073280290818, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 18:24:19');
INSERT INTO `sys_logininfor` VALUES (1467802258647556097, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 18:25:04');
INSERT INTO `sys_logininfor` VALUES (1467802752333914113, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 18:27:01');
INSERT INTO `sys_logininfor` VALUES (1467835930876583938, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 20:38:52');
INSERT INTO `sys_logininfor` VALUES (1467836753371213825, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 20:42:08');
INSERT INTO `sys_logininfor` VALUES (1467838645522464769, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-06 20:49:39');
INSERT INTO `sys_logininfor` VALUES (1468044521193603073, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 10:27:43');
INSERT INTO `sys_logininfor` VALUES (1468044897275871233, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 10:29:13');
INSERT INTO `sys_logininfor` VALUES (1468044962052702209, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 10:29:29');
INSERT INTO `sys_logininfor` VALUES (1468045122900066305, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 10:30:07');
INSERT INTO `sys_logininfor` VALUES (1468046069713899521, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 10:33:53');
INSERT INTO `sys_logininfor` VALUES (1468046407191793665, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 10:35:13');
INSERT INTO `sys_logininfor` VALUES (1468046527039836162, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 10:35:42');
INSERT INTO `sys_logininfor` VALUES (1468047574315601922, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 10:39:51');
INSERT INTO `sys_logininfor` VALUES (1468047985114124289, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 10:41:29');
INSERT INTO `sys_logininfor` VALUES (1468048351436247041, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 10:42:57');
INSERT INTO `sys_logininfor` VALUES (1468048605724315650, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 10:43:57');
INSERT INTO `sys_logininfor` VALUES (1468048761676926978, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 10:44:34');
INSERT INTO `sys_logininfor` VALUES (1468049242071535618, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 10:46:29');
INSERT INTO `sys_logininfor` VALUES (1468049377555943425, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 10:47:01');
INSERT INTO `sys_logininfor` VALUES (1468049581172625409, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 10:47:50');
INSERT INTO `sys_logininfor` VALUES (1468049888560582658, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 10:49:03');
INSERT INTO `sys_logininfor` VALUES (1468056583374385154, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 11:15:39');
INSERT INTO `sys_logininfor` VALUES (1468057111143657474, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码错误', '2021-12-07 11:17:45');
INSERT INTO `sys_logininfor` VALUES (1468058592550858754, 'admin1', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码已失效', '2021-12-07 11:23:38');
INSERT INTO `sys_logininfor` VALUES (1468058678647336961, 'admin1', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', 'Index: 0, Size: 0', '2021-12-07 11:23:59');
INSERT INTO `sys_logininfor` VALUES (1468059138812817409, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '用户不存在/密码错误', '2021-12-07 11:25:49');
INSERT INTO `sys_logininfor` VALUES (1468060451713892353, 'admin123', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', 'Index: 0, Size: 0', '2021-12-07 11:31:02');
INSERT INTO `sys_logininfor` VALUES (1468099071824908290, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码错误', '2021-12-07 14:04:29');
INSERT INTO `sys_logininfor` VALUES (1468099099272433665, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '验证码错误', '2021-12-07 14:04:36');
INSERT INTO `sys_logininfor` VALUES (1468099130876514306, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '用户不存在/密码错误', '2021-12-07 14:04:43');
INSERT INTO `sys_logininfor` VALUES (1468099513208311809, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '1', '用户不存在/密码错误', '2021-12-07 14:06:15');
INSERT INTO `sys_logininfor` VALUES (1468105846179557378, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 14:31:24');
INSERT INTO `sys_logininfor` VALUES (1468106395062956033, 'wick', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 14:33:35');
INSERT INTO `sys_logininfor` VALUES (1468106444824178689, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 14:33:47');
INSERT INTO `sys_logininfor` VALUES (1468106572700119041, 'wick', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 14:34:18');
INSERT INTO `sys_logininfor` VALUES (1468110878958452737, 'ack', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 14:51:24');
INSERT INTO `sys_logininfor` VALUES (1468110973506453505, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-07 14:51:47');
INSERT INTO `sys_logininfor` VALUES (1468394002271518722, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-08 09:36:26');
INSERT INTO `sys_logininfor` VALUES (1468394039152033794, 'Wick', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '退出成功', '2021-12-08 09:36:35');
INSERT INTO `sys_logininfor` VALUES (1468481463802658818, 'admin', '192.168.2.72', '内网IP', 'Unknown', 'Unknown', '1', '账号密码错误', '2021-12-08 15:23:59');
INSERT INTO `sys_logininfor` VALUES (1468481815159504897, 'admin', '192.168.2.72', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2021-12-08 15:25:22');
INSERT INTO `sys_logininfor` VALUES (1468498731861344257, 'admin', '192.168.2.72', '内网IP', 'Unknown', 'Unknown', '1', '用户密码错误', '2021-12-08 16:32:36');
INSERT INTO `sys_logininfor` VALUES (1468499354820345858, 'admin', '192.168.2.72', '内网IP', 'Unknown', 'Unknown', '1', '用户密码错误', '2021-12-08 16:35:04');
INSERT INTO `sys_logininfor` VALUES (1468500060180643841, 'admin', '192.168.2.72', '内网IP', 'Unknown', 'Unknown', '1', '用户密码错误', '2021-12-08 16:37:52');
INSERT INTO `sys_logininfor` VALUES (1468500667134124034, 'admin', '192.168.2.72', '内网IP', 'Unknown', 'Unknown', '0', '登录成功', '2021-12-08 16:40:17');
INSERT INTO `sys_logininfor` VALUES (1468503257502765058, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-12-08 16:50:35');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `version` int NULL DEFAULT 1 COMMENT '乐观锁',
  `deleted` int NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', '', 1, 0, 'M', '0', '0', '', 'system', 1, 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (4, '用户管理', 1, 1, 'admin', 'system/admin/index', 1, 0, 'C', '0', '0', '', 'user', 1, 0, NULL, '2020-12-30 16:13:55');
INSERT INTO `sys_menu` VALUES (5, '菜单管理', 1, 3, 'menu', 'system/menu/index', 1, 0, 'C', '0', '0', NULL, 'guide', 1, 0, NULL, '2020-12-29 14:18:56');
INSERT INTO `sys_menu` VALUES (9, '角色管理', 1, 2, 'role', 'system/role/index', 1, 0, 'M', '0', '0', NULL, 'people', 1, 0, '2020-12-28 20:15:47', '2020-12-29 14:19:03');
INSERT INTO `sys_menu` VALUES (11, '日志管理', 1, 6, 'log', 'system/log/index', 1, 0, 'M', '0', '0', NULL, 'peoples', 1, 0, '2020-12-29 16:24:23', '2021-07-12 16:56:30');
INSERT INTO `sys_menu` VALUES (12, '操作日志', 11, 1, 'operlog', 'monitor/operlog/index', 1, 0, 'C', '0', '0', NULL, 'edit', 1, 0, '2020-12-29 16:33:34', '2020-12-29 16:37:10');
INSERT INTO `sys_menu` VALUES (13, '登录日志', 11, 2, 'logininfor', 'monitor/logininfor/index', 1, 0, 'M', '0', '0', NULL, 'validCode', 1, 0, '2020-12-30 10:53:12', '2020-12-30 10:53:21');
INSERT INTO `sys_menu` VALUES (38, '代码生成', 42, 5, 'gen', 'tool/gen/index', 1, 0, 'M', '0', '0', NULL, 'code', 1, 0, '2021-07-07 17:38:01', '2021-10-22 09:31:02');
INSERT INTO `sys_menu` VALUES (39, '字典管理', 1, 4, 'dict', 'system/dict/index', 1, 0, 'C', '0', '0', NULL, 'dict', 1, 0, '2021-07-09 09:41:11', '2021-07-12 16:56:14');
INSERT INTO `sys_menu` VALUES (41, '视频', 1, 7, 'Video', 'system/video/index', 1, 0, 'M', '0', '0', NULL, 'date', 1, 1, '2021-10-12 16:53:54', '2021-10-12 16:54:25');
INSERT INTO `sys_menu` VALUES (42, '系统工具', 0, 2, 'tool', NULL, 1, 0, 'M', '0', '0', NULL, 'tool', 1, 0, '2021-10-22 09:30:51', '2021-10-22 09:30:51');
INSERT INTO `sys_menu` VALUES (43, '系统监控', 0, 3, 'monitor', NULL, 1, 0, 'M', '0', '0', NULL, 'druid', 1, 0, '2021-11-29 19:19:44', '2021-11-29 19:19:49');
INSERT INTO `sys_menu` VALUES (44, '服务监控', 43, 1, 'server', 'monitor/server/index', 1, 0, 'M', '0', '0', NULL, 'monitor', 1, 0, '2021-11-29 19:20:59', '2021-11-29 19:20:59');
INSERT INTO `sys_menu` VALUES (45, '服务监控', 43, 1, 'server', 'monitor/server/index', 1, 0, 'M', '0', '0', NULL, 'monitor', 1, 1, '2021-11-29 19:20:59', '2021-11-29 19:20:59');

-- ----------------------------
-- Table structure for sys_open_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_open_log`;
CREATE TABLE `sys_open_log`  (
  `oper_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `oper_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '模块标题',
  `open_business_type` int NULL DEFAULT NULL COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `open_method` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '方法名称',
  `open_request_method` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '请求方式',
  `oper_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '主机地址IP',
  `open_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '请求url',
  `oper_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '地址',
  `oper_param` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '请求参数',
  `open_result` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '返回参数',
  `open_status` int NULL DEFAULT NULL COMMENT '状态',
  `open_error_msg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '错误消息',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1468110929512398851 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '操作日志记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_open_log
-- ----------------------------
INSERT INTO `sys_open_log` VALUES (1465279304537751553, '菜单表', 1, 'com.liao.web.controller.system.SysMenuController.add()', 'POST', '192.168.2.229', '/system/sys-menu/add', '内网IP', '{\"menuName\":[\"系统监控\"],\"icon\":[\"druid\"],\"menuType\":[\"C\"],\"orderNum\":[\"3\"],\"isFrame\":[\"1\"],\"isCache\":[\"0\"],\"visible\":[\"0\"],\"status\":[\"0\"],\"path\":[\"monitor\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-11-29 19:19:44');
INSERT INTO `sys_open_log` VALUES (1465279325115006978, '菜单表', 2, 'com.liao.web.controller.system.SysMenuController.upd()', 'POST', '192.168.2.229', '/system/sys-menu/upd_id', '内网IP', '{\"menuId\":[\"43\"],\"menuName\":[\"系统监控\"],\"orderNum\":[\"3\"],\"path\":[\"monitor\"],\"isFrame\":[\"1\"],\"isCache\":[\"0\"],\"menuType\":[\"M\"],\"visible\":[\"0\"],\"status\":[\"0\"],\"icon\":[\"druid\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-11-29 19:19:49');
INSERT INTO `sys_open_log` VALUES (1465279615960629249, '菜单表', 1, 'com.liao.web.controller.system.SysMenuController.add()', 'POST', '192.168.2.229', '/system/sys-menu/add', '内网IP', '{\"parentId\":[\"43\"],\"menuName\":[\"服务监控\"],\"icon\":[\"monitor\"],\"menuType\":[\"M\"],\"orderNum\":[\"1\"],\"isFrame\":[\"1\"],\"isCache\":[\"0\"],\"visible\":[\"0\"],\"status\":[\"0\"],\"component\":[\"monitor/server/index\"],\"path\":[\"server\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-11-29 19:20:59');
INSERT INTO `sys_open_log` VALUES (1465279617462190082, '菜单表', 1, 'com.liao.web.controller.system.SysMenuController.add()', 'POST', '192.168.2.229', '/system/sys-menu/add', '内网IP', '{\"parentId\":[\"43\"],\"menuName\":[\"服务监控\"],\"icon\":[\"monitor\"],\"menuType\":[\"M\"],\"orderNum\":[\"1\"],\"isFrame\":[\"1\"],\"isCache\":[\"0\"],\"visible\":[\"0\"],\"status\":[\"0\"],\"component\":[\"monitor/server/index\"],\"path\":[\"server\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-11-29 19:20:59');
INSERT INTO `sys_open_log` VALUES (1465279690145284097, '角色表', 2, 'com.liao.web.controller.system.SysRoleController.upd()', 'POST', '192.168.2.229', '/system/sys-role/upd_id', '内网IP', '{\"roleId\":[\"1\"],\"roleName\":[\"超级管理员\"],\"roleAuthority\":[\"admin\"],\"roleSort\":[\"1\"],\"roleStatus\":[\"0\"],\"menuIds\":[\"1,4,5,9,11,12,13,39,42,38,43,44\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-11-29 19:21:16');
INSERT INTO `sys_open_log` VALUES (1465279731903774722, '菜单表', 3, 'com.liao.web.controller.system.SysMenuController.delete()', 'POST', '192.168.2.229', '/system/sys-menu/del_id/', '内网IP', '{\"id\":[\"45\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-11-29 19:21:26');
INSERT INTO `sys_open_log` VALUES (1465279770688503809, '角色表', 2, 'com.liao.web.controller.system.SysRoleController.upd()', 'POST', '192.168.2.229', '/system/sys-role/upd_id', '内网IP', '{\"roleId\":[\"1\"],\"roleName\":[\"超级管理员\"],\"roleAuthority\":[\"admin\"],\"roleSort\":[\"1\"],\"roleStatus\":[\"0\"],\"menuIds\":[\"1,4,5,9,11,12,13,39,42,38,43,44\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-11-29 19:21:36');
INSERT INTO `sys_open_log` VALUES (1468106345293344770, '管理员', 1, 'com.liao.web.controller.system.SysAdminController.add()', 'POST', '192.168.2.229', '/system/sys-admin/add', '内网IP', '{\"roleId\":[\"1343796202273001474\"],\"adminAccount\":[\"wick\"],\"adminEmail\":[\"wick@wick.com\"],\"adminName\":[\"Wick\"],\"adminNickname\":[\"wick\"],\"adminPassword\":[\"wick\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-12-07 14:33:23');
INSERT INTO `sys_open_log` VALUES (1468106504307798017, '角色表', 2, 'com.liao.web.controller.system.SysRoleController.upd()', 'POST', '192.168.2.229', '/system/sys-role/upd_id', '内网IP', '{\"roleId\":[\"1343796202273001474\"],\"roleName\":[\"普通管理员\"],\"roleAuthority\":[\"mange\"],\"roleSort\":[\"2\"],\"roleStatus\":[\"0\"],\"menuCheckStrictly\":[\"true\"],\"menuIds\":[\"1,4,5,9,11,12,13,39,42,38,43,44\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-12-07 14:34:01');
INSERT INTO `sys_open_log` VALUES (1468109698320596993, '管理员', 3, 'com.liao.web.controller.system.SysAdminController.deletes()', 'POST', '192.168.2.229', '/system/sys-admin/del_ids', '内网IP', '{\"ids\":[\"1468106344961994753\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-12-07 14:46:43');
INSERT INTO `sys_open_log` VALUES (1468110728429076481, '用户管理', 6, 'com.liao.web.controller.system.SysAdminController.importData()', 'POST', '192.168.2.229', '/system/sys-admin/importData', '内网IP', '{\"isUpdate\":[\"0\"]}', '{\"msg\":\"恭喜您，数据已全部导入成功！共 1 条，数据如下：<br/>1、账号 ack 导入成功\",\"code\":200}', 0, NULL, '2021-12-07 14:50:48');
INSERT INTO `sys_open_log` VALUES (1468110826592567297, '管理员', 2, 'com.liao.web.controller.system.SysAdminController.upd()', 'POST', '192.168.2.229', '/system/sys-admin/upd_id', '内网IP', '{\"adminId\":[\"1468110728340996097\"],\"adminAccount\":[\"ack\"],\"adminName\":[\"ack\"],\"adminSex\":[\"男\"],\"adminEmail\":[\"ack@ack.com\"],\"adminNickname\":[\"ack\"],\"adminPassword\":[\"ack\"],\"roleId\":[\"1343796202273001474\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-12-07 14:51:12');
INSERT INTO `sys_open_log` VALUES (1468110929512398850, '管理员', 3, 'com.liao.web.controller.system.SysAdminController.deletes()', 'POST', '192.168.2.229', '/system/sys-admin/del_ids', '内网IP', '{\"ids\":[\"1468110728340996097,1442004961175355394\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-12-07 14:51:36');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色名称',
  `role_authority` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限字符',
  `role_sort` int NULL DEFAULT NULL COMMENT '顺序',
  `role_status` int NULL DEFAULT 0 COMMENT '角色状态',
  `version` int NULL DEFAULT 1 COMMENT '乐观锁',
  `deleted` int NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1437984199305093122 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, 0, 1, 0, '2020-12-21 11:15:07', '2021-11-29 19:21:36');
INSERT INTO `sys_role` VALUES (1343796202273001474, '普通管理员', 'mange', 2, 0, 1, 0, '2020-12-29 13:49:16', '2021-12-07 14:34:01');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint NULL DEFAULT NULL COMMENT '菜单id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1343807643550027778, 1);
INSERT INTO `sys_role_menu` VALUES (1343807643550027778, 4);
INSERT INTO `sys_role_menu` VALUES (1343807643550027778, 5);
INSERT INTO `sys_role_menu` VALUES (1343807643550027778, 9);
INSERT INTO `sys_role_menu` VALUES (1343807643550027778, 2);
INSERT INTO `sys_role_menu` VALUES (1437984199305093121, 1);
INSERT INTO `sys_role_menu` VALUES (1437984199305093121, 4);
INSERT INTO `sys_role_menu` VALUES (1437984199305093121, 5);
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 9);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (1, 13);
INSERT INTO `sys_role_menu` VALUES (1, 39);
INSERT INTO `sys_role_menu` VALUES (1, 42);
INSERT INTO `sys_role_menu` VALUES (1, 38);
INSERT INTO `sys_role_menu` VALUES (1, 43);
INSERT INTO `sys_role_menu` VALUES (1, 44);
INSERT INTO `sys_role_menu` VALUES (1343796202273001474, 1);
INSERT INTO `sys_role_menu` VALUES (1343796202273001474, 4);
INSERT INTO `sys_role_menu` VALUES (1343796202273001474, 5);
INSERT INTO `sys_role_menu` VALUES (1343796202273001474, 9);
INSERT INTO `sys_role_menu` VALUES (1343796202273001474, 11);
INSERT INTO `sys_role_menu` VALUES (1343796202273001474, 12);
INSERT INTO `sys_role_menu` VALUES (1343796202273001474, 13);
INSERT INTO `sys_role_menu` VALUES (1343796202273001474, 39);
INSERT INTO `sys_role_menu` VALUES (1343796202273001474, 42);
INSERT INTO `sys_role_menu` VALUES (1343796202273001474, 38);
INSERT INTO `sys_role_menu` VALUES (1343796202273001474, 43);
INSERT INTO `sys_role_menu` VALUES (1343796202273001474, 44);

SET FOREIGN_KEY_CHECKS = 1;
