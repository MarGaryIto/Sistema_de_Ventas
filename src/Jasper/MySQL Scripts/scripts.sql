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

INSERT INTO MARCAS (IDMARCA, NOMBRE, PAIS) VALUES (01, "Ford", "USA");
INSERT INTO MARCAS (IDMARCA, NOMBRE, PAIS) VALUES (02, "Chevrolet", "USA");
INSERT INTO MARCAS (IDMARCA, NOMBRE, PAIS) VALUES (03, "Dodge", "USA");
INSERT INTO MARCAS (IDMARCA, NOMBRE, PAIS) VALUES (04, "Honda", "JPN");
INSERT INTO MARCAS (IDMARCA, NOMBRE, PAIS) VALUES (05, "Nissan", "JPN");

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (01, "Focus", 2014, 10000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (01, "Ranger", 2002, 15600);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (01, "Mustang", 1998, 60000);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (01, "Mustang", 2000, 12300);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (01, "Mustang", 2005, 20000);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (01, "Mustang", 2010, 23500);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (01, "Mustang", 2015, 12000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (02, "Spark", 2002, 36000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (02, "Spark", 2014, 15000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (02, "Malibu", 2010, 12000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (02, "Camaro", 1998, 23000);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (02, "Corvette", 1990, 36600);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (02, "Corvette", 1992, 58000);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (02, "Corvette", 1993, 68000);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (02, "Corvette", 1994, 76000);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (02, "Corvette", 1997, 60000);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (02, "Corvette", 2000, 54000);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (02, "Corvette", 2002, 65000);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (02, "Corvette", 2008, 70000);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (02, "Corvette", 2010, 65000);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (02, "Corvette", 2014, 80000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (03, "Viper", 1992, 90000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (03, "Viper", 1993, 98500);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (03, "Viper", 1994, 100000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (03, "Viper", 1996, 95000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (03, "Viper", 1998, 87000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (03, "Viper", 2001, 110000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (03, "Viper", 2002, 98000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (03, "Viper", 2003, 90000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (03, "Viper", 2004, 98000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (03, "Viper", 2009, 99000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (03, "Viper", 2010, 120000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (03, "Durango", 2014, 21000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (03, "Charger", 2015, 25000);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (03, "Challenger", 1998, 36000);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (03, "Challenger", 2010, 45000);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (03, "Challenger", 2014, 30000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (03, "Challenger", 2015, 55000);



INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (04, "Civic", 2000, 22000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (04, "Civic", 2001, 36000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (04, "Civic", 2002, 21000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (04, "Civic", 2010, 31000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (04, "2000", 2000, 35200);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (04, "Accord", 2014, 10000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (04, "CR-V", 2014, 16000);


INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (05, "GT-R", 1990, 66000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (05, "GT-R", 1991, 66000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (05, "GT-R", 1992, 66000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (05, "GT-R", 1993, 65000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (05, "GT-R", 1994, 66000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (05, "GT-R", 1995, 66000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (05, "GT-R", 1996, 68000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (05, "GT-R", 1997, 66000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (05, "GT-R", 1998, 66000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (05, "GT-R", 1999, 61000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (05, "GT-R", 2000, 66000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (05, "GT-R", 2001, 66000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (05, "GT-R", 2002, 62000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (05, "GT-R", 2014, 66000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (05, "GT-R", 2015, 66000);

INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (05, "370-Z", 2013, 36000);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (05, "Pulsar", 2013, 26000);
INSERT INTO MODELOS (MARCA, MODELO, ANNO, PRECIO) VALUES (05, "Micra", 2010, 16000);

INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (04, "Negro", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (04, "Amarillo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (04, "Negro", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (04, "Blanco", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (05, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (05, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (05, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (06, "Negro", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (07, "Negro", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (07, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (07, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (08, "Verde", "Disponible");

INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (13, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (13, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (13, "Verde", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (13, "Amarillo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (13, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (14, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (14, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (14, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (14, "Amarillo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (15, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (15, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (15, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (15, "Negro", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (16, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (17, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (17, "Amarillo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (17, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (17, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (18, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (18, "Amarillo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (18, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (18, "Negro", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (18, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (19, "Amarillo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (19, "Negro", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (19, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (19, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (20, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (21, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (21, "Negro", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (21, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (22, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (22, "Negro", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (22, "Azul", "Disponible");

INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (23, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (23, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (23, "Amarillo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (24, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (24, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (24, "Negro", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (24, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (25, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (26, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (26, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (26, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (27, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (27, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (28, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (29, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (29, "Amarillo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (30, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (30, "Blanco", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (31, "Verde", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (32, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (33, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (34, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (34, "Amarillo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (34, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (34, "Azul", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (34, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (34, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (35, "Verde", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (36, "Rojo", "Disponible"); 
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (36, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (36, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (36, "Rojo", "Disponible");

INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (44, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (45, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (46, "Rojo", "Disponible");
INSERT INTO AUTOMOVILES (MODELO, COLOR, ESTADO) VALUES (47, "Rojo", "Disponible");