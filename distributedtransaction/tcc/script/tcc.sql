/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : tcc

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2018-12-03 14:03:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hmily_account_service
-- ----------------------------
DROP TABLE IF EXISTS `hmily_account_service`;
CREATE TABLE `hmily_account_service` (
  `trans_id` varchar(64) NOT NULL,
  `target_class` varchar(256) DEFAULT NULL,
  `target_method` varchar(128) DEFAULT NULL,
  `confirm_method` varchar(128) DEFAULT NULL,
  `cancel_method` varchar(128) DEFAULT NULL,
  `retried_count` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_time` datetime NOT NULL,
  `version` tinyint(4) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `invocation` longblob,
  `role` tinyint(4) NOT NULL,
  `pattern` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`trans_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hmily_account_service
-- ----------------------------

-- ----------------------------
-- Table structure for hmily_inventory_service
-- ----------------------------
DROP TABLE IF EXISTS `hmily_inventory_service`;
CREATE TABLE `hmily_inventory_service` (
  `trans_id` varchar(64) NOT NULL,
  `target_class` varchar(256) DEFAULT NULL,
  `target_method` varchar(128) DEFAULT NULL,
  `confirm_method` varchar(128) DEFAULT NULL,
  `cancel_method` varchar(128) DEFAULT NULL,
  `retried_count` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_time` datetime NOT NULL,
  `version` tinyint(4) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `invocation` longblob,
  `role` tinyint(4) NOT NULL,
  `pattern` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`trans_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hmily_inventory_service
-- ----------------------------

-- ----------------------------
-- Table structure for hmily_order_service
-- ----------------------------
DROP TABLE IF EXISTS `hmily_order_service`;
CREATE TABLE `hmily_order_service` (
  `trans_id` varchar(64) NOT NULL,
  `target_class` varchar(256) DEFAULT NULL,
  `target_method` varchar(128) DEFAULT NULL,
  `confirm_method` varchar(128) DEFAULT NULL,
  `cancel_method` varchar(128) DEFAULT NULL,
  `retried_count` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_time` datetime NOT NULL,
  `version` tinyint(4) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `invocation` longblob,
  `role` tinyint(4) NOT NULL,
  `pattern` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`trans_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hmily_order_service
-- ----------------------------
