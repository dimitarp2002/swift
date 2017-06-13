-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema School
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema School
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `School` DEFAULT CHARACTER SET big5 ;
USE `School` ;

-- -----------------------------------------------------
-- Table `School`.`addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `School`.`addresses` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `street` VARCHAR(45) NULL DEFAULT NULL,
  `number` VARCHAR(45) NULL DEFAULT NULL,
  `floor` INT(11) NULL DEFAULT NULL,
  `apartment_no` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `School`.`disciplines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `School`.`disciplines` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `School`.`students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `School`.`students` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `enrollment_date` DATE NULL DEFAULT NULL,
  `address_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `address_id_UNIQUE` (`address_id` ASC),
  CONSTRAINT `fk_students`
    FOREIGN KEY (`address_id`)
    REFERENCES `School`.`addresses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `School`.`disciplines_studied`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `School`.`disciplines_studied` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `student_id` INT(11) NULL DEFAULT NULL,
  `discipline_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_disciplines_studied_1_idx` (`student_id` ASC),
  INDEX `fk_disciplines_studied_2_idx` (`discipline_id` ASC),
  CONSTRAINT `fk_discipline_id`
    FOREIGN KEY (`discipline_id`)
    REFERENCES `School`.`disciplines` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_students_id`
    FOREIGN KEY (`student_id`)
    REFERENCES `School`.`students` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `School`.`teachers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `School`.`teachers` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `salary` FLOAT NULL DEFAULT NULL COMMENT '\n',
  `address_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `address_id_UNIQUE` (`address_id` ASC),
  CONSTRAINT `fk_teachers`
    FOREIGN KEY (`address_id`)
    REFERENCES `School`.`addresses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `School`.`disciplines_taught`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `School`.`disciplines_taught` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `teacher_id` INT(11) NULL DEFAULT NULL,
  `discipline_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_disciplines_taught_1_idx` (`teacher_id` ASC),
  INDEX `fk_disciplines_taught_2_idx` (`discipline_id` ASC),
  CONSTRAINT `fk_disciplines_id`
    FOREIGN KEY (`discipline_id`)
    REFERENCES `School`.`disciplines` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_teacher_id`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `School`.`teachers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = big5;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
