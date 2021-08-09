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
-- Table structure for table `clientReport`
--

DROP TABLE IF EXISTS `clientReport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientReport` (
  `idclientReport` int(11) NOT NULL AUTO_INCREMENT,
  `deuda` decimal(10,1) DEFAULT NULL,
  `mensual` decimal(10,1) DEFAULT NULL,
  `cambio` decimal(10,1) DEFAULT NULL,
  `deudaActual` decimal(10,0) DEFAULT NULL,
  `fechaDeuda` varchar(45) DEFAULT NULL,
  `ultimoPago` decimal(10,0) DEFAULT NULL,
  `fechaPago` varchar(45) DEFAULT NULL,
  `ticket` varchar(45) DEFAULT NULL,
  `fechaLimite` varchar(45) DEFAULT NULL,
  `idClient` int(11) DEFAULT NULL,
  PRIMARY KEY (`idclientReport`),
  KEY `fk_clientReport_1_idx` (`idClient`),
  CONSTRAINT `fk_clientReport_1` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientReport`
--

LOCK TABLES `clientReport` WRITE;
/*!40000 ALTER TABLE `clientReport` DISABLE KEYS */;
INSERT INTO `clientReport` VALUES (1,500.0,41.7,8.3,374,'25/04/2021',50,'25/04/2021','0000000003','05/05/2021',1),(3,500.0,41.7,8.3,290,'04/06/2021',50,'04/06/2021','0000000005','05/05/2021',3);
/*!40000 ALTER TABLE `clientReport` ENABLE KEYS */;
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
