-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-03-2019 a las 18:30:07
-- Versión del servidor: 10.1.37-MariaDB
-- Versión de PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

create database compras;
use compras;
--
-- Base de datos: `compras`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE  PROCEDURE `pedido` (IN `xgerencia` INT, IN `xfechapedido` DATE, IN `xiva` DOUBLE, IN `xtotal` DOUBLE, IN `inventario` INT, IN `venta` INT, IN `proyeccionmensual` INT, IN `necesidadcompra` INT)  BEGIN
insert into tab_proyeccion  values (inventario,venta,proyeccionmensual,necesidadcompra);
insert into tab_pedido  values (last_insert_id(tab_proyeccion.id_proyeccion),xgerencia,xfechapedido,xiva,xtotal);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `chartsell`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `chartsell` (
`codigo` varchar(100)
,`nombre` varchar(100)
,`cantidad` int(11)
,`total` double(7,2)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `det_vent`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `det_vent` (
`id_detalle` int(11)
,`codigo` varchar(100)
,`nombre` varchar(100)
,`comprador` varchar(100)
,`precio` double(7,2)
,`stock` int(11)
,`cantidad` int(11)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `inventario_producto`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `inventario_producto` (
`id_inventario` int(11)
,`codigo` varchar(100)
,`nombre` varchar(100)
,`nombre_categoria` varchar(100)
,`nombre_proveedor` varchar(100)
,`precio` double(7,2)
,`stock` int(11)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `pedido_proyeccion`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `pedido_proyeccion` (
`id_inventario` int(11)
,`codigo` varchar(100)
,`nombre` varchar(100)
,`stock` int(11)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tab_categoria`
--

CREATE TABLE `tab_categoria` (
  `id_categoria` int(11) NOT NULL,
  `nombre_categoria` varchar(100) DEFAULT NULL,
  `descripcion` varchar(2500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tab_categoria`
--

INSERT INTO `tab_categoria` (`id_categoria`, `nombre_categoria`, `descripcion`) VALUES
(1, 'Clase 01', 'Productos químicos destinados a la industria, ciencia, fotografía, horticultura y silvicultura; resinas artificiales en estado bruto, materias plásticas en estado bruto; abono para las tierras; composiciones extintoras; preparaciones para el temple y soldadura de metales; productos químicos destinados a conservar los alimentos; materias curtientes; adhesivos (pegamentos) destinados a la industria.'),
(2, 'Clase 02', 'Colores, barnices, lacas; conservantes contra la herrumbre y el deterioro de la madera; materias tintóreas; mordientes; resinas naturales en estado bruto; metales en hojas y en polvo para pintores, decoradores, impresores y artistas.'),
(3, 'Clase 03', 'Preparaciones para blanquear y otras sustancias para la colada; preparaciones para limpiar, pulir, desengrasar y raspar; jabones; perfumería, aceites esenciales, cosméticos, lociones para el cabello; dentífricos.'),
(4, 'Clase 04', 'Aceites y grasas industriales; lubricantes; productos para absorber, regar y concentrar el polvo; combustibles (incluyendo gasolinas para motores) y materias de alumbrado; bujías, mechas.'),
(5, 'Clase 05', 'Productos farmacéuticos, veterinarios e higiénicos; sustancias dietéticas para uso médico, alimentos para bebes; emplastos, material para apósitos; material para empastar los dientes y para moldes dentales; desinfectantes; productos para la destrucción de animales dañinos; fungicidas, herbicidas'),
(6, 'Clase 06', 'Metales comunes y sus aleaciones; materiales de construcción metálicos; construcciones transportables metálicas; materiales metálicos para vías férreas; cables e hilos metálicos no eléctricos; cerrajería y ferretería metálica; tubos metálicos; cajas de caudales; productos metálicosno comprendidos en otras clases; minerales.'),
(7, 'Clase 07', 'Máquinas y máquinas herramientas; motores (excepto motores para vehículos terrestres); acoplamientos y órganos de transmisión (excepto para vehículos terrestres); instrumentos agrícolas; incubadoras de huevos.'),
(8, 'Clase 08', 'Herramientas e instrumentos de mano impulsados manualmente; cuchillería, tenedores y cucharas; armas blancas; maquinillas de afeitar.'),
(9, 'Clase 09', 'Aparatos e instrumentos científicos, náuticos, geodésicos, eléctricos, fotográficos, cinematográficos, ópticos, de pesar, de medida, de señalización, de control (inspección), de socorro (salvamento) y de enseñanza; aparatos para el registro, transmisión, reproducción de sonido o imágenes; soportes de registro magnéticos, discos acústicos; distribuidores automáticos y mecanismos para aparatos de previo pago; cajas registradoras, máquinas calculadoras, equipo para el tratamiento de la información y ordenadores; extintores.'),
(10, 'Clase 10', 'Aparatos e instrumentos quirúrgicos, médicos, dentales y veterinarios, miembros, ojos y dientes artificiales; artículos ortopédicos; material de sutura.'),
(11, 'Clase 11', 'Aparatos de alumbrado, de calefacción, de producción de vapor, de cocción, de refrigeración, de secado, de ventilación, de distribución de agua e instalaciones sanitarias.'),
(12, 'Clase 12', 'Vehículos; aparatos de locomoción terrestre, aérea o marítima.'),
(13, 'Clase 13', 'Armas de fuego; municiones y proyectiles; explosivos; fuegos de artificio'),
(14, 'Clase 14', 'Metales preciosos y sus aleaciones y artículos de estas materias o de chapado no comprendidos en otras clases; joyería, bisutería, piedras preciosas; relojería e instrumentos cronométricos.'),
(15, 'Clase 15', 'Instrumentos de música.'),
(16, 'Clase 16', 'Papel, cartón y artículos de estas materias, no comprendidos en otras clases; productos de imprenta; artículos de encuadernación; fotografías; papelería; adhesivos (pegamentos) para la papelería o para la casa; material para artistas; pinceles; máquinas de escribir y artículos de oficina (excepto muebles); material de instrucción o de enseñanza (excepto aparatos); materias plásticas para embalaje (no comprendidas en otras clases); naipes; caracteres de imprenta; clichés.'),
(17, 'Clase 17', 'Caucho, gutapercha, goma, amianto, mica y productos de estas materias no comprendidos en otras clases; productos en materias plásticas semielaboradas; materias que sirven para clafatear, cerrar con estopa y aislar; tubos flexibles no metálicos.'),
(18, 'Clase 18', 'Cuero e imitaciones de cuero, productos de estas materias no comprendidos en otras clases; pieles de animales, baúles y maletas; paraguas, sombrillas y bastones; fustas y guarnicionería.'),
(19, 'Clase 19', 'Materiales de construcción no metálicos; tubos rígidos no metálicos para la construcción; asfalto, pez y betún; construcciones transportables no metálicas; monumentos no metálicos.'),
(20, 'Clase 20', 'Muebles, espejos, marcos; productos, no comprendidos en otras clases, de madera, corcho, caña, junco, mimbre, cuerno, hueso, marfil, ballena, concha, ámbar, nácar, espuma de mar, sucedáneos de todas estas materias o materias plásticas.'),
(21, 'Clase 21', 'Utensilios y recipientes para el menaje o la cocina (que no sean de metales preciosos ni chapados); peines y esponjas; cepillos (excepto pinceles); materiales para la fabricación de cepillos; material de limpieza; viruta de hierro; vidrio en bruto o semielaborado (excepto vidrio de construcción); cristalería, porcelana y loza, no comprendidas en otras clases'),
(22, 'Clase 22', 'Cuerda, bramante, redes, tiendas de campaña, toldos, velas, sacos (no comprendidos en otras clases); materias de relleno (con excepción del caucho o materias plásticas); materias textiles fibrosas, en bruto.'),
(23, 'Clase 23', 'Hilos para uso textil.'),
(24, 'Clase 24', 'Tejidos y productos textiles no comprendidos en otras clases; ropa de cama y de mesa.'),
(25, 'Clase 25', 'Vestidos, calzados, sombrerería.'),
(26, 'Clase 26', 'Puntillas y bordados, cintas y lazos; botones, corchetes y ojetes, alfileres y agujas; flores artificiales.'),
(27, 'Clase 27', 'Alfombras, felpudos,esteras, linóleum y otros revestimientos de suelos; tapicerías murales que no sean en materias textiles.'),
(28, 'Clase 28', 'Juegos, juguetes; artículos de gimnasia y de deporte no comprendidos en otras clases; decoraciones para árboles de Navidad.'),
(29, 'Clase 29', 'Carne, pescado, aves y caza; extractos de carne; frutas y legumbres en conserva, secas y cocidas; gelatinas, mermeladas, compotas; huevos, leche y productos lácteos; aceites y grasas comestibles.'),
(30, 'Clase 30', 'Café, té, cacao, azúcar, arroz, tapioca, sagú, sucedáneos del café; harinas y preparaciones hechas de cereales, pan, pastelería y confitería, helados comestibles; miel, jarabe de melaza; levaduras, polvos para esponjar; sal, mostaza; vinagre, salsas (condimentos); especias, hielo.'),
(31, 'Clase 31', 'Productos agrícolas, hortícolas, forestales y granos, no comprendidos en otras clases; animales vivos; frutas y legumbres frescas; semillas, plantas y flores naturales; alimentos para los animales, malta.'),
(32, 'Clase 32', 'Cervezas; aguas minerales y gaseosas y otras bebidas no alcohólicas; bebidas y zumos de frutas; siropes y otras preparaciones para hacer bebidas.'),
(33, 'Clase 33', 'Bebidas alcohólicas (excepto cervezas).'),
(34, 'Clase 34', ' Tabaco; artículos para fumadores; cerillas.'),
(35, 'Clase 35', 'Publicidad; gestión de negocios comerciales; administración comercial; trabajos de oficina.'),
(36, 'Clase 36', 'Seguros; negocios financieros; negocios monetarios; negocios inmobiliarios.'),
(37, 'Clase 37', ' Construcción; reparación; servicios de instalación.'),
(38, 'Clase 38', 'Telecomunicaciones.'),
(39, 'Clase 39', 'Transporte; embalaje y almacenaje de mercancías; organización de viajes.'),
(40, 'Clase 40', ' Tratamiento de materiales.'),
(41, 'Clase 41', 'Educación; formación; esparcimiento; actividades deportivas y culturales.'),
(42, 'Clase 42', 'Restauración (alimentación); alojamiento temporal; cuidados médicos, de higiene y de belleza; servicios veterinarios y de agricultura; servicios jurídicos; investigación científica e industrial; programación de ordenadores; servicios que no puedan ser clasificados en otras clases');

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
(1, 'Logistica'),
(2, 'Compras'),
(3, 'Ventas'),
(4, 'Gerencia');

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
(2, 'InActivo'),
(8, 'Activo');

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
(1, 'ACTIVO'),
(2, 'INACTIVO');

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

--
-- Volcado de datos para la tabla `tab_estado_pedido`
--

INSERT INTO `tab_estado_pedido` (`id_estado_pedido`, `codigo_pedido`, `fecha_estado`, `estado`) VALUES
(1, 52, '2019-03-08', 8),
(2, 53, '2019-03-08', 8),
(3, 54, '2019-03-08', 8),
(4, 55, '2019-03-08', 8),
(5, 56, '2019-03-08', 8),
(6, 57, '2019-03-08', 8),
(7, 58, '2019-03-08', 8),
(8, 59, '2019-03-08', 8),
(9, 60, '2019-03-08', 8),
(10, 61, '2019-03-08', 8),
(11, 62, '2019-03-08', 8),
(12, 63, '2019-03-08', 8),
(13, 64, '2019-03-08', 8),
(14, 65, '2019-03-08', 8),
(15, 66, '2019-03-08', 8),
(16, 67, '2019-03-08', 8),
(17, 68, '2019-03-08', 8),
(18, 69, '2019-03-08', 8),
(19, 70, '2019-03-08', 8),
(20, 71, '2019-03-08', 8),
(21, 72, '2019-03-08', 8),
(22, 73, '2019-03-08', 8),
(23, 74, '2019-03-08', 8),
(24, 75, '2019-03-08', 8),
(25, 76, '2019-03-08', 8),
(26, 77, '2019-03-08', 8),
(27, 78, '2019-03-08', 8),
(28, 79, '2019-03-08', 8),
(29, 80, '2019-03-08', 8),
(30, 81, '2019-03-08', 8),
(31, 82, '2019-03-08', 8),
(32, 83, '2019-03-08', 8),
(33, 84, '2019-03-08', 8),
(34, 85, '2019-03-08', 8),
(35, 86, '2019-03-08', 8);

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
  `pass` varchar(100) DEFAULT NULL,
  `avatar` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tab_gerencia`
--

INSERT INTO `tab_gerencia` (`id_gerencia`, `departamento`, `nombre`, `apellido`, `cod_empleado`, `usuario`, `pass`, `avatar`) VALUES
(1, 3, 'Sergio', 'Torres', '0001', 'sergio', '1234', 'sergio.gif'),
(2, 4, 'Jonathan', 'rodriguez', '0002', 'jony', '123', 'facebook.png'),
(3, 4, 'Roberto', 'Hernandez', '0003', 'roby', '1234', 'cahuate.jpg'),
(4, 4, 'Geovanny', 'Fuentes', '0004', 'geo', 'ayuwoki', 'geo.gif'),
(5, 1, 'Pro', 'Fesional', '0005', 'pro', '123', 'admingeo.png'),
(6, 1, 'Jose', 'Hernandez', '0006', 'jose', '123', 'bod.gif'),
(7, 4, 'Youssef', 'Omar', '0007', 'hrz', '123', 'omar.png');

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
(1, 'MINERIA'),
(2, 'PESCA'),
(3, 'BIENES RAÍCES'),
(4, 'CONSTRUCCIÓN'),
(5, 'GANADERÍA'),
(6, 'TRANSPORTE AÉREO'),
(7, 'TURISMO'),
(8, 'SOFTWARE'),
(9, 'TELECOMUNICACIONES'),
(10, 'METALURGIA'),
(11, 'CINEMATOGRAFÍA'),
(12, 'EDITORIAL'),
(13, 'MERCADOS MAYORISTAS'),
(14, 'PRODUCTORES AGRICOLAS'),
(15, 'EMPRESAS DE DISEÑO'),
(16, 'ELECTRICIDAD'),
(17, 'AGUA POTABLE'),
(18, 'COBRANZAS'),
(19, 'VIGILANCIA'),
(20, 'DERECHO');

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
(1, '01', 94),
(2, '03', 32),
(5, '02', 100);

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

--
-- Volcado de datos para la tabla `tab_pedido`
--

INSERT INTO `tab_pedido` (`codigo_pedido`, `proyeccion`, `gerencia`, `fecha_pedido`, `iva`, `total`) VALUES
(52, 1, 2, '2019-03-07', 0.00, 0.00),
(53, 1, 2, '2019-03-07', 0.00, 0.00),
(54, 1, 1, '2019-03-07', 15.86, 1522.56),
(55, 1, 1, '2019-03-07', 15.86, 1522.56),
(56, 1, 1, '2019-03-07', 15.86, 1522.56),
(57, 1, 1, '2019-03-07', 15.86, 1522.56),
(58, 1, 1, '2019-03-07', 15.86, 1522.56),
(59, 1, 1, '2019-03-07', 15.86, 1522.56),
(60, 1, 1, '2019-03-07', 15.86, 1522.56),
(61, 1, 1, '2019-03-07', 15.86, 1522.56),
(62, 1, 1, '2019-03-07', 15.86, 1522.56),
(63, 1, 1, '2019-03-07', 15.86, 1522.56),
(64, 1, 1, '2019-03-07', 15.86, 1522.56),
(65, 1, 1, '2019-03-07', 15.86, 1522.56),
(66, 1, 1, '2019-03-07', 15.86, 1522.56),
(67, 1, 1, '2019-03-07', 15.86, 1522.56),
(68, 1, 1, '2019-03-07', 15.86, 1522.56),
(69, 1, 1, '2019-03-07', 15.86, 1522.56),
(70, 1, 1, '2019-03-07', 15.86, 1522.56),
(71, 1, 1, '2019-03-07', 15.86, 1522.56),
(72, 1, 1, '2019-03-07', 15.86, 1522.56),
(73, 1, 1, '2019-03-07', 15.86, 1522.56),
(74, 1, 1, '2019-03-07', 15.86, 1522.56),
(75, 1, 1, '2019-03-07', 15.86, 1522.56),
(76, 1, 1, '2019-03-07', 15.86, 1522.56),
(77, 1, 1, '2019-03-07', 15.86, 1522.56),
(78, 1, 1, '2019-03-07', 15.86, 1522.56),
(79, 1, 1, '2019-03-07', 15.86, 507.52),
(80, 1, 1, '2019-03-07', 15.86, 507.52),
(81, 1, 1, '2019-03-07', 15.86, 507.52),
(82, 1, 2, '2019-03-07', 15.86, 1522.56),
(83, 1, 2, '2019-03-07', 15.86, 1522.56),
(84, 1, 2, '2019-03-07', 15.86, 1522.56),
(85, 1, 2, '2019-03-07', 15.86, 1522.56),
(86, 1, 2, '2019-03-07', 15.86, 1490.84);

--
-- Disparadores `tab_pedido`
--
DELIMITER $$
CREATE TRIGGER `new_estado` AFTER INSERT ON `tab_pedido` FOR EACH ROW begin
insert into tab_estado_pedido (codigo_pedido,fecha_estado,estado) values (new.codigo_pedido,current_date(),8);
END
$$
DELIMITER ;

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
('01', 'teclado', 12, 28, 122.00),
('02', 'monitor 22°', 1, 28, 12.00),
('03', 'router', 12, 51, 2.85),
('04', 'memoria ram', 1, 28, 122.00),
('05', 'case', 9, 80, 12.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tab_provedor`
--

CREATE TABLE `tab_provedor` (
  `id_provedor` int(11) NOT NULL,
  `nombre_proveedor` varchar(100) DEFAULT NULL,
  `telefono` varchar(100) DEFAULT NULL,
  `giros` int(11) DEFAULT NULL,
  `estado_global` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tab_provedor`
--

INSERT INTO `tab_provedor` (`id_provedor`, `nombre_proveedor`, `telefono`, `giros`, `estado_global`) VALUES
(28, 'Josue', '2123-2323', 2, 1),
(51, 'roberto', '2290-0190', 20, 1),
(80, 'cj', '2290-0190', 4, 2);

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
(1, 1, 6, 22, 12),
(67, 2, 6, 2, 2);

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
(11, '01', 12, 12.00),
(12, '01', 2, 12.00),
(13, '01', 2, 12.00),
(14, '03', 12, 12.00),
(15, '03', 12, 12.00),
(16, '01', 2, 2.00);

--
-- Disparadores `tab_venta`
--
DELIMITER $$
CREATE TRIGGER `tab_venta_AFTER_INSERT` AFTER INSERT ON `tab_venta` FOR EACH ROW BEGIN
update tab_inventario as a set a.stock=a.stock-new.cantidad
where a.producto=new.codigo;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `tab_venta_AFTER_UPDATE` AFTER UPDATE ON `tab_venta` FOR EACH ROW BEGIN

update tab_inventario as a set a.stock=a.stock-new.cantidad
where a.producto=new.codigo;

END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `tab_venta_BEFORE_DELETE` BEFORE DELETE ON `tab_venta` FOR EACH ROW BEGIN
update tab_inventario as a set a.stock=a.stock+old.cantidad
where a.producto=old.codigo;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `tab_venta_BEFORE_UPDATE` BEFORE UPDATE ON `tab_venta` FOR EACH ROW BEGIN

update tab_inventario as a set a.stock=a.stock+old.cantidad
where a.producto=old.codigo;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura para la vista `chartsell`
--
DROP TABLE IF EXISTS `chartsell`;

CREATE ALGORITHM=UNDEFINED DEFINER=`kz`@`%` SQL SECURITY DEFINER VIEW `chartsell`  AS  select `p`.`codigo` AS `codigo`,`p`.`nombre` AS `nombre`,`v`.`cantidad` AS `cantidad`,`v`.`total` AS `total` from (`tab_venta` `v` join `tab_producto` `p` on((`v`.`codigo` = `p`.`codigo`))) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `det_vent`
--
DROP TABLE IF EXISTS `det_vent`;

CREATE ALGORITHM=UNDEFINED DEFINER=`kz`@`%` SQL SECURITY DEFINER VIEW `det_vent`  AS  select `deta`.`id_detalle` AS `id_detalle`,`vent`.`codigo` AS `codigo`,`produc`.`nombre` AS `nombre`,`deta`.`comprador` AS `comprador`,`produc`.`precio` AS `precio`,`inventario`.`stock` AS `stock`,`vent`.`cantidad` AS `cantidad` from (((`tab_detalle_venta` `deta` join `tab_venta` `vent` on((`deta`.`id_venta` = `vent`.`id_venta`))) join `tab_producto` `produc` on((`vent`.`codigo` = `produc`.`codigo`))) join `tab_inventario` `inventario` on((`produc`.`codigo` = `inventario`.`producto`))) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `inventario_producto`
--
DROP TABLE IF EXISTS `inventario_producto`;

CREATE ALGORITHM=UNDEFINED DEFINER=`kz`@`%` SQL SECURITY DEFINER VIEW `inventario_producto`  AS  select `a`.`id_inventario` AS `id_inventario`,`b`.`codigo` AS `codigo`,`b`.`nombre` AS `nombre`,`c`.`nombre_categoria` AS `nombre_categoria`,`d`.`nombre_proveedor` AS `nombre_proveedor`,`b`.`precio` AS `precio`,`a`.`stock` AS `stock` from (((`tab_producto` `b` join `tab_inventario` `a` on((`a`.`producto` = `b`.`codigo`))) join `tab_categoria` `c` on((`b`.`categoria` = `c`.`id_categoria`))) join `tab_provedor` `d` on((`b`.`provedor` = `d`.`id_provedor`))) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `pedido_proyeccion`
--
DROP TABLE IF EXISTS `pedido_proyeccion`;

CREATE ALGORITHM=UNDEFINED DEFINER=`kz`@`%` SQL SECURITY DEFINER VIEW `pedido_proyeccion`  AS  select `inventario`.`id_inventario` AS `id_inventario`,`produc`.`codigo` AS `codigo`,`produc`.`nombre` AS `nombre`,`inventario`.`stock` AS `stock` from (((`tab_detalle_venta` `deta` join `tab_venta` `vent` on((`deta`.`id_venta` = `vent`.`id_venta`))) join `tab_producto` `produc` on((`vent`.`codigo` = `produc`.`codigo`))) join `tab_inventario` `inventario` on((`produc`.`codigo` = `inventario`.`producto`))) ;

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
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT de la tabla `tab_departamento`
--
ALTER TABLE `tab_departamento`
  MODIFY `id_departamento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tab_detalle_venta`
--
ALTER TABLE `tab_detalle_venta`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tab_estado`
--
ALTER TABLE `tab_estado`
  MODIFY `id_estado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `tab_estado_global`
--
ALTER TABLE `tab_estado_global`
  MODIFY `id_estado_global` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tab_estado_pedido`
--
ALTER TABLE `tab_estado_pedido`
  MODIFY `id_estado_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT de la tabla `tab_gerencia`
--
ALTER TABLE `tab_gerencia`
  MODIFY `id_gerencia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `tab_giro`
--
ALTER TABLE `tab_giro`
  MODIFY `id_giro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `tab_inventario`
--
ALTER TABLE `tab_inventario`
  MODIFY `id_inventario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tab_pedido`
--
ALTER TABLE `tab_pedido`
  MODIFY `codigo_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=87;

--
-- AUTO_INCREMENT de la tabla `tab_provedor`
--
ALTER TABLE `tab_provedor`
  MODIFY `id_provedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;

--
-- AUTO_INCREMENT de la tabla `tab_proyeccion`
--
ALTER TABLE `tab_proyeccion`
  MODIFY `id_proyeccion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT de la tabla `tab_venta`
--
ALTER TABLE `tab_venta`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

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
