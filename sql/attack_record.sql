/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.157.128
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : 192.168.157.128:3306
 Source Schema         : vueblog

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 26/04/2021 22:29:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attack_record
-- ----------------------------
DROP TABLE IF EXISTS `attack_record`;
CREATE TABLE `attack_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '攻击类型',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '基本内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '攻击记录' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
