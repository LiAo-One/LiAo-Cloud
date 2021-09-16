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

 Date: 16/09/2021 17:01:19
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
) ENGINE = InnoDB AUTO_INCREMENT = 1438009978344742915 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 1438011600408006658 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '管理员' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES (1339032578400731137, 'liao', 'liao', 'LiAo', '女', '', 'liao991224@gmail.com', '冯秋阳是猪', NULL, 2, 0, '2020-12-16 10:20:19', '2021-01-11 20:26:57');

-- ----------------------------
-- Table structure for sys_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_role`;
CREATE TABLE `sys_admin_role`  (
  `admin_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '管理员角色关联表' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1414504576294588418 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1414503278904086531 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1438408289702686723 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统访问记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (1438007928252862466, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-15 13:13:02');
INSERT INTO `sys_logininfor` VALUES (1438013726395846658, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-15 13:36:04');
INSERT INTO `sys_logininfor` VALUES (1438019256996634625, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-15 13:58:03');
INSERT INTO `sys_logininfor` VALUES (1438063165181349890, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-15 16:52:31');
INSERT INTO `sys_logininfor` VALUES (1438064972204953602, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-15 16:59:42');
INSERT INTO `sys_logininfor` VALUES (1438064989263187969, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-15 16:59:46');
INSERT INTO `sys_logininfor` VALUES (1438065028022751234, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-15 16:59:55');
INSERT INTO `sys_logininfor` VALUES (1438065219190738946, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-15 17:00:41');
INSERT INTO `sys_logininfor` VALUES (1438065231937228801, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-15 17:00:44');
INSERT INTO `sys_logininfor` VALUES (1438065299922702338, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-15 17:01:00');
INSERT INTO `sys_logininfor` VALUES (1438065456215052290, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-15 17:01:37');
INSERT INTO `sys_logininfor` VALUES (1438070538432237570, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-15 17:21:49');
INSERT INTO `sys_logininfor` VALUES (1438074417597165569, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-15 17:37:14');
INSERT INTO `sys_logininfor` VALUES (1438074476933984257, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-15 17:37:28');
INSERT INTO `sys_logininfor` VALUES (1438075040086409217, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-15 17:39:47');
INSERT INTO `sys_logininfor` VALUES (1438075686000836610, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-15 17:42:16');
INSERT INTO `sys_logininfor` VALUES (1438119170653974529, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-15 20:35:04');
INSERT INTO `sys_logininfor` VALUES (1438345106908549122, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-16 11:32:51');
INSERT INTO `sys_logininfor` VALUES (1438373658148245506, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-16 13:26:18');
INSERT INTO `sys_logininfor` VALUES (1438373686778564610, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-16 13:26:25');
INSERT INTO `sys_logininfor` VALUES (1438373759981752322, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-16 13:26:43');
INSERT INTO `sys_logininfor` VALUES (1438373872238104577, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-16 13:27:09');
INSERT INTO `sys_logininfor` VALUES (1438377546028982273, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-16 13:41:45');
INSERT INTO `sys_logininfor` VALUES (1438381606845091841, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-16 13:57:54');
INSERT INTO `sys_logininfor` VALUES (1438381649597632513, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-16 13:58:04');
INSERT INTO `sys_logininfor` VALUES (1438382857771106306, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-16 14:02:52');
INSERT INTO `sys_logininfor` VALUES (1438404745301856258, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-16 15:29:50');
INSERT INTO `sys_logininfor` VALUES (1438405665490272258, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-16 15:33:30');
INSERT INTO `sys_logininfor` VALUES (1438408289702686722, 'liao', '127.0.0.1,192.168.2.229', '内网IP', 'Chrome 9', 'Windows 10', '0', '登录成功', '2021-09-16 15:43:55');

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
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

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
INSERT INTO `sys_menu` VALUES (38, '代码生成', 1, 5, 'gen', 'tool/gen/index', 1, 0, 'M', '0', '0', NULL, 'code', 1, 0, '2021-07-07 17:38:01', '2021-07-12 16:56:23');
INSERT INTO `sys_menu` VALUES (39, '字典管理', 1, 4, 'dict', 'system/dict/index', 1, 0, 'C', '0', '0', NULL, 'dict', 1, 0, '2021-07-09 09:41:11', '2021-07-12 16:56:14');

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
) ENGINE = InnoDB AUTO_INCREMENT = 1438380874301513730 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '操作日志记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_open_log
-- ----------------------------
INSERT INTO `sys_open_log` VALUES (1414509220890677250, '清空操作日志', 9, 'com.liao.web.controller.system.SysOpenLogController.clean()', 'DELETE', '192.168.2.229', '/sys-open-log/clean', '内网IP', '{}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-07-12 16:57:33');
INSERT INTO `sys_open_log` VALUES (1437977090714796033, '代码生成', 6, 'com.liao.gen.controller.GenTableController.importTableSave()', 'POST', '192.168.2.72', '/gen-table/importTable', '内网IP', '{\"tables\":[\"sys_open_log\"],\"token_str\":[\"e743fe28a08c04b2bfe2e1bbfd74fc29\"],\"timeInfo\":[\"2021-09-15 11:10:23\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 11:10:29');
INSERT INTO `sys_open_log` VALUES (1437977237708374017, '代码生成', 2, 'com.liao.gen.controller.GenTableController.editSave()', 'PUT', '192.168.2.72', '/gen-table', '内网IP', '{}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 11:11:04');
INSERT INTO `sys_open_log` VALUES (1437978835771752449, '代码生成', 2, 'com.liao.gen.controller.GenTableController.editSave()', 'PUT', '192.168.2.72', '/gen-table', '内网IP', '{}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 11:17:25');
INSERT INTO `sys_open_log` VALUES (1437981254853382146, '代码生成', 8, 'com.liao.gen.controller.GenTableController.batchGenCode()', 'GET', '192.168.2.72', '/gen-table/batchGenCode', '内网IP', '{\"tables\":[\"sys_open_log\"]}', 'null', 1, 'Unable to find resource \'templates/vm/java/entity.java.vm\'', '2021-09-15 11:27:02');
INSERT INTO `sys_open_log` VALUES (1437982407917842434, '代码生成', 8, 'com.liao.gen.controller.GenTableController.batchGenCode()', 'GET', '192.168.2.72', '/gen-table/batchGenCode', '内网IP', '{\"tables\":[\"sys_open_log\"]}', 'null', 0, NULL, '2021-09-15 11:31:37');
INSERT INTO `sys_open_log` VALUES (1437983308623654914, '代码生成', 2, 'com.liao.gen.controller.GenTableController.editSave()', 'PUT', '192.168.2.72', '/gen-table', '内网IP', '{}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 11:35:12');
INSERT INTO `sys_open_log` VALUES (1437983733926080514, '代码生成', 2, 'com.liao.gen.controller.GenTableController.editSave()', 'PUT', '192.168.2.72', '/gen-table', '内网IP', '{}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 11:36:53');
INSERT INTO `sys_open_log` VALUES (1437983951375577090, '代码生成业务', 3, 'com.liao.gen.controller.GenTableController.deletes()', 'POST', '192.168.2.72', '/gen-table/del_ids', '内网IP', '{\"ids\":[\"1437977089242595329\"],\"token_str\":[\"4701f9f2e29722f04100598b852e4a96\"],\"timeInfo\":[\"2021-09-15 11:37:40\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 11:37:45');
INSERT INTO `sys_open_log` VALUES (1437984063166373890, '管理员', 1, 'com.liao.system.controller.SysAdminController.add()', 'POST', '192.168.2.72', '/sys-admin/add', '内网IP', '{\"roleId\":[\"1343807643550027778\"],\"adminAccount\":[\"admin123\"],\"adminAvatar\":[\"admin123\"],\"adminEmail\":[\"admin123@ada.com\"],\"adminName\":[\"admin123\"],\"adminNickname\":[\"admin123\"],\"adminPassword\":[\"admin123\"],\"adminSex\":[\"男\"],\"token_str\":[\"1ef898a8fae83250fc68fc79cac89312\"],\"timeInfo\":[\"2021-09-15 11:38:7\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 11:38:12');
INSERT INTO `sys_open_log` VALUES (1437984091196907522, '管理员', 2, 'com.liao.system.controller.SysAdminController.upd()', 'POST', '192.168.2.72', '/sys-admin/upd_id', '内网IP', '{\"adminId\":[\"1437984062990213122\"],\"adminAccount\":[\"admin123\"],\"adminPassword\":[\"admin123\"],\"adminName\":[\"admin123\"],\"adminSex\":[\"女\"],\"adminAvatar\":[\"admin123\"],\"adminEmail\":[\"admin123@ada.com\"],\"adminNickname\":[\"admin123\"],\"roleId\":[\"1343807643550027778\"],\"password\":[\"\"],\"token_str\":[\"16c4bc22eaaad6ba2c491c2b1efb757c\"],\"timeInfo\":[\"2021-09-15 11:38:13\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 11:38:18');
INSERT INTO `sys_open_log` VALUES (1437984119017725953, '管理员', 3, 'com.liao.system.controller.SysAdminController.deletes()', 'POST', '192.168.2.72', '/sys-admin/del_ids', '内网IP', '{\"ids\":[\"1437984062990213122\"],\"token_str\":[\"6d8d7341f65ed7de2ff5d25b33d55079\"],\"timeInfo\":[\"2021-09-15 11:38:20\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 11:38:25');
INSERT INTO `sys_open_log` VALUES (1437984199502225409, '角色表', 1, 'com.liao.system.controller.SysRoleController.add()', 'POST', '192.168.2.72', '/sys-role/add', '内网IP', '{\"roleName\":[\"asdas\"],\"roleSort\":[\"3\"],\"roleStatus\":[\"0\"],\"menuIds\":[\"1,4\"],\"menuCheckStrictly\":[\"true\"],\"deptCheckStrictly\":[\"true\"],\"roleAuthority\":[\"asdcs\"],\"token_str\":[\"8015bad5c53a8293fd29fd2ebdcb3722\"],\"timeInfo\":[\"2021-09-15 11:38:39\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 11:38:44');
INSERT INTO `sys_open_log` VALUES (1437984223418146817, '角色表', 2, 'com.liao.system.controller.SysRoleController.upd()', 'POST', '192.168.2.72', '/sys-role/upd_id', '内网IP', '{\"roleId\":[\"1437984199305093121\"],\"roleName\":[\"asdas\"],\"roleAuthority\":[\"asdcs\"],\"roleSort\":[\"3\"],\"roleStatus\":[\"0\"],\"menuIds\":[\"1,4,5\"],\"token_str\":[\"36cedf4155769fd04968081a989b910c\"],\"timeInfo\":[\"2021-09-15 11:38:44\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 11:38:50');
INSERT INTO `sys_open_log` VALUES (1437984232247156737, '角色表', 3, 'com.liao.system.controller.SysRoleController.deletes()', 'POST', '192.168.2.72', '/sys-role/del_ids/', '内网IP', '{\"ids\":[\"1437984199305093121\"],\"token_str\":[\"3cd8bcff8759bac7efbbdb4b425bcf62\"],\"timeInfo\":[\"2021-09-15 11:38:47\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 11:38:52');
INSERT INTO `sys_open_log` VALUES (1437984247820607490, '角色表', 3, 'com.liao.system.controller.SysRoleController.deletes()', 'POST', '192.168.2.72', '/sys-role/del_ids/', '内网IP', '{\"ids\":[\"1343807643550027778\"],\"token_str\":[\"bad613ad450d2e4903be238c152900fc\"],\"timeInfo\":[\"2021-09-15 11:38:51\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 11:38:56');
INSERT INTO `sys_open_log` VALUES (1437984381811843074, '菜单表', 1, 'com.liao.system.controller.SysMenuController.add()', 'POST', '192.168.2.72', '/sys-menu/add', '内网IP', '{\"parentId\":[\"1\"],\"menuName\":[\"asda\"],\"icon\":[\"list\"],\"menuType\":[\"M\"],\"orderNum\":[\"7\"],\"isFrame\":[\"1\"],\"isCache\":[\"0\"],\"visible\":[\"0\"],\"status\":[\"0\"],\"component\":[\"/system/out\"],\"path\":[\"Out\"],\"token_str\":[\"1dcfb136a57474b6cb39935c79b38efe\"],\"timeInfo\":[\"2021-09-15 11:39:23\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 11:39:28');
INSERT INTO `sys_open_log` VALUES (1437984412103106561, '菜单表', 2, 'com.liao.system.controller.SysMenuController.upd()', 'POST', '192.168.2.72', '/sys-menu/upd_id', '内网IP', '{\"menuId\":[\"40\"],\"menuName\":[\"asda\"],\"parentId\":[\"1\"],\"orderNum\":[\"9\"],\"path\":[\"Out\"],\"component\":[\"/system/out\"],\"isFrame\":[\"1\"],\"isCache\":[\"0\"],\"menuType\":[\"M\"],\"visible\":[\"0\"],\"status\":[\"0\"],\"icon\":[\"list\"],\"token_str\":[\"15c972130ab373feda575fb07171c312\"],\"timeInfo\":[\"2021-09-15 11:39:29\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 11:39:35');
INSERT INTO `sys_open_log` VALUES (1437984421963915266, '菜单表', 3, 'com.liao.system.controller.SysMenuController.delete()', 'POST', '192.168.2.72', '/sys-menu/del_id/', '内网IP', '{\"id\":[\"40\"],\"token_str\":[\"39af14db4febb16c373d8a89bda95ccc\"],\"timeInfo\":[\"2021-09-15 11:39:32\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 11:39:37');
INSERT INTO `sys_open_log` VALUES (1438008093240004609, '系统访问记录', 3, 'com.liao.system.controller.SysLogininforController.deletes()', 'POST', '192.168.2.72', '/sys-logininfor/del_ids', '内网IP', '{\"ids\":[\"1437990063453855745,1437987928129462274\"],\"token_str\":[\"4b4bcc904b27ee8b3b4ac749ef60f6ba\"],\"timeInfo\":[\"2021-09-15 13:13:36\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 13:13:41');
INSERT INTO `sys_open_log` VALUES (1438009979066163202, '代码生成', 6, 'com.liao.gen.controller.GenTableController.importTableSave()', 'POST', '192.168.2.72', '/gen-table/importTable', '内网IP', '{\"tables\":[\"sys_admin_role\"],\"token_str\":[\"da28402d810a697b336ded53084434fd\"],\"timeInfo\":[\"2021-09-15 13:21:5\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 13:21:11');
INSERT INTO `sys_open_log` VALUES (1438011600810659841, '管理员', 1, 'com.liao.system.controller.SysAdminController.add()', 'POST', '192.168.2.72', '/sys-admin/add', '内网IP', '{\"adminAccount\":[\"123qwew\"],\"adminAvatar\":[\"123qwew\"],\"adminEmail\":[\"123qwew@qq.com\"],\"adminName\":[\"123qwew\"],\"adminNickname\":[\"123qwew\"],\"adminPassword\":[\"123qwew\"],\"adminSex\":[\"男\"],\"token_str\":[\"e37ee148f17b31eb39f989fe98ae0eb9\"],\"timeInfo\":[\"2021-09-15 13:27:31\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 13:27:37');
INSERT INTO `sys_open_log` VALUES (1438076669959458817, '操作日志记录', 3, 'com.liao.system.controller.SysOpenLogController.deletes()', 'POST', '192.168.2.72', '/sys-open-log/del_ids/', '内网IP', '{\"ids\":[\"1438011685862756353\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 17:46:11');
INSERT INTO `sys_open_log` VALUES (1438077303290974209, '操作日志记录', 3, 'com.liao.system.controller.SysOpenLogController.deletes()', 'POST', '192.168.2.72', '/sys-open-log/del_ids/', '内网IP', '{\"ids\":[\"1438010074486579202\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-15 17:48:42');
INSERT INTO `sys_open_log` VALUES (1438379717726339073, '角色表', 2, 'com.liao.system.controller.SysRoleController.upd()', 'POST', '192.168.2.72', '/sys-role/upd_id', '内网IP', '{\"roleId\":[\"1343796202273001474\"],\"roleName\":[\"普通管理员\"],\"roleAuthority\":[\"mange\"],\"roleSort\":[\"2\"],\"roleStatus\":[\"0\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-16 13:50:23');
INSERT INTO `sys_open_log` VALUES (1438379743517114370, '角色表', 2, 'com.liao.system.controller.SysRoleController.upd()', 'POST', '192.168.2.72', '/sys-role/upd_id', '内网IP', '{\"roleId\":[\"1343796202273001474\"],\"roleName\":[\"普通管理员\"],\"roleAuthority\":[\"mange\"],\"roleSort\":[\"2\"],\"roleStatus\":[\"0\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-16 13:50:29');
INSERT INTO `sys_open_log` VALUES (1438380453881257985, '角色表', 2, 'com.liao.system.controller.SysRoleController.upd()', 'POST', '192.168.2.72', '/sys-role/upd_id', '内网IP', '{\"roleId\":[\"1343796202273001474\"],\"roleName\":[\"普通管理员\"],\"roleAuthority\":[\"mange\"],\"roleSort\":[\"2\"],\"roleStatus\":[\"0\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-16 13:53:19');
INSERT INTO `sys_open_log` VALUES (1438380874301513729, '角色表', 2, 'com.liao.system.controller.SysRoleController.upd()', 'POST', '192.168.2.72', '/sys-role/upd_id', '内网IP', '{\"roleId\":[\"1343796202273001474\"],\"roleName\":[\"普通管理员\"],\"roleAuthority\":[\"mange\"],\"roleSort\":[\"2\"],\"roleStatus\":[\"0\"],\"menuIds\":[\"1\"]}', '{\"msg\":\"成功\",\"code\":200}', 0, NULL, '2021-09-16 13:54:59');

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
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, 0, 1, 0, '2020-12-21 11:15:07', '2021-07-12 16:56:41');
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
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 9);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (1, 13);
INSERT INTO `sys_role_menu` VALUES (1, 38);
INSERT INTO `sys_role_menu` VALUES (1, 39);
INSERT INTO `sys_role_menu` VALUES (1437984199305093121, 1);
INSERT INTO `sys_role_menu` VALUES (1437984199305093121, 4);
INSERT INTO `sys_role_menu` VALUES (1437984199305093121, 5);
INSERT INTO `sys_role_menu` VALUES (1343796202273001474, 1);

SET FOREIGN_KEY_CHECKS = 1;
