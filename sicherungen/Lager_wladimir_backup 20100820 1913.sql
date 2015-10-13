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
 (1,'Schlosserei'),
 (2,'Schreinerei');
/*!40000 ALTER TABLE `abteilung` ENABLE KEYS */;


--
-- Definition of table `abteilungbenutzer`
--

DROP TABLE IF EXISTS `abteilungbenutzer`;
CREATE TABLE `abteilungbenutzer` (
  `fk_abteilung` int(10) unsigned NOT NULL,
  `fk_benutzer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_abteilung`,`fk_benutzer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `abteilungbenutzer`
--

/*!40000 ALTER TABLE `abteilungbenutzer` DISABLE KEYS */;
INSERT INTO `abteilungbenutzer` (`fk_abteilung`,`fk_benutzer`) VALUES 
 (1,14),
 (1,17),
 (1,18),
 (1,19),
 (1,57),
 (1,65),
 (2,14),
 (2,19),
 (2,55),
 (2,57),
 (2,66),
 (3,0),
 (3,14),
 (3,19),
 (3,20),
 (3,55),
 (3,57),
 (3,65),
 (3,66),
 (3,67);
/*!40000 ALTER TABLE `abteilungbenutzer` ENABLE KEYS */;


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
  `fk_kostenstelle` int(10) unsigned DEFAULT NULL,
  `fk_mengeneinheit` int(10) unsigned NOT NULL,
  `fk_hersteller` int(10) unsigned NOT NULL,
  `empfohlene_bestellmenge` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `artikel_keg_nr_eindeutig` (`keg_nr`),
  UNIQUE KEY `artikel_typ_eindeutig` (`typ`),
  KEY `FK_artikel_mengeneinheit` (`fk_mengeneinheit`),
  KEY `FK_artikel_hersteller` (`fk_hersteller`),
  KEY `FK_artikel_kostenstelle` (`fk_kostenstelle`),
  CONSTRAINT `FK_artikel_hersteller` FOREIGN KEY (`fk_hersteller`) REFERENCES `herstellerlieferant` (`id`),
  CONSTRAINT `FK_artikel_kostenstelle` FOREIGN KEY (`fk_kostenstelle`) REFERENCES `kostenstelle` (`id`),
  CONSTRAINT `FK_artikel_mengeneinheit` FOREIGN KEY (`fk_mengeneinheit`) REFERENCES `mengeneinheit` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `artikel`
--

/*!40000 ALTER TABLE `artikel` DISABLE KEYS */;
INSERT INTO `artikel` (`id`,`bezeichnung`,`typ`,`keg_nr`,`mindestbestand`,`fk_kostenstelle`,`fk_mengeneinheit`,`fk_hersteller`,`empfohlene_bestellmenge`) VALUES 
 (1,'Ventil','78 Prima B 24 Volt M',1221,1,1,1,3,1),
 (2,'Schutz','ABM 32 23',1222,1,2,2,3,2),
 (3,'Relais','2851 - DC',1223,1,3,3,3,3),
 (4,'Ventil','79 Prima B 12 Volt M',1224,10,1,3,3,4),
 (5,'Schutz','ABC 32 11',1225,11,1,3,2,5),
 (6,'Relais','2951 - AC',1226,12,1,3,2,6),
 (7,'Lager','6001',1227,13,1,3,1,10),
 (8,'Seiltrommel 20t','1234',1228,1,1,1,7,NULL);
/*!40000 ALTER TABLE `artikel` ENABLE KEYS */;


--
-- Definition of table `baugruppe`
--

DROP TABLE IF EXISTS `baugruppe`;
CREATE TABLE `baugruppe` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET latin1 NOT NULL,
  `fk_baugruppe` int(10) unsigned DEFAULT NULL,
  `erfassungsbenutzer` int(10) unsigned DEFAULT NULL,
  `erfassungsdatum` datetime DEFAULT NULL,
  `aenderungsbenutzer` int(10) unsigned DEFAULT NULL,
  `aenderungsdatum` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_baugruppe_baugruppe` (`fk_baugruppe`),
  CONSTRAINT `FK_baugruppe_baugruppe` FOREIGN KEY (`fk_baugruppe`) REFERENCES `baugruppe` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `baugruppe`
--

/*!40000 ALTER TABLE `baugruppe` DISABLE KEYS */;
INSERT INTO `baugruppe` (`id`,`name`,`fk_baugruppe`,`erfassungsbenutzer`,`erfassungsdatum`,`aenderungsbenutzer`,`aenderungsdatum`) VALUES 
 (1,'Baugruppe 1',NULL,NULL,NULL,14,'2010-08-13 00:00:00'),
 (2,'Baugruppe 1.1',1,NULL,NULL,NULL,NULL),
 (3,'Baugruppe 1.1',1,NULL,NULL,14,'2010-08-13 00:00:00'),
 (4,'Baugruppe 1.3',1,NULL,NULL,NULL,NULL),
 (5,'Baugruppe 1.1.1',2,NULL,NULL,NULL,NULL),
 (6,'Baugruppe 1.1.2',2,NULL,NULL,NULL,NULL),
 (7,'Baugruppe 1.1.3',2,NULL,NULL,NULL,NULL),
 (8,'Kran 2',NULL,NULL,NULL,NULL,NULL),
 (9,'Hubwerk',8,NULL,NULL,NULL,NULL),
 (10,'Feinhubmotor',9,NULL,NULL,NULL,NULL),
 (11,'Grobhubmotor',9,NULL,NULL,NULL,NULL),
 (12,'Hubwerkgetriebe',9,NULL,NULL,NULL,NULL),
 (13,'Steuerungskasten',8,NULL,NULL,NULL,NULL),
 (14,'Katzenfahrt',8,NULL,NULL,NULL,NULL),
 (15,'Kranfahrt',8,NULL,NULL,NULL,NULL),
 (16,'Motor 165DD16/2',14,NULL,NULL,NULL,NULL),
 (17,'Getriebe 11HH12-A',14,NULL,NULL,NULL,NULL),
 (18,'Motor 155DD14/2',15,NULL,NULL,NULL,NULL),
 (19,'Getriebe 11HH14-B',15,NULL,NULL,NULL,NULL),
 (20,'Kran 3',NULL,NULL,NULL,NULL,NULL),
 (21,'Hubwerk',20,NULL,NULL,NULL,NULL),
 (22,'Feinhubmotor',21,NULL,NULL,NULL,NULL),
 (23,'Grobhubmotor',21,NULL,NULL,NULL,NULL),
 (24,'Hubwerkgetriebe',21,NULL,NULL,NULL,NULL),
 (25,'Steuerungskasten',20,NULL,NULL,NULL,NULL),
 (26,'Katzenfahrt',20,NULL,NULL,NULL,NULL),
 (27,'Kranfahrt',20,NULL,NULL,NULL,NULL),
 (28,'Motor 165DD16/2',26,NULL,NULL,NULL,NULL),
 (29,'Getriebe 11HH12-A',26,NULL,NULL,NULL,NULL),
 (30,'Motor 155DD14/2',27,NULL,NULL,NULL,NULL),
 (31,'Getriebe 11HH14-B',27,NULL,NULL,NULL,NULL),
 (32,'34432',1,0,'2010-08-13 00:00:00',14,'2010-08-14 00:00:00'),
 (47,'BG 1.1.1.1',5,14,'2010-08-15 00:00:00',NULL,NULL),
 (48,'BG 1.1.1.1.1',47,14,'2010-08-15 00:00:00',NULL,NULL);
/*!40000 ALTER TABLE `baugruppe` ENABLE KEYS */;


--
-- Definition of table `baugruppeartikel`
--

DROP TABLE IF EXISTS `baugruppeartikel`;
CREATE TABLE `baugruppeartikel` (
  `id_artikel` int(10) unsigned NOT NULL,
  `id_baugruppe` int(10) unsigned NOT NULL,
  `eingebauteArtikelMenge` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_artikel`,`id_baugruppe`),
  KEY `FK_baugruppeartikel_id_baugruppe` (`id_baugruppe`),
  CONSTRAINT `FK_baugruppeartikel_id_artikel` FOREIGN KEY (`id_artikel`) REFERENCES `artikel` (`id`),
  CONSTRAINT `FK_baugruppeartikel_id_baugruppe` FOREIGN KEY (`id_baugruppe`) REFERENCES `baugruppe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `baugruppeartikel`
--

/*!40000 ALTER TABLE `baugruppeartikel` DISABLE KEYS */;
INSERT INTO `baugruppeartikel` (`id_artikel`,`id_baugruppe`,`eingebauteArtikelMenge`) VALUES 
 (1,1,6),
 (1,2,1),
 (1,3,7),
 (2,1,7),
 (2,2,2),
 (2,32,2),
 (3,5,9),
 (4,5,5),
 (6,2,2),
 (6,48,1),
 (7,1,1),
 (8,12,1),
 (8,24,1);
/*!40000 ALTER TABLE `baugruppeartikel` ENABLE KEYS */;


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
  `email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `copy_ba_email_empfaenger` int(11) NOT NULL,
  `erfassungsbenutzer` int(10) unsigned DEFAULT NULL,
  `erfassungsdatum` datetime DEFAULT NULL,
  `aenderungsbenutzer` int(10) unsigned DEFAULT NULL,
  `aenderungsdatum` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `benutzer_loginName_eindeutig` (`loginName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `benutzer`
--

/*!40000 ALTER TABLE `benutzer` DISABLE KEYS */;
INSERT INTO `benutzer` (`id`,`name`,`vorname`,`loginName`,`password`,`email`,`copy_ba_email_empfaenger`,`erfassungsbenutzer`,`erfassungsdatum`,`aenderungsbenutzer`,`aenderungsdatum`) VALUES 
 (14,'lager','lager','lager','pass','',0,NULL,NULL,NULL,NULL),
 (17,'Wachter','Peter','peter','pete','schlosserei@ke-ag.de',0,NULL,NULL,NULL,NULL),
 (18,'Miftari','Saja','HeiligerMann','hagi','',0,NULL,NULL,NULL,NULL),
 (19,'Thurner','Dieter','Dieter','Diet','thurner@ke-ag.de',0,NULL,NULL,NULL,NULL),
 (20,'Schmidt','Eugen','eugen','1111','elektrowerkstatt@ke-ag.de',0,NULL,NULL,NULL,NULL),
 (57,'admin','admin','admin2','1111','eugen.schmidt@freenet.de',0,NULL,NULL,57,'2010-08-13 00:00:00'),
 (65,'name','vorname','ligin','asfd','',0,NULL,NULL,NULL,NULL),
 (66,'Testbenuzter','Testvorname','Testlogin','1111','testbenutzer',0,NULL,NULL,NULL,NULL),
 (67,'Mitarbeiter Name','Mitarbeiter Vorname','mitarbeiter','1111','',0,NULL,NULL,NULL,NULL);
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
INSERT INTO `benutzerzugrifsrechtecht` (`fk_benutzer`,`fk_zugrifsrecht`) VALUES 
 (14,1),
 (20,1),
 (57,1),
 (14,2),
 (20,2),
 (57,2),
 (14,3),
 (20,3),
 (57,3),
 (67,3);
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
  `email_ba_empfaenger` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
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
INSERT INTO `bestellanforderung` (`id`,`erstellungsdatum`,`status`,`fk_herstellerLieferant`,`fk_benutzerAbsender`,`fk_benutzerAnnehmer`,`email_ba_empfaenger`) VALUES 
 (0,'2010-01-11 00:00:00','L',2,18,19,NULL),
 (1,'0000-00-00 00:00:00','F',5,17,19,NULL),
 (2,'2010-01-11 00:00:00','F',6,18,19,NULL),
 (3,'2010-01-12 00:00:00','A',4,17,19,NULL),
 (4,'2010-01-13 00:00:00','L',1,18,19,NULL),
 (5,'2010-01-11 00:00:00','F',1,18,19,NULL),
 (6,'0000-00-00 00:00:00','F',1,17,19,NULL),
 (7,'2010-01-11 00:00:00','F',1,18,19,NULL),
 (8,'2010-01-12 00:00:00','A',2,18,19,NULL),
 (9,'2010-01-13 00:00:00','L',1,17,19,NULL),
 (10,'2010-01-11 00:00:00','L',1,18,19,NULL),
 (11,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),
 (12,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (13,'2010-01-12 00:00:00','L',1,NULL,NULL,NULL),
 (14,'2010-01-13 00:00:00','L',1,NULL,NULL,NULL),
 (15,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (16,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),
 (17,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (18,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),
 (19,'2010-01-13 00:00:00','L',1,NULL,NULL,NULL),
 (20,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (21,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),
 (22,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (23,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),
 (24,'2010-01-13 00:00:00','L',2,NULL,NULL,NULL),
 (25,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (26,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),
 (27,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (28,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),
 (29,'2010-01-13 00:00:00','O',3,NULL,NULL,NULL),
 (30,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (31,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),
 (32,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (33,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),
 (34,'2010-01-13 00:00:00','O',1,NULL,NULL,NULL),
 (35,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (36,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),
 (37,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (38,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),
 (39,'2010-01-13 00:00:00','L',1,NULL,NULL,NULL),
 (40,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (41,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),
 (42,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (43,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),
 (44,'2010-01-13 00:00:00','O',1,NULL,NULL,NULL),
 (45,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (46,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),
 (47,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (48,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),
 (49,'2010-01-13 00:00:00','O',1,NULL,NULL,NULL),
 (50,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (51,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),
 (52,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (53,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),
 (54,'2010-01-13 00:00:00','L',1,NULL,NULL,NULL),
 (55,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (56,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),
 (57,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (58,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),
 (59,'2010-01-13 00:00:00','L',1,NULL,NULL,NULL),
 (60,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (61,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),
 (62,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (63,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),
 (64,'2010-01-13 00:00:00','O',1,NULL,NULL,NULL),
 (65,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (66,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),
 (67,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (68,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),
 (69,'2010-01-13 00:00:00','O',1,NULL,NULL,NULL),
 (70,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (71,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),
 (72,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (73,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),
 (74,'2010-01-13 00:00:00','O',6,NULL,NULL,NULL),
 (75,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (76,'0000-00-00 00:00:00','F',1,NULL,NULL,NULL),
 (77,'2010-01-11 00:00:00','F',1,NULL,NULL,NULL),
 (78,'2010-01-12 00:00:00','A',1,NULL,NULL,NULL),
 (79,'2010-01-13 00:00:00','O',6,NULL,NULL,NULL),
 (1013,'2010-03-01 00:00:00','F',3,NULL,NULL,NULL),
 (1015,'2010-04-09 00:00:00','F',NULL,NULL,NULL,NULL),
 (1016,'2010-04-09 00:00:00','F',2,NULL,NULL,NULL),
 (1019,'2010-04-09 00:00:00','F',NULL,NULL,NULL,NULL),
 (1020,'2010-04-09 00:00:00','O',1,NULL,NULL,NULL),
 (1021,'2010-04-09 00:00:00','L',NULL,NULL,NULL,NULL),
 (1022,'2010-04-09 00:00:00','O',NULL,NULL,NULL,NULL),
 (1023,'2010-04-09 00:00:00','O',1,NULL,NULL,NULL),
 (1024,'2010-04-09 00:00:00','O',NULL,NULL,NULL,NULL),
 (1026,'2010-04-09 00:00:00','O',NULL,NULL,NULL,NULL),
 (1030,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),
 (1032,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),
 (1033,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),
 (1034,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),
 (1035,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),
 (1036,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),
 (1037,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),
 (1038,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),
 (1039,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),
 (1040,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),
 (1041,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),
 (1042,'2010-05-13 00:00:00','O',5,NULL,NULL,NULL),
 (1046,'2010-05-14 00:00:00','O',5,NULL,NULL,NULL),
 (1048,'2010-05-14 00:00:00','O',5,NULL,NULL,NULL),
 (1049,'2010-05-14 00:00:00','O',5,NULL,NULL,NULL),
 (1051,'2010-05-14 00:00:00','O',5,NULL,NULL,NULL),
 (1052,'2010-05-14 00:00:00','O',5,NULL,NULL,NULL),
 (1053,'2010-05-14 00:00:00','O',5,NULL,NULL,NULL),
 (1054,'2010-05-14 00:00:00','L',5,NULL,NULL,NULL),
 (1055,'2010-05-14 00:00:00','L',NULL,NULL,NULL,NULL),
 (1056,'2010-05-14 00:00:00','O',5,NULL,NULL,NULL),
 (1057,'2010-05-14 00:00:00','L',2,NULL,NULL,NULL),
 (1058,'2010-05-14 00:00:00','O',NULL,NULL,NULL,NULL),
 (1059,'2010-05-21 00:00:00','O',NULL,NULL,NULL,'email@email.de'),
 (1060,'2010-05-21 00:00:00','L',NULL,NULL,NULL,NULL),
 (1061,'2010-05-21 00:00:00','L',NULL,NULL,NULL,NULL),
 (1078,'2010-05-21 00:00:00','L',2,NULL,NULL,NULL),
 (1079,'2010-05-22 00:00:00','L',5,NULL,NULL,'thurner@ke-ag.de'),
 (1085,'2010-06-08 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1086,'2010-06-08 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1088,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1089,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1090,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1091,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1092,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1093,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1094,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1095,'2010-06-11 00:00:00','L',5,NULL,NULL,'thurner@ke-ag.de'),
 (1096,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1097,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1098,'2010-06-11 00:00:00','O',5,NULL,NULL,'thurner@ke-ag.de'),
 (1099,'2010-06-11 00:00:00','O',5,NULL,NULL,'thurner@ke-ag.de'),
 (1100,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1102,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1103,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1104,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1105,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1107,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1108,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1109,'2010-06-11 00:00:00','L',5,NULL,NULL,'thurner@ke-ag.de'),
 (1112,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1113,'2010-06-11 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1114,'2010-06-11 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1115,'2010-06-11 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1116,'2010-06-11 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1122,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1123,'2010-06-11 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1124,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1126,'2010-06-12 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1127,'2010-06-12 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1128,'2010-06-12 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1129,'2010-06-12 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1130,'2010-06-12 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1131,'2010-06-12 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1132,'2010-06-12 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1133,'2010-06-12 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1140,'2010-06-12 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1141,'2010-06-12 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1142,'2010-06-25 00:00:00','O',NULL,NULL,NULL,'email@email.de23'),
 (1143,'2010-06-25 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1145,'2010-07-02 00:00:00','O',5,NULL,NULL,'email@emai5l.de'),
 (1146,'2010-07-03 00:00:00','O',6,NULL,NULL,'thurner@ke-ag.de'),
 (1147,'2010-07-04 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1148,'2010-07-04 00:00:00','O',5,NULL,NULL,'thurner@ke-ag.de'),
 (1149,'2010-07-04 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de');
/*!40000 ALTER TABLE `bestellanforderung` ENABLE KEYS */;


--
-- Definition of table `bestellanforderungsposition`
--

DROP TABLE IF EXISTS `bestellanforderungsposition`;
CREATE TABLE `bestellanforderungsposition` (
  `id` int(10) unsigned NOT NULL,
  `fk_bestellanforderung` int(10) unsigned NOT NULL,
  `fk_artikel` int(10) unsigned NOT NULL,
  `bestellmenge` int(10) unsigned NOT NULL,
  `liefertermin` int(2) unsigned DEFAULT NULL,
  `status` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `fk_kostenstelle` int(10) unsigned DEFAULT NULL,
  `fk_mengeneinheit` int(10) unsigned NOT NULL,
  `fk_katalog` int(10) unsigned DEFAULT NULL,
  `lieferantenbestellnummer` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `katalogseite` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `katalogpreis` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `bestellanforderungsposition_bestellanforderung_artikel` (`fk_bestellanforderung`,`fk_artikel`) USING BTREE,
  KEY `FK_bestellanforderungsposition_artikel` (`fk_artikel`) USING BTREE,
  KEY `FK_bestellanforderungsposition_kostenstelle` (`fk_kostenstelle`) USING BTREE,
  KEY `FK_bestellanforderungsposition_mengeneinheit` (`fk_mengeneinheit`) USING BTREE,
  CONSTRAINT `FK_bestellanfotderungsposition_artikel` FOREIGN KEY (`fk_artikel`) REFERENCES `artikel` (`id`),
  CONSTRAINT `FK_bestellanfotderungsposition_bestellanforderung` FOREIGN KEY (`fk_bestellanforderung`) REFERENCES `bestellanforderung` (`id`),
  CONSTRAINT `FK_bestellanfotderungsposition_kostenstelle` FOREIGN KEY (`fk_kostenstelle`) REFERENCES `kostenstelle` (`id`) ON UPDATE NO ACTION,
  CONSTRAINT `FK_bestellanfotderungsposition_mengeneinheit` FOREIGN KEY (`fk_mengeneinheit`) REFERENCES `mengeneinheit` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bestellanforderungsposition`
--

/*!40000 ALTER TABLE `bestellanforderungsposition` DISABLE KEYS */;
INSERT INTO `bestellanforderungsposition` (`id`,`fk_bestellanforderung`,`fk_artikel`,`bestellmenge`,`liefertermin`,`status`,`fk_kostenstelle`,`fk_mengeneinheit`,`fk_katalog`,`lieferantenbestellnummer`,`katalogseite`,`katalogpreis`) VALUES 
 (1,1,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (2,2,1,10,2010,'O',5,1,0,NULL,NULL,0),
 (3,3,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (4,4,1,10,2010,'O',NULL,1,NULL,NULL,NULL,NULL),
 (5,5,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (6,6,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (7,7,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (8,8,1,33,33,'O',2,1,0,NULL,NULL,0),
 (9,9,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (11,11,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (12,12,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (13,13,1,10,44,'O',2,1,0,NULL,NULL,0),
 (14,14,1,10,7,'O',2,1,0,NULL,NULL,0),
 (15,15,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (16,16,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (17,17,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (18,18,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (19,19,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (20,20,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (21,21,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (22,22,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (23,23,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (24,24,4,10,1,'O',NULL,3,0,'','',0),
 (25,25,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (26,26,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (27,27,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (28,28,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (29,29,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (30,30,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (31,31,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (32,32,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (33,33,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (34,34,1,8,33,'O',2,3,0,NULL,NULL,0),
 (35,35,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (36,36,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (37,37,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (38,38,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (39,39,1,10,44,'O',2,1,0,NULL,NULL,0),
 (40,40,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (41,41,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (42,42,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (43,43,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (44,44,1,10,3,'O',2,1,0,NULL,NULL,0),
 (45,45,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (46,46,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (47,47,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (48,48,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (49,49,1,10,33,'O',2,1,0,NULL,NULL,0),
 (50,50,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (51,51,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (52,52,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (53,53,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (54,54,1,10,2010,'O',2,1,0,NULL,NULL,0),
 (55,55,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (56,56,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (57,57,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (58,58,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (59,59,1,10,7,'O',2,3,0,NULL,NULL,0),
 (60,60,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (61,61,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (62,62,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (63,63,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (65,65,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (66,66,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (67,67,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (68,68,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (69,69,1,10,20,'O',2,1,0,'','',0),
 (70,70,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (71,71,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (72,72,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (73,73,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (74,74,1,10,11,'O',2,1,0,'','',0),
 (75,75,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (76,76,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (77,77,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (78,78,1,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (80,0,2,10,2010,'O',2,1,0,NULL,NULL,0),
 (81,1,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (82,2,2,10,2010,'O',3,1,0,NULL,NULL,0),
 (83,3,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (84,4,2,10,2010,'O',1,1,NULL,NULL,NULL,NULL),
 (85,5,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (86,6,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (87,7,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (88,8,2,10,33,'O',2,1,0,NULL,NULL,0),
 (89,9,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (90,10,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (91,11,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (92,12,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (93,13,2,10,33,'O',2,1,0,NULL,NULL,0),
 (94,14,2,10,5,'O',2,1,0,NULL,NULL,0),
 (95,15,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (96,16,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (97,17,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (98,18,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (99,19,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (100,20,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (101,21,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (102,22,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (103,23,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (104,24,3,10,1,'O',NULL,3,0,'','',0),
 (105,25,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (106,26,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (107,27,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (108,28,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (109,29,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (110,30,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (111,31,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (112,32,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (113,33,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (114,34,7,10,3,'O',NULL,3,0,'','',0),
 (115,35,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (116,36,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (117,37,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (118,38,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (119,39,2,10,44,'O',2,1,0,NULL,NULL,0),
 (120,40,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (121,41,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (122,42,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (123,43,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (124,44,2,10,44,'O',2,1,0,NULL,NULL,0),
 (125,45,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (126,46,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (127,47,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (128,48,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (129,49,2,6,33,'O',2,1,0,NULL,NULL,0),
 (130,50,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (131,51,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (132,52,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (133,53,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (134,54,2,10,55,'O',2,1,0,NULL,NULL,0),
 (135,55,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (136,56,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (137,57,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (138,58,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (139,59,2,10,6,'O',2,1,0,NULL,NULL,0),
 (140,60,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (141,61,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (142,62,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (143,63,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (144,64,2,6,6,'O',2,1,0,NULL,NULL,0),
 (145,65,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (146,66,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (147,67,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (148,68,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (149,69,2,10,43,'O',2,1,0,NULL,NULL,0),
 (150,70,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (151,71,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (152,72,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (153,73,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (154,74,2,10,11,'O',2,1,0,'','',0),
 (155,75,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (156,76,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (157,77,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (158,78,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (159,79,2,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (160,0,3,10,44,'O',2,1,2,'144433','3/543',0),
 (161,1,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (162,2,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (163,3,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (164,4,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (165,5,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (166,6,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (167,7,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (168,8,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (169,9,3,10,2010,'O',3,1,NULL,NULL,NULL,NULL),
 (170,10,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (171,11,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (172,12,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (173,13,3,10,11,'O',2,1,0,NULL,NULL,0),
 (174,14,3,100,6,'O',2,2,0,NULL,NULL,0),
 (175,15,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (176,16,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (177,17,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (178,18,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (179,19,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (180,20,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (181,21,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (182,22,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (183,23,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (184,24,7,10,22,'O',5,3,0,'','',0),
 (185,25,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (186,26,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (187,27,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (188,28,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (189,29,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (190,30,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (191,31,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (192,32,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (193,33,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (194,34,3,10,8,'O',2,1,0,NULL,NULL,0),
 (195,35,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (196,36,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (197,37,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (198,38,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (199,39,3,10,44,'O',2,1,0,NULL,NULL,0),
 (200,40,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (201,41,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (202,42,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (203,43,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (204,44,3,10,4,'O',2,1,0,NULL,NULL,0),
 (205,45,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (206,46,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (207,47,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (208,48,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (209,49,3,6,44,'O',2,1,0,NULL,NULL,0),
 (210,50,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (211,51,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (212,52,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (213,53,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (214,54,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (215,55,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (216,56,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (217,57,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (218,58,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (219,59,3,10,7,'O',2,1,0,NULL,NULL,0),
 (220,60,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (221,61,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (222,62,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (223,63,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (224,64,3,10,20,'O',2,1,0,'','',0),
 (225,65,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (226,66,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (227,67,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (228,68,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (229,69,3,10,20,'O',2,1,0,'','',0),
 (230,70,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (231,71,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (232,72,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (233,73,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (234,74,3,10,23,'O',2,1,0,'','',0),
 (235,75,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (236,76,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (237,77,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (238,78,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (239,79,3,10,2010,'O',2,1,NULL,NULL,NULL,NULL),
 (321,0,4,10,2010,'O',2,1,0,NULL,NULL,0),
 (322,0,5,10,2010,'O',2,1,0,NULL,NULL,0),
 (323,0,6,10,33,'O',2,1,0,NULL,NULL,0),
 (2009,1015,1,1,0,'O',3,1,NULL,NULL,NULL,NULL),
 (2010,1016,1,1,0,'O',3,1,NULL,NULL,NULL,NULL),
 (2011,1019,1,1,0,'O',3,1,NULL,NULL,NULL,NULL),
 (2012,1020,1,1,0,'O',3,1,NULL,NULL,NULL,NULL),
 (2013,1021,1,1,0,'O',3,1,NULL,NULL,NULL,NULL),
 (2014,1022,1,1,0,'O',3,1,NULL,NULL,NULL,NULL),
 (2015,1023,1,1,0,'O',3,1,NULL,NULL,NULL,NULL),
 (2016,1024,1,1,0,'O',1,1,NULL,NULL,NULL,NULL),
 (2018,1026,1,1,0,'O',3,1,2,'712123','5 - 123',0),
 (2031,39,7,6,16,'O',NULL,3,0,'','',0),
 (2032,1021,6,6,16,'O',NULL,3,0,'','',0),
 (2033,1021,2,2,16,'O',NULL,2,2,'121111','1/543',0),
 (2034,1021,7,3,16,'O',NULL,3,0,'','',0),
 (2038,2,7,10,16,'O',NULL,3,0,'','',0),
 (2039,2,4,4,16,'O',NULL,3,0,'','',0),
 (2041,64,5,5,16,'O',NULL,3,0,'','',0),
 (2043,34,4,4,16,'O',NULL,3,0,'','',0),
 (2053,1030,7,10,19,'O',NULL,3,0,'','',0),
 (2056,1033,7,10,19,'O',NULL,3,0,'','',0),
 (2057,1034,7,10,19,'O',NULL,3,0,'','',0),
 (2058,1035,7,10,19,'O',NULL,3,0,'','',0),
 (2059,1036,7,10,19,'O',NULL,3,0,'','',0),
 (2060,1037,7,10,19,'O',NULL,3,0,'','',0),
 (2063,1039,7,10,19,'O',NULL,3,0,'','',0),
 (2064,1040,7,10,19,'O',NULL,3,0,'','',0),
 (2065,1041,7,10,19,'O',NULL,3,0,'','',0),
 (2069,1041,3,10,22,'O',4,3,0,'','',0),
 (2070,1042,6,10,22,'O',4,3,0,'','',0),
 (2071,34,6,10,22,'O',NULL,3,0,'','',0),
 (2074,1046,3,10,19,'O',NULL,3,0,'','',0),
 (2076,1048,7,10,19,'O',NULL,3,0,'','',0),
 (2091,1051,7,10,19,'O',NULL,3,0,'','',0),
 (2093,1052,7,10,19,'O',NULL,3,0,'','',0),
 (2095,1053,7,10,19,'O',NULL,3,0,'','',0),
 (2097,1054,7,15,19,'O',NULL,3,0,'','',0),
 (2099,1054,6,10,19,'O',NULL,3,0,'','',0),
 (2100,1057,6,10,19,'O',NULL,3,0,'','',0),
 (2117,1098,1,1,53,'O',4,1,0,'','',0),
 (2129,1095,1,1,23,'O',NULL,1,0,'','',0),
 (2130,1095,7,10,23,'O',NULL,3,0,'','',0),
 (2134,1112,1,1,53,'O',NULL,1,0,'','',0),
 (2136,1122,4,2,23,'O',NULL,3,0,'','',0),
 (2137,1122,2,2,23,'O',NULL,2,0,'','',0),
 (2138,1122,3,3,23,'O',NULL,3,0,'','',0),
 (2140,1124,1,1,23,'O',NULL,1,2,'712123','5 - 123',0),
 (2141,1124,4,4,23,'O',NULL,3,0,'','',0),
 (2142,1089,5,1,23,'O',NULL,3,0,'','',0),
 (2143,1126,1,1,23,'O',NULL,1,0,'','',0),
 (2144,1127,1,1,23,'O',NULL,1,0,'','',0),
 (2145,1128,7,10,23,'O',NULL,3,0,'','',0),
 (2146,1129,7,10,23,'O',NULL,3,0,'','',0),
 (2150,1142,1,1,25,'O',NULL,1,0,'','',0),
 (2151,1143,1,1,25,'O',NULL,1,0,'','',0),
 (2152,1143,5,5,25,'O',NULL,3,0,'','',0),
 (2153,1145,7,10,26,'O',NULL,2,0,'','',0),
 (2154,1145,1,1,26,'O',NULL,3,2,'712123','5 - 123',0),
 (2155,1146,1,1,26,'O',NULL,1,0,'','',0),
 (2156,1146,4,4,26,'O',NULL,3,0,'','',0),
 (2157,1148,1,1,26,'O',NULL,1,2,'712123','5 - 123',0),
 (2158,1146,3,10,28,'O',NULL,3,0,'','',0);
/*!40000 ALTER TABLE `bestellanforderungsposition` ENABLE KEYS */;


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
 (1,'Siemens',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (2,'Moeller','DE','87435','Kempten','Eichendorffweg ','0831 / 110','0831 / 110 ','email@email.de','Ansprechpartner von Hersteller 1'),
 (3,'Vesto','DE','87439','Augsburg','Eichendorffweg ','0831 / 1102','0831 / 1103 ','email@email.de23','Ansprechpartner von Hersteller 2'),
 (4,'Schneider','DE','87478','Muenchen','Eichendorffweg 16','0831 / 1102','0831 / 1103 ','email@email.de23','Ansprechpartner von Hersteller 4'),
 (5,'Hagemaier','DE','87439','Kempten (Thingers)','Eichendorffweg 5','0831 / 110 5','0831 / 1103 55','email@emai5l.de','Ansprechpartner von Lieferant 5'),
 (6,'Schaller','DE','87436','Kempten (Ursulasried','Eichendorffweg 6','0831 / 110 6','0831 / 1103 6','email@emai6l.de','Ansprechpartner von Lieferant 6'),
 (7,'Demag','DE',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `herstellerlieferant` ENABLE KEYS */;


--
-- Definition of table `katalog`
--

DROP TABLE IF EXISTS `katalog`;
CREATE TABLE `katalog` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(20) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `katalog`
--

/*!40000 ALTER TABLE `katalog` DISABLE KEYS */;
INSERT INTO `katalog` (`id`,`name`) VALUES 
 (1,'Schaller 2010'),
 (2,'Hagemeier 2009'),
 (3,'Siemens 2010'),
 (4,'Vesto 2008');
/*!40000 ALTER TABLE `katalog` ENABLE KEYS */;


--
-- Definition of table `kostenstelle`
--

DROP TABLE IF EXISTS `kostenstelle`;
CREATE TABLE `kostenstelle` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(20) CHARACTER SET latin1 NOT NULL,
  `nummer` varchar(10) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kostenstelle`
--

/*!40000 ALTER TABLE `kostenstelle` DISABLE KEYS */;
INSERT INTO `kostenstelle` (`id`,`name`,`nummer`) VALUES 
 (1,'Elektrowerkstatt','1711'),
 (2,'Schlosserei','152 / 112'),
 (3,'Formautomat','148 / 15'),
 (4,'Elektroofen','1212'),
 (5,'Schreinerei','1211');
/*!40000 ALTER TABLE `kostenstelle` ENABLE KEYS */;


--
-- Definition of table `lieferantenartikelbestellnummer`
--

DROP TABLE IF EXISTS `lieferantenartikelbestellnummer`;
CREATE TABLE `lieferantenartikelbestellnummer` (
  `fk_artikel` int(10) unsigned NOT NULL,
  `fk_katalog` int(10) unsigned NOT NULL,
  `bestellnummer` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `katalogseite` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `preis` double DEFAULT NULL,
  PRIMARY KEY (`fk_artikel`,`fk_katalog`) USING BTREE,
  KEY `FK_externerlieferantenartikelnummer_katalog` (`fk_katalog`),
  CONSTRAINT `FK_externerLieferantenArtikelNummer_artikel` FOREIGN KEY (`fk_artikel`) REFERENCES `artikel` (`id`),
  CONSTRAINT `FK_externerlieferantenartikelnummer_katalog` FOREIGN KEY (`fk_katalog`) REFERENCES `katalog` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `lieferantenartikelbestellnummer`
--

/*!40000 ALTER TABLE `lieferantenartikelbestellnummer` DISABLE KEYS */;
INSERT INTO `lieferantenartikelbestellnummer` (`fk_artikel`,`fk_katalog`,`bestellnummer`,`katalogseite`,`preis`) VALUES 
 (1,1,'123123  / b12','3 / 152',293),
 (1,2,'712123','5 - 123',200),
 (2,1,'12312311','1/432',22),
 (2,2,'121111','1/543',23),
 (3,1,'122333','2/211',1),
 (3,2,'144433','3/543',1);
/*!40000 ALTER TABLE `lieferantenartikelbestellnummer` ENABLE KEYS */;


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
-- Definition of table `provider`
--

DROP TABLE IF EXISTS `provider`;
CREATE TABLE `provider` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(45) NOT NULL,
  `mail_smtp_host` varchar(45) DEFAULT NULL,
  `mail_smtp_port` int(10) unsigned DEFAULT NULL,
  `mail_smtp_user` varchar(45) DEFAULT NULL,
  `mail_smtp_pwd` varchar(45) DEFAULT NULL,
  `mail_smtp_auth` int(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Verbindungsdaten zu einem E-Mail - Provider';

--
-- Dumping data for table `provider`
--

/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
INSERT INTO `provider` (`id`,`name`,`mail_smtp_host`,`mail_smtp_port`,`mail_smtp_user`,`mail_smtp_pwd`,`mail_smtp_auth`) VALUES 
 (1,'GMX','mail.gmx.net',25,'wladimir.stoll@gmx.de','ksutksut',1),
 (2,'Kabel Deutschland','smtp.superkabel.de',25,'Wladimir.Stoll@kabelmail.de','ksutksut',1);
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;


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
 (1151,'BestellAnforderungID'),
 (2158,'BestellAnforderungPosID'),
 (67,'BenutzerID'),
 (3,'agent007'),
 (48,'BaugruppeID');
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
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `zugriftsrecht_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `zugriftsrechtt`
--

/*!40000 ALTER TABLE `zugriftsrechtt` DISABLE KEYS */;
INSERT INTO `zugriftsrechtt` (`id`,`name`) VALUES 
 (2,'Abteilungsleiter'),
 (1,'Administrator'),
 (3,'Mitarbeiter');
/*!40000 ALTER TABLE `zugriftsrechtt` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
