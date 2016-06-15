DROP DATABASE IF EXISTS db_convenientbuy;
CREATE DATABASE db_convenientbuy;

DROP TABLE IF EXISTS db_convenientbuy.cb_content;
CREATE TABLE db_convenientbuy.cb_content (
  id BIGINT(20) AUTO_INCREMENT PRIMARY KEY COMMENT 'PK ID',
  category_id BIGINT(20) NOT NULL COMMENT '类别 ID',
  title VARCHAR(200) DEFAULT NULL COMMENT '标题',
  sub_title VARCHAR(100) DEFAULT NULL COMMENT '子标题',
  title_desc VARCHAR(500) DEFAULT NULL COMMENT '标题描述',
  url VARCHAR(300) DEFAULT NULL COMMENT '链接',
  pic VARCHAR(400) DEFAULT NULL COMMENT '图片1绝对路径',
  pic2 VARCHAR(400) DEFAULT NULL COMMENT '图片2绝对路径',
  content DATETIME DEFAULT NULL COMMENT '内容',
  created DATETIME DEFAULT NULL COMMENT '创建时间',
  updated DATETIME DEFAULT NULL COMMENT '更新时间',
  KEY category_id(category_id),
  KEY updated(updated)
) COMMENT '首页展示图';


DROP TABLE IF EXISTS db_convenientbuy.cb_content_category;
CREATE TABLE db_convenientbuy.cb_content_category (
  id BIGINT(20) AUTO_INCREMENT PRIMARY KEY COMMENT '',
  parent_id BIGINT(20) DEFAULT NULL COMMENT '',
  name VARCHAR(50) DEFAULT NULL COMMENT '',
  status INT(1) DEFAULT '1' COMMENT '',
  sort_order INT(4) DEFAULT NULL COMMENT '',
  is_parent TINYINT(1) DEFAULT '1' COMMENT '',
  created DATETIME DEFAULT NULL COMMENT '创建时间',
  updated DATETIME DEFAULT NULL COMMENT '更新时间',
  KEY parent_id (parent_id,status) USING BTREE,
  KEY sort_order(sort_order)
) COMMENT '';


SELECT *
FROM db_convenientbuy.cb_content);
