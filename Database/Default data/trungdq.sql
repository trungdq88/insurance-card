-- MySQL dump 10.13  Distrib 5.6.20, for osx10.6 (x86_64)
--
-- Host: localhost    Database: mic_data
-- ------------------------------------------------------
-- Server version	5.6.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `increments`
--

DROP TABLE IF EXISTS `increments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `increments` (
  `table_name` varchar(255) NOT NULL,
  `increment` int(11) DEFAULT NULL,
  PRIMARY KEY (`table_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='increments';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `increments`
--

LOCK TABLES `increments` WRITE;
/*!40000 ALTER TABLE `increments` DISABLE KEYS */;
INSERT INTO `increments` VALUES ('BT',1),('HD',12);
/*!40000 ALTER TABLE `increments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mic_accident`
--

DROP TABLE IF EXISTS `mic_accident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mic_accident` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(250) NOT NULL,
  `attachment` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified` datetime NOT NULL,
  `contract_code` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mic_accident_mic_contract1_idx` (`contract_code`),
  CONSTRAINT `fk_mic_accident_mic_contract1` FOREIGN KEY (`contract_code`) REFERENCES `mic_contract` (`contract_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='Accident information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_accident`
--

LOCK TABLES `mic_accident` WRITE;
/*!40000 ALTER TABLE `mic_accident` DISABLE KEYS */;
/*!40000 ALTER TABLE `mic_accident` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mic_business_rules`
--

DROP TABLE IF EXISTS `mic_business_rules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mic_business_rules` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `start_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `start_date_before` int(10) unsigned NOT NULL,
  `start_date_after` int(10) unsigned NOT NULL,
  `contract_min_term` int(11) unsigned NOT NULL,
  `contract_default_term` int(10) unsigned NOT NULL,
  `paid_date_before` int(10) unsigned NOT NULL,
  `paid_date_after` int(10) unsigned NOT NULL,
  `cancel_date_before` int(10) unsigned NOT NULL,
  `cancel_date_after` int(10) unsigned NOT NULL,
  `payment_due_date` int(10) unsigned NOT NULL,
  `nearly_exceed_expired_one` int(10) unsigned NOT NULL,
  `nearly_exceed_expired_two` int(10) unsigned NOT NULL,
  `nearly_exceed_expired_three` int(10) unsigned NOT NULL,
  `new_card_request_fee` int(10) unsigned NOT NULL,
  `delivery_fee` int(10) unsigned NOT NULL,
  `update_contract_due_date` int(10) unsigned NOT NULL,
  `contract_renew_limit` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_business_rules`
--

LOCK TABLES `mic_business_rules` WRITE;
/*!40000 ALTER TABLE `mic_business_rules` DISABLE KEYS */;
INSERT INTO `mic_business_rules` VALUES (1,'2015-07-26 13:22:39',7,1825,1,12,7,30,7,30,7,15,7,3,50000,10000,30,60);
/*!40000 ALTER TABLE `mic_business_rules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mic_card`
--

DROP TABLE IF EXISTS `mic_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mic_card` (
  `card_id` varchar(20) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_card`
--

LOCK TABLES `mic_card` WRITE;
/*!40000 ALTER TABLE `mic_card` DISABLE KEYS */;
INSERT INTO `mic_card` VALUES ('1029375283',1),('2874916285723',1),('347298376510',1),('34875720103',1),('3683457892',1),('382719658223',1),('384792387651',1),('3857629834326',1),('4871928491232',1),('5032384203434',1),('589271982732',1),('8049283172874',1),('8374659283423',1),('85287912372',1),('9021748528325',1),('90284717292325',1),('940382734892',1);
/*!40000 ALTER TABLE `mic_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mic_card_access_log`
--

DROP TABLE IF EXISTS `mic_card_access_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mic_card_access_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `access_date` datetime NOT NULL,
  `device` varchar(50) NOT NULL,
  `request_service` varchar(200) NOT NULL,
  `response_content` varchar(200) NOT NULL,
  `card_instance_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mic_card_access_log_mic_card1_idx` (`card_instance_id`),
  CONSTRAINT `fk_card_id` FOREIGN KEY (`card_instance_id`) REFERENCES `mic_card_instance` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='Card access log';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_card_access_log`
--

LOCK TABLES `mic_card_access_log` WRITE;
/*!40000 ALTER TABLE `mic_card_access_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `mic_card_access_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mic_card_instance`
--

DROP TABLE IF EXISTS `mic_card_instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mic_card_instance` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `card_id` varchar(20) NOT NULL,
  `activated_date` datetime NOT NULL,
  `deactivated_date` datetime DEFAULT NULL,
  `contract_code` varchar(10) NOT NULL,
  `new_card_request_id` int(11) unsigned DEFAULT NULL,
  `customer_code` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mic_card_mic_contract1_idx` (`contract_code`),
  KEY `fk_mic_card_mic_new_card_request1_idx` (`new_card_request_id`),
  KEY `fk_card_id_idx` (`card_id`),
  KEY `fk_customer_code_idx` (`customer_code`),
  CONSTRAINT `fk_card_id_instance_id` FOREIGN KEY (`card_id`) REFERENCES `mic_card` (`card_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_code` FOREIGN KEY (`customer_code`) REFERENCES `mic_customer` (`customer_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_mic_card_mic_contract1` FOREIGN KEY (`contract_code`) REFERENCES `mic_contract` (`contract_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_mic_card_mic_new_card_request1` FOREIGN KEY (`new_card_request_id`) REFERENCES `mic_new_card_request` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='Card information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_card_instance`
--

LOCK TABLES `mic_card_instance` WRITE;
/*!40000 ALTER TABLE `mic_card_instance` DISABLE KEYS */;
INSERT INTO `mic_card_instance` VALUES (22,'3683457892','2015-07-26 14:16:16',NULL,'HD000B',NULL,'KH000A'),(23,'1029375283','2015-07-26 14:16:25',NULL,'HD000A',NULL,'KH0009'),(24,'85287912372','2015-07-26 14:16:33',NULL,'HD0009',NULL,'KH0008'),(25,'384792387651','2015-07-26 14:16:41',NULL,'HD0008',NULL,'KH0007'),(26,'34875720103','2015-07-26 14:16:51','2015-07-26 14:18:27','HD0007',NULL,'KH0006'),(27,'940382734892','2015-07-26 14:16:59','2015-07-26 14:18:37','HD0006',NULL,'KH0005'),(28,'5032384203434','2015-07-26 14:17:10',NULL,'HD0005',NULL,'KH0004'),(29,'8374659283423','2015-07-26 14:17:18','2015-07-26 14:17:44','HD0004',NULL,'KH0003'),(30,'589271982732','2015-07-26 14:17:44',NULL,'HD0004',NULL,'KH0003'),(31,'90284717292325','2015-07-26 14:17:57','2015-07-26 14:50:13','HD0003',NULL,'KH0002'),(32,'8049283172874','2015-07-26 14:18:08','2015-07-26 14:18:47','HD0002',NULL,'KH0001'),(33,'3857629834326','2015-07-26 14:18:18',NULL,'HD0001',NULL,'KH0001'),(34,'2874916285723','2015-07-26 14:18:27',NULL,'HD0007',NULL,'KH0006'),(35,'347298376510','2015-07-26 14:18:37',NULL,'HD0006',NULL,'KH0005'),(36,'9021748528325','2015-07-26 14:18:48',NULL,'HD0002',NULL,'KH0001'),(37,'4871928491232','2015-07-26 14:40:47',NULL,'HD000C',NULL,'KH0004'),(38,'382719658223','2015-07-26 14:50:34',NULL,'HD0003',19,'KH0002');
/*!40000 ALTER TABLE `mic_card_instance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mic_compensation`
--

DROP TABLE IF EXISTS `mic_compensation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mic_compensation` (
  `compensation_code` varchar(10) NOT NULL,
  `driver_name` varchar(80) NOT NULL,
  `license_number` varchar(15) NOT NULL,
  `license_type` varchar(10) NOT NULL,
  `driver_phone` varchar(15) NOT NULL,
  `vehicle_capacity` varchar(20) NOT NULL,
  `driver_address` varchar(250) NOT NULL,
  `plate` varchar(15) NOT NULL,
  `accident_date` datetime NOT NULL,
  `accident_place` varchar(250) NOT NULL,
  `control_department` varchar(250) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `human_damage` varchar(2000) NOT NULL,
  `asset_damage` varchar(2000) NOT NULL,
  `observer` varchar(80) NOT NULL,
  `observer_address` varchar(250) NOT NULL,
  `compensation_note` varchar(2000) DEFAULT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `resolve_date` datetime DEFAULT NULL,
  `decision` varchar(250) DEFAULT NULL,
  `resolve_note` varchar(2000) DEFAULT NULL,
  `last_modified` datetime NOT NULL,
  `contract_code` varchar(10) NOT NULL,
  PRIMARY KEY (`compensation_code`),
  KEY `fk_mic_compensation_mic_contract1_idx` (`contract_code`),
  CONSTRAINT `fk_mic_compensation_mic_contract1` FOREIGN KEY (`contract_code`) REFERENCES `mic_contract` (`contract_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Compensation information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_compensation`
--

LOCK TABLES `mic_compensation` WRITE;
/*!40000 ALTER TABLE `mic_compensation` DISABLE KEYS */;
INSERT INTO `mic_compensation` VALUES ('BT0001','Trương Văn Khải','6232342331','A1','0984283338','2','Phường 1, Q. 9, TP HCM','78A4-3624','2015-07-26 00:00:00','347 Lê Văn Thọ, Gò Vấp, Ho Chi Minh, Vietnam','CSGT Quận 12','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce id diam non magna faucibus vulputate at a nibh. In vel nunc sodales, aliquam lacus ut, imperdiet nisl. Morbi non est tellus. Donec risus eros, tristique quis ultricies quis, facilisis ac turpis. Ut dignissim gravida lobortis.','Fusce efficitur nisl eros, in consequat orci auctor id. Nullam rhoncus maximus condimentum. Nulla in felis in quam malesuada posuere in sed ex. Vestibulum non aliquet ligula. Maecenas rhoncus orci eu mi elementum, ut accumsan arcu congue.','Cras ac pretium neque, non tristique nisi. Donec libero ipsum, ornare a mollis id, finibus nec sem. Phasellus turpis mauris, imperdiet vitae viverra vel, auctor eget tellus. Cras maximus iaculis tortor. Pellentesque blandit accumsan vehicula. Curabitur ac sem semper, imperdiet libero ultricies, accumsan diam.','Đặng Thu Thảo','96 Nguyễn Thị Minh Khai, Ho Chi Minh, Vietnam','12 triệu đồng','https://www.filepicker.io/api/file/AAoiGLrRbaVQ2JA0shDv','2015-07-26 00:00:00','2015-07-26 00:00:00','Đồng ý bồi thường','Đã xác nhận và bồi thường theo biên bản số BB73742','2015-07-26 14:49:21','HD000B');
/*!40000 ALTER TABLE `mic_compensation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mic_contract`
--

DROP TABLE IF EXISTS `mic_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mic_contract` (
  `contract_code` varchar(10) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `start_date` datetime NOT NULL,
  `expired_date` datetime NOT NULL,
  `status` varchar(50) NOT NULL,
  `contract_fee` float unsigned NOT NULL,
  `plate` varchar(15) NOT NULL,
  `brand` varchar(20) NOT NULL,
  `model_code` varchar(20) DEFAULT NULL,
  `vehicle_type` varchar(20) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `engine` varchar(20) NOT NULL,
  `chassis` varchar(20) NOT NULL,
  `capacity` varchar(20) NOT NULL,
  `year_of_manufacture` int(11) unsigned DEFAULT NULL,
  `weight` int(11) unsigned DEFAULT NULL,
  `seat_capacity` int(11) unsigned DEFAULT NULL,
  `cancel_date` datetime DEFAULT NULL,
  `cancel_reason` varchar(250) DEFAULT NULL,
  `cancel_note` varchar(2000) DEFAULT NULL,
  `last_modified` datetime NOT NULL,
  `staff_code` varchar(10) DEFAULT NULL,
  `contract_type_id` int(11) unsigned NOT NULL,
  `customer_code` varchar(10) NOT NULL,
  `need_renew_payment` int(11) unsigned DEFAULT NULL,
  `active_card_instance_id` int(10) unsigned DEFAULT NULL,
  `modify_reason` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`contract_code`),
  KEY `fk_mic_contract_mic_staff_idx` (`staff_code`),
  KEY `fk_mic_contract_mic_contract_type1_idx` (`contract_type_id`),
  KEY `fk_mic_contract_mic_customer1_idx` (`customer_code`),
  KEY `fk_mic_contract_mic_card_instance_idx` (`active_card_instance_id`),
  CONSTRAINT `fk_mic_contract_mic_card_instance` FOREIGN KEY (`active_card_instance_id`) REFERENCES `mic_card_instance` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_mic_contract_mic_contract_type1` FOREIGN KEY (`contract_type_id`) REFERENCES `mic_contract_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_mic_contract_mic_customer1` FOREIGN KEY (`customer_code`) REFERENCES `mic_customer` (`customer_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_mic_contract_mic_staff` FOREIGN KEY (`staff_code`) REFERENCES `mic_staff` (`staff_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Contract information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_contract`
--

LOCK TABLES `mic_contract` WRITE;
/*!40000 ALTER TABLE `mic_contract` DISABLE KEYS */;
INSERT INTO `mic_contract` VALUES ('HD0001','2015-07-26 13:57:42','2015-03-26 00:00:00','2016-03-26 00:00:00','Ready',66000,'93H3-1232','Honda','X12','Hai bánh','Đỏ','626323','124232','110',2000,50,2,NULL,NULL,NULL,'2015-07-26 13:57:42','NV0001',2,'KH0001',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0002','2015-07-26 13:59:31','2015-04-27 00:00:00','2016-04-27 00:00:00','Ready',80500,'56E4-5953','Yamaha','H66','Hai bánh','Xanh','845624','251263','150',2002,56,2,NULL,NULL,NULL,'2015-07-26 13:59:31','NV0001',3,'KH0001',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0003','2015-07-26 14:02:17','2014-12-12 00:00:00','2015-12-12 00:00:00','Ready',66000,'86H4-3853','Yamaha','G33','Hai bánh','22','3562343','574564','130',2001,62,2,NULL,NULL,NULL,'2015-07-26 14:02:17','NV0001',2,'KH0002',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0004','2015-07-26 14:03:54','2015-02-06 00:00:00','2016-02-06 00:00:00','Ready',80500,'33H4-3735','Honda','A3','Hai bánh','Vàng','3462343','46347245','110',2003,53,2,NULL,NULL,NULL,'2015-07-26 14:03:54','NV0001',3,'KH0003',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0005','2015-07-26 14:05:01','2015-04-30 00:00:00','2016-04-30 00:00:00','Ready',319000,'84F3-5638','Yamaha','B31','Hai bánh','Đỏ','834623','7248345','120',2005,55,2,NULL,NULL,NULL,'2015-07-26 14:05:01','NV0001',5,'KH0004',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0006','2015-07-26 14:06:22','2014-10-23 00:00:00','2015-10-23 00:00:00','Ready',60500,'76B3-7654','Honda','J81','Hai bánh','Xanh','73451373','6283453','120',2007,58,2,NULL,NULL,NULL,'2015-07-26 14:06:22','NV0001',4,'KH0005',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0007','2015-07-26 14:07:34','2015-01-01 00:00:00','2016-01-01 00:00:00','Ready',60500,'47K6-5824','Honda','Y66','Hai bánh','Xanh','3472345','7453346','120',2003,56,2,NULL,NULL,NULL,'2015-07-26 14:07:34','NV0001',4,'KH0006',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0008','2015-07-26 14:08:56','2015-03-01 00:00:00','2016-03-01 00:00:00','Ready',80500,'43B6-0678','Honda','M633','Hai bánh','Hồng','8650534','7456236','130',2003,56,2,NULL,NULL,NULL,'2015-07-26 14:08:56','NV0001',3,'KH0007',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0009','2015-07-26 14:12:05','2015-05-22 00:00:00','2016-05-22 00:00:00','Ready',60500,'30H8-1823','Yamaha','L231','Hai bánh','Tím','246723434','6247345','150',2003,54,2,NULL,NULL,NULL,'2015-07-26 14:12:05','NV0001',4,'KH0008',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD000A','2015-07-26 14:13:46','2015-02-11 00:00:00','2016-02-11 00:00:00','Ready',80500,'67Y3-3462','Honda','T55','Hai bánh','Đỏ','8354562','7385456','150',2003,49,2,NULL,NULL,NULL,'2015-07-26 14:13:46','NV0001',3,'KH0009',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD000B','2015-07-26 14:15:08','2014-08-12 00:00:00','2015-08-12 00:00:00','Ready',319000,'78A4-3624','Yamaha','R53','Hai bánh','Trắng','60586423','61732234','120',2003,50,2,NULL,NULL,NULL,'2015-07-26 14:15:08','NV0001',5,'KH000A',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD000C','2015-07-26 14:40:29','2015-02-12 00:00:00','2016-02-12 00:00:00','Ready',80500,'67H3-1231','Honda','T64','Hai bánh','Hồng','236123423','73584563','120',2003,52,2,NULL,NULL,NULL,'2015-07-26 14:40:29','NV0001',3,'KH0004',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này');
/*!40000 ALTER TABLE `mic_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mic_contract_type`
--

DROP TABLE IF EXISTS `mic_contract_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mic_contract_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `price_per_year` float unsigned NOT NULL,
  `active` int(10) unsigned DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='Contract type information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_contract_type`
--

LOCK TABLES `mic_contract_type` WRITE;
/*!40000 ALTER TABLE `mic_contract_type` DISABLE KEYS */;
INSERT INTO `mic_contract_type` VALUES (1,'Xe trên 50cc có BH cho người trên xe','Xe trên 50cc có BH cho người trên xe',86000,1),(2,'Xe trên 50cc không có BH cho người trên xe','Xe trên 50cc không có BH cho người trên xe',66000,1),(3,'Xe từ 50cc trở xuống có BH cho người trên xe','Xe dưới 50cc có BH cho người trên xe',80500,1),(4,'Xe từ 50cc trở xuống có BH cho người trên xe','Xe dưới 50cc không có BH cho người trên xe',60500,1),(5,'Xe mô tô ba bánh, xe gắn máy và các loại xe tương tự','Bảo hiểm dành cho xe ba bánh, xe gắn máy v.v',319000,1);
/*!40000 ALTER TABLE `mic_contract_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mic_customer`
--

DROP TABLE IF EXISTS `mic_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mic_customer` (
  `customer_code` varchar(10) NOT NULL,
  `name` varchar(80) NOT NULL,
  `address` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `personal_id` varchar(15) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `is_default_password` int(1) unsigned NOT NULL,
  `last_modified` datetime NOT NULL,
  PRIMARY KEY (`customer_code`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Customer information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_customer`
--

LOCK TABLES `mic_customer` WRITE;
/*!40000 ALTER TABLE `mic_customer` DISABLE KEYS */;
INSERT INTO `mic_customer` VALUES ('KH0001','Đinh Quang Trung','Phường Tân Chánh Hiệp, Q 12, TP HCM','trungdq88@gmail.com','123123123','123123123','4297f44b13955235245b2497399d7a93',1,'2015-07-03 16:01:08'),('KH0002','Trần Quang Đại','Phường Đa Kao, Q 1, TP HCM','daitranquang@test.vn','0903012842','273846158','4297f44b13955235245b2497399d7a93',1,'2015-07-26 14:49:56'),('KH0003','Nguyễn Thị Trang','Phường 7, Q 5, TP HCM','trangnguyenthi@test.vn','0987302918','283718237','4297f44b13955235245b2497399d7a93',0,'2015-06-30 12:07:33'),('KH0004','Cao Văn Trường','Phường 3, Q 4, TPHCM','truongcaovan@test.vn','0935823754','827461283','4297f44b13955235245b2497399d7a93',0,'2015-06-30 12:07:33'),('KH0005','Nguyễn Hữu Cảnh','Phường 3, Q. Phú Nhuận, TP HCM','canhnguyenhuu@test.vn','0988263748','837461523','4297f44b13955235245b2497399d7a93',0,'2015-06-30 12:07:33'),('KH0006','Lý Phương','Phường 1, Q. Thủ Đức, TP HCM','phuongly@test.vn','0928374185','928316237','4297f44b13955235245b2497399d7a93',0,'2015-06-30 12:07:33'),('KH0007','Mai Trần Hậu','Phường 3, Q. 12, TP HCM','hautranmai@test.vn','0982384556','293182394','4297f44b13955235245b2497399d7a93',0,'2015-06-30 12:07:33'),('KH0008','Nguyễn Văn Trung','Phường 5, Q. 6, TP HCM','trungnguyenvan@test.vn','0909231848','293814734','4297f44b13955235245b2497399d7a93',0,'2015-06-30 12:07:33'),('KH0009','Trần Văn Linh','Phường 2, Q. 8, TP HCM','linhtranvan@test.vn','0929384445','293332842','4297f44b13955235245b2497399d7a93',0,'2015-06-30 12:07:33'),('KH000A','Trương Văn Khải','Phường 1, Q. 9, TP HCM','khaitruongvan@test.vn','0984283338','281394283','4297f44b13955235245b2497399d7a93',0,'2015-06-30 12:07:33'),('KH000B','Trần Vương','Phường 3, Q. 2, TP HCM','vuongtran@test.vn','0992837423','928371283','4297f44b13955235245b2497399d7a93',0,'2015-06-30 12:07:33'),('KH000C','Trần Đắc Anh','135 Nguyễn Hữu Cảnh, Bình Thạnh, Hồ Chí Minh, Việt Nam','trandacanh@test.vn','01212012012','1231231231','9c824046a4ab65f1d90c7fd54ba3ece9',0,'2015-07-23 14:56:15'),('KH000D','Vũ Tuấn Anh','67/3 Quốc lộ 1A, Hồ Chí Minh, Việt Nam','vutuananh@test.vn','0909765321','','02d55c11f1135b1c5a334ddd2ed6e8bc',0,'2015-07-23 14:57:18'),('KH000E','Lê Huy Bình','134 Đường Số 8, Hồ Chí Minh, Việt Nam','lehuybinh@test.vn','01234123456','','9d77f1bcd0e66efe53a95dd84096e654',0,'2015-07-23 14:59:34'),('KH000F','Hoàng Văn Cường','135 Dương Đình Hội, Phước Long B, Hồ Chí Minh, Việt Nam','hoangvancuong@test.vn','01234123457','','25eb9fd124577158a236dd94eb6a6877',0,'2015-07-23 15:00:18'),('KH000G','Dương Văn Dũng','1362 Kha Vạn Cân, Linh Trung, Hồ Chí Minh, Việt Nam','duongvandung@test.vn','01234123458','','fd9c4ab8c8ccc47187eb934c88d4aa75',0,'2015-07-23 15:00:43'),('KH000H','Nguyễn Anh Đức','137 Dương Đình Hội, Hồ Chí Minh, Việt Nam','nguyenanhduc@test.vn','01234123459','','364ab9c17108a13a8a0b7a0838bb31a2',0,'2015-07-23 15:01:04'),('KH000I','Lê Việt Hà','138 Bình Quới, Hồ Chí Minh, Việt Nam','levietha@test.vn','01234123460','','df01870f05ab411c18415f7879b06497',0,'2015-07-23 15:01:24'),('KH000J','Lưu Thị Thu Hiền','139 Trần Huy Liệu, Hồ Chí Minh, Việt Nam','lethithuhien@test.vn','01234123461','','06046c7465c1528332bd050f251e018c',0,'2015-07-23 15:01:48'),('KH000K','Đặng Việt Hưng','140 Lê Thánh Tôn, Bến Thành, Hồ Chí Minh, Việt Nam','dangviethung@test.vn','01234123462','','668cd65a39103cacb928301e29f0d2be',0,'2015-07-23 15:02:09'),('KH000L','Vũ Thị Thanh Huệ','141 Phan Đình Phùng, Lái Thiêu, Bình Dương, Việt Nam','vuthithanhhue@test.vn','01234123463','','893843a7d1a7188f18acec0f51346575',0,'2015-07-23 15:02:27'),('KH000M','Đinh Thị Lan Hương','142 Bùi Đình Tuý, Hồ Chí Minh, Việt Nam','dinhthilanhuong@test.vn','01234123464','','bfd924003d6fbebd1cfeb920384b76fc',0,'2015-07-23 15:02:46'),('KH000N','Trần Tuyết Lan','143 Hoàng Diệu 2, Linh Trung, Hồ Chí Minh, Việt Nam','trantuyetlan@test.vn','01234123465','','a147bbfdc43bdb09c2cfdee8544cf363',0,'2015-07-23 15:03:05'),('KH000O','Cao Thị Ly','145 Điện Biên Phủ, Bình Thạnh, Hồ Chí Minh, Việt Nam','caothily@test.vn','01234123466','','a28f981b9e88da1b0e0aaf600ced77ea',0,'2015-07-23 15:03:32'),('KH000P','Ngô Đức Nghị','146 Lê Văn Việt, Tăng Nhơn Phú B, Hồ Chí Minh, Việt Nam','ngoducnghi@test.vn','01234123467','','4d53fde309f7d831c20e81de6058eca0',0,'2015-07-23 15:03:54'),('KH000Q','Đào Thị Hồng Ngọc','146 Võ Thị Sáu, Hồ Chí Minh, Việt Nam','daothihongngoc@test.vn','01234123468','','c400b7a706871febf2f6ce1799a63fa6',0,'2015-07-23 15:04:13'),('KH000R','Đặng Tiến Nguyên','147 Quốc lộ 1K, Linh Xuân, Hồ Chí Minh, Việt Nam','dangtiennguyen@test.vn','01234012000','','f1bc9bd1688d866dc8edc15b88c0d30e',0,'2015-07-23 15:04:47'),('KH000S','Phan Hữu Phú','148 Tây Hòa, Hồ Chí Minh, Việt Nam','phanhuuphu@test.vn','01234012001','','4e9edf84533fdc46e04960c2e199944e',0,'2015-07-23 15:05:33'),('KH000T','Nguyễn Xuân Quỳnh','150 Nam Cao, Hồ Chí Minh, Việt Nam','nguyenxuanquynh@test.vn','01234012002','','e5cbea1962a2038863e864204fa69058',0,'2015-07-23 15:05:53'),('KH000U','Đặng Thị Thanh','151 Kha Vạn Cân, Hồ Chí Minh, Việt Nam','dangthithanh@test.vn','01234012003','','fe531553c2b0129ca525d58ab7b71f07',0,'2015-07-23 15:06:10'),('KH000V','Trần Khánh Thành','153 Quốc lộ 13, Hiệp Bình Phước, Hồ Chí Minh, Việt Nam','trankhanhthanh@test.vn','01234012004','','f191cda5cc5e221327701b3dd8824e01',0,'2015-07-23 15:06:30'),('KH000W','Lương Thị Thuận','160 Trần Hưng Đạo, Đông Hòa, Bình Dương, Việt Nam','luongthithuan@test.vn','01234012005','','90c40532cb6339ca40a32052f98f3323',0,'2015-07-23 15:06:48'),('KH000X','Trần Văn Thực','161 Đồng Khởi, Bến Nghé, Hồ Chí Minh, Việt Nam','tranvanthuc@test.vn','01234012006','','5a52d6c7f8901db336728c077d94a161',0,'2015-07-23 15:07:52'),('KH000Y','Đỗ Tiến Dũng','162 Điện Biên Phủ, Bình Thạnh, Hồ Chí Minh, Việt Nam','dotiendung@test.vn','01234012007','','cd887b389ddab96de6f39bdba1fdc2a9',0,'2015-07-23 15:08:08'),('KH000Z','Đỗ Tiến Sỹ','163 Gò Dưa, Hồ Chí Minh, Việt Nam','dotiensy@test.vn','01234012008','','57107803589f3d659b434825954527d4',0,'2015-07-23 15:08:26'),('KH0010','Nguyễn Hoàng Trường','164 Kha Vạn Cân, Hiệp Bình Chánh, Hồ Chí Minh, Việt Nam','nguyenhoangtruong@test.vn','01234012009','','c365352bdb0dfe8a118fcceeaad5968b',0,'2015-07-23 15:08:52'),('KH0011','Vũ Thị Hậu','165/6 Lê Thị Hoa, Bình Chiểu, Hồ Chí Minh, Việt Nam','vuthihau@test.vn','01234012010','','5d4b6f618bca9cc1a196d730a9dbbb0c',0,'2015-07-23 15:09:09'),('KH0012','Dương Phạm Huy Hùng','166 Trần Hưng Đạo, Đông Hòa, Bình Dương, Việt Nam','duongphamhuyhung@test.vn','01234012011','','61f28902284f944d2506b0e7f460c1e6',0,'2015-07-23 15:09:37'),('KH0013','Trần Văn Thông','167 Chu Văn An, Bình Thạnh, Hồ Chí Minh, Việt Nam','tranvanthong@test.vn','01234012012','','e693248c7accda724ab5abf49e6a6092',0,'2015-07-23 15:10:00'),('KH0014','Nguyễn Tuấn Hùng','169 Ngô Quyền, Hiệp Phú, Hồ Chí Minh, Việt Nam','nguyentuanhung@test.vn','01234012013','','fea7b483d3169c26116b1849dc9dc4c0',0,'2015-07-23 15:10:27');
/*!40000 ALTER TABLE `mic_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mic_new_card_request`
--

DROP TABLE IF EXISTS `mic_new_card_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mic_new_card_request` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `request_date` datetime NOT NULL,
  `resolve_date` datetime DEFAULT NULL,
  `note` varchar(2000) DEFAULT NULL,
  `old_card_instance_id` int(10) unsigned NOT NULL,
  `is_delivery_requested` int(1) unsigned NOT NULL DEFAULT '0',
  `is_paid` int(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_mic_new_card_request_mic_card1_idx` (`old_card_instance_id`),
  CONSTRAINT `fk_card_instance_id` FOREIGN KEY (`old_card_instance_id`) REFERENCES `mic_card_instance` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='New card request information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_new_card_request`
--

LOCK TABLES `mic_new_card_request` WRITE;
/*!40000 ALTER TABLE `mic_new_card_request` DISABLE KEYS */;
INSERT INTO `mic_new_card_request` VALUES (19,'2015-07-26 14:50:13','2015-07-26 14:50:34','23232',31,0,0);
/*!40000 ALTER TABLE `mic_new_card_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mic_notification`
--

DROP TABLE IF EXISTS `mic_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mic_notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(2000) NOT NULL,
  `receiver` varchar(250) NOT NULL,
  `method` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `extra_data` varchar(2000) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `resolved_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_notification`
--

LOCK TABLES `mic_notification` WRITE;
/*!40000 ALTER TABLE `mic_notification` DISABLE KEYS */;
INSERT INTO `mic_notification` VALUES (38,'Khách hàng Trương Văn Khải gửi yêu cầu bồi thường cho hợp đồng HD000B','^NV([0-9A-Z]{4,8})$',1,2,'BT0001','2015-07-26 14:48:58',NULL),(39,'Khách hàng Trần Quang Đại gửi yêu cầu cấp thẻ mới cho hợp đồng HD0003','^NV([0-9A-Z]{4,8})$',1,3,'19','2015-07-26 14:50:13',NULL);
/*!40000 ALTER TABLE `mic_notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mic_notification_read`
--

DROP TABLE IF EXISTS `mic_notification_read`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mic_notification_read` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(10) NOT NULL,
  `notification_id` int(11) NOT NULL,
  `is_read` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_user_code_is_read` (`user_code`,`notification_id`),
  KEY `fk_notification_id_idx` (`notification_id`),
  CONSTRAINT `fk_notification_id` FOREIGN KEY (`notification_id`) REFERENCES `mic_notification` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_notification_read`
--

LOCK TABLES `mic_notification_read` WRITE;
/*!40000 ALTER TABLE `mic_notification_read` DISABLE KEYS */;
INSERT INTO `mic_notification_read` VALUES (77,'NV0001',38,1),(78,'NV0001',39,1);
/*!40000 ALTER TABLE `mic_notification_read` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mic_payment`
--

DROP TABLE IF EXISTS `mic_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mic_payment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `paid_date` datetime NOT NULL,
  `payment_method` varchar(20) NOT NULL,
  `content` varchar(250) NOT NULL,
  `amount` float unsigned NOT NULL,
  `paypal_trans_id` varchar(50) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `expired_date` datetime DEFAULT NULL,
  `contract_code` varchar(10) NOT NULL,
  `receiver` varchar(10) DEFAULT NULL COMMENT 'staff code',
  PRIMARY KEY (`id`),
  KEY `fk_mic_payment_mic_contract1_idx` (`contract_code`),
  KEY `fk_mic_payment_mic_staff_idx` (`receiver`),
  CONSTRAINT `fk_mic_payment_mic_contract1` FOREIGN KEY (`contract_code`) REFERENCES `mic_contract` (`contract_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_mic_payment_mic_staff` FOREIGN KEY (`receiver`) REFERENCES `mic_staff` (`staff_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8 COMMENT='Payment information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_payment`
--

LOCK TABLES `mic_payment` WRITE;
/*!40000 ALTER TABLE `mic_payment` DISABLE KEYS */;
INSERT INTO `mic_payment` VALUES (105,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0001',66000,NULL,NULL,NULL,'HD0001','NV0001'),(106,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0002',80500,NULL,NULL,NULL,'HD0002','NV0001'),(107,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0003',66000,NULL,NULL,NULL,'HD0003','NV0001'),(108,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0004',80500,NULL,NULL,NULL,'HD0004','NV0001'),(109,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0005',319000,NULL,NULL,NULL,'HD0005','NV0001'),(110,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0006',60500,NULL,NULL,NULL,'HD0006','NV0001'),(111,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0007',60500,NULL,NULL,NULL,'HD0007','NV0001'),(112,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0008',80500,NULL,NULL,NULL,'HD0008','NV0001'),(113,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0009',60500,NULL,NULL,NULL,'HD0009','NV0001'),(114,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000A',80500,NULL,NULL,NULL,'HD000A','NV0001'),(115,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000B',319000,NULL,NULL,NULL,'HD000B','NV0001'),(116,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000C',80500,NULL,NULL,NULL,'HD000C','NV0001');
/*!40000 ALTER TABLE `mic_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mic_punishment`
--

DROP TABLE IF EXISTS `mic_punishment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mic_punishment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `created_date` datetime NOT NULL,
  `title` varchar(250) NOT NULL,
  `attachment` varchar(255) NOT NULL,
  `last_modified` datetime NOT NULL,
  `contract_code` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mic_punishment_mic_contract1_idx` (`contract_code`),
  CONSTRAINT `fk_mic_punishment_mic_contract1` FOREIGN KEY (`contract_code`) REFERENCES `mic_contract` (`contract_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='Punishment information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_punishment`
--

LOCK TABLES `mic_punishment` WRITE;
/*!40000 ALTER TABLE `mic_punishment` DISABLE KEYS */;
/*!40000 ALTER TABLE `mic_punishment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mic_staff`
--

DROP TABLE IF EXISTS `mic_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mic_staff` (
  `staff_code` varchar(10) NOT NULL,
  `password` varchar(32) NOT NULL,
  `name` varchar(80) NOT NULL,
  `email` varchar(250) NOT NULL,
  `phone` varchar(15) NOT NULL,
  PRIMARY KEY (`staff_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Staff information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_staff`
--

LOCK TABLES `mic_staff` WRITE;
/*!40000 ALTER TABLE `mic_staff` DISABLE KEYS */;
INSERT INTO `mic_staff` VALUES ('NV0001','4297f44b13955235245b2497399d7a93','Nguyễn Chí Kha','khanc@gmail.com','0912025544'),('NV0002','4297f44b13955235245b2497399d7a93','Lê Xuân Tiến','globavina.hrd@gmail.com','0983660909'),('NV0003','4297f44b13955235245b2497399d7a93','Trương Đắc Huy Hoàng','hoangtdh@gmail.com','0912116730'),('NV0004','4297f44b13955235245b2497399d7a93','Vũ Nhật Minh','minhnm@gmail.com','0915250309'),('NV0005','4297f44b13955235245b2497399d7a93','Lê Duy Thanh','tmnthanh0125@yahoo.com','0913002087'),('NV0006','4297f44b13955235245b2497399d7a93','Trần Đình Quang','quangtd@outlook.com','0912632096'),('NV0007','4297f44b13955235245b2497399d7a93','Lê Xuân Tiến','conan_3137@yahoo.com','0940984464'),('NV0008','4297f44b13955235245b2497399d7a93','Võ Thị Minh Châu','chauvtm@gmail.com','0915020617');
/*!40000 ALTER TABLE `mic_staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-26 14:57:16
