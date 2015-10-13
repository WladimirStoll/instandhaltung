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
-- Table structure for table `benutzer`
--

DROP TABLE IF EXISTS `benutzer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `benutzer` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `vorname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `loginName` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `copy_ba_email_empfaenger` int(11) NOT NULL,
  `erfassungsbenutzer` int(10) unsigned DEFAULT NULL,
  `erfassungsdatum` datetime DEFAULT NULL,
  `aenderungsbenutzer` int(10) unsigned DEFAULT NULL,
  `aenderungsdatum` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `benutzer_loginName_eindeutig` (`loginName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `benutzer`
--

LOCK TABLES `benutzer` WRITE;
/*!40000 ALTER TABLE `benutzer` DISABLE KEYS */;
INSERT INTO `benutzer` VALUES (14,'lager','lager','lager','pass','',0,NULL,NULL,NULL,NULL),(17,'Wachter','Peter','peter','pete','schlosserei@ke-ag.de',0,NULL,NULL,NULL,NULL),(18,'Miftari','Saja','HeiligerMann','hagi','schlosserei@ke-ag.de',0,NULL,NULL,14,'2011-01-17 00:00:00'),(19,'Thurner','Dieter','Dieter','Diet','thurner@ke-ag.de',0,NULL,NULL,NULL,NULL),(20,'Schmidt','Eugen','eugen','1111','elektrowerkstatt@ke-ag.de',0,NULL,NULL,NULL,NULL),(57,'admin','admin2','admin2','1111','eugen.schmidt@freenet.de',0,NULL,NULL,14,'2011-01-29 00:00:00'),(67,'Clausnitzer','Dietmar','mitarbeiter','mita','elektrowerkstatt@ke-ag.de',0,NULL,NULL,14,'2011-01-19 00:00:00'),(70,'Kovacevic','Mile','mile','mile','elektrowerkstatt@ke-ag.de',0,NULL,NULL,NULL,NULL),(71,'Cebulla','Harald','cebu','cebu','elektrowerkstatt@ke-ag.de',0,NULL,NULL,NULL,NULL),(74,'Marquart','Peter','pit','pitt','marquardt@ke-ag.de',0,NULL,NULL,14,'2011-01-25 00:00:00'),(75,'Schreier','Matthias','mate','mate','schlosserei@ke-ag.de',0,NULL,NULL,NULL,NULL),(76,'Bakic','Schevko','schevko','sche','schlosserei@ke-ag.de',0,NULL,NULL,NULL,NULL),(77,'Kristen','Klaus','krause','krau','schlosserei@ke-ag.de',0,NULL,NULL,14,'2011-01-25 00:00:00'),(78,'Schieleit','Klaus','klaus','grun','schlosserei@ke-ag.de',0,NULL,NULL,14,'2011-01-17 00:00:00'),(79,'Schmidt','Alfred','alufred','aluf','schlosserei@ke-ag.de',0,NULL,NULL,NULL,NULL),(80,'Bönke','Peter','Nikolaus','niko','',0,NULL,NULL,NULL,NULL),(81,'Harder','Vitali','Vitali','Vita','',0,NULL,NULL,NULL,NULL),(82,'Reichenberger','Wolfgang','Wolfi','wolf','',0,NULL,NULL,NULL,NULL),(98,'Hübner','Roland','hübner','roli','',0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `benutzer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-08-12 17:21:29
