-- MySQL dump 10.13  Distrib 5.7.34, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: Hillary
-- ------------------------------------------------------
-- Server version	5.7.34-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `TPagosClient`
--

DROP TABLE IF EXISTS `TPagosClient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TPagosClient` (
  `idTPagosClient` int(11) NOT NULL AUTO_INCREMENT,
  `deuda` decimal(10,1) DEFAULT NULL,
  `saldo` decimal(10,1) DEFAULT NULL,
  `pago` decimal(10,1) DEFAULT NULL,
  `cambio` decimal(10,1) DEFAULT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `fechaLimite` varchar(45) DEFAULT NULL,
  `ticket` varchar(45) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `idClient` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTPagosClient`),
  KEY `fk_TPagosClient_1_idx` (`idClient`),
  CONSTRAINT `fk_TPagosClient_1` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TPagosClient`
--

LOCK TABLES `TPagosClient` WRITE;
/*!40000 ALTER TABLE `TPagosClient` DISABLE KEYS */;
INSERT INTO `TPagosClient` VALUES (1,500.0,458.3,50.0,8.3,'19/05/2021','19/05/2021','0000000001',1,'Moises',1),(28,500.0,458.3,50.0,8.3,'19/05/2021','19/05/2021','0000000001',1,'Moises',1),(29,500.0,416.3,50.0,8.3,'24/05/2021','24/05/2021','0000000002',1,'Moises',1),(30,500.0,374.3,50.0,8.3,'libreria.Calendario@272d5a6d','25/05/2021','0000000003',1,'Moises',1),(31,500.0,458.3,50.0,8.3,'25/05/2021','25/05/2021','0000000001',1,'Moises',3),(32,500.0,416.3,50.0,8.3,'25/05/2021','25/05/2021','0000000002',1,'Moises',3),(33,500.0,374.3,50.0,8.3,'25/05/2021','25/05/2021','0000000003',1,'Moises',3),(34,500.0,332.3,50.0,8.3,'25/05/2021','25/05/2021','0000000004',1,'Moises',3),(35,500.0,332.3,50.0,8.3,'25/05/2021','25/05/2021','0000000004',1,'Moises',3),(36,500.0,332.3,50.0,8.3,'25/05/2021','25/05/2021','0000000004',1,'Moises',3),(37,500.0,332.3,50.0,8.3,'25/05/2021','25/05/2021','0000000004',1,'Moises',3),(38,500.0,332.3,50.0,8.3,'25/05/2021','25/05/2021','0000000004',1,'Moises',3),(39,500.0,332.3,50.0,8.3,'25/05/2021','25/05/2021','0000000004',1,'Moises',3),(40,500.0,332.3,50.0,8.3,'25/05/2021','25/05/2021','0000000004',1,'Moises',3),(41,500.0,332.3,50.0,8.3,'25/05/2021','25/05/2021','0000000004',1,'Moises',3),(42,500.0,332.3,50.0,8.3,'25/05/2021','25/05/2021','0000000004',1,'Moises',3),(43,500.0,332.3,50.0,8.3,'25/05/2021','25/05/2021','0000000004',1,'Moises',3),(44,500.0,332.3,50.0,8.3,'25/05/2021','25/05/2021','0000000004',1,'Moises',3),(45,500.0,332.3,50.0,8.3,'25/05/2021','25/05/2021','0000000004',1,'Moises',3),(46,500.0,332.3,50.0,8.3,'25/05/2021','25/05/2021','0000000004',1,'Moises',3),(47,500.0,332.3,50.0,8.3,'25/05/2021','25/05/2021','0000000004',1,'Moises',3),(48,500.0,332.3,50.0,8.3,'25/05/2021','25/05/2021','0000000004',1,'Moises',3),(49,500.0,332.3,50.0,8.3,'04/06/2021','04/06/2021','0000000004',1,'Moises',3),(50,500.0,332.3,50.0,8.3,'04/06/2021','04/06/2021','0000000004',1,'Moises',3),(51,500.0,332.3,50.0,8.3,'04/06/2021','04/06/2021','0000000004',1,'Moises',3),(52,500.0,332.3,50.0,8.3,'04/06/2021','04/06/2021','0000000004',1,'Moises',3),(53,500.0,332.3,50.0,8.3,'04/06/2021','04/06/2021','0000000004',1,'Moises',3),(54,500.0,332.3,50.0,8.3,'04/06/2021','04/06/2021','0000000004',1,'Moises',3),(55,500.0,332.3,50.0,8.3,'04/06/2021','04/06/2021','0000000004',1,'Moises',3),(56,500.0,290.3,50.0,8.3,'04/06/2021','04/06/2021','0000000005',1,'Moises',3);
/*!40000 ALTER TABLE `TPagosClient` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-12 17:59:21
