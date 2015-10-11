-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.37


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema lager
--

CREATE DATABASE IF NOT EXISTS lager;
USE lager;

--
-- Definition of table `abteilung`
--

DROP TABLE IF EXISTS `abteilung`;
CREATE TABLE `abteilung` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `abteilung_name_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `abteilung`
--

/*!40000 ALTER TABLE `abteilung` DISABLE KEYS */;
INSERT INTO `abteilung` (`id`,`name`) VALUES 
 (3,'Elektrowerkstatt'),
 (1,'Schlosserei');
/*!40000 ALTER TABLE `abteilung` ENABLE KEYS */;


--
-- Definition of table `artikel`
--

DROP TABLE IF EXISTS `artikel`;
CREATE TABLE `artikel` (
  `id` int(10) unsigned NOT NULL,
  `bezeichnung` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `typ` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `keg_nr` int(10) unsigned NOT NULL,
  `mindestbestand` int(10) unsigned NOT NULL,
  `kostenstelle` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_mengeneinheit` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `artikel_keg_nr_eindeutig` (`keg_nr`),
  UNIQUE KEY `artikel_typ_eindeutig` (`typ`),
  KEY `FK_artikel_mengeneinheit` (`fk_mengeneinheit`),
  CONSTRAINT `FK_artikel_mengeneinheit` FOREIGN KEY (`fk_mengeneinheit`) REFERENCES `mengeneinheit` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `artikel`
--

/*!40000 ALTER TABLE `artikel` DISABLE KEYS */;
INSERT INTO `artikel` (`id`,`bezeichnung`,`typ`,`keg_nr`,`mindestbestand`,`kostenstelle`,`fk_mengeneinheit`) VALUES 
 (1,'artikel 1','typ 1',1221,1,'133/128',1),
 (2,'artikel 2','typ 2',1222,1,'133/128',2),
 (3,'artikel 3','typ 3',1223,1,'133/129',3),
 (4,'artikel 4','typ 4',1224,10,'133/129',3),
 (5,'artikel 5','typ 5',1225,11,'133/129',3),
 (6,'artikel 6','typ 6',1226,12,'133/129',3),
 (7,'artikel 7','typ 7',1227,13,'345',3);
/*!40000 ALTER TABLE `artikel` ENABLE KEYS */;


--
-- Definition of table `baugruppe`
--

DROP TABLE IF EXISTS `baugruppe`;
CREATE TABLE `baugruppe` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `fk_baugruppe` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `baugruppe_name_eindeutig` (`name`),
  KEY `FK_baugruppe_baugruppe` (`fk_baugruppe`),
  CONSTRAINT `FK_baugruppe_baugruppe` FOREIGN KEY (`fk_baugruppe`) REFERENCES `baugruppe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `baugruppe`
--

/*!40000 ALTER TABLE `baugruppe` DISABLE KEYS */;
/*!40000 ALTER TABLE `baugruppe` ENABLE KEYS */;


--
-- Definition of table `benutzer`
--

DROP TABLE IF EXISTS `benutzer`;
CREATE TABLE `benutzer` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `vorname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `loginName` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `benutzer_loginName_eindeutig` (`loginName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `benutzer`
--

/*!40000 ALTER TABLE `benutzer` DISABLE KEYS */;
INSERT INTO `benutzer` (`id`,`name`,`vorname`,`loginName`,`password`) VALUES 
 (14,'lager','lager','lager','pass'),
 (15,'Benutzer Absender','Vorname Absender','absender','abse'),
 (16,'Benutzer Empfänger','Vorname Empfänger','empfaenger','empf'),
 (17,'Wachter','Peter','peter','pete');
/*!40000 ALTER TABLE `benutzer` ENABLE KEYS */;


--
-- Definition of table `benutzerzugrifsrechtecht`
--

DROP TABLE IF EXISTS `benutzerzugrifsrechtecht`;
CREATE TABLE `benutzerzugrifsrechtecht` (
  `fk_benutzer` int(10) unsigned NOT NULL,
  `fk_zugrifsrecht` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_benutzer`,`fk_zugrifsrecht`),
  KEY `FK_benutzerZugrifsrechtecht_zugrifsrecht` (`fk_zugrifsrecht`),
  CONSTRAINT `FK_benutzerZugrifsrechtecht_benutzer` FOREIGN KEY (`fk_benutzer`) REFERENCES `benutzer` (`id`),
  CONSTRAINT `FK_benutzerZugrifsrechtecht_zugrifsrecht` FOREIGN KEY (`fk_zugrifsrecht`) REFERENCES `zugriftsrechtt` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `benutzerzugrifsrechtecht`
--

/*!40000 ALTER TABLE `benutzerzugrifsrechtecht` DISABLE KEYS */;
/*!40000 ALTER TABLE `benutzerzugrifsrechtecht` ENABLE KEYS */;


--
-- Definition of table `bestellanforderung`
--

