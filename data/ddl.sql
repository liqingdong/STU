/*
Navicat MySQL Data Transfer

Source Server         : 172.16.21.201_3306
Source Server Version : 50636
Source Host           : 172.16.21.201:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-08-22 15:34:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for T_SYS_MENU
-- ----------------------------
DROP TABLE IF EXISTS `T_SYS_MENU`;
CREATE TABLE `T_SYS_MENU` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `MENU_NAME` varchar(50) NOT NULL COMMENT '菜单名称',
  `MENU_CODE` varchar(50) NOT NULL COMMENT '菜单编码',
  `MENU_ICON` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `MENU_DESC` varchar(256) DEFAULT NULL COMMENT '菜单描述',
  `IS_LEAF` varchar(1) NOT NULL COMMENT '是否叶子节点0:非叶子节点 1:叶子节点',
  `MENU_LEVEL` int(2) NOT NULL COMMENT '菜单级别',
  `PARENT_MENU_ID` bigint(20) DEFAULT NULL COMMENT '父菜单id',
  `SORT_NO` int(4) NOT NULL COMMENT '菜单排序',
  `URL` varchar(256) DEFAULT NULL COMMENT '菜单访问的url',
  `CAN_DELETE` varchar(1) NOT NULL COMMENT '菜单是否可删除 0:不可删除 1:可删除',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATE_BY` varchar(30) DEFAULT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(30) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `MENU_CODE` (`MENU_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='系统菜单';

-- ----------------------------
-- Table structure for T_SYS_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `T_SYS_ROLE`;
CREATE TABLE `T_SYS_ROLE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ROLE_NAME` varchar(50) NOT NULL COMMENT '角色名称',
  `ROLE_CODE` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色代码',
  `ROLE_DESC` varchar(256) DEFAULT NULL COMMENT '角色描述',
  `CAN_DELETE` varchar(1) NOT NULL COMMENT '角色是否可删除 0:不可删除 1:可删除',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATE_BY` varchar(30) DEFAULT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(30) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ROLE_CODE` (`ROLE_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='系统用户组';

-- ----------------------------
-- Table structure for T_SYS_ROLE_MENU
-- ----------------------------
DROP TABLE IF EXISTS `T_SYS_ROLE_MENU`;
CREATE TABLE `T_SYS_ROLE_MENU` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色id',
  `MENU_ID` bigint(20) NOT NULL COMMENT '菜单id',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATE_BY` varchar(30) DEFAULT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(30) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8 COMMENT='角色菜单关联关系表';

-- ----------------------------
-- Table structure for T_SYS_USER
-- ----------------------------
DROP TABLE IF EXISTS `T_SYS_USER`;
CREATE TABLE `T_SYS_USER` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USERNAME` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  `PASSWORD_LENGTH` int(2) DEFAULT NULL COMMENT '密码长度',
  `NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '用户姓名',
  `SEX` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '用户性别1-男2-女',
  `TEL` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话',
  `EMAIL` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `CAN_DELETE` char(1) COLLATE utf8_bin NOT NULL COMMENT '用户是否可删除 0:不可删除 1:可删除',
  `STATUS` char(1) COLLATE utf8_bin NOT NULL COMMENT '用户状态0-无效1-有效',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATE_BY` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统用户';

-- ----------------------------
-- Table structure for T_SYS_USER_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `T_SYS_USER_ROLE`;
CREATE TABLE `T_SYS_USER_ROLE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_ID` bigint(20) NOT NULL COMMENT '用户id',
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色id',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATE_BY` varchar(30) DEFAULT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(30) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 COMMENT='用户与角色的关联关系表';
SET FOREIGN_KEY_CHECKS=1;
