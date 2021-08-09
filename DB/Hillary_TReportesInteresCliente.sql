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
-- Table structure for table `TReportesInteresCliente`
--

DROP TABLE IF EXISTS `TReportesInteresCliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TReportesInteresCliente` (
  `idTReportesInteresCliente` int(11) NOT NULL AUTO_INCREMENT,
  `intereses` decimal(10,1) DEFAULT NULL,
  `pago` decimal(10,1) DEFAULT NULL,
  `cambio` decimal(10,1) DEFAULT NULL,
  `cuotas` int(11) DEFAULT NULL,
  `interesFecha` varchar(45) DEFAULT NULL,
  `ticketInteres` varchar(45) DEFAULT NULL,
  `idClient` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTReportesInteresCliente`),
  KEY `fk_TReportesInteresCliente_1_idx` (`idTReportesInteresCliente`),
  KEY `fk_TReportesInteresCliente_1_idx1` (`idClient`),
  CONSTRAINT `fk_TReportesInteresCliente_1` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TReportesInteresCliente`
--

LOCK TABLES `TReportesInteresCliente` WRITE;
/*!40000 ALTER TABLE `TReportesInteresCliente` DISABLE KEYS */;
INSERT INTO `TReportesInteresCliente` VALUES (1,3.3,4.0,0.7,1,'13/05/2021','0000000001',1),(2,0.0,4.0,0.7,1,'13/05/2021','0000000001',3);
/*!40000 ALTER TABLE `TReportesInteresCliente` ENABLE KEYS */;
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
