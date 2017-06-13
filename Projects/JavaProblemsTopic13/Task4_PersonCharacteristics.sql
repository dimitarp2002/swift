-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema citizen_registrations
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema citizen_registrations
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `citizen_registrations` DEFAULT CHARACTER SET big5 ;
USE `citizen_registrations` ;

-- -----------------------------------------------------
-- Table `citizen_registrations`.`addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `citizen_registrations`.`addresses` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `municipality` VARCHAR(45) NOT NULL,
  `postal_code` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `number` VARCHAR(45) NOT NULL,
  `floor` INT(11) NULL DEFAULT NULL,
  `apartmentNo` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `citizen_registrations`.`education_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `citizen_registrations`.`education_types` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `citizen_registrations`.`educations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `citizen_registrations`.`educations` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type_id` INT(11) NOT NULL,
  `institution_name` VARCHAR(45) NOT NULL,
  `enrollment_date` DATE NOT NULL,
  `graduation_date` DATE NULL DEFAULT NULL,
  `graduated` BIT(1) NOT NULL,
  `final_grade` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_educations_1_idx` (`type_id` ASC),
  CONSTRAINT `fk_educations_1`
    FOREIGN KEY (`type_id`)
    REFERENCES `citizen_registrations`.`education_types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `citizen_registrations`.`genders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `citizen_registrations`.`genders` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `citizen_registrations`.`people`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `citizen_registrations`.`people` (
  `id` INT(11) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `middle_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `gender_id` INT(11) NOT NULL,
  `height` SMALLINT(6) NULL DEFAULT NULL,
  `birth_date` DATE NOT NULL,
  `current_address` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_people_1_idx` (`current_address` ASC),
  INDEX `fk_people_2_idx` (`gender_id` ASC),
  CONSTRAINT `fk_people_1`
    FOREIGN KEY (`current_address`)
    REFERENCES `citizen_registrations`.`addresses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_people_2`
    FOREIGN KEY (`gender_id`)
    REFERENCES `citizen_registrations`.`genders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `citizen_registrations`.`people_addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `citizen_registrations`.`people_addresses` (
  `person_id` INT(11) NOT NULL,
  `address_id` INT(11) NOT NULL,
  PRIMARY KEY (`person_id`),
  INDEX `fk_people_addresses_2_idx` (`address_id` ASC),
  CONSTRAINT `fk_people_addresses_1`
    FOREIGN KEY (`person_id`)
    REFERENCES `citizen_registrations`.`people` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_people_addresses_2`
    FOREIGN KEY (`address_id`)
    REFERENCES `citizen_registrations`.`addresses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `citizen_registrations`.`people_educations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `citizen_registrations`.`people_educations` (
  `person_id` INT(11) NOT NULL,
  `education_id` INT(11) NOT NULL,
  PRIMARY KEY (`person_id`),
  INDEX `fk_people_educations_2_idx` (`education_id` ASC),
  CONSTRAINT `fk_people_educations_1`
    FOREIGN KEY (`person_id`)
    REFERENCES `citizen_registrations`.`people` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_people_educations_2`
    FOREIGN KEY (`education_id`)
    REFERENCES `citizen_registrations`.`educations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
