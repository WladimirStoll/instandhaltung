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
-- Table structure for table `bericht_bestellanforderungsposition`
--

DROP TABLE IF EXISTS `bericht_bestellanforderungsposition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bericht_bestellanforderungsposition` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `fk_bestellanforderung` int(10) unsigned NOT NULL,
  `fk_artikel` int(10) unsigned DEFAULT NULL,
  `fk_artikel_bezeichnung` varchar(70) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_artikel_typ` varchar(70) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_artikel_keg_nr` int(10) unsigned DEFAULT NULL,
  `bestellmenge` int(10) unsigned DEFAULT NULL,
  `liefertermin` int(2) unsigned DEFAULT NULL,
  `status` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_kostenstelle` int(10) unsigned DEFAULT NULL,
  `fk_kostenstelle_name` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `fk_kostenstelle_nummer` varchar(10) CHARACTER SET latin1 DEFAULT NULL,
  `fk_mengeneinheit` int(10) unsigned DEFAULT NULL,
  `fk_katalog` int(10) unsigned DEFAULT NULL,
  `fk_katalog_name` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `lieferantenbestellnummer` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `katalogseite` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `katalogpreis` double DEFAULT NULL,
  PRIMARY KEY (`id`,`fk_bericht_id`) USING BTREE,
  KEY `FK_bericht_bestellanforderungsposition_1` (`fk_bericht_id`,`id`),
  KEY `FK_bericht_bestellanforderungsposition` (`fk_bericht_id`,`fk_bestellanforderung`),
  CONSTRAINT `FK_bericht_bestellanforderungsposition` FOREIGN KEY (`fk_bericht_id`, `fk_bestellanforderung`) REFERENCES `bericht_bestellanforderung` (`fk_bericht_id`, `id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bericht_bestellanforderungsposition`
--

LOCK TABLES `bericht_bestellanforderungsposition` WRITE;
/*!40000 ALTER TABLE `bericht_bestellanforderungsposition` DISABLE KEYS */;
INSERT INTO `bericht_bestellanforderungsposition` VALUES (169,34,34,1,'Schütz','3RT1036-1A / 22,0kW',1,8,33,'O',2,'Schlosserei','152 / 112',3,NULL,NULL,NULL,NULL,0),(170,34,34,1,'Schütz','3RT1036-1A / 22,0kW',1,8,33,'O',2,'Schlosserei','152 / 112',3,NULL,NULL,NULL,NULL,0),(169,114,34,7,'Schütz','3RT1017-1AP01 / 5,5kW / Spule~230V',7,10,3,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(170,114,34,7,'Schütz','3RT1017-1AP01 / 5,5kW / Spule~230V',7,10,3,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(169,194,34,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,10,8,'O',2,'Schlosserei','152 / 112',1,NULL,NULL,NULL,NULL,0),(170,194,34,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,10,8,'O',2,'Schlosserei','152 / 112',1,NULL,NULL,NULL,NULL,0),(169,2043,34,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,4,16,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(170,2043,34,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,4,16,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(169,2071,34,6,'Schütz','3TF2010-OAPO /4kW / Spule~230V',6,10,22,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(170,2071,34,6,'Schütz','3TF2010-OAPO /4kW / Spule~230V',6,10,22,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(47,2074,1046,17,'Drahtseil','8x36+SES zS/14 mm/30m',17,7,19,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(48,2074,1046,17,'Drahtseil','8x36+SES zS/14 mm/30m',17,7,19,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(67,2074,1046,17,'Drahtseil','8x36+SES zS/14 mm/30m',17,7,19,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(68,2074,1046,17,'Drahtseil','8x36+SES zS/14 mm/30m',17,7,19,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(71,2076,1048,7,'Schütz','3RT1017-1AAP01 / 5,5kW / Spule~230V',7,10,19,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(72,2076,1048,7,'Schütz','3RT1017-1AAP01 / 5,5kW / Spule~230V',7,10,19,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(76,2091,1051,7,'Schütz','3RT1017-1AP01 / 5,5kW / Spule~230V',7,10,19,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(78,2093,1052,7,'Schütz','3RT1017-1AP01 / 5,5kW / Spule~230V',7,10,19,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(79,2093,1052,7,'Schütz','3RT1017-1AP01 / 5,5kW / Spule~230V',7,10,19,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(80,2093,1052,7,'Schütz','3RT1017-1AP01 / 5,5kW / Spule~230V',7,10,19,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(20,2117,1098,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,53,'O',4,'Elektroofen','1212',1,2,'Hagemeier 2009','712123','5 - 123',0),(50,2134,1112,105,'Messumformer für Leitfähigkeit','Liquisys M CLM223/253',105,1,53,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(52,2134,1112,105,'Messumformer für Leitfähigkeit','Liquisys M CLM223/253',105,1,53,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(53,2134,1112,105,'Messumformer für Leitfähigkeit','Liquisys M CLM223/253',105,1,53,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(54,2134,1112,105,'Messumformer für Leitfähigkeit','Liquisys M CLM223/253',105,1,53,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(55,2134,1112,105,'Messumformer für Leitfähigkeit','Liquisys M CLM223/253',105,1,53,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(56,2134,1112,105,'Messumformer für Leitfähigkeit','Liquisys M CLM223/253',105,1,53,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(61,2134,1112,105,'Messumformer für Leitfähigkeit','Liquisys M CLM223/253',105,1,53,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(62,2134,1112,105,'Messumformer für Leitfähigkeit','Liquisys M CLM223/253',105,1,53,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(34,2136,1122,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,2,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(35,2136,1122,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,2,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(36,2136,1122,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,2,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(37,2136,1122,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,2,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(34,2137,1122,2,'Schütz','3RT1035-1A / 18,5kW / Spule~230V',2,2,23,'O',NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,0),(35,2137,1122,2,'Schütz','3RT1035-1A / 18,5kW / Spule~230V',2,2,23,'O',NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,0),(36,2137,1122,2,'Schütz','3RT1035-1A / 18,5kW / Spule~230V',2,2,23,'O',NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,0),(37,2137,1122,2,'Schütz','3RT1035-1A / 18,5kW / Spule~230V',2,2,23,'O',NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,0),(34,2138,1122,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,3,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(35,2138,1122,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,3,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(36,2138,1122,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,3,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(37,2138,1122,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,3,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(33,2140,1124,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,23,'O',NULL,NULL,NULL,1,2,'Hagemeier 2009','712123','5 - 123',0),(33,2141,1124,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,4,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(39,2142,1089,5,'Schütz','3RT1045-1A / 37,0kW / Spule~230V',5,1,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(40,2142,1089,5,'Schütz','3RT1045-1A / 37,0kW / Spule~230V',5,1,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(15,2143,1126,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,23,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(30,2144,1127,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,23,'O',1,'Elektrowerkstatt','1711',1,1,'Schaller 2010','123123  / b12','3 / 152',0),(31,2144,1127,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,23,'O',1,'Elektrowerkstatt','1711',1,1,'Schaller 2010','123123  / b12','3 / 152',0),(32,2144,1127,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,23,'O',1,'Elektrowerkstatt','1711',1,1,'Schaller 2010','123123  / b12','3 / 152',0),(27,2145,1128,7,'Schütz','3RT1017-1AAP01 / 5,5kW / Spule~230V',7,10,23,'O',4,'Elektroofen','1212',3,NULL,NULL,NULL,NULL,0),(28,2145,1128,7,'Schütz','3RT1017-1AAP01 / 5,5kW / Spule~230V',7,10,23,'O',4,'Elektroofen','1212',3,NULL,NULL,NULL,NULL,0),(13,2150,1142,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,25,'O',4,'Elektroofen','1212',1,1,'Schaller 2010','123123  / b12','3 / 152',0),(14,2150,1142,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,25,'O',4,'Elektroofen','1212',1,1,'Schaller 2010','123123  / b12','3 / 152',0),(17,2151,1143,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,25,'O',5,'Schreinerei','1211',1,2,'Hagemeier 2009','712123','5 - 123',0),(18,2151,1143,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,25,'O',5,'Schreinerei','1211',1,2,'Hagemeier 2009','712123','5 - 123',0),(19,2151,1143,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,25,'O',5,'Schreinerei','1211',1,2,'Hagemeier 2009','712123','5 - 123',0),(17,2152,1143,5,'Schütz','3RT1045-1A / 37,0kW / Spule~230V',5,5,25,'O',1,'Elektrowerkstatt','1711',3,NULL,NULL,NULL,NULL,0),(18,2152,1143,5,'Schütz','3RT1045-1A / 37,0kW / Spule~230V',5,5,25,'O',1,'Elektrowerkstatt','1711',3,NULL,NULL,NULL,NULL,0),(19,2152,1143,5,'Schütz','3RT1045-1A / 37,0kW / Spule~230V',5,5,25,'O',1,'Elektrowerkstatt','1711',3,NULL,NULL,NULL,NULL,0),(7,2153,1145,7,'Schütz','3RT1017-1AAP01 / 5,5kW / Spule~230V',7,10,26,'O',NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,0),(7,2154,1145,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,26,'O',NULL,NULL,NULL,3,2,'Hagemeier 2009','712123','5 - 123',0),(9,2155,1146,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,26,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(10,2155,1146,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,26,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(11,2155,1146,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,26,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(12,2155,1146,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,26,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(9,2156,1146,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,4,26,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(10,2156,1146,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,4,26,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(11,2156,1146,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,4,26,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(12,2156,1146,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,4,26,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(3,2157,1148,71,'3~Motor','SSKh 90L-8 /0,55kW /675U/min',71,2,33,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(4,2157,1148,71,'3~Motor','SSKh 90L-8 /0,55kW /675U/min',71,2,33,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(5,2157,1148,71,'3~Motor','SSKh 90L-8 /0,55kW /675U/min',71,2,33,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(6,2157,1148,71,'3~Motor','SSKh 90L-8 /0,55kW /675U/min',71,2,33,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(8,2157,1148,71,'3~Motor','SSKh 90L-8 /0,55kW /675U/min',71,2,33,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(9,2158,1146,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,10,28,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(10,2158,1146,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,10,28,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(11,2158,1146,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,10,28,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(12,2158,1146,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,10,28,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),(13,2167,1142,107,'2-Wege  Kugelhahn ,Kunstoff,für Säurelei','546',107,1,48,'O',3,'Formautomat','148 / 15',1,NULL,NULL,NULL,NULL,0),(14,2167,1142,107,'2-Wege  Kugelhahn ,Kunstoff,für Säurelei','546',107,1,48,'O',3,'Formautomat','148 / 15',1,NULL,NULL,NULL,NULL,0),(15,2169,1126,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,5,48,'O',4,'Elektroofen','1212',1,2,'Hagemeier 2009','144433','3/543',0),(16,2170,1141,2,'Schütz','3RT1035-1A / 18,5kW / Spule~230V',2,5,48,'O',2,'Schlosserei','152 / 112',1,2,'Hagemeier 2009','121111','1/543',0),(171,2170,1141,2,'Schütz','3RT1035-1A / 18,5kW / Spule~230V',2,5,48,'O',2,'Schlosserei','152 / 112',1,2,'Hagemeier 2009','121111','1/543',0),(16,2171,1141,52,'Schnellschütz / 5,5kW','3RT1517-1APOO',52,10,48,'O',5,'Schreinerei','1211',1,1,'Schaller 2010','???',NULL,0),(171,2171,1141,52,'Schnellschütz / 5,5kW','3RT1517-1APOO',52,10,48,'O',5,'Schreinerei','1211',1,1,'Schaller 2010','???',NULL,0),(16,2172,1141,53,'Schütz / 5,5kW','3RT1017-1AP01',53,10,48,'O',1,'Elektrowerkstatt','1711',1,1,'Schaller 2010','???',NULL,0),(171,2172,1141,53,'Schütz / 5,5kW','3RT1017-1AP01',53,10,48,'O',1,'Elektrowerkstatt','1711',1,1,'Schaller 2010','???',NULL,0),(20,2173,1098,107,'2-Wege  Kugelhahn ,Kunstoff,für Säurelei','546',107,1,48,'O',2,'Schlosserei','152 / 112',1,NULL,NULL,NULL,NULL,0),(21,2176,1140,118,'DIAZED-Schraubkappe','DII 25A AC DC50 Keramik mit Prüfloch',118,10,49,'O',NULL,NULL,NULL,1,2,'Hagemeier 2009','2606490',NULL,0),(22,2176,1140,118,'DIAZED-Schraubkappe','DII 25A AC DC50 Keramik mit Prüfloch',118,10,49,'O',NULL,NULL,NULL,1,2,'Hagemeier 2009','2606490',NULL,0),(23,2176,1140,118,'DIAZED-Schraubkappe','DII 25A AC DC50 Keramik mit Prüfloch',118,10,49,'O',NULL,NULL,NULL,1,2,'Hagemeier 2009','2606490',NULL,0),(24,2176,1140,118,'DIAZED-Schraubkappe','DII 25A AC DC50 Keramik mit Prüfloch',118,10,49,'O',NULL,NULL,NULL,1,2,'Hagemeier 2009','2606490',NULL,0),(25,2176,1140,118,'DIAZED-Schraubkappe','DII 25A AC DC50 Keramik mit Prüfloch',118,10,49,'O',NULL,NULL,NULL,1,2,'Hagemeier 2009','2606490',NULL,0),(26,2177,1099,71,'3~Motor','SSKh 90L-8 /0,55kW /675U/min',71,1,49,'O',3,'Formautomat','148 / 15',1,NULL,NULL,NULL,NULL,0),(29,2178,1092,72,'3~Motor','AR 90L-8 /0,55kW /705U/min',72,1,49,'O',1,'Elektrowerkstatt','1711',1,NULL,NULL,NULL,NULL,0),(41,2180,1090,2,'Schütz','3RT1035-1A / 18,5kW / Spule~230V',2,5,49,'O',4,'Elektroofen','1212',1,2,'Hagemeier 2009','121111','1/543',0),(42,2180,1090,2,'Schütz','3RT1035-1A / 18,5kW / Spule~230V',2,5,49,'O',4,'Elektroofen','1212',1,2,'Hagemeier 2009','121111','1/543',0),(43,2181,1091,81,'Schütz','3RT1034-1AP00 /15kW/Spule~230V',81,5,49,'O',5,'Schreinerei','1211',1,NULL,NULL,NULL,NULL,0),(44,2181,1091,81,'Schütz','3RT1034-1AP00 /15kW/Spule~230V',81,5,49,'O',5,'Schreinerei','1211',1,NULL,NULL,NULL,NULL,0),(45,2181,1091,81,'Schütz','3RT1034-1AP00 /15kW/Spule~230V',81,5,49,'O',5,'Schreinerei','1211',1,NULL,NULL,NULL,NULL,0),(46,2181,1091,81,'Schütz','3RT1034-1AP00 /15kW/Spule~230V',81,5,49,'O',5,'Schreinerei','1211',1,NULL,NULL,NULL,NULL,0),(43,2182,1091,52,'Schnellschütz / 5,5kW','3RT1517-1APOO',52,10,49,'O',3,'Formautomat','148 / 15',1,1,'Schaller 2010','???',NULL,0),(44,2182,1091,52,'Schnellschütz / 5,5kW','3RT1517-1APOO',52,10,49,'O',3,'Formautomat','148 / 15',1,1,'Schaller 2010','???',NULL,0),(45,2182,1091,52,'Schnellschütz / 5,5kW','3RT1517-1APOO',52,10,49,'O',3,'Formautomat','148 / 15',1,1,'Schaller 2010','???',NULL,0),(46,2182,1091,52,'Schnellschütz / 5,5kW','3RT1517-1APOO',52,10,49,'O',3,'Formautomat','148 / 15',1,1,'Schaller 2010','???',NULL,0),(49,2183,1093,96,'Multifunktions-Zeitrelais,18Funkt.,1W','MFZ12DX-UC',96,5,49,'O',1,'Elektrowerkstatt','1711',1,NULL,NULL,NULL,NULL,0),(57,2183,1093,96,'Multifunktions-Zeitrelais,18Funkt.,1W','MFZ12DX-UC',96,5,49,'O',1,'Elektrowerkstatt','1711',1,NULL,NULL,NULL,NULL,0),(58,2183,1093,96,'Multifunktions-Zeitrelais,18Funkt.,1W','MFZ12DX-UC',96,5,49,'O',1,'Elektrowerkstatt','1711',1,NULL,NULL,NULL,NULL,0),(60,2183,1093,96,'Multifunktions-Zeitrelais,18Funkt.,1W','MFZ12DX-UC',96,5,49,'O',1,'Elektrowerkstatt','1711',1,NULL,NULL,NULL,NULL,0),(51,2184,1096,17,'Drahtseil','8x36+SES zS/14 mm/30m',17,6,49,'O',5,'Schreinerei','1211',1,NULL,NULL,NULL,NULL,0),(63,2184,1096,17,'Drahtseil','8x36+SES zS/14 mm/30m',17,6,49,'O',5,'Schreinerei','1211',1,NULL,NULL,NULL,NULL,0),(52,2185,1112,76,'Dosierventil','3/2-Wege 3/4\"',76,1,49,'O',4,'Elektroofen','1212',1,NULL,NULL,NULL,NULL,0),(53,2185,1112,76,'Dosierventil','3/2-Wege 3/4\"',76,1,49,'O',4,'Elektroofen','1212',1,NULL,NULL,NULL,NULL,0),(54,2185,1112,76,'Dosierventil','3/2-Wege 3/4\"',76,1,49,'O',4,'Elektroofen','1212',1,NULL,NULL,NULL,NULL,0),(55,2185,1112,76,'Dosierventil','3/2-Wege 3/4\"',76,1,49,'O',4,'Elektroofen','1212',1,NULL,NULL,NULL,NULL,0),(56,2185,1112,76,'Dosierventil','3/2-Wege 3/4\"',76,1,49,'O',4,'Elektroofen','1212',1,NULL,NULL,NULL,NULL,0),(61,2185,1112,76,'Dosierventil','3/2-Wege 3/4\"',76,1,49,'O',4,'Elektroofen','1212',1,NULL,NULL,NULL,NULL,0),(62,2185,1112,76,'Dosierventil','3/2-Wege 3/4\"',76,1,49,'O',4,'Elektroofen','1212',1,NULL,NULL,NULL,NULL,0),(59,2186,1094,17,'Drahtseil','8x36+SES zS/14 mm/30m',17,1,49,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(64,2187,1097,80,'5/2-Wege-Magnetventil','JMFH-5-1/4',80,7,49,'O',1,'Elektrowerkstatt','1711',1,NULL,NULL,NULL,NULL,0),(65,2188,1102,80,'5/2-Wege-Magnetventil','JMFH-5-1/4',80,6,49,'O',4,'Elektroofen','1212',1,NULL,NULL,NULL,NULL,0),(66,2188,1102,80,'5/2-Wege-Magnetventil','JMFH-5-1/4',80,6,49,'O',4,'Elektroofen','1212',1,NULL,NULL,NULL,NULL,0),(69,2189,1108,107,'2-Wege  Kugelhahn ,Kunstoff,für Säurelei','546',107,1,49,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(71,2190,1048,18,'Drahtseil','9x19+SES zS/14 mm/28m',18,7,49,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(72,2190,1048,18,'Drahtseil','9x19+SES zS/14 mm/28m',18,7,49,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(73,2191,1105,117,'Durchgangsklemmen (PE)','UT 2,5-TWIN PE',117,50,49,'O',2,'Schlosserei','152 / 112',1,2,'Hagemeier 2009','2842906',NULL,0),(74,2192,1049,117,'Durchgangsklemmen (PE)','UT 2,5-TWIN PE',117,50,49,'O',1,'Elektrowerkstatt','1711',1,2,'Hagemeier 2009','2842906',NULL,0),(76,2197,1051,107,'2-Wege  Kugelhahn ','546',107,1,9,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(76,2203,1051,189,'Aderendhülsen isoliert,0,75qmm','471/???',189,300,9,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(76,2204,1051,129,'Dosenklemme,3x0,75-1,5qmm','273-100',129,1,9,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(77,2205,1155,80,'5/2-Wege-Magnetventil','JMFH-5-1/4',80,3,9,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(109,2206,1103,72,'3~Motor','AR 90L-8 /0,55kW /705U/min',72,1,10,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(110,2206,1103,72,'3~Motor','AR 90L-8 /0,55kW /705U/min',72,1,10,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(109,2208,1103,130,'Dosenklemme,8x0,75-1,5qmm','273-108',130,100,14,'O',4,'Elektroofen','1212',1,2,'Hagemeier 2009','2014005',NULL,0),(110,2208,1103,130,'Dosenklemme,8x0,75-1,5qmm','273-108',130,100,14,'O',4,'Elektroofen','1212',1,2,'Hagemeier 2009','2014005',NULL,0),(107,2209,1156,129,'Dosenklemme,3x0,75-1,5qmm','273-100',129,1,14,'O',1,'Elektrowerkstatt','1711',1,2,'Hagemeier 2009','2599967',NULL,0),(108,2209,1156,129,'Dosenklemme,3x0,75-1,5qmm','273-100',129,1,14,'O',1,'Elektrowerkstatt','1711',1,2,'Hagemeier 2009','2599967',NULL,0),(115,2210,1157,322,'Motorschützschalter 0,63-1,0 A','PKZM0-1',322,1,15,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(116,2210,1157,322,'Motorschützschalter 0,63-1,0 A','PKZM0-1',322,1,15,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(117,2210,1157,322,'Motorschützschalter 0,63-1,0 A','PKZM0-1',322,1,15,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(119,2210,1157,322,'Motorschützschalter 0,63-1,0 A','PKZM0-1',322,1,15,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(120,2210,1157,322,'Motorschützschalter 0,63-1,0 A','PKZM0-1',322,1,15,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(115,2211,1157,321,'Motorschützschalter 0,16-0,25 A','PKZM0-0,25',321,1,15,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(116,2211,1157,321,'Motorschützschalter 0,16-0,25 A','PKZM0-0,25',321,1,15,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(117,2211,1157,321,'Motorschützschalter 0,16-0,25 A','PKZM0-0,25',321,1,15,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(119,2211,1157,321,'Motorschützschalter 0,16-0,25 A','PKZM0-0,25',321,1,15,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(120,2211,1157,321,'Motorschützschalter 0,16-0,25 A','PKZM0-0,25',321,1,15,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(115,2212,1157,304,'Motorschützschalter 24-40 A','GV7 RS40',304,1,15,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(116,2212,1157,304,'Motorschützschalter 24-40 A','GV7 RS40',304,1,15,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(117,2212,1157,304,'Motorschützschalter 24-40 A','GV7 RS40',304,1,15,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(119,2212,1157,304,'Motorschützschalter 24-40 A','GV7 RS40',304,1,15,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(120,2212,1157,304,'Motorschützschalter 24-40 A','GV7 RS40',304,1,15,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(174,2213,1158,107,'2-Wege  Kugelhahn ','546',107,2,29,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(191,2214,1159,288,'Aluminium-Steckmuffe 40','AMS-E 40',288,10,31,'O',NULL,NULL,NULL,1,2,'Hagemeier 2009','2687956','3/56',0),(192,2214,1159,288,'Aluminium-Steckmuffe 40','AMS-E 40',288,10,31,'O',NULL,NULL,NULL,1,2,'Hagemeier 2009','2687956','3/56',0),(194,2214,1159,288,'Aluminium-Steckmuffe 40','AMS-E 40',288,10,31,'O',NULL,NULL,NULL,1,2,'Hagemeier 2009','2687956','3/56',0),(193,2215,1160,288,'Aluminium-Steckmuffe 40','AMS-E 40',288,10,31,'O',NULL,NULL,NULL,1,2,'Hagemeier 2009','2687956','3/56',0),(195,2215,1160,288,'Aluminium-Steckmuffe 40','AMS-E 40',288,10,31,'O',NULL,NULL,NULL,1,2,'Hagemeier 2009','2687956','3/56',0),(196,2216,1161,107,'2-Wege  Kugelhahn ','546',107,2,31,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(198,2217,1162,107,'2-Wege  Kugelhahn ','546',107,2,31,'O',7,'Allg. Werkdienst','11',1,NULL,NULL,NULL,NULL,0),(199,2218,1163,382,'3~Motor; 0,18kW; 1360/83U/min; m.Bremse; m.Getriebe','SK01 F63L74 Bre4',382,1,31,'O',1,'Elektrowerkstatt','61',1,NULL,NULL,NULL,NULL,0),(201,2219,1164,382,'3~Motor; 0,18kW; 1360/83U/min; m.Bremse; m.Getriebe','SK01 F63L74 Bre4',382,1,31,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),(202,2220,1165,10,'Drahtseil','8x36+SES zS/20 mm/42m',10,3,31,'O',4,'Elektroofen','302',1,NULL,NULL,NULL,NULL,0),(203,2221,1166,293,'Endtülle , M32','129 M32',293,50,31,'O',9,'Druckluft','23',1,7,'OBO-Bettermann','2047853',NULL,0),(204,2222,1167,83,'Einfachhaken (Kran)','GR.5,0',83,1,31,'O',15,'EDV','90',1,NULL,NULL,NULL,NULL,0),(205,2223,1168,221,'Funkempfänger ','DRC-MP D2',221,1,31,'O',13,'Krananlagen','52',1,NULL,NULL,NULL,NULL,0),(206,2223,1168,221,'Funkempfänger ','DRC-MP D2',221,1,31,'O',13,'Krananlagen','52',1,NULL,NULL,NULL,NULL,0),(205,2224,1168,96,'Multifunktions-Zeitrelais,18Funkt.,1W','MFZ12DX-UC',96,5,31,'O',33,'HF','324',1,NULL,NULL,NULL,NULL,0),(206,2224,1168,96,'Multifunktions-Zeitrelais,18Funkt.,1W','MFZ12DX-UC',96,5,31,'O',33,'HF','324',1,NULL,NULL,NULL,NULL,0),(205,2225,1168,371,'3~Motor; 0,12kW; 660U/min','3AP71/8',371,1,31,'O',29,'Kernschießmaschinen','315',1,NULL,NULL,NULL,NULL,0),(206,2225,1168,371,'3~Motor; 0,12kW; 660U/min','3AP71/8',371,1,31,'O',29,'Kernschießmaschinen','315',1,NULL,NULL,NULL,NULL,0),(207,2226,1169,426,'Überlastabschaltgerät mit Lastkollektivspeicher','FWL 230VAC',426,2,32,'O',13,'Krananlagen','52',1,30,'Schäfer/Siegen','469 667 44',NULL,0);
/*!40000 ALTER TABLE `bericht_bestellanforderungsposition` ENABLE KEYS */;
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