
create database compras;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `compras`
--





CREATE TABLE `tab_categoria` (
  `id_categoria` int(11) NOT NULL,
  `nombre_categoria` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tab_categoria`
--

INSERT INTO `tab_categoria` (`id_categoria`, `nombre_categoria`) VALUES
(11, 'componentes'),
(12, 'perifericos'),
(13, 'accesorios'),
(14, 'impresoras'),
(16, 'computadoras');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tab_departamento`
--

CREATE TABLE `tab_departamento` (
  `id_departamento` int(11) NOT NULL,
  `nombre_departamento` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tab_departamento`
--

INSERT INTO `tab_departamento` (`id_departamento`, `nombre_departamento`) VALUES
(1, 'logistica'),
(2, 'compras'),
(3, 'ventas'),
(4, 'gerencia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tab_detalle_venta`
--

CREATE TABLE `tab_detalle_venta` (
  `id_detalle` int(11) NOT NULL,
  `id_venta` int(11) NOT NULL,
  `fecha` varchar(100) DEFAULT NULL,
  `comprador` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tab_detalle_venta`
--

INSERT INTO `tab_detalle_venta` (`id_detalle`, `id_venta`, `fecha`, `comprador`) VALUES
(6, 11, '2019-02-02', 'jonathan');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tab_estado`
--

CREATE TABLE `tab_estado` (
  `id_estado` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tab_estado`
--

INSERT INTO `tab_estado` (`id_estado`, `nombre`) VALUES
(8, 'Activo'),
(9, 'InActivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tab_estado_global`
--

CREATE TABLE `tab_estado_global` (
  `id_estado_global` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tab_estado_global`
--

INSERT INTO `tab_estado_global` (`id_estado_global`, `nombre`) VALUES
(1, 'activo'),
(2, 'inactivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tab_estado_pedido`
--

CREATE TABLE `tab_estado_pedido` (
  `id_estado_pedido` int(11) NOT NULL,
  `codigo_pedido` int(11) NOT NULL,
  `fecha_estado` varchar(100) DEFAULT NULL,
  `estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tab_gerencia`
--

CREATE TABLE `tab_gerencia` (
  `id_gerencia` int(11) NOT NULL,
  `departamento` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `cod_empleado` varchar(100) DEFAULT NULL,
  `usuario` varchar(100) DEFAULT NULL,
  `pass` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tab_gerencia`
--

INSERT INTO `tab_gerencia` (`id_gerencia`, `departamento`, `nombre`, `apellido`, `cod_empleado`, `usuario`, `pass`) VALUES
(1, 4, 'sergio', 'torres', '0001', 'sergio', '1234'),
(2, 4, 'jonathan', 'rodriguez', '0002', 'jony', '123'),
(3, 4, 'roberto', 'hernandez', '0003', 'roby', '1234'),
(4, 4, 'Geovanny', 'Fuentes', '0004', 'geo', 'huevos123'),
(36, 4, 'pro', 'fesional', '0005', 'pro', '123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tab_giro`
--

CREATE TABLE `tab_giro` (
  `id_giro` int(11) NOT NULL,
  `nombre_giro` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tab_giro`
--

INSERT INTO `tab_giro` (`id_giro`, `nombre_giro`) VALUES
(21, 'software'),
(27, 'pesca'),
(29, 'aviomn'),
(30, 'asdf'),
(31, 'ffffdsa'),
(32, 'fff');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tab_inventario`
--

CREATE TABLE `tab_inventario` (
  `id_inventario` int(11) NOT NULL,
  `producto` varchar(100) NOT NULL,
  `stock` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tab_inventario`
--

INSERT INTO `tab_inventario` (`id_inventario`, `producto`, `stock`) VALUES
(7, '01', 102);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tab_pedido`
--

CREATE TABLE `tab_pedido` (
  `codigo_pedido` int(11) NOT NULL,
  `proyeccion` int(11) DEFAULT NULL,
  `gerencia` int(11) DEFAULT NULL,
  `fecha_pedido` date DEFAULT NULL,
  `iva` double(7,2) DEFAULT NULL,
  `total` double(7,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tab_producto`
--

CREATE TABLE `tab_producto` (
  `codigo` varchar(100) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `categoria` int(11) NOT NULL,
  `provedor` int(11) NOT NULL,
  `precio` double(7,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tab_producto`
--

INSERT INTO `tab_producto` (`codigo`, `nombre`, `categoria`, `provedor`, `precio`) VALUES
('01', 'impresora', 11, 28, 122.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tab_provedor`
--

CREATE TABLE `tab_provedor` (
  `id_provedor` int(11) NOT NULL,
  `nombre_proveedor` varchar(100) DEFAULT NULL,
  `telefono` varchar(100) DEFAULT NULL,
  `giros` int(11) NOT NULL,
  `estado_global` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tab_provedor`
--

INSERT INTO `tab_provedor` (`id_provedor`, `nombre_proveedor`, `telefono`, `giros`, `estado_global`) VALUES
(28, 'josue', '2123-2323', 21, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tab_proyeccion`
--

CREATE TABLE `tab_proyeccion` (
  `id_proyeccion` int(11) NOT NULL,
  `inventario` int(11) DEFAULT NULL,
  `venta` int(11) DEFAULT NULL,
  `proyeccionmensual` int(11) DEFAULT NULL,
  `necesidadcompra` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tab_proyeccion`
--

INSERT INTO `tab_proyeccion` (`id_proyeccion`, `inventario`, `venta`, `proyeccionmensual`, `necesidadcompra`) VALUES
(47, 7, 6, 0, 0),
(48, 7, 6, 250, 250),
(49, 7, 6, 250, 250),
(50, 7, 6, 75, -6),
(51, 7, 6, 75, 62);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tab_venta`
--

CREATE TABLE `tab_venta` (
  `id_venta` int(11) NOT NULL,
  `codigo` varchar(100) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `total` double(7,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tab_venta`
--

INSERT INTO `tab_venta` (`id_venta`, `codigo`, `cantidad`, `total`) VALUES
(11, '01', 12, 12.00);

-- --------------------------------------------------------

--
-- Estructura para la vista `asdas`
--
DROP TABLE IF EXISTS `asdas`;

CREATE ALGORITHM=UNDEFINED  SQL SECURITY DEFINER VIEW `asdas`  AS  select `tab_producto`.`codigo` AS `codigo`,`tab_producto`.`nombre` AS `nombre`,`tab_producto`.`categoria` AS `categoria`,`tab_producto`.`provedor` AS `provedor`,`tab_producto`.`precio` AS `precio` from `tab_producto` ;

-- --------------------------------------------------------

--
-- Estructura para la vista `detavent`
--
DROP TABLE IF EXISTS `detavent`;

CREATE ALGORITHM=UNDEFINED  SQL SECURITY DEFINER VIEW `detavent`  AS  select `deta`.`id_detalle` AS `id_detalle`,`vent`.`codigo` AS `codigo`,`produc`.`nombre` AS `nombre`,`produc`.`precio` AS `precio`,`inventario`.`stock` AS `stock`,`vent`.`cantidad` AS `cantidad` from (((`tab_detalle_venta` `deta` join `tab_venta` `vent` on((`deta`.`id_venta` = `vent`.`id_venta`))) join `tab_producto` `produc` on((`vent`.`codigo` = `produc`.`codigo`))) join `tab_inventario` `inventario` on((`produc`.`codigo` = `inventario`.`producto`))) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `inventario_producto`
--
DROP TABLE IF EXISTS `inventario_producto`;

CREATE ALGORITHM=UNDEFINED  SQL SECURITY DEFINER VIEW `inventario_producto`  AS  select `a`.`id_inventario` AS `id_inventario`,`b`.`codigo` AS `codigo`,`b`.`nombre` AS `nombre`,`c`.`nombre_categoria` AS `nombre_categoria`,`d`.`nombre_proveedor` AS `nombre_proveedor`,`b`.`precio` AS `precio`,`a`.`stock` AS `stock` from (((`tab_producto` `b` join `tab_inventario` `a` on((`a`.`producto` = `b`.`codigo`))) join `tab_categoria` `c` on((`b`.`categoria` = `c`.`id_categoria`))) join `tab_provedor` `d` on((`b`.`provedor` = `d`.`id_provedor`))) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `pedido_proyeccion`
--
DROP TABLE IF EXISTS `pedido_proyeccion`;

CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `pedido_proyeccion`  AS  select `inventario`.`id_inventario` AS `id_inventario`,`produc`.`codigo` AS `codigo`,`produc`.`nombre` AS `nombre`,`inventario`.`stock` AS `stock` from (((`tab_detalle_venta` `deta` join `tab_venta` `vent` on((`deta`.`id_venta` = `vent`.`id_venta`))) join `tab_producto` `produc` on((`vent`.`codigo` = `produc`.`codigo`))) join `tab_inventario` `inventario` on((`produc`.`codigo` = `inventario`.`producto`))) ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tab_categoria`
--
ALTER TABLE `tab_categoria`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indices de la tabla `tab_departamento`
--
ALTER TABLE `tab_departamento`
  ADD PRIMARY KEY (`id_departamento`);

--
-- Indices de la tabla `tab_detalle_venta`
--
ALTER TABLE `tab_detalle_venta`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `id_venta` (`id_venta`);

--
-- Indices de la tabla `tab_estado`
--
ALTER TABLE `tab_estado`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indices de la tabla `tab_estado_global`
--
ALTER TABLE `tab_estado_global`
  ADD PRIMARY KEY (`id_estado_global`);

--
-- Indices de la tabla `tab_estado_pedido`
--
ALTER TABLE `tab_estado_pedido`
  ADD PRIMARY KEY (`id_estado_pedido`),
  ADD KEY `codigo_pedido` (`codigo_pedido`),
  ADD KEY `estado` (`estado`);

--
-- Indices de la tabla `tab_gerencia`
--
ALTER TABLE `tab_gerencia`
  ADD PRIMARY KEY (`id_gerencia`),
  ADD KEY `departamento` (`departamento`);

--
-- Indices de la tabla `tab_giro`
--
ALTER TABLE `tab_giro`
  ADD PRIMARY KEY (`id_giro`);

--
-- Indices de la tabla `tab_inventario`
--
ALTER TABLE `tab_inventario`
  ADD PRIMARY KEY (`id_inventario`),
  ADD KEY `producto` (`producto`);

--
-- Indices de la tabla `tab_pedido`
--
ALTER TABLE `tab_pedido`
  ADD PRIMARY KEY (`codigo_pedido`),
  ADD KEY `proyeccion` (`proyeccion`),
  ADD KEY `gerencia` (`gerencia`);

--
-- Indices de la tabla `tab_producto`
--
ALTER TABLE `tab_producto`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `categoria` (`categoria`),
  ADD KEY `provedor` (`provedor`);

--
-- Indices de la tabla `tab_provedor`
--
ALTER TABLE `tab_provedor`
  ADD PRIMARY KEY (`id_provedor`),
  ADD KEY `giros` (`giros`),
  ADD KEY `estado_global_idx` (`estado_global`);

--
-- Indices de la tabla `tab_proyeccion`
--
ALTER TABLE `tab_proyeccion`
  ADD PRIMARY KEY (`id_proyeccion`),
  ADD KEY `inventario` (`inventario`),
  ADD KEY `venta` (`venta`);

--
-- Indices de la tabla `tab_venta`
--
ALTER TABLE `tab_venta`
  ADD PRIMARY KEY (`id_venta`),
  ADD KEY `codigo` (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tab_categoria`
--
ALTER TABLE `tab_categoria`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `tab_departamento`
--
ALTER TABLE `tab_departamento`
  MODIFY `id_departamento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `tab_detalle_venta`
--
ALTER TABLE `tab_detalle_venta`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tab_estado`
--
ALTER TABLE `tab_estado`
  MODIFY `id_estado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `tab_estado_global`
--
ALTER TABLE `tab_estado_global`
  MODIFY `id_estado_global` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tab_estado_pedido`
--
ALTER TABLE `tab_estado_pedido`
  MODIFY `id_estado_pedido` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tab_gerencia`
--
ALTER TABLE `tab_gerencia`
  MODIFY `id_gerencia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `tab_giro`
--
ALTER TABLE `tab_giro`
  MODIFY `id_giro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT de la tabla `tab_inventario`
--
ALTER TABLE `tab_inventario`
  MODIFY `id_inventario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `tab_pedido`
--
ALTER TABLE `tab_pedido`
  MODIFY `codigo_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `tab_provedor`
--
ALTER TABLE `tab_provedor`
  MODIFY `id_provedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `tab_proyeccion`
--
ALTER TABLE `tab_proyeccion`
  MODIFY `id_proyeccion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT de la tabla `tab_venta`
--
ALTER TABLE `tab_venta`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tab_detalle_venta`
--
ALTER TABLE `tab_detalle_venta`
  ADD CONSTRAINT `tab_detalle_venta_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `tab_venta` (`id_venta`);

--
-- Filtros para la tabla `tab_estado_pedido`
--
ALTER TABLE `tab_estado_pedido`
  ADD CONSTRAINT `tab_estado_pedido_ibfk_1` FOREIGN KEY (`codigo_pedido`) REFERENCES `tab_pedido` (`codigo_pedido`),
  ADD CONSTRAINT `tab_estado_pedido_ibfk_2` FOREIGN KEY (`estado`) REFERENCES `tab_estado` (`id_estado`);

--
-- Filtros para la tabla `tab_gerencia`
--
ALTER TABLE `tab_gerencia`
  ADD CONSTRAINT `tab_gerencia_ibfk_1` FOREIGN KEY (`departamento`) REFERENCES `tab_departamento` (`id_departamento`);

--
-- Filtros para la tabla `tab_inventario`
--
ALTER TABLE `tab_inventario`
  ADD CONSTRAINT `tab_inventario_ibfk_1` FOREIGN KEY (`producto`) REFERENCES `tab_producto` (`codigo`);

--
-- Filtros para la tabla `tab_pedido`
--
ALTER TABLE `tab_pedido`
  ADD CONSTRAINT `tab_pedido_ibfk_1` FOREIGN KEY (`proyeccion`) REFERENCES `tab_proyeccion` (`id_proyeccion`),
  ADD CONSTRAINT `tab_pedido_ibfk_2` FOREIGN KEY (`gerencia`) REFERENCES `tab_gerencia` (`id_gerencia`);

--
-- Filtros para la tabla `tab_producto`
--
ALTER TABLE `tab_producto`
  ADD CONSTRAINT `tab_producto_ibfk_1` FOREIGN KEY (`categoria`) REFERENCES `tab_categoria` (`id_categoria`),
  ADD CONSTRAINT `tab_producto_ibfk_2` FOREIGN KEY (`provedor`) REFERENCES `tab_provedor` (`id_provedor`);

--
-- Filtros para la tabla `tab_provedor`
--
ALTER TABLE `tab_provedor`
  ADD CONSTRAINT `estado_global` FOREIGN KEY (`estado_global`) REFERENCES `tab_estado_global` (`id_estado_global`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `giros` FOREIGN KEY (`giros`) REFERENCES `tab_giro` (`id_giro`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tab_proyeccion`
--
ALTER TABLE `tab_proyeccion`
  ADD CONSTRAINT `inventario` FOREIGN KEY (`inventario`) REFERENCES `tab_inventario` (`id_inventario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `venta` FOREIGN KEY (`venta`) REFERENCES `tab_detalle_venta` (`id_detalle`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tab_venta`
--
ALTER TABLE `tab_venta`
  ADD CONSTRAINT `tab_venta_ibfk_1` FOREIGN KEY (`codigo`) REFERENCES `tab_producto` (`codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
