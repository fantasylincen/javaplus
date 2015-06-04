/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : mobile10005

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2014-12-10 17:18:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `recharge_record`
-- ----------------------------
DROP TABLE IF EXISTS `recharge_record`;
CREATE TABLE `recharge_record` (
  `ids` bigint(20) NOT NULL COMMENT '充值流水号',
  `uname` varchar(32) NOT NULL,
  `amount` int(11) NOT NULL COMMENT '充值的人民币数目',
  `createTime` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ids`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recharge_record
-- ----------------------------
INSERT INTO `recharge_record` VALUES ('100330', '100232', '100', '1417614110');
INSERT INTO `recharge_record` VALUES ('100332', '100226', '300', '1417614811');
INSERT INTO `recharge_record` VALUES ('100334', '100233', '100', '1417615155');
INSERT INTO `recharge_record` VALUES ('100336', '100231', '100', '1417615231');
INSERT INTO `recharge_record` VALUES ('100338', '100231', '300', '1417615336');
INSERT INTO `recharge_record` VALUES ('100340', '100226', '100', '1417615403');
INSERT INTO `recharge_record` VALUES ('100342', '100233', '100', '1417615608');
INSERT INTO `recharge_record` VALUES ('100344', '100231', '100', '1417615900');
INSERT INTO `recharge_record` VALUES ('100346', '100231', '300', '1417616269');
INSERT INTO `recharge_record` VALUES ('100348', '100234', '300', '1417616322');
INSERT INTO `recharge_record` VALUES ('100350', '100238', '100', '1417660503');
INSERT INTO `recharge_record` VALUES ('100352', '100238', '300', '1417660558');
INSERT INTO `recharge_record` VALUES ('100354', '100238', '4000', '1417660611');
INSERT INTO `recharge_record` VALUES ('100356', '100248', '100', '1417666965');
INSERT INTO `recharge_record` VALUES ('100358', '100239', '100', '1417667272');
INSERT INTO `recharge_record` VALUES ('100360', '100235', '100', '1417671722');
INSERT INTO `recharge_record` VALUES ('100362', '100266', '100', '1417672051');
INSERT INTO `recharge_record` VALUES ('100364', '100231', '6000', '1417675013');
INSERT INTO `recharge_record` VALUES ('100366', '100231', '100', '1417675208');
INSERT INTO `recharge_record` VALUES ('100369', '100231', '6000', '1417679019');
INSERT INTO `recharge_record` VALUES ('100371', '100281', '600', '1417679420');
INSERT INTO `recharge_record` VALUES ('100373', '100229', '6000', '1417679754');
INSERT INTO `recharge_record` VALUES ('100375', '100305', '6000', '1417681801');
INSERT INTO `recharge_record` VALUES ('100377', '100305', '1000', '1417682047');
INSERT INTO `recharge_record` VALUES ('100379', '100342', '100', '1417683278');
INSERT INTO `recharge_record` VALUES ('100381', '100328', '100', '1417684540');
INSERT INTO `recharge_record` VALUES ('100383', '100321', '1000', '1417687377');
INSERT INTO `recharge_record` VALUES ('100385', '100349', '600', '1417687794');
INSERT INTO `recharge_record` VALUES ('100387', '100231', '6000', '1417689810');
INSERT INTO `recharge_record` VALUES ('100389', '100379', '100', '1417691004');
INSERT INTO `recharge_record` VALUES ('100391', '100425', '100', '1417699220');
INSERT INTO `recharge_record` VALUES ('100393', '100409', '300', '1417700142');
INSERT INTO `recharge_record` VALUES ('100395', '100342', '300', '1417700522');
INSERT INTO `recharge_record` VALUES ('100397', '100360', '100', '1417703314');
INSERT INTO `recharge_record` VALUES ('100399', '100393', '100', '1417707941');
INSERT INTO `recharge_record` VALUES ('100401', '100348', '6000', '1417711482');
INSERT INTO `recharge_record` VALUES ('100403', '100356', '100', '1417712673');
INSERT INTO `recharge_record` VALUES ('100405', '100348', '1000', '1417713932');
INSERT INTO `recharge_record` VALUES ('100407', '100252', '100', '1417714071');
INSERT INTO `recharge_record` VALUES ('100409', '100348', '2000', '1417717591');
INSERT INTO `recharge_record` VALUES ('100411', '100348', '1000', '1417717608');
INSERT INTO `recharge_record` VALUES ('100413', '100334', '300', '1417729500');
INSERT INTO `recharge_record` VALUES ('100415', '100342', '100', '1417733947');
INSERT INTO `recharge_record` VALUES ('100417', '100305', '4000', '1417743373');
INSERT INTO `recharge_record` VALUES ('100419', '100305', '4000', '1417746343');
INSERT INTO `recharge_record` VALUES ('100421', '100328', '100', '1417754383');
INSERT INTO `recharge_record` VALUES ('100423', '100328', '300', '1417754842');
INSERT INTO `recharge_record` VALUES ('100425', '100328', '600', '1417755975');
INSERT INTO `recharge_record` VALUES ('100427', '100328', '100', '1417756383');
INSERT INTO `recharge_record` VALUES ('100429', '100289', '2000', '1417756757');
INSERT INTO `recharge_record` VALUES ('100431', '100328', '300', '1417757380');
INSERT INTO `recharge_record` VALUES ('100433', '100273', '100', '1417759458');
INSERT INTO `recharge_record` VALUES ('100435', '100273', '300', '1417759503');
INSERT INTO `recharge_record` VALUES ('100437', '100273', '600', '1417760491');
INSERT INTO `recharge_record` VALUES ('100439', '100273', '300', '1417764382');
INSERT INTO `recharge_record` VALUES ('100441', '100543', '100', '1417772344');
INSERT INTO `recharge_record` VALUES ('100443', '100557', '600', '1417781634');
INSERT INTO `recharge_record` VALUES ('100445', '100229', '6000', '1417782907');
INSERT INTO `recharge_record` VALUES ('100448', '100525', '6000', '1417790787');
INSERT INTO `recharge_record` VALUES ('100450', '100620', '100', '1417791890');
INSERT INTO `recharge_record` VALUES ('100452', '100305', '100', '1417800801');
INSERT INTO `recharge_record` VALUES ('100454', '100620', '600', '1417810050');
INSERT INTO `recharge_record` VALUES ('100456', '100620', '4000', '1417811924');
INSERT INTO `recharge_record` VALUES ('100458', '100692', '300', '1417812080');
INSERT INTO `recharge_record` VALUES ('100460', '100700', '100', '1417825233');
INSERT INTO `recharge_record` VALUES ('100462', '100681', '600', '1417828271');
INSERT INTO `recharge_record` VALUES ('100464', '100477', '300', '1417829394');
INSERT INTO `recharge_record` VALUES ('100466', '100607', '300', '1417829753');
INSERT INTO `recharge_record` VALUES ('100468', '100730', '100', '1417832581');
INSERT INTO `recharge_record` VALUES ('100470', '100823', '100', '1417861586');
INSERT INTO `recharge_record` VALUES ('100472', '100621', '100', '1417864357');
INSERT INTO `recharge_record` VALUES ('100474', '100863', '2000', '1417865763');
INSERT INTO `recharge_record` VALUES ('100476', '100908', '100', '1417870089');
INSERT INTO `recharge_record` VALUES ('100478', '100914', '100', '1417871897');
INSERT INTO `recharge_record` VALUES ('100480', '100677', '100', '1417871911');
INSERT INTO `recharge_record` VALUES ('100482', '100697', '300', '1417875886');
INSERT INTO `recharge_record` VALUES ('100484', '101005', '2000', '1417880621');
INSERT INTO `recharge_record` VALUES ('100486', '101007', '600', '1417881085');
INSERT INTO `recharge_record` VALUES ('100488', '100985', '100', '1417881205');
INSERT INTO `recharge_record` VALUES ('100490', '100681', '300', '1417883999');
INSERT INTO `recharge_record` VALUES ('100492', '101021', '300', '1417884027');
INSERT INTO `recharge_record` VALUES ('100494', '101016', '6000', '1417884034');
INSERT INTO `recharge_record` VALUES ('100496', '100488', '600', '1417888688');
INSERT INTO `recharge_record` VALUES ('100498', '100305', '100', '1417894043');
INSERT INTO `recharge_record` VALUES ('100500', '100806', '1000', '1417894235');
INSERT INTO `recharge_record` VALUES ('100502', '101074', '600', '1417899638');
INSERT INTO `recharge_record` VALUES ('100504', '100920', '300', '1417907584');
INSERT INTO `recharge_record` VALUES ('100506', '100829', '100', '1417908245');
INSERT INTO `recharge_record` VALUES ('100508', '101083', '1000', '1417910530');
INSERT INTO `recharge_record` VALUES ('100510', '100620', '2000', '1417915739');
INSERT INTO `recharge_record` VALUES ('100512', '100305', '4000', '1417916506');
INSERT INTO `recharge_record` VALUES ('100514', '100305', '600', '1417916538');
INSERT INTO `recharge_record` VALUES ('100516', '100305', '300', '1417916553');
INSERT INTO `recharge_record` VALUES ('100518', '101128', '100', '1417921003');
INSERT INTO `recharge_record` VALUES ('100520', '101159', '100', '1417925013');
INSERT INTO `recharge_record` VALUES ('100522', '100677', '100', '1417925453');
INSERT INTO `recharge_record` VALUES ('100524', '100863', '2000', '1417928224');
INSERT INTO `recharge_record` VALUES ('100526', '101156', '100', '1417929238');
INSERT INTO `recharge_record` VALUES ('100528', '101156', '300', '1417929669');
INSERT INTO `recharge_record` VALUES ('100530', '101207', '100', '1417934008');
INSERT INTO `recharge_record` VALUES ('100536', '101156', '100', '1417948482');
INSERT INTO `recharge_record` VALUES ('100538', '101231', '1000', '1417949035');
INSERT INTO `recharge_record` VALUES ('100540', '101005', '1000', '1417953807');
INSERT INTO `recharge_record` VALUES ('100542', '101185', '6000', '1417954303');
INSERT INTO `recharge_record` VALUES ('100544', '100525', '1000', '1417956852');
INSERT INTO `recharge_record` VALUES ('100547', '101156', '300', '1417956922');
INSERT INTO `recharge_record` VALUES ('100549', '101156', '4000', '1417959206');
INSERT INTO `recharge_record` VALUES ('100551', '101156', '1000', '1417963192');
INSERT INTO `recharge_record` VALUES ('100553', '101156', '2000', '1417963215');
INSERT INTO `recharge_record` VALUES ('100555', '101314', '1000', '1417965760');
INSERT INTO `recharge_record` VALUES ('100557', '101156', '600', '1417965936');
INSERT INTO `recharge_record` VALUES ('100559', '101314', '100', '1417966026');
INSERT INTO `recharge_record` VALUES ('100561', '101316', '100', '1417966223');
INSERT INTO `recharge_record` VALUES ('100563', '101016', '4000', '1417967837');
INSERT INTO `recharge_record` VALUES ('100565', '100305', '100', '1417969014');
INSERT INTO `recharge_record` VALUES ('100567', '100501', '100', '1417970677');
INSERT INTO `recharge_record` VALUES ('100569', '101381', '100', '1417986504');
INSERT INTO `recharge_record` VALUES ('100572', '100370', '1000', '1417994705');
INSERT INTO `recharge_record` VALUES ('100574', '100557', '1000', '1417994823');
INSERT INTO `recharge_record` VALUES ('100576', '101202', '300', '1417998320');
INSERT INTO `recharge_record` VALUES ('100578', '100235', '100', '1418005846');
INSERT INTO `recharge_record` VALUES ('100580', '100229', '100', '1418006145');
INSERT INTO `recharge_record` VALUES ('100582', '100235', '6000', '1418006285');
INSERT INTO `recharge_record` VALUES ('100584', '100229', '100', '1418006613');
INSERT INTO `recharge_record` VALUES ('100586', '100235', '1000', '1418006616');
INSERT INTO `recharge_record` VALUES ('100588', '100229', '100', '1418006726');
INSERT INTO `recharge_record` VALUES ('100590', '100220', '6000', '1418007187');
INSERT INTO `recharge_record` VALUES ('100592', '100220', '1000', '1418007531');
INSERT INTO `recharge_record` VALUES ('100594', '100229', '100', '1418007667');
INSERT INTO `recharge_record` VALUES ('100596', '101410', '2000', '1418017580');
INSERT INTO `recharge_record` VALUES ('100598', '101020', '600', '1418023272');
INSERT INTO `recharge_record` VALUES ('100600', '100681', '100', '1418031224');
INSERT INTO `recharge_record` VALUES ('100602', '101156', '100', '1418036108');
INSERT INTO `recharge_record` VALUES ('100604', '101189', '300', '1418040452');
INSERT INTO `recharge_record` VALUES ('100606', '101463', '1000', '1418043183');
INSERT INTO `recharge_record` VALUES ('100608', '101314', '4000', '1418052115');
INSERT INTO `recharge_record` VALUES ('100610', '101119', '100', '1418052636');
INSERT INTO `recharge_record` VALUES ('100612', '101492', '100', '1418053216');
INSERT INTO `recharge_record` VALUES ('100614', '101491', '2000', '1418053719');
INSERT INTO `recharge_record` VALUES ('100616', '101491', '1000', '1418055160');
INSERT INTO `recharge_record` VALUES ('100618', '101021', '100', '1418056795');
INSERT INTO `recharge_record` VALUES ('100620', '101021', '100', '1418057003');
INSERT INTO `recharge_record` VALUES ('100622', '101060', '100', '1418072725');
INSERT INTO `recharge_record` VALUES ('100624', '100720', '600', '1418077199');
INSERT INTO `recharge_record` VALUES ('100626', '100536', '600', '1418082690');
INSERT INTO `recharge_record` VALUES ('100628', '100231', '1000', '1418090851');
INSERT INTO `recharge_record` VALUES ('100630', '100220', '1000', '1418096011');
INSERT INTO `recharge_record` VALUES ('100632', '100220', '100', '1418096553');
INSERT INTO `recharge_record` VALUES ('100634', '100700', '600', '1418102054');
INSERT INTO `recharge_record` VALUES ('100636', '100231', '2000', '1418108713');
INSERT INTO `recharge_record` VALUES ('100638', '100231', '4000', '1418109014');
INSERT INTO `recharge_record` VALUES ('100640', '100229', '4000', '1418109465');
INSERT INTO `recharge_record` VALUES ('100642', '100229', '4000', '1418109562');
INSERT INTO `recharge_record` VALUES ('100658', '100226', '300', '1418117894');
INSERT INTO `recharge_record` VALUES ('100660', '100226', '300', '1418117910');
INSERT INTO `recharge_record` VALUES ('100663', '100226', '600', '1418117954');
INSERT INTO `recharge_record` VALUES ('100665', '101418', '100', '1418119466');
INSERT INTO `recharge_record` VALUES ('100667', '100900', '600', '1418120916');
INSERT INTO `recharge_record` VALUES ('100669', '101564', '100', '1418121684');
INSERT INTO `recharge_record` VALUES ('100671', '101314', '4000', '1418130388');
INSERT INTO `recharge_record` VALUES ('100673', '101498', '100', '1418130798');
INSERT INTO `recharge_record` VALUES ('100675', '100557', '1000', '1418131817');
INSERT INTO `recharge_record` VALUES ('100677', '100348', '1000', '1418137579');
INSERT INTO `recharge_record` VALUES ('100679', '101048', '100', '1418138087');
INSERT INTO `recharge_record` VALUES ('100681', '100348', '1000', '1418141459');
INSERT INTO `recharge_record` VALUES ('100683', '101617', '600', '1418153587');
INSERT INTO `recharge_record` VALUES ('100685', '100305', '300', '1418170901');
INSERT INTO `recharge_record` VALUES ('100687', '101634', '100', '1418174521');
INSERT INTO `recharge_record` VALUES ('100689', '101636', '100', '1418186985');
