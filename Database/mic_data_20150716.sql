-- MySQL Workbench Forward Engineering
-- 20150716 changes:
-- - Add contract.active_card_instance_id

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mic_data
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mic_data
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mic_data` DEFAULT CHARACTER SET utf8 ;
USE `mic_data` ;

-- -----------------------------------------------------
-- Table `mic_data`.`increments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic_data`.`increments` (
  `table_name` VARCHAR(255) NOT NULL,
  `increment` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`table_name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'increments';


-- -----------------------------------------------------
-- Table `mic_data`.`mic_contract_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic_data`.`mic_contract_type` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  `description` VARCHAR(2000) NOT NULL,
  `price_per_year` FLOAT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8
COMMENT = 'Contract type information';


-- -----------------------------------------------------
-- Table `mic_data`.`mic_customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic_data`.`mic_customer` (
  `customer_code` VARCHAR(10) NOT NULL,
  `name` VARCHAR(80) NOT NULL,
  `address` VARCHAR(250) NOT NULL,
  `email` VARCHAR(250) NOT NULL,
  `phone` VARCHAR(15) NOT NULL,
  `personal_id` VARCHAR(15) NULL DEFAULT NULL,
  `password` VARCHAR(32) NOT NULL,
  `is_default_password` INT(1) UNSIGNED NOT NULL,
  `last_modified` DATETIME NOT NULL,
  PRIMARY KEY (`customer_code`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Customer information';


-- -----------------------------------------------------
-- Table `mic_data`.`mic_staff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic_data`.`mic_staff` (
  `staff_code` VARCHAR(10) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `name` VARCHAR(80) NOT NULL,
  `email` VARCHAR(250) NOT NULL,
  `phone` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`staff_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Staff information';


-- -----------------------------------------------------
-- Table `mic_data`.`mic_card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic_data`.`mic_card` (
  `card_id` VARCHAR(20) NOT NULL,
  `status` INT(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`card_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mic_data`.`mic_new_card_request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic_data`.`mic_new_card_request` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `request_date` DATETIME NOT NULL,
  `resolve_date` DATETIME NULL DEFAULT NULL,
  `note` VARCHAR(2000) NULL DEFAULT NULL,
  `old_card_instance_id` INT(10) UNSIGNED NOT NULL,
  `is_delivery_requested` INT(1) UNSIGNED NOT NULL DEFAULT '0',
  `is_paid` INT(1) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `fk_mic_new_card_request_mic_card1_idx` (`old_card_instance_id` ASC),
  CONSTRAINT `fk_card_instance_id`
    FOREIGN KEY (`old_card_instance_id`)
    REFERENCES `mic_data`.`mic_card_instance` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8
COMMENT = 'New card request information';


-- -----------------------------------------------------
-- Table `mic_data`.`mic_card_instance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic_data`.`mic_card_instance` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `card_id` VARCHAR(20) NOT NULL,
  `activated_date` DATETIME NOT NULL,
  `deactivated_date` DATETIME NULL DEFAULT NULL,
  `contract_code` VARCHAR(10) NOT NULL,
  `new_card_request_id` INT(11) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_mic_card_mic_contract1_idx` (`contract_code` ASC),
  INDEX `fk_mic_card_mic_new_card_request1_idx` (`new_card_request_id` ASC),
  INDEX `fk_card_id_idx` (`card_id` ASC),
  CONSTRAINT `fk_card_id_instance_id`
    FOREIGN KEY (`card_id`)
    REFERENCES `mic_data`.`mic_card` (`card_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mic_card_mic_contract1`
    FOREIGN KEY (`contract_code`)
    REFERENCES `mic_data`.`mic_contract` (`contract_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mic_card_mic_new_card_request1`
    FOREIGN KEY (`new_card_request_id`)
    REFERENCES `mic_data`.`mic_new_card_request` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8
COMMENT = 'Card information';


-- -----------------------------------------------------
-- Table `mic_data`.`mic_contract`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic_data`.`mic_contract` (
  `contract_code` VARCHAR(10) NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `start_date` DATETIME NOT NULL,
  `expired_date` DATETIME NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `contract_fee` FLOAT UNSIGNED NOT NULL,
  `plate` VARCHAR(15) NOT NULL,
  `brand` VARCHAR(20) NOT NULL,
  `model_code` VARCHAR(20) NULL DEFAULT NULL,
  `vehicle_type` VARCHAR(20) NULL DEFAULT NULL,
  `color` VARCHAR(20) NULL DEFAULT NULL,
  `engine` VARCHAR(20) NOT NULL,
  `chassis` VARCHAR(20) NOT NULL,
  `capacity` VARCHAR(20) NOT NULL,
  `year_of_manufacture` INT(11) UNSIGNED NULL DEFAULT NULL,
  `weight` INT(11) UNSIGNED NULL DEFAULT NULL,
  `seat_capacity` INT(11) UNSIGNED NULL DEFAULT NULL,
  `cancel_date` DATETIME NULL DEFAULT NULL,
  `cancel_reason` VARCHAR(250) NULL DEFAULT NULL,
  `cancel_note` VARCHAR(2000) NULL DEFAULT NULL,
  `last_modified` DATETIME NOT NULL,
  `staff_code` VARCHAR(10) NULL DEFAULT NULL,
  `contract_type_id` INT(11) UNSIGNED NOT NULL,
  `customer_code` VARCHAR(10) NOT NULL,
  `need_renew_payment` INT(11) UNSIGNED NULL DEFAULT NULL,
  `active_card_instance_id` INT UNSIGNED NULL,
  PRIMARY KEY (`contract_code`),
  INDEX `fk_mic_contract_mic_staff_idx` (`staff_code` ASC),
  INDEX `fk_mic_contract_mic_contract_type1_idx` (`contract_type_id` ASC),
  INDEX `fk_mic_contract_mic_customer1_idx` (`customer_code` ASC),
  INDEX `fk_mic_contract_mic_card_instance_idx` (`active_card_instance_id` ASC),
  CONSTRAINT `fk_mic_contract_mic_contract_type1`
    FOREIGN KEY (`contract_type_id`)
    REFERENCES `mic_data`.`mic_contract_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mic_contract_mic_customer1`
    FOREIGN KEY (`customer_code`)
    REFERENCES `mic_data`.`mic_customer` (`customer_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mic_contract_mic_staff`
    FOREIGN KEY (`staff_code`)
    REFERENCES `mic_data`.`mic_staff` (`staff_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mic_contract_mic_card_instance`
    FOREIGN KEY (`active_card_instance_id`)
    REFERENCES `mic_data`.`mic_card_instance` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Contract information';


-- -----------------------------------------------------
-- Table `mic_data`.`mic_accident`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic_data`.`mic_accident` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(250) NOT NULL,
  `attachment` VARCHAR(255) NOT NULL,
  `created_date` DATETIME NOT NULL,
  `last_modified` DATETIME NOT NULL,
  `contract_code` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_mic_accident_mic_contract1_idx` (`contract_code` ASC),
  CONSTRAINT `fk_mic_accident_mic_contract1`
    FOREIGN KEY (`contract_code`)
    REFERENCES `mic_data`.`mic_contract` (`contract_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8
COMMENT = 'Accident information';


-- -----------------------------------------------------
-- Table `mic_data`.`mic_business_rules`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic_data`.`mic_business_rules` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `start_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `start_date_before` INT(10) UNSIGNED NOT NULL,
  `start_date_after` INT(10) UNSIGNED NOT NULL,
  `contract_min_term` INT(11) UNSIGNED NOT NULL,
  `contract_default_term` INT(10) UNSIGNED NOT NULL,
  `paid_date_before` INT(10) UNSIGNED NOT NULL,
  `paid_date_after` INT(10) UNSIGNED NOT NULL,
  `cancel_date_before` INT(10) UNSIGNED NOT NULL,
  `cancel_date_after` INT(10) UNSIGNED NOT NULL,
  `payment_due_date` INT(10) UNSIGNED NOT NULL,
  `nearly_exceed_expired_one` INT(10) UNSIGNED NOT NULL,
  `nearly_exceed_expired_two` INT(10) UNSIGNED NOT NULL,
  `nearly_exceed_expired_three` INT(10) UNSIGNED NOT NULL,
  `new_card_request_fee` INT(10) UNSIGNED NOT NULL,
  `delivery_fee` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mic_data`.`mic_card_access_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic_data`.`mic_card_access_log` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `access_date` DATETIME NOT NULL,
  `device` VARCHAR(50) NOT NULL,
  `request_service` VARCHAR(200) NOT NULL,
  `response_content` VARCHAR(200) NOT NULL,
  `card_instance_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_mic_card_access_log_mic_card1_idx` (`card_instance_id` ASC),
  CONSTRAINT `fk_card_id`
    FOREIGN KEY (`card_instance_id`)
    REFERENCES `mic_data`.`mic_card_instance` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8
COMMENT = 'Card access log';


-- -----------------------------------------------------
-- Table `mic_data`.`mic_compensation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic_data`.`mic_compensation` (
  `compensation_code` VARCHAR(10) NOT NULL,
  `driver_name` VARCHAR(80) NOT NULL,
  `license_number` VARCHAR(15) NOT NULL,
  `license_type` VARCHAR(10) NOT NULL,
  `driver_phone` VARCHAR(15) NOT NULL,
  `vehicle_capacity` VARCHAR(20) NOT NULL,
  `driver_address` VARCHAR(250) NOT NULL,
  `plate` VARCHAR(15) NOT NULL,
  `accident_date` DATETIME NOT NULL,
  `accident_place` VARCHAR(250) NOT NULL,
  `control_department` VARCHAR(250) NOT NULL,
  `description` VARCHAR(2000) NOT NULL,
  `human_damage` VARCHAR(2000) NOT NULL,
  `asset_damage` VARCHAR(2000) NOT NULL,
  `observer` VARCHAR(80) NOT NULL,
  `observer_address` VARCHAR(250) NOT NULL,
  `compensation_note` VARCHAR(2000) NULL DEFAULT NULL,
  `attachment` VARCHAR(255) NULL DEFAULT NULL,
  `created_date` DATETIME NOT NULL,
  `resolve_date` DATETIME NULL DEFAULT NULL,
  `decision` VARCHAR(250) NULL DEFAULT NULL,
  `resolve_note` VARCHAR(2000) NULL DEFAULT NULL,
  `last_modified` DATETIME NOT NULL,
  `contract_code` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`compensation_code`),
  INDEX `fk_mic_compensation_mic_contract1_idx` (`contract_code` ASC),
  CONSTRAINT `fk_mic_compensation_mic_contract1`
    FOREIGN KEY (`contract_code`)
    REFERENCES `mic_data`.`mic_contract` (`contract_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Compensation information';


-- -----------------------------------------------------
-- Table `mic_data`.`mic_notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic_data`.`mic_notification` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(2000) NOT NULL,
  `receiver` VARCHAR(250) NOT NULL,
  `method` INT(11) NOT NULL,
  `type` INT(11) NOT NULL,
  `extra_data` VARCHAR(2000) NULL DEFAULT NULL,
  `created_date` DATETIME NOT NULL,
  `resolved_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 38
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mic_data`.`mic_notification_read`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic_data`.`mic_notification_read` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_code` VARCHAR(10) NOT NULL,
  `notification_id` INT(11) NOT NULL,
  `is_read` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_user_code_is_read` (`user_code` ASC, `notification_id` ASC),
  INDEX `fk_notification_id_idx` (`notification_id` ASC),
  CONSTRAINT `fk_notification_id`
    FOREIGN KEY (`notification_id`)
    REFERENCES `mic_data`.`mic_notification` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 77
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mic_data`.`mic_payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic_data`.`mic_payment` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `paid_date` DATETIME NOT NULL,
  `payment_method` VARCHAR(20) NOT NULL,
  `content` VARCHAR(250) NOT NULL,
  `amount` FLOAT UNSIGNED NOT NULL,
  `paypal_trans_id` VARCHAR(50) NULL DEFAULT NULL,
  `start_date` DATETIME NULL DEFAULT NULL,
  `expired_date` DATETIME NULL DEFAULT NULL,
  `contract_code` VARCHAR(10) NOT NULL,
  `receiver` VARCHAR(10) NULL DEFAULT NULL COMMENT 'staff code',
  PRIMARY KEY (`id`),
  INDEX `fk_mic_payment_mic_contract1_idx` (`contract_code` ASC),
  INDEX `fk_mic_payment_mic_staff_idx` (`receiver` ASC),
  CONSTRAINT `fk_mic_payment_mic_contract1`
    FOREIGN KEY (`contract_code`)
    REFERENCES `mic_data`.`mic_contract` (`contract_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mic_payment_mic_staff`
    FOREIGN KEY (`receiver`)
    REFERENCES `mic_data`.`mic_staff` (`staff_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 105
DEFAULT CHARACTER SET = utf8
COMMENT = 'Payment information';


-- -----------------------------------------------------
-- Table `mic_data`.`mic_punishment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mic_data`.`mic_punishment` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_date` DATETIME NOT NULL,
  `title` VARCHAR(250) NOT NULL,
  `attachment` VARCHAR(255) NOT NULL,
  `last_modified` DATETIME NOT NULL,
  `contract_code` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_mic_punishment_mic_contract1_idx` (`contract_code` ASC),
  CONSTRAINT `fk_mic_punishment_mic_contract1`
    FOREIGN KEY (`contract_code`)
    REFERENCES `mic_data`.`mic_contract` (`contract_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 26
DEFAULT CHARACTER SET = utf8
COMMENT = 'Punishment information';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
