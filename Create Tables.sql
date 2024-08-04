SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cs204301ateam1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cs204301ateam1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cs204301ateam1` DEFAULT CHARACTER SET latin1 ;
USE `cs204301ateam1` ;

-- -----------------------------------------------------
-- Table `cs204301ateam1`.`BookInfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs204301ateam1`.`BookInfo` ;

CREATE TABLE IF NOT EXISTS `cs204301ateam1`.`BookInfo` (
  `BookID` INT NOT NULL AUTO_INCREMENT,
  `Isbn` VARCHAR(45) NULL,
  `Title` VARCHAR(45) NULL,
  `Author` VARCHAR(45) NULL,
  PRIMARY KEY (`BookID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs204301ateam1`.`MemberProfile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs204301ateam1`.`MemberProfile` ;

CREATE TABLE IF NOT EXISTS `cs204301ateam1`.`MemberProfile` (
  `MemberID` VARCHAR(25) NOT NULL,
  `Name` VARCHAR(45) NULL,
  `Location` VARCHAR(45) NULL,
  `Email` VARCHAR(45) NULL,
  `PhoneNumber` VARCHAR(45) NULL,
  `Bio` VARCHAR(300) NULL,
  `Password` VARCHAR(40) NULL,
  PRIMARY KEY (`MemberID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs204301ateam1`.`PostInfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs204301ateam1`.`PostInfo` ;

CREATE TABLE IF NOT EXISTS `cs204301ateam1`.`PostInfo` (
  `PostID` INT NOT NULL AUTO_INCREMENT,
  `Date` DATE NULL,
  `Description` VARCHAR(250) NULL,
  `BookID` INT NOT NULL,
  `MemberID` VARCHAR(25) NOT NULL,
  `Title` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`PostID`),
  CONSTRAINT `fk_PostInfo_BookInfo1`
    FOREIGN KEY (`BookID`)
    REFERENCES `cs204301ateam1`.`BookInfo` (`BookID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_PostInfo_MemberProfile1`
    FOREIGN KEY (`MemberID`)
    REFERENCES `cs204301ateam1`.`MemberProfile` (`MemberID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs204301ateam1`.`WishList`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs204301ateam1`.`WishList` ;

CREATE TABLE IF NOT EXISTS `cs204301ateam1`.`WishList` (
  `PostID` INT NOT NULL,
  `MemberID` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`PostID`, `MemberID`),
  CONSTRAINT `fk_WishList_PostInfo1`
    FOREIGN KEY (`PostID`)
    REFERENCES `cs204301ateam1`.`PostInfo` (`PostID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_WishList_MemberProfile1`
    FOREIGN KEY (`MemberID`)
    REFERENCES `cs204301ateam1`.`MemberProfile` (`MemberID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs204301ateam1`.`MessageInfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs204301ateam1`.`MessageInfo` ;

CREATE TABLE IF NOT EXISTS `cs204301ateam1`.`MessageInfo` (
  `Message` VARCHAR(300) NOT NULL,
  `Date` DATETIME NOT NULL,
  `MemberID1` VARCHAR(25) NOT NULL,
  `MemberID2` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`MemberID1`, `MemberID2`, `Date`),
  CONSTRAINT `fk_MessageInfo_MemberProfile1`
    FOREIGN KEY (`MemberID1`)
    REFERENCES `cs204301ateam1`.`MemberProfile` (`MemberID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_MessageInfo_MemberProfile2`
    FOREIGN KEY (`MemberID2`)
    REFERENCES `cs204301ateam1`.`MemberProfile` (`MemberID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs204301ateam1`.`AdminRecord`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs204301ateam1`.`AdminRecord` ;

CREATE TABLE IF NOT EXISTS `cs204301ateam1`.`AdminRecord` (
  `AdminID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL,
  `Rank` INT NULL,
  `Email` VARCHAR(45) NULL,
  `PhoneNumber` VARCHAR(15) NULL,
  `Password` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`AdminID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs204301ateam1`.`Reports`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs204301ateam1`.`Reports` ;

CREATE TABLE IF NOT EXISTS `cs204301ateam1`.`Reports` (
  `ReportID` INT NOT NULL AUTO_INCREMENT,
  `PostID` INT NOT NULL,
  `PostReason` VARCHAR(45) NULL,
  `MemberID` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`ReportID`),
  CONSTRAINT `fk_Reports_PostInfo1`
    FOREIGN KEY (`PostID`)
    REFERENCES `cs204301ateam1`.`PostInfo` (`PostID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Reports_MemberProfile1`
    FOREIGN KEY (`MemberID`)
    REFERENCES `cs204301ateam1`.`MemberProfile` (`MemberID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

