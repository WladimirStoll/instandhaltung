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
-- Table structure for table `einlagerungposition`
--

DROP TABLE IF EXISTS `einlagerungposition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `einlagerungposition` (
  `id` int(10) unsigned NOT NULL,
  `fk_artikel` int(10) unsigned NOT NULL,
  `menge` int(10) unsigned NOT NULL,
  `fk_mengeeinheit` int(10) unsigned NOT NULL,
  `fk_position` int(10) unsigned NOT NULL,
  `status` varchar(1) NOT NULL,
  `fk_kostenstelle` int(10) unsigned DEFAULT NULL,
  `fk_benutzer_einlagerer` int(10) unsigned DEFAULT NULL,
  `erstellungsdatum` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `fk_benutzer_kontroller` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_einlagerungposition_artikel` (`fk_artikel`),
  KEY `FK_einlagerungposition_mengeneinheit` (`fk_mengeeinheit`),
  KEY `FK_einlagerungposition_position` (`fk_position`),
  KEY `FK_einlagerungposition_kostenstelle` (`fk_kostenstelle`),
  KEY `FK_einlagerungposition_benutzer_einlagerer` (`fk_benutzer_einlagerer`),
  KEY `FK_einlagerungposition_kontroller` (`fk_benutzer_kontroller`),
  CONSTRAINT `FK_einlagerungposition_artikel` FOREIGN KEY (`fk_artikel`) REFERENCES `artikel` (`id`),
  CONSTRAINT `FK_einlagerungposition_benutzer_einlagerer` FOREIGN KEY (`fk_benutzer_einlagerer`) REFERENCES `benutzer` (`id`),
  CONSTRAINT `FK_einlagerungposition_kontroller` FOREIGN KEY (`fk_benutzer_kontroller`) REFERENCES `benutzer` (`id`),
  CONSTRAINT `FK_einlagerungposition_kostenstelle` FOREIGN KEY (`fk_kostenstelle`) REFERENCES `kostenstelle` (`id`),
  CONSTRAINT `FK_einlagerungposition_mengeneinheit` FOREIGN KEY (`fk_mengeeinheit`) REFERENCES `mengeneinheit` (`id`),
  CONSTRAINT `FK_einlagerungposition_position` FOREIGN KEY (`fk_position`) REFERENCES `position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `einlagerungposition`
--

LOCK TABLES `einlagerungposition` WRITE;
/*!40000 ALTER TABLE `einlagerungposition` DISABLE KEYS */;
INSERT INTO `einlagerungposition` VALUES (1,1,10,1,336,'B',NULL,NULL,'2011-06-10 00:00:00',NULL),(2,1,5,1,336,'B',NULL,NULL,'2011-06-10 00:00:00',NULL),(3,1,1,1,336,'O',NULL,NULL,'2011-06-10 00:00:00',NULL);
/*!40000 ALTER TABLE `einlagerungposition` ENABLE KEYS */;
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
