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
-- Table structure for table `bericht_inventur_zeile`
--

DROP TABLE IF EXISTS `bericht_inventur_zeile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bericht_inventur_zeile` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `nummer` int(10) unsigned NOT NULL,
  `fk_halle` int(10) unsigned NOT NULL,
  `fk_etage` int(10) unsigned DEFAULT NULL,
  `fk_abteilung` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bericht_inventur_zeile`
--

LOCK TABLES `bericht_inventur_zeile` WRITE;
/*!40000 ALTER TABLE `bericht_inventur_zeile` DISABLE KEYS */;
INSERT INTO `bericht_inventur_zeile` VALUES (83,1,7,1,1,3),(83,2,6,1,1,3),(83,3,1,1,5,3),(83,7,5,1,14,1),(83,30,2,1,5,3),(84,1,7,1,1,3),(84,2,6,1,1,3),(84,3,1,1,5,3),(84,7,5,1,14,1),(84,30,2,1,5,3),(85,3,1,1,5,3),(86,1,7,1,1,3),(86,2,6,1,1,3),(86,3,1,1,5,3),(86,7,5,1,14,1),(86,30,2,1,5,3),(87,1,7,1,1,3),(87,2,6,1,1,3),(87,3,1,1,5,3),(87,7,5,1,14,1),(87,30,2,1,5,3),(88,1,7,1,1,3),(88,2,6,1,1,3),(88,3,1,1,5,3),(88,7,5,1,14,1),(88,30,2,1,5,3),(89,1,7,1,1,3),(89,2,6,1,1,3),(89,3,1,1,5,3),(89,7,5,1,14,1),(89,30,2,1,5,3),(90,3,1,1,5,3),(91,1,7,1,1,3),(91,2,6,1,1,3),(91,3,1,1,5,3),(91,7,5,1,14,1),(91,30,2,1,5,3),(92,3,1,1,5,3),(93,3,1,1,5,3),(94,1,7,1,1,3),(94,2,6,1,1,3),(94,3,1,1,5,3),(94,7,5,1,14,1),(94,30,2,1,5,3),(95,1,7,1,1,3),(95,2,6,1,1,3),(95,3,1,1,5,3),(95,7,5,1,14,1),(95,30,2,1,5,3),(96,2,6,1,1,3),(98,20,2,7,4,4),(98,21,4,7,4,4),(98,22,3,7,4,4),(98,23,5,7,4,3),(98,24,1,7,4,4),(98,26,6,7,15,4),(103,1,7,1,1,3),(103,2,6,1,1,3),(103,3,1,1,5,3),(103,7,5,1,14,1),(103,30,2,1,5,3),(104,7,5,1,14,1),(105,1,7,1,1,3),(105,2,6,1,1,3),(105,3,1,1,5,3),(105,7,5,1,14,1),(105,30,2,1,5,3),(106,1,7,1,1,3),(106,2,6,1,1,3),(106,3,1,1,5,3),(106,7,5,1,14,1),(106,30,2,1,5,3),(111,3,1,1,5,3),(112,3,1,1,5,3),(113,3,1,1,5,3),(114,3,1,1,5,3),(118,1,7,1,1,3),(118,2,6,1,1,3),(118,3,1,1,5,3),(118,7,5,1,14,1),(118,30,2,1,5,3),(129,3,1,1,5,3),(130,3,1,1,5,3),(131,3,1,1,5,3),(132,1,7,1,1,3),(132,2,6,1,1,3),(132,3,1,1,5,3),(132,7,5,1,14,1),(132,30,2,1,5,3),(132,38,8,1,1,3),(133,1,7,1,1,3),(133,2,6,1,1,3),(133,3,1,1,5,3),(133,7,5,1,14,1),(133,30,2,1,5,3),(133,38,8,1,1,3),(135,3,1,1,5,3),(136,3,1,1,5,3),(137,3,1,1,5,3),(143,3,1,1,5,3),(155,3,1,1,5,3),(156,3,1,1,5,3),(157,3,1,1,5,3),(158,3,1,1,5,3),(159,3,1,1,5,3),(160,3,1,1,5,3),(161,3,1,1,5,3),(162,3,1,1,5,3),(164,3,1,1,5,3),(165,3,1,1,5,3),(166,3,1,1,5,3),(167,3,1,1,5,3),(168,3,1,1,5,3),(172,3,1,1,5,3),(176,5,1,15,12,1),(177,3,1,1,5,3),(183,3,1,1,5,3),(188,3,1,1,5,3),(189,3,1,1,5,3);
/*!40000 ALTER TABLE `bericht_inventur_zeile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-08-12 17:21:23
