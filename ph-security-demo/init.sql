
/*数据库名*/
CREATE DATABASE `ph-security` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
Use `ph-security`;

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `element`
-- ----------------------------
DROP TABLE IF EXISTS `element`;
CREATE TABLE `element` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `menu_id` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `path` varchar(2000) DEFAULT NULL,
  `method` varchar(10) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `element`
-- ----------------------------
BEGIN;
INSERT INTO `element` VALUES ('3', 'userManager:btn_add', 'button', '新增', '/user', '1', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null), ('4', 'userManager:btn_edit', 'button', '编辑', '/user', '1', null, null, 'PUT', '', null, null, null, null, null, null, null, null, null, null, null, null), ('5', 'userManager:btn_del	', 'button', '删除', '/user', '1', null, null, 'DELETE', '', null, null, null, null, null, null, null, null, null, null, null, null), ('9', 'menuManager:element', 'uri', '按钮页面', '/admin/element', '6', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null), ('10', 'menuManager:btn_add', 'button', '新增', '/menu', '6', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null), ('11', 'menuManager:btn_edit', 'button', '编辑', '/menu', '6', '', '', 'PUT', '', '2017-06-24 00:00:00', '', '', '', '', '', '', '', '', '', '', ''), ('12', 'menuManager:btn_del	', 'button', '删除', '/menu', '6', '', '', 'DELETE', '', '2017-06-24 00:00:00', '', '', '', '', '', '', '', '', '', '', ''), ('13', 'menuManager:btn_element_add', 'button', '新增元素', '/element', '6', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null), ('14', 'menuManager:btn_element_edit', 'button', '编辑元素', '/element', '6', null, null, 'PUT', '', null, null, null, null, null, null, null, null, null, null, null, null), ('15', 'btn_element_del', 'button', '删除元素', '/element', '6', null, null, 'DELETE', '', null, null, null, null, null, null, null, null, null, null, null, null), ('16', 'groupManager:btn_add', 'button', '新增', '/group', '7', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null), ('17', 'groupManager:btn_edit', 'button', '编辑', '/group', '7', null, null, 'PUT', '', null, null, null, null, null, null, null, null, null, null, null, null), ('18', 'groupManager:btn_del', 'button', '删除', '/group', '7', null, null, 'DELETE', '', null, null, null, null, null, null, null, null, null, null, null, null), ('19', 'groupManager:btn_userManager', 'button', '分配用户', '/group/{*}/user', '7', null, null, 'PUT', '', null, null, null, null, null, null, null, null, null, null, null, null), ('20', 'groupManager:btn_resourceManager', 'button', '分配权限', '/group/{*}/authority', '7', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null), ('21', 'groupManager:menu', 'uri', '分配菜单', '/group/{*}/authority/menu', '7', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null), ('22', 'groupManager:element', 'uri', '分配资源', '/group/{*}/authority/element', '7', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null), ('23', 'userManager:view	', 'uri', '查看', '/user', '1', '', '', 'GET', '', '2017-06-26 00:00:00', '', '', '', '', '', '', '', '', '', '', ''), ('24', 'menuManager:view', 'uri', '查看', '/menu', '6', '', '', 'GET', '', '2017-06-26 00:00:00', '', '', '', '', '', '', '', '', '', '', ''), ('27', 'menuManager:element_view', 'uri', '查看', '/element', '6', null, null, 'GET', null, null, null, null, null, null, null, null, null, null, null, null, null), ('28', 'groupManager:view', 'uri', '查看', '/group', '7', null, null, 'GET', null, null, null, null, null, null, null, null, null, null, null, null, null), ('30', 'adminAPI:view', 'uri', '查看', '/swagger', '23', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null), ('31', 'adminAPI:swagger', 'uri', '查看', '/v2', '23', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null), ('32', 'groupTypeManager:view', 'uri', '查看', '/groupType', '8', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null), ('33', 'groupTypeManager:btn_add', 'button', '新增', '/groupType', '8', null, null, 'POST', null, null, null, null, null, null, null, null, null, null, null, null, null), ('34', 'groupTypeManager:btn_edit', 'button', '编辑', '/groupType', '8', null, null, 'PUT', null, null, null, null, null, null, null, null, null, null, null, null, null), ('35', 'groupTypeManager:btn_del', 'button', '删除', '/groupType', '8', null, null, 'DELETE', null, null, null, null, null, null, null, null, null, null, null, null, null), ('36', 'gateClientManager:view', 'uri', '查看', '/client', '26', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null), ('37', 'gateClientManager:btn_add', 'button', '新增', '/client', '26', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null), ('38', 'gateClientManager:btn_edit', 'button', '编辑', '/client', '26', null, null, 'PUT', '', null, null, null, null, null, null, null, null, null, null, null, null), ('39', 'gateClientManager:btn_del', 'button', '删除', '/client', '26', null, null, 'DELETE', '', null, null, null, null, null, null, null, null, null, null, null, null), ('40', 'adminAPI:swagger_resources', 'uri', '查看', '/swagger-resources', '23', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null), ('41', 'gateLogManager:view', 'button', '查看', 'Log', '27', null, null, 'GET', '', '2017-07-01 00:00:00', '1', 'admin', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null), ('42', 'gateServiceManager:view', 'uri', '查看', '/service', '28', null, null, 'GET', '', '2017-07-07 12:04:36', '1', '管理员', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null), ('43', 'gateServiceManager:add', 'button', '新增', '/service', '28', null, null, 'POST', '', '2017-07-07 12:06:20', '1', '管理员', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null), ('44', 'gateServiceManager:edit', 'button', '编辑', '/service', '28', null, null, 'PUT', '', '2017-07-07 12:06:45', '1', '管理员', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null), ('45', 'gateServiceManager:del', 'button', '删除', '/service', '28', null, null, 'DELETE', '', '2017-07-07 12:07:07', '1', '管理员', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null), ('46', 'demo:serivce', 'uri', 'demo服务', '/provider/test', '-1', null, null, 'GET', '', '2017-07-09 12:55:15', '1', '管理员', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null), ('47', 'demo:index', 'uri', '首页', '/index', '-1', null, null, 'GET', '', '2017-07-09 12:55:15', '1', '管理员', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) NOT NULL,
  `path` varchar(2000) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  `upd_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `base_group`
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES ('1', 'adminRole', '管理员', '-1', '/adminRole', null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), ('3', 'testGroup', '体验组', '-1', '/testGroup', null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), ('4', 'visitorRole', '游客', '3', '/testGroup/visitorRole', null,'', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), ('5', 'freeRole', '试用', '3', '/testGroup/freeRole', null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), ('6', 'company', 'AG集团', '-1', '/company', null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), ('7', 'financeDepart', '财务部', '6', '/company/financeDepart', null,  '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), ('8', 'hrDepart', '人力资源部', '6', '/company/hrDepart', null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `role_element`
-- ----------------------------
DROP TABLE IF EXISTS `role_element`;
CREATE TABLE `role_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `element_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `role_element`
-- ----------------------------
BEGIN ;
INSERT INTO role_element(id,role_id,element_id) values(1,1,3),(2,1,4),(3,1,5),(4,1,9),(5,1,10),(6,1,12),(7,1,13),(8,1,14),(9,1,15),(10,1,16),(11,1,17),(12,1,18),(13,1,19),(14,1,20),(15,1,21),(16,1,22),(17,1,23),(18,1,24),(19,1,27),(20,1,28),(21,1,30),(22,1,31),(23,1,32),(24,1,33),(25,1,34),(26,1,35),(27,1,36),(28,1,37),(29,1,38),(30,1,39),(31,1,40),(32,1,41),(33,1,42),(34,1,43),(35,1,44),(36,1,45),(37,1,47);
COMMIT;

-- ----------------------------
--  Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `parent_id` int(11) NOT NULL,
  `href` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `order_num` int(11) NOT NULL DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  `path` varchar(500) DEFAULT NULL,
  `enabled` char(1) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  `upd_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `menu`
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES ('1', 'userManager', '用户管理', '5', '/user', '&#xe631;', null, '0', '', '/adminSys/baseManager/userManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), ('5', 'baseManager', '基础配置管理', '13', '', '&#xe631;', null, '0', '', '/adminSys/baseManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), ('6', 'menuManager', '菜单管理', '5', '/menu', '&#xe631;', null, '0', '', '/adminSys/baseManager/menuManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), ('7', 'roleManager', '角色管理', '5', '/role', '&#xe631;', null, '0', '', '/adminSys/baseManager/roleManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null),('13', 'adminSys', '权限管理系统', '-1', '', '&#xe631;', null, '0', '', '/adminSys', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), ('14', 'contentSys', '内容管理系统', '-1', '', '&#xe631;', null, '0', '', '/contentSys', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), ('17', 'articleManger', '文章管理', '20', '/test/monitor', '', null, '0', '', '/contentSys/artComManger/articleManger', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), ('18', 'commentManager', '评论管理', '20', '', '', null, '0', '', '/contentSys/artComManger/commentManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), ('20', 'artComManger', '文章评论管理', '14', '', '&#xe631;', null, '0', '', '/contentSys/artComManger', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), ('22', 'apiManager', '服务Api文档', '13', '', '&#xe631;', null, '0', '', '/adminSys/apiManager', null, null, null, null, null, '2017-07-09 09:14:02', '1', '管理员', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null), ('23', 'adminAPI', '网关鉴权API', '22', '/swagger-ui.html', '&#xe631;', null, '0', '', '/adminSys/apiManager/adminAPI', null, null, null, null, null, '2017-07-09 09:26:06', '1', '管理员', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null), ('25', 'gateWayManager', '网关管理', '13', '', '&#xe631;', null, '0', '', '/adminSys/gateWayManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), ('26', 'gateClientManager', '客户端管理', '25', '/gateClient', '&#xe631;', null, '0', '', '/adminSys/gateWayManager/gataClientManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), ('28', 'serviceManger', '服务注册', '25', '/service', '&#xe631;', null, '0', '', '/adminSys/gateWayManager/serviceManger', null, '2017-07-03 20:43:01', '1', '¡X', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `mobile_phone` varchar(255) DEFAULT NULL,
  `tel_phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  `upd_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'admin', '$2a$12$qb.WpfxqnHHKYSdIPfmG7uV4Hz8pYa6fuheMvCp1FpO0TkJ1F7aZW', '管理员', '', null, '', null, '', '男', null, '1', '', null, null, null, null, '2017-07-01 00:00:00', '1', 'admin', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null), ('2', 'test', '$2a$12$ZIBIZ/.zBlGql9E4aVWBle8DdDmL8rGSw7sea4.B1bTjoXTm36TMG', '测试账户', '', null, '', null, '', '男', null, '1', '1234', null, null, null, null, '2017-07-08 22:14:53', 'null', 'null', 'null', null, null, null, null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `user_roles`
-- ----------------------------
BEGIN ;
INSERT INTO user_roles(id,role_id,user_id) values(1,1,1);
COMMIT;

-- ----------------------------
--  Table structure for `auth_client`
-- ----------------------------
DROP TABLE IF EXISTS `auth_client`;
CREATE TABLE `auth_client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `locked` char(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  `upd_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `auth_client`
-- ----------------------------
BEGIN;
INSERT INTO `auth_client` VALUES ('1', 'admin-ui', '$2a$12$zmM.eBOEpcp7wUfo7ypBSemy23bcGSEESLlIreg1FhHWV3ADoap/q', 'admin-ui', '0', '', null, '', '', '', '2017-07-07 21:51:32', '1', '管理员', '0:0:0:0:0:0:0:1', '', '', '', '', '', '', '', ''), ('3', 'admin-api-gate', '$2a$12$03C/g.y/QHnDe8P90Oi3cOS1nViIb22EoRUoeK9cWALmv5NJi1sUC', 'admin-api-gate', '0', '', null, null, null, null, '2017-07-06 21:42:17', '1', '管理员', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null), ('4', 'gate-demo-client', '$2a$12$.4fRICu1r4AJbxeVkA0zN.GlrkiV64Cbl.R5bB8rWbVgFVfj8JDqu', 'gate-demo-client', '0', '', '2017-07-09 12:53:09', '1', '管理员', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `auth_client_service`
-- ----------------------------
DROP TABLE IF EXISTS `auth_client_service`;
CREATE TABLE `auth_client_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_id` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `auth_client_service`
-- ----------------------------
BEGIN;
INSERT INTO `auth_client_service` VALUES ('21', '46', '4', null, null, null, null, null, null, null, null, null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `gate_log`
-- ----------------------------
DROP TABLE IF EXISTS `gate_log`;
CREATE TABLE `gate_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu` varchar(255) DEFAULT NULL,
  `opt` varchar(255) DEFAULT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8mb4;