DROP TABLE IF EXISTS `bestellanforderung`;
CREATE TABLE `bestellanforderung` (
  `id` int(10) unsigned NOT NULL,
  `erstellungsdatum` datetime NOT NULL,
  `status` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `fk_herstellerLieferant` int(10) unsigned DEFAULT NULL,
  `fk_benutzerAbsender` int(10) unsigned DEFAULT NULL,
  `fk_benutzerAnnehmer` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bestellanforderung_herstellerLieferant` (`fk_herstellerLieferant`),
  KEY `FK_bestellanforderung_benutzerAbsender` (`fk_benutzerAbsender`),
  KEY `FK_bestellanforderung_benutzerAnnehmer` (`fk_benutzerAnnehmer`),
  CONSTRAINT `FK_bestellanforderung_benutzerAbsender` FOREIGN KEY (`fk_benutzerAbsender`) REFERENCES `benutzer` (`id`),
  CONSTRAINT `FK_bestellanforderung_benutzerAnnehmer` FOREIGN KEY (`fk_benutzerAnnehmer`) REFERENCES `benutzer` (`id`),
  CONSTRAINT `FK_bestellanforderung_herstellerLieferant` FOREIGN KEY (`fk_herstellerLieferant`) REFERENCES `herstellerlieferant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bestellanforderung`
--

/*!40000 ALTER TABLE `bestellanforderung` DISABLE KEYS */;
INSERT INTO `bestellanforderung` (`id`,`erstellungsdatum`,`status`,`fk_herstellerLieferant`,`fk_benutzerAbsender`,`fk_benutzerAnnehmer`) VALUES 
 (0,'2010-01-11 00:00:00','F',4,15,16),
 (1,'0000-00-00 00:00:00','F',5,15,16),
 (2,'2010-01-11 00:00:00','F',6,15,NULL),
 (3,'2010-01-12 00:00:00','A',4,17,16),
 (4,'2010-01-13 00:00:00','O',1,NULL,NULL),
 (5,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (6,'0000-00-00 00:00:00','F',1,NULL,NULL),
 (7,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (8,'2010-01-12 00:00:00','A',1,NULL,NULL),
 (9,'2010-01-13 00:00:00','O',1,NULL,NULL),
 (10,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (11,'0000-00-00 00:00:00','F',1,NULL,NULL),
 (12,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (13,'2010-01-12 00:00:00','A',1,NULL,NULL),
 (14,'2010-01-13 00:00:00','O',1,NULL,NULL),
 (15,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (16,'0000-00-00 00:00:00','F',1,NULL,NULL),
 (17,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (18,'2010-01-12 00:00:00','A',1,NULL,NULL),
 (19,'2010-01-13 00:00:00','O',1,NULL,NULL),
 (20,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (21,'0000-00-00 00:00:00','F',1,NULL,NULL),
 (22,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (23,'2010-01-12 00:00:00','A',1,NULL,NULL),
 (24,'2010-01-13 00:00:00','O',1,NULL,NULL),
 (25,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (26,'0000-00-00 00:00:00','F',1,NULL,NULL),
 (27,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (28,'2010-01-12 00:00:00','A',1,NULL,NULL),
 (29,'2010-01-13 00:00:00','O',1,NULL,NULL),
 (30,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (31,'0000-00-00 00:00:00','F',1,NULL,NULL),
 (32,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (33,'2010-01-12 00:00:00','A',1,NULL,NULL),
 (34,'2010-01-13 00:00:00','O',1,NULL,NULL),
 (35,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (36,'0000-00-00 00:00:00','F',1,NULL,NULL),
 (37,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (38,'2010-01-12 00:00:00','A',1,NULL,NULL),
 (39,'2010-01-13 00:00:00','O',1,NULL,NULL),
 (40,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (41,'0000-00-00 00:00:00','F',1,NULL,NULL),
 (42,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (43,'2010-01-12 00:00:00','A',1,NULL,NULL),
 (44,'2010-01-13 00:00:00','O',1,NULL,NULL),
 (45,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (46,'0000-00-00 00:00:00','F',1,NULL,NULL),
 (47,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (48,'2010-01-12 00:00:00','A',1,NULL,NULL),
 (49,'2010-01-13 00:00:00','O',1,NULL,NULL),
 (50,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (51,'0000-00-00 00:00:00','F',1,NULL,NULL),
 (52,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (53,'2010-01-12 00:00:00','A',1,NULL,NULL),
 (54,'2010-01-13 00:00:00','O',1,NULL,NULL),
 (55,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (56,'0000-00-00 00:00:00','F',1,NULL,NULL),
 (57,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (58,'2010-01-12 00:00:00','A',1,NULL,NULL),
 (59,'2010-01-13 00:00:00','O',1,NULL,NULL),
 (60,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (61,'0000-00-00 00:00:00','F',1,NULL,NULL),
 (62,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (63,'2010-01-12 00:00:00','A',1,NULL,NULL),
 (64,'2010-01-13 00:00:00','O',1,NULL,NULL),
 (65,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (66,'0000-00-00 00:00:00','F',1,NULL,NULL),
 (67,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (68,'2010-01-12 00:00:00','A',1,NULL,NULL),
 (69,'2010-01-13 00:00:00','O',1,NULL,NULL),
 (70,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (71,'0000-00-00 00:00:00','F',1,NULL,NULL),
 (72,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (73,'2010-01-12 00:00:00','A',1,NULL,NULL),
 (74,'2010-01-13 00:00:00','O',1,NULL,NULL),
 (75,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (76,'0000-00-00 00:00:00','F',1,NULL,NULL),
 (77,'2010-01-11 00:00:00','F',1,NULL,NULL),
 (78,'2010-01-12 00:00:00','A',1,NULL,NULL),
 (79,'2010-01-13 00:00:00','O',1,NULL,NULL),
 (1013,'2010-03-01 00:00:00','F',3,NULL,NULL);
/*!40000 ALTER TABLE `bestellanforderung` ENABLE KEYS */;


--
-- Definition of table `bestellanfotderungsposition`
--

DROP TABLE IF EXISTS `bestellanfotderungsposition`;
CREATE TABLE `bestellanfotderungsposition` (
  `id` int(10) unsigned NOT NULL,
  `fk_bestellanforderung` int(10) unsigned NOT NULL,
  `fk_artikel` int(10) unsigned NOT NULL,
  `bestellmenge` int(10) unsigned NOT NULL,
  `liefertermin` datetime DEFAULT NULL,
  `status` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `fk_kostenstelle` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `bestellanfotderungsposition_bestellanforderung_artikel` (`fk_bestellanforderung`,`fk_artikel`),
  KEY `FK_bestellanfotderungsposition_artikel` (`fk_artikel`),
  KEY `FK_bestellanfotderungsposition_kostenstelle` (`fk_kostenstelle`),
  CONSTRAINT `FK_bestellanfotderungsposition_artikel` FOREIGN KEY (`fk_artikel`) REFERENCES `artikel` (`id`),
  CONSTRAINT `FK_bestellanfotderungsposition_bestellanforderung` FOREIGN KEY (`fk_bestellanforderung`) REFERENCES `bestellanforderung` (`id`),
  CONSTRAINT `FK_bestellanfotderungsposition_kostenstelle` FOREIGN KEY (`fk_kostenstelle`) REFERENCES `kostenstelle` (`id`) ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bestellanfotderungsposition`
--

/*!40000 ALTER TABLE `bestellanfotderungsposition` DISABLE KEYS */;
INSERT INTO `bestellanfotderungsposition` (`id`,`fk_bestellanforderung`,`fk_artikel`,`bestellmenge`,`liefertermin`,`status`,`fk_kostenstelle`) VALUES 
 (0,0,1,10,'2010-01-31 00:00:00','O',2),
 (1,1,1,10,'2010-01-31 00:00:00','O',2),
 (2,2,1,10,'2010-01-31 00:00:00','O',2),
 (3,3,1,10,'2010-01-31 00:00:00','O',2),
 (4,4,1,10,'2010-01-31 00:00:00','O',2),
 (5,5,1,10,'2010-01-31 00:00:00','O',2),
 (6,6,1,10,'2010-01-31 00:00:00','O',2),
 (7,7,1,10,'2010-01-31 00:00:00','O',2),
 (8,8,1,10,'2010-01-31 00:00:00','O',2),
 (9,9,1,10,'2010-01-31 00:00:00','O',2),
 (10,10,1,10,'2010-01-31 00:00:00','O',2),
 (11,11,1,10,'2010-01-31 00:00:00','O',2),
 (12,12,1,10,'2010-01-31 00:00:00','O',2),
 (13,13,1,10,'2010-01-31 00:00:00','O',2),
 (14,14,1,10,'2010-01-31 00:00:00','O',2),
 (15,15,1,10,'2010-01-31 00:00:00','O',2),
 (16,16,1,10,'2010-01-31 00:00:00','O',2),
 (17,17,1,10,'2010-01-31 00:00:00','O',2),
 (18,18,1,10,'2010-01-31 00:00:00','O',2),
 (19,19,1,10,'2010-01-31 00:00:00','O',2),
 (20,20,1,10,'2010-01-31 00:00:00','O',2),
 (21,21,1,10,'2010-01-31 00:00:00','O',2),
 (22,22,1,10,'2010-01-31 00:00:00','O',2),
 (23,23,1,10,'2010-01-31 00:00:00','O',2),
 (24,24,1,10,'2010-01-31 00:00:00','O',2),
 (25,25,1,10,'2010-01-31 00:00:00','O',2),
 (26,26,1,10,'2010-01-31 00:00:00','O',2),
 (27,27,1,10,'2010-01-31 00:00:00','O',2),
 (28,28,1,10,'2010-01-31 00:00:00','O',2),
 (29,29,1,10,'2010-01-31 00:00:00','O',2),
 (30,30,1,10,'2010-01-31 00:00:00','O',2),
 (31,31,1,10,'2010-01-31 00:00:00','O',2),
 (32,32,1,10,'2010-01-31 00:00:00','O',2),
 (33,33,1,10,'2010-01-31 00:00:00','O',2),
 (34,34,1,10,'2010-01-31 00:00:00','O',2),
 (35,35,1,10,'2010-01-31 00:00:00','O',2),
 (36,36,1,10,'2010-01-31 00:00:00','O',2),
 (37,37,1,10,'2010-01-31 00:00:00','O',2),
 (38,38,1,10,'2010-01-31 00:00:00','O',2),
 (39,39,1,10,'2010-01-31 00:00:00','O',2),
 (40,40,1,10,'2010-01-31 00:00:00','O',2),
 (41,41,1,10,'2010-01-31 00:00:00','O',2),
 (42,42,1,10,'2010-01-31 00:00:00','O',2),
 (43,43,1,10,'2010-01-31 00:00:00','O',2),
 (44,44,1,10,'2010-01-31 00:00:00','O',2),
 (45,45,1,10,'2010-01-31 00:00:00','O',2),
 (46,46,1,10,'2010-01-31 00:00:00','O',2),
 (47,47,1,10,'2010-01-31 00:00:00','O',2),
 (48,48,1,10,'2010-01-31 00:00:00','O',2),
 (49,49,1,10,'2010-01-31 00:00:00','O',2),
 (50,50,1,10,'2010-01-31 00:00:00','O',2),
 (51,51,1,10,'2010-01-31 00:00:00','O',2),
 (52,52,1,10,'2010-01-31 00:00:00','O',2),
 (53,53,1,10,'2010-01-31 00:00:00','O',2),
 (54,54,1,10,'2010-01-31 00:00:00','O',2),
 (55,55,1,10,'2010-01-31 00:00:00','O',2),
 (56,56,1,10,'2010-01-31 00:00:00','O',2),
 (57,57,1,10,'2010-01-31 00:00:00','O',2),
 (58,58,1,10,'2010-01-31 00:00:00','O',2),
 (59,59,1,10,'2010-01-31 00:00:00','O',2),
 (60,60,1,10,'2010-01-31 00:00:00','O',2),
 (61,61,1,10,'2010-01-31 00:00:00','O',2),
 (62,62,1,10,'2010-01-31 00:00:00','O',2),
 (63,63,1,10,'2010-01-31 00:00:00','O',2),
 (64,64,1,10,'2010-01-31 00:00:00','O',2),
 (65,65,1,10,'2010-01-31 00:00:00','O',2),
 (66,66,1,10,'2010-01-31 00:00:00','O',2),
 (67,67,1,10,'2010-01-31 00:00:00','O',2),
 (68,68,1,10,'2010-01-31 00:00:00','O',2),
 (69,69,1,10,'2010-01-31 00:00:00','O',2),
 (70,70,1,10,'2010-01-31 00:00:00','O',2),
 (71,71,1,10,'2010-01-31 00:00:00','O',2),
 (72,72,1,10,'2010-01-31 00:00:00','O',2),
 (73,73,1,10,'2010-01-31 00:00:00','O',2),
 (74,74,1,10,'2010-01-31 00:00:00','O',2),
 (75,75,1,10,'2010-01-31 00:00:00','O',2),
 (76,76,1,10,'2010-01-31 00:00:00','O',2),
 (77,77,1,10,'2010-01-31 00:00:00','O',2),
 (78,78,1,10,'2010-01-31 00:00:00','O',2),
 (79,79,1,10,'2010-01-31 00:00:00','O',2),
 (80,0,2,10,'2010-01-31 00:00:00','O',2),
 (81,1,2,10,'2010-01-31 00:00:00','O',2),
 (82,2,2,10,'2010-01-31 00:00:00','O',2),
 (83,3,2,10,'2010-01-31 00:00:00','O',2),
 (84,4,2,10,'2010-01-31 00:00:00','O',2),
 (85,5,2,10,'2010-01-31 00:00:00','O',2),
 (86,6,2,10,'2010-01-31 00:00:00','O',2),
 (87,7,2,10,'2010-01-31 00:00:00','O',2),
 (88,8,2,10,'2010-01-31 00:00:00','O',2),
 (89,9,2,10,'2010-01-31 00:00:00','O',2),
 (90,10,2,10,'2010-01-31 00:00:00','O',2),
 (91,11,2,10,'2010-01-31 00:00:00','O',2),
 (92,12,2,10,'2010-01-31 00:00:00','O',2),
 (93,13,2,10,'2010-01-31 00:00:00','O',2),
 (94,14,2,10,'2010-01-31 00:00:00','O',2),
 (95,15,2,10,'2010-01-31 00:00:00','O',2),
 (96,16,2,10,'2010-01-31 00:00:00','O',2),
 (97,17,2,10,'2010-01-31 00:00:00','O',2),
 (98,18,2,10,'2010-01-31 00:00:00','O',2),
 (99,19,2,10,'2010-01-31 00:00:00','O',2),
 (100,20,2,10,'2010-01-31 00:00:00','O',2),
 (101,21,2,10,'2010-01-31 00:00:00','O',2),
 (102,22,2,10,'2010-01-31 00:00:00','O',2),
 (103,23,2,10,'2010-01-31 00:00:00','O',2),
 (104,24,2,10,'2010-01-31 00:00:00','O',2),
 (105,25,2,10,'2010-01-31 00:00:00','O',2),
 (106,26,2,10,'2010-01-31 00:00:00','O',2),
 (107,27,2,10,'2010-01-31 00:00:00','O',2),
 (108,28,2,10,'2010-01-31 00:00:00','O',2),
 (109,29,2,10,'2010-01-31 00:00:00','O',2),
 (110,30,2,10,'2010-01-31 00:00:00','O',2),
 (111,31,2,10,'2010-01-31 00:00:00','O',2),
 (112,32,2,10,'2010-01-31 00:00:00','O',2),
 (113,33,2,10,'2010-01-31 00:00:00','O',2),
 (114,34,2,10,'2010-01-31 00:00:00','O',2),
 (115,35,2,10,'2010-01-31 00:00:00','O',2),
 (116,36,2,10,'2010-01-31 00:00:00','O',2),
 (117,37,2,10,'2010-01-31 00:00:00','O',2),
 (118,38,2,10,'2010-01-31 00:00:00','O',2),
 (119,39,2,10,'2010-01-31 00:00:00','O',2),
 (120,40,2,10,'2010-01-31 00:00:00','O',2),
 (121,41,2,10,'2010-01-31 00:00:00','O',2),
 (122,42,2,10,'2010-01-31 00:00:00','O',2),
 (123,43,2,10,'2010-01-31 00:00:00','O',2),
 (124,44,2,10,'2010-01-31 00:00:00','O',2),
 (125,45,2,10,'2010-01-31 00:00:00','O',2),
 (126,46,2,10,'2010-01-31 00:00:00','O',2),
 (127,47,2,10,'2010-01-31 00:00:00','O',2),
 (128,48,2,10,'2010-01-31 00:00:00','O',2),
 (129,49,2,10,'2010-01-31 00:00:00','O',2),
 (130,50,2,10,'2010-01-31 00:00:00','O',2),
 (131,51,2,10,'2010-01-31 00:00:00','O',2),
 (132,52,2,10,'2010-01-31 00:00:00','O',2),
 (133,53,2,10,'2010-01-31 00:00:00','O',2),
 (134,54,2,10,'2010-01-31 00:00:00','O',2),
 (135,55,2,10,'2010-01-31 00:00:00','O',2),
 (136,56,2,10,'2010-01-31 00:00:00','O',2),
 (137,57,2,10,'2010-01-31 00:00:00','O',2),
 (138,58,2,10,'2010-01-31 00:00:00','O',2),
 (139,59,2,10,'2010-01-31 00:00:00','O',2),
 (140,60,2,10,'2010-01-31 00:00:00','O',2),
 (141,61,2,10,'2010-01-31 00:00:00','O',2),
 (142,62,2,10,'2010-01-31 00:00:00','O',2),
 (143,63,2,10,'2010-01-31 00:00:00','O',2),
 (144,64,2,10,'2010-01-31 00:00:00','O',2),
 (145,65,2,10,'2010-01-31 00:00:00','O',2),
 (146,66,2,10,'2010-01-31 00:00:00','O',2),
 (147,67,2,10,'2010-01-31 00:00:00','O',2),
 (148,68,2,10,'2010-01-31 00:00:00','O',2),
 (149,69,2,10,'2010-01-31 00:00:00','O',2),
 (150,70,2,10,'2010-01-31 00:00:00','O',2),
 (151,71,2,10,'2010-01-31 00:00:00','O',2),
 (152,72,2,10,'2010-01-31 00:00:00','O',2),
 (153,73,2,10,'2010-01-31 00:00:00','O',2),
 (154,74,2,10,'2010-01-31 00:00:00','O',2),
 (155,75,2,10,'2010-01-31 00:00:00','O',2),
 (156,76,2,10,'2010-01-31 00:00:00','O',2),
 (157,77,2,10,'2010-01-31 00:00:00','O',2),
 (158,78,2,10,'2010-01-31 00:00:00','O',2),
 (159,79,2,10,'2010-01-31 00:00:00','O',2),
 (160,0,3,10,'2010-01-31 00:00:00','O',2),
 (161,1,3,10,'2010-01-31 00:00:00','O',2),
 (162,2,3,10,'2010-01-31 00:00:00','O',2),
 (163,3,3,10,'2010-01-31 00:00:00','O',2),
 (164,4,3,10,'2010-01-31 00:00:00','O',2),
 (165,5,3,10,'2010-01-31 00:00:00','O',2),
 (166,6,3,10,'2010-01-31 00:00:00','O',2),
 (167,7,3,10,'2010-01-31 00:00:00','O',2),
 (168,8,3,10,'2010-01-31 00:00:00','O',2),
 (169,9,3,10,'2010-01-31 00:00:00','O',2),
 (170,10,3,10,'2010-01-31 00:00:00','O',2),
 (171,11,3,10,'2010-01-31 00:00:00','O',2),
 (172,12,3,10,'2010-01-31 00:00:00','O',2),
 (173,13,3,10,'2010-01-31 00:00:00','O',2),
 (174,14,3,10,'2010-01-31 00:00:00','O',2),
 (175,15,3,10,'2010-01-31 00:00:00','O',2),
 (176,16,3,10,'2010-01-31 00:00:00','O',2),
 (177,17,3,10,'2010-01-31 00:00:00','O',2),
 (178,18,3,10,'2010-01-31 00:00:00','O',2),
 (179,19,3,10,'2010-01-31 00:00:00','O',2),
 (180,20,3,10,'2010-01-31 00:00:00','O',2),
 (181,21,3,10,'2010-01-31 00:00:00','O',2),
 (182,22,3,10,'2010-01-31 00:00:00','O',2),
 (183,23,3,10,'2010-01-31 00:00:00','O',2),
 (184,24,3,10,'2010-01-31 00:00:00','O',2),
 (185,25,3,10,'2010-01-31 00:00:00','O',2),
 (186,26,3,10,'2010-01-31 00:00:00','O',2),
 (187,27,3,10,'2010-01-31 00:00:00','O',2),
 (188,28,3,10,'2010-01-31 00:00:00','O',2),
 (189,29,3,10,'2010-01-31 00:00:00','O',2),
 (190,30,3,10,'2010-01-31 00:00:00','O',2),
 (191,31,3,10,'2010-01-31 00:00:00','O',2),
 (192,32,3,10,'2010-01-31 00:00:00','O',2),
 (193,33,3,10,'2010-01-31 00:00:00','O',2),
 (194,34,3,10,'2010-01-31 00:00:00','O',2),
 (195,35,3,10,'2010-01-31 00:00:00','O',2),
 (196,36,3,10,'2010-01-31 00:00:00','O',2),
 (197,37,3,10,'2010-01-31 00:00:00','O',2),
 (198,38,3,10,'2010-01-31 00:00:00','O',2),
 (199,39,3,10,'2010-01-31 00:00:00','O',2),
 (200,40,3,10,'2010-01-31 00:00:00','O',2),
 (201,41,3,10,'2010-01-31 00:00:00','O',2),
 (202,42,3,10,'2010-01-31 00:00:00','O',2),
 (203,43,3,10,'2010-01-31 00:00:00','O',2),
 (204,44,3,10,'2010-01-31 00:00:00','O',2),
 (205,45,3,10,'2010-01-31 00:00:00','O',2),
 (206,46,3,10,'2010-01-31 00:00:00','O',2),
 (207,47,3,10,'2010-01-31 00:00:00','O',2),
 (208,48,3,10,'2010-01-31 00:00:00','O',2),
 (209,49,3,10,'2010-01-31 00:00:00','O',2),
 (210,50,3,10,'2010-01-31 00:00:00','O',2),
 (211,51,3,10,'2010-01-31 00:00:00','O',2),
 (212,52,3,10,'2010-01-31 00:00:00','O',2),
 (213,53,3,10,'2010-01-31 00:00:00','O',2),
 (214,54,3,10,'2010-01-31 00:00:00','O',2),
 (215,55,3,10,'2010-01-31 00:00:00','O',2),
 (216,56,3,10,'2010-01-31 00:00:00','O',2),
 (217,57,3,10,'2010-01-31 00:00:00','O',2),
 (218,58,3,10,'2010-01-31 00:00:00','O',2),
 (219,59,3,10,'2010-01-31 00:00:00','O',2),
 (220,60,3,10,'2010-01-31 00:00:00','O',2),
 (221,61,3,10,'2010-01-31 00:00:00','O',2),
 (222,62,3,10,'2010-01-31 00:00:00','O',2),
 (223,63,3,10,'2010-01-31 00:00:00','O',2),
 (224,64,3,10,'2010-01-31 00:00:00','O',2),
 (225,65,3,10,'2010-01-31 00:00:00','O',2),
 (226,66,3,10,'2010-01-31 00:00:00','O',2),
 (227,67,3,10,'2010-01-31 00:00:00','O',2),
 (228,68,3,10,'2010-01-31 00:00:00','O',2),
 (229,69,3,10,'2010-01-31 00:00:00','O',2),
 (230,70,3,10,'2010-01-31 00:00:00','O',2),
 (231,71,3,10,'2010-01-31 00:00:00','O',2),
 (232,72,3,10,'2010-01-31 00:00:00','O',2),
 (233,73,3,10,'2010-01-31 00:00:00','O',2),
 (234,74,3,10,'2010-01-31 00:00:00','O',2),
 (235,75,3,10,'2010-01-31 00:00:00','O',2),
 (236,76,3,10,'2010-01-31 00:00:00','O',2),
 (237,77,3,10,'2010-01-31 00:00:00','O',2),
 (238,78,3,10,'2010-01-31 00:00:00','O',2),
 (239,79,3,10,'2010-01-31 00:00:00','O',2),
 (321,0,4,10,'2010-01-31 00:00:00','O',2),
 (322,0,5,10,'2010-01-31 00:00:00','O',2),
 (323,0,6,10,'2010-01-31 00:00:00','O',2);
/*!40000 ALTER TABLE `bestellanfotderungsposition` ENABLE KEYS */;


--
-- Definition of table `ebene`
--

DROP TABLE IF EXISTS `ebene`;
CREATE TABLE `ebene` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nummer` int(10) unsigned NOT NULL,
  `fk_saeule` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ebene_nummer_saeule_eindeutlich` (`nummer`,`fk_saeule`),
  KEY `FK_ebene_saeule` (`fk_saeule`),
  CONSTRAINT `FK_ebene_saeule` FOREIGN KEY (`fk_saeule`) REFERENCES `saeule` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ebene`
--

/*!40000 ALTER TABLE `ebene` DISABLE KEYS */;
/*!40000 ALTER TABLE `ebene` ENABLE KEYS */;


--
-- Definition of table `einlagerung`
--

DROP TABLE IF EXISTS `einlagerung`;
CREATE TABLE `einlagerung` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `erstellungsdatum` datetime NOT NULL,
  `fk_benutzer` int(10) unsigned NOT NULL,
  `status` varchar(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_einlagerung_benutzer` (`fk_benutzer`),
  CONSTRAINT `FK_einlagerung_benutzer` FOREIGN KEY (`fk_benutzer`) REFERENCES `benutzer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `einlagerung`
--

/*!40000 ALTER TABLE `einlagerung` DISABLE KEYS */;
/*!40000 ALTER TABLE `einlagerung` ENABLE KEYS */;


--
-- Definition of table `einlagerungsposition`
--

DROP TABLE IF EXISTS `einlagerungsposition`;
CREATE TABLE `einlagerungsposition` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_einlagerung` int(10) unsigned NOT NULL,
  `fk_artikel` int(10) unsigned NOT NULL,
  `menge` int(10) unsigned NOT NULL,
  `fk_mengeneinheit` int(10) unsigned NOT NULL,
  `fk_halle` int(10) unsigned NOT NULL,
  `fk_zeile` int(10) unsigned NOT NULL,
  `fk_saeule` int(10) unsigned NOT NULL,
  `fk_ebene` int(10) unsigned NOT NULL,
  `fk_position` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_einlagerungsposition_einlagerung` (`fk_einlagerung`),
  KEY `FK_einlagerungsposition_artikel` (`fk_artikel`),
  KEY `FK_einlagerungsposition_mengeneinheit` (`fk_mengeneinheit`),
  KEY `FK_einlagerungsposition_halle` (`fk_halle`),
  KEY `FK_einlagerungsposition_zeile` (`fk_zeile`),
  KEY `FK_einlagerungsposition_saeule` (`fk_saeule`),
  KEY `FK_einlagerungsposition_ebene` (`fk_ebene`),
  KEY `FK_einlagerungsposition_position` (`fk_position`),
  CONSTRAINT `FK_einlagerungsposition_artikel` FOREIGN KEY (`fk_artikel`) REFERENCES `artikel` (`id`),
  CONSTRAINT `FK_einlagerungsposition_ebene` FOREIGN KEY (`fk_ebene`) REFERENCES `ebene` (`id`),
  CONSTRAINT `FK_einlagerungsposition_einlagerung` FOREIGN KEY (`fk_einlagerung`) REFERENCES `einlagerung` (`id`),
  CONSTRAINT `FK_einlagerungsposition_halle` FOREIGN KEY (`fk_halle`) REFERENCES `halle` (`id`),
  CONSTRAINT `FK_einlagerungsposition_mengeneinheit` FOREIGN KEY (`fk_mengeneinheit`) REFERENCES `mengeneinheit` (`id`),
  CONSTRAINT `FK_einlagerungsposition_position` FOREIGN KEY (`fk_position`) REFERENCES `position` (`id`),
  CONSTRAINT `FK_einlagerungsposition_saeule` FOREIGN KEY (`fk_saeule`) REFERENCES `saeule` (`id`),
  CONSTRAINT `FK_einlagerungsposition_zeile` FOREIGN KEY (`fk_zeile`) REFERENCES `zeile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `einlagerungsposition`
--

/*!40000 ALTER TABLE `einlagerungsposition` DISABLE KEYS */;
/*!40000 ALTER TABLE `einlagerungsposition` ENABLE KEYS */;


--
-- Definition of table `entnahme`
--

DROP TABLE IF EXISTS `entnahme`;
CREATE TABLE `entnahme` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `erstellungsdatum` datetime NOT NULL,
  `status` varchar(1) NOT NULL,
  `fk_benutzer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`,`fk_benutzer`) USING BTREE,
  KEY `FK_entnahme_benutzer` (`fk_benutzer`),
  CONSTRAINT `FK_entnahme_benutzer` FOREIGN KEY (`fk_benutzer`) REFERENCES `benutzer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `entnahme`
--

/*!40000 ALTER TABLE `entnahme` DISABLE KEYS */;
/*!40000 ALTER TABLE `entnahme` ENABLE KEYS */;


--
-- Definition of table `entnahmeposition`
--

DROP TABLE IF EXISTS `entnahmeposition`;
CREATE TABLE `entnahmeposition` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_entnahme` int(10) unsigned NOT NULL,
  `fk_artikel` int(10) unsigned NOT NULL,
  `menge` int(10) unsigned NOT NULL,
  `fk_mengeeinheit` int(10) unsigned NOT NULL,
  `fk_halle` int(10) unsigned NOT NULL,
  `fk_zeile` int(10) unsigned NOT NULL,
  `fk_saeule` int(10) unsigned NOT NULL,
  `fk_ebene` int(10) unsigned NOT NULL,
  `fk_position` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_entnahmeposition_entnahme` (`fk_entnahme`),
  KEY `FK_entnahmeposition_artikel` (`fk_artikel`),
  KEY `FK_entnahmeposition_mengeeinheit` (`fk_mengeeinheit`),
  KEY `FK_entnahmeposition_halle` (`fk_halle`),
  KEY `FK_entnahmeposition_zeile` (`fk_zeile`),
  KEY `FK_entnahmeposition_saeule` (`fk_saeule`),
  KEY `FK_entnahmeposition_ebene` (`fk_ebene`),
  KEY `FK_entnahmeposition_position` (`fk_position`),
  CONSTRAINT `FK_entnahmeposition_artikel` FOREIGN KEY (`fk_artikel`) REFERENCES `artikel` (`id`),
  CONSTRAINT `FK_entnahmeposition_ebene` FOREIGN KEY (`fk_ebene`) REFERENCES `ebene` (`id`),
  CONSTRAINT `FK_entnahmeposition_entnahme` FOREIGN KEY (`fk_entnahme`) REFERENCES `entnahme` (`id`),
  CONSTRAINT `FK_entnahmeposition_halle` FOREIGN KEY (`fk_halle`) REFERENCES `halle` (`id`),
  CONSTRAINT `FK_entnahmeposition_mengeeinheit` FOREIGN KEY (`fk_mengeeinheit`) REFERENCES `mengeneinheit` (`id`),
  CONSTRAINT `FK_entnahmeposition_position` FOREIGN KEY (`fk_position`) REFERENCES `position` (`id`),
  CONSTRAINT `FK_entnahmeposition_saeule` FOREIGN KEY (`fk_saeule`) REFERENCES `saeule` (`id`),
  CONSTRAINT `FK_entnahmeposition_zeile` FOREIGN KEY (`fk_zeile`) REFERENCES `zeile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `entnahmeposition`
