/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50639
Source Host           : localhost:3306
Source Database       : dinner

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-08-02 16:05:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cuisine
-- ----------------------------
DROP TABLE IF EXISTS `cuisine`;
CREATE TABLE `cuisine` (
  `c_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `c_name` varchar(60) NOT NULL,
  `c_cost` double DEFAULT NULL,
  `c_price` double DEFAULT NULL,
  `c_img` varchar(100) NOT NULL DEFAULT '',
  `c_info` varchar(90) DEFAULT NULL,
  `m_id` int(10) unsigned NOT NULL,
  `c_exist` enum('0','1') DEFAULT '0',
  PRIMARY KEY (`c_id`),
  UNIQUE KEY `c_name` (`c_name`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cuisine
-- ----------------------------
INSERT INTO `cuisine` VALUES ('1', '红烧肉', '12', '12', '1.jpg', 'info', '1', '1');
INSERT INTO `cuisine` VALUES ('50', '番茄炒蛋', '20', '30', 'f86ef74f.jpg', '炒蛋', '4', '1');
INSERT INTO `cuisine` VALUES ('51', '红烧茄子', '20', '30', 'f86f9873.jpg', '茄子', '4', '0');

-- ----------------------------
-- Table structure for desk
-- ----------------------------
DROP TABLE IF EXISTS `desk`;
CREATE TABLE `desk` (
  `d_id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `d_name` varchar(60) DEFAULT NULL,
  `d_status` int(8) NOT NULL DEFAULT '0',
  `d_exist` enum('0','1') DEFAULT NULL,
  PRIMARY KEY (`d_id`),
  KEY `d_id` (`d_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of desk
-- ----------------------------
INSERT INTO `desk` VALUES ('1', '餐1', '0', '0');
INSERT INTO `desk` VALUES ('2', '餐2', '0', '1');
INSERT INTO `desk` VALUES ('3', '餐3', '0', '1');
INSERT INTO `desk` VALUES ('4', '餐4', '0', '1');
INSERT INTO `desk` VALUES ('5', '餐5', '0', '1');
INSERT INTO `desk` VALUES ('6', '擦1', '0', '1');
INSERT INTO `desk` VALUES ('7', '餐6', '0', '1');
INSERT INTO `desk` VALUES ('8', '餐7', '0', '1');
INSERT INTO `desk` VALUES ('9', '餐8', '0', '1');
INSERT INTO `desk` VALUES ('10', '餐9', '0', '1');
INSERT INTO `desk` VALUES ('11', '餐桌10', '0', '1');
INSERT INTO `desk` VALUES ('12', '餐11', '0', '1');
INSERT INTO `desk` VALUES ('13', '餐121', '0', '1');
INSERT INTO `desk` VALUES ('14', '11号桌', '0', '1');

-- ----------------------------
-- Table structure for menutype
-- ----------------------------
DROP TABLE IF EXISTS `menutype`;
CREATE TABLE `menutype` (
  `m_id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `m_type` varchar(60) NOT NULL,
  `m_exist` enum('0','1') DEFAULT '0',
  PRIMARY KEY (`m_id`),
  UNIQUE KEY `m_type` (`m_type`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menutype
-- ----------------------------
INSERT INTO `menutype` VALUES ('1', '鲁菜', '0');
INSERT INTO `menutype` VALUES ('4', '热菜', '1');
INSERT INTO `menutype` VALUES ('12', '凉菜', '1');
INSERT INTO `menutype` VALUES ('17', '西餐', '0');
INSERT INTO `menutype` VALUES ('18', '粤菜', '0');
INSERT INTO `menutype` VALUES ('19', '甜点', '0');
INSERT INTO `menutype` VALUES ('20', '川菜', '0');

-- ----------------------------
-- Table structure for order_form
-- ----------------------------
DROP TABLE IF EXISTS `order_form`;
CREATE TABLE `order_form` (
  `f_id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `d_id` int(8) unsigned DEFAULT NULL,
  `f_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `f_date2` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `f_state` enum('0','1','2') NOT NULL DEFAULT '0',
  `a_id` int(8) unsigned DEFAULT NULL,
  `f_income` double DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_form
-- ----------------------------
INSERT INTO `order_form` VALUES ('25', '14', '2018-08-02 15:00:58', '2018-08-02 15:40:10', '1', '1', '3000');
INSERT INTO `order_form` VALUES ('27', '14', '2018-08-02 15:04:39', '2018-08-02 15:40:10', '1', '1', '3000');
INSERT INTO `order_form` VALUES ('28', '14', '2018-08-02 15:11:07', '2018-08-02 15:40:10', '1', '1', '3000');
INSERT INTO `order_form` VALUES ('39', '14', '2018-08-02 15:39:28', '2018-08-02 15:40:10', '1', '1', '3000');
INSERT INTO `order_form` VALUES ('40', '14', '2018-08-02 15:40:02', '2018-08-02 15:40:10', '1', '1', '3000');
INSERT INTO `order_form` VALUES ('41', '14', '2018-08-02 15:41:57', '2018-08-02 15:46:00', '1', '1', '5000');

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `f_id` int(8) unsigned NOT NULL,
  `c_id` int(8) unsigned NOT NULL,
  `i_num` int(6) unsigned NOT NULL,
  PRIMARY KEY (`f_id`,`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES ('25', '1', '2');
INSERT INTO `order_item` VALUES ('25', '50', '1');
INSERT INTO `order_item` VALUES ('28', '1', '6');
INSERT INTO `order_item` VALUES ('28', '50', '11');
INSERT INTO `order_item` VALUES ('39', '1', '1');
INSERT INTO `order_item` VALUES ('39', '50', '2');
INSERT INTO `order_item` VALUES ('40', '1', '1');
INSERT INTO `order_item` VALUES ('40', '50', '1');
INSERT INTO `order_item` VALUES ('41', '1', '1');
INSERT INTO `order_item` VALUES ('41', '50', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL DEFAULT '',
  `password` varchar(30) NOT NULL DEFAULT '',
  `type` int(6) unsigned NOT NULL DEFAULT '0',
  `exist` int(6) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `name_pas` (`username`,`password`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', '0', '1');
