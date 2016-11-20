-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-10-2016 a las 05:30:18
-- Versión del servidor: 10.1.13-MariaDB
-- Versión de PHP: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `acme`
--
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--
create database tecno_phone;
use tecno_phone;

CREATE TABLE usuarios (
    id_usuarios int(4) not null auto_increment primary key,
    nombre varchar(15) not null,
    contrasena varchar(15) not null,
    tipo_usuario varchar(15) not null
    );

-- --------------------------------------------------------
insert into usuarios(nombre,contrasena,tipo_usuario) values("margarito","1234","administrador");   
--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` mediumint(8) NOT NULL,
  `nombre` varchar(25) CHARACTER SET utf8 COLLATE utf8_swedish_ci DEFAULT NULL,
  `ap_paterno` varchar(25) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `ap_materno` varchar(25) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` int(10) DEFAULT NULL,
  `email` varchar(25) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `rfc` varchar(20) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `calle` varchar(20) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `no` int(4) DEFAULT NULL,
  `colonia` varchar(15) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `ciudad` int(15) DEFAULT NULL,
  `estado` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

CREATE TABLE `compras` (
  `id_compra` mediumint(8) NOT NULL,
  `fecha` date DEFAULT NULL,
  `id_proveedror` mediumint(8) NOT NULL,
  `subtotal` int(10) DEFAULT NULL,
  `iva` int(3) DEFAULT NULL,
  `total` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_compra`
--

CREATE TABLE `detalle_compra` (
  `id_detalle_compra` mediumint(8) NOT NULL,
  `id_compra` mediumint(8) NOT NULL,
  `id_producto` mediumint(8) NOT NULL,
  `cantidad` int(10) DEFAULT NULL,
  `total_producto` int(10) DEFAULT NULL,
  `precio` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_venta`
--

CREATE TABLE `detalle_venta` (
  `id_det_venta` mediumint(8) NOT NULL,
  `id_venta` mediumint(8) NOT NULL,
  `id_producto` mediumint(8) NOT NULL,
  `cantidad` int(10) DEFAULT NULL,
  `total_producto` int(10) DEFAULT NULL,
  `precio` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prductos`
--

CREATE TABLE `prductos` (
  `id_producto` mediumint(8) NOT NULL,
  `producto` varchar(15) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `descripcion` varchar(25) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `precio_compra` int(10) DEFAULT NULL,
  `precio_venta` int(10) DEFAULT NULL,
  `existencias` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `id_proveedor` mediumint(8) NOT NULL auto_increment primary key,
  `nombre` varchar(25) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `rfc` varchar(20) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `calle` varchar(25) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `no` int(4) DEFAULT NULL,
  `colonia` varchar(25) CHARACTER SET utf8 COLLATE utf8_swedish_ci DEFAULT NULL,
  `ciudad` varchar(20) CHARACTER SET utf8 COLLATE utf8_swedish_ci DEFAULT NULL,
  `estado` varchar(20) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombre_contacto` varchar(20) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` int(10) DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------
insert into proveedores values(1,"LG","VECJ880326XXX","Camino ahuehuetitla",105,"Las presas","Tulancingo","Hidalgo","Hugo Godinez",7751575043,"Hu_Go@lg.mx.com");
--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id_venta` mediumint(8) NOT NULL,
  `fecha` date NOT NULL,
  `id_cliente` mediumint(8) DEFAULT NULL,
  `subtotal` int(10) DEFAULT NULL,
  `iva` int(3) DEFAULT NULL,
  `total` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`);

--
-- Indices de la tabla `compras`
--
ALTER TABLE `compras`
  ADD PRIMARY KEY (`id_compra`),
  ADD KEY `id_proveedor` (`id_proveedror`);

--
-- Indices de la tabla `detalle_compra`
--
ALTER TABLE `detalle_compra`
  ADD PRIMARY KEY (`id_detalle_compra`),
  ADD KEY `id_producto` (`id_producto`),
  ADD KEY `id_compra` (`id_compra`);

--
-- Indices de la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD PRIMARY KEY (`id_det_venta`),
  ADD KEY `id_venta` (`id_venta`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `prductos`
--
ALTER TABLE `prductos`
  ADD PRIMARY KEY (`id_producto`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`id_proveedor`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id_venta`),
  ADD KEY `id_index` (`id_cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_cliente` mediumint(8) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `compras`
--
ALTER TABLE `compras`
  MODIFY `id_compra` mediumint(8) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `detalle_compra`
--
ALTER TABLE `detalle_compra`
  MODIFY `id_detalle_compra` mediumint(8) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  MODIFY `id_det_venta` mediumint(8) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `prductos`
--
ALTER TABLE `prductos`
  MODIFY `id_producto` mediumint(8) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id_proveedor` mediumint(8) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id_venta` mediumint(8) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