--

/*!40000 ALTER TABLE `entnahmeposition` DISABLE KEYS */;
/*!40000 ALTER TABLE `entnahmeposition` ENABLE KEYS */;


--
-- Definition of table `etage`
--

DROP TABLE IF EXISTS `etage`;
CREATE TABLE `etage` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  `fk_halle` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Ietage_name_eindeutig` (`name`),
  KEY `FK_etage_halle` (`fk_halle`),
  CONSTRAINT `FK_etage_halle` FOREIGN KEY (`fk_halle`) REFERENCES `halle` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `etage`
--

/*!40000 ALTER TABLE `etage` DISABLE KEYS */;
/*!40000 ALTER TABLE `etage` ENABLE KEYS */;


--
-- Definition of table `externerlieferantenartikelnummer`
--

DROP TABLE IF EXISTS `externerlieferantenartikelnummer`;
CREATE TABLE `externerlieferantenartikelnummer` (
  `fk_artikel` int(10) unsigned NOT NULL,
  `fk_herstellerLieferant` int(10) unsigned NOT NULL,
  `katalog` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bestellnummer` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`fk_artikel`,`fk_herstellerLieferant`),
  KEY `FK_externerLieferantenArtikelNummer_herstellerLieferant` (`fk_herstellerLieferant`),
  CONSTRAINT `FK_externerLieferantenArtikelNummer_artikel` FOREIGN KEY (`fk_artikel`) REFERENCES `artikel` (`id`),
  CONSTRAINT `FK_externerLieferantenArtikelNummer_herstellerLieferant` FOREIGN KEY (`fk_herstellerLieferant`) REFERENCES `herstellerlieferant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `externerlieferantenartikelnummer`
--

/*!40000 ALTER TABLE `externerlieferantenartikelnummer` DISABLE KEYS */;
/*!40000 ALTER TABLE `externerlieferantenartikelnummer` ENABLE KEYS */;


--
-- Definition of table `halle`
--

DROP TABLE IF EXISTS `halle`;
CREATE TABLE `halle` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `halle_name_eindeutig` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `halle`
--

/*!40000 ALTER TABLE `halle` DISABLE KEYS */;
/*!40000 ALTER TABLE `halle` ENABLE KEYS */;


--
-- Definition of table `herstellerlieferant`
--

DROP TABLE IF EXISTS `herstellerlieferant`;
CREATE TABLE `herstellerlieferant` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `adresse_land` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adresse_plz` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adresse_stadt` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adresse_strasse` varchar(35) COLLATE utf8_unicode_ci DEFAULT NULL,
  `telefon` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fax` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ansprechpartner` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `herstellerlieferant_name_eindeutlich` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `herstellerlieferant`
