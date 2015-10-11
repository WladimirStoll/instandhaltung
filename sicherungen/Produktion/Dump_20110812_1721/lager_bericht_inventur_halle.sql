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
-- Table structure for table `bericht_inventur_halle`
--

DROP TABLE IF EXISTS `bericht_inventur_halle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bericht_inventur_halle` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `name` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `nummer` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bericht_inventur_halle`
--

LOCK TABLES `bericht_inventur_halle` WRITE;
/*!40000 ALTER TABLE `bericht_inventur_halle` DISABLE KEYS */;
INSERT INTO `bericht_inventur_halle` VALUES (83,1,'03 Schlosserei',NULL),(84,1,'03 Schlosserei',NULL),(85,1,'03 Schlosserei',NULL),(86,1,'03 Schlosserei',NULL),(87,1,'03 Schlosserei',NULL),(88,1,'03 Schlosserei',NULL),(89,1,'03 Schlosserei',NULL),(90,1,'03 Schlosserei',NULL),(91,1,'03 Schlosserei',NULL),(92,1,'03 Schlosserei',NULL),(93,1,'03 Schlosserei',NULL),(94,1,'03 Schlosserei',NULL),(95,1,'03 Schlosserei',NULL),(96,1,'03 Schlosserei',NULL),(97,17,'Bergerhalle',NULL),(98,7,'E.OfenHalle',NULL),(99,11,'Gattierungshalle',NULL),(100,2,'Gießhalle 1',NULL),(101,23,'Kupolofenhalle',NULL),(102,13,'Schreinerei',NULL),(103,1,'03 Schlosserei',NULL),(104,1,'03 Schlosserei',NULL),(105,1,'03 Schlosserei',NULL),(106,1,'03 Schlosserei',NULL),(111,1,'03 Schlosserei',NULL),(112,1,'03 Schlosserei',NULL),(113,1,'03 Schlosserei',NULL),(114,1,'03 Schlosserei',NULL),(118,1,'03 Schlosserei',NULL),(129,1,'03 Schlosserei',NULL),(130,1,'03 Schlosserei',NULL),(131,1,'03 Schlosserei',NULL),(132,1,'03 Schlosserei',NULL),(133,1,'03 Schlosserei',NULL),(135,1,'03 Schlosserei',NULL),(136,1,'03 Schlosserei',NULL),(137,1,'03 Schlosserei',NULL),(143,1,'03 Schlosserei',NULL),(155,1,'Schlosserei',NULL),(156,1,'Schlosserei',NULL),(157,1,'Schlosserei',NULL),(158,1,'Schlosserei',NULL),(159,1,'Schlosserei',NULL),(160,1,'Schlosserei',NULL),(161,1,'Schlosserei',NULL),(162,1,'Schlosserei',NULL),(164,1,'Schlosserei',1),(165,1,'Schlosserei',1),(166,1,'Schlosserei',1),(167,1,'Schlosserei',1),(168,1,'Schlosserei',1),(172,1,'Schlosserei',1),(176,15,'Modellager',15),(177,1,'Schlosserei',1),(183,1,'Schlosserei',1),(188,1,'Schlosserei',1),(189,1,'Schlosserei',1);
/*!40000 ALTER TABLE `bericht_inventur_halle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-08-12 17:21:22
