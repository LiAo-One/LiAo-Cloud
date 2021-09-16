/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.2.239_3306
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : 192.168.2.239:3306
 Source Schema         : liao-config

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 16/09/2021 17:01:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (1, 'liao-gateway-dev.yml', 'DEFAULT_GROUP', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          lowerCaseServiceId: true\n          enabled: true\n      routes:\n        # 系统模块\n        - id: liao-system\n          uri: lb://liao-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: liao-gen\n          uri: lb://liao-gen\n          predicates:\n            - Path=/gen/**\n          filters:\n            - StripPrefix=1\n# 安全配置\nsecurity:\n  # 不校验白名单\n  ignore:\n    whites:\n      - /system/sys-admin/login\n      - /system/sys-admin/logout\n      - /system/token/token-mes-all\n      - /system/token/token-mes', '5d4c91e66d0a79d229687a64cf9e7a60', '2021-09-14 06:08:38', '2021-09-15 11:25:10', 'nacos', '192.168.2.72', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (6, 'liao-system-dev.yml', 'DEFAULT_GROUP', 'spring:\n  # 连接信息\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  datasource:\n    username: root\n    password: liao\n    type: com.alibaba.druid.pool.DruidDataSource\n    url: jdbc:mysql://192.168.2.72:3306/liao_module?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n    # 连接池\n    druid:\n      # 初始连接数\n      initialSize: 5\n      # 最小连接池数量\n      minIdle: 10\n      # 最大连接池数量\n      maxActive: 20\n      # 配置获取连接等待超时的时间\n      maxWait: 60000\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\n      timeBetweenEvictionRunsMillis: 60000\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\n      minEvictableIdleTimeMillis: 300000\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\n      # maxEvictableIdleTimeMillis: 900000\n      # 配置检测连接是否有效\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        # 设置白名单，不填则允许所有访问\n        allow:\n        url-pattern: /druid/*\n        # 控制台管理用户名和密码\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          # 慢SQL记录\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n  # Redis\n  redis:\n    host: 192.168.2.87\n    port: 6379\n    database: 0\n    #连 接超时时间\n    timeout: 30000\n\n# mybatis\nmybatis:\n  mapperClassScan: com.liao.**.dao\n  type-aliases-package: com.liao.**.entity\n  config-locations: classpath:mybatis-config.xml\n  mapper-locations: classpath*:mapper/*Mapper.xml\n# mybatis-plus\nmybatis-plus:\n  mapperClassScan: com.liao.**.dao\n  type-aliases-package: com.liao.**.entity\n  config-locations: classpath:mybatis-config.xml\n  mapper-locations: classpath*:mapper/*Mapper.xml\n  configuration:\n    # 日志打印\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n  global-config:\n    db-config:\n      #主键类型  0:\"数据库ID自增\", 1:\"用户输入ID\",2:\"全局唯一ID (数字类型唯一ID)\", 3:\"全局唯一ID UUID\";\n      id-type: ID_WORKER\n      # 逻辑已删除值(默认为 1)\n      logic-delete-value: 1\n      # 逻辑未删除值(默认为 0)\n      logic-not-delete-value: 0', '4404c1fddde60ba446ee47e729f0ea08', '2021-09-14 08:53:17', '2021-09-15 12:25:16', 'nacos', '192.168.2.72', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (12, 'liao-gen-dev.yml', 'DEFAULT_GROUP', 'spring:\n  # 连接信息\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  datasource:\n    username: root\n    password: liao\n    type: com.alibaba.druid.pool.DruidDataSource\n    url: jdbc:mysql://localhost:3306/liao_module?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n    # 连接池\n    druid:\n      # 初始连接数\n      initialSize: 5\n      # 最小连接池数量\n      minIdle: 10\n      # 最大连接池数量\n      maxActive: 20\n      # 配置获取连接等待超时的时间\n      maxWait: 60000\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\n      #timeBetweenEvictionRunsMillis: 60000\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\n      minEvictableIdleTimeMillis: 300000\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\n      # maxEvictableIdleTimeMillis: 900000\n      # 配置检测连接是否有效\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        # 设置白名单，不填则允许所有访问\n        allow:\n        url-pattern: /druid/*\n        # 控制台管理用户名和密码\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          # 慢SQL记录\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n  # Redis\n  redis:\n    host: 192.168.2.87\n    port: 6379\n    database: 0\n    #连 接超时时间\n    timeout: 30000\n\n# mybatis\nmybatis:\n  mapperClassScan: com.liao.**.dao\n  type-aliases-package: com.liao.**.entity\n  config-locations: classpath:mybatis-config.xml\n  mapper-locations: classpath*:mapper/*Mapper.xml\n# mybatis-plus\nmybatis-plus:\n  mapperClassScan: com.liao.**.dao\n  type-aliases-package: com.liao.**.entity\n  config-locations: classpath:mybatis-config.xml\n  mapper-locations: classpath*:mapper/*Mapper.xml\n  configuration:\n    # 日志打印\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n  global-config:\n    db-config:\n      #主键类型  0:\"数据库ID自增\", 1:\"用户输入ID\",2:\"全局唯一ID (数字类型唯一ID)\", 3:\"全局唯一ID UUID\";\n      id-type: ID_WORKER\n      # 逻辑已删除值(默认为 1)\n      logic-delete-value: 1\n      # 逻辑未删除值(默认为 0)\n      logic-not-delete-value: 0\n\n# 代码生成\ngen:\n  # 作者\n  author: LiAo\n  # 默认生成包路径 system 需改成自己的模块名称 如 system monitor tool\n  packageName: com.liao.system\n  # 自动去除表前缀，默认是false\n  autoRemovePre: false\n  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）\n  tablePrefix: sys_', 'b92c1c82880853dff9a403bfdf293e13', '2021-09-15 02:35:11', '2021-09-15 03:25:12', 'nacos', '192.168.2.72', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (26, 'liao-monitor-dev.yml', 'DEFAULT_GROUP', '# spring\r\nspring: \r\n  security:\r\n    user:\r\n      name: admin\r\n      password: admin\r\n  boot:\r\n    admin:\r\n      ui:\r\n        title: 服务状态监控\r\n', '2f49f0bd78f4e6d8ceef46dfa020c1cd', '2021-09-16 01:58:28', '2021-09-16 01:58:28', NULL, '192.168.2.72', '', '', NULL, NULL, NULL, 'yaml', NULL);

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint UNSIGNED NOT NULL,
  `nid` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 1, 'liao-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:', '8a8c3fec24cde5f873a43f62bc53ef6f', '2021-09-14 14:08:38', '2021-09-14 06:08:38', NULL, '192.168.2.72', 'I', '');
