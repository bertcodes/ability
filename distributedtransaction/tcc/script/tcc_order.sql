/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : tcc_order

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2018-12-03 14:03:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `number` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `status` tinyint(4) NOT NULL,
  `product_id` varchar(128) COLLATE utf8mb4_bin NOT NULL,
  `total_amount` decimal(10,0) NOT NULL,
  `count` int(4) NOT NULL,
  `user_id` varchar(128) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', '2018-11-24 22:24:53', '1066336858004258816', '4', '1', '1', '1', '10000');
INSERT INTO `order` VALUES ('2', '2018-11-24 22:27:44', '1066337574152306688', '3', '1', '1', '1', '10000');
INSERT INTO `order` VALUES ('3', '2018-11-24 22:28:20', '1066337723666661376', '3', '1', '1', '1', '10000');
INSERT INTO `order` VALUES ('4', '2018-11-24 23:46:59', '1066357517828751360', '3', '1', '1', '2', '10000');
INSERT INTO `order` VALUES ('5', '2018-11-26 14:40:12', '1066944693180301312', '3', '1', '1', '1', '10000');
INSERT INTO `order` VALUES ('6', '2018-11-26 14:53:49', '1066948120404819968', '3', '1', '1', '1', '10000');
INSERT INTO `order` VALUES ('7', '2018-11-26 17:39:52', '1066989907886473216', '3', '1', '1', '1', '10000');
INSERT INTO `order` VALUES ('8', '2018-11-26 17:43:07', '1066990723091398656', '3', '1', '1', '1', '10000');
