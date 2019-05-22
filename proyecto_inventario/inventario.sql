CREATE DATABASE  IF NOT EXISTS `inventario` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `inventario`;
-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: inventario
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_cliente` varchar(25) NOT NULL,
  `apellido_cliente` varchar(25) NOT NULL,
  `email_cliente` varchar(50) NOT NULL,
  `dui_cliente` varchar(10) NOT NULL,
  `nit_cliente` varchar(17) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `celular` varchar(10) NOT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Vanessa L.','Gaither','VanessaLGaither@rhyta.com ','46109691-5','5516-010185-692-3','2253-7985','6523-5646'),(2,'James J.','Foster','JamesJFoster@armyspy.com ','85351699-0','5285-766387-220-1','2565-7657','7897-5465'),(3,'Carl S.','Frey','CarlSFrey@dayrep.com ','95924646-3','4916-180372-413-9','2456-8677','6523-1442'),(4,'Monique P.','Whitehead','MoniquePWhitehead@dayrep.com ','42428556-3','5359-988578-035-1','2235-5654','7554-5654'),(5,'Sarah H. ','Halligan','SarahHHalligan@teleworm.us ','59227379-1','5142-136082-556-2','2543-4565','6445-4464'),(6,'Linda D.','Johnson','LindaDJohnson@rhyta.com ','41261287-7','4716-010588-168-7','2365-6564','6955-6445'),(7,'Mary M.','Waggoner','MaryMWaggoner@jourrapide.com ','32432452-6','3425-010186-563-4','2253-4531','7465-4654'),(8,'Shirley ','Sanford','ShirleyGSanford@armyspy.com ','43254654-7','4355-040490-453-4','2343-5456','7656-5434'),(9,'Maria ','Hernandez','Maria@gmail.com','32432452-6','3425-010186-563-4','2556-8964','7856-9632'),(10,'Joao','Coutinho','joao@yahoo.com','43546575-6','3454-010156-056-7','2343-5466','7667-8678');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `factura` (
  `id_factura` int(11) NOT NULL AUTO_INCREMENT,
  `id_vendedor` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_inventario` int(11) NOT NULL,
  `fecha_facturacion` date NOT NULL,
  `tipo_pago` int(11) NOT NULL,
  `total_productos` int(11) NOT NULL,
  `total_ventas` double NOT NULL,
  PRIMARY KEY (`id_factura`),
  KEY `fk_factura_vendedor1_idx` (`id_vendedor`),
  KEY `fk_factura_inventario1_idx` (`id_inventario`),
  KEY `fk_factura_tipo_pago1_idx` (`tipo_pago`),
  KEY `fk_factura_cliente1_idx` (`id_cliente`),
  CONSTRAINT `fk_factura_cliente1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_factura_inventario1` FOREIGN KEY (`id_inventario`) REFERENCES `inventario` (`id_inventario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_factura_tipo_pago1` FOREIGN KEY (`tipo_pago`) REFERENCES `tipo_pago` (`id_tipo_pago`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_factura_vendedor1` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedor` (`id_vendedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
INSERT INTO `factura` VALUES (1,3,2,3,'2018-12-17',3,12,12),(2,4,1,3,'2019-01-10',2,12,21);
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `facturacion`
--

DROP TABLE IF EXISTS `facturacion`;
/*!50001 DROP VIEW IF EXISTS `facturacion`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `facturacion` AS SELECT 
 1 AS `id_producto`,
 1 AS `codigo_producto`,
 1 AS `nombre_producto`,
 1 AS `precio`,
 1 AS `contacto`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `inventario`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `inventario` (
  `id_inventario` int(11) NOT NULL AUTO_INCREMENT,
  `stock_minimo` int(11) NOT NULL,
  `existencias` int(11) NOT NULL,
  `estado` varchar(25) NOT NULL,
  `id_proveedor_producto` int(11) NOT NULL,
  PRIMARY KEY (`id_inventario`),
  KEY `fk_inventario_proveedor_producto1_idx` (`id_proveedor_producto`),
  CONSTRAINT `fk_inventario_proveedor_producto1` FOREIGN KEY (`id_proveedor_producto`) REFERENCES `proveedor_producto` (`id_proveedor_producto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
INSERT INTO `inventario` VALUES (3,150,20,'Disponible',3);
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `login` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(20) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `tipo_usuario` int(11) NOT NULL,
  `respuesta` varchar(45) DEFAULT NULL,
  `pregunta` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'victor.rosales','victor123',1,'1993','¿Cual es su año de nacimiento?'),(2,'josue.figueroa','jos123',2,'perro','¿Cual fue su primera mascota?'),(3,'jonathan.rodriguez','jhon123',1,'1999','¿Cual es su año de nacimiento?'),(4,'juan.perez','juan123',2,'gato','¿Cual fue su primera mascota?'),(5,'luis.miguel','pto',1,'1999','¿Cual es su año de nacimiento?'),(6,'josue.rodriguez','123',2,'1999','¿Cual es su año de nacimiento?'),(7,'mario.granados','123',0,'1999','¿Cual es su año de nacimiento?'),(8,'roberto.hernandez','123',2,'1999','¿Cual es su año de nacimiento?');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `productos` (
  `id_producto` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_producto` varchar(17) NOT NULL,
  `nombre_producto` varchar(50) NOT NULL,
  `descripcion_producto` varchar(100) NOT NULL,
  `precio` double NOT NULL,
  `tipo_producto` varchar(45) NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'cod-000090','GTX 1050TI','Tarjeta Grafica Nvidia',230,'Tarjeta Grafica','2025-01-01'),(2,'cod-000100','GTX 1070','Tarjeta Grafica Nvidia',450,'Tarjeta Grafica','2025-01-01'),(3,'cod-000200','GTX 2080','Tarjeta Grafica Nvidia',1000,'Tarjeta Grafica','2025-01-01'),(4,'cod-000250','GTX 2080TI','Tarjeta Grafica Nvidia',1800,'Tarjeta Grafica','2025-01-01'),(5,'cod-000300','DDr4 8Gb ','Ram Hyper Fury',160,'Memoria Ram','2025-01-01');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `proveedor` (
  `id_proveedor` int(11) NOT NULL AUTO_INCREMENT,
  `telefono` varchar(15) NOT NULL,
  `tipo_persona` varchar(50) NOT NULL,
  `contacto` varchar(30) NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `ubicacion_proveedor` varchar(70) NOT NULL,
  PRIMARY KEY (`id_proveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (2,'2134-6365','Juridica','Juan Perez','2018-12-06','San Salvador');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor_producto`
--

DROP TABLE IF EXISTS `proveedor_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `proveedor_producto` (
  `id_proveedor_producto` int(11) NOT NULL AUTO_INCREMENT,
  `id_proveedor` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  PRIMARY KEY (`id_proveedor_producto`),
  KEY `fk_proveedor_producto_proveedor1_idx` (`id_proveedor`),
  KEY `fk_proveedor_producto_productos1_idx` (`id_producto`),
  CONSTRAINT `fk_proveedor_producto_productos1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_proveedor_producto_proveedor1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor_producto`
--

LOCK TABLES `proveedor_producto` WRITE;
/*!40000 ALTER TABLE `proveedor_producto` DISABLE KEYS */;
INSERT INTO `proveedor_producto` VALUES (3,2,1);
/*!40000 ALTER TABLE `proveedor_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sucursal` (
  `id_sucursal` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_sucursal` varchar(30) NOT NULL,
  `ubicacion_sucursal` varchar(100) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `email_sucursal` varchar(50) NOT NULL,
  `cantidad_vendedores` int(11) NOT NULL,
  PRIMARY KEY (`id_sucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursal`
--

LOCK TABLES `sucursal` WRITE;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
INSERT INTO `sucursal` VALUES (2,'Metrocentro','San Salvador','2539-6694','techmetro@gmail.com',15),(3,'Plazamundo','Soyapango','2448-5445','techplaza@gmail.com',10);
/*!40000 ALTER TABLE `sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_pago`
--

DROP TABLE IF EXISTS `tipo_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tipo_pago` (
  `id_tipo_pago` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_pago` varchar(30) NOT NULL,
  PRIMARY KEY (`id_tipo_pago`),
  KEY `fk_tipo_pago_factura_idx` (`tipo_pago`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_pago`
--

LOCK TABLES `tipo_pago` WRITE;
/*!40000 ALTER TABLE `tipo_pago` DISABLE KEYS */;
INSERT INTO `tipo_pago` VALUES (2,'efectivo'),(3,'tarjeta debito');
/*!40000 ALTER TABLE `tipo_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedor`
--

DROP TABLE IF EXISTS `vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vendedor` (
  `id_vendedor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_vendedor` varchar(25) NOT NULL,
  `apellido_vendedor` varchar(25) NOT NULL,
  `email_vendedor` varchar(50) NOT NULL,
  `dui_vendedor` varchar(10) NOT NULL,
  `nit_vendedor` varchar(17) NOT NULL,
  `residencia` varchar(100) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `celular` varchar(10) NOT NULL,
  `referencia` varchar(50) NOT NULL,
  `fecha_contratacion` varchar(45) NOT NULL,
  `id_login` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  PRIMARY KEY (`id_vendedor`),
  KEY `fk_vendedor_sucursal_idx` (`id_sucursal`),
  KEY `fk_vendedor_login1_idx` (`id_login`),
  CONSTRAINT `fk_vendedor_login1` FOREIGN KEY (`id_login`) REFERENCES `login` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_vendedor_sucursal` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor`
--

LOCK TABLES `vendedor` WRITE;
/*!40000 ALTER TABLE `vendedor` DISABLE KEYS */;
INSERT INTO `vendedor` VALUES (1,'Victor','Rosales','victor@gmail.com','12316142-9','123-4564566-455-8','San Salvador','2345-5465','7894-7895','Luis Peres','2000-01-01',1,3),(3,'Jonathan','Rodriguez','jhon@hotmail.com','12316124-9','465-4654501-212-9','Soyapango','2433-3543','7895-1234','Juana Hernandez','2000-01-01',3,3),(4,'Luis','Miguel','luisjoto@gmail.com','89787817-9','465-4654525-550-1','San Salvador','2356-1234','7879-5656','Alejandra Quiñonez','2000-01-01',5,2),(5,'Jonathan','Rodriguez','jhon@hotmail.com','12316124-9','4654-654501-212-9','Soyapango','2433-3543','7895-1234','Juana Hernandez','2000-01-01',3,2);
/*!40000 ALTER TABLE `vendedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'inventario'
--

--
-- Dumping routines for database 'inventario'
--

--
-- Final view structure for view `facturacion`
--

/*!50001 DROP VIEW IF EXISTS `facturacion`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `facturacion` AS select `pro`.`id_producto` AS `id_producto`,`pro`.`codigo_producto` AS `codigo_producto`,`pro`.`nombre_producto` AS `nombre_producto`,`pro`.`precio` AS `precio`,`pre`.`contacto` AS `contacto` from ((`proveedor_producto` `prove` join `productos` `pro` on((`prove`.`id_producto` = `pro`.`id_producto`))) join `proveedor` `pre` on((`prove`.`id_proveedor` = `pre`.`id_proveedor`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-14 14:06:47
