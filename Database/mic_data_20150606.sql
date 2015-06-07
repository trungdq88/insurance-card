-- 20150606 Changes:
-- - Add `increments` table
-- 
-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 06, 2015 at 09:14 AM
-- Server version: 5.5.43-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mic_data`
--

-- --------------------------------------------------------

--
-- Table structure for table `increments`
--

CREATE TABLE IF NOT EXISTS `increments` (
  `table_name` varchar(255) NOT NULL,
  `increment` int(11) NOT NULL,
  PRIMARY KEY (`table_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `mic_accident`
--

CREATE TABLE IF NOT EXISTS `mic_accident` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(250) NOT NULL,
  `attachment` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `contract_code` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mic_accident_mic_contract1_idx` (`contract_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Accident information' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mic_card`
--

CREATE TABLE IF NOT EXISTS `mic_card` (
  `card_id` varchar(20) NOT NULL,
  `activated_date` datetime NOT NULL,
  `deactivated_date` datetime DEFAULT NULL,
  `contract_code` varchar(10) NOT NULL,
  `new_card_request_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`card_id`),
  KEY `fk_mic_card_mic_contract1_idx` (`contract_code`),
  KEY `fk_mic_card_mic_new_card_request1_idx` (`new_card_request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Card information';

-- --------------------------------------------------------

--
-- Table structure for table `mic_card_access_log`
--

CREATE TABLE IF NOT EXISTS `mic_card_access_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `access_date` datetime NOT NULL,
  `device` varchar(50) NOT NULL,
  `request_service` varchar(200) NOT NULL,
  `response_content` varchar(200) NOT NULL,
  `card_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mic_card_access_log_mic_card1_idx` (`card_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Card access log' AUTO_INCREMENT=11 ;

-- --------------------------------------------------------

--
-- Table structure for table `mic_compensation`
--

CREATE TABLE IF NOT EXISTS `mic_compensation` (
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
  `compensation_note` varchar(2000) DEFAULT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `resolve_date` datetime DEFAULT NULL,
  `desicion` varchar(250) DEFAULT NULL,
  `resolve_note` varchar(2000) DEFAULT NULL,
  `contract_code` varchar(10) NOT NULL,
  PRIMARY KEY (`compensation_code`),
  KEY `fk_mic_compensation_mic_contract1_idx` (`contract_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Compensation information';

-- --------------------------------------------------------

--
-- Table structure for table `mic_contract`
--

CREATE TABLE IF NOT EXISTS `mic_contract` (
  `contract_code` varchar(10) NOT NULL,
  `start_date` datetime NOT NULL,
  `expired_date` datetime NOT NULL,
  `status` varchar(50) NOT NULL,
  `contract_fee` float unsigned NOT NULL,
  `plate` varchar(15) NOT NULL,
  `brand` varchar(20) NOT NULL,
  `model_code` varchar(20) NOT NULL,
  `vehicle_type` varchar(20) NOT NULL,
  `color` varchar(20) NOT NULL,
  `engine` varchar(20) NOT NULL,
  `chassis` varchar(20) NOT NULL,
  `capacity` varchar(20) NOT NULL,
  `year_of_manufacture` int(11) unsigned NOT NULL,
  `weight` int(11) unsigned NOT NULL,
  `seat_capacity` int(11) unsigned NOT NULL,
  `cert_image` varchar(255) DEFAULT NULL,
  `cancel_date` datetime DEFAULT NULL,
  `cancel_reason` varchar(250) DEFAULT NULL,
  `cancel_note` varchar(2000) DEFAULT NULL,
  `staff_code` varchar(10) DEFAULT NULL,
  `contract_type_id` int(11) unsigned NOT NULL,
  `customer_code` varchar(10) NOT NULL,
  PRIMARY KEY (`contract_code`),
  KEY `fk_mic_contract_mic_staff_idx` (`staff_code`),
  KEY `fk_mic_contract_mic_contract_type1_idx` (`contract_type_id`),
  KEY `fk_mic_contract_mic_customer1_idx` (`customer_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Contract information';

-- --------------------------------------------------------

--
-- Table structure for table `mic_contract_type`
--

CREATE TABLE IF NOT EXISTS `mic_contract_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `price_per_year` float unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Contract type information' AUTO_INCREMENT=7 ;

-- --------------------------------------------------------

--
-- Table structure for table `mic_customer`
--

CREATE TABLE IF NOT EXISTS `mic_customer` (
  `customer_code` varchar(10) NOT NULL,
  `name` varchar(80) NOT NULL,
  `address` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `personal_id` varchar(15) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`customer_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Customer information';

-- --------------------------------------------------------

--
-- Table structure for table `mic_new_card_request`
--

CREATE TABLE IF NOT EXISTS `mic_new_card_request` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `request_date` datetime NOT NULL,
  `resolve_date` datetime DEFAULT NULL,
  `note` varchar(2000) DEFAULT NULL,
  `old_card_id` varchar(20) NOT NULL,
  `customer_code` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mic_new_card_request_mic_card1_idx` (`old_card_id`),
  KEY `fk_mic_new_card_request_mic_customer1_idx` (`customer_code`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='New card request information' AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Table structure for table `mic_payment`
--

CREATE TABLE IF NOT EXISTS `mic_payment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `paid_date` datetime NOT NULL,
  `payment_method` varchar(20) NOT NULL,
  `content` varchar(250) NOT NULL,
  `amount` float unsigned NOT NULL,
  `receiver` varchar(80) DEFAULT NULL,
  `paypal_trans_id` varchar(50) DEFAULT NULL,
  `contract_code` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mic_payment_mic_contract1_idx` (`contract_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Payment information' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mic_punishment`
--

CREATE TABLE IF NOT EXISTS `mic_punishment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `created_date` datetime NOT NULL,
  `title` varchar(250) NOT NULL,
  `attachment` varchar(255) NOT NULL,
  `contract_code` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mic_punishment_mic_contract1_idx` (`contract_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Punishment information' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mic_staff`
--

CREATE TABLE IF NOT EXISTS `mic_staff` (
  `staff_code` varchar(10) NOT NULL,
  `password` varchar(32) NOT NULL,
  `name` varchar(80) NOT NULL,
  `email` varchar(250) NOT NULL,
  `phone` varchar(15) NOT NULL,
  PRIMARY KEY (`staff_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Staff information';

--
-- Constraints for dumped tables
--

--
-- Constraints for table `mic_accident`
--
ALTER TABLE `mic_accident`
  ADD CONSTRAINT `fk_mic_accident_mic_contract1` FOREIGN KEY (`contract_code`) REFERENCES `mic_contract` (`contract_code`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `mic_card`
--
ALTER TABLE `mic_card`
  ADD CONSTRAINT `fk_mic_card_mic_contract1` FOREIGN KEY (`contract_code`) REFERENCES `mic_contract` (`contract_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_mic_card_mic_new_card_request1` FOREIGN KEY (`new_card_request_id`) REFERENCES `mic_new_card_request` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `mic_card_access_log`
--
ALTER TABLE `mic_card_access_log`
  ADD CONSTRAINT `fk_mic_card_access_log_mic_card1` FOREIGN KEY (`card_id`) REFERENCES `mic_card` (`card_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `mic_compensation`
--
ALTER TABLE `mic_compensation`
  ADD CONSTRAINT `fk_mic_compensation_mic_contract1` FOREIGN KEY (`contract_code`) REFERENCES `mic_contract` (`contract_code`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `mic_contract`
--
ALTER TABLE `mic_contract`
  ADD CONSTRAINT `fk_mic_contract_mic_contract_type1` FOREIGN KEY (`contract_type_id`) REFERENCES `mic_contract_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_mic_contract_mic_customer1` FOREIGN KEY (`customer_code`) REFERENCES `mic_customer` (`customer_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_mic_contract_mic_staff` FOREIGN KEY (`staff_code`) REFERENCES `mic_staff` (`staff_code`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `mic_new_card_request`
--
ALTER TABLE `mic_new_card_request`
  ADD CONSTRAINT `fk_mic_new_card_request_mic_card1` FOREIGN KEY (`old_card_id`) REFERENCES `mic_card` (`card_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_mic_new_card_request_mic_customer1` FOREIGN KEY (`customer_code`) REFERENCES `mic_customer` (`customer_code`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `mic_payment`
--
ALTER TABLE `mic_payment`
  ADD CONSTRAINT `fk_mic_payment_mic_contract1` FOREIGN KEY (`contract_code`) REFERENCES `mic_contract` (`contract_code`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `mic_punishment`
--
ALTER TABLE `mic_punishment`
  ADD CONSTRAINT `fk_mic_punishment_mic_contract1` FOREIGN KEY (`contract_code`) REFERENCES `mic_contract` (`contract_code`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
