CREATE DATABASE IF NOT EXISTS collect;
USE collect;

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci ,
                         `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `create_time` datetime(0) ,
                         `end_time` datetime(0) ,
                         `delete_time` datetime(0) NULL DEFAULT NULL,
                         `required_number` int(11) ,
                         `employer_id` int(11) ,
                         `employer_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `status` int(11) NOT NULL DEFAULT 4,
                         `accepted` tinyint(1) NOT NULL DEFAULT 0,
                         `manageable` tinyint(1) NOT NULL DEFAULT 0,
                         `file_name1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `file_name2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `similarity` double(16,8) NULL DEFAULT NULL,
                         `device` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `difficulty` int(11) NOT NULL,

                         PRIMARY KEY (`id`) USING BTREE,
                         INDEX `fk_user_task`(`employer_id`) USING BTREE,
                         CONSTRAINT `fk_user_task` FOREIGN KEY (`employer_id`) REFERENCES `user_info` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (1, '安卓游戏平台sdk优化', '功能测试', 'sdk添加功能模块及优化：一键登录  sdk热更新  广告推送 游戏时长统计、sdk老ui图部分更换。', '2022-3-1 10:00:00', '2023-8-1 10:00:00', NULL, 2, 1, '刘伊眉',4,false,false,'sdwd','swxw',0,'Android',2);
INSERT INTO `task` VALUES (2, '财新测试', '性能测试', '测试是否有bug', '2022-3-1 10:00:00', '2023-8-1 10:00:00', NULL, 2, 1, '刘伊眉',4,false,false,'excd','wdqd',0,'Android',1);
INSERT INTO `task` VALUES (3, '国海金探号-Bug探索', '性能测试', '国海证券手机版是国海证券推出的一款手机炒股软件,国海证券的交易用户可以通过手机、PDA可以享受一站式专业服务,真正实现“随时随地、证券交易”的移动商务理念', '2022-2-1 10:00:00', '2023-8-1 10:00:00', NULL, 2, 1, '刘伊眉',4,false,false,'dewq','wxde',0,'ios',3);
INSERT INTO `task` VALUES (4, '全民生活-8.1.5', '性能测试', '自行注册，全功能探索测试。', '2022-2-1 10:00:00', '2023-8-1 10:00:00', NULL, 2, 3, 'njq',4,false,false,'dewq','wxde',0,'鸿蒙',4);


