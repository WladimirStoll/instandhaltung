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
-- Table structure for table `ebene`
--

DROP TABLE IF EXISTS `ebene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ebene` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nummer` int(10) unsigned NOT NULL,
  `fk_saeule` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ebene_nummer_saeule_eindeutlich` (`nummer`,`fk_saeule`),
  KEY `FK_ebene_saeule` (`fk_saeule`),
  CONSTRAINT `FK_ebene_saeule` FOREIGN KEY (`fk_saeule`) REFERENCES `saeule` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ebene`
--

LOCK TABLES `ebene` WRITE;
/*!40000 ALTER TABLE `ebene` DISABLE KEYS */;
INSERT INTO `ebene` VALUES (1,1,1),(3,1,11),(6,1,12),(13,1,13),(17,1,14),(21,1,15),(25,1,16),(26,1,17),(30,1,18),(34,1,19),(38,1,20),(42,1,21),(46,1,22),(50,1,23),(54,1,24),(58,1,25),(62,1,26),(67,1,27),(73,1,29),(77,1,30),(81,1,31),(85,1,32),(89,1,33),(93,1,34),(97,1,35),(102,1,37),(107,1,38),(112,1,39),(122,1,40),(117,1,43),(123,1,45),(125,1,48),(133,1,49),(142,1,50),(179,1,52),(151,1,64),(2,2,11),(7,2,12),(12,2,13),(66,2,14),(20,2,15),(23,2,16),(27,2,17),(31,2,18),(35,2,19),(39,2,20),(43,2,21),(47,2,22),(51,2,23),(55,2,24),(59,2,25),(63,2,26),(68,2,27),(74,2,29),(78,2,30),(82,2,31),(86,2,32),(90,2,33),(94,2,34),(98,2,35),(103,2,37),(108,2,38),(113,2,39),(118,2,43),(126,2,48),(134,2,49),(143,2,50),(180,2,52),(152,2,64),(4,3,11),(8,3,12),(11,3,13),(15,3,14),(19,3,15),(24,3,16),(28,3,17),(32,3,18),(36,3,19),(40,3,20),(44,3,21),(52,3,23),(56,3,24),(60,3,25),(64,3,26),(69,3,27),(75,3,29),(79,3,30),(83,3,31),(87,3,32),(91,3,33),(95,3,34),(99,3,35),(104,3,37),(109,3,38),(114,3,39),(119,3,43),(127,3,48),(135,3,49),(144,3,50),(181,3,52),(153,3,64),(5,4,11),(9,4,12),(10,4,13),(14,4,14),(18,4,15),(22,4,16),(29,4,17),(33,4,18),(37,4,19),(41,4,20),(45,4,21),(49,4,22),(53,4,23),(57,4,24),(61,4,25),(65,4,26),(70,4,27),(76,4,29),(80,4,30),(84,4,31),(88,4,32),(92,4,33),(96,4,34),(100,4,35),(105,4,37),(110,4,38),(115,4,39),(120,4,43),(128,4,48),(136,4,49),(145,4,50),(182,4,52),(154,4,64),(71,5,27),(101,5,35),(106,5,37),(111,5,38),(116,5,39),(129,5,48),(137,5,49),(146,5,50),(183,5,52),(155,5,64),(72,6,27),(130,6,48),(138,6,49),(147,6,50),(184,6,52),(156,6,64),(131,7,48),(139,7,49),(148,7,50),(185,7,52),(157,7,64),(162,8,48),(140,8,49),(149,8,50),(186,8,52),(158,8,64),(163,9,48),(141,9,49),(150,9,50),(187,9,52),(159,9,64),(164,10,48),(167,10,49),(165,11,48),(168,11,49),(166,12,48),(169,12,49),(170,13,49),(16,22,1),(48,128,4);
/*!40000 ALTER TABLE `ebene` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-08-12 17:21:27
