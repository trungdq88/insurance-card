CREATE DATABASE  IF NOT EXISTS `mic_data` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mic_data`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: mic_data
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
INSERT INTO `increments` VALUES ('BT',2),('HD',46),('KH',40),('NV',9);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_business_rules`
--

LOCK TABLES `mic_business_rules` WRITE;
/*!40000 ALTER TABLE `mic_business_rules` DISABLE KEYS */;
INSERT INTO `mic_business_rules` VALUES (1,'2015-07-26 13:22:39',7,1825,1,12,7,30,7,30,7,15,7,3,50000,10000,30,60),(2,'2015-08-19 08:52:38',7,1825,1,12,7,30,7,30,7,15,7,3,10000,5000,30,60);
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
INSERT INTO `mic_card` VALUES ('0VGYTEBN5T9',1),('1029375283',1),('232498734432',1),('2874916285723',1),('347298376510',1),('34875720103',1),('3683457892',1),('382719658223',1),('384792387651',1),('3857629834326',1),('41M3DHT6IU4',1),('4871928491232',1),('5032384203434',1),('543534534534',1),('546346576443',1),('589271982732',1),('634333423433',1),('654547445432',1),('654765465333',1),('765865543756',1),('8049283172874',1),('8374659283423',1),('845545434346',1),('85287912372',1),('8E7ONFNC86B',1),('8T583FM6NFM',1),('9021748528325',1),('90284717292325',1),('940382734892',1),('96IFZNEL5TO',1),('9VQILYB18HD',1),('AB74090914',1),('AB74871234',1),('AB74873323',1),('AB74873324',1),('AB74934824',1),('AB92121133',1),('AB9232832',1),('AB92331133',1),('AB92331211',1),('AB92331234',1),('AB92873233',1),('C4VH4H1XHWJ',1),('E1D6QGI8FMJ',1),('EBG9TSSTWS4',1),('GVCV9C49CFW',1),('J21X60K9DFO',1),('J6NEGVAOB4R',1),('T96MVN991I3',1),('YKL3FFIPHJS',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 COMMENT='Card information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_card_instance`
--

LOCK TABLES `mic_card_instance` WRITE;
/*!40000 ALTER TABLE `mic_card_instance` DISABLE KEYS */;
INSERT INTO `mic_card_instance` VALUES (22,'3683457892','2015-07-26 14:16:16',NULL,'HD000B',NULL,'KH000A'),(23,'1029375283','2015-07-26 14:16:25',NULL,'HD000A',NULL,'KH0009'),(24,'85287912372','2015-07-26 14:16:33',NULL,'HD0009',NULL,'KH0008'),(25,'384792387651','2015-07-26 14:16:41',NULL,'HD0008',NULL,'KH0007'),(26,'34875720103','2015-07-26 14:16:51','2015-07-26 14:18:27','HD0007',NULL,'KH0006'),(27,'940382734892','2015-07-26 14:16:59','2015-07-26 14:18:37','HD0006',NULL,'KH0005'),(28,'5032384203434','2015-07-26 14:17:10',NULL,'HD0005',NULL,'KH0004'),(29,'8374659283423','2015-07-26 14:17:18','2015-07-26 14:17:44','HD0004',NULL,'KH0003'),(30,'589271982732','2015-07-26 14:17:44',NULL,'HD0004',NULL,'KH0003'),(31,'90284717292325','2015-07-26 14:17:57','2015-07-26 14:50:13','HD0003',NULL,'KH0002'),(32,'8049283172874','2015-07-26 14:18:08','2015-07-26 14:18:47','HD0002',NULL,'KH0001'),(33,'3857629834326','2015-07-26 14:18:18',NULL,'HD0001',NULL,'KH0001'),(34,'2874916285723','2015-07-26 14:18:27',NULL,'HD0007',NULL,'KH0006'),(35,'347298376510','2015-07-26 14:18:37','2015-08-19 09:02:45','HD0006',NULL,'KH0005'),(36,'9021748528325','2015-07-26 14:18:48',NULL,'HD0002',NULL,'KH0001'),(37,'4871928491232','2015-07-26 14:40:47',NULL,'HD000C',NULL,'KH0004'),(38,'382719658223','2015-07-26 14:50:34',NULL,'HD0003',19,'KH0002'),(39,'543534534534','2015-07-25 19:32:37',NULL,'HD000D',NULL,'KH000B'),(40,'765865543756','2015-07-25 19:35:58',NULL,'HD000E',NULL,'KH000C'),(41,'654547445432','2015-07-25 19:37:52',NULL,'HD000F',NULL,'KH000F'),(42,'654765465333','2015-07-25 19:39:46',NULL,'HD000G',NULL,'KH000G'),(43,'232498734432','2015-07-25 19:40:57',NULL,'HD000H',NULL,'KH000H'),(44,'845545434346','2015-07-25 19:42:23',NULL,'HD000I',NULL,'KH000I'),(45,'546346576443','2015-07-25 19:43:41',NULL,'HD000J',NULL,'KH000J'),(46,'634333423433','2015-07-25 19:45:30',NULL,'HD000K',NULL,'KH000K'),(47,'AB9232832','2014-06-28 22:11:46','2015-08-19 09:00:46','HD000L',NULL,'KH000U'),(48,'AB92331234','2014-06-28 22:14:38',NULL,'HD000M',NULL,'KH000T'),(49,'AB92331211','2014-06-28 22:19:09',NULL,'HD000N',NULL,'KH000T'),(50,'AB92331133','2014-06-28 22:21:47','2015-08-19 09:02:20','HD000O',NULL,'KH000S'),(51,'AB92121133','2014-09-24 22:27:28',NULL,'HD000P',NULL,'KH000R'),(52,'AB92873233','2014-09-24 22:29:13',NULL,'HD000Q',NULL,'KH000Q'),(53,'AB74873323','2014-09-24 22:31:59','2014-09-24 22:32:56','HD000R',NULL,'KH000P'),(54,'AB74873324','2014-09-24 22:32:56',NULL,'HD000R',NULL,'KH000P'),(55,'AB74871234','2014-09-24 22:35:40',NULL,'HD000S',NULL,'KH000O'),(56,'AB74934824','2014-09-24 22:37:34',NULL,'HD000T',NULL,'KH000N'),(57,'AB74090914','2014-09-24 22:39:27',NULL,'HD000U',NULL,'KH000M'),(58,'J6NEGVAOB4R','2015-07-26 23:41:35',NULL,'HD000W',NULL,'KH000V'),(59,'YKL3FFIPHJS','2015-07-26 23:41:50',NULL,'HD000X',NULL,'KH000W'),(60,'GVCV9C49CFW','2015-07-26 23:42:01',NULL,'HD000Y',NULL,'KH000X'),(61,'E1D6QGI8FMJ','2015-07-26 23:42:17',NULL,'HD000Z',NULL,'KH000Y'),(62,'EBG9TSSTWS4','2015-07-26 23:42:32',NULL,'HD0010',NULL,'KH000Z'),(63,'41M3DHT6IU4','2015-07-26 23:42:41',NULL,'HD0011',NULL,'KH0010'),(64,'J21X60K9DFO','2015-07-26 23:42:48',NULL,'HD0012',NULL,'KH0011'),(65,'0VGYTEBN5T9','2015-07-26 23:42:55',NULL,'HD0013',NULL,'KH0012'),(66,'C4VH4H1XHWJ','2015-07-26 23:43:02',NULL,'HD0014',NULL,'KH0013'),(67,'96IFZNEL5TO','2015-07-26 23:43:09',NULL,'HD0015',NULL,'KH0014'),(68,'T96MVN991I3','2015-07-26 23:43:16','2015-07-26 23:44:19','HD0016',NULL,'KH0012'),(69,'9VQILYB18HD','2015-07-26 23:43:22',NULL,'HD0017',NULL,'KH0011'),(70,'8E7ONFNC86B','2015-07-26 23:43:35',NULL,'HD0018',NULL,'KH0012'),(71,'8T583FM6NFM','2015-08-19 09:00:54',NULL,'HD000L',20,'KH000U');
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
  `driver_name` varchar(80) DEFAULT NULL,
  `license_number` varchar(15) DEFAULT NULL,
  `license_type` varchar(10) DEFAULT NULL,
  `driver_phone` varchar(15) DEFAULT NULL,
  `vehicle_capacity` varchar(20) DEFAULT NULL,
  `driver_address` varchar(250) DEFAULT NULL,
  `plate` varchar(15) DEFAULT NULL,
  `accident_date` datetime DEFAULT NULL,
  `accident_place` varchar(250) DEFAULT NULL,
  `control_department` varchar(250) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `human_damage` varchar(2000) DEFAULT NULL,
  `asset_damage` varchar(2000) DEFAULT NULL,
  `observer` varchar(80) DEFAULT NULL,
  `observer_address` varchar(250) DEFAULT NULL,
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
INSERT INTO `mic_compensation` VALUES ('BT0001','Trương Văn Khải','6232342331','A1','0984283338','2','Phường 1, Q. 9, TP HCM','78A4-3624','2015-07-26 00:00:00','347 Lê Văn Thọ, Gò Vấp, Ho Chi Minh, Vietnam','CSGT Quận 12','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce id diam non magna faucibus vulputate at a nibh. In vel nunc sodales, aliquam lacus ut, imperdiet nisl. Morbi non est tellus. Donec risus eros, tristique quis ultricies quis, facilisis ac turpis. Ut dignissim gravida lobortis.','Fusce efficitur nisl eros, in consequat orci auctor id. Nullam rhoncus maximus condimentum. Nulla in felis in quam malesuada posuere in sed ex. Vestibulum non aliquet ligula. Maecenas rhoncus orci eu mi elementum, ut accumsan arcu congue.','Cras ac pretium neque, non tristique nisi. Donec libero ipsum, ornare a mollis id, finibus nec sem. Phasellus turpis mauris, imperdiet vitae viverra vel, auctor eget tellus. Cras maximus iaculis tortor. Pellentesque blandit accumsan vehicula. Curabitur ac sem semper, imperdiet libero ultricies, accumsan diam.','Đặng Thu Thảo','96 Nguyễn Thị Minh Khai, Ho Chi Minh, Vietnam','12 triệu đồng','https://www.filepicker.io/api/file/AAoiGLrRbaVQ2JA0shDv','2015-07-26 00:00:00','2015-07-26 00:00:00','Đồng ý bồi thường','Đã xác nhận và bồi thường theo biên bản số BB73742','2015-07-26 14:49:21','HD000B'),('BT0002','Trương Văn Khải','656565434334','A1','0984283338','2','Phường 1, Q. 9, TP HCM','78A4-3624','2015-07-25 00:00:00','Tân Bình, Ho Chi Minh, Vietnam','Đội CĐCS TPHCM','hai xe chay nguoc chieu va quet','khong co','2 xe hu hong ','Huyền My','An Phú, Ho Chi Minh City, Ho Chi Minh, Vietnam','','','2015-07-25 00:00:00',NULL,NULL,NULL,'2015-07-25 19:47:35','HD000B');
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
INSERT INTO `mic_contract` VALUES ('HD0001','2015-07-26 13:57:42','2015-03-26 00:00:00','2016-03-26 00:00:00','Ready',66000,'93H3-1232','Honda','X12','Hai bánh','Đỏ','626323','124232','110',2000,50,2,NULL,NULL,NULL,'2015-07-26 13:57:42','NV0001',2,'KH0001',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0002','2015-07-26 13:59:31','2015-04-27 00:00:00','2016-04-27 00:00:00','Ready',80500,'56E4-5953','Yamaha','H66','Hai bánh','Xanh','845624','251263','150',2002,56,2,NULL,NULL,NULL,'2015-07-26 13:59:31','NV0001',3,'KH0001',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0003','2015-07-26 14:02:17','2014-12-12 00:00:00','2015-12-12 00:00:00','Ready',66000,'86H4-3853','Yamaha','G33','Hai bánh','22','3562343','574564','130',2001,62,2,NULL,NULL,NULL,'2015-07-26 14:02:17','NV0001',2,'KH0002',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0004','2015-07-26 14:03:54','2015-02-06 00:00:00','2016-02-06 00:00:00','Request cancel',80500,'33H4-3735','Honda','A3','Hai bánh','Vàng','3462343','46347245','110',2003,53,2,'2015-08-19 00:00:00','Xe cơ giới bị mất được cơ quan công an xác nhận',NULL,'2015-08-19 09:05:52','NV0001',3,'KH0003',NULL,NULL,'Khách hàng đã gửi yêu cầu hủy hợp đồng này trước đó'),('HD0005','2015-07-26 14:05:01','2015-04-30 00:00:00','2016-04-30 00:00:00','Ready',319000,'84F3-5638','Yamaha','B31','Hai bánh','Đỏ','834623','7248345','120',2005,55,2,NULL,NULL,NULL,'2015-07-26 14:05:01','NV0001',5,'KH0004',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0006','2015-07-26 14:06:22','2014-10-23 00:00:00','2016-10-23 00:00:00','No card',61000,'76B3-7654','Honda','J81','Hai bánh','Xanh','73451373','6283453','120',2007,58,2,NULL,NULL,NULL,'2015-08-19 09:02:45','NV0001',4,'KH0005',NULL,NULL,'Một nhân viên đã gia hạn cho hợp đồng này trước đó'),('HD0007','2015-07-26 14:07:34','2015-01-01 00:00:00','2016-01-01 00:00:00','Ready',60500,'47K6-5824','Honda','Y66','Hai bánh','Xanh','3472345','7453346','120',2003,56,2,NULL,NULL,NULL,'2015-07-26 14:07:34','NV0001',4,'KH0006',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0008','2015-07-26 14:08:56','2015-03-01 00:00:00','2016-03-01 00:00:00','Ready',80500,'43B6-0678','Honda','M633','Hai bánh','Hồng','8650534','7456236','130',2003,56,2,NULL,NULL,NULL,'2015-07-26 14:08:56','NV0001',3,'KH0007',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0009','2015-07-26 14:12:05','2015-05-22 00:00:00','2016-05-22 00:00:00','Ready',60500,'30H8-1823','Yamaha','L231','Hai bánh','Tím','246723434','6247345','150',2003,54,2,NULL,NULL,NULL,'2015-07-26 14:12:05','NV0001',4,'KH0008',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD000A','2015-07-26 14:13:46','2015-02-11 00:00:00','2016-02-11 00:00:00','Request cancel',80500,'67Y3-3462','Honda','T55','Hai bánh','Đỏ','8354562','7385456','150',2003,49,2,'2015-08-19 00:00:00','Xe cơ giới hỏng không sử dụng được hoặc bị phá huỷ do tai nạn giao thông được cơ quan công an xác nhận',NULL,'2015-08-19 09:04:31','NV0001',3,'KH0009',NULL,NULL,'Khách hàng đã gửi yêu cầu hủy hợp đồng này trước đó'),('HD000B','2015-07-26 14:15:08','2014-08-12 00:00:00','2015-08-12 00:00:00','Expired',319000,'78A4-3624','Yamaha','R53','Hai bánh','Trắng','60586423','61732234','120',2003,50,2,NULL,NULL,NULL,'2015-08-19 08:50:52','NV0001',5,'KH000A',NULL,NULL,'Hợp đồng này đã hết hạn'),('HD000C','2015-07-26 14:40:29','2015-02-12 00:00:00','2016-02-12 00:00:00','Ready',80500,'67H3-1231','Honda','T64','Hai bánh','Hồng','236123423','73584563','120',2003,52,2,NULL,NULL,NULL,'2015-07-26 14:40:29','NV0001',3,'KH0004',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD000D','2015-07-25 19:32:37','2015-07-25 00:00:00','2016-07-25 00:00:00','Ready',86000,'53U8-12343','Honda','Elizabert','Hai Bánh','trắng','5476656523','3231246523','110',NULL,NULL,NULL,NULL,NULL,NULL,'2015-07-25 19:32:37','NV0001',1,'KH000B',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD000E','2015-07-25 19:35:58','2015-07-25 00:00:00','2016-07-25 00:00:00','Ready',80500,'54Y7-54323','Honda','','','trắng','3654356547','342343523','110',NULL,NULL,NULL,NULL,NULL,NULL,'2015-07-25 19:35:58','NV0001',3,'KH000C',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD000F','2015-07-25 19:37:52','2015-07-25 00:00:00','2016-07-25 00:00:00','Ready',60500,'78Y9-63894','SuZuKi','','','Đỏ','4234325463','4325574674','110',NULL,NULL,NULL,NULL,NULL,NULL,'2015-07-25 19:37:52','NV0001',4,'KH000F',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD000G','2015-07-25 19:39:46','2015-07-25 00:00:00','2016-07-25 00:00:00','Ready',80500,'67Y9-23221','Yamaha','','','Xanh','6754746744','767676767','110',NULL,NULL,NULL,NULL,NULL,NULL,'2015-07-25 19:39:46','NV0001',3,'KH000G',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD000H','2015-07-25 19:40:57','2015-07-25 00:00:00','2016-07-25 00:00:00','Ready',80500,'67H6-35487','Yamaha','','','','767546553','343242355','110',NULL,NULL,NULL,NULL,NULL,NULL,'2015-07-25 19:40:57','NV0001',3,'KH000H',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD000I','2015-07-25 19:42:23','2015-07-25 00:00:00','2016-07-25 00:00:00','Ready',319000,'67G8-35363','Honda','','ba bánh','Đỏ','4343243343','54363543532','110',2006,986,NULL,NULL,NULL,NULL,'2015-07-25 19:42:23','NV0001',5,'KH000I',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD000J','2015-07-25 19:43:41','2015-07-25 00:00:00','2016-07-25 00:00:00','Ready',66000,'78Y9-56345','Honda','Vision','Hai Bánh','Xanh','67576567','747549754','100',2013,567,2,NULL,NULL,NULL,'2015-07-25 19:43:41','NV0001',2,'KH000J',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD000K','2015-07-25 19:45:30','2015-07-25 00:00:00','2016-07-25 00:00:00','Ready',86000,'56H9','Honda','Dream','Hai Bánh','Nho','5453465364','4324234234','100',2005,465,2,NULL,NULL,NULL,'2015-07-25 19:45:30','NV0001',1,'KH000K',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD000L','2014-06-28 22:08:14','2014-06-28 00:00:00','2016-08-19 00:00:00','Ready',86000,'52H3 - 8329','Honda','SHi','Hai bánh','Nâu','SM19000421','SK19000421','150',2013,158,2,NULL,NULL,NULL,'2015-08-19 09:00:45','NV0001',1,'KH000U',NULL,NULL,'Một nhân viên đã gia hạn cho hợp đồng này trước đó'),('HD000M','2014-06-28 22:14:05','2014-09-13 00:00:00','2015-09-13 00:00:00','Ready',86000,'59H1 - 2938','Suzuki','Smash','Hai bánh','Xanh','SM19002314','SK19002314','110',2006,102,2,NULL,NULL,NULL,'2014-09-24 22:24:52','NV0001',1,'KH000T',NULL,NULL,'Trạng thái của hợp đồng này đã bị thay đổi trước đó'),('HD000N','2014-06-28 22:18:57','2014-06-28 00:00:00','2015-06-28 00:00:00','Expired',80500,'59HA - 9110','Suzuki','Max','Hai bánh','Đỏ','SM19002123','SK19002123','50',2000,68,2,NULL,NULL,NULL,'2015-07-26 22:46:39','NV0001',3,'KH000T',NULL,NULL,'Hợp đồng này đã hết hạn'),('HD000O','2014-06-28 22:21:28','2014-08-28 00:00:00','2016-08-28 00:00:00','No card',86000,'52Y1 - 89012','Honda','CBR1100XX','Hai bánh','Đỏ','SM18000001','SK18000001','1100',2014,280,2,NULL,NULL,NULL,'2015-08-19 09:02:20','NV0001',1,'KH000S',NULL,NULL,'Một nhân viên đã gia hạn cho hợp đồng này trước đó'),('HD000P','2014-09-24 22:27:08','2014-09-24 00:00:00','2016-09-24 00:00:00','Ready',86000,'52U1 - 38291','Suzuki','Hayabusa','Hai bánh','Đen','SM18000004','SK18000004','1340',2014,308,2,NULL,NULL,NULL,'2015-08-19 08:54:52','NV0001',1,'KH000R',NULL,NULL,'Một nhân viên đã gia hạn cho hợp đồng này trước đó'),('HD000Q','2014-09-24 22:28:59','2014-09-24 00:00:00','2015-09-24 00:00:00','Ready',80500,'60AB - 1234','Honda','Club','Hai bánh','Xanh dương','SM19001212','SK19001212','49',1998,68,2,NULL,NULL,NULL,'2014-09-24 22:28:59','NV0001',3,'KH000Q',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD000R','2014-09-24 22:31:37','2014-09-24 00:00:00','2015-07-24 00:00:00','Expired',71391.8,'60Y2 - 0602','Honda','Wave','Hai bánh','Xanh dương','SM19230231','SK19230231','100',2004,98,2,NULL,NULL,NULL,'2015-07-26 22:46:40','NV0001',1,'KH000P',NULL,NULL,'Hợp đồng này đã hết hạn'),('HD000S','2014-09-24 22:35:30','2014-09-24 00:00:00','2015-09-24 00:00:00','Ready',80500,'72DA - 2312','Honda','Little Cub','Hai bánh','Trắng','SM12341482','SK12341482','50',2008,59,2,NULL,NULL,NULL,'2014-09-24 22:35:30','NV0001',3,'KH000O',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD000T','2014-09-24 22:37:21','2014-09-24 00:00:00','2016-09-24 00:00:00','Ready',319000,'52A1 - 12345','Honda','Avenger','Ba bánh','Xanh','SM18012345','SK18012345','1000',2010,350,2,NULL,NULL,NULL,'2015-08-19 08:54:37','NV0001',5,'KH000N',NULL,NULL,'Một nhân viên đã gia hạn cho hợp đồng này trước đó'),('HD000U','2014-09-24 22:39:14','2014-09-26 00:00:00','2016-09-26 00:00:00','Ready',81000,'60FA - 0369','Honda','Cub','Hai bánh','Xanh','SM19000123','SK19000123','50',1998,58,2,NULL,NULL,NULL,'2015-08-19 08:54:25','NV0001',3,'KH000M',NULL,NULL,'Một nhân viên đã gia hạn cho hợp đồng này trước đó'),('HD000V','2014-10-24 22:42:50','2014-10-24 00:00:00','2014-10-24 00:00:00','Cancelled',86000,'51A1 - 8239','Honda','Air Blade','Hai bÃÂ¡nh','ÃÂÃ¡Â»Â','SM19231923','SK19231923','135',2010,98,2,'2014-10-31 00:00:00','Quá ngày thanh toán hợp đồng',NULL,'2014-10-31 22:44:29',NULL,1,'KH000L',NULL,NULL,'Hợp đồng này đã bị hủy vì quá hạn thanh toán'),('HD000W','2015-07-26 23:13:25','2015-07-26 00:00:00','2016-07-26 00:00:00','Ready',86000,'56Y8-29281','Honda','Air Blade','Hai bánh','Trắng','S90234838','Y83938493','108',2012,130,2,NULL,NULL,NULL,'2015-07-26 23:13:25','NV0001',1,'KH000V',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD000X','2015-07-26 23:15:05','2015-07-26 00:00:00','2015-12-26 00:00:00','Ready',133718,'51S3-44393','Harley Davidson','883 Roadster','Hai bánh','Đen-Bạc','129DF302023','SI3203924','883',2015,220,2,NULL,NULL,NULL,'2015-07-26 23:15:05','NV0001',5,'KH000W',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD000Y','2015-07-26 23:18:02','2015-07-19 00:00:00','2016-05-19 00:00:00','Ready',55150.7,'59S1-23442','Yamaha','Exciter','2 bánh','Xanh-Trắng','CX20002438','PO032034','125',2015,130,2,NULL,NULL,NULL,'2015-07-26 23:18:02','NV0001',2,'KH000X',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD000Z','2015-07-26 23:21:22','2015-07-26 00:00:00','2016-05-26 00:00:00','Ready',266562,'51S1-43212','BMW','S1000RR','Hai bánh','Đen-Trắng','XS8233292','OL3030288','250',2013,180,2,NULL,NULL,NULL,'2015-07-26 23:21:22','NV0001',5,'KH000Y',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0010','2015-07-26 23:23:29','2015-07-26 00:00:00','2016-07-26 00:00:00','Ready',86000,'55Y1-54323','Honda','Click','2 bánh','Hồng','FA349234','DFK323004','108',2012,120,2,NULL,NULL,NULL,'2015-07-26 23:23:29','NV0001',1,'KH000Z',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0011','2014-08-16 13:28:43','2014-08-16 00:00:00','2015-08-16 00:00:00','Expired',80500,'47K1-24293','Honda','Cub','Hai bánh','Xanh-Trắng','049KL49009','29032DFL203','50',1999,NULL,NULL,NULL,NULL,NULL,'2015-08-19 08:50:52','NV0001',3,'KH0010',NULL,NULL,'Hợp đồng này đã hết hạn'),('HD0012','2015-07-26 23:26:27','2015-09-26 00:00:00','2016-07-26 00:00:00','Pending',71627.4,'77Y7-58929','Yamaha','Nozze','2 bánh','Tím','F3E0903209','2OO49302','108',2014,122,2,NULL,NULL,NULL,'2015-07-26 23:26:27','NV0001',1,'KH0011',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0013','2014-09-16 23:30:31','2014-09-16 00:00:00','2016-09-16 00:00:00','Ready',61000,'49Z8-49299','Honda','Scoopy Crea','2 bánh','Trắng-Hồng','HH48339223','LK34902003','50',2012,70,2,NULL,NULL,NULL,'2015-08-19 08:53:07','NV0001',4,'KH0012',NULL,NULL,'Một nhân viên đã gia hạn cho hợp đồng này trước đó'),('HD0014','2015-07-26 23:31:41','2015-08-26 00:00:00','2016-04-26 00:00:00','Pending',57490.4,'56Y8-29283','Honda','','','Tím','CSSFE02438','S83ZF36893','125',NULL,NULL,NULL,NULL,NULL,NULL,'2015-07-26 23:31:41','NV0001',1,'KH0013',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0015','2014-07-26 23:33:07','2015-07-26 00:00:00','2016-07-26 00:00:00','Ready',319000,'79S9-48399','Ducati','','','','30248D8238','90923D92','300',NULL,NULL,NULL,NULL,NULL,NULL,'2015-07-26 23:33:07','NV0001',5,'KH0014',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0016','2015-07-26 23:35:21','2015-07-26 00:00:00','2016-07-26 00:00:00','Cancelled',66000,'59Y1-22462','Suzuki','Impulse','Hai bánh','Đen-Bạc','S9D3434838','AS4390230','125',2014,133,2,'2015-07-26 23:44:18','Xe bị mất cắp','Đã xác nhận bởi cơ quan công an phường 12, quận Tân Bình. Biên bản số 212945SZZS','2015-07-26 23:44:18','NV0001',2,'KH0012',NULL,NULL,'Một nhân viên đã hủy hợp đồng này trước đó'),('HD0017','2015-07-26 23:37:02','2015-07-26 00:00:00','2016-07-26 00:00:00','Ready',86000,'48S1-39303','Yamaha','Ultimo','Hai bánh','Tím','FO23RFJ','F03OFDIO3','108',NULL,NULL,NULL,NULL,NULL,NULL,'2015-07-26 23:37:02','NV0001',1,'KH0011',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD0018','2014-07-26 23:40:21','2014-07-26 00:00:00','2016-07-26 00:00:00','Ready',86000,'48S1-23543','Honda','Air Blade','Hai bánh','Đen-Bạc','DS2334433','3425333D2','125',NULL,127,2,NULL,NULL,NULL,'2015-07-26 23:40:21','NV0001',1,'KH0012',NULL,NULL,'Một nhân viên đã tạo mới hợp đồng này'),('HD001A','2016-08-21 08:59:15','2016-08-25 00:00:00','2016-08-25 00:00:00','Pending',66000,'47K1-4896','Honda','Air Blade','Hai bánh','Đen-Trắng','7I6R65TFOQM','U60QU5FFUIF','108',2012,120,2,NULL,NULL,NULL,'2016-08-25 08:59:15',NULL,2,'KH0008',NULL,NULL,'Khách hàng đã đăng ký mới hợp đồng này');
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
INSERT INTO `mic_contract_type` VALUES (1,'Xe trên 50cc có BH cho người trên xe','Xe trên 50cc có BH cho người trên xe',86000,1),(2,'Xe trên 50cc không có BH cho người trên xe','Xe trên 50cc không có BH cho người trên xe',66000,1),(3,'Xe từ 50cc trở xuống có BH cho người trên xe','Xe dưới 50cc có BH cho người trên xe',80500,1),(4,'Xe từ 50cc trở xuống không có BH cho người trên xe','Xe dưới 50cc không có BH cho người trên xe',60500,1),(5,'Xe mô tô ba bánh, xe gắn máy và các loại xe tương tự','Bảo hiểm dành cho xe ba bánh, xe gắn máy v.v',319000,1);
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
INSERT INTO `mic_customer` VALUES ('KH0001','Nguyễn Văn Hoàng','Phường Tân Chánh Hiệp, Q 12, TP HCM','hoangnv1294@test.vn','123123123','123123123','4297f44b13955235245b2497399d7a93',1,'2015-07-03 16:01:08'),('KH0002','Trần Quang Đại','Phường Đa Kao, Q 1, TP HCM','daitranquang@test.vn','0903012842','273846158','4297f44b13955235245b2497399d7a93',1,'2015-07-26 14:49:56'),('KH0003','Nguyễn Thị Trang','Phường 7, Q 5, TP HCM','trangnguyenthi@test.vn','0987302918','283718237','4297f44b13955235245b2497399d7a93',1,'2015-06-30 12:07:33'),('KH0004','Cao Văn Trường','Phường 3, Q 4, TPHCM','truongcaovan@test.vn','0935823754','827461283','4297f44b13955235245b2497399d7a93',1,'2015-06-30 12:07:33'),('KH0005','Nguyễn Hữu Cảnh','Phường 3, Q. Phú Nhuận, TP HCM','canhnguyenhuu@test.vn','0988263748','837461523','4297f44b13955235245b2497399d7a93',1,'2015-06-30 12:07:33'),('KH0006','Lý Phương','Phường 1, Q. Thủ Đức, TP HCM','phuongly@test.vn','0928374185','928316237','4297f44b13955235245b2497399d7a93',1,'2015-06-30 12:07:33'),('KH0007','Mai Trần Hậu','Phường 3, Q. 12, TP HCM','hautranmai@test.vn','0982384556','293182394','4297f44b13955235245b2497399d7a93',1,'2015-06-30 12:07:33'),('KH0008','Nguyễn Văn Trung','Phường 5, Q. 6, TP HCM','trungnguyenvan@test.vn','0909231848','293814734','4297f44b13955235245b2497399d7a93',1,'2015-06-30 12:07:33'),('KH0009','Trần Văn Linh','Phường 2, Q. 8, TP HCM','linhtranvan@test.vn','0929384445','293332842','4297f44b13955235245b2497399d7a93',1,'2015-06-30 12:07:33'),('KH000A','Trương Văn Khải','Phường 1, Q. 9, TP HCM','khaitruongvan@test.vn','0984283338','281394283','4297f44b13955235245b2497399d7a93',1,'2015-06-30 12:07:33'),('KH000B','Trần Vương','Phường 3, Q. 2, TP HCM','vuongtran@test.vn','0992837423','928371283','4297f44b13955235245b2497399d7a93',1,'2015-06-30 12:07:33'),('KH000C','Trần Đắc Anh','135 Nguyễn Hữu Cảnh, Bình Thạnh, Hồ Chí Minh, Việt Nam','trandacanh@test.vn','01212012012','1231231231','4297f44b13955235245b2497399d7a93',1,'2015-07-23 14:56:15'),('KH000D','Vũ Tuấn Anh','67/3 Quốc lộ 1A, Hồ Chí Minh, Việt Nam','vutuananh@test.vn','0909765321','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 14:57:18'),('KH000E','Lê Huy Bình','134 Đường Số 8, Hồ Chí Minh, Việt Nam','lehuybinh@test.vn','01234123456','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 14:59:34'),('KH000F','Hoàng Văn Cường','135 Dương Đình Hội, Phước Long B, Hồ Chí Minh, Việt Nam','hoangvancuong@test.vn','01234123457','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:00:18'),('KH000G','Dương Văn Dũng','1362 Kha Vạn Cân, Linh Trung, Hồ Chí Minh, Việt Nam','duongvandung@test.vn','01234123458','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:00:43'),('KH000H','Nguyễn Anh Đức','137 Dương Đình Hội, Hồ Chí Minh, Việt Nam','nguyenanhduc@test.vn','01234123459','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:01:04'),('KH000I','Lê Việt Hà','138 Bình Quới, Hồ Chí Minh, Việt Nam','levietha@test.vn','01234123460','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:01:24'),('KH000J','Lưu Thị Thu Hiền','139 Trần Huy Liệu, Hồ Chí Minh, Việt Nam','lethithuhien@test.vn','01234123461','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:01:48'),('KH000K','Đặng Việt Hưng','140 Lê Thánh Tôn, Bến Thành, Hồ Chí Minh, Việt Nam','dangviethung@test.vn','01234123462','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:02:09'),('KH000L','Vũ Thị Thanh Huệ','141 Phan Đình Phùng, Lái Thiêu, Bình Dương, Việt Nam','vuthithanhhue@test.vn','01234123463','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:02:27'),('KH000M','Đinh Thị Lan Hương','142 Bùi Đình Tuý, Hồ Chí Minh, Việt Nam','dinhthilanhuong@test.vn','01234123464','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:02:46'),('KH000N','Trần Tuyết Lan','143 Hoàng Diệu 2, Linh Trung, Hồ Chí Minh, Việt Nam','trantuyetlan@test.vn','01234123465','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:03:05'),('KH000O','Cao Thị Ly','145 Điện Biên Phủ, Bình Thạnh, Hồ Chí Minh, Việt Nam','caothily@test.vn','01234123466','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:03:32'),('KH000P','Ngô Đức Nghị','146 Lê Văn Việt, Tăng Nhơn Phú B, Hồ Chí Minh, Việt Nam','ngoducnghi@test.vn','01234123467','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:03:54'),('KH000Q','Đào Thị Hồng Ngọc','146 Võ Thị Sáu, Hồ Chí Minh, Việt Nam','daothihongngoc@test.vn','01234123468','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:04:13'),('KH000R','Đặng Tiến Nguyên','147 Quốc lộ 1K, Linh Xuân, Hồ Chí Minh, Việt Nam','dangtiennguyen@test.vn','01234012000','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:04:47'),('KH000S','Phan Hữu Phú','148 Tây Hòa, Hồ Chí Minh, Việt Nam','phanhuuphu@test.vn','01234012001','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:05:33'),('KH000T','Nguyễn Xuân Quỳnh','150 Nam Cao, Hồ Chí Minh, Việt Nam','nguyenxuanquynh@test.vn','01234012002','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:05:53'),('KH000U','Đặng Thị Thanh','151 Kha Vạn Cân, Hồ Chí Minh, Việt Nam','dangthithanh@test.vn','01234012003','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:06:10'),('KH000V','Trần Khánh Thành','153 Quốc lộ 13, Hiệp Bình Phước, Hồ Chí Minh, Việt Nam','trankhanhthanh@test.vn','01234012004','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:06:30'),('KH000W','Lương Thị Thuận','160 Trần Hưng Đạo, Đông Hòa, Bình Dương, Việt Nam','luongthithuan@test.vn','01234012005','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:06:48'),('KH000X','Trần Văn Thực','161 Đồng Khởi, Bến Nghé, Hồ Chí Minh, Việt Nam','tranvanthuc@test.vn','01234012006','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:07:52'),('KH000Y','Đỗ Tiến Dũng','162 Điện Biên Phủ, Bình Thạnh, Hồ Chí Minh, Việt Nam','dotiendung@test.vn','01234012007','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:08:08'),('KH000Z','Đỗ Tiến Sỹ','163 Gò Dưa, Hồ Chí Minh, Việt Nam','dotiensy@test.vn','01234012008','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:08:26'),('KH0010','Nguyễn Hoàng Trường','164 Kha Vạn Cân, Hiệp Bình Chánh, Hồ Chí Minh, Việt Nam','nguyenhoangtruong@test.vn','01234012009','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:08:52'),('KH0011','Vũ Thị Hậu','165/6 Lê Thị Hoa, Bình Chiểu, Hồ Chí Minh, Việt Nam','vuthihau@test.vn','01234012010','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:09:09'),('KH0012','Dương Phạm Huy Hùng','166 Trần Hưng Đạo, Đông Hòa, Bình Dương, Việt Nam','duongphamhuyhung@test.vn','01234012011','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:09:37'),('KH0013','Trần Văn Thông','167 Chu Văn An, Bình Thạnh, Hồ Chí Minh, Việt Nam','tranvanthong@test.vn','01234012012','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:10:00'),('KH0014','Nguyễn Tuấn Hùng','169 Ngô Quyền, Hiệp Phú, Hồ Chí Minh, Việt Nam','nguyentuanhung@test.vn','01234012013','','4297f44b13955235245b2497399d7a93',1,'2015-07-23 15:10:27');
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='New card request information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_new_card_request`
--

LOCK TABLES `mic_new_card_request` WRITE;
/*!40000 ALTER TABLE `mic_new_card_request` DISABLE KEYS */;
INSERT INTO `mic_new_card_request` VALUES (19,'2015-07-26 14:50:13','2015-07-26 14:50:34','23232',31,0,1),(20,'2015-08-19 09:00:46','2015-08-19 09:00:54',NULL,47,0,1),(21,'2015-08-19 09:02:20',NULL,NULL,50,0,1),(22,'2015-08-19 09:02:45',NULL,NULL,35,0,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_notification`
--

LOCK TABLES `mic_notification` WRITE;
/*!40000 ALTER TABLE `mic_notification` DISABLE KEYS */;
INSERT INTO `mic_notification` VALUES (38,'Khách hàng Trương Văn Khải gửi yêu cầu bồi thường cho hợp đồng HD000B','^NV([0-9A-Z]{4,8})$',1,2,'BT0001','2015-07-26 14:48:58',NULL),(39,'Khách hàng Trần Quang Đại gửi yêu cầu cấp thẻ mới cho hợp đồng HD0003','^NV([0-9A-Z]{4,8})$',1,3,'19','2015-07-26 14:50:13',NULL),(40,'Khách hàng Trương Văn Khải gửi yêu cầu bồi thường cho hợp đồng HD000B','^NV([0-9A-Z]{4,8})$',1,2,'BT0002','2015-07-25 19:47:36',NULL),(41,'Hợp đồng HD000B sẽ hết hạn trong 12 ngày nữa','^KH000A$',0,41,'HD000B','2015-07-31 21:24:12',NULL),(42,'Hợp đồng HD000B sẽ hết hạn trong 12 ngày nữa','^KH000A$',0,41,'HD000B','2015-07-31 21:24:12',NULL),(43,'Hợp đồng HD000M đã bắt đầu có hiệu lực','^KH000T$',0,8,'HD000M','2014-09-24 22:24:52',NULL),(44,'Hợp đồng HD000O đã bắt đầu có hiệu lực','^KH000S$',0,8,'HD000O','2014-09-24 22:24:52',NULL),(45,'Hợp đồng HD000U đã bắt đầu có hiệu lực','^KH000M$',0,8,'HD000U','2014-10-24 22:39:49',NULL),(46,'Khách hàng Vũ Thị Thanh Huệ đăng ký hợp đồng mới HD000V','^NV([0-9A-Z]{4,8})$',1,1,'HD000V','2014-10-24 22:42:50',NULL),(47,'Hợp đồng HD000V đã bị huỷ do không thanh toán đúng hạn','^NV([0-9A-Z]{4,8})$|^KH000L$',0,7,'HD000V','2014-10-31 22:44:29',NULL),(48,'Hợp đồng HD000L đã hết hạn, vui lòng gia hạn để tiếp tục sử dụng dịch vụ.','^KH000U$',0,5,'HD000L','2015-07-26 22:46:29',NULL),(49,'Hợp đồng HD000N đã hết hạn, vui lòng gia hạn để tiếp tục sử dụng dịch vụ.','^KH000T$',0,5,'HD000N','2015-07-26 22:46:39',NULL),(50,'Hợp đồng HD000R đã hết hạn, vui lòng gia hạn để tiếp tục sử dụng dịch vụ.','^KH000P$',0,5,'HD000R','2015-07-26 22:46:40',NULL),(51,'Hợp đồng HD000B đã hết hạn, vui lòng gia hạn để tiếp tục sử dụng dịch vụ.','^KH000A$',0,5,'HD000B','2015-08-19 08:50:52',NULL),(52,'Hợp đồng HD000O sẽ hết hạn trong 9 ngày nữa','^KH000S$',0,41,'HD000O','2015-08-19 08:50:52',NULL),(53,'Hợp đồng HD0011 đã hết hạn, vui lòng gia hạn để tiếp tục sử dụng dịch vụ.','^KH0010$',0,5,'HD0011','2015-08-19 08:50:52',NULL),(54,'Khách hàng Nguyễn Văn Trung đăng ký hợp đồng mới HD001A','^NV([0-9A-Z]{4,8})$',1,1,'HD001A','2015-08-19 08:59:15',NULL),(55,'Khách hàng Đặng Thị Thanh gửi yêu cầu cấp thẻ mới cho hợp đồng HD000L','^NV([0-9A-Z]{4,8})$',1,3,'20','2015-08-19 09:00:46',NULL),(56,'Khách hàng Phan Hữu Phú gửi yêu cầu cấp thẻ mới cho hợp đồng HD000O','^NV([0-9A-Z]{4,8})$',1,3,'21','2015-08-19 09:02:20',NULL),(57,'Khách hàng Nguyễn Hữu Cảnh gửi yêu cầu cấp thẻ mới cho hợp đồng HD0006','^NV([0-9A-Z]{4,8})$',1,3,'22','2015-08-19 09:02:45',NULL),(58,'Khách hàng Trần Văn Linh yêu cầu huỷ hợp đồng HD000A','^NV([0-9A-Z]{4,8})$',1,6,'HD000A','2015-08-19 09:04:31',NULL),(59,'Khách hàng Nguyễn Thị Trang yêu cầu huỷ hợp đồng HD0004','^NV([0-9A-Z]{4,8})$',1,6,'HD0004','2015-08-19 09:05:52',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_notification_read`
--

LOCK TABLES `mic_notification_read` WRITE;
/*!40000 ALTER TABLE `mic_notification_read` DISABLE KEYS */;
INSERT INTO `mic_notification_read` VALUES (77,'NV0001',38,1),(78,'NV0001',39,1),(79,'NV0001',40,1),(80,'NV0001',47,1),(81,'NV0001',46,1),(83,'NV0001',54,1),(84,'NV0001',55,1),(85,'NV0001',56,1),(86,'NV0001',57,1),(87,'NV0001',59,1),(88,'NV0001',58,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8 COMMENT='Payment information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_payment`
--

LOCK TABLES `mic_payment` WRITE;
/*!40000 ALTER TABLE `mic_payment` DISABLE KEYS */;
INSERT INTO `mic_payment` VALUES (105,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0001',66000,NULL,NULL,NULL,'HD0001','NV0001'),(106,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0002',80500,NULL,NULL,NULL,'HD0002','NV0001'),(107,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0003',66000,NULL,NULL,NULL,'HD0003','NV0001'),(108,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0004',80500,NULL,NULL,NULL,'HD0004','NV0001'),(109,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0005',319000,NULL,NULL,NULL,'HD0005','NV0001'),(110,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0006',60500,NULL,NULL,NULL,'HD0006','NV0001'),(111,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0007',60500,NULL,NULL,NULL,'HD0007','NV0001'),(112,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0008',80500,NULL,NULL,NULL,'HD0008','NV0001'),(113,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0009',60500,NULL,NULL,NULL,'HD0009','NV0001'),(114,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000A',80500,NULL,NULL,NULL,'HD000A','NV0001'),(115,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000B',319000,NULL,NULL,NULL,'HD000B','NV0001'),(116,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000C',80500,NULL,NULL,NULL,'HD000C','NV0001'),(117,'2015-07-25 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000D',86000,NULL,NULL,NULL,'HD000D','NV0001'),(118,'2015-07-25 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000E',80500,NULL,NULL,NULL,'HD000E','NV0001'),(119,'2015-07-25 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000F',60500,NULL,NULL,NULL,'HD000F','NV0001'),(120,'2015-07-25 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000G',80500,NULL,NULL,NULL,'HD000G','NV0001'),(121,'2015-07-25 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000H',80500,NULL,NULL,NULL,'HD000H','NV0001'),(122,'2015-07-25 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000I',319000,NULL,NULL,NULL,'HD000I','NV0001'),(123,'2015-07-25 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000J',66000,NULL,NULL,NULL,'HD000J','NV0001'),(124,'2015-07-25 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000K',86000,NULL,NULL,NULL,'HD000K','NV0001'),(125,'2014-06-28 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000L',86000,NULL,NULL,NULL,'HD000L','NV0001'),(126,'2014-06-28 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000M',86000,NULL,NULL,NULL,'HD000M','NV0001'),(127,'2014-06-28 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000N',80500,NULL,NULL,NULL,'HD000N','NV0001'),(128,'2014-06-28 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000O',86000,NULL,NULL,NULL,'HD000O','NV0001'),(129,'2014-09-24 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000P',86000,NULL,NULL,NULL,'HD000P','NV0001'),(130,'2014-09-24 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000Q',80500,NULL,NULL,NULL,'HD000Q','NV0001'),(131,'2014-09-24 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000R',71391.8,NULL,NULL,NULL,'HD000R','NV0001'),(132,'2014-09-24 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000S',80500,NULL,NULL,NULL,'HD000S','NV0001'),(133,'2014-09-24 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000T',319000,NULL,NULL,NULL,'HD000T','NV0001'),(134,'2014-09-24 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000U',80500,NULL,NULL,NULL,'HD000U','NV0001'),(135,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000W',86000,NULL,NULL,NULL,'HD000W','NV0001'),(136,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000X',133718,NULL,NULL,NULL,'HD000X','NV0001'),(137,'2015-07-21 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000Y',55150.7,NULL,NULL,NULL,'HD000Y','NV0001'),(138,'2015-07-23 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD000Z',266562,NULL,NULL,NULL,'HD000Z','NV0001'),(139,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0010',86000,NULL,NULL,NULL,'HD0010','NV0001'),(140,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0011',80500,NULL,NULL,NULL,'HD0011','NV0001'),(141,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0012',71627.4,NULL,NULL,NULL,'HD0012','NV0001'),(142,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0013',60500,NULL,NULL,NULL,'HD0013','NV0001'),(143,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0014',57490.4,NULL,NULL,NULL,'HD0014','NV0001'),(144,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0015',319000,NULL,NULL,NULL,'HD0015','NV0001'),(145,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0016',66000,NULL,NULL,NULL,'HD0016','NV0001'),(146,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0017',86000,NULL,NULL,NULL,'HD0017','NV0001'),(147,'2015-07-26 00:00:00','Trực tiếp','Đăng ký hợp đồng mới HD0018',86000,NULL,NULL,NULL,'HD0018','NV0001'),(148,'2015-08-19 08:53:07','Trực tiếp','Gia hạn hợp đồng',61000,NULL,'2015-09-16 00:00:00','2016-09-16 00:00:00','HD0013','NV0001'),(149,'2015-08-19 08:54:25','Trực tiếp','Gia hạn hợp đồng',81000,NULL,'2015-09-26 00:00:00','2016-09-26 00:00:00','HD000U','NV0001'),(150,'2015-08-19 08:54:37','Trực tiếp','Gia hạn hợp đồng',319000,NULL,'2015-09-24 00:00:00','2016-09-24 00:00:00','HD000T','NV0001'),(151,'2015-08-19 08:54:52','Trực tiếp','Gia hạn hợp đồng',86000,NULL,'2015-09-24 00:00:00','2016-09-24 00:00:00','HD000P','NV0001'),(153,'2015-08-19 09:00:45','Trực tiếp','Gia hạn hợp đồng, đổi thẻ mới',96000,NULL,'2015-08-19 00:00:00','2016-08-19 00:00:00','HD000L','NV0001'),(154,'2015-08-19 00:00:00','Trực tiếp','Đăng ký thẻ mới HD0003',10000,NULL,NULL,NULL,'HD0003','NV0001'),(155,'2015-08-19 09:02:20','Trực tiếp','Gia hạn hợp đồng, đổi thẻ mới',96000,NULL,'2015-08-28 00:00:00','2016-08-28 00:00:00','HD000O','NV0001'),(156,'2015-08-19 09:02:45','Trực tiếp','Gia hạn hợp đồng, đổi thẻ mới',71000,NULL,'2015-10-23 00:00:00','2016-10-23 00:00:00','HD0006','NV0001');
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='Punishment information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mic_punishment`
--

LOCK TABLES `mic_punishment` WRITE;
/*!40000 ALTER TABLE `mic_punishment` DISABLE KEYS */;
INSERT INTO `mic_punishment` VALUES (26,'2015-07-26 00:00:00','Vượt quá tốc độ 15%','https://www.filepicker.io/api/file/HFYJn4yrRxCyFM5jFEku','2015-07-26 23:16:07','HD000X');
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

-- Dump completed on 2015-08-19  9:13:23
