-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema AddressDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema AddressDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `AddressDB` DEFAULT CHARACTER SET big5 ;
USE `AddressDB` ;

-- -----------------------------------------------------
-- Table `AddressDB`.`populated_area_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AddressDB`.`populated_area_types` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `AddressDB`.`countries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AddressDB`.`countries` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `AddressDB`.`regions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AddressDB`.`regions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `country_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_regions_1_idx` (`country_id` ASC),
  CONSTRAINT `fk_regions_1`
    FOREIGN KEY (`country_id`)
    REFERENCES `AddressDB`.`countries` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `AddressDB`.`populated_areas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AddressDB`.`populated_areas` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `type_id` INT(11) NULL DEFAULT NULL,
  `region_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_populated_areas_1_idx` (`type_id` ASC),
  INDEX `fk_populated_areas_2_idx` (`region_id` ASC),
  CONSTRAINT `fk_populated_areas_1`
    FOREIGN KEY (`type_id`)
    REFERENCES `AddressDB`.`populated_area_types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_populated_areas_2`
    FOREIGN KEY (`region_id`)
    REFERENCES `AddressDB`.`regions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `AddressDB`.`municipalities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AddressDB`.`municipalities` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `postal_code` VARCHAR(45) NULL DEFAULT NULL,
  `populated_area_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_municipalities_1_idx` (`populated_area_id` ASC),
  CONSTRAINT `fk_municipalities_1`
    FOREIGN KEY (`populated_area_id`)
    REFERENCES `AddressDB`.`populated_areas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `AddressDB`.`streets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AddressDB`.`streets` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `municipality_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_streets_1_idx` (`municipality_id` ASC),
  CONSTRAINT `fk_streets_1`
    FOREIGN KEY (`municipality_id`)
    REFERENCES `AddressDB`.`municipalities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `AddressDB`.`addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AddressDB`.`addresses` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `street_id` INT(11) NULL DEFAULT NULL,
  `number` VARCHAR(45) NULL DEFAULT NULL,
  `apartmentNo` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_addresses_1_idx` (`street_id` ASC),
  CONSTRAINT `fk_addresses_1`
    FOREIGN KEY (`street_id`)
    REFERENCES `AddressDB`.`streets` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = big5;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