-- ----------------------------
-- Table structure for todo_task
-- ----------------------------
DROP TABLE IF EXISTS `todo_task`;
CREATE TABLE `todo_task`  (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `task_id` int(11) NOT NULL,
                              `task_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                              `create_time` datetime(0) NOT NULL,
                              `user_id` int(11) NOT NULL,
                              `status` int(11) NOT NULL,
                              PRIMARY KEY (`id`) USING BTREE,
                              INDEX `fk_todo_task`(`task_id`) USING BTREE,
                              INDEX `fk_user_task`(`user_id`) USING BTREE,
                              CONSTRAINT `fk_todo_task` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
                              CONSTRAINT `fk_user_order` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of todo_task
-- ----------------------------







-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'lym', '10112345678', '123456', 'EMPLOYER', '2022-2-18 10:00:00');
INSERT INTO `user_info` VALUES (3, 'njq', '10312345678', '123456', 'EMPLOYER', '2022-2-18 10:00:00');
INSERT INTO `user_info` VALUES (2, 'lrx', '10212345678', '123456', 'EMPLOYEE', '2022-2-28 10:00:00');
INSERT INTO `user_info` VALUES (8, 'zzr', '10412345678', '123456', 'ADMIN', '2022-2-28 10:00:00');

-- reports
--
DROP TABLE IF EXISTS report;
CREATE TABLE report (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    task_id INTEGER NOT NULL REFERENCES task (id),
    employee_id INTEGER NOT NULL REFERENCES user_info (id),
    employee_name VARCHAR(255) NOT NULL,
    report_time DATETIME(3) NOT NULL,
    title VARCHAR(255) NOT NULL,
    intro VARCHAR(255) NOT NULL,
    steps VARCHAR(255) NOT NULL,
    device VARCHAR(255) NOT NULL,
    picture VARCHAR(255) NOT NULL,
    bug_form VARCHAR(255) NOT NULL,
    severity VARCHAR(255) NOT NULL,
    recurrence VARCHAR(255) NOT NULL,
    score DOUBLE NULL DEFAULT NULL
)ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
INSERT INTO report VALUES (1, 2, 2, '1','2020-12-18 10:00:00', 'report 1', '使用直观的测试管理工具帮助创建和管理测试','第一步先打开软件然后选择个人喜好','鸿蒙设备','http://collect-191250.oss-cn-nanjing.aliyuncs.com/upload/2022-05-21/fa3e8124037f4a00934e3ec21384d133.jpg','功能不完整','待定','无规律复现',null);
INSERT INTO report VALUES (2, 1, 2, '1','2020-12-28 10:00:00', 'report 2', '通过重复性测试了解问题的规模','第二步再选择活跃度等个人信息作为标签','Android设备','http://collect-191250.oss-cn-nanjing.aliyuncs.com/upload/2022-05-21/fa3e8124037f4a00934e3ec21384d133.jpg','功能不完整','较轻','小概率复现',null);
INSERT INTO report VALUES (3, 1, 5, '1','2021-02-28 05:00:00', 'report 3', '帮助快速迭代反馈，以提供更快、更可预测的版本。','第三步在任务推荐是选择何种任务推荐规则由管理员决定','鸿蒙设备','http://collect-191250.oss-cn-nanjing.aliyuncs.com/upload/2022-05-21/fa3e8124037f4a00934e3ec21384d133.jpg','不正常退出','严重','无规律复现',null);
INSERT INTO report VALUES (4, 4, 6, '1','2021-03-28 10:34:00', 'report 4', '提供快速测试执行','在显示报告列表除基本信息外还应有详细页面供使用者参考才更人性化','Android设备','http://collect-191250.oss-cn-nanjing.aliyuncs.com/upload/2022-05-21/fa3e8124037f4a00934e3ec21384d133.jpg','不正常退出','待定','小概率复现',null);
INSERT INTO report VALUES (5, 2, 5, '1','2021-04-28 10:00:00', 'report 5', '简化测试管理','简化测试管理需要强有力的平台支持，未来的工作很多','Android设备','http://collect-191250.oss-cn-nanjing.aliyuncs.com/upload/2022-05-21/fa3e8124037f4a00934e3ec21384d133.jpg','不正常退出','一般','无规律复现',null);
INSERT INTO report VALUES (6, 4, 5, '1','2021-05-28 12:56:00', 'report 6', '实时接收结果，并集成到CI/CD工作流中','测试IO帮助提升搜索引擎排名。软件由人工测试人员进行测试。','鸿蒙设备','http://collect-191250.oss-cn-nanjing.aliyuncs.com/upload/2022-05-21/fa3e8124037f4a00934e3ec21384d133.jpg','功能不完整','严重','大概率复现',null);
INSERT INTO report VALUES (7, 4, 2, '1','2021-06-28 01:00:00', 'report 7', 'TestIO是一家软件测试服务提供商，可以帮助提高应用程序的质量。他们为网站、移动设备、可穿戴设备和物联网设备提供各种QA解决方案。','提供经过测试的软件的快速交付。良好的众包测试网站或平台是我们所需要的','Linux设备','http://collect-191250.oss-cn-nanjing.aliyuncs.com/upload/2022-05-21/fa3e8124037f4a00934e3ec21384d133.jpg','不正常退出','一般','大概率复现',null);

-- ----------------------------
-- Table structure for recommend_rule
-- ----------------------------
DROP TABLE IF EXISTS `recommend_rule`;
CREATE TABLE `recommend_rule`  (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                              `status` tinyint(1) NOT NULL DEFAULT 1,
                              `type` int(11) NOT NULL,
                              `change_time` datetime(0) NOT NULL,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recommend_rule
-- ----------------------------
INSERT INTO `recommend_rule` VALUES (1, '基于任务内容的协同过滤推荐', 1, 1,  '2022-3-12 10:00:00');

-- ----------------------------
-- Table structure for preference
-- ----------------------------
DROP TABLE IF EXISTS `preference`;
CREATE TABLE `preference`  (
                                   `id` int(11) NOT NULL AUTO_INCREMENT,
                                   employee_id INTEGER NOT NULL REFERENCES user_info (id),
                                   `device` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                   `task_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of preference
-- ----------------------------
INSERT INTO `preference` VALUES ( 1, 2, 'Android',  '性能测试');
INSERT INTO `preference` VALUES ( 2, 4, 'Linux',  '性能测试');
INSERT INTO `preference` VALUES ( 3, 5, 'ios',  '功能测试');
INSERT INTO `preference` VALUES ( 4, 6, 'Linux',  '功能测试');


DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               employee_id INTEGER NOT NULL REFERENCES user_info (id),
                               `report_id` INTEGER NOT NULL REFERENCES report (id),
                               `score` int(11) ,
                               `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci ,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report_comment
-- ----------------------------
INSERT INTO `comment` VALUES ( 1, 1, 1,4,  'well done');


SET FOREIGN_KEY_CHECKS = 1;


-- report_similarity
--
DROP TABLE IF EXISTS report_similarity;
CREATE TABLE report_similarity (
                        id INTEGER PRIMARY KEY AUTO_INCREMENT,
                        report_id1 INTEGER NOT NULL REFERENCES report (id),
                        report_id2 INTEGER NOT NULL,
                        title DOUBLE NOT NULL,
                        intro DOUBLE NOT NULL,
                        steps DOUBLE NOT NULL,
                        device DOUBLE NOT NULL,
                        picture VARCHAR(255) NOT NULL,
                        bug_form DOUBLE NOT NULL,
                        severity DOUBLE NOT NULL,
                        recurrence DOUBLE NOT NULL,
                        similarity DOUBLE NOT NULL
)ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
INSERT INTO report_similarity VALUES (1, 8, 2, 0.3,0.5, 0.5, 1,'collect-191250.oss-cn-nanjing.aliyuncs.com/upload/2022-05-21/fa3e8124037f4a00934e3ec21384d133.jpg',0.8,1,1,0.8);
INSERT INTO report_similarity VALUES (2, 8, 3, 0.3,0.7, 0.5, 1,'collect-191250.oss-cn-nanjing.aliyuncs.com/upload/2022-05-21/fa3e8124037f4a00934e3ec21384d133.jpg',0.8,1,1,0.8);

-- report_cluster
--
DROP TABLE IF EXISTS report_cluster;
CREATE TABLE report_cluster (
                                   id INTEGER PRIMARY KEY AUTO_INCREMENT,
                                   task_id INTEGER NOT NULL REFERENCES task (id),
                                   report_id INTEGER NOT NULL REFERENCES report (id),
                                   report_cluster INTEGER NOT NULL,
                                   intro VARCHAR(255) NOT NULL,
                                   status INTEGER NOT NULL DEFAULT 0,
                                   extra VARCHAR(255) DEFAULT NULL
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

