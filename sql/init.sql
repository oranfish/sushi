create database `order`;
create database product;
create database user;

CREATE TABLE user.`u_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL COMMENT '0:后台账号 1:前台账号',
  `user_name` varchar(255) DEFAULT NULL COMMENT '登陆名',
  `password` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `telephone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `open_id` varchar(255) DEFAULT NULL COMMENT '微信小程序openid',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `telephone__index` (`telephone`)
);

CREATE TABLE user.`u_cookie` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_token` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `usertoken_index` (`user_token`)
)
