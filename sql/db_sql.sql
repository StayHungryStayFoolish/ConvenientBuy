DROP DATABASE IF EXISTS db_convenientbuy;
CREATE DATABASE db_convenientbuy;

DROP TABLE IF EXISTS db_convenientbuy.cb_content;
CREATE TABLE db_convenientbuy.cb_content (
  id          BIGINT(20)   AUTO_INCREMENT PRIMARY KEY
  COMMENT 'PK ID',
  category_id BIGINT(20) NOT NULL
  COMMENT '类别 ID',
  title       VARCHAR(200) DEFAULT NULL
  COMMENT '标题',
  sub_title   VARCHAR(100) DEFAULT NULL
  COMMENT '子标题',
  title_desc  VARCHAR(500) DEFAULT NULL
  COMMENT '标题描述',
  url         VARCHAR(300) DEFAULT NULL
  COMMENT '链接',
  pic         VARCHAR(400) DEFAULT NULL
  COMMENT '图片1绝对路径',
  pic2        VARCHAR(400) DEFAULT NULL
  COMMENT '图片2绝对路径',
  content     DATETIME     DEFAULT NULL
  COMMENT '内容',
  created     DATETIME     DEFAULT NULL
  COMMENT '创建时间',
  updated     DATETIME     DEFAULT NULL
  COMMENT '更新时间',
  KEY category_id(category_id),
  KEY updated(updated)
)
  COMMENT '首页展示图';


DROP TABLE IF EXISTS db_convenientbuy.cb_content_category;
CREATE TABLE db_convenientbuy.cb_content_category (
  id         BIGINT(20)  AUTO_INCREMENT PRIMARY KEY
  COMMENT 'PK ID',
  parent_id  BIGINT(20)  DEFAULT NULL
  COMMENT '父类 ID=0，代表一级',
  name       VARCHAR(50) DEFAULT NULL
  COMMENT '类别名称',
  status     INT(1)      DEFAULT '1'
  COMMENT '状态，1正常/2删除',
  sort_order INT(4)      DEFAULT NULL
  COMMENT '排序，同级类别展示次序。',
  is_parent  TINYINT(1)  DEFAULT '1'
  COMMENT '1 true/ 2false',
  created    DATETIME    DEFAULT NULL
  COMMENT '创建时间',
  updated    DATETIME    DEFAULT NULL
  COMMENT '更新时间',
  KEY parent_id (parent_id, status) USING BTREE,
  KEY sort_order(sort_order)
)
  COMMENT 'CMS - 类目分类表';

DROP TABLE IF EXISTS db_convenientbuy.cb_item;
CREATE TABLE db_convenientbuy.cb_iteme (
  id         BIGINT(20)            AUTO_INCREMENT PRIMARY KEY
  COMMENT 'PK ID',
  title      VARCHAR(100) NOT NULL
  COMMENT '',
  sell_point VARCHAR(500)          DEFAULT NULL
  COMMENT '',
  price      BIGINT(20)   NOT NULL
  COMMENT '',
  num        INT(10)      NOT NULL
  COMMENT '',
  barcode    VARCHAR(30)           DEFAULT NULL
  COMMENT '',
  image      VARCHAR(500)          DEFAULT NULL
  COMMENT '',
  cid        BIGINT(10)   NOT NULL
  COMMENT '',
  status     TINYINT(4)   NOT NULL DEFAULT '1'
  COMMENT '',
  created    DATETIME              DEFAULT NULL
  COMMENT '创建时间',
  updated    DATETIME              DEFAULT NULL
  COMMENT '更新时间',
  KEY cid(cid),
  KEY status(status),
  KEY updated(updated)
)
  COMMENT '';


SELECT *
FROM db_convenientbuy.cb_content;
