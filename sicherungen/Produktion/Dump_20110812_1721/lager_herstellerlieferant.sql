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
-- Table structure for table `herstellerlieferant`
--

DROP TABLE IF EXISTS `herstellerlieferant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `herstellerlieferant` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `adresse_land` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adresse_plz` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adresse_stadt` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adresse_strasse` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `telefon` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fax` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ansprechpartner` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `herstellerlieferant_name_eindeutlich` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `herstellerlieferant`
--

LOCK TABLES `herstellerlieferant` WRITE;
/*!40000 ALTER TABLE `herstellerlieferant` DISABLE KEYS */;
INSERT INTO `herstellerlieferant` VALUES (1,'Siemens','DE','87434','stadt','strasse','tel','fax','','and'),(2,'Moeller','DE','?','?','?','?','?','?','?'),(3,'Festo','DE','87439','Augsburg','Eichendorffweg ','0831 / 1102','0831 / 1103 ','email@email.de23','Ansprechpartner von Hersteller 2'),(4,'Schneider','DE','87478','Muenchen','Eichendorffweg 16','0831 / 1102','0831 / 1103 ','email@email.de23',''),(5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger'),(6,'Schaller','DE','87436','Kempten (Ursulasried','Eichendorffweg 6','0831 / 110 6','0831 / 1103 6','email@emai6l.de',''),(7,'Demag','DE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'WTC','DE','asfg','asf','asf','asfd','asfdasfd','afd','H.Bröcker'),(9,'Bauer','DE','asfd','afd','asfd','asf','asf','asf','asf'),(10,'Nord','DE','a','asf','asfd','as','asf','asf','a'),(11,'Elektrim Fritz Obers','DE','86720','Nödlingen',NULL,NULL,NULL,NULL,NULL),(12,'Schäfer/Siegen','DE','57074','Siegen-Feuersbach','Kränerstr.11','027375010','02737501100','ino@e-schaefer-kg.de','Weber'),(13,'Busch-Jäger','DE','safd','asfd','afd','afd','','',''),(14,'Norka','DE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,'Bals','DE','asfd','af','asfd','asf','asf','asfd','asf'),(16,'Fein','DE','73529','SchwäbischGmünd-Barg','Hans-Fein-Str.81',NULL,'07173/183823',NULL,'Fr.Zondler'),(18,'Omron','Schweiz ?','?','?','?','?','?','?',''),(32,'SAWA','Schweiz ?','?','?','','?','','','?'),(33,'Rüetschi','Schweiz','5040','Schöftland','?','','','',''),(34,'Unitec AG','Schweiz','?','Kloten','','','','',''),(35,'Bachofen','Schweiz?','8610','Uster','Ackerstr.42','01 940 7001','','',''),(36,'Roth+Co','Schweiz?',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(37,'Bürkert GmbH & Co.KG','DE','74653 ','Ingelfingen','Christian-Bürkert-Straße 13-17','07940 10-0','07940 1091204','',''),(38,'Schneider Electric','DE','40880','Ratingen','Gothauer Strasse 29','02102404-0','02102404-9256','www.schneider-electric.de',NULL),(39,'R.Pöppel GmbH & Co.K','DE','87700','Memmingen','Alpenstr.45','08331-9559-0','08331-0550-69','',''),(40,'Endress+Hauser Messtechnik ','DE','81249','München','ClaritaBernhardStr25','089-84009-117','089-84009-133','janet.curmann@de.endress.com','Janet Curmann'),(43,'Elektra','','','','','','','',''),(44,'Klauke','','','','','','','',''),(45,'Eltako','','','','','','','',''),(46,'Georg Fischer GmbH','DE','73095','Albershausen','Daimlerstr.6','07161-302-0','07161-302-111','info@georgfischer.de',''),(48,'Stemmann-Technik GmbH','DE','48465','Schüttdorf','Niedersachsenstr.2','05923-810','05923-81100','info@stemmann.de / j.lammereing@stemmann.de','Jurgen Lammering'),(51,'Conrad Elektronic SE','','92241','Hirschau','Klaus-Conradstr.1','09604 40-8988','09604 40-8936','www.conrad.biz',''),(52,'Robert Bosch GmbH','','37589','Kalefeld/Willershaus','Zur Lunne 2','01801 505010','01801 505011','Fachhandel@de.bosch.com',''),(53,'Hörburger Control Systems','DE','87448','Waltenhofen','Gewerbestr.5','0831 52241-0','0831 12918','info@hoerburger.de',''),(54,'PHOENIX CONTACT','D','32825','Blomberg','Flachsmarktstraße 8','05235 300','05235 341200','www.phoenixcontact.com',''),(55,'Hensel','','','','','','','',''),(57,'Helerma','?','?','?','?','?','?','?','?'),(58,'Newlec','','','','','','','',''),(59,'Wago','','','','','','','',''),(60,'OBO (Bettermann)','','','','','','','',''),(61,'Kleinhuis','','','','','','','',''),(62,'Fränkische Rohrwerke','D','97486','Königsberg/Bayern','','09525 88-0','09525 88751','',''),(65,'Pfeifer','','','','','','','',''),(66,'Foundry-Service GmbH','D','58675','Hemer','Untere Weide 12-14','02372 / 559810','02372 / 559816','info@fondry-srevice.de','Fr.Schulz'),(67,'Industrieanlagen Hlebar','','58256','Ennepetal','Katzbachstr.3','02333 / 604470','02333 / 630910','www.industrieanlagen-hlebar.de',''),(68,'Zerrle Schweisstechnik Grosshandel GmbH','D','86154','Augsburg','Meierweg 6','0821 / 298299-0','0821 / 298299-29','zerrle@zerrle.com','Kübler Moritz'),(69,'AHB Giessereitechnik GmbH','','79585','Steinen','Bahnhofstr.20','07627 / 924908','07627 / 924909','',''),(71,'Mannesmann Demag','','','','','','','',''),(72,'Telemecanique','','','','','','','',''),(73,'ABB','','','','','','','',''),(74,'E.Dold & Söhne KG','D','78120','Furtwangen','','','','www.dold.com',''),(75,'Emod','','','','','','','',''),(76,'Kühnle','','','','','','','',''),(77,'SEW','','','','','','','',''),(78,'Stöber','','','','','','','',''),(79,'AEW','','','','','','','',''),(80,'UHDE','','','','','','','',''),(81,'Schörch','','','','','','','',''),(82,'MAZ','','','','','','','',''),(83,'Pepperl+Fuchs','','','','','','','',''),(84,'SIHI','','','','','','','',''),(85,'???','d','213','231asdf','','','','',''),(86,'Moog','','','','','','','',''),(88,'Mangold','D','88046','Friedrichshafen','Lindauerstr.116','07541 500622','07541 500630','info@mangold-fn.de','Reinhold Müller'),(91,'Belimo','Schweiz','????','????','????','','','',''),(92,'Schäfer Technik GmbH','D','89077','Ulm','Bleichstraße 24','0731 96622-0','0731 96622-51','info@schaefer-technik.de',''),(93,'Ludwid Meister','','87437','Kempten','Ludwigstraße 10','8031 564254-710','','','Bernhard Baur'),(94,'SKF','???','?','?','?','?','','',''),(95,'Leopold Siegle','D','86165','Augsburg','Stätzlinger Straße 53','0821 7905-115','0821 7905-155','','Dieter Weiß'),(96,'Atlas Copco','Schweden','ß','ß','ß','ß','','',''),(97,'Greif Robot Schleifsystem GmbH','D','58285','Gevelsberg','Jahnstraße 24','02332 7049-0','02332 12248','info@greif-an.de',''),(98,'Sudhoff','D','89003','Ulm','August-Nagel Straße 1','07305 9261-0','07305 9261-500','info@sudhoff-technik.de',''),(100,'Hempel','D','89231','Ulm','Otto-Renner-Straße 22','0731 75050','0731 74951','info@hempel-vulkanisation.de','Roy Hempel'),(101,'EME Elektro Maschinenbau Ettlingen GmbH','D','','','','','','',''),(102,'Rietschle Thomas','D','','','','','','','');
/*!40000 ALTER TABLE `herstellerlieferant` ENABLE KEYS */;
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
