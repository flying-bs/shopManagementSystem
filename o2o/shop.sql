USE O2O;
`‘area_name‘`
#MYSQL数据库引擎主要分为MYISAM（表级锁,按照全表扫描，读性能快），INNODB为行级锁，可以同时更新，更新速度快。
DROP TABLE IF EXISTS `tb_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_area` (
  `area_id` INT(5) NOT NULL AUTO_INCREMENT,
  `area_name` VARCHAR(200) NOT NULL,
  `area_desc` VARCHAR(1000) DEFAULT NULL,
  `priority` INT(2) NOT NULL DEFAULT '0',
  `create_time` DATETIME DEFAULT NULL,
  `last_edit_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`area_id`),
  UNIQUE KEY `UK_AREA` (`area_name`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_head_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_head_line` (
  `line_id` INT(100) NOT NULL AUTO_INCREMENT,
  `line_name` VARCHAR(1000) DEFAULT NULL,
  `line_link` VARCHAR(2000) NOT NULL,
  `line_img` VARCHAR(2000) NOT NULL,
  `priority` INT(2) DEFAULT NULL,
  `enable_status` INT(2) NOT NULL DEFAULT '0',
  `create_time` DATETIME DEFAULT NULL,
  `last_edit_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`line_id`)
) ENGINE=INNODB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

CREATE TABLE `tb_local_auth` (
  `local_auth_id` INT(10) NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) DEFAULT NULL,
  `user_name` VARCHAR(128) COLLATE utf8_unicode_ci NOT NULL,
  `password` VARCHAR(128) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` DATETIME DEFAULT NULL,
  `last_edit_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`local_auth_id`),
  UNIQUE KEY `uk_local_profile` (`user_name`),
  KEY `fk_local_profile` (`user_id`),
  CONSTRAINT `fk_local_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=INNODB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `tb_person_info` (
  `user_id` INT(10) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthday` DATETIME DEFAULT NULL,
  `gender` VARCHAR(2) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` VARCHAR(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` VARCHAR(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `profile_img` VARCHAR(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `customer_flag` INT(2) NOT NULL DEFAULT '0',
  `shop_owner_flag` INT(2) NOT NULL DEFAULT '0',
  `admin_flag` INT(2) NOT NULL,
  `create_time` DATETIME DEFAULT NULL,
  `last_edit_time` DATETIME DEFAULT NULL,
  `enable_status` INT(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `tb_product` (
  `product_id` INT(100) NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(100) NOT NULL,
  `product_desc` VARCHAR(2000) DEFAULT NULL,
  `img_addr` VARCHAR(2000) DEFAULT '',
  `normal_price` VARCHAR(100) DEFAULT NULL,
  `promotion_price` VARCHAR(100) DEFAULT NULL,
  `priority` INT(2) NOT NULL DEFAULT '0',
  `create_time` DATETIME DEFAULT NULL,
  `last_edit_time` DATETIME DEFAULT NULL,
  `enable_status` INT(2) NOT NULL DEFAULT '0',
  `point` INT(10) DEFAULT NULL,
  `product_category_id` INT(11) DEFAULT NULL,
  `shop_id` INT(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`),
  KEY `fk_product_shop` (`shop_id`),
  KEY `fk_product_procate` (`product_category_id`),
  CONSTRAINT `fk_product_procate` FOREIGN KEY (`product_category_id`) REFERENCES `tb_product_category` (`product_category_id`),
  CONSTRAINT `fk_product_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=INNODB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
CREATE TABLE `tb_product_category` (
  `product_category_id` INT(11) NOT NULL AUTO_INCREMENT,
  `product_category_name` VARCHAR(100) NOT NULL,
  `product_category_desc` VARCHAR(500) DEFAULT NULL,
  `priority` INT(2) DEFAULT '0',
  `create_time` DATETIME DEFAULT NULL,
  `last_edit_time` DATETIME DEFAULT NULL,
  `shop_id` INT(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_category_id`),
  KEY `fk_procate_shop` (`shop_id`),
  CONSTRAINT `fk_procate_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=INNODB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

CREATE TABLE `tb_product_img` (
  `product_img_id` INT(20) NOT NULL AUTO_INCREMENT,
  `img_addr` VARCHAR(2000) NOT NULL,
  `img_desc` VARCHAR(2000) DEFAULT NULL,
  `priority` INT(2) DEFAULT '0',
  `create_time` DATETIME DEFAULT NULL,
  `product_id` INT(20) DEFAULT NULL,
  PRIMARY KEY (`product_img_id`),
  KEY `fk_proimg_product` (`product_id`),
  CONSTRAINT `fk_proimg_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

CREATE TABLE `tb_shop` (
  `shop_id` INT(10) NOT NULL AUTO_INCREMENT,
  `owner_id` INT(10) NOT NULL COMMENT '店铺创建人',
  `area_id` INT(5) DEFAULT NULL,
  `shop_category_id` INT(11) DEFAULT NULL,
  `parent_category_id` INT(11) DEFAULT NULL,
  `shop_name` VARCHAR(256) COLLATE utf8_unicode_ci NOT NULL,
  `shop_desc` VARCHAR(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_addr` VARCHAR(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` VARCHAR(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_img` VARCHAR(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `longitude` DOUBLE(16,12) DEFAULT NULL,
  `latitude` DOUBLE(16,12) DEFAULT NULL,
  `priority` INT(3) DEFAULT '0',
  `create_time` DATETIME DEFAULT NULL,
  `last_edit_time` DATETIME DEFAULT NULL,
  `enable_status` INT(2) NOT NULL DEFAULT '0',
  `advice` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`shop_id`),
  KEY `fk_shop_profile` (`owner_id`),
  KEY `fk_shop_area` (`area_id`),
  KEY `fk_shop_shopcate` (`shop_category_id`),
  KEY `fk_shop_parentcate` (`parent_category_id`),
  CONSTRAINT `fk_shop_area` FOREIGN KEY (`area_id`) REFERENCES `tb_area` (`area_id`),
  CONSTRAINT `fk_shop_parentcate` FOREIGN KEY (`parent_category_id`) REFERENCES `tb_shop_category` (`shop_category_id`),
  CONSTRAINT `fk_shop_profile` FOREIGN KEY (`owner_id`) REFERENCES `tb_person_info` (`user_id`),
  CONSTRAINT `fk_shop_shopcate` FOREIGN KEY (`shop_category_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=INNODB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `tb_shop_category` (
  `shop_category_id` INT(11) NOT NULL AUTO_INCREMENT,
  `shop_category_name` VARCHAR(100) NOT NULL DEFAULT '',
  `shop_category_desc` VARCHAR(1000) DEFAULT '',
  `shop_category_img` VARCHAR(2000) DEFAULT NULL,
  `priority` INT(2) NOT NULL DEFAULT '0',
  `create_time` DATETIME DEFAULT NULL,
  `last_edit_time` DATETIME DEFAULT NULL,
  `parent_id` INT(11) DEFAULT NULL,
  PRIMARY KEY (`shop_category_id`),
  KEY `fk_shop_category_self` (`parent_id`),
  CONSTRAINT `fk_shop_category_self` FOREIGN KEY (`parent_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=INNODB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
CREATE TABLE `tb_wechat_auth` (
  `wechat_auth_id` INT(10) NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) NOT NULL,
  `open_id` VARCHAR(512) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`wechat_auth_id`),
  KEY `fk_oauth_profile` (`user_id`),
  KEY `uk_oauth` (`open_id`(255)),
  CONSTRAINT `fk_oauth_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=INNODB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
