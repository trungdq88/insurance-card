-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mic
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mic
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mic` DEFAULT CHARACTER SET utf8 ;
USE `mic` ;

-- -----------------------------------------------------
-- Table `mic`.`accident`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic`.`accident` (
  `title` VARCHAR(250) NOT NULL,
  `attachment` VARCHAR(255) NOT NULL,
  `date` INT(10) UNSIGNED NOT NULL,
  `contract_code` INT(11) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Accident information';


-- -----------------------------------------------------
-- Table `mic`.`card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic`.`card` (
  `card_id` VARCHAR(20) NOT NULL,
  `activated_date` INT(10) UNSIGNED NOT NULL,
  `deactivated_date` INT(11) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`card_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Card information';


-- -----------------------------------------------------
-- Table `mic`.`card_access_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic`.`card_access_log` (
  `card_id` VARCHAR(20) NOT NULL,
  `date` INT(11) NOT NULL,
  `device` VARCHAR(50) NOT NULL,
  `request` VARCHAR(200) NOT NULL,
  `response` VARCHAR(200) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Card access log';


-- -----------------------------------------------------
-- Table `mic`.`compensation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic`.`compensation` (
  `compensation_id` INT(11) NOT NULL AUTO_INCREMENT,
  `contract_code` INT(11) NOT NULL,
  `driver_name` VARCHAR(80) NOT NULL,
  `license_number` VARCHAR(15) NOT NULL,
  `license_type` VARCHAR(10) NOT NULL,
  `driver_phone` VARCHAR(15) NOT NULL,
  `vehicle_capacity` VARCHAR(20) NOT NULL,
  `driver_address` VARCHAR(250) NOT NULL,
  `plate` VARCHAR(15) NOT NULL,
  `date` INT(11) NOT NULL,
  `place` VARCHAR(20) NOT NULL,
  `control_department` VARCHAR(250) NOT NULL,
  `description` VARCHAR(2000) NOT NULL,
  `human_damage` VARCHAR(2000) NOT NULL,
  `asset_damage` VARCHAR(2000) NOT NULL,
  `observer` VARCHAR(80) NOT NULL,
  `compensation_note` VARCHAR(2000) NULL DEFAULT NULL,
  `attachment` VARCHAR(255) NULL DEFAULT NULL,
  `created_date` INT(11) NOT NULL,
  `resolve_date` INT(11) NULL DEFAULT NULL,
  `desicion` VARCHAR(250) NULL DEFAULT NULL,
  `resolve_note` VARCHAR(2000) NULL DEFAULT NULL,
  PRIMARY KEY (`compensation_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Compensation information';


-- -----------------------------------------------------
-- Table `mic`.`contract`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic`.`contract` (
  `contract_code` INT(11) NOT NULL AUTO_INCREMENT,
  `customer_code` VARCHAR(20) NOT NULL,
  `start_date` INT(11) NOT NULL,
  `expired_date` INT(11) NOT NULL,
  `contract_type_id` INT(11) NOT NULL,
  `contract_fee` FLOAT NOT NULL,
  `plate` VARCHAR(15) NOT NULL,
  `brand` VARCHAR(20) NOT NULL,
  `model_code` VARCHAR(20) NOT NULL,
  `type` VARCHAR(20) NOT NULL,
  `color` VARCHAR(20) NOT NULL,
  `engine` VARCHAR(20) NOT NULL,
  `chassis` VARCHAR(20) NOT NULL,
  `capacity` VARCHAR(20) NOT NULL,
  `year_of_manufactuer` INT(11) NOT NULL,
  `weight` INT(11) NOT NULL,
  `seat_capacity` INT(11) NOT NULL,
  `cert_image` VARCHAR(255) NULL DEFAULT NULL,
  `cancel_date` INT(11) NULL DEFAULT NULL,
  `cancel_reason` VARCHAR(250) NULL DEFAULT NULL,
  `cancel_note` VARCHAR(2000) NULL DEFAULT NULL,
  `card_id` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`contract_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Contract information';


-- -----------------------------------------------------
-- Table `mic`.`contract_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic`.`contract_type` (
  `contract_type_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  `description` VARCHAR(2000) NOT NULL,
  `price_per_year` FLOAT NOT NULL,
  PRIMARY KEY (`contract_type_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Contract type information';


-- -----------------------------------------------------
-- Table `mic`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic`.`customer` (
  `customer_code` VARCHAR(20) NOT NULL,
  `name` VARCHAR(80) NOT NULL,
  `address` VARCHAR(250) NOT NULL,
  `email` VARCHAR(250) NOT NULL,
  `phone` VARCHAR(15) NOT NULL,
  `personal_id` VARCHAR(15) NULL DEFAULT NULL,
  `password` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`customer_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Customer information';


-- -----------------------------------------------------
-- Table `mic`.`new_card_request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic`.`new_card_request` (
  `request_date` INT(11) NOT NULL,
  `resolve_date` INT(11) NOT NULL,
  `note` VARCHAR(2000) NULL DEFAULT NULL,
  `card_id` VARCHAR(20) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'New card request information';


-- -----------------------------------------------------
-- Table `mic`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic`.`payment` (
  `payment_id` INT(11) NOT NULL AUTO_INCREMENT,
  `contract_code` INT(11) NOT NULL,
  `date` INT(11) NOT NULL,
  `method` VARCHAR(20) NOT NULL,
  `service` VARCHAR(250) NOT NULL,
  `amount` FLOAT NOT NULL,
  `receiver` VARCHAR(80) NULL DEFAULT NULL,
  `paypal_trans_id` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`payment_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Payment information';


-- -----------------------------------------------------
-- Table `mic`.`punishment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic`.`punishment` (
  `date` INT(11) NOT NULL,
  `title` VARCHAR(250) NOT NULL,
  `attachment` VARCHAR(255) NOT NULL,
  `contract_code` INT(11) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Punishment information';


-- -----------------------------------------------------
-- Table `mic`.`staff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic`.`staff` (
  `staff_code` VARCHAR(20) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `name` VARCHAR(80) NOT NULL,
  `email` VARCHAR(250) NOT NULL,
  `phone` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`staff_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Staff information';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
