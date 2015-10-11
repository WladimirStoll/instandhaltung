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
-- Table structure for table `bericht_bestellanforderung`
--

DROP TABLE IF EXISTS `bericht_bestellanforderung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bericht_bestellanforderung` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `erstellungsdatum` datetime DEFAULT NULL,
  `status` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_herstellerLieferant` int(10) unsigned DEFAULT NULL,
  `fk_herstellerLieferant_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_herstellerLieferant_adresse_land` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_herstellerLieferant_adresse_plz` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_herstellerLieferant_adresse_stadt` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_herstellerLieferant_adresse_strasse` varchar(35) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_herstellerLieferant_telefon` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_herstellerLieferant_fax` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_herstellerLieferant_email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_herstellerLieferant_ansprechpartner` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_benutzerAbsender` int(10) unsigned DEFAULT NULL,
  `fk_benutzerAbsender_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_benutzerAbsender_vorname` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_benutzerAbsender_loginName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_benutzerAbsender_email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_benutzerAnnehmer` int(10) unsigned DEFAULT NULL,
  `fk_benutzerAnnehmer_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_benutzerAnnehmer_vorname` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_benutzerAnnehmer_loginName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_benutzerAnnehmer_email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email_ba_empfaenger` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bericht_bestellanforderung`
--

LOCK TABLES `bericht_bestellanforderung` WRITE;
/*!40000 ALTER TABLE `bericht_bestellanforderung` DISABLE KEYS */;
INSERT INTO `bericht_bestellanforderung` VALUES (3,1148,'2010-07-04 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(4,1148,'2010-07-04 00:00:00','E',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(5,1148,'2010-07-04 00:00:00','E',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(6,1148,'2010-07-04 00:00:00','E',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(7,1145,'2010-07-02 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'email@emai5l.de'),(8,1148,'2010-07-04 00:00:00','E',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(9,1146,'2010-07-03 00:00:00','O',6,'Schaller','DE','87436','Kempten (Ursulasried','Eichendorffweg 6','0831 / 110 6','0831 / 1103 6','email@emai6l.de','Ansprechpartner von Lieferant 6',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(10,1146,'2010-07-03 00:00:00','E',6,'Schaller','DE','87436','Kempten (Ursulasried','Eichendorffweg 6','0831 / 110 6','0831 / 1103 6','email@emai6l.de','Ansprechpartner von Lieferant 6',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(11,1146,'2010-07-03 00:00:00','E',6,'Schaller','DE','87436','Kempten (Ursulasried','Eichendorffweg 6','0831 / 110 6','0831 / 1103 6','email@emai6l.de','Ansprechpartner von Lieferant 6',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(12,1146,'2010-07-03 00:00:00','E',6,'Schaller','DE','87436','Kempten (Ursulasried','Eichendorffweg 6','0831 / 110 6','0831 / 1103 6','email@emai6l.de','Ansprechpartner von Lieferant 6',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(13,1142,'2010-06-25 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'email@email.de23'),(14,1142,'2010-06-25 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'email@email.de23'),(15,1126,'2010-06-12 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(16,1141,'2010-06-12 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(17,1143,'2010-06-25 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(18,1143,'2010-06-25 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(19,1143,'2010-06-25 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(20,1098,'2010-06-11 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(21,1140,'2010-06-12 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(22,1140,'2010-06-12 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(23,1140,'2010-06-12 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(24,1140,'2010-06-12 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(25,1140,'2010-06-12 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(26,1099,'2010-06-11 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(27,1128,'2010-06-12 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(28,1128,'2010-06-12 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(29,1092,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(30,1127,'2010-06-12 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(31,1127,'2010-06-12 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(32,1127,'2010-06-12 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(33,1124,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(34,1122,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(35,1122,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(36,1122,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(37,1122,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(38,1088,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(39,1089,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(40,1089,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(41,1090,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(42,1090,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(43,1091,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(44,1091,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(45,1091,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(46,1091,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(47,1046,'2010-05-14 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(48,1046,'2010-05-14 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(49,1093,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(50,1112,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(51,1096,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(52,1112,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(53,1112,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(54,1112,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(55,1112,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(56,1112,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(57,1093,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(58,1093,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(59,1094,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(60,1093,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(61,1112,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(62,1112,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(63,1096,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(64,1097,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'schlosserei@ke-ag.de'),(65,1102,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),(66,1102,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),(67,1046,'2010-05-14 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'schlosserei@ke-ag.de'),(68,1046,'2010-05-14 00:00:00','E',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'schlosserei@ke-ag.de'),(69,1108,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(70,1107,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(71,1048,'2010-05-14 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(72,1048,'2010-05-14 00:00:00','E',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(73,1105,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(74,1049,'2010-05-14 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(75,1104,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(76,1051,'2010-05-14 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),(77,1155,'2011-03-05 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(78,1052,'2010-05-14 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(79,1052,'2010-05-14 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(80,1052,'2010-05-14 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(107,1156,'2011-04-10 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(108,1156,'2011-04-10 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(109,1103,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(110,1103,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(115,1157,'2011-04-14 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),(116,1157,'2011-04-14 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),(117,1157,'2011-04-14 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),(119,1157,'2011-04-14 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),(120,1157,'2011-04-14 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),(121,1056,'2010-05-14 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(169,34,'2010-01-13 00:00:00','O',1,'Siemens','DE','87434','stadt','strasse','tel','fax','email','and',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(170,34,'2010-01-13 00:00:00','E',1,'Siemens','DE','87434','stadt','strasse','tel','fax','email','and',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'eugen.schmidt3@freenet.de'),(171,1141,'2010-06-12 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(174,1158,'2011-07-18 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(191,1159,'2011-08-04 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(192,1159,'2011-08-04 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(193,1160,'2011-08-04 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(194,1159,'2011-08-04 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(195,1160,'2011-08-04 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'wladimir.stoll@gmx.de'),(196,1161,'2011-08-05 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),(198,1162,'2011-08-06 00:00:00','O',69,'AHB Giessereitechnik GmbH',NULL,'79585','Steinen','Bahnhofstr.20','07627 / 924908','07627 / 924909',NULL,NULL,14,'lager','lager','lager',NULL,NULL,NULL,NULL,NULL,NULL,'wladimir.stoll@gmx.de'),(199,1163,'2011-08-06 00:00:00','O',35,'Bachofen','Schweiz?','8610','Uster','Ackerstr.42','01 940 7001',NULL,NULL,NULL,14,'lager','lager','lager',NULL,NULL,NULL,NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),(201,1164,'2011-08-06 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,70,'Kovacevic','Mile','mile','elektrowerkstatt@ke-ag.de',NULL,NULL,NULL,NULL,NULL,'schlosserei@ke-ag.de'),(202,1165,'2011-08-06 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,70,'Kovacevic','Mile','mile','elektrowerkstatt@ke-ag.de',NULL,NULL,NULL,NULL,NULL,'schlosserei@ke-ag.de'),(203,1166,'2011-08-06 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,70,'Kovacevic','Mile','mile','elektrowerkstatt@ke-ag.de',NULL,NULL,NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),(204,1167,'2011-08-06 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,70,'Kovacevic','Mile','mile','elektrowerkstatt@ke-ag.de',NULL,NULL,NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),(205,1168,'2011-08-06 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,70,'Kovacevic','Mile','mile','elektrowerkstatt@ke-ag.de',NULL,NULL,NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),(206,1168,'2011-08-06 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,70,'Kovacevic','Mile','mile','elektrowerkstatt@ke-ag.de',NULL,NULL,NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),(207,1169,'2011-08-12 00:00:00','O',12,'Schäfer/Siegen','DE','57074','Siegen-Feuersbach','Kränerstr.11','027375010','02737501100','ino@e-schaefer-kg.de','Weber',20,'Schmidt','Eugen','eugen','elektrowerkstatt@ke-ag.de',NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de');
/*!40000 ALTER TABLE `bericht_bestellanforderung` ENABLE KEYS */;
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
