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
-- Table structure for table `benutzerzugrifsrechtecht`
--

DROP TABLE IF EXISTS `benutzerzugrifsrechtecht`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `benutzerzugrifsrechtecht` (
  `fk_benutzer` int(10) unsigned NOT NULL,
  `fk_zugrifsrecht` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_benutzer`,`fk_zugrifsrecht`),
  KEY `FK_benutzerZugrifsrechtecht_zugrifsrecht` (`fk_zugrifsrecht`),
  CONSTRAINT `FK_benutzerZugrifsrechtecht_benutzer` FOREIGN KEY (`fk_benutzer`) REFERENCES `benutzer` (`id`),
  CONSTRAINT `FK_benutzerZugrifsrechtecht_zugrifsrecht` FOREIGN KEY (`fk_zugrifsrecht`) REFERENCES `zugriftsrechtt` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `benutzerzugrifsrechtecht`
--

LOCK TABLES `benutzerzugrifsrechtecht` WRITE;
/*!40000 ALTER TABLE `benutzerzugrifsrechtecht` DISABLE KEYS */;
INSERT INTO `benutzerzugrifsrechtecht` VALUES (14,1),(20,1),(57,1),(14,2),(17,2),(19,2),(20,2),(57,2),(71,2),(74,2),(75,2),(80,2),(81,2),(82,2),(98,2),(14,3),(18,3),(20,3),(57,3),(67,3),(70,3),(76,3),(77,3),(78,3),(79,3);
/*!40000 ALTER TABLE `benutzerzugrifsrechtecht` ENABLE KEYS */;
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
