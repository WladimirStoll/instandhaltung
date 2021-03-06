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
-- Table structure for table `lieferantenartikelbestellnummer`
--

DROP TABLE IF EXISTS `lieferantenartikelbestellnummer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lieferantenartikelbestellnummer` (
  `fk_artikel` int(10) unsigned NOT NULL,
  `fk_katalog` int(10) unsigned NOT NULL,
  `bestellnummer` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `katalogseite` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `preis` double DEFAULT NULL,
  PRIMARY KEY (`fk_artikel`,`fk_katalog`) USING BTREE,
  KEY `FK_externerlieferantenartikelnummer_katalog` (`fk_katalog`),
  CONSTRAINT `FK_externerLieferantenArtikelNummer_artikel` FOREIGN KEY (`fk_artikel`) REFERENCES `artikel` (`id`),
  CONSTRAINT `FK_externerlieferantenartikelnummer_katalog` FOREIGN KEY (`fk_katalog`) REFERENCES `katalog` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lieferantenartikelbestellnummer`
--

LOCK TABLES `lieferantenartikelbestellnummer` WRITE;
/*!40000 ALTER TABLE `lieferantenartikelbestellnummer` DISABLE KEYS */;
INSERT INTO `lieferantenartikelbestellnummer` VALUES (1,1,'123123  / b12','3 / 152',293),(1,2,'712123','5 - 123',200.4),(1,10,'2','',0),(2,1,'12312311','1/432',22),(2,2,'121111','1/543',23),(3,1,'122333','2/211',1),(3,2,'144433','3/543',1),(26,1,'469 664 44','',0),(27,1,'46952544','',NULL),(33,1,'6ES7 315-2AG10-0AB0','',NULL),(34,1,'6GK7 343-1CX10-OXEO','',NULL),(35,1,'6ES7 321-1BL00-OAAO','',NULL),(36,1,'6ES7 322-1BL01-OAAO','',NULL),(37,1,'6ES7 322-1BH01-OAAO','',NULL),(38,1,'6ES7 153-1AA03-OXBO','',NULL),(39,1,'6ES7 321-1BH02-OAAO','',NULL),(40,1,'6ES7 331-7KF02-OABO','',NULL),(41,1,'6ES7 313-6CEOO-OABO','',NULL),(42,1,'6ES7 323-1BLOO-OAAO','',NULL),(43,1,'6ES7 331-1KFOO-OABO','',NULL),(44,1,'6ES7 312-1AD10-OABO','',NULL),(45,1,'6ES7 334-OCE01-OAAO','',NULL),(46,1,'MP 270 ????????????','',NULL),(47,1,'OP 3 ??????????????','',NULL),(48,1,'6AV6 641-OCA01-OAXO','',NULL),(49,1,'6ES7 332-5HBO1-OABO','',NULL),(50,1,'6ES7 307-1EA00-OAAO','',NULL),(51,1,'???','',NULL),(52,1,'???','',NULL),(53,1,'???','',NULL),(54,1,'2029100','5/1',NULL),(55,1,'1004062','',NULL),(56,1,'2068045','8/9',NULL),(57,1,'???','',NULL),(58,1,'ß???','',NULL),(59,1,'???','',NULL),(60,1,'2632869','7/2',NULL),(61,1,'2606354','8/69',NULL),(62,1,'2606357','8/69',NULL),(63,1,'2466190','8/69',NULL),(64,1,'2103258','7/4',NULL),(67,11,'326 02 062 002','',NULL),(80,4,'10410','',0),(82,1,'6ES5 942-7UA13','',NULL),(86,1,'823 800 44','',NULL),(89,1,'586 970 44','',NULL),(92,11,'314 13 095 001','',NULL),(113,6,'5040025','',NULL),(114,2,'2538137','',NULL),(115,2,'2077127','',NULL),(116,2,'2088969','',NULL),(117,2,'2842906','',NULL),(118,2,'2606490','',NULL),(119,2,'2474102','',NULL),(125,2,'2558188',' ',NULL),(128,2,'2793963','',NULL),(129,2,'2599967','',NULL),(130,2,'2014005','',NULL),(131,2,'2014004','',NULL),(132,2,'3001685','',NULL),(133,2,'3041475','',NULL),(135,2,'3041481','',NULL),(136,7,'1391 201','',NULL),(138,7,'1361 236','',NULL),(139,7,'1391 295','',NULL),(141,7,'1361 384','',NULL),(143,2,'111','',NULL),(143,3,'111','',NULL),(143,4,'11','',NULL),(143,6,'11111','',NULL),(143,8,'1111','1121',1111),(143,10,'122','',0),(143,11,'122','',NULL),(143,16,'1','1',1),(147,7,'1361 481','',NULL),(148,8,'627/47','',NULL),(149,2,'2466213','8/69',NULL),(151,2,'2466193','8/69',NULL),(152,2,'2466196','8/69',NULL),(153,2,'2466202','8/69',NULL),(154,2,'2466209','8/69',NULL),(155,2,'2466217','8/69 ',NULL),(156,2,'2466221','8/69 ',3),(157,2,'2466225','8/70',3),(158,2,'2466226','8/70',4),(160,2,'2466118','8/70',7),(161,2,'2466122','8/70',7),(162,2,'2466124','8/70',7),(163,2,'2466126','8/70',7),(165,2,'2466135','8/71',NULL),(166,2,'2466137','8/71',NULL),(167,2,'2466139','8/71',NULL),(168,2,'2466141','8/71',NULL),(169,2,'2466143','8/71',NULL),(170,2,'2466147','8/71',NULL),(171,2,'2466149','8/71',NULL),(172,2,'2466153','8/71',NULL),(176,2,'2466128','8/70',NULL),(178,2,'2466131','8/70',NULL),(179,2,'2688002','',0),(179,14,'209.70.020','',0),(180,2,'2688004','',0),(180,14,'209.70.032','',0),(181,2,'2688005','',0),(181,14,'209.70.040','',0),(182,2,'2688006','',0),(182,14,'209.70.050','',0),(183,2,'2688013','3/57',0),(183,14,'209.75.050','',0),(184,14,'208.70.032','',0),(185,14,'208.70.050','',0),(219,9,'24099100','',NULL),(220,9,'24023573','',NULL),(226,11,'820 17','',NULL),(227,11,'820 16','',NULL),(228,11,'820 15','',NULL),(229,11,'823 18','',NULL),(230,11,'823 18 ?','',NULL),(231,11,'632 07 069 005','',NULL),(233,11,'318 10 070 121','',NULL),(234,11,'318 10 071 114','',NULL),(235,11,'324 19 055 003','',NULL),(236,11,'334 06 102 009','',NULL),(237,11,'324 36 025 004','',NULL),(238,11,'305 01 237 005','',NULL),(239,11,'312 07 061 002','',NULL),(240,11,'335 08 030 011','',NULL),(241,11,'310 01 116 001','',NULL),(242,11,'312 07 060 003','',NULL),(243,11,'528 12 001 066','',NULL),(244,11,'528 12 001 165','',NULL),(245,11,'638 01 120 006','',NULL),(246,11,'638 02 052 000','',NULL),(247,11,'338 09 130 018','',NULL),(248,11,'338 09 133 014','',NULL),(249,11,'338 09 132 010','',NULL),(250,11,'312 04 067 001','',NULL),(251,11,'307 01 064 004','',NULL),(252,11,'314 13 095 001','',NULL),(253,11,'328 05 062 009','',NULL),(254,11,'324 27 049 008','',NULL),(255,11,'305 14 101 007','',NULL),(256,11,'334 06 227 006','',NULL),(257,11,'315 10 070 000','',NULL),(258,11,'301 02 072 001','',NULL),(259,11,'335 02 053 013','',NULL),(260,11,'319 03 138 007','',NULL),(261,11,'518 18 001 062','',NULL),(262,11,'518 18 001 161','',NULL),(263,11,'324 16 074 008','',NULL),(264,11,'315 06 224 002','',NULL),(265,11,'305 17 008 009','',NULL),(266,11,'305 17 007 001','',NULL),(268,11,'7 221 41','',0),(269,11,'7229 26','',0),(272,12,'29935255','',0),(275,13,'2751586','',0),(276,13,'2751585','',0),(278,13,'2751584','',0),(279,8,'795/20','',0),(280,8,'795/28','',0),(281,7,'1018 205','',0),(282,7,'1018 280','',0),(283,7,'1018 361','',0),(284,2,'2687957','3/56',0),(284,14,'209.50.050','',0),(286,2,'2687953','3/56',0),(286,14,'20950020','',0),(287,2,'2687955','3/56',0),(287,14,'20950032','',0),(288,2,'2687956','3/56',0),(288,14,'20950040','',0),(289,7,'2044161','',0),(290,8,'1590/42','',0),(292,7,'2047829','',0),(293,7,'2047853','',0),(295,14,'259.95.020','',0),(296,14,'259.95.040','',0),(297,14,'259.95.050','',0),(298,8,'1590/29','',0),(392,16,'111PU',NULL,6350),(403,30,'86029046','',0),(404,31,'060201004','',0),(407,32,'22100251738','',0),(408,33,'ßß','',0),(411,36,'94951','',0),(412,37,'4175 0186 90','',0),(413,38,'EL 494 K 338 L','',550),(414,39,'E 55 / 01 / 153-a','',0),(415,39,'E 55 / 01 / 153 / 1','',0),(416,39,'E 55 / 01 / 153-b','',0),(419,41,'00645561','',0),(420,42,'01375-0003592','',0),(422,4,'15901','',0),(426,30,'469 667 44','',0),(427,30,'469 525 44','',0);
/*!40000 ALTER TABLE `lieferantenartikelbestellnummer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-08-12 17:21:24
