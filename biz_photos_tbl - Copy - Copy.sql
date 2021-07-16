/*
Navicat MySQL Data Transfer

Source Server         : LOCAL
Source Server Version : 50730
Source Host           : localhost:3306
Source Database       : vehicle_db

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2020-08-12 03:47:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `biz_photos_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `biz_photos_tbl`;
CREATE TABLE `biz_photos_tbl` (
  `bizid` int(11) NOT NULL AUTO_INCREMENT,
  `photo` longblob,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`bizid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;


-- Table structure for `biz_service_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `biz_service_tbl`;
CREATE TABLE `biz_service_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `doe` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `photo` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
