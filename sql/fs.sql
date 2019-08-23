/*
Navicat MySQL Data Transfer

Source Server         : youzhi
Source Server Version : 50636
Source Host           : localhost:3306
Source Database       : fs

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2018-09-10 22:49:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for draw
-- ----------------------------
DROP TABLE IF EXISTS `draw`;
CREATE TABLE `draw` (
  `id` varchar(255) DEFAULT NULL,
  `drawDate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `airPhoto` varchar(255) DEFAULT NULL,
  `drawJSON` varchar(255) DEFAULT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of draw
-- ----------------------------
INSERT INTO `draw` VALUES ('64f36fc2-250f-4992-9e46-85bd1ff66422', '2018-09-10 21:18:13', 'D:\\workspace\\PowerRunWeb\\南亭\\编号4图片.jpg', 'D:\\workspace\\PowerRunWeb\\南亭\\2018_08_03_a_zt.json');

-- ----------------------------
-- Table structure for locate
-- ----------------------------
DROP TABLE IF EXISTS `locate`;
CREATE TABLE `locate` (
  `id` varchar(255) DEFAULT NULL,
  `locateDate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `jdkJAR` varchar(255) DEFAULT NULL,
  `sdkJAR` varchar(255) DEFAULT NULL,
  `northPhoto` varchar(255) DEFAULT NULL,
  `locateJSON` varchar(255) DEFAULT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of locate
-- ----------------------------
INSERT INTO `locate` VALUES ('4a69dd4b-e0fe-419f-8a6f-2b0cc4bb60f5', '2018-09-10 21:18:14', 'D:\\workspace\\PowerRunWeb\\南亭\\2018910.jar', null, 'D:\\workspace\\PowerRunWeb\\南亭\\编号4图片-正北图.jpg', 'D:\\workspace\\PowerRunWeb\\南亭\\编号4图片.json');

-- ----------------------------
-- Table structure for model
-- ----------------------------
DROP TABLE IF EXISTS `model`;
CREATE TABLE `model` (
  `id` varchar(255) DEFAULT NULL,
  `modelDate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modFile` varchar(255) DEFAULT NULL,
  `modelJSON` varchar(255) DEFAULT NULL,
  `northPhoto` varchar(255) DEFAULT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of model
-- ----------------------------
INSERT INTO `model` VALUES ('8a7f53e4-9e15-4926-8470-53aca912fd48', '2018-09-10 21:21:02', 'D:\\workspace\\PowerRunWeb\\南亭\\mod-2018-08-26-1.mod', 'D:\\workspace\\PowerRunWeb\\南亭\\恒信花园A5公用配电站.json', '');

-- ----------------------------
-- Table structure for region
-- ----------------------------
DROP TABLE IF EXISTS `region`;
CREATE TABLE `region` (
  `id` varchar(255) NOT NULL,
  `regionName` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `finishDate` datetime DEFAULT NULL,
  `marketExcel` varchar(255) DEFAULT NULL,
  `createUserId` varchar(255) DEFAULT NULL,
  `drawUserId` varchar(255) DEFAULT NULL,
  `locateUserId` varchar(255) DEFAULT NULL,
  `modelUserId` varchar(255) DEFAULT NULL,
  `outputUserId` varchar(255) DEFAULT NULL,
  `finishUserId` varchar(255) DEFAULT NULL,
  `drawId` varchar(255) DEFAULT NULL,
  `locateId` varchar(255) DEFAULT NULL,
  `modelId` varchar(255) DEFAULT NULL,
  `reportId` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `drawTeam` varchar(255) DEFAULT NULL,
  `modelTeam` varchar(255) DEFAULT NULL,
  `outputTeam` varchar(255) DEFAULT NULL,
  `drawRetreat` varchar(255) DEFAULT NULL,
  `modelRetreat` varchar(255) DEFAULT NULL,
  `outputRetreat` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `region_1` (`createUserId`),
  KEY `region_10` (`reportId`),
  KEY `region_2` (`drawUserId`),
  KEY `region_3` (`locateUserId`),
  KEY `region_4` (`modelUserId`),
  KEY `region_5` (`outputUserId`),
  KEY `region_6` (`finishUserId`),
  KEY `region_8` (`locateId`),
  KEY `region_9` (`modelId`),
  KEY `region_7` (`drawId`),
  CONSTRAINT `region_1` FOREIGN KEY (`createUserId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `region_10` FOREIGN KEY (`reportId`) REFERENCES `report` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `region_2` FOREIGN KEY (`drawUserId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `region_3` FOREIGN KEY (`locateUserId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `region_4` FOREIGN KEY (`modelUserId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `region_5` FOREIGN KEY (`outputUserId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `region_6` FOREIGN KEY (`finishUserId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `region_7` FOREIGN KEY (`drawId`) REFERENCES `draw` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `region_8` FOREIGN KEY (`locateId`) REFERENCES `locate` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `region_9` FOREIGN KEY (`modelId`) REFERENCES `model` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of region
-- ----------------------------
INSERT INTO `region` VALUES ('809e9ea9-dc63-4407-b875-645a90c62f6f', '南亭', '2018-09-10 21:23:42', '2018-09-10 21:23:43', 'D:\\workspace\\PowerRunWeb\\南亭\\东区供电局所有用户.xlsx', 'b5c7dbdc-0a13-4a58-9240-cbb3cf6a38c3', 'c2f72305-097b-467a-977c-48962a72f97f', null, 'c2f72305-097b-467a-977c-48962a72f97f', 'c2f72305-097b-467a-977c-48962a72f97f', 'c2f72305-097b-467a-977c-48962a72f97f', '64f36fc2-250f-4992-9e46-85bd1ff66422', '4a69dd4b-e0fe-419f-8a6f-2b0cc4bb60f5', '8a7f53e4-9e15-4926-8470-53aca912fd48', '39c4ae61-a976-4c25-a53d-c91b3fe915e9', '5', null, null, null, 'b5c7dbdc-0a13-4a58-9240-cbb3cf6a38c3', 'b5c7dbdc-0a13-4a58-9240-cbb3cf6a38c3', 'b5c7dbdc-0a13-4a58-9240-cbb3cf6a38c3');

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` varchar(255) DEFAULT NULL,
  `outputDate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `reportExcel` varchar(255) DEFAULT NULL,
  `reportPicWithBG` varchar(255) DEFAULT NULL,
  `reportPicWithoutBG` varchar(255) DEFAULT NULL,
  `reportCover` varchar(255) DEFAULT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES ('39c4ae61-a976-4c25-a53d-c91b3fe915e9', '2018-09-10 21:23:23', 'D:\\workspace\\PowerRunWeb\\南亭\\恒信花园A4公用配电站.xls', 'D:\\workspace\\PowerRunWeb\\南亭\\恒信花园A4公用配电站-带底图.png', 'D:\\workspace\\PowerRunWeb\\南亭\\恒信花园A4公用配电站.png', 'D:\\workspace\\PowerRunWeb\\南亭\\发射点犯得上.docx');

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` varchar(255) DEFAULT NULL,
  `teamName` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `captain` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team
-- ----------------------------

-- ----------------------------
-- Table structure for team_user
-- ----------------------------
DROP TABLE IF EXISTS `team_user`;
CREATE TABLE `team_user` (
  `id` varchar(255) DEFAULT NULL,
  `uesrId` varchar(255) DEFAULT NULL,
  `teamId` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team_user
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `pw` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('b5c7dbdc-0a13-4a58-9240-cbb3cf6a38c3', '陈危机 ', '123456', 'chen815004130');
INSERT INTO `user` VALUES ('c2f72305-097b-467a-977c-48962a72f97f', 'admin', 'admin', 'admin');
