CREATE DATABASE  IF NOT EXISTS `lager` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `lager`;
-- MySQL dump 10.13  Distrib 5.5.9, for Win32 (x86)
--
-- Host: localhost    Database: lager
-- ------------------------------------------------------
-- Server version	5.5.15

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
-- Table structure for table `saeule`
--

DROP TABLE IF EXISTS `saeule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `saeule` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nummer` int(10) unsigned NOT NULL,
  `fk_zeile` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `saeule_nummer_zeile_eindeutig` (`nummer`,`fk_zeile`),
  KEY `FK_saeule_zeile` (`fk_zeile`),
  CONSTRAINT `FK_saeule_zeile` FOREIGN KEY (`fk_zeile`) REFERENCES `zeile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saeule`
--

LOCK TABLES `saeule` WRITE;
/*!40000 ALTER TABLE `saeule` DISABLE KEYS */;
INSERT INTO `saeule` VALUES (1,1,1),(4,1,2),(48,1,3),(11,1,5),(27,1,6),(37,1,20),(45,1,21),(40,1,22),(29,1,23),(35,1,24),(43,1,26),(51,1,30),(2,2,1),(5,2,2),(49,2,3),(12,2,5),(38,2,20),(30,2,23),(36,2,24),(52,2,30),(3,3,1),(50,3,3),(13,3,5),(39,3,20),(31,3,23),(53,3,30),(14,4,5),(32,4,23),(54,4,30),(15,5,5),(33,5,23),(55,5,30),(16,6,5),(34,6,23),(56,6,30),(17,7,5),(57,7,30),(18,8,5),(58,8,30),(19,9,5),(59,9,30),(20,10,5),(60,10,30),(21,11,5),(61,11,30),(22,12,5),(62,12,30),(23,13,5),(63,13,30),(24,14,5),(64,14,30),(25,15,5),(65,15,30),(26,16,5),(66,16,30),(67,17,30);
/*!40000 ALTER TABLE `saeule` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-08-12 17:21:28
