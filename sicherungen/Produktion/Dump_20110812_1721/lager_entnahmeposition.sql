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
-- Table structure for table `entnahmeposition`
--

DROP TABLE IF EXISTS `entnahmeposition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entnahmeposition` (
  `id` int(10) unsigned NOT NULL,
  `fk_artikel` int(10) unsigned NOT NULL,
  `menge` int(10) unsigned NOT NULL,
  `fk_mengeeinheit` int(10) unsigned NOT NULL,
  `fk_position` int(10) unsigned NOT NULL,
  `status` varchar(1) NOT NULL,
  `fk_kostenstelle` int(10) unsigned DEFAULT NULL,
  `fk_benutzer_entnehmer` int(10) unsigned DEFAULT NULL,
  `erstellungsdatum` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `fk_benutzer_kontroller` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_entnahmeposition_artikel` (`fk_artikel`),
  KEY `FK_entnahmeposition_mengeeinheit` (`fk_mengeeinheit`),
  KEY `FK_entnahmeposition_position` (`fk_position`),
  KEY `FK_entnahmeposition_kostenstelle` (`fk_kostenstelle`),
  KEY `FK_benutzer_entnehmer` (`fk_benutzer_entnehmer`),
  KEY `FK_benutzer_kontroller` (`fk_benutzer_kontroller`),
  CONSTRAINT `FK_benutzer_entnehmer` FOREIGN KEY (`fk_benutzer_entnehmer`) REFERENCES `benutzer` (`id`),
  CONSTRAINT `FK_benutzer_kontroller` FOREIGN KEY (`fk_benutzer_kontroller`) REFERENCES `benutzer` (`id`),
  CONSTRAINT `FK_entnahmeposition_artikel` FOREIGN KEY (`fk_artikel`) REFERENCES `artikel` (`id`),
  CONSTRAINT `FK_entnahmeposition_kostenstelle` FOREIGN KEY (`fk_kostenstelle`) REFERENCES `kostenstelle` (`id`),
  CONSTRAINT `FK_entnahmeposition_mengeeinheit` FOREIGN KEY (`fk_mengeeinheit`) REFERENCES `mengeneinheit` (`id`),
  CONSTRAINT `FK_entnahmeposition_position` FOREIGN KEY (`fk_position`) REFERENCES `position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entnahmeposition`
--

LOCK TABLES `entnahmeposition` WRITE;
/*!40000 ALTER TABLE `entnahmeposition` DISABLE KEYS */;
INSERT INTO `entnahmeposition` VALUES (43,86,1,1,1,'o',NULL,NULL,'0000-00-00 00:00:00',NULL),(44,88,1,1,2,'o',NULL,NULL,'0000-00-00 00:00:00',NULL),(100,136,4,1,336,'B',NULL,NULL,'2011-04-09 00:00:00',NULL),(101,136,5,1,336,'B',4,NULL,'2011-04-09 00:00:00',NULL),(102,139,5,1,338,'O',NULL,NULL,'2011-04-09 00:00:00',NULL),(103,152,1,1,356,'B',NULL,NULL,'2011-04-09 00:00:00',NULL),(104,161,9,1,365,'B',NULL,NULL,'2011-04-10 00:00:00',NULL),(105,151,77,1,355,'O',4,NULL,'2011-04-10 00:00:00',NULL),(118,136,2,1,336,'L',4,NULL,'2011-04-15 00:00:00',NULL),(121,136,1,1,336,'B',NULL,NULL,'2011-04-15 00:00:00',NULL),(124,1,1,1,336,'B',NULL,NULL,'2011-06-10 00:00:00',NULL),(125,1,1,1,336,'B',NULL,NULL,'2011-06-10 00:00:00',NULL),(126,1,1,1,336,'B',NULL,NULL,'2011-06-10 00:00:00',NULL),(128,1,1,1,336,'B',NULL,NULL,'2011-06-10 00:00:00',NULL),(129,1,1,1,336,'B',NULL,NULL,'2011-06-10 00:00:00',NULL),(130,1,1,1,336,'B',NULL,NULL,'2011-06-10 00:00:00',NULL),(131,1,1,1,336,'B',NULL,NULL,'2011-06-10 00:00:00',NULL);
/*!40000 ALTER TABLE `entnahmeposition` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-08-12 17:21:26
