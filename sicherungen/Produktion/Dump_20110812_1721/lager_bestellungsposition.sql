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
-- Table structure for table `bestellungsposition`
--

DROP TABLE IF EXISTS `bestellungsposition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bestellungsposition` (
  `id` int(10) unsigned NOT NULL,
  `fk_bestellung` int(10) unsigned NOT NULL,
  `fk_artikel` int(10) unsigned NOT NULL,
  `bestellmenge` int(10) unsigned NOT NULL,
  `liefertermin` int(2) unsigned DEFAULT NULL,
  `status` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `fk_kostenstelle` int(10) unsigned DEFAULT NULL,
  `fk_mengeneinheit` int(10) unsigned NOT NULL,
  `fk_katalog` int(10) unsigned DEFAULT NULL,
  `lieferantenbestellnummer` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `katalogseite` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `katalogpreis` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `bestellungsposition_bestellung_artikel` (`fk_bestellung`,`fk_artikel`) USING BTREE,
  KEY `FK_bestellungsposition_artikel` (`fk_artikel`) USING BTREE,
  KEY `FK_bestellungsposition_kostenstelle` (`fk_kostenstelle`) USING BTREE,
  KEY `FK_bestellungsposition_mengeneinheit` (`fk_mengeneinheit`) USING BTREE,
  CONSTRAINT `FK_bestellungsposition_artikel` FOREIGN KEY (`fk_artikel`) REFERENCES `artikel` (`id`),
  CONSTRAINT `FK_bestellungsposition_bestellanforderung` FOREIGN KEY (`fk_bestellung`) REFERENCES `bestellung` (`id`),
  CONSTRAINT `FK_bestellungsposition_kostenstelle` FOREIGN KEY (`fk_kostenstelle`) REFERENCES `kostenstelle` (`id`) ON UPDATE NO ACTION,
  CONSTRAINT `FK_bestellungsposition_mengeneinheit` FOREIGN KEY (`fk_mengeneinheit`) REFERENCES `mengeneinheit` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bestellungsposition`
--

LOCK TABLES `bestellungsposition` WRITE;
/*!40000 ALTER TABLE `bestellungsposition` DISABLE KEYS */;
INSERT INTO `bestellungsposition` VALUES (2,2,16,1,37,'O',NULL,1,0,'','',0),(3,2,25,2,37,'O',NULL,1,0,'','',0);
/*!40000 ALTER TABLE `bestellungsposition` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-08-12 17:21:21
