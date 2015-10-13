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
-- Table structure for table `kostenstelle`
--

DROP TABLE IF EXISTS `kostenstelle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kostenstelle` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(20) CHARACTER SET latin1 NOT NULL,
  `nummer` varchar(10) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kostenstelle`
--

LOCK TABLES `kostenstelle` WRITE;
/*!40000 ALTER TABLE `kostenstelle` DISABLE KEYS */;
INSERT INTO `kostenstelle` VALUES (1,'Elektrowerkstatt','61'),(2,'Schlosserei','62'),(3,'Formautomat','325'),(4,'Elektroofen','302'),(5,'Modelbau','130'),(6,'Grundstücke/Gebäude','10'),(7,'Allg. Werkdienst','11'),(8,'Strom-,Gas-,Wasser','20'),(9,'Druckluft','23'),(10,'Heizungsanlage','24'),(11,'Fuhrpark','30'),(12,'Flurförderfahrzeuge','51'),(13,'Krananlagen','52'),(14,'Sozialeinrichtung','85'),(15,'EDV','90'),(16,'AV','105'),(17,'QS','110'),(18,'Modellager','140'),(19,'Umweltschütz','150'),(20,'Entst. Putzerei KE16','161'),(21,'Entst. EOfen KE15','162'),(22,'Entst. Automat 2+3+4','163'),(23,'Entst. Rump','164'),(24,'Elektrolager','1200/4825'),(25,'Schlosserlager','1200/4822'),(26,'Gattierungsanlage','303'),(27,'Pfannenwirtschaft','305'),(28,'Handkernmacherei','314'),(29,'Kernschießmaschinen','315'),(30,'HF1','321'),(31,'HF2','322'),(33,'HF','324'),(34,'Sandregen. Furanharz','334'),(35,'Sandregen. Nassguss','335'),(36,'Strahlen Kaltharz','411'),(37,'Strahlen Nassguss','412'),(38,'Putzen HF1+2','422'),(39,'Putzen HF','424'),(40,'Putzen Nassguss','425'),(41,'Gussschweißen','430'),(42,'Wärmebehandlung','480'),(43,'Grundieren Kaltguss','491'),(44,'Grundieren Nassguss','492'),(45,'Versand','805'),(46,'Ausbildung','900');
/*!40000 ALTER TABLE `kostenstelle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-08-12 17:21:28
