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
-- Table structure for table `TInteresesClientes`
--

DROP TABLE IF EXISTS `TInteresesClientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TInteresesClientes` (
  `idTInteresesClientes` int(11) NOT NULL AUTO_INCREMENT,
  `fechaInicial` varchar(45) DEFAULT NULL,
  `deuda` decimal(10,1) DEFAULT NULL,
  `mensual` decimal(10,1) DEFAULT NULL,
  `intereses` decimal(10,1) DEFAULT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `cancelado` tinyint(7) DEFAULT NULL,
  `idClient` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTInteresesClientes`),
  KEY `fk_TInteresesClientes_1_idx` (`idClient`),
  CONSTRAINT `fk_TInteresesClientes_1` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TInteresesClientes`
--

LOCK TABLES `TInteresesClientes` WRITE;
/*!40000 ALTER TABLE `TInteresesClientes` DISABLE KEYS */;
INSERT INTO `TInteresesClientes` VALUES (1,'03/03/2021',500.0,41.7,3.3,'03/03/2021',1,1),(2,'03/03/2021',500.0,41.7,3.3,'03/04/2021',0,1),(3,'03/03/2021',500.0,41.7,3.3,'03/04/2021',1,3),(4,'03/03/2021',500.0,41.7,3.3,'03/04/2021',1,3);
/*!40000 ALTER TABLE `TInteresesClientes` ENABLE KEYS */;
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