INSERT INTO `his_config_info` VALUES (1, 2, 'liao-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:', '8a8c3fec24cde5f873a43f62bc53ef6f', '2021-09-14 15:40:14', '2021-09-14 07:40:14', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 3, 'liao-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n    cloud:\n        gateway:\n        routes:\n            # 系统模块\n            - id: ruoyi-system\n            uri: lb://ruoyi-system\n            predicates:\n                - Path=/system/**\n            filters:\n                - StripPrefix=1', '3a31d9b8cfce2090e9bb26485de6b180', '2021-09-14 15:43:22', '2021-09-14 07:43:23', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 4, 'liao-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        # 系统模块\n        - id: ruoyi-system\n          uri: lb://ruoyi-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1', 'c391193d96f81906f83fbe22cb0a4c1a', '2021-09-14 16:51:21', '2021-09-14 08:51:21', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 5, 'liao-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        # 系统模块\n        - id: ruoyi-system\n          uri: lb://ruoyi-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1', 'c391193d96f81906f83fbe22cb0a4c1a', '2021-09-14 16:51:33', '2021-09-14 08:51:34', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 6, 'liao-system-dev.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  profiles:\r\n    # 生产环境\r\n    active: dev\r\n  # 连接信息\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n  datasource:\r\n    username: root\r\n    password: liao\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    url: jdbc:mysql://localhost:3306/liao_module?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n    # 连接池\r\n    druid:\r\n      # 初始连接数\r\n      initialSize: 5\r\n      # 最小连接池数量\r\n      minIdle: 10\r\n      # 最大连接池数量\r\n      maxActive: 20\r\n      # 配置获取连接等待超时的时间\r\n      maxWait: 60000\r\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      timeBetweenEvictionRunsMillis: 60000\r\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000\r\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\r\n      maxEvictableIdleTimeMillis: 900000\r\n      # 配置检测连接是否有效\r\n      validationQuery: SELECT 1 FROM DUAL\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n      webStatFilter:\r\n        enabled: true\r\n      statViewServlet:\r\n        enabled: true\r\n        # 设置白名单，不填则允许所有访问\r\n        allow:\r\n        url-pattern: /druid/*\r\n        # 控制台管理用户名和密码\r\n        login-username:\r\n        login-password:\r\n      filter:\r\n        stat:\r\n          enabled: true\r\n          # 慢SQL记录\r\n          log-slow-sql: true\r\n          slow-sql-millis: 1000\r\n          merge-sql: true\r\n        wall:\r\n          config:\r\n            multi-statement-allow: true\r\n  # Redis\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    database: 0\r\n    #连 接超时时间\r\n    timeout: 30000\r\n\r\n# mybatis\r\nmybatis:\r\n  mapperClassScan: com.liao.**.dao\r\n  type-aliases-package: com.liao.**.entity\r\n  config-locations: classpath:mybatis-config.xml\r\n  mapper-locations: classpath*:mapper/*Mapper.xml\r\n# mybatis-plus\r\nmybatis-plus:\r\n  mapperClassScan: com.liao.**.dao\r\n  type-aliases-package: com.liao.**.entity\r\n  config-locations: classpath:mybatis-config.xml\r\n  mapper-locations: classpath*:mapper/*Mapper.xml\r\n  configuration:\r\n    # 日志打印\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\r\n  global-config:\r\n    db-config:\r\n      #主键类型  0:\"数据库ID自增\", 1:\"用户输入ID\",2:\"全局唯一ID (数字类型唯一ID)\", 3:\"全局唯一ID UUID\";\r\n      id-type: ID_WORKER\r\n      # 逻辑已删除值(默认为 1)\r\n      logic-delete-value: 1\r\n      # 逻辑未删除值(默认为 0)\r\n      logic-not-delete-value: 0', 'fb264f6188fddccc1d29ca67744ab007', '2021-09-14 16:53:16', '2021-09-14 08:53:17', NULL, '192.168.2.72', 'I', '');
INSERT INTO `his_config_info` VALUES (6, 7, 'liao-system-dev.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  profiles:\r\n    # 生产环境\r\n    active: dev\r\n  # 连接信息\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n  datasource:\r\n    username: root\r\n    password: liao\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    url: jdbc:mysql://localhost:3306/liao_module?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n    # 连接池\r\n    druid:\r\n      # 初始连接数\r\n      initialSize: 5\r\n      # 最小连接池数量\r\n      minIdle: 10\r\n      # 最大连接池数量\r\n      maxActive: 20\r\n      # 配置获取连接等待超时的时间\r\n      maxWait: 60000\r\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      timeBetweenEvictionRunsMillis: 60000\r\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000\r\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\r\n      maxEvictableIdleTimeMillis: 900000\r\n      # 配置检测连接是否有效\r\n      validationQuery: SELECT 1 FROM DUAL\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n      webStatFilter:\r\n        enabled: true\r\n      statViewServlet:\r\n        enabled: true\r\n        # 设置白名单，不填则允许所有访问\r\n        allow:\r\n        url-pattern: /druid/*\r\n        # 控制台管理用户名和密码\r\n        login-username:\r\n        login-password:\r\n      filter:\r\n        stat:\r\n          enabled: true\r\n          # 慢SQL记录\r\n          log-slow-sql: true\r\n          slow-sql-millis: 1000\r\n          merge-sql: true\r\n        wall:\r\n          config:\r\n            multi-statement-allow: true\r\n  # Redis\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    database: 0\r\n    #连 接超时时间\r\n    timeout: 30000\r\n\r\n# mybatis\r\nmybatis:\r\n  mapperClassScan: com.liao.**.dao\r\n  type-aliases-package: com.liao.**.entity\r\n  config-locations: classpath:mybatis-config.xml\r\n  mapper-locations: classpath*:mapper/*Mapper.xml\r\n# mybatis-plus\r\nmybatis-plus:\r\n  mapperClassScan: com.liao.**.dao\r\n  type-aliases-package: com.liao.**.entity\r\n  config-locations: classpath:mybatis-config.xml\r\n  mapper-locations: classpath*:mapper/*Mapper.xml\r\n  configuration:\r\n    # 日志打印\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\r\n  global-config:\r\n    db-config:\r\n      #主键类型  0:\"数据库ID自增\", 1:\"用户输入ID\",2:\"全局唯一ID (数字类型唯一ID)\", 3:\"全局唯一ID UUID\";\r\n      id-type: ID_WORKER\r\n      # 逻辑已删除值(默认为 1)\r\n      logic-delete-value: 1\r\n      # 逻辑未删除值(默认为 0)\r\n      logic-not-delete-value: 0', 'fb264f6188fddccc1d29ca67744ab007', '2021-09-14 16:59:07', '2021-09-14 08:59:08', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (6, 8, 'liao-system-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  profiles:\n    # 生产环境\n    active: dev\n  # 连接信息\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  datasource:\n    username: root\n    password: liao\n    type: com.alibaba.druid.pool.DruidDataSource\n    url: jdbc:mysql://localhost:3306/liao_module?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n    # 连接池\n    druid:\n      # 初始连接数\n      initialSize: 5\n      # 最小连接池数量\n      minIdle: 10\n      # 最大连接池数量\n      maxActive: 20\n      # 配置获取连接等待超时的时间\n      maxWait: 60000\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\n      timeBetweenEvictionRunsMillis: 60000\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\n      minEvictableIdleTimeMillis: 300000\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\n      maxEvictableIdleTimeMillis: 900000\n      # 配置检测连接是否有效\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        # 设置白名单，不填则允许所有访问\n        allow:\n        url-pattern: /druid/*\n        # 控制台管理用户名和密码\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          # 慢SQL记录\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n  # Redis\n  redis:\n    host: 192.168.2.87\n    port: 6379\n    database: 0\n    #连 接超时时间\n    timeout: 30000\n\n# mybatis\nmybatis:\n  mapperClassScan: com.liao.**.dao\n  type-aliases-package: com.liao.**.entity\n  config-locations: classpath:mybatis-config.xml\n  mapper-locations: classpath*:mapper/*Mapper.xml\n# mybatis-plus\nmybatis-plus:\n  mapperClassScan: com.liao.**.dao\n  type-aliases-package: com.liao.**.entity\n  config-locations: classpath:mybatis-config.xml\n  mapper-locations: classpath*:mapper/*Mapper.xml\n  configuration:\n    # 日志打印\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n  global-config:\n    db-config:\n      #主键类型  0:\"数据库ID自增\", 1:\"用户输入ID\",2:\"全局唯一ID (数字类型唯一ID)\", 3:\"全局唯一ID UUID\";\n      id-type: ID_WORKER\n      # 逻辑已删除值(默认为 1)\n      logic-delete-value: 1\n      # 逻辑未删除值(默认为 0)\n      logic-not-delete-value: 0', 'dca94ce794ec66d92c2c25a7e78098b2', '2021-09-14 17:03:31', '2021-09-14 09:03:32', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (6, 9, 'liao-system-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  # 连接信息\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  datasource:\n    username: root\n    password: liao\n    type: com.alibaba.druid.pool.DruidDataSource\n    url: jdbc:mysql://localhost:3306/liao_module?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n    # 连接池\n    druid:\n      # 初始连接数\n      initialSize: 5\n      # 最小连接池数量\n      minIdle: 10\n      # 最大连接池数量\n      maxActive: 20\n      # 配置获取连接等待超时的时间\n      maxWait: 60000\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\n      timeBetweenEvictionRunsMillis: 60000\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\n      minEvictableIdleTimeMillis: 300000\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\n      maxEvictableIdleTimeMillis: 900000\n      # 配置检测连接是否有效\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        # 设置白名单，不填则允许所有访问\n        allow:\n        url-pattern: /druid/*\n        # 控制台管理用户名和密码\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          # 慢SQL记录\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n  # Redis\n  redis:\n    host: 192.168.2.87\n    port: 6379\n    database: 0\n    #连 接超时时间\n    timeout: 30000\n\n# mybatis\nmybatis:\n  mapperClassScan: com.liao.**.dao\n  type-aliases-package: com.liao.**.entity\n  config-locations: classpath:mybatis-config.xml\n  mapper-locations: classpath*:mapper/*Mapper.xml\n# mybatis-plus\nmybatis-plus:\n  mapperClassScan: com.liao.**.dao\n  type-aliases-package: com.liao.**.entity\n  config-locations: classpath:mybatis-config.xml\n  mapper-locations: classpath*:mapper/*Mapper.xml\n  configuration:\n    # 日志打印\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n  global-config:\n    db-config:\n      #主键类型  0:\"数据库ID自增\", 1:\"用户输入ID\",2:\"全局唯一ID (数字类型唯一ID)\", 3:\"全局唯一ID UUID\";\n      id-type: ID_WORKER\n      # 逻辑已删除值(默认为 1)\n      logic-delete-value: 1\n      # 逻辑未删除值(默认为 0)\n      logic-not-delete-value: 0', 'fac6416361d7afbce1fea65a17e8ca59', '2021-09-14 17:07:45', '2021-09-14 09:07:45', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (6, 10, 'liao-system-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  # 连接信息\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  datasource:\n    username: root\n    password: liao\n    type: com.alibaba.druid.pool.DruidDataSource\n    url: jdbc:mysql://localhost:3306/liao_module?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n    # 连接池\n    druid:\n      # 初始连接数\n      initialSize: 5\n      # 最小连接池数量\n      minIdle: 10\n      # 最大连接池数量\n      maxActive: 20\n      # 配置获取连接等待超时的时间\n      maxWait: 60000\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\n      timeBetweenEvictionRunsMillis: 60000\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\n      minEvictableIdleTimeMillis: 300000\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\n      maxEvictableIdleTimeMillis: 900000\n      # 配置检测连接是否有效\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        # 设置白名单，不填则允许所有访问\n        allow:\n        url-pattern: /druid/*\n        # 控制台管理用户名和密码\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          # 慢SQL记录\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n  # Redis\n  redis:\n    host: 192.168.2.87\n    port: 6379\n    database: 0\n    #连 接超时时间\n    timeout: 30000\n\n# mybatis\nmybatis:\n  mapperClassScan: com.liao.**.dao\n  type-aliases-package: com.liao.**.entity\n  config-locations: classpath:mybatis-config.xml\n  mapper-locations: classpath*:mapper/*Mapper.xml\n# mybatis-plus\nmybatis-plus:\n  mapperClassScan: com.liao.**.dao\n  type-aliases-package: com.liao.**.entity\n  config-locations: classpath:mybatis-config.xml\n  mapper-locations: classpath*:mapper/*Mapper.xml\n  configuration:\n    # 日志打印\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n  global-config:\n    db-config:\n      #主键类型  0:\"数据库ID自增\", 1:\"用户输入ID\",2:\"全局唯一ID (数字类型唯一ID)\", 3:\"全局唯一ID UUID\";\n      id-type: ID_WORKER\n      # 逻辑已删除值(默认为 1)\n      logic-delete-value: 1\n      # 逻辑未删除值(默认为 0)\n      logic-not-delete-value: 0', 'fac6416361d7afbce1fea65a17e8ca59', '2021-09-14 17:26:38', '2021-09-14 09:26:38', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 11, 'liao-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        # 系统模块\n        - id: liao-system\n          uri: lb://liao-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1', '9bc1b528f727d7b2b946d530f4244c20', '2021-09-14 17:34:26', '2021-09-14 09:34:27', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 12, 'liao-gen-dev.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  # 连接信息\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n  datasource:\r\n    username: root\r\n    password: liao\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    url: jdbc:mysql://localhost:3306/liao_module?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n    # 连接池\r\n    druid:\r\n      # 初始连接数\r\n      initialSize: 5\r\n      # 最小连接池数量\r\n      minIdle: 10\r\n      # 最大连接池数量\r\n      maxActive: 20\r\n      # 配置获取连接等待超时的时间\r\n      maxWait: 60000\r\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      timeBetweenEvictionRunsMillis: 60000\r\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000\r\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\r\n      # maxEvictableIdleTimeMillis: 900000\r\n      # 配置检测连接是否有效\r\n      validationQuery: SELECT 1 FROM DUAL\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n      webStatFilter:\r\n        enabled: true\r\n      statViewServlet:\r\n        enabled: true\r\n        # 设置白名单，不填则允许所有访问\r\n        allow:\r\n        url-pattern: /druid/*\r\n        # 控制台管理用户名和密码\r\n        login-username:\r\n        login-password:\r\n      filter:\r\n        stat:\r\n          enabled: true\r\n          # 慢SQL记录\r\n          log-slow-sql: true\r\n          slow-sql-millis: 1000\r\n          merge-sql: true\r\n        wall:\r\n          config:\r\n            multi-statement-allow: true\r\n  # Redis\r\n  redis:\r\n    host: 192.168.2.87\r\n    port: 6379\r\n    database: 0\r\n    #连 接超时时间\r\n    timeout: 30000\r\n\r\n# mybatis\r\nmybatis:\r\n  mapperClassScan: com.liao.**.dao\r\n  type-aliases-package: com.liao.**.entity\r\n  config-locations: classpath:mybatis-config.xml\r\n  mapper-locations: classpath*:mapper/*Mapper.xml\r\n# mybatis-plus\r\nmybatis-plus:\r\n  mapperClassScan: com.liao.**.dao\r\n  type-aliases-package: com.liao.**.entity\r\n  config-locations: classpath:mybatis-config.xml\r\n  mapper-locations: classpath*:mapper/*Mapper.xml\r\n  configuration:\r\n    # 日志打印\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\r\n  global-config:\r\n    db-config:\r\n      #主键类型  0:\"数据库ID自增\", 1:\"用户输入ID\",2:\"全局唯一ID (数字类型唯一ID)\", 3:\"全局唯一ID UUID\";\r\n      id-type: ID_WORKER\r\n      # 逻辑已删除值(默认为 1)\r\n      logic-delete-value: 1\r\n      # 逻辑未删除值(默认为 0)\r\n      logic-not-delete-value: 0', 'efd5134c7d5d4d987ed2ffd926260a49', '2021-09-15 10:35:10', '2021-09-15 02:35:11', NULL, '192.168.2.72', 'I', '');
INSERT INTO `his_config_info` VALUES (1, 13, 'liao-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        # 系统模块\n        - id: liao-system\n          uri: lb://liao-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1', '9bc1b528f727d7b2b946d530f4244c20', '2021-09-15 10:37:38', '2021-09-15 02:37:38', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 14, 'liao-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        # 系统模块\n        - id: liao-system\n          uri: lb://liao-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: liao-gen\n          uri: lb://liao-gen\n          predicates:\n            - Path=/gen/**\n          filters:\n            - StripPrefix=1', 'c365a45186f4b97ad8a9d0ca83a66c19', '2021-09-15 10:38:58', '2021-09-15 02:38:59', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (12, 15, 'liao-gen-dev.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  # 连接信息\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n  datasource:\r\n    username: root\r\n    password: liao\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    url: jdbc:mysql://localhost:3306/liao_module?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\r\n    # 连接池\r\n    druid:\r\n      # 初始连接数\r\n      initialSize: 5\r\n      # 最小连接池数量\r\n      minIdle: 10\r\n      # 最大连接池数量\r\n      maxActive: 20\r\n      # 配置获取连接等待超时的时间\r\n      maxWait: 60000\r\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      timeBetweenEvictionRunsMillis: 60000\r\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000\r\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\r\n      # maxEvictableIdleTimeMillis: 900000\r\n      # 配置检测连接是否有效\r\n      validationQuery: SELECT 1 FROM DUAL\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n      webStatFilter:\r\n        enabled: true\r\n      statViewServlet:\r\n        enabled: true\r\n        # 设置白名单，不填则允许所有访问\r\n        allow:\r\n        url-pattern: /druid/*\r\n        # 控制台管理用户名和密码\r\n        login-username:\r\n        login-password:\r\n      filter:\r\n        stat:\r\n          enabled: true\r\n          # 慢SQL记录\r\n          log-slow-sql: true\r\n          slow-sql-millis: 1000\r\n          merge-sql: true\r\n        wall:\r\n          config:\r\n            multi-statement-allow: true\r\n  # Redis\r\n  redis:\r\n    host: 192.168.2.87\r\n    port: 6379\r\n    database: 0\r\n    #连 接超时时间\r\n    timeout: 30000\r\n\r\n# mybatis\r\nmybatis:\r\n  mapperClassScan: com.liao.**.dao\r\n  type-aliases-package: com.liao.**.entity\r\n  config-locations: classpath:mybatis-config.xml\r\n  mapper-locations: classpath*:mapper/*Mapper.xml\r\n# mybatis-plus\r\nmybatis-plus:\r\n  mapperClassScan: com.liao.**.dao\r\n  type-aliases-package: com.liao.**.entity\r\n  config-locations: classpath:mybatis-config.xml\r\n  mapper-locations: classpath*:mapper/*Mapper.xml\r\n  configuration:\r\n    # 日志打印\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\r\n  global-config:\r\n    db-config:\r\n      #主键类型  0:\"数据库ID自增\", 1:\"用户输入ID\",2:\"全局唯一ID (数字类型唯一ID)\", 3:\"全局唯一ID UUID\";\r\n      id-type: ID_WORKER\r\n      # 逻辑已删除值(默认为 1)\r\n      logic-delete-value: 1\r\n      # 逻辑未删除值(默认为 0)\r\n      logic-not-delete-value: 0', 'efd5134c7d5d4d987ed2ffd926260a49', '2021-09-15 10:44:11', '2021-09-15 02:44:11', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (12, 16, 'liao-gen-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  # 连接信息\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  datasource:\n    username: root\n    password: liao\n    type: com.alibaba.druid.pool.DruidDataSource\n    url: jdbc:mysql://localhost:3306/liao_module?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n    # 连接池\n    druid:\n      # 初始连接数\n      initialSize: 5\n      # 最小连接池数量\n      minIdle: 10\n      # 最大连接池数量\n      maxActive: 20\n      # 配置获取连接等待超时的时间\n      maxWait: 60000\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\n      timeBetweenEvictionRunsMillis: 60000\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\n      minEvictableIdleTimeMillis: 300000\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\n      # maxEvictableIdleTimeMillis: 900000\n      # 配置检测连接是否有效\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        # 设置白名单，不填则允许所有访问\n        allow:\n        url-pattern: /druid/*\n        # 控制台管理用户名和密码\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          # 慢SQL记录\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n  # Redis\n  redis:\n    host: 192.168.2.87\n    port: 6379\n    database: 0\n    #连 接超时时间\n    timeout: 30000\n\n# mybatis\nmybatis:\n  mapperClassScan: com.liao.**.dao\n  type-aliases-package: com.liao.**.entity\n  config-locations: classpath:mybatis-config.xml\n  mapper-locations: classpath*:mapper/*Mapper.xml\n# mybatis-plus\nmybatis-plus:\n  mapperClassScan: com.liao.**.dao\n  type-aliases-package: com.liao.**.entity\n  config-locations: classpath:mybatis-config.xml\n  mapper-locations: classpath*:mapper/*Mapper.xml\n  configuration:\n    # 日志打印\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n  global-config:\n    db-config:\n      #主键类型  0:\"数据库ID自增\", 1:\"用户输入ID\",2:\"全局唯一ID (数字类型唯一ID)\", 3:\"全局唯一ID UUID\";\n      id-type: ID_WORKER\n      # 逻辑已删除值(默认为 1)\n      logic-delete-value: 1\n      # 逻辑未删除值(默认为 0)\n      logic-not-delete-value: 0\n\n# 代码生成\ngen:\n  # 作者\n  author: LiAo\n  # 默认生成包路径 system 需改成自己的模块名称 如 system monitor tool\n  packageName: com.liao.system\n  # 自动去除表前缀，默认是false\n  autoRemovePre: false\n  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）\n  tablePrefix: sys_', '43011e6e1d45809082c0c309098b87ee', '2021-09-15 11:25:11', '2021-09-15 03:25:12', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 17, 'liao-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        # 系统模块\n        - id: liao-system\n          uri: lb://liao-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: liao-gen\n          uri: lb://liao-gen\n          predicates:\n            - Path=/gen/**\n          filters:\n            - StripPrefix=1', 'c365a45186f4b97ad8a9d0ca83a66c19', '2021-09-15 14:21:14', '2021-09-15 06:21:14', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 18, 'liao-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        # 系统模块\n        - id: liao-system\n          uri: lb://liao-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: liao-gen\n          uri: lb://liao-gen\n          predicates:\n            - Path=/gen/**\n          filters:\n            - StripPrefix=1\n# 安全配置\nsecurity:\n  # 不校验白名单\n  ignore:\n    whites:\n      - /system/sys-admin/login\n      - /system/sys-admin/logout', 'cafc0b8ab383547a94d175480ece135e', '2021-09-15 16:55:02', '2021-09-15 08:55:03', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 19, 'liao-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          lowerCaseServiceId: true\n          enabled: true\n      routes:\n        # 系统模块\n        - id: liao-system\n          uri: lb://liao-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: liao-gen\n          uri: lb://liao-gen\n          predicates:\n            - Path=/gen/**\n          filters:\n            - StripPrefix=1\n# 安全配置\nsecurity:\n  # 不校验白名单\n  ignore:\n    whites:\n      - /system/sys-admin/login\n      - /system/sys-admin/logout', 'ac48ce5c328788b2071b6ecb8c442b7c', '2021-09-15 17:00:36', '2021-09-15 09:00:36', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 20, 'liao-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          lowerCaseServiceId: true\n          enabled: true\n      routes:\n        # 系统模块\n        - id: liao-system\n          uri: lb://liao-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: liao-gen\n          uri: lb://liao-gen\n          predicates:\n            - Path=/gen/**\n          filters:\n            - StripPrefix=1\n# 安全配置\nsecurity:\n  # 不校验白名单\n  ignore:\n    whites:\n      - /system/sys-admin/login\n      - /system/sys-admin/logout\n      - /system/token/token-mes-all', 'a949815852253e02d2216f2d878677a5', '2021-09-15 17:00:49', '2021-09-15 09:00:50', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 21, 'liao-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          lowerCaseServiceId: true\n          enabled: true\n      routes:\n        # 系统模块\n        - id: liao-system\n          uri: lb://liao-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: liao-gen\n          uri: lb://liao-gen\n          predicates:\n            - Path=/gen/**\n          filters:\n            - StripPrefix=1\n# 安全配置\nsecurity:\n  # 不校验白名单\n  ignore:\n    whites:\n      - /system/sys-admin/login\n      - /system/sys-admin/logout\n      - /system/token/token-mes-all', 'a949815852253e02d2216f2d878677a5', '2021-09-15 17:01:15', '2021-09-15 09:01:16', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 22, 'liao-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          lowerCaseServiceId: true\n          enabled: true\n      routes:\n        # 系统模块\n        - id: liao-system\n          uri: lb://liao-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: liao-gen\n          uri: lb://liao-gen\n          predicates:\n            - Path=/gen/**\n          filters:\n            - StripPrefix=1\n# 安全配置\nsecurity:\n  # 不校验白名单\n  ignore:\n    whites:\n      - /system/sys-admin/login\n      - /system/sys-admin/logout\n      - /system/token/token-mes-all\n      - /system/token/token-mes', '5d4c91e66d0a79d229687a64cf9e7a60', '2021-09-15 19:19:05', '2021-09-15 11:19:05', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 23, 'liao-gateway-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          lowerCaseServiceId: true\n          enabled: true\n      routes:\n        # 系统模块\n        - id: liao-system\n          uri: lb://liao-systems\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: liao-gen\n          uri: lb://liao-gen\n          predicates:\n            - Path=/gen/**\n          filters:\n            - StripPrefix=1\n# 安全配置\nsecurity:\n  # 不校验白名单\n  ignore:\n    whites:\n      - /system/sys-admin/login\n      - /system/sys-admin/logout\n      - /system/token/token-mes-all\n      - /system/token/token-mes', '9a0b35fb76c1b0627637e621580ae5dd', '2021-09-15 19:25:10', '2021-09-15 11:25:10', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (6, 24, 'liao-system-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  # 连接信息\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  datasource:\n    username: root\n    password: liao\n    type: com.alibaba.druid.pool.DruidDataSource\n    url: jdbc:mysql://localhost:3306/liao_module?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n    # 连接池\n    druid:\n      # 初始连接数\n      initialSize: 5\n      # 最小连接池数量\n      minIdle: 10\n      # 最大连接池数量\n      maxActive: 20\n      # 配置获取连接等待超时的时间\n      maxWait: 60000\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\n      timeBetweenEvictionRunsMillis: 60000\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\n      minEvictableIdleTimeMillis: 300000\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\n      # maxEvictableIdleTimeMillis: 900000\n      # 配置检测连接是否有效\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        # 设置白名单，不填则允许所有访问\n        allow:\n        url-pattern: /druid/*\n        # 控制台管理用户名和密码\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          # 慢SQL记录\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n  # Redis\n  redis:\n    host: 192.168.2.87\n    port: 6379\n    database: 0\n    #连 接超时时间\n    timeout: 30000\n\n# mybatis\nmybatis:\n  mapperClassScan: com.liao.**.dao\n  type-aliases-package: com.liao.**.entity\n  config-locations: classpath:mybatis-config.xml\n  mapper-locations: classpath*:mapper/*Mapper.xml\n# mybatis-plus\nmybatis-plus:\n  mapperClassScan: com.liao.**.dao\n  type-aliases-package: com.liao.**.entity\n  config-locations: classpath:mybatis-config.xml\n  mapper-locations: classpath*:mapper/*Mapper.xml\n  configuration:\n    # 日志打印\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n  global-config:\n    db-config:\n      #主键类型  0:\"数据库ID自增\", 1:\"用户输入ID\",2:\"全局唯一ID (数字类型唯一ID)\", 3:\"全局唯一ID UUID\";\n      id-type: ID_WORKER\n      # 逻辑已删除值(默认为 1)\n      logic-delete-value: 1\n      # 逻辑未删除值(默认为 0)\n      logic-not-delete-value: 0', 'fab3860212120984f521e8ee463108dd', '2021-09-15 20:22:14', '2021-09-15 12:22:15', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (6, 25, 'liao-system-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  # 连接信息\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  datasource:\n    username: root\n    password: liao\n    type: com.alibaba.druid.pool.DruidDataSource\n    url: jdbc:mysql://192.168.2.72:3306/liao_module?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n    # 连接池\n    druid:\n      # 初始连接数\n      initialSize: 5\n      # 最小连接池数量\n      minIdle: 10\n      # 最大连接池数量\n      maxActive: 20\n      # 配置获取连接等待超时的时间\n      maxWait: 60000\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\n      timeBetweenEvictionRunsMillis: 60000\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\n      minEvictableIdleTimeMillis: 300000\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\n      # maxEvictableIdleTimeMillis: 900000\n      # 配置检测连接是否有效\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      webStatFilter:\n        enabled: true\n      statViewServlet:\n        enabled: true\n        # 设置白名单，不填则允许所有访问\n        allow:\n        url-pattern: /druid/*\n        # 控制台管理用户名和密码\n        login-username:\n        login-password:\n      filter:\n        stat:\n          enabled: true\n          # 慢SQL记录\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n  # Redis\n  redis:\n    host: 192.168.2.87\n    port: 6379\n    database: 0\n    #连 接超时时间\n    timeout: 30000\n\n# mybatis\nmybatis:\n  mapperClassScan: com.liao.**.dao\n  type-aliases-package: com.liao.**.entity\n  config-locations: classpath:mybatis-config.xml\n  mapper-locations: classpath*:mapper/*Mapper.xml\n# mybatis-plus\nmybatis-plus:\n  mapperClassScan: com.liao.**.dao\n  type-aliases-package: com.liao.**.entity\n  config-locations: classpath:mybatis-config.xml\n  mapper-locations: classpath*:mapper/*Mapper.xml\n  configuration:\n    # 日志打印\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n  global-config:\n    db-config:\n      #主键类型  0:\"数据库ID自增\", 1:\"用户输入ID\",2:\"全局唯一ID (数字类型唯一ID)\", 3:\"全局唯一ID UUID\";\n      id-type: ID_WORKER\n      # 逻辑已删除值(默认为 1)\n      logic-delete-value: 1\n      # 逻辑未删除值(默认为 0)\n      logic-not-delete-value: 0', '4404c1fddde60ba446ee47e729f0ea08', '2021-09-15 20:25:15', '2021-09-15 12:25:16', 'nacos', '192.168.2.72', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 26, 'liao-monitor-dev.yml', 'DEFAULT_GROUP', '', '# spring\r\nspring: \r\n  security:\r\n    user:\r\n      name: admin\r\n      password: admin\r\n  boot:\r\n    admin:\r\n      ui:\r\n        title: 服务状态监控\r\n', '2f49f0bd78f4e6d8ceef46dfa020c1cd', '2021-09-16 09:58:27', '2021-09-16 01:58:28', NULL, '192.168.2.72', 'I', '');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
