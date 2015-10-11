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
-- Table structure for table `bestellanforderung`
--

DROP TABLE IF EXISTS `bestellanforderung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bestellanforderung` (
  `id` int(10) unsigned NOT NULL,
  `erstellungsdatum` datetime NOT NULL,
  `status` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `fk_herstellerLieferant` int(10) unsigned DEFAULT NULL,
  `fk_benutzerAbsender` int(10) unsigned DEFAULT NULL,
  `fk_benutzerAnnehmer` int(10) unsigned DEFAULT NULL,
  `email_ba_empfaenger` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bestellanforderung_herstellerLieferant` (`fk_herstellerLieferant`),
  KEY `FK_bestellanforderung_benutzerAbsender` (`fk_benutzerAbsender`),
  KEY `FK_bestellanforderung_benutzerAnnehmer` (`fk_benutzerAnnehmer`),
  CONSTRAINT `FK_bestellanforderung_benutzerAbsender` FOREIGN KEY (`fk_benutzerAbsender`) REFERENCES `benutzer` (`id`),
  CONSTRAINT `FK_bestellanforderung_benutzerAnnehmer` FOREIGN KEY (`fk_benutzerAnnehmer`) REFERENCES `benutzer` (`id`),
  CONSTRAINT `FK_bestellanforderung_herstellerLieferant` FOREIGN KEY (`fk_herstellerLieferant`) REFERENCES `herstellerlieferant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bestellanforderung`
--

LOCK TABLES `bestellanforderung` WRITE;
/*!40000 ALTER TABLE `bestellanforderung` DISABLE KEYS */;
INSERT INTO `bestellanforderung` VALUES (1,'0000-00-00 00:00:00','F',5,17,19,NULL),(2,'2010-01-11 00:00:00','F',6,18,19,NULL),(3,'2010-01-12 00:00:00','A',4,17,19,NULL),(4,'2010-01-13 00:00:00','L',1,18,19,NULL),(5,'2010-01-11 00:00:00','F',1,18,19,NULL),(6,'0000-00-00 00:00:00','F',1,17,19,NULL),(7,'2010-01-11 00:00:00','F',1,18,19,NULL),(8,'2010-01-12 00:00:00','A',2,18,19,NULL),(9,'2010-01-13 00:00:00','L',1,17,19,NULL),(10,'2010-01-11 00:00:00','L',1,18,19,NULL),(11,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),(12,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(13,'2010-01-12 00:00:00','L',1,NULL,NULL,NULL),(14,'2010-01-13 00:00:00','L',1,NULL,NULL,NULL),(15,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(16,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),(17,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(18,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),(19,'2010-01-13 00:00:00','L',1,NULL,NULL,NULL),(20,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(21,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),(22,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(23,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),(24,'2010-01-13 00:00:00','L',2,NULL,NULL,NULL),(25,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(26,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),(27,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(28,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),(29,'2010-01-13 00:00:00','O',3,NULL,NULL,NULL),(30,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(31,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),(32,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(33,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),(34,'2010-01-13 00:00:00','E',1,NULL,NULL,'eugen.schmidt3@freenet.de'),(35,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(36,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),(37,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(38,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),(39,'2010-01-13 00:00:00','L',1,NULL,NULL,NULL),(40,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(41,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),(42,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(43,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),(44,'2010-01-13 00:00:00','O',1,NULL,NULL,NULL),(45,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(46,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),(47,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(48,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),(49,'2010-01-13 00:00:00','O',1,NULL,NULL,NULL),(50,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(51,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),(52,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(53,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),(54,'2010-01-13 00:00:00','L',1,NULL,NULL,NULL),(55,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(56,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),(57,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(58,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),(59,'2010-01-13 00:00:00','L',1,NULL,NULL,NULL),(60,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(61,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),(62,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(63,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),(64,'2010-01-13 00:00:00','O',1,NULL,NULL,NULL),(65,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(66,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),(67,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(68,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),(69,'2010-01-13 00:00:00','O',1,NULL,NULL,NULL),(70,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(71,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),(72,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(73,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),(74,'2010-01-13 00:00:00','O',6,NULL,NULL,NULL),(75,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(76,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),(77,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),(78,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),(79,'2010-01-13 00:00:00','O',6,NULL,NULL,NULL),(1013,'2010-03-01 00:00:00','F',3,NULL,NULL,NULL),(1015,'2010-04-09 00:00:00','F',NULL,NULL,NULL,NULL),(1016,'2010-04-09 00:00:00','F',2,NULL,NULL,NULL),(1019,'2010-04-09 00:00:00','F',NULL,NULL,NULL,NULL),(1020,'2010-04-09 00:00:00','O',1,NULL,NULL,NULL),(1021,'2010-04-09 00:00:00','L',NULL,NULL,NULL,NULL),(1022,'2010-04-09 00:00:00','O',NULL,NULL,NULL,NULL),(1023,'2010-04-09 00:00:00','O',1,NULL,NULL,NULL),(1024,'2010-04-09 00:00:00','O',NULL,NULL,NULL,NULL),(1026,'2010-04-09 00:00:00','O',NULL,NULL,NULL,NULL),(1030,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),(1032,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),(1033,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),(1034,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),(1035,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),(1036,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),(1037,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),(1038,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),(1039,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),(1040,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),(1041,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),(1042,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),(1046,'2010-05-14 00:00:00','E',5,NULL,NULL,'schlosserei@ke-ag.de'),(1048,'2010-05-14 00:00:00','E',5,NULL,NULL,''),(1049,'2010-05-14 00:00:00','E',5,NULL,NULL,'thurner@ke-ag.de'),(1051,'2010-05-14 00:00:00','E',5,NULL,NULL,'elektrowerkstatt@ke-ag.de'),(1052,'2010-05-14 00:00:00','O',5,NULL,NULL,'thurner@ke-ag.de'),(1053,'2010-05-14 00:00:00','O',5,NULL,NULL,NULL),(1054,'2010-05-14 00:00:00','L',5,NULL,NULL,NULL),(1055,'2010-05-14 00:00:00','L',NULL,NULL,NULL,NULL),(1056,'2010-05-14 00:00:00','E',5,NULL,NULL,''),(1057,'2010-05-14 00:00:00','L',2,NULL,NULL,NULL),(1058,'2010-05-14 00:00:00','O',NULL,NULL,NULL,NULL),(1059,'2010-05-21 00:00:00','O',NULL,NULL,NULL,'email@email.de'),(1060,'2010-05-21 00:00:00','L',NULL,NULL,NULL,NULL),(1061,'2010-05-21 00:00:00','L',NULL,NULL,NULL,NULL),(1078,'2010-05-21 00:00:00','L',2,NULL,NULL,NULL),(1079,'2010-05-22 00:00:00','L',5,NULL,NULL,'thurner@ke-ag.de'),(1085,'2010-06-08 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),(1086,'2010-06-08 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),(1088,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1089,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1090,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1091,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1092,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1093,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1094,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1095,'2010-06-11 00:00:00','L',5,NULL,NULL,'thurner@ke-ag.de'),(1096,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1097,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'schlosserei@ke-ag.de'),(1098,'2010-06-11 00:00:00','E',5,NULL,NULL,'thurner@ke-ag.de'),(1099,'2010-06-11 00:00:00','E',5,NULL,NULL,'thurner@ke-ag.de'),(1100,'2010-06-11 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),(1102,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),(1103,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1104,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1105,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1107,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),(1108,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1109,'2010-06-11 00:00:00','L',5,NULL,NULL,'thurner@ke-ag.de'),(1112,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1113,'2010-06-11 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),(1114,'2010-06-11 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),(1115,'2010-06-11 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),(1116,'2010-06-11 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),(1122,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1123,'2010-06-11 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),(1124,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1126,'2010-06-12 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1127,'2010-06-12 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1128,'2010-06-12 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1129,'2010-06-12 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),(1130,'2010-06-12 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),(1131,'2010-06-12 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),(1132,'2010-06-12 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),(1133,'2010-06-12 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),(1140,'2010-06-12 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1141,'2010-06-12 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1142,'2010-06-25 00:00:00','E',NULL,NULL,NULL,'email@email.de23'),(1143,'2010-06-25 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1145,'2010-07-02 00:00:00','E',5,NULL,NULL,'email@emai5l.de'),(1146,'2010-07-03 00:00:00','E',6,NULL,NULL,'thurner@ke-ag.de'),(1147,'2010-07-04 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),(1148,'2010-07-04 00:00:00','E',5,NULL,NULL,'thurner@ke-ag.de'),(1149,'2010-07-04 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),(1155,'2011-03-05 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1156,'2011-04-10 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1157,'2011-04-14 00:00:00','E',NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),(1158,'2011-07-18 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),(1159,'2011-08-04 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1160,'2011-08-04 00:00:00','E',NULL,NULL,NULL,'wladimir.stoll@gmx.de'),(1161,'2011-08-05 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),(1162,'2011-08-06 00:00:00','E',69,14,NULL,'wladimir.stoll@gmx.de'),(1163,'2011-08-06 00:00:00','E',35,14,NULL,'elektrowerkstatt@ke-ag.de'),(1164,'2011-08-06 00:00:00','E',NULL,70,NULL,'schlosserei@ke-ag.de'),(1165,'2011-08-06 00:00:00','E',NULL,70,NULL,'schlosserei@ke-ag.de'),(1166,'2011-08-06 00:00:00','E',NULL,70,NULL,'elektrowerkstatt@ke-ag.de'),(1167,'2011-08-06 00:00:00','E',NULL,70,NULL,'elektrowerkstatt@ke-ag.de'),(1168,'2011-08-06 00:00:00','E',NULL,70,NULL,'elektrowerkstatt@ke-ag.de'),(1169,'2011-08-12 00:00:00','E',12,20,NULL,'thurner@ke-ag.de');
/*!40000 ALTER TABLE `bestellanforderung` ENABLE KEYS */;
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