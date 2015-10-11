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
-- Table structure for table `bericht_inventur_etage`
--

DROP TABLE IF EXISTS `bericht_inventur_etage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bericht_inventur_etage` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `name` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `fk_halle` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bericht_inventur_etage`
--

LOCK TABLES `bericht_inventur_etage` WRITE;
/*!40000 ALTER TABLE `bericht_inventur_etage` DISABLE KEYS */;
INSERT INTO `bericht_inventur_etage` VALUES (83,1,'Lager über dem E.Werkstatt',83),(83,5,'E.Werkstatt',83),(83,13,'Obergeschoss E.Werkstatt',83),(83,14,'Schraubenlager',83),(84,1,'Lager über dem E.Werkstatt',84),(84,5,'E.Werkstatt',84),(84,13,'Obergeschoss E.Werkstatt',84),(84,14,'Schraubenlager',84),(85,1,'Lager über dem E.Werkstatt',85),(85,5,'E.Werkstatt',85),(85,13,'Obergeschoss E.Werkstatt',85),(85,14,'Schraubenlager',85),(86,1,'Lager über dem E.Werkstatt',86),(86,5,'E.Werkstatt',86),(86,13,'Obergeschoss E.Werkstatt',86),(86,14,'Schraubenlager',86),(87,1,'Lager über dem E.Werkstatt',87),(87,5,'E.Werkstatt',87),(87,13,'Obergeschoss E.Werkstatt',87),(87,14,'Schraubenlager',87),(88,1,'Lager über dem E.Werkstatt',88),(88,5,'E.Werkstatt',88),(88,13,'Obergeschoss E.Werkstatt',88),(88,14,'Schraubenlager',88),(89,1,'Lager über dem E.Werkstatt',89),(89,5,'E.Werkstatt',89),(89,13,'Obergeschoss E.Werkstatt',89),(89,14,'Schraubenlager',89),(90,1,'Lager über dem E.Werkstatt',90),(90,5,'E.Werkstatt',90),(90,13,'Obergeschoss E.Werkstatt',90),(90,14,'Schraubenlager',90),(91,1,'Lager über dem E.Werkstatt',91),(91,5,'E.Werkstatt',91),(91,13,'Obergeschoss E.Werkstatt',91),(91,14,'Schraubenlager',91),(92,1,'Lager über dem E.Werkstatt',92),(92,5,'E.Werkstatt',92),(92,13,'Obergeschoss E.Werkstatt',92),(92,14,'Schraubenlager',92),(93,1,'Lager über dem E.Werkstatt',93),(93,5,'E.Werkstatt',93),(93,13,'Obergeschoss E.Werkstatt',93),(93,14,'Schraubenlager',93),(94,1,'Lager über dem E.Werkstatt',94),(94,5,'E.Werkstatt',94),(94,13,'Obergeschoss E.Werkstatt',94),(94,14,'Schraubenlager',94),(95,1,'Lager über dem E.Werkstatt',95),(95,5,'E.Werkstatt',95),(95,13,'Obergeschoss E.Werkstatt',95),(95,14,'Schraubenlager',95),(96,1,'Lager über dem E.Werkstatt',96),(96,5,'E.Werkstatt',96),(96,13,'Obergeschoss E.Werkstatt',96),(96,14,'Schraubenlager',96),(98,3,'Obergeschoss E.Ofen',98),(98,4,'E.OfenKeller',98),(98,15,'Ofenleitstand',98),(102,6,'SchreinereiKeller',102),(103,1,'Lager über dem E.Werkstatt',103),(103,5,'E.Werkstatt',103),(103,13,'Obergeschoss E.Werkstatt',103),(103,14,'Schraubenlager',103),(104,1,'Lager über dem E.Werkstatt',104),(104,5,'E.Werkstatt',104),(104,13,'Obergeschoss E.Werkstatt',104),(104,14,'Schraubenlager',104),(105,1,'Lager über dem E.Werkstatt',105),(105,5,'E.Werkstatt',105),(105,13,'Obergeschoss E.Werkstatt',105),(105,14,'Schraubenlager',105),(106,1,'Lager über dem E.Werkstatt',106),(106,5,'E.Werkstatt',106),(106,13,'Obergeschoss E.Werkstatt',106),(106,14,'Schraubenlager',106),(111,1,'Lager über dem E.Werkstatt',111),(111,5,'E.Werkstatt',111),(111,13,'Obergeschoss E.Werkstatt',111),(111,14,'Schraubenlager',111),(112,1,'Lager über dem E.Werkstatt',112),(112,5,'E.Werkstatt',112),(112,13,'Obergeschoss E.Werkstatt',112),(112,14,'Schraubenlager',112),(113,1,'Lager über dem E.Werkstatt',113),(113,5,'E.Werkstatt',113),(113,13,'Obergeschoss E.Werkstatt',113),(113,14,'Schraubenlager',113),(114,1,'Lager über dem E.Werkstatt',114),(114,5,'E.Werkstatt',114),(114,13,'Obergeschoss E.Werkstatt',114),(114,14,'Schraubenlager',114),(118,1,'Lager über dem E.Werkstatt',118),(118,5,'E.Werkstatt',118),(118,13,'Obergeschoss E.Werkstatt',118),(118,14,'Schraubenlager',118),(129,1,'Lager über dem E.Werkstatt',129),(129,5,'E.Werkstatt',129),(129,13,'Obergeschoss E.Werkstatt',129),(129,14,'Schraubenlager',129),(130,1,'Lager über dem E.Werkstatt',130),(130,5,'E.Werkstatt',130),(130,13,'Obergeschoss E.Werkstatt',130),(130,14,'Schraubenlager',130),(131,1,'Lager über dem E.Werkstatt',131),(131,5,'E.Werkstatt',131),(131,13,'Obergeschoss E.Werkstatt',131),(131,14,'Schraubenlager',131),(132,1,'Lager über dem E.Werkstatt',132),(132,5,'E.Werkstatt',132),(132,13,'Obergeschoss E.Werkstatt',132),(132,14,'Schraubenlager',132),(133,1,'Lager über dem E.Werkstatt',133),(133,5,'E.Werkstatt',133),(133,13,'Obergeschoss E.Werkstatt',133),(133,14,'Schraubenlager',133),(135,1,'Lager über dem E.Werkstatt',135),(135,5,'E.Werkstatt',135),(135,13,'Obergeschoss E.Werkstatt',135),(135,14,'Schraubenlager',135),(136,1,'Lager über dem E.Werkstatt',136),(136,5,'E.Werkstatt',136),(136,13,'Obergeschoss E.Werkstatt',136),(136,14,'Schraubenlager',136),(137,1,'Lager über dem E.Werkstatt',137),(137,5,'E.Werkstatt',137),(137,13,'Obergeschoss E.Werkstatt',137),(137,14,'Schraubenlager',137),(143,1,'Lager über dem E.Werkstatt',143),(143,5,'E.Werkstatt',143),(143,13,'Obergeschoss E.Werkstatt',143),(143,14,'Schraubenlager',143),(155,1,'Lager über dem E.Werkstatt',155),(155,5,'E.Werkstatt',155),(155,13,'Obergeschoss E.Werkstatt',155),(155,14,'Schraubenlager',155),(156,1,'Lager über dem E.Werkstatt',156),(156,5,'E.Werkstatt',156),(156,13,'Obergeschoss E.Werkstatt',156),(156,14,'Schraubenlager',156),(157,1,'Lager über dem E.Werkstatt',157),(157,5,'E.Werkstatt',157),(157,13,'Obergeschoss E.Werkstatt',157),(157,14,'Schraubenlager',157),(158,1,'Lager über dem E.Werkstatt',158),(158,5,'E.Werkstatt',158),(158,13,'Obergeschoss E.Werkstatt',158),(158,14,'Schraubenlager',158),(159,1,'Lager über dem E.Werkstatt',159),(159,5,'E.Werkstatt',159),(159,13,'Obergeschoss E.Werkstatt',159),(159,14,'Schraubenlager',159),(160,1,'Lager über dem E.Werkstatt',160),(160,5,'E.Werkstatt',160),(160,13,'Obergeschoss E.Werkstatt',160),(160,14,'Schraubenlager',160),(161,1,'Lager über dem E.Werkstatt',161),(161,5,'E.Werkstatt',161),(161,13,'Obergeschoss E.Werkstatt',161),(161,14,'Schraubenlager',161),(162,1,'Lager über dem E.Werkstatt',162),(162,5,'E.Werkstatt',162),(162,13,'Obergeschoss E.Werkstatt',162),(162,14,'Schraubenlager',162),(164,1,'Lager über dem E.Werkstatt',164),(164,5,'E.Werkstatt',164),(164,13,'Obergeschoss E.Werkstatt',164),(164,14,'Schraubenlager',164),(165,1,'Lager über dem E.Werkstatt',165),(165,5,'E.Werkstatt',165),(165,13,'Obergeschoss E.Werkstatt',165),(165,14,'Schraubenlager',165),(166,1,'Lager über dem E.Werkstatt',166),(166,5,'E.Werkstatt',166),(166,13,'Obergeschoss E.Werkstatt',166),(166,14,'Schraubenlager',166),(167,1,'Lager über dem E.Werkstatt',167),(167,5,'E.Werkstatt',167),(167,13,'Obergeschoss E.Werkstatt',167),(167,14,'Schraubenlager',167),(168,1,'Lager über dem E.Werkstatt',168),(168,5,'E.Werkstatt',168),(168,13,'Obergeschoss E.Werkstatt',168),(168,14,'Schraubenlager',168),(172,1,'Lager über dem E.Werkstatt',172),(172,5,'E.Werkstatt',172),(172,13,'Obergeschoss E.Werkstatt',172),(172,14,'Schraubenlager',172),(176,11,'Eisenlager',176),(176,12,'KranersatzzeileLager',176),(177,1,'Lager über dem E.Werkstatt',177),(177,5,'E.Werkstatt',177),(177,13,'Obergeschoss E.Werkstatt',177),(177,14,'Schraubenlager',177),(183,1,'Lager über dem E.Werkstatt',183),(183,5,'E.Werkstatt',183),(183,13,'Obergeschoss E.Werkstatt',183),(183,14,'Schraubenlager',183),(188,1,'Lager über dem E.Werkstatt',188),(188,5,'E.Werkstatt',188),(188,13,'Obergeschoss E.Werkstatt',188),(188,14,'Schraubenlager',188),(189,1,'Lager über dem E.Werkstatt',189),(189,5,'E.Werkstatt',189),(189,13,'Obergeschoss E.Werkstatt',189),(189,14,'Schraubenlager',189);
/*!40000 ALTER TABLE `bericht_inventur_etage` ENABLE KEYS */;
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
