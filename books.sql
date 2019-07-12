/*
 Navicat Premium Data Transfer

 Source Server         : aliyunRDS
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : rm-2ze9c6q06e8q31530ho.mysql.rds.aliyuncs.com:3306
 Source Schema         : books

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 12/07/2019 15:50:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '管理员用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '管理员密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES (1, 'admin', 'admin');

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sjid` int(11) NULL DEFAULT NULL,
  `btid` int(11) NULL DEFAULT NULL,
  `states` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES (1, '少儿读物', '/files/2019071215015565.png', 1, 1, '1');
INSERT INTO `books` VALUES (2, '杂志', '/files/201907121512207.png', 2, 2, '0');
INSERT INTO `books` VALUES (3, '期刊', '/files/2019071215123649.jpg', 1, 3, '1');
INSERT INTO `books` VALUES (4, '文学', '/files/2019071215130923.jpg', 1, 4, '1');
INSERT INTO `books` VALUES (7, '1', '/files/2019071215143238.png', 1, 1, '1');

-- ----------------------------
-- Table structure for booktype
-- ----------------------------
DROP TABLE IF EXISTS `booktype`;
CREATE TABLE `booktype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `names` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `kj` int(255) NULL DEFAULT NULL COMMENT '可解天数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of booktype
-- ----------------------------
INSERT INTO `booktype` VALUES (1, '少儿读物', 15);
INSERT INTO `booktype` VALUES (2, '杂志', 3);
INSERT INTO `booktype` VALUES (3, '期刊', 7);
INSERT INTO `booktype` VALUES (4, '文学', 30);

-- ----------------------------
-- Table structure for br
-- ----------------------------
DROP TABLE IF EXISTS `br`;
CREATE TABLE `br`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `myid` int(11) NULL DEFAULT NULL,
  `bid` int(11) NULL DEFAULT NULL,
  `xjcs` int(11) NULL DEFAULT NULL,
  `states` int(11) NULL DEFAULT NULL,
  `btimes` datetime(0) NULL DEFAULT NULL,
  `etimes` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of br
-- ----------------------------
INSERT INTO `br` VALUES (2, 1, 3, 0, 0, '2019-07-12 15:37:16', '2019-07-19 15:37:05');
INSERT INTO `br` VALUES (3, 1, 2, 0, 1, '2019-07-12 15:39:25', '2019-07-15 15:39:19');

-- ----------------------------
-- Table structure for jb
-- ----------------------------
DROP TABLE IF EXISTS `jb`;
CREATE TABLE `jb`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bzf` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '办证费',
  `yxq` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '有效期',
  `fkone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '罚款',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基本信息设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jb
-- ----------------------------
INSERT INTO `jb` VALUES (1, '100', '365', '1');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `menuPath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '面板名称 需与代码对应',
  `type` int(11) NULL DEFAULT NULL COMMENT '菜单级别 0-管理员 1用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '图书管理', 1, '/mypages/bookManager.jsp', 0);
INSERT INTO `menu` VALUES (2, '借阅管理', 2, 'FriendPane', 0);
INSERT INTO `menu` VALUES (3, '读者管理', 4, 'ActivePane', 0);
INSERT INTO `menu` VALUES (4, '报表管理', 5, 'CooperationPane', 0);
INSERT INTO `menu` VALUES (5, '系统管理', 6, 'GiftPane', 0);
INSERT INTO `menu` VALUES (6, '今日到期', 3, NULL, 0);

-- ----------------------------
-- Table structure for sj
-- ----------------------------
DROP TABLE IF EXISTS `sj`;
CREATE TABLE `sj`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `names` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '书架表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sj
-- ----------------------------
INSERT INTO `sj` VALUES (1, '1');
INSERT INTO `sj` VALUES (2, '2');
INSERT INTO `sj` VALUES (4, '书架三');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `utid` int(11) NULL DEFAULT NULL COMMENT '类型id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'u1', '1', '123', '456哈哈哈哈', 1);
INSERT INTO `users` VALUES (2, 'u2', '1', '11', '11', 2);
INSERT INTO `users` VALUES (3, 'u3', '1', '11', '11', 1);
INSERT INTO `users` VALUES (4, '群众', '1', '1', '1', 3);

-- ----------------------------
-- Table structure for usertype
-- ----------------------------
DROP TABLE IF EXISTS `usertype`;
CREATE TABLE `usertype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `names` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `kj` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of usertype
-- ----------------------------
INSERT INTO `usertype` VALUES (1, '儿童', 7);
INSERT INTO `usertype` VALUES (2, '学生', 15);
INSERT INTO `usertype` VALUES (3, '群众', 30);

SET FOREIGN_KEY_CHECKS = 1;
