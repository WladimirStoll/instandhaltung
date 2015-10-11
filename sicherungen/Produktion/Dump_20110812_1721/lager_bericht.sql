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
-- Table structure for table `bericht`
--

DROP TABLE IF EXISTS `bericht`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bericht` (
  `id` int(10) unsigned NOT NULL,
  `erfassungsbenutzer` int(10) unsigned DEFAULT NULL,
  `erfassungsdatum` datetime DEFAULT NULL,
  `aenderungsbenutzer` int(10) unsigned DEFAULT NULL,
  `aenderungsdatum` datetime DEFAULT NULL,
  `TYP` varchar(20) NOT NULL,
  `benutzer` int(10) unsigned NOT NULL,
  `art` varchar(10) NOT NULL,
  `druckdatumoriginal` datetime DEFAULT NULL,
  `druckdatumkopie` datetime DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `typID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bericht`
--

LOCK TABLES `bericht` WRITE;
/*!40000 ALTER TABLE `bericht` DISABLE KEYS */;
INSERT INTO `bericht` VALUES (1,NULL,NULL,NULL,NULL,'BENUTZER',1,'PRINT',NULL,NULL,NULL,0),(2,NULL,NULL,NULL,NULL,'BESTELLANFORDERUNG',2,'EMAIL',NULL,NULL,NULL,0),(3,14,'2010-12-03 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-03 00:00:00','2010-12-03 00:00:00','',1148),(4,14,'2010-12-03 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-03 00:00:00','2010-12-03 00:00:00','',1148),(5,14,'2010-12-03 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-03 00:00:00','2010-12-03 00:00:00','',1148),(6,14,'2010-12-03 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-03 00:00:00','2010-12-03 00:00:00','',1148),(7,14,'2010-12-03 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-03 00:00:00','2010-12-03 00:00:00','',1145),(8,14,'2010-12-03 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-03 00:00:00','2010-12-03 00:00:00','',1148),(9,14,'2010-12-04 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-04 00:00:00','2010-12-04 00:00:00','',1146),(10,14,'2010-12-04 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-04 00:00:00','2010-12-04 00:00:00','',1146),(11,14,'2010-12-04 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-04 00:00:00','2010-12-04 00:00:00','',1146),(12,14,'2010-12-04 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-04 00:00:00','2010-12-04 00:00:00','',1146),(13,14,'2010-12-04 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-04 00:00:00','2010-12-04 00:00:00','',1142),(14,14,'2010-12-04 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-04 00:00:00','2010-12-04 00:00:00','',1142),(15,14,'2010-12-04 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-04 00:00:00','2010-12-04 00:00:00','',1126),(16,14,'2010-12-04 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-04 00:00:00','2010-12-04 00:00:00','',1141),(17,14,'2010-12-05 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-05 00:00:00','2010-12-05 00:00:00','',1143),(18,14,'2010-12-05 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-05 00:00:00','2010-12-05 00:00:00','',1143),(19,14,'2010-12-05 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-05 00:00:00','2010-12-05 00:00:00','',1143),(20,14,'2010-12-05 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-05 00:00:00','2010-12-05 00:00:00','',1098),(21,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1140),(22,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1140),(23,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1140),(24,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1140),(25,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1140),(26,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1099),(27,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1128),(28,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1128),(29,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1092),(30,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1127),(31,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1127),(32,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1127),(33,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1124),(34,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1122),(35,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1122),(36,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1122),(37,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1122),(38,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1088),(39,20,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1089),(40,20,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1089),(41,20,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1090),(42,20,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1090),(43,20,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1091),(44,20,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1091),(45,20,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1091),(46,20,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1091),(47,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1046),(48,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1046),(49,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1093),(50,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1112),(51,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1096),(52,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1112),(53,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1112),(54,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1112),(55,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1112),(56,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1112),(57,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1093),(58,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1093),(59,14,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1094),(60,14,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1093),(61,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1112),(62,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1112),(63,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1096),(64,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1097),(65,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1102),(66,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1102),(67,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1046),(68,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1046),(69,14,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1108),(70,14,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1107),(71,14,'2010-12-09 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-09 00:00:00','2010-12-09 00:00:00','',1048),(72,14,'2010-12-09 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-09 00:00:00','2010-12-09 00:00:00','',1048),(73,14,'2010-12-09 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-09 00:00:00','2010-12-09 00:00:00','',1105),(74,14,'2010-12-09 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-09 00:00:00','2010-12-09 00:00:00','',1049),(75,14,'2011-01-29 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-01-29 00:00:00','2011-01-29 00:00:00','',1104),(76,14,'2011-03-05 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-03-05 00:00:00','2011-03-05 00:00:00','',1051),(77,14,'2011-03-05 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-03-05 00:00:00','2011-03-05 00:00:00','',1155),(78,14,'2011-03-09 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-03-09 00:00:00','2011-03-09 00:00:00','',1052),(79,14,'2011-03-09 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-03-09 00:00:00','2011-03-09 00:00:00','',1052),(80,14,'2011-03-11 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-03-11 00:00:00','2011-03-11 00:00:00','',1052),(81,14,'2011-03-12 00:00:00',NULL,NULL,'INVENTUR_LISTE',14,'PRINT','2011-03-12 00:00:00','2011-03-12 00:00:00','',1),(82,14,'2011-03-12 00:00:00',NULL,NULL,'INVENTUR_LISTE',14,'PRINT','2011-03-12 00:00:00','2011-03-12 00:00:00','',1),(83,14,'2011-03-12 00:00:00',NULL,NULL,'INVENTUR_LISTE',14,'PRINT','2011-03-12 00:00:00','2011-03-12 00:00:00','',1),(84,14,'2011-04-01 00:00:00',NULL,NULL,'INVENTUR_LISTE',14,'PRINT','2011-04-01 00:00:00','2011-04-01 00:00:00','',1),(85,14,'2011-04-01 00:00:00',NULL,NULL,'INVENTUR_LISTE_ZEILE',14,'PRINT','2011-04-01 00:00:00','2011-04-01 00:00:00','',3),(86,14,'2011-04-01 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-01 00:00:00','2011-04-01 00:00:00','',1),(87,14,'2011-04-01 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-01 00:00:00','2011-04-01 00:00:00','',1),(88,14,'2011-04-01 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-01 00:00:00','2011-04-01 00:00:00','',1),(89,14,'2011-04-01 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-01 00:00:00','2011-04-01 00:00:00','',1),(90,14,'2011-04-01 00:00:00',NULL,NULL,'INVENTUR_LISTE_ZEILE',14,'PRINT','2011-04-01 00:00:00','2011-04-01 00:00:00','',3),(91,14,'2011-04-01 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-01 00:00:00','2011-04-01 00:00:00','',1),(92,14,'2011-04-01 00:00:00',NULL,NULL,'INVENTUR_LISTE_ZEILE',14,'PRINT','2011-04-01 00:00:00','2011-04-01 00:00:00','',3),(93,14,'2011-04-01 00:00:00',NULL,NULL,'INVENTUR_LISTE_ZEILE',14,'PRINT','2011-04-01 00:00:00','2011-04-01 00:00:00','',3),(94,14,'2011-04-01 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-01 00:00:00','2011-04-01 00:00:00','',1),(95,14,'2011-04-02 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-02 00:00:00','2011-04-02 00:00:00','',1),(96,14,'2011-04-02 00:00:00',NULL,NULL,'INVENTUR_LISTE_ZEILE',14,'PRINT','2011-04-02 00:00:00','2011-04-02 00:00:00','',2),(97,14,'2011-04-02 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-02 00:00:00','2011-04-02 00:00:00','',17),(98,14,'2011-04-02 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-02 00:00:00','2011-04-02 00:00:00','',7),(99,14,'2011-04-02 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-02 00:00:00','2011-04-02 00:00:00','',11),(100,14,'2011-04-02 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-02 00:00:00','2011-04-02 00:00:00','',2),(101,14,'2011-04-02 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-02 00:00:00','2011-04-02 00:00:00','',23),(102,14,'2011-04-02 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-02 00:00:00','2011-04-02 00:00:00','',13),(103,14,'2011-04-02 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-02 00:00:00','2011-04-02 00:00:00','',1),(104,14,'2011-04-02 00:00:00',NULL,NULL,'INVENTUR_LISTE_ZEILE',14,'PRINT','2011-04-02 00:00:00','2011-04-02 00:00:00','',7),(105,14,'2011-04-02 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-02 00:00:00','2011-04-02 00:00:00','',1),(106,14,'2011-04-07 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-07 00:00:00','2011-04-07 00:00:00','',1),(107,14,'2011-04-10 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-04-10 00:00:00','2011-04-10 00:00:00','',1156),(108,14,'2011-04-10 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-04-10 00:00:00','2011-04-10 00:00:00','',1156),(109,14,'2011-04-10 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-04-10 00:00:00','2011-04-10 00:00:00','',1103),(110,14,'2011-04-10 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-04-10 00:00:00','2011-04-10 00:00:00','',1103),(111,14,'2011-04-14 00:00:00',NULL,NULL,'INVENTUR_LISTE_ZEILE',14,'PRINT','2011-04-14 00:00:00','2011-04-14 00:00:00','',3),(112,14,'2011-04-14 00:00:00',NULL,NULL,'INVENTUR_LISTE_ZEILE',14,'PRINT','2011-04-14 00:00:00','2011-04-14 00:00:00','',3),(113,14,'2011-04-14 00:00:00',NULL,NULL,'INVENTUR_LISTE_ZEILE',14,'PRINT','2011-04-14 00:00:00','2011-04-14 00:00:00','',3),(114,14,'2011-04-14 00:00:00',NULL,NULL,'INVENTUR_LISTE_ZEILE',14,'PRINT','2011-04-14 00:00:00','2011-04-14 00:00:00','',3),(115,14,'2011-04-14 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-04-14 00:00:00','2011-04-14 00:00:00','',1157),(116,14,'2011-04-14 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-04-14 00:00:00','2011-04-14 00:00:00','',1157),(117,14,'2011-04-14 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-04-14 00:00:00','2011-04-14 00:00:00','',1157),(118,14,'2011-04-16 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-16 00:00:00','2011-04-16 00:00:00','',1),(119,14,'2011-04-16 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-04-16 00:00:00','2011-04-16 00:00:00','',1157),(120,14,'2011-04-16 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-04-16 00:00:00','2011-04-16 00:00:00','',1157),(121,14,'2011-04-16 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-04-16 00:00:00','2011-04-16 00:00:00','',1056),(129,14,'2011-04-22 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-04-22 00:00:00','2011-04-22 00:00:00','',48),(130,14,'2011-04-22 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-04-22 00:00:00','2011-04-22 00:00:00','',48),(131,14,'2011-04-22 00:00:00',NULL,NULL,'INVENTUR_LISTE_ZEILE',14,'PRINT','2011-04-22 00:00:00','2011-04-22 00:00:00','',3),(132,14,'2011-04-22 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-22 00:00:00','2011-04-22 00:00:00','',1),(133,14,'2011-04-29 00:00:00',NULL,NULL,'INVENTUR_LISTE_HALLE',14,'PRINT','2011-04-29 00:00:00','2011-04-29 00:00:00','',1),(135,14,'2011-04-29 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-04-29 00:00:00','2011-04-29 00:00:00','',48),(136,14,'2011-04-29 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-04-29 00:00:00','2011-04-29 00:00:00','',48),(137,14,'2011-04-29 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-04-29 00:00:00','2011-04-29 00:00:00','',48),(141,14,'2011-04-29 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-04-29 00:00:00','2011-04-29 00:00:00','',0),(142,14,'2011-04-29 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-04-29 00:00:00','2011-04-29 00:00:00','',0),(143,14,'2011-04-29 00:00:00',NULL,NULL,'INVENTUR_LISTE_ZEILE',14,'PRINT','2011-04-29 00:00:00','2011-04-29 00:00:00','',3),(144,14,'2011-04-29 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-04-29 00:00:00','2011-04-29 00:00:00','',0),(145,14,'2011-04-29 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-04-29 00:00:00','2011-04-29 00:00:00','',0),(146,14,'2011-04-29 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-04-29 00:00:00','2011-04-29 00:00:00','',0),(147,14,'2011-04-29 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-04-29 00:00:00','2011-04-29 00:00:00','',0),(148,14,'2011-04-29 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-04-29 00:00:00','2011-04-29 00:00:00','',0),(149,14,'2011-05-07 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-05-07 00:00:00','2011-05-07 00:00:00','',0),(150,14,'2011-05-07 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-05-07 00:00:00','2011-05-07 00:00:00','',0),(151,14,'2011-06-10 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-06-10 00:00:00','2011-06-10 00:00:00','',0),(152,14,'2011-06-10 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-06-10 00:00:00','2011-06-10 00:00:00','',0),(153,14,'2011-06-17 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-06-17 00:00:00','2011-06-17 00:00:00','',0),(154,14,'2011-06-17 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-06-17 00:00:00','2011-06-17 00:00:00','',0),(155,14,'2011-06-17 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-06-17 00:00:00','2011-06-17 00:00:00','',48),(156,14,'2011-06-24 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-06-24 00:00:00','2011-06-24 00:00:00','',49),(157,14,'2011-06-24 00:00:00',NULL,NULL,'INVENTUR_LISTE_ZEILE',14,'PRINT','2011-06-24 00:00:00','2011-06-24 00:00:00','',3),(158,14,'2011-06-24 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-06-24 00:00:00','2011-06-24 00:00:00','',49),(159,14,'2011-06-24 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-06-24 00:00:00','2011-06-24 00:00:00','',49),(160,14,'2011-06-24 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-06-24 00:00:00','2011-06-24 00:00:00','',49),(161,14,'2011-06-24 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-06-24 00:00:00','2011-06-24 00:00:00','',49),(162,14,'2011-06-24 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-06-24 00:00:00','2011-06-24 00:00:00','',49),(163,14,'2011-06-26 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-06-26 00:00:00','2011-06-26 00:00:00','',48),(164,14,'2011-06-26 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-06-26 00:00:00','2011-06-26 00:00:00','',48),(165,14,'2011-06-26 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-06-26 00:00:00','2011-06-26 00:00:00','',48),(166,14,'2011-06-26 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-06-26 00:00:00','2011-06-26 00:00:00','',48),(167,14,'2011-06-26 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-06-26 00:00:00','2011-06-26 00:00:00','',48),(168,14,'2011-06-26 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-06-26 00:00:00','2011-06-26 00:00:00','',48),(169,14,'2011-07-17 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-07-17 00:00:00','2011-07-17 00:00:00','',34),(170,14,'2011-07-17 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-07-17 00:00:00','2011-07-17 00:00:00','',34),(171,14,'2011-07-17 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-07-17 00:00:00','2011-07-17 00:00:00','',1141),(172,14,'2011-07-18 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-07-18 00:00:00','2011-07-18 00:00:00','',48),(173,14,'2011-07-18 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-07-18 00:00:00','2011-07-18 00:00:00','',0),(174,14,'2011-07-18 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-07-18 00:00:00','2011-07-18 00:00:00','',1158),(175,14,'2011-07-18 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-07-18 00:00:00','2011-07-18 00:00:00','',0),(176,14,'2011-07-18 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-07-18 00:00:00','2011-07-18 00:00:00','',11),(177,14,'2011-07-18 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-07-18 00:00:00','2011-07-18 00:00:00','',48),(178,14,'2011-07-30 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-07-30 00:00:00','2011-07-30 00:00:00','',0),(179,14,'2011-07-30 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-07-30 00:00:00','2011-07-30 00:00:00','',0),(180,14,'2011-07-30 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-07-30 00:00:00','2011-07-30 00:00:00','',0),(181,14,'2011-07-30 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-07-30 00:00:00','2011-07-30 00:00:00','',0),(182,14,'2011-07-30 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-07-30 00:00:00','2011-07-30 00:00:00','',0),(183,14,'2011-07-30 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-07-30 00:00:00','2011-07-30 00:00:00','',48),(184,14,'2011-07-30 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-07-30 00:00:00','2011-07-30 00:00:00','',0),(185,17,'2011-07-30 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',17,'PRINT','2011-07-30 00:00:00','2011-07-30 00:00:00','',0),(186,14,'2011-07-30 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-07-30 00:00:00','2011-07-30 00:00:00','',0),(187,17,'2011-07-30 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',17,'PRINT','2011-07-30 00:00:00','2011-07-30 00:00:00','',0),(188,17,'2011-07-30 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',17,'PRINT','2011-07-30 00:00:00','2011-07-30 00:00:00','',49),(189,14,'2011-08-02 00:00:00',NULL,NULL,'INVENTUR_LISTE_SAULE',14,'PRINT','2011-08-02 00:00:00','2011-08-02 00:00:00','',49),(190,17,'2011-08-04 00:00:00',NULL,NULL,'ANFORDERUNG',17,'EMAIL','2011-08-04 00:00:00','2011-08-04 00:00:00','',1159),(191,17,'2011-08-04 00:00:00',NULL,NULL,'ANFORDERUNG',17,'EMAIL','2011-08-04 00:00:00','2011-08-04 00:00:00','',1159),(192,17,'2011-08-04 00:00:00',NULL,NULL,'ANFORDERUNG',17,'EMAIL','2011-08-04 00:00:00','2011-08-04 00:00:00','',1159),(193,17,'2011-08-04 00:00:00',NULL,NULL,'ANFORDERUNG',17,'EMAIL','2011-08-04 00:00:00','2011-08-04 00:00:00','',1160),(194,14,'2011-08-04 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-08-04 00:00:00','2011-08-04 00:00:00','',1159),(195,14,'2011-08-04 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-08-04 00:00:00','2011-08-04 00:00:00','',1160),(196,14,'2011-08-05 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-08-05 00:00:00','2011-08-05 00:00:00','',1161),(197,14,'2011-08-06 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-08-06 00:00:00','2011-08-06 00:00:00','',1162),(198,14,'2011-08-06 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-08-06 00:00:00','2011-08-06 00:00:00','',1162),(199,14,'2011-08-06 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-08-06 00:00:00','2011-08-06 00:00:00','',1163),(200,14,'2011-08-06 00:00:00',NULL,NULL,'MINDEST_BESTAND_LST',14,'PRINT','2011-08-06 00:00:00','2011-08-06 00:00:00','',0),(201,70,'2011-08-06 00:00:00',NULL,NULL,'ANFORDERUNG',70,'EMAIL','2011-08-06 00:00:00','2011-08-06 00:00:00','',1164),(202,70,'2011-08-06 00:00:00',NULL,NULL,'ANFORDERUNG',70,'EMAIL','2011-08-06 00:00:00','2011-08-06 00:00:00','',1165),(203,70,'2011-08-06 00:00:00',NULL,NULL,'ANFORDERUNG',70,'EMAIL','2011-08-06 00:00:00','2011-08-06 00:00:00','',1166),(204,70,'2011-08-06 00:00:00',NULL,NULL,'ANFORDERUNG',70,'EMAIL','2011-08-06 00:00:00','2011-08-06 00:00:00','',1167),(205,70,'2011-08-06 00:00:00',NULL,NULL,'ANFORDERUNG',70,'EMAIL','2011-08-06 00:00:00','2011-08-06 00:00:00','',1168),(206,70,'2011-08-06 00:00:00',NULL,NULL,'ANFORDERUNG',70,'EMAIL','2011-08-06 00:00:00','2011-08-06 00:00:00','',1168),(207,20,'2011-08-12 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2011-08-12 00:00:00','2011-08-12 00:00:00','',1169);
/*!40000 ALTER TABLE `bericht` ENABLE KEYS */;
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
