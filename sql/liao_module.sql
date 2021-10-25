/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.2.72
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : 192.168.2.72:3306
 Source Schema         : liao_module

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 25/10/2021 13:38:31
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
) ENGINE = InnoDB AUTO_INCREMENT = 1451354328415899651 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = DYNAMIC;

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
  `admin_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
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
) ENGINE = InnoDB AUTO_INCREMENT = 1442004961175355395 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '管理员' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES (1339032578400731137, 'liao', 'liao', 'Wick', '女', '', 'liao991224@gmail.com', 'Admin', NULL, 2, 0, '2020-12-16 10:20:19', '2021-10-22 09:08:46');
INSERT INTO `sys_admin` VALUES (1442004961175355394, 'test', 'test', 'User', '男', 'test', 'update@update.com', 'update', NULL, 1, 0, '2021-09-26 13:55:49', '2021-10-22 09:09:09');

-- ----------------------------
-- Table structure for sys_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_role`;
CREATE TABLE `sys_admin_role`  (
  `admin_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '管理员角色关联表' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1447843614225125379 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1451462380792082434 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统访问记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1451462789896151042 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '操作日志记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_open_log
-- ----------------------------

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
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, 0, 1, 0, '2020-12-21 11:15:07', '2021-10-22 09:31:24');
INSERT INTO `sys_role` VALUES (1343796202273001474, '普通管理员', 'mange', 2, 0, 1, 0, '2020-12-29 13:49:16', '2021-09-16 13:54:59');

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
INSERT INTO `sys_role_menu` VALUES (1343796202273001474, 1);
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

SET FOREIGN_KEY_CHECKS = 1;