--

/*!40000 ALTER TABLE `herstellerlieferant` DISABLE KEYS */;
INSERT INTO `herstellerlieferant` (`id`,`name`,`adresse_land`,`adresse_plz`,`adresse_stadt`,`adresse_strasse`,`telefon`,`fax`,`email`,`ansprechpartner`) VALUES 
 (1,'HerstellerName',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (2,'Hersteller 1','DE','87435','Kempten','Eichendorffweg ','0831 / 110','0831 / 110 ','email@email.de','Ansprechpartner von Hersteller 1'),
 (3,'Hersteller 3','DE','87437','Kempten(Allgäu)','Eichendorffweg ','0831 / 1102','0831 / 1103 ','email@email.de23','Ansprechpartner von Hersteller 2'),
 (4,'Hersteller 4','DE','87437','Kempten(Allgäu)','Eichendorffweg 16','0831 / 1102','0831 / 1103 ','email@email.de23','Ansprechpartner von Hersteller 4'),
 (5,'Lieferant 5','DE','87439','Kempten ( Allgäu )','Eichendorffweg 5','0831 / 110 5','0831 / 1103 55','email@emai5l.de','Ansprechpartner von Lieferant 5'),
 (6,'Lieferant 6','DE','87436','Kempten ( Allgäu6 )','Eichendorffweg 6','0831 / 110 6','0831 / 1103 6','email@emai6l.de','Ansprechpartner von Lieferant 6');
/*!40000 ALTER TABLE `herstellerlieferant` ENABLE KEYS */;


--
-- Definition of table `kostenstelle`
--

DROP TABLE IF EXISTS `kostenstelle`;
CREATE TABLE `kostenstelle` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(10) CHARACTER SET latin1 NOT NULL,
  `nummer` varchar(10) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kostenstelle`
--

/*!40000 ALTER TABLE `kostenstelle` DISABLE KEYS */;
INSERT INTO `kostenstelle` (`id`,`name`,`nummer`) VALUES 
 (1,'Konstenste','Kostenstel'),
 (2,'Schlossere','152 / 112'),
 (3,'Formautoma','148 / 15');
/*!40000 ALTER TABLE `kostenstelle` ENABLE KEYS */;


--
-- Definition of table `mengeneinheit`
--

DROP TABLE IF EXISTS `mengeneinheit`;
CREATE TABLE `mengeneinheit` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mengeneinheit_name_eindeutig` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `mengeneinheit`
--

/*!40000 ALTER TABLE `mengeneinheit` DISABLE KEYS */;
INSERT INTO `mengeneinheit` (`id`,`name`) VALUES 
 (3,'Eimer'),
 (2,'Karton'),
 (1,'Stück');
/*!40000 ALTER TABLE `mengeneinheit` ENABLE KEYS */;


--
-- Definition of table `position`
--

DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nummer` int(10) unsigned NOT NULL,
  `fk_ebene` int(10) unsigned NOT NULL,
  `fk_artikel` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `position_nummer_ebene_eindeutig` (`nummer`,`fk_ebene`),
  KEY `FK_position_ebene` (`fk_ebene`),
  KEY `FK_position_artikel` (`fk_artikel`),
  CONSTRAINT `FK_position_artikel` FOREIGN KEY (`fk_artikel`) REFERENCES `artikel` (`id`),
  CONSTRAINT `FK_position_ebene` FOREIGN KEY (`fk_ebene`) REFERENCES `ebene` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `position`
--

/*!40000 ALTER TABLE `position` DISABLE KEYS */;
/*!40000 ALTER TABLE `position` ENABLE KEYS */;


--
-- Definition of table `saeule`
--

DROP TABLE IF EXISTS `saeule`;
CREATE TABLE `saeule` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nummer` int(10) unsigned NOT NULL,
  `fk_zeile` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `saeule_nummer_zeile_eindeutig` (`nummer`,`fk_zeile`),
  KEY `FK_saeule_zeile` (`fk_zeile`),
  CONSTRAINT `FK_saeule_zeile` FOREIGN KEY (`fk_zeile`) REFERENCES `zeile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `saeule`
--

/*!40000 ALTER TABLE `saeule` DISABLE KEYS */;
/*!40000 ALTER TABLE `saeule` ENABLE KEYS */;


--
-- Definition of table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
CREATE TABLE `sequence` (
  `id` int(11) NOT NULL,
  `tablefield` varchar(45) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`tablefield`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `sequence`
--

/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` (`id`,`tablefield`) VALUES 
 (100,'testtabelle'),
 (1013,'BestellAnforderungID');
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;


--
-- Definition of table `zeile`
--

DROP TABLE IF EXISTS `zeile`;
CREATE TABLE `zeile` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nummer` int(10) unsigned NOT NULL,
  `fk_halle` int(10) unsigned NOT NULL,
  `fk_etage` int(10) unsigned DEFAULT NULL,
  `fk_abteilung` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_zeile_halle` (`fk_halle`),
  KEY `FK_zeile_etage` (`fk_etage`),
  KEY `FK_zeile_abteilung` (`fk_abteilung`),
  CONSTRAINT `FK_zeile_abteilung` FOREIGN KEY (`fk_abteilung`) REFERENCES `abteilung` (`id`),
  CONSTRAINT `FK_zeile_etage` FOREIGN KEY (`fk_etage`) REFERENCES `etage` (`id`),
  CONSTRAINT `FK_zeile_halle` FOREIGN KEY (`fk_halle`) REFERENCES `halle` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `zeile`
--

/*!40000 ALTER TABLE `zeile` DISABLE KEYS */;
/*!40000 ALTER TABLE `zeile` ENABLE KEYS */;


--
-- Definition of table `zugriftsrechtt`
--

DROP TABLE IF EXISTS `zugriftsrechtt`;
CREATE TABLE `zugriftsrechtt` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `zugriftsrecht_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `zugriftsrechtt`
--

/*!40000 ALTER TABLE `zugriftsrechtt` DISABLE KEYS */;
/*!40000 ALTER TABLE `zugriftsrechtt` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
