/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : testsystem

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 05/06/2022 14:40:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer_sheet
-- ----------------------------
DROP TABLE IF EXISTS `answer_sheet`;
CREATE TABLE `answer_sheet`  (
  `answer_sheet_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL COMMENT '学生id',
  `test_id` int NULL DEFAULT NULL COMMENT '考试id',
  `upload_time` datetime NULL DEFAULT NULL COMMENT '上传时间',
  `score` int NULL DEFAULT NULL COMMENT '得分（未打分时是-1）',
  PRIMARY KEY (`answer_sheet_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `test_id`(`test_id` ASC) USING BTREE,
  CONSTRAINT `answer_sheet_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `answer_sheet_ibfk_2` FOREIGN KEY (`test_id`) REFERENCES `test` (`test_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of answer_sheet
-- ----------------------------
INSERT INTO `answer_sheet` VALUES (1, 2, 1, '2022-05-28 17:10:46', 90);
INSERT INTO `answer_sheet` VALUES (4, 5, 1, '2022-05-28 17:10:46', -1);
INSERT INTO `answer_sheet` VALUES (5, 2, 2, '2022-06-01 21:40:50', 80);
INSERT INTO `answer_sheet` VALUES (6, 2, 3, '2022-06-02 21:41:54', 100);

-- ----------------------------
-- Table structure for answer_sheet_content_link
-- ----------------------------
DROP TABLE IF EXISTS `answer_sheet_content_link`;
CREATE TABLE `answer_sheet_content_link`  (
  `link_id` int NOT NULL AUTO_INCREMENT,
  `answer_sheet_id` int NULL DEFAULT NULL,
  `answer_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `question_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`link_id`) USING BTREE,
  INDEX `answer_sheet_id`(`answer_sheet_id` ASC) USING BTREE,
  INDEX `question_id`(`question_id` ASC) USING BTREE,
  CONSTRAINT `answer_sheet_content_link_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `answer_sheet_content_link_ibfk_3` FOREIGN KEY (`answer_sheet_id`) REFERENCES `answer_sheet` (`answer_sheet_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of answer_sheet_content_link
-- ----------------------------
INSERT INTO `answer_sheet_content_link` VALUES (1, 1, 'zhangsan', 1);
INSERT INTO `answer_sheet_content_link` VALUES (3, 4, 'A', 1);

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `class_id` int NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `class_mark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '班级备注',
  PRIMARY KEY (`class_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES (1, 'class1', 'class1');
INSERT INTO `class` VALUES (3, 'class2', '222');

-- ----------------------------
-- Table structure for class_user_link
-- ----------------------------
DROP TABLE IF EXISTS `class_user_link`;
CREATE TABLE `class_user_link`  (
  `link_id` int NOT NULL AUTO_INCREMENT,
  `class_id` int NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`link_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `class_id`(`class_id` ASC) USING BTREE,
  CONSTRAINT `class_user_link_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `class_user_link_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of class_user_link
-- ----------------------------
INSERT INTO `class_user_link` VALUES (1, 1, 2);
INSERT INTO `class_user_link` VALUES (2, 1, 5);
INSERT INTO `class_user_link` VALUES (3, 1, 7);
INSERT INTO `class_user_link` VALUES (4, 1, 8);
INSERT INTO `class_user_link` VALUES (5, 3, 1);
INSERT INTO `class_user_link` VALUES (6, 3, 3);
INSERT INTO `class_user_link` VALUES (7, 3, 2);

-- ----------------------------
-- Table structure for option
-- ----------------------------
DROP TABLE IF EXISTS `option`;
CREATE TABLE `option`  (
  `option_id` int NOT NULL AUTO_INCREMENT,
  `question_id` int NULL DEFAULT NULL,
  `option_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`option_id`) USING BTREE,
  INDEX `question_id`(`question_id` ASC) USING BTREE,
  CONSTRAINT `option_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of option
-- ----------------------------
INSERT INTO `option` VALUES (1, 1, 'zhangsan');
INSERT INTO `option` VALUES (2, 1, 'li44444da');
INSERT INTO `option` VALUES (3, 1, 'wangwu');
INSERT INTO `option` VALUES (4, 1, 'sxc');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `question_id` int NOT NULL AUTO_INCREMENT COMMENT '问题id',
  `question_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '问题内容',
  `question_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '问题类型',
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '参考答案（仅客观）',
  `score` int NULL DEFAULT NULL COMMENT '此题分值',
  PRIMARY KEY (`question_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, 'your name?', 'single', 'zhangsan', 100);

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `test_id` int NOT NULL AUTO_INCREMENT,
  `test_create_time` datetime NULL DEFAULT NULL COMMENT '考试创建时间',
  `test_start_time` datetime NULL DEFAULT NULL COMMENT '考试开始时间',
  `test_end_time` datetime NULL DEFAULT NULL COMMENT '考试截止时间',
  `test_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考试状态',
  `creator_id` int NULL DEFAULT NULL COMMENT '创建者id',
  `test_ description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考试说明',
  PRIMARY KEY (`test_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (1, '2022-05-27 17:08:31', '2022-05-28 17:08:36', '2022-05-29 17:08:40', 'end', 1, 'testtest');
INSERT INTO `test` VALUES (2, '2022-06-01 21:39:42', '2022-06-01 21:39:52', '2022-06-02 21:39:56', 'end', 1, 'test0');
INSERT INTO `test` VALUES (3, '2022-06-02 21:41:04', '2022-06-03 21:41:08', '2022-06-02 21:41:11', 'end', 1, 'test00');

-- ----------------------------
-- Table structure for test_question_link
-- ----------------------------
DROP TABLE IF EXISTS `test_question_link`;
CREATE TABLE `test_question_link`  (
  `link_id` int NOT NULL AUTO_INCREMENT,
  `question_id` int NULL DEFAULT NULL,
  `test_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`link_id`) USING BTREE,
  INDEX `test_id`(`test_id` ASC) USING BTREE,
  INDEX `question_id`(`question_id` ASC) USING BTREE,
  CONSTRAINT `test_question_link_ibfk_1` FOREIGN KEY (`test_id`) REFERENCES `test` (`test_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `test_question_link_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of test_question_link
-- ----------------------------
INSERT INTO `test_question_link` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `department` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户所在部门',
  `user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户类型',
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'sxc', 'zucc', 'superAdmin', '123');
INSERT INTO `user` VALUES (2, 'zhangsan', 'zucc.edu', 'student', '123');
INSERT INTO `user` VALUES (3, 'lisi', 'zucc', 'teacher', '123');
INSERT INTO `user` VALUES (4, 'wangwu', 'zucc', 'canceled', '123');
INSERT INTO `user` VALUES (5, 'vds', 'zucc', 'student', '123');
INSERT INTO `user` VALUES (6, 'szy', 'zucc', 'admin', '123');
INSERT INTO `user` VALUES (7, 'asd', 'zucc', 'student', '123');
INSERT INTO `user` VALUES (8, 'dfzg', 'zucc', 'student', '123');
INSERT INTO `user` VALUES (9, 'qwe', 'zucc', 'teacher', '123');
INSERT INTO `user` VALUES (11, 'mio_katamiya', 'dem', 'admin', '123456');

-- ----------------------------
-- Table structure for user_test_link
-- ----------------------------
DROP TABLE IF EXISTS `user_test_link`;
CREATE TABLE `user_test_link`  (
  `link_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考试成员类型',
  `test_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`link_id`) USING BTREE,
  INDEX `test_id`(`test_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `user_test_link_ibfk_1` FOREIGN KEY (`test_id`) REFERENCES `test` (`test_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_test_link_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_test_link
-- ----------------------------
INSERT INTO `user_test_link` VALUES (1, 2, 'student', 1);
INSERT INTO `user_test_link` VALUES (2, 5, 'student', 1);
INSERT INTO `user_test_link` VALUES (3, 7, 'student', 1);
INSERT INTO `user_test_link` VALUES (4, 8, 'student', 1);
INSERT INTO `user_test_link` VALUES (5, 2, 'student', 2);
INSERT INTO `user_test_link` VALUES (6, 2, 'student', 3);
INSERT INTO `user_test_link` VALUES (7, 6, 'admin', 1);
INSERT INTO `user_test_link` VALUES (8, 8, 'student', 1);
INSERT INTO `user_test_link` VALUES (9, 9, 'teacher', 1);

SET FOREIGN_KEY_CHECKS = 1;
