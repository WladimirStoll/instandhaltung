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
-- Table structure for table `zeile`
--

DROP TABLE IF EXISTS `zeile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zeile` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nummer` int(10) unsigned NOT NULL,
  `fk_halle` int(10) unsigned NOT NULL,
  `fk_etage` int(10) unsigned DEFAULT NULL,
  `fk_abteilung` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `eindeutig_zeilen_nummer` (`nummer`,`fk_halle`),
  KEY `FK_zeile_halle` (`fk_halle`),
  KEY `FK_zeile_etage` (`fk_etage`),
  KEY `FK_zeile_abteilung` (`fk_abteilung`),
  CONSTRAINT `FK_zeile_abteilung` FOREIGN KEY (`fk_abteilung`) REFERENCES `abteilung` (`id`),
  CONSTRAINT `FK_zeile_etage` FOREIGN KEY (`fk_etage`) REFERENCES `etage` (`id`),
  CONSTRAINT `FK_zeile_halle` FOREIGN KEY (`fk_halle`) REFERENCES `halle` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zeile`
--

LOCK TABLES `zeile` WRITE;
/*!40000 ALTER TABLE `zeile` DISABLE KEYS */;
INSERT INTO `zeile` VALUES (1,7,1,1,3),(2,6,1,1,3),(3,1,1,5,3),(5,1,15,12,1),(6,2,15,11,1),(7,5,1,14,1),(20,2,7,4,4),(21,4,7,4,4),(22,3,7,4,4),(23,5,7,4,3),(24,1,7,4,4),(26,6,7,15,4),(30,2,1,5,3),(38,8,1,1,3);
/*!40000 ALTER TABLE `zeile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-08-12 17:21:25
