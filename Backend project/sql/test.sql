CREATE DATABASE IF NOT EXISTS collect;
USE collect;

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;



-- ----------------------------
-- Records of task
-- ----------------------------

INSERT INTO `task` VALUES (5, '中国移动 7.7.0-用例执行', '性能测试', '根据用例执行', '2022-2-1 10:00:00', '2022-8-1 10:00:00', NULL, 2, 3, 'njq',4,false,false,'dewq','wxde',0,'ios',3);
INSERT INTO `task` VALUES (6 , '金阳光测试', '功能测试', '理财首页各icon进入情况；基金、私募、活期、稳健理财、天天赢详情页展示情况；', '2022-2-1 10:00:00', '2022-8-1 10:00:00', NULL, 2, 3, 'njq',4,false,false,'dewq','wxde',0,'Linux',3);
INSERT INTO `task` VALUES (7 , '中国联通-9.0.2', '功能测试', '测试重点：参考讨论组文档进行测试，文档中内容需要重点测试', '2022-2-1 10:00:00', '2022-8-1 10:00:00', NULL, 2, 3, 'njq',4,false,false,'dewq','wxde',0,'Android',3);




-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (4, 'lym1', '11', '1', 'EMPLOYEE', '2022-2-18 10:00:00');
INSERT INTO `user_info` VALUES (5, 'njq1', '12', '1', 'EMPLOYEE', '2022-2-18 10:00:00');
INSERT INTO `user_info` VALUES (6, 'lrx1', '13', '1', 'EMPLOYEE', '2022-2-28 10:00:00');
INSERT INTO `user_info` VALUES (7, 'admin', '1111', '1', 'ADMIN', '2022-2-28 10:00:00');

-- ----------------------------
-- Records of todo_task
-- ----------------------------
INSERT INTO `todo_task` VALUES (1, 1, '安卓游戏平台sdk优化', '2022-2-1 10:00:00', 2, 3);
INSERT INTO `todo_task` VALUES (2, 1, '安卓游戏平台sdk优化', '2022-2-1 10:00:00', 5, 3);
INSERT INTO `todo_task` VALUES (3, 2, '财新测试', '2022-2-1 10:00:00', 2, 2);
INSERT INTO `todo_task` VALUES (4, 2, '财新测试', '2022-2-1 10:00:00', 5, 2);
INSERT INTO `todo_task` VALUES (7, 4, '全民生活-8.1.5', '2022-2-1 10:00:00', 2, 3);
 INSERT INTO `todo_task` VALUES (5, 5, '中国移动 7.7.0-用例执行', '2022-2-1 10:00:00', 4, 3);
 INSERT INTO `todo_task` VALUES (6, 6, '金阳光测试', '2022-2-1 10:00:00', 5, 3);
INSERT INTO `todo_task` VALUES (8, 4, '全民生活-8.1.5', '2022-2-1 10:00:00', 6, 3);
INSERT INTO `todo_task` VALUES (9, 5, '中国移动 7.7.0-用例执行', '2022-2-1 10:00:00', 5, 3);
INSERT INTO `todo_task` VALUES (10, 6, '金阳光测试', '2022-2-1 10:00:00', 6, 3);

INSERT INTO `recommend_rule` VALUES (2, '根据用户自选偏好进行推荐', 1, 2,  '2022-3-13 10:00:00');

INSERT INTO `recommend_rule` VALUES (3, '根据用户往期任务推荐', 2, 3,  '2022-3-14 10:00:00');

INSERT INTO report VALUES (8, 3, 2, '1','2020-12-18 10:00:00', 'report 8', '进入兴趣部落 在兴趣部落的帖子内无法进行回复操作','第一步先打开软件然后选择个人喜好','鸿蒙设备','http://collect-191250.oss-cn-nanjing.aliyuncs.com/upload/2022-05-21/fa3e8124037f4a00934e3ec21384d133.jpg','功能不完整','待定','无规律复现',null);
INSERT INTO report VALUES (9, 1, 3, '1','2020-12-28 10:00:00', 'report 9', '1打开企鹅众测，登录并进入发现页面 2点击积分兑换 3点击兑换70元qq红包 4个人信息手机号填写111 5点击保存 结果：没有提示手机号格式错误，安全有很大问题','Android设备','http://collect-191250.oss-cn-nanjing.aliyuncs.com/upload/2022-05-21/fa3e8124037f4a00934e3ec21384d133.jpg','功能不完整','较轻','小概率复现',null);
INSERT INTO report VALUES (10, 1, 4, '1','2021-02-28 05:00:00', 'report 10', '1.点击我的 2.点击右上角设置 3.点击手机号绑定 让客户误以为没有绑定手机号，手机号已绑定应该显示手机号，再设置修改绑定手机号，','第三步在任务推荐是选择何种任务推荐规则由管理员决定','鸿蒙设备','http://collect-191250.oss-cn-nanjing.aliyuncs.com/upload/2022-05-21/fa3e8124037f4a00934e3ec21384d133.jpg','不正常退出','严重','无规律复现',null);
INSERT INTO report VALUES (11, 4, 1, '1','2021-03-28 10:34:00', 'report 11', '点击任务分享 选择QQ分享 点击返回企鹅众测 分享渠道选择浮层没有收起','在显示报告列表除基本信息外还应有详细页面供使用者参考才更人性化','Android设备','http://collect-191250.oss-cn-nanjing.aliyuncs.com/upload/2022-05-21/fa3e8124037f4a00934e3ec21384d133.jpg','不正常退出','待定','小概率复现',null);
INSERT INTO report VALUES (12, 2, 3, '1','2021-04-28 10:00:00', 'report 12', '点击优秀Bug展示 没有显示“加载中”之类提示语以表明会出现更多内容，或者显示“已经到底”之类提示语以表明会不会出现更多内容，使得用户出现误解，不能及时判断是否已经加载完毕','简化测试管理需要强有力的平台支持，未来的工作很多','Android设备','http://collect-191250.oss-cn-nanjing.aliyuncs.com/upload/2022-05-21/fa3e8124037f4a00934e3ec21384d133.jpg','不正常退出','一般','无规律复现',null);