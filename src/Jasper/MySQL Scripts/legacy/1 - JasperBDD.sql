-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema jasper_bdd
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema jasper_bdd
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jasper_bdd` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `jasper_bdd` ;

-- -----------------------------------------------------
-- Table `jasper_bdd`.`Marcas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jasper_bdd`.`Marcas` ;

CREATE TABLE IF NOT EXISTS `jasper_bdd`.`Marcas` (
  `idMarca` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `Nombre` VARCHAR(45) NOT NULL COMMENT '',
  `Pais` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`idMarca`)  COMMENT '',
  UNIQUE INDEX `Nombre_UNIQUE` (`Nombre` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jasper_bdd`.`Modelos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jasper_bdd`.`Modelos` ;

CREATE TABLE IF NOT EXISTS `jasper_bdd`.`Modelos` (
  `idModelo` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `Marca` INT NOT NULL COMMENT '',
  `Modelo` VARCHAR(45) NOT NULL COMMENT '',
  `Anno` YEAR NOT NULL COMMENT '',
  `Precio` INT NOT NULL COMMENT '',
  INDEX `fk_Modelos_Marcas_idx` (`Marca` ASC)  COMMENT '',
  PRIMARY KEY (`idModelo`)  COMMENT '',
  CONSTRAINT `fk_Modelos_Marcas`
    FOREIGN KEY (`Marca`)
    REFERENCES `jasper_bdd`.`Marcas` (`idMarca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jasper_bdd`.`Automoviles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jasper_bdd`.`Automoviles` ;

CREATE TABLE IF NOT EXISTS `jasper_bdd`.`Automoviles` (
  `Placa` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `Modelo` INT NOT NULL COMMENT '',
  `Color` VARCHAR(45) NOT NULL COMMENT '',
  `Estado` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`Placa`)  COMMENT '',
  INDEX `fk_Automoviles_Modelos1_idx` (`Modelo` ASC)  COMMENT '',
  CONSTRAINT `fk_Automoviles_Modelos1`
    FOREIGN KEY (`Modelo`)
    REFERENCES `jasper_bdd`.`Modelos` (`idModelo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jasper_bdd`.`Clientes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jasper_bdd`.`Clientes` ;

CREATE TABLE IF NOT EXISTS `jasper_bdd`.`Clientes` (
  `idCliente` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `Nombre` VARCHAR(45) NOT NULL COMMENT '',
  `Cedula` VARCHAR(45) NOT NULL COMMENT '',
  `Numero_Tarjeta` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`idCliente`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jasper_bdd`.`Factura`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jasper_bdd`.`Factura` ;

CREATE TABLE IF NOT EXISTS `jasper_bdd`.`Factura` (
  `Cliente` INT NOT NULL COMMENT '',
  `Fecha` DATETIME NOT NULL COMMENT '',
  INDEX `fk_Factura_Clientes1_idx` (`Cliente` ASC)  COMMENT '',
  PRIMARY KEY (`Cliente`, `Fecha`)  COMMENT '',
  CONSTRAINT `fk_Factura_Clientes1`
    FOREIGN KEY (`Cliente`)
    REFERENCES `jasper_bdd`.`Clientes` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jasper_bdd`.`Autos_Comprados`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jasper_bdd`.`Autos_Comprados` ;

CREATE TABLE IF NOT EXISTS `jasper_bdd`.`Autos_Comprados` (
  `Placa` INT NOT NULL COMMENT '',
  `Cliente` INT NOT NULL COMMENT '',
  `Fecha` DATETIME NOT NULL COMMENT '',
  INDEX `fk_Autos_Comprados_Automoviles1_idx` (`Placa` ASC)  COMMENT '',
  INDEX `fk_Autos_Comprados_Factura1_idx` (`Cliente` ASC, `Fecha` ASC)  COMMENT '',
  PRIMARY KEY (`Fecha`, `Placa`, `Cliente`)  COMMENT '',
  CONSTRAINT `fk_Autos_Comprados_Automoviles1`
    FOREIGN KEY (`Placa`)
    REFERENCES `jasper_bdd`.`Automoviles` (`Placa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Autos_Comprados_Factura1`
    FOREIGN KEY (`Cliente` , `Fecha`)
    REFERENCES `jasper_bdd`.`Factura` (`Cliente` , `Fecha`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
