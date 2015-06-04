
CREATE TABLE IF NOT EXISTS `TABLE_NAME` (
  `ids` int(11) NOT NULL AUTO_INCREMENT,
  `log_time` datetime NOT NULL,
  `server_id` int(11) NOT NULL,
  `log_head` varchar(32) NOT NULL COMMENT '日志头部',
  `log_text` varchar(1024) NOT NULL,
  PRIMARY KEY (`ids`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 该表为日志表表结构. 其中 `TABLE_NAME` 不可修改, 其余部分可以根据具体需求修改
-- ----------------------------