-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Usuarios
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Usuarios` ;

-- -----------------------------------------------------
-- Schema Usuarios
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Usuarios` DEFAULT CHARACTER SET utf8 ;
USE `Usuarios` ;

-- -----------------------------------------------------
-- Table `Usuarios`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Usuarios`.`Usuario` ;

CREATE TABLE IF NOT EXISTS `Usuarios`.`Usuario` (
  `user` VARCHAR(16) NOT NULL,
  `clave` VARCHAR(20) NOT NULL,
  `nombre` VARCHAR(15) NOT NULL,
  `apellidoPaterno` VARCHAR(16) NOT NULL,
  `apellidoMaterno` VARCHAR(16) NULL,
  PRIMARY KEY (`user`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
