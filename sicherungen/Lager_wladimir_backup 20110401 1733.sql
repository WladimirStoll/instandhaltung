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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `abteilung`
--

/*!40000 ALTER TABLE `abteilung` DISABLE KEYS */;
INSERT INTO `abteilung` (`id`,`name`) VALUES 
 (4,'Elektroofen'),
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
 (1,75),
 (1,76),
 (1,77),
 (1,78),
 (1,79),
 (2,14),
 (2,19),
 (2,55),
 (2,57),
 (2,66),
 (2,74),
 (2,82),
 (3,0),
 (3,14),
 (3,19),
 (3,20),
 (3,55),
 (3,57),
 (3,65),
 (3,66),
 (3,67),
 (3,69),
 (3,70),
 (3,71),
 (4,80),
 (4,81);
/*!40000 ALTER TABLE `abteilungbenutzer` ENABLE KEYS */;


--
-- Definition of table `artikel`
--

DROP TABLE IF EXISTS `artikel`;
CREATE TABLE `artikel` (
  `id` int(10) unsigned NOT NULL,
  `bezeichnung` varchar(70) COLLATE utf8_unicode_ci NOT NULL,
  `typ` varchar(70) COLLATE utf8_unicode_ci NOT NULL,
  `keg_nr` int(10) unsigned NOT NULL,
  `mindestbestand` int(10) unsigned NOT NULL,
  `fk_kostenstelle` int(10) unsigned DEFAULT NULL,
  `fk_mengeneinheit` int(10) unsigned NOT NULL,
  `fk_hersteller` int(10) unsigned NOT NULL,
  `empfohlene_bestellmenge` int(10) unsigned DEFAULT NULL,
  `bemerkung` varchar(70) COLLATE utf8_unicode_ci DEFAULT NULL,
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
INSERT INTO `artikel` (`id`,`bezeichnung`,`typ`,`keg_nr`,`mindestbestand`,`fk_kostenstelle`,`fk_mengeneinheit`,`fk_hersteller`,`empfohlene_bestellmenge`,`bemerkung`) VALUES 
 (1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,2,1,1,1,5,NULL),
 (2,'Schütz','3RT1035-1A / 18,5kW / Spule~230V',2,2,1,1,1,5,NULL),
 (3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,2,1,1,1,5,NULL),
 (4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,2,1,1,1,5,NULL),
 (5,'Schütz','3RT1045-1A / 37,0kW / Spule~230V',5,2,1,1,1,5,NULL),
 (6,'Schütz','3TF2010-OAPO /4kW / Spule~230V',6,2,1,1,1,5,NULL),
 (7,'Schütz','3RT1017-1AP01 / 5,5kW / Spule~230V',7,2,1,1,1,5,NULL),
 (8,'Seiltrommel 20t','1234',8,1,1,1,7,NULL,NULL),
 (9,'Seiltrommel 12t','1233',9,1,1,1,7,NULL,NULL),
 (10,'Drahtseil','8x36+SES zS/20 mm/42 m',10,1,1,1,7,NULL,NULL),
 (12,'Seilführung','10/20 LI',82480233,1,1,1,7,NULL,NULL),
 (13,'Läufer ','KBH160B12 GTR',8932784,1,1,1,7,NULL,NULL),
 (14,'Rollenstern Kupplung','H3',71842144,1,1,1,7,NULL,NULL),
 (15,'Kupplungshälfte','H 3ND-42',71845744,1,1,1,7,NULL,NULL),
 (16,'Drahtseil','8x36+SES zS/20 mm/30m',16,1,1,1,7,NULL,NULL),
 (17,'Drahtseil','8x36+SES zS/14 mm/30m',17,1,1,1,7,1,NULL),
 (18,'Drahtseil','9x19+SES zS/14 mm/28m',18,1,1,1,7,1,NULL),
 (20,'Drahtseil','6x37+1FE/13 mm/19,5 m',20,1,1,1,7,1,NULL),
 (21,'Drahtseil','9x19+SES zS/14mm/28m',21,1,1,1,7,NULL,NULL),
 (22,'Drahtseil','8x19+SES /11mm/28 m',22,1,1,1,7,NULL,NULL),
 (23,'Drahtseil','6x37+1FE/13 mm/19 m',23,1,1,1,7,1,NULL),
 (24,'Kette','9 mm/6,73 m',24,1,1,1,7,NULL,NULL),
 (25,'Drahtseil','8x19+SES /11mm/29 m',125,1,1,1,7,NULL,NULL),
 (26,'Lastmesseinrichtung','FGB-1',46966444,1,1,1,7,NULL,NULL),
 (27,'Lastmesseinrichtung','FAW-1 / 200-240V',46952544,1,NULL,1,7,NULL,NULL),
 (28,'Leitwertmessgerät','WTC',28,0,NULL,1,8,1,NULL),
 (29,'Leitwertmesskopf','WTC1',29,0,NULL,1,8,1,NULL),
 (30,'Flantschmotor mit Getriebe','G32-20/D4A4-331 (5,5kW;12A;n1=1420U/M;n2=110U/M;Welle d=40mm)',30,1,NULL,1,9,1,NULL),
 (31,'Motor 3~','SK 160 L74 (15kW;28,5A; n=1460U/M)',31,1,NULL,1,10,1,NULL),
 (32,'Flantschgetriebe','SK 872 F-160 L/4 (i=6,34;B5;n1/n2=230)',32,1,NULL,1,11,1,NULL),
 (33,'Simatic S7 CPU','315-2 DP',33,1,NULL,1,1,1,NULL),
 (34,'Simatic S7 CP','343-1 Lean',34,1,NULL,1,1,1,NULL),
 (35,'Simatic S7 Dig.Eingabegruppe','DI32x24V',35,1,NULL,1,1,1,NULL),
 (36,'Simatic S7 Dig.Ausgabegruppe','DO32x24V/0,5A',36,1,NULL,1,1,1,NULL),
 (37,'Simatic S7 Dig.Ausgabegruppe','DO16x24V/0,5A',37,1,NULL,1,1,1,NULL),
 (38,'Simatic S7 Slave','IM 153-1',38,1,NULL,1,1,1,NULL),
 (39,'Simatic S7 Dig.Eingabegruppe','DI16x24V',39,1,NULL,1,1,1,NULL),
 (40,'Simatic S7 Analog.Eingabegruppe','AI8x12Bit',40,1,NULL,1,1,1,NULL),
 (41,'Simatic S7 CPU','313C-2 DP',41,0,NULL,1,1,1,NULL),
 (42,'Simatic S7 Dig.Ein/Ausgabegruppe','DI16/DO16x24V/0,5A',42,0,NULL,1,1,1,NULL),
 (43,'Simatic S7 Analog.Eingabegruppe','AI8x13Bit',43,0,NULL,1,1,1,NULL),
 (44,'Simatic S7 CPU','312',44,0,NULL,1,1,1,NULL),
 (45,'Simatic S7 Analog Ein/Ausgabegruppe','AI4/AO2x8/8 Bit',45,0,NULL,1,1,1,NULL),
 (46,'Simatic Multipanel','MP 270',46,0,NULL,1,1,1,NULL),
 (47,'Simatic Panel','OP 3',47,0,NULL,1,1,1,NULL),
 (48,'Simatic Panel','OP77B',48,0,NULL,1,1,1,NULL),
 (49,'Simatic S7 Analog  Ausgabegruppe','A02x12 Bit',49,0,NULL,1,1,1,NULL),
 (50,'Simatic S7 Netzteil','PS 307 5A',50,0,NULL,1,1,1,NULL),
 (51,'Wendeschützkombination / 5,5kW','3RA1317-8XB30-1APO',51,2,NULL,1,1,5,NULL),
 (52,'Schnellschütz / 5,5kW','3RT1517-1APOO',52,2,NULL,1,1,10,NULL),
 (53,'Schütz / 5,5kW','3RT1017-1AP01',53,2,NULL,1,1,10,NULL),
 (54,'Wechselschalter ','Busch-Duro 2000/6 US',54,2,NULL,1,13,10,NULL),
 (55,'Starkstromkabel','NYY-J 5x2,5 RE RI',55,0,NULL,1,12,1,NULL),
 (56,'G-Sicherungseinsatze','20x5 mm MT 0,5A',56,2,NULL,1,5,20,NULL),
 (57,'Lampenschützrohr','RMMA Glasklar 18',57,5,NULL,1,14,20,NULL),
 (58,'Schaltstück','3RT1935-6A (für 18,5kW Schütz)',58,2,NULL,1,1,5,NULL),
 (59,'Schaltstück','3RT1936-6A (für 22 kW Schütz)',59,2,NULL,1,1,5,NULL),
 (60,'Wandsteckdose','16A 6H 4-polig',60,1,NULL,1,15,5,NULL),
 (61,'NH-Sicherungen Gr.000 /35A','3NA6814 35A',61,3,NULL,1,1,9,NULL),
 (62,'NH-Sicherungen Gr.000 /63A','3NA6822 63A',62,3,NULL,1,1,9,NULL),
 (63,'NH-Sicherungen Gr.000 /16A','3NA3805 16A',63,3,NULL,1,1,9,NULL),
 (64,'Stecker','16A 6H  4-polig',64,1,NULL,1,15,5,NULL),
 (65,'Ständer mit Wicklung','300 Hz / 200 V',65,2,NULL,1,16,5,NULL),
 (66,'Ständer mit Wicklung','200 Hz / 265 V',66,2,NULL,1,16,5,NULL),
 (67,'Ring mit Ansatz','32 602 062 002',67,1,NULL,1,16,3,NULL),
 (68,'Handgriff','31 204 067 001',68,1,NULL,1,16,3,NULL),
 (69,'Motorgehäuse','31 903 138 007',69,2,NULL,1,16,3,NULL),
 (70,'Hülse','30 517 007 001',70,1,NULL,1,16,5,NULL),
 (71,'3~Motor','SSKh 90L-8 /0,55kW /675U/min',71,1,NULL,1,33,1,NULL),
 (72,'3~Motor','AR 90L-8 /0,55kW /705U/min',72,1,NULL,1,34,1,NULL),
 (73,'Zahnradpumpe','WR 3-18-GQS',73,1,NULL,1,32,1,NULL),
 (74,'Niveauschalter','E7B-111-0Q6',74,1,NULL,1,18,1,NULL),
 (75,'Niveauschalter','E2K-C',75,1,NULL,1,18,1,NULL),
 (76,'Dosierventil','3/2-Wege 3/4\"',76,0,NULL,1,35,1,NULL),
 (77,'Sicherheitstemperaturbegrenzer','0-60°C',77,0,NULL,1,36,1,NULL),
 (78,'Thermostat','0-40°C',78,0,NULL,1,36,NULL,NULL),
 (79,'Pneumatikzylinder','DN2-100-100-PPV',79,1,NULL,1,3,1,NULL),
 (80,'5/2-Wege-Magnetventil','JMFH-5-1/4',80,1,NULL,1,3,2,NULL),
 (81,'Schütz','3RT1034-1AP00 /15kW/Spule~230V',81,2,NULL,1,1,5,NULL),
 (82,'Simatic S5 CPU','942 115U',82,0,NULL,1,1,1,NULL),
 (83,'Einfachhaken (Kran)','GR.5,0',83,1,NULL,1,7,1,NULL),
 (84,'Einfachhaken (Kran)','GSN 10',84,1,NULL,1,7,1,NULL),
 (85,'Einfachhaken (Kran)','GR.10,0',85,1,NULL,1,7,1,NULL),
 (86,'Seilführung','für 8T Kran',86,1,NULL,1,7,2,NULL),
 (87,'Positionsschalter Telemecanique','XCK-MR1618668002',87,2,NULL,1,38,5,NULL),
 (88,'Einfachhacken (Kran)','ASN 10',88,1,NULL,1,7,1,NULL),
 (89,'Seilführung metall.','??',89,1,NULL,1,7,1,NULL),
 (90,'Ersatzklinge,60x19x0,5mm (fürUniversalme','KL542ES',90,0,NULL,1,44,10,NULL),
 (91,'Wendeschalter isogekapselt','WT 32',91,0,NULL,1,43,1,NULL),
 (92,'Zeitrelais ansprechverz.,1W,15 Zeitberei','3RP1525-1AP30',92,2,NULL,1,1,5,NULL),
 (93,'Zeitrelais ansprechverz.,2W,15 Zeitberei','3RP1525-1BP30',93,2,NULL,1,1,5,NULL),
 (94,'Schaltstücke für Schütz Bgr.S3,3pol.,AC-','3RT1944-6A',94,2,NULL,1,1,10,NULL),
 (96,'Multifunktions-Zeitrelais,18Funkt.,1W','MFZ12DX-UC',96,1,NULL,1,45,5,NULL),
 (97,'Zeitrelais,1W pot.frei 10A/250VAC','TGI12DX-UC',97,1,NULL,1,45,2,NULL),
 (98,'Schutzhaube fürWinkelschleifer d=180mm','31810070121',98,2,NULL,1,16,5,NULL),
 (99,'Füllstand-Radarsonde,B 7.000mm,Seil 6mm,','Levelflex M FMP40-FBB2GRJB21AA',99,0,NULL,1,40,1,NULL),
 (100,'Prozessanzeiger','RIA261,Feld',100,1,NULL,1,40,1,NULL),
 (101,'Prozessanzeiger','RIA250',101,0,NULL,1,40,1,NULL),
 (102,'Füllstand-Radarsonde,B 7.000mm,Seil 6mm,','Levelflex M FMP40-FBB2GRJB211AA',102,0,NULL,1,40,1,NULL),
 (103,'Kapazitive Sonde (Füllstand Harz/Haerter','Liquicap T FMI21',103,0,NULL,1,40,1,NULL),
 (104,'Grenzwertschalter (zB. für Kapazitive So','RTA421',104,1,NULL,1,40,1,NULL),
 (105,'Messumformer für Leitfähigkeit','Liquisys M CLM223/253',105,1,NULL,1,40,1,NULL),
 (106,'Induktive Sensor mit Temp.-füller für Le','Indumax H CLS52',106,0,NULL,1,40,1,NULL),
 (107,'2-Wege  Kugelhahn ','546',107,0,NULL,1,46,1,'Kunstoff für Säurelei'),
 (108,'Multifunktions-Modul zum Kugelhahn Typ 5','???',108,0,NULL,1,46,1,NULL),
 (111,'2-WegKugelhahn ','Typ 546',111,0,NULL,1,46,1,NULL),
 (112,'Multifunktionsmodul zum Kugelhahn ','zum Kugelhahn 546',112,0,NULL,1,46,1,NULL),
 (113,'Stromabnehmer (Kräne)','PKR 80D/100 A Gewinde M10 mitRohrschelle D=42mm',113,5,NULL,1,48,15,NULL),
 (114,'Kabelabzweigkasten','KF 9060',114,1,NULL,1,55,5,NULL),
 (115,'Durchgangsklemmen (Grau)','UT 2,5-TWIN',115,10,NULL,1,54,50,NULL),
 (116,'Durchgangsklemmen (Blau)','UT 2,5-TWIN BU',116,10,NULL,1,54,50,NULL),
 (117,'Durchgangsklemmen (PE)','UT 2,5-TWIN PE',117,10,NULL,1,54,50,NULL),
 (118,'DIAZED-Schraubkappe','DII 25A AC DC50 Keramik mit Prüfloch',118,5,NULL,1,1,10,NULL),
 (119,'Schaltstück für 3TB44','3TY440-0A',119,3,NULL,1,1,10,NULL),
 (125,'Kabelkanal','HTC 30SK PP WH 40',124,0,3,1,57,5,NULL),
 (127,'Schütz','3TF20',126,5,NULL,1,1,5,NULL),
 (128,'Schutzkontakt -Steckdosenleiste 6-fach','HSDL6',128,0,NULL,1,58,3,NULL),
 (129,'Dosenklemme,3x0,75-1,5qmm','273-100',129,0,NULL,1,59,1,NULL),
 (130,'Dosenklemme,8x0,75-1,5qmm','273-108',130,0,NULL,1,59,100,NULL),
 (131,'Dosenklemme 3x0,5-2,5qmm','273-104',131,0,NULL,1,59,100,NULL),
 (132,'Leuchtstoffröhre 58W/840','HFT58/840',132,0,NULL,1,58,25,NULL),
 (133,'Feuchtraum-Wannenleuchte 1x58W EVG','HFRW158 EVG',133,0,NULL,1,58,5,NULL),
 (135,'Feuchtraum-Wannenleuchte 2x58W EVG','HFRW258 EVG',135,0,NULL,1,58,5,NULL),
 (136,'Abstandschellen,Stahl verzinkt,19-21 mm','733/M20',136,0,NULL,1,60,50,NULL),
 (138,'Abstandschellen,Stahl verzinkt,21-23 mm','733 PG 16',138,0,NULL,1,60,50,NULL),
 (139,'Abstandschellen,Stahl verzinkt,24-29 mm','733/M25 PG 21',139,0,NULL,1,60,50,NULL),
 (141,'Abstandschellen,Stahl verzinkt,30-38 mm','733',141,0,NULL,1,60,50,NULL),
 (143,'111111','11111',1111,1,NULL,3,9,1,'1111111');
/*!40000 ALTER TABLE `artikel` ENABLE KEYS */;


--
-- Definition of table `baugruppe`
--

DROP TABLE IF EXISTS `baugruppe`;
CREATE TABLE `baugruppe` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(45) CHARACTER SET latin1 NOT NULL,
  `fk_baugruppe` int(10) unsigned DEFAULT NULL,
  `erfassungsbenutzer` int(10) unsigned DEFAULT NULL,
  `erfassungsdatum` datetime DEFAULT NULL,
  `aenderungsbenutzer` int(10) unsigned DEFAULT NULL,
  `aenderungsdatum` datetime DEFAULT NULL,
  `fk_halle` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_baugruppe_baugruppe` (`fk_baugruppe`),
  KEY `FK_baugruppe_halle` (`fk_halle`),
  CONSTRAINT `FK_baugruppe_baugruppe` FOREIGN KEY (`fk_baugruppe`) REFERENCES `baugruppe` (`id`),
  CONSTRAINT `FK_baugruppe_halle` FOREIGN KEY (`fk_halle`) REFERENCES `halle` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `baugruppe`
--

/*!40000 ALTER TABLE `baugruppe` DISABLE KEYS */;
INSERT INTO `baugruppe` (`id`,`name`,`fk_baugruppe`,`erfassungsbenutzer`,`erfassungsdatum`,`aenderungsbenutzer`,`aenderungsdatum`,`fk_halle`) VALUES 
 (8,'Kran 02  / 20t',NULL,NULL,NULL,14,'2010-11-11 00:00:00',2),
 (9,'Hubwerk EZDH 1050 H16 KV3 4/1F10',8,NULL,NULL,14,'2010-10-07 00:00:00',NULL),
 (10,'Feinhubwerk KBA 100 B4',9,NULL,NULL,14,'2010-10-07 00:00:00',NULL),
 (11,'Haupthubwerk KBH 200 B6',9,NULL,NULL,14,'2010-10-07 00:00:00',NULL),
 (12,'Rahmen',9,NULL,NULL,14,'2010-08-17 00:00:00',NULL),
 (14,'Katzfahrwerk KBF 80 A8/2',8,NULL,NULL,14,'2010-10-07 00:00:00',NULL),
 (15,'Kranfahrwerk ZBF 100 A8/2',8,NULL,NULL,14,'2010-10-07 00:00:00',NULL),
 (16,'Motor KBF 80 A 8/2 0,13/0,5kW / 630/2790U/min',14,NULL,NULL,14,'2010-10-05 00:00:00',NULL),
 (17,'Getriebe AMK 40 TS / 5,0/20,0m/min',14,NULL,NULL,14,'2010-10-07 00:00:00',NULL),
 (18,'Motor 0,29/1,2kW / 685/2760U/min',15,NULL,NULL,14,'2010-10-07 00:00:00',NULL),
 (19,'Getriebe ADE 50 DD / 10,0/40,0m/min',15,NULL,NULL,14,'2010-10-07 00:00:00',NULL),
 (20,'Kran 03  / 20t',NULL,NULL,NULL,14,'2010-11-11 00:00:00',2),
 (21,'Hubwerk Typ EZDH 1050 H16 KV3 4/1F10',20,NULL,NULL,14,'2010-08-17 00:00:00',NULL),
 (23,'Haupthubwerk KBH 200 B6',21,NULL,NULL,14,'2010-10-07 00:00:00',NULL),
 (24,'Feinhubwerk KBA 112 B4',21,NULL,NULL,14,'2010-10-07 00:00:00',NULL),
 (25,'Steuerungskasten',20,NULL,NULL,NULL,NULL,NULL),
 (27,'Kranfahrt KBF 100 A8/2',20,NULL,NULL,14,'2010-10-05 00:00:00',NULL),
 (30,'Motor 0,26/1,2kW / 610/2620U/min',27,NULL,NULL,14,'2010-10-05 00:00:00',NULL),
 (31,' Getriebe AF10 S / 10/40m/min',27,NULL,NULL,14,'2010-10-07 00:00:00',NULL),
 (34,'Kran 04 / 8t / BJ 1999',NULL,NULL,NULL,14,'2010-12-19 00:00:00',2),
 (55,'Hubwerk EZLDH 520 H16 KV3 4/1 F10',34,14,'2010-08-15 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (56,'Feinhubwerk KBA 80 B4',55,14,'2010-08-15 00:00:00',14,'2010-10-05 00:00:00',NULL),
 (60,'Katzenfahrt KBF 80 A 8/2',34,14,'2010-08-15 00:00:00',14,'2010-10-05 00:00:00',NULL),
 (64,'Motor KBF 80 A 8/2 / 0,13/0,5kW /630/2710U/m',60,14,'2010-08-15 00:00:00',14,'2010-10-05 00:00:00',NULL),
 (65,'läufer',64,14,'2010-08-15 00:00:00',NULL,NULL,NULL),
 (66,'Kran 01 / 12,5t',NULL,NULL,NULL,14,'2011-01-19 00:00:00',2),
 (69,'Kran 07 / 12,5t',NULL,NULL,NULL,14,'2010-11-24 00:00:00',7),
 (70,'Kran 08 / 12,5t',NULL,NULL,NULL,14,'2010-12-19 00:00:00',7),
 (71,'Kran 09 / 20t',NULL,NULL,NULL,14,'2010-12-19 00:00:00',3),
 (72,'Kran 10 / 20t (Schäfer)',NULL,NULL,NULL,14,'2010-12-19 00:00:00',3),
 (73,'Kran 11 / bei Flutbecken',NULL,NULL,NULL,14,'2011-01-19 00:00:00',9),
 (74,'Kran 12 / bei Mischer 6',NULL,NULL,NULL,14,'2011-01-19 00:00:00',9),
 (75,'Kran 13 / bei Stempeluhr',NULL,NULL,NULL,14,'2011-01-19 00:00:00',9),
 (76,'Kran 14 / Großkernmacherei (bei Mischer 5) ',NULL,NULL,NULL,14,'2010-08-17 00:00:00',NULL),
 (77,'Kran 15 / Gießgehänge',NULL,NULL,NULL,14,'2011-01-19 00:00:00',8),
 (78,'Kran 16 /  (bei Auspackrohr)',NULL,NULL,NULL,14,'2011-01-19 00:00:00',8),
 (79,'Kran 17 /  ( mitte)',NULL,NULL,NULL,14,'2011-01-19 00:00:00',8),
 (80,'Kran 18 /  ( bei Mischer 7)',NULL,NULL,NULL,14,'2011-01-19 00:00:00',8),
 (81,'Kran 19 / ( zum Gießgehänge)',NULL,NULL,NULL,14,'2011-01-19 00:00:00',8),
 (82,'Kran 20  / 5t',NULL,NULL,NULL,14,'2011-01-19 00:00:00',11),
 (83,'Kran 21 /  12,5t',NULL,NULL,NULL,14,'2011-01-19 00:00:00',16),
 (84,'Kran 22 /  1.5t',NULL,NULL,NULL,14,'2011-01-19 00:00:00',1),
 (85,'Kran 23 / 6t (Alt Meisterb)',NULL,NULL,NULL,14,'2011-01-19 00:00:00',6),
 (86,'Kran 24 / 12,5t (mitte)',NULL,NULL,NULL,14,'2011-01-19 00:00:00',6),
 (87,'Kran 25 / 8t (Rump_Anlage)',NULL,NULL,NULL,14,'2011-01-19 00:00:00',6),
 (88,'Kran 05 / 5t',NULL,NULL,NULL,14,'2010-12-19 00:00:00',2),
 (89,'Kran 06 / 12,5t',NULL,NULL,NULL,14,'2010-12-19 00:00:00',2),
 (90,'Formautomat',NULL,NULL,NULL,NULL,NULL,NULL),
 (91,'Strahlanlage Rump',NULL,NULL,NULL,14,'2011-01-19 00:00:00',5),
 (92,'Strahlanlage Berger',NULL,NULL,NULL,14,'2011-01-19 00:00:00',17),
 (93,'3T Öfen (1+2)',NULL,NULL,NULL,NULL,NULL,NULL),
 (94,'6T Öfen (3+4)',NULL,NULL,NULL,NULL,NULL,NULL),
 (97,'Motor KBA 100 B4 / 3,5kW /1405U/min ',10,14,'2010-08-17 00:00:00',14,'2010-10-05 00:00:00',NULL),
 (98,'Getriebe FG 10 / 0,8m/min',10,14,'2010-08-17 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (99,'Motor KBH 200 B6 / 28,5kW / 955U/min',11,14,'2010-08-17 00:00:00',14,'2010-10-05 00:00:00',NULL),
 (100,'Getriebe DH 1050 / 8,0m/min / I= 37,0',11,14,'2010-08-17 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (101,'Unterflasche 365/20',9,14,'2010-08-17 00:00:00',14,'2010-08-17 00:00:00',NULL),
 (104,'Kranfahrwerk KBF 100 A8/2',71,14,'2010-08-17 00:00:00',14,'2010-10-06 00:00:00',NULL),
 (105,'Katzfahrwerk KBF 100 A8/2',71,14,'2010-08-17 00:00:00',14,'2010-10-06 00:00:00',NULL),
 (111,'Hubwerk EZDH 1050 H16 KV3 4/1F10',71,14,'2010-08-17 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (112,'Haupthubwerk KBH 200 B6',111,14,'2010-08-17 00:00:00',14,'2010-10-06 00:00:00',NULL),
 (113,'Motor KBH 200 B6 / 28,5kW / 955U/min',112,14,'2010-08-17 00:00:00',14,'2010-10-06 00:00:00',NULL),
 (114,'Getriebe DH1050 / 8m/min',112,14,'2010-08-17 00:00:00',14,'2010-10-06 00:00:00',NULL),
 (115,'Feinhubwerk KBA 100 B4',111,14,'2010-08-17 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (116,'Motor KBA 100 B 4 /3,5kW /1405U/min',115,14,'2010-08-17 00:00:00',14,'2010-10-06 00:00:00',NULL),
 (117,'Getriebe FG 10 / 0,8m/min',115,14,'2010-08-17 00:00:00',14,'2010-10-06 00:00:00',NULL),
 (118,'Motor 0,26/1,2kW / 610/2620U/min',105,14,'2010-08-17 00:00:00',14,'2010-10-06 00:00:00',NULL),
 (119,'Getriebe AMK 10 TS / 5/20m/min',105,14,'2010-08-17 00:00:00',14,'2010-10-06 00:00:00',NULL),
 (120,'Motor 0,26/1,2kW / 610/2620U/min',104,14,'2010-08-17 00:00:00',14,'2010-10-06 00:00:00',NULL),
 (121,'Getriebe ADE 50 DS / 10/40m/min',104,14,'2010-08-17 00:00:00',14,'2010-10-06 00:00:00',NULL),
 (122,'Hubwerk EZLDH 1050 H16 KV2 4/1F10',72,14,'2010-08-17 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (123,'Katzfahrwerk ZBF 90 B9-2+B020',72,14,'2010-08-17 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (124,'Kranfahrwerk ZBF 90 B8-2 B020',72,14,'2010-08-17 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (125,'Hubwerk EKDH 316 H12 F6 2/1',73,14,'2010-08-17 00:00:00',14,'2010-10-06 00:00:00',NULL),
 (126,'Kranfahrwerk',73,14,'2010-08-17 00:00:00',NULL,NULL,NULL),
 (127,'Katzfahrwerk KBF 90 A8/2',73,14,'2010-08-17 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (128,'Bahn 1',90,14,'2010-08-17 00:00:00',NULL,NULL,NULL),
 (129,'Waidmannband',90,14,'2010-08-17 00:00:00',14,'2010-08-17 00:00:00',NULL),
 (130,'Bahn 2',90,14,'2010-08-17 00:00:00',14,'2010-08-17 00:00:00',NULL),
 (131,'Formatic',90,14,'2010-08-17 00:00:00',NULL,NULL,NULL),
 (132,'Drehtisch',131,14,'2010-08-17 00:00:00',NULL,NULL,NULL),
 (133,'Steuerschrank Katze',71,14,'2010-08-17 00:00:00',14,'2010-09-29 00:00:00',NULL),
 (134,'Steuerschrank Kran',71,14,'2010-08-17 00:00:00',14,'2010-09-29 00:00:00',NULL),
 (135,'Kondensatorgerüst 2',93,14,'2010-08-17 00:00:00',14,'2010-08-17 00:00:00',NULL),
 (136,'Kondensatorgerüst 1',93,14,'2010-08-17 00:00:00',NULL,NULL,NULL),
 (137,'Umrichter',93,14,'2010-08-17 00:00:00',NULL,NULL,NULL),
 (138,'Drossel',137,14,'2010-08-17 00:00:00',NULL,NULL,NULL),
 (139,'Hubwerk  EZDH 1063 H16 KV2-2/1',66,14,'2010-08-17 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (140,'Kran 26 / 10t (Schweißplatz)',NULL,NULL,NULL,14,'2011-01-19 00:00:00',5),
 (141,'Kran 27 / 10t (zur Taucherei)',NULL,NULL,NULL,14,'2011-01-19 00:00:00',5),
 (142,'Kran 30 / ',NULL,NULL,NULL,14,'2011-01-19 00:00:00',1),
 (143,'Kran 31 /',NULL,NULL,NULL,14,'2011-01-19 00:00:00',9),
 (144,'Kran 32 /  0,5t',NULL,NULL,NULL,14,'2011-01-19 00:00:00',13),
 (145,'Steuerung Kran',8,14,'2010-08-17 00:00:00',NULL,NULL,NULL),
 (146,'Kran 33 / Labor (QS) / 2t',NULL,NULL,NULL,14,'2011-01-19 00:00:00',14),
 (147,'Kran 34 / 2 t',NULL,NULL,NULL,14,'2011-01-19 00:00:00',14),
 (148,'Kran 35 /  (R) / 2t',NULL,NULL,NULL,14,'2011-01-19 00:00:00',16),
 (149,'Kran 36 /  (L) / 2t',NULL,NULL,NULL,14,'2011-01-19 00:00:00',16),
 (150,'Kran 37 / 2t ( Berger-Strahlanlage )',NULL,NULL,NULL,14,'2011-01-19 00:00:00',17),
 (151,'Schleuderrad',92,14,'2010-08-17 00:00:00',NULL,NULL,NULL),
 (152,'Kran 38 / 2t ( Rump-Strahlanl.-Keller )',NULL,NULL,NULL,14,'2011-01-19 00:00:00',17),
 (153,'Kran 39 /  Automat-Zulegen',NULL,NULL,NULL,14,'2011-01-19 00:00:00',8),
 (154,'Kran 41 /',NULL,NULL,NULL,14,'2011-01-19 00:00:00',4),
 (155,'Kran 42 /',NULL,NULL,NULL,14,'2011-01-19 00:00:00',4),
 (156,'Kran 43 / ',NULL,NULL,NULL,14,'2011-01-19 00:00:00',4),
 (157,'Kran 44 / ',NULL,NULL,NULL,14,'2011-01-19 00:00:00',4),
 (158,'Kran 45/ Möldnerhalle',NULL,NULL,NULL,NULL,NULL,NULL),
 (159,'Kran 46 / Möldnerhalle',NULL,NULL,NULL,NULL,NULL,NULL),
 (160,'Kran 47 / Möldnerhalle',NULL,NULL,NULL,NULL,NULL,NULL),
 (163,'Entstaubung     E.Ofen',NULL,14,'2010-08-22 00:00:00',14,'2010-09-29 00:00:00',NULL),
 (164,'Steuerschrank',163,14,'2010-08-22 00:00:00',14,'2010-09-29 00:00:00',NULL),
 (165,'Spülluftradialgebläse',163,14,'2010-08-22 00:00:00',14,'2010-09-30 00:00:00',NULL),
 (166,'Hauptradialgebläse',163,14,'2010-08-22 00:00:00',14,'2010-09-30 00:00:00',NULL),
 (167,'3~Motor ???',165,14,'2010-08-22 00:00:00',14,'2010-09-30 00:00:00',NULL),
 (170,'Kernschießmaschine AHB 40',NULL,14,'2010-09-28 00:00:00',14,'2011-01-19 00:00:00',4),
 (171,'Kernschießmaschine AHB 18',NULL,14,'2010-09-28 00:00:00',14,'2011-01-19 00:00:00',4),
 (172,'Sandmischer 8 (ECO-25T)',NULL,14,'2010-09-28 00:00:00',14,'2011-01-19 00:00:00',23),
 (173,'Sandmischer 1 (GFA 35T )',NULL,14,'2010-09-28 00:00:00',14,'2011-01-19 00:00:00',2),
 (174,'Sandmischer 4 (ECO 15T )',NULL,14,'2010-09-28 00:00:00',14,'2011-01-19 00:00:00',22),
 (175,'Sandmischer 5 (ECO 15T )',NULL,14,'2010-09-28 00:00:00',14,'2011-01-19 00:00:00',10),
 (176,'Sandmischer 6 (Hensel 15T )',NULL,14,'2010-09-28 00:00:00',14,'2011-01-19 00:00:00',9),
 (177,'Sandmischer 7 ( Wohr 15T )',NULL,14,'2010-09-28 00:00:00',14,'2011-01-19 00:00:00',8),
 (178,'Endmischertrog',172,14,'2010-09-28 00:00:00',14,'2010-09-29 00:00:00',NULL),
 (180,'Vormischertrog Harz',172,14,'2010-09-28 00:00:00',14,'2010-09-29 00:00:00',NULL),
 (181,'Motor mit Getriebe',180,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (182,'Vormischertrog Härter',172,14,'2010-09-28 00:00:00',14,'2010-09-29 00:00:00',NULL),
 (183,'Schieber Sandauslaß',178,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (185,'Schieber Sandeinlaß',172,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (186,'Dosierpumpe Harz',172,14,'2010-09-28 00:00:00',14,'2010-09-30 00:00:00',NULL),
 (187,'Motor mit Getriebe',182,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (188,'Bedienschrank',172,14,'2010-09-28 00:00:00',14,'2010-09-30 00:00:00',NULL),
 (189,'Dosierpumpe Härter',172,14,'2010-09-28 00:00:00',14,'2010-09-30 00:00:00',NULL),
 (190,'Vormischtrog Harz',174,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (191,'Vormischtrog Harz',174,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (192,'Dosierpumpe Harz',174,14,'2010-09-28 00:00:00',14,'2010-09-30 00:00:00',NULL),
 (193,'Dosierpumpe Härter',174,14,'2010-09-28 00:00:00',14,'2010-09-30 00:00:00',NULL),
 (194,'Endmischtrog',174,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (195,'Bedienschrank',174,14,'2010-09-28 00:00:00',14,'2010-09-30 00:00:00',NULL),
 (196,'Schieber Sandeinlaß',174,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (197,'Endmischtrog',175,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (198,'Vormischtrog Harz',175,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (199,'Vormischtrog Haerter',175,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (200,'Dosierpumpe Harz',175,14,'2010-09-28 00:00:00',14,'2010-09-30 00:00:00',NULL),
 (201,'Dosierpumpe Härter',175,14,'2010-09-28 00:00:00',14,'2010-09-30 00:00:00',NULL),
 (202,'Schieber Sandauslaß',197,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (203,'Kondensatorgerüst 3',94,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (204,'Kondensatorgerüst 4',94,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (205,'Umrichter',94,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (206,'Kühlwasseraggregat Ofenkreis 3+4',94,14,'2010-09-28 00:00:00',14,'2010-09-28 00:00:00',NULL),
 (207,'Kühlwasseraggregat Umrichterkreis',94,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (208,'Kühlwasseraggregat Ofenkreis 1+2',93,14,'2010-09-28 00:00:00',14,'2010-09-28 00:00:00',NULL),
 (209,'Kühlwasseraggregat Umrichterkreis',93,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (210,'Starteinrichtung ',136,14,'2010-09-28 00:00:00',14,'2010-09-28 00:00:00',NULL),
 (211,'Starteinrichtung ',135,14,'2010-09-28 00:00:00',14,'2010-09-28 00:00:00',NULL),
 (212,'Pumpe 1',208,14,'2010-09-28 00:00:00',14,'2010-09-28 00:00:00',NULL),
 (213,'Pumpe 2',208,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (214,'Pumpe 1',209,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (215,'Pumpe 2',209,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (216,'Gleichrichter 1(GR 1)',137,14,'2010-09-28 00:00:00',14,'2010-09-28 00:00:00',NULL),
 (217,'Gleichrichter 2 (GR 2)',137,14,'2010-09-28 00:00:00',14,'2010-09-28 00:00:00',NULL),
 (218,'Wechselrichter 1 (WR 1)',137,14,'2010-09-28 00:00:00',14,'2010-09-28 00:00:00',NULL),
 (219,'Wechselrichter 2 (WR 2)',137,14,'2010-09-28 00:00:00',14,'2010-09-28 00:00:00',NULL),
 (220,'DICU',137,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (221,'Kühlturm',209,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (222,'Kühlturm',208,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (223,'Ventilator',222,14,'2010-09-28 00:00:00',14,'2010-09-28 00:00:00',NULL),
 (224,'Sprühwasserpumpe',222,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (225,'Sprühwasserpumpe',221,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (226,'Ventilator',221,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (227,'Steuerschrank',208,14,'2010-09-28 00:00:00',14,'2010-09-29 00:00:00',NULL),
 (228,'Schaltschrank',209,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (230,'Leitwertmessgerät',221,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (231,'Leitwertmesseinrichtung',227,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (232,'Leitwertmesseinrichtung',228,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (233,'SPS SimaticS7',136,14,'2010-09-28 00:00:00',14,'2010-09-28 00:00:00',NULL),
 (234,'SPS Simatic S7',135,14,'2010-09-28 00:00:00',14,'2010-09-28 00:00:00',NULL),
 (235,'SPS Simatic S7',205,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (236,'SPS Simatic S7',204,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (237,'SPS Simatic S7',203,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (238,'SPS Simatic S7',137,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (239,'Drossel',235,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (240,'Gleichrichter 1(GR 1)',235,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (241,'Gleichrichter 2 (GR 2)',235,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (243,'Starteinrichtung',203,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (244,'Starteinrichtung',204,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (246,'Hubwerk  EZDH 1032 H16 KV2 4/1 F10',70,14,'2010-09-28 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (247,'Hubwerk  EZDH 1032 H16 KV2 4/1 F10',69,14,'2010-09-28 00:00:00',14,'2010-10-05 00:00:00',NULL),
 (248,'Hubwerk EZLDH 1063 H16 KV2 2/1 F10',89,14,'2010-09-28 00:00:00',14,'2010-10-05 00:00:00',NULL),
 (249,'Hubwerk EZLDH 625 H12 KV3-2/1 F1',88,14,'2010-09-28 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (250,'Katzfahrwerk ZBF 100 A8/2',70,14,'2010-09-28 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (251,'Katzfahrwerk ZBF 100 A8/2',69,14,'2010-09-28 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (252,'Katzfahrwerk KBF 80 A8/2',89,14,'2010-09-28 00:00:00',14,'2010-10-05 00:00:00',NULL),
 (254,'Katzfahrwerk ZBF 71 A 8/2',88,14,'2010-09-28 00:00:00',14,'2010-10-05 00:00:00',NULL),
 (255,'Katzfahrwerk ZBF 100 A8/2',20,14,'2010-09-28 00:00:00',14,'2010-10-05 00:00:00',NULL),
 (256,'Katzfahrwerk KBF 90 A8/2',66,14,'2010-09-28 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (257,'Kranfahrwerk ZBF 100 A8/2',70,14,'2010-09-28 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (258,'Kranfahrwerk ZBF 100 A8/2',69,14,'2010-09-28 00:00:00',14,'2010-10-05 00:00:00',NULL),
 (259,'Kranfahrwerk KBF 90 A8/2',89,14,'2010-09-28 00:00:00',14,'2010-10-05 00:00:00',NULL),
 (260,'Kranfahrwerk DES 3/6',88,14,'2010-09-28 00:00:00',14,'2010-10-05 00:00:00',NULL),
 (261,'Kranfahrwerk KBF 90 A8/2',34,14,'2010-09-28 00:00:00',14,'2010-10-05 00:00:00',NULL),
 (262,'Kranfahrwerk KBF 90 A8/2',66,14,'2010-09-28 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (263,'Kranfahrwerk',74,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (264,'Kranfahrwerk',75,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (265,'Kranfahrwerk',76,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (266,'Katzfahrwerk',78,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (267,'Katzfahrwerk',79,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (268,'Katzfahrwerk',80,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (269,'Katzfahrwerk',81,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (270,'Katzfahrwerk',82,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (271,'Katzfahrwerk',83,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (272,'Katzfahrwerk',85,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (273,'Katzfahrwerk',86,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (274,'Katzfahrwerk',87,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (275,'Katzfahrwerk',140,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (276,'Katzfahrwerk',141,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (277,'Katzfahrwerk',74,14,'2010-09-28 00:00:00',14,'2010-10-06 00:00:00',NULL),
 (278,'Katzfahrwerk KBF 80 A8/2',75,14,'2010-09-28 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (279,'Katzfahrwerk KMF 80 A8/2',76,14,'2010-09-28 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (280,'Baugruppe Kranfahrwerk',78,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (281,'Baugruppe Kranfahrwerk',79,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (282,'Baugruppe Kranfahrwerk',80,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (283,'Baugruppe Kranfahrwerk',82,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (284,'Baugruppe Kranfahrwerk',83,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (285,'Baugruppe Kranfahrwerk',85,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (286,'Baugruppe Kranfahrwerk',86,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (287,'Baugruppe Kranfahrwerk',87,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (288,'Baugruppe Kranfahrwerk',140,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (289,'Baugruppe Kranfahrwerk',141,14,'2010-09-28 00:00:00',NULL,NULL,NULL),
 (290,'Ofenleitstand',93,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (291,'Bedienschrank',290,14,'2010-09-29 00:00:00',14,'2010-09-29 00:00:00',NULL),
 (294,'Ofenleitstand',94,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (302,'Drossel',205,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (303,'Gleichrichter 3(GR 3)',205,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (304,'Gleichrichter 4 (GR 4)',205,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (305,'Wechselrichter 3 (WR 3)',205,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (306,'Wechselrichter 4 (WR 4)',205,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (307,'Bedienschrank',294,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (308,'SPS Simatic S7',307,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (309,'Kühlwasserüberwachungsstation Ofen 3',94,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (310,'Kühlwasserüberwachungsstation Ofen 4',94,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (311,'SPS Simatic S7',310,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (312,'SPS Simatic S7',309,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (315,'Kühlwasserüberwachungsstation Ofen 2',93,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (316,'Kühlwasserüberwachungsstation Ofen 1',93,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (317,'SPS Simatic S7',315,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (319,'SPS Simatic S7',316,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (320,'Sandregenerierung',NULL,14,'2010-09-29 00:00:00',14,'2011-01-19 00:00:00',24),
 (321,'GUT-Anlage',320,14,'0000-00-00 00:00:00',NULL,NULL,NULL),
 (322,'SPS Simatic S7',321,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (323,'Bedienschrank',176,14,'2010-09-29 00:00:00',14,'2010-09-30 00:00:00',NULL),
 (324,'SPS Simatic S7',323,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (325,'Entstaubung 7 (Rumpanlage)',NULL,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (327,'Bedinschrank',325,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (328,'SPS Simatic S7',327,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (329,'Entstaubung 16',NULL,14,'2010-09-29 00:00:00',14,'2010-11-11 00:00:00',5),
 (330,'Steuerschrank Hauptmotor',329,14,'2010-09-29 00:00:00',14,'2010-09-29 00:00:00',NULL),
 (331,'Bedienschrank unten',329,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (332,'Bedienschrank oben',329,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (334,'SPS Simatic S7',332,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (335,'SPS Simatic S7',164,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (336,'SPS Simatic S7',177,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (337,'Entstaubung 2',NULL,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (338,'Entstaubung 3 (Hante-Filter)',NULL,14,'2010-09-29 00:00:00',14,'2010-11-11 00:00:00',8),
 (340,'Entstaubung 4',NULL,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (341,'Entstaubung 14 (Knollenbrecher)',NULL,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (342,'Steuerschrank Katze',70,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (343,'Steuerschrank Katze',69,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (344,'Steuerschrank Katze',89,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (345,'Steuerschrank Katze',88,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (346,'Steuerschrank Katze',34,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (347,'Steuerschrank Katze',8,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (348,'Dachventilator 1',NULL,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (349,'Regenerierung 1',320,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (350,'Regenerierung 2',320,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (351,'Rinne 3',349,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (352,'Rinne 2',349,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (353,'Rinne 1',349,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (354,'Magnetband',349,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (355,'Rinne 4',349,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (356,'Rüttler',349,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (357,'Permanentmagnet',349,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (358,'Rost 2',356,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (359,'Rost 1',356,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (360,'Ventilator',350,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (361,'Rinne 6',350,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (362,'Knollenbrecher',350,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (363,'Kühler',350,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (364,'Sender',350,14,'2010-09-29 00:00:00',NULL,NULL,NULL),
 (365,'Vormischertrog Harz',173,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (366,'Bedienschrank an der Säule',173,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (367,'Bedienschrank oben',173,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (368,'Vormischertrog Härter',173,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (369,'Endmischer',173,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (370,'Funksteuerung',367,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (371,'SPS Simatic S5',367,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (372,'Dosierpumpe Härter',173,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (373,'Dosierpumpe Harz',173,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (374,'Transportband Sandzufuhr',173,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (375,'Bedienpult',174,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (376,'Vormischer (Härter)',176,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (377,'Bedienpult',176,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (378,'Endmischer (Harz)',176,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (379,'Dosierpumpe Harz',176,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (380,'Dosierpumpe Härter',176,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (381,'Dosierpumpe Harz',177,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (382,'Dosierpumpe Härter',177,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (383,'Endmischer',177,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (384,'Bedienpult',177,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (385,'Bedienpult',172,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (386,'Hubwerk AP 210 H7F 2/1',74,14,'2010-09-30 00:00:00',14,'2010-10-06 00:00:00',NULL),
 (387,'Hubwerk EZLDH 625 H12 KV3-2/1 F10',75,14,'2010-09-30 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (388,'Hubwerk EKDH 316 H12 KV2-2/1 F6',76,14,'2010-09-30 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (389,'Hubwerk ST 2010-8/2 2/1',77,14,'2010-09-30 00:00:00',14,'2010-10-06 00:00:00',NULL),
 (390,'Hubwerk',78,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (391,'Hubwerk',79,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (392,'Hubwerk',80,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (393,'Hubwerk',87,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (394,'Haupthubwerk KBH 160 B4',139,14,'2010-09-30 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (396,'3~Motor 17.8 kW /n=1440U ',394,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (397,'Feinhubwerk KBA 100 B4',139,14,'2010-09-30 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (398,'3~Motor 3,0kW /n=1420U',397,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (400,'3~Motor 1kW / 2510U/M /',256,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (401,'Getriebe Typ AF 06  /I.ges=71,4',256,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (402,'Getriebe AF 08/ I.ges=58,6',262,14,'2010-09-30 00:00:00',14,'2010-09-30 00:00:00',NULL),
 (403,'3~Motor 0,2/0,8kW 590/2520U',262,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (404,'Steuerschrank Brücke',69,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (405,'Kranfahrtsteuerung',404,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (406,'???',405,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (407,'Steuerschrank Brücke',70,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (408,'Kransteuerung',407,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (409,'Katzensteuerung',407,14,'2010-09-30 00:00:00',NULL,NULL,NULL),
 (414,'Kernschießmaschine Bicor',NULL,14,'2010-10-04 00:00:00',14,'2011-01-19 00:00:00',4),
 (415,'Kernsandaufbereitung',NULL,14,'2010-10-04 00:00:00',14,'2011-01-19 00:00:00',4),
 (416,'Mischer 1',415,14,'2010-10-04 00:00:00',14,'2010-10-04 00:00:00',NULL),
 (417,'Mischer 2',415,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (420,'Pumpenschrank',415,14,'2010-10-04 00:00:00',14,'2010-10-04 00:00:00',NULL),
 (421,'Bedienschrank',415,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (422,'Mischelement',416,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (423,'Mischerklappe ',416,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (424,'Mischelement',417,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (425,'Mischerklappe ',417,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (426,'Abrufkasten 4',415,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (427,'Abrufkasten 3',415,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (428,'Abrufkasten 2',415,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (429,'Abrufkasten 1',415,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (430,'Abrufkasten 5',415,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (431,'Fahrwagen',415,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (432,'Doppelschneckendosierer (Sand)',415,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (433,'Sanddosierschnecke',415,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (438,'Vorlagebehälter 3',420,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (439,'Vorlagebehälter 2',420,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (440,'Vorlagebehälter 1',420,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (441,'Dosirpumpe1',440,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (442,'Dosirpumpe2',439,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (443,'Dosirpumpe3',438,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (444,'Zahnradpumpe WR3-18-GQS',441,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (445,'Zahnradpumpe WR3-18-GQS',442,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (446,'Zahnradpumpe WR3-18-GQS',443,14,'2010-10-04 00:00:00',NULL,NULL,NULL),
 (448,'Motor KBA 112 B4 /3,5kW / 1405U/min',24,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (449,'Getriebe FG 10 L / 0,8m/min ',24,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (450,'Getriebe DH 1050 / 8,0m/min',23,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (451,'Motor KBH 200 B 6 / 28,5kW / 955U/min',23,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (454,'Getriebe AMK 40 TD / 5/20m/mim',255,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (455,'Motor ZBF 100  A 8/2 / 0,2/0,8kW / 685/2760U',255,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (456,'Getriebe FG 08 / 0,8m/min',56,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (457,'Motor KBA 80 B4 / 1,5kW /1340U/min',56,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (458,'Haupthubwerk KBH 140 B4',55,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (459,'Getriebe DH 520 / 8m/min',458,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (460,'Motor KBH 140 B4 / 11,4kW / 1420U/min',458,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (461,'Getriebe AF 05 / 4/16m/min',60,14,'2010-10-05 00:00:00',14,'2010-10-05 00:00:00',NULL),
 (462,'Getriebe AF 05 / 4/16m/min',261,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (463,'Motor 0,2/0,8kW / 590/2520U/min',261,14,'2010-10-05 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (464,'Feinhubwerk KBA 80 B4',249,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (465,'Haupthubwerk KBH 140 B4 ',249,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (466,'Getriebe 12,5m/min',465,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (467,'Motor 11,4kW / 1420U/min',465,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (468,'Getriebe FG 08 / 1,2m/min',464,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (469,'Motor 1,5kW / 1340U/min',464,14,'2010-10-05 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (470,'Getriebe AME10DD / 5/20m/min',254,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (471,'Motor 0,09/0,34kW / 675/2785U/min',254,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (472,'Motor 3,2kW 930U/min',260,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (475,'Feinhubwerk KBA 90 B4',248,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (476,'Haupthubwerk KMH 160 B4',248,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (477,'Getriebe  8m/min',476,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (478,'Motor 17,8kW / 1440U/min',476,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (479,'Getriebe FG 10 / 0,8m/min',475,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (480,'Motor 2,3kW / 1365U/min',475,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (481,'Getriebe AF 06 / 20m/min',252,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (482,'Motor 0,5kW / 2570U/min',252,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (483,'Getriebe AF 08 / 10/40m/min',259,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (484,'Motor 0,2/0,8kW / 590/2520U/min',259,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (485,'Kranfahrwerk KBF 90 A8/2',248,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (486,'Getriebe AF 08 / 10/40m/min',485,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (487,'Motor 0,2/0,8kW / 590/2520U/min',485,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (488,'Kranfahrwerk KBF 90 A8/2',89,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (489,'Motor 0,2/0,8kW / 590/2520U/min',488,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (490,'Getriebe AF 08 / 10/40m/min',488,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (492,'Kranfahrwerk KBF 90 A8/2',34,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (493,'Motor 0,2/0,8kW / 590/2520U/min',492,14,'2010-10-05 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (494,'Kranfahrwerk KBF 90 A8/2',492,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (495,'Kranfahrt KBF 100 A8/2',20,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (496,'Motor 0,26/1,2kW / 610/2620U/min',495,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (497,' Getriebe AF10 S / 10/40U/min',495,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (498,'Katzfahrwerk KBF 80 A8/2',8,14,'2010-10-05 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (499,'Motor 0,13/0,5kW / 630/2710U/min',498,14,'2010-10-05 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (500,' Getriebe AMK 40 TS / 5,0/20,0m/min',498,14,'2010-10-05 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (501,'Kranfahrwerk ZBF 100 A8/2',8,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (502,'Motor 0,29/1,2kW / 685/2760U/min',501,14,'2010-10-05 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (503,'Getriebe ADE 50 DD / 10,0/40,0U/min',501,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (504,'Kranfahrwerk KBF 90 A8/2',66,14,'2010-10-05 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (505,'Getriebe AF 08/ I.ges=58,6',504,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (506,'3~Motor 0,2/0,8kW 590/2520U',504,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (507,'Feinhubwerk KBA 100 B4',247,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (508,'Haupthubwerk KBH 160 B4',247,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (509,'Getriebe DH1032 / 6,3m/min',508,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (510,'Motor 14,2kW / 1450U/min',508,14,'2010-10-05 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (511,'Getriebe FG 10 / 0,6m/min',507,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (512,'Motor 3kW / 1420U/min',507,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (513,'Getriebe AMK 40 TD / 5/20m/min',251,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (514,'Motor 0,29/1,2kW / 685/2760U/min',251,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (515,'Getriebe ADE 50 DD / 10/40m/min',258,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (516,'Motor 0,29/1,2kW / 685/2760U/min',258,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (517,'Kranfahrwerk ZBF 100 A8/2',69,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (518,'Getriebe ADE 50 DD / 10/40m/min',517,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (519,'Motor 0,29/1,2kW / 685/2760U/min',517,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (520,'Feinhubwerk KBA 100 B4',246,14,'2010-10-05 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (521,'Haupthubwerk KBH 160 B4',246,14,'2010-10-05 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (522,'Getriebe DH 1032 / 6,3m/min',521,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (523,'Motor 14,2kW / 1450U/min',521,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (524,'Getriebe FG 10 0,6m/min',520,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (525,'Motor 3kW / 1420U/min',520,14,'2010-10-05 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (526,'Getriebe AMK 40 TD / 5/20m/min',250,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (527,'Motor 0,29/1,2kW / 685/2760U/min',250,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (528,'Getriebe ADE 50 DD / 10/40m/min',257,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (529,'Motor 0,29/1,2kW / 685/2760U/min',257,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (530,'Kranfahrwerk ZBF 100 A8/2',70,14,'2010-10-05 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (531,'Getriebe ADE 50 DD / 10/40m/min',530,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (532,' Motor 0,29/1,2kW / 685/2760U/min',530,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (533,'Funksteuerung',70,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (534,'Funksender (Joysticksteuerung) DRC-J',533,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (535,'Funkempfänger DRC-MP',533,14,'2010-10-05 00:00:00',NULL,NULL,NULL),
 (536,'Feinheben',407,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (537,'Grobheben',407,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (538,'Kranfahrwerk KBF 100 A8/2',71,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (539,'Getriebe ADE 50 DS / 10/40m/min',538,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (540,'Motor 0,26/1,2kW / 610/2620U/min',538,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (541,'Feinhubwerk KBA 90 B4',122,14,'2010-10-06 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (542,'Haupthubwerk KBH 160 B4',122,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (543,'Getriebe DH 1050 / 10m/min',542,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (544,'Motor 17,8kW / 1440U/min',542,14,'2010-10-06 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (545,'Getriebe FG 10 ',541,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (546,'Motor 2,3kW / 1365U/min',541,14,'2010-10-06 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (547,'GetriebeAMK 40 DL / 10/20m/min',123,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (548,'Motor 0,2/0,8kW',123,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (549,'GetriebeAMK 50 DL / 10/40m/min',124,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (550,'Motor 0,2/0,8kW',124,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (551,'Kranfahrwerk ZBF 90 B8-2 B020',72,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (552,'GetriebeAMK 50 DL / 10/40m/min',551,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (553,'Motor 0,2/0,8kW',551,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (554,'Feinhubwerk KBA 80 B4',387,14,'2010-10-06 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (555,'Haupthubwerk KBH 140 B4',387,14,'2010-10-06 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (556,'Motor 11,4kW / 1420U/min',555,14,'2010-10-06 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (557,'Getriebe 12,5m/min',555,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (558,'FG 08 1,2m/min',554,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (559,'Motor 1,5kW / 1340U/min',554,14,'2010-10-06 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (560,'Getriebe AMK 30 DS / i=55,7 / 5/20m/min',278,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (561,'Motor 0,13/0,5kW / 630/2710U/min',278,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (563,'Haupthubwerk KBH 125 B 2/12',388,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (564,'Motoren 4,6/0,68kW / 2870/430U/min',563,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (565,'Motor 0,13/0,5kW / 630/2710U/min',279,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (566,'Getriebe KDHGR5 / 6,3/25m/min',279,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (567,'Hubmotor E 31',389,14,'2010-10-06 00:00:00',NULL,NULL,NULL),
 (568,'Halle Schlosserei',NULL,14,'2010-10-07 00:00:00',14,'2010-10-07 00:00:00',NULL),
 (569,'Zeile 1',568,14,'2010-10-07 00:00:00',NULL,NULL,NULL),
 (570,'Zeile 2',568,14,'2010-10-07 00:00:00',NULL,NULL,NULL),
 (571,'Säule 1',570,14,'2010-10-07 00:00:00',NULL,NULL,NULL),
 (572,'Säule 2',570,14,'2010-10-07 00:00:00',NULL,NULL,NULL),
 (573,'Säule 3',570,14,'2010-10-07 00:00:00',NULL,NULL,NULL),
 (574,'Ebene 1',571,14,'2010-10-07 00:00:00',NULL,NULL,NULL),
 (575,'Ebene 4',571,14,'2010-10-07 00:00:00',NULL,NULL,NULL),
 (576,'Ebene 3',571,14,'2010-10-07 00:00:00',NULL,NULL,NULL),
 (577,'Ebene 2',571,14,'2010-10-07 00:00:00',NULL,NULL,NULL),
 (578,'Position 2',574,14,'2010-10-07 00:00:00',NULL,NULL,NULL),
 (579,'Position 1',574,14,'2010-10-07 00:00:00',NULL,NULL,NULL),
 (580,'Halle Modellager',NULL,14,'2010-10-07 00:00:00',NULL,NULL,NULL),
 (581,'Zeile 1',580,14,'2010-10-07 00:00:00',NULL,NULL,NULL),
 (582,'Säule 1',581,14,'2010-10-07 00:00:00',NULL,NULL,NULL),
 (583,'Ebene 1',582,14,'2010-10-07 00:00:00',NULL,NULL,NULL),
 (584,'Position 1',583,14,'2010-10-07 00:00:00',NULL,NULL,NULL);
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
 (8,9,1),
 (8,12,1),
 (10,9,1),
 (10,21,1),
 (10,111,1),
 (10,112,1),
 (10,122,1),
 (10,246,1),
 (10,247,1),
 (10,248,1),
 (12,9,1),
 (12,21,1),
 (12,111,1),
 (12,139,1),
 (12,246,1),
 (12,247,1),
 (13,579,4),
 (14,112,1),
 (15,112,1),
 (15,578,3),
 (16,139,1),
 (17,55,1),
 (18,249,1),
 (20,125,1),
 (21,387,1),
 (22,388,1),
 (24,389,1),
 (28,221,1),
 (28,231,1),
 (28,232,1),
 (29,231,1),
 (29,232,1),
 (30,180,1),
 (30,187,1),
 (31,178,1),
 (32,178,1),
 (33,291,1),
 (33,308,1),
 (33,328,1),
 (33,334,1),
 (33,335,1),
 (33,336,1),
 (34,291,1),
 (34,308,1),
 (35,233,1),
 (35,234,1),
 (35,235,1),
 (35,238,1),
 (35,291,1),
 (35,308,1),
 (35,322,1),
 (35,324,1),
 (36,235,1),
 (36,238,1),
 (36,291,1),
 (36,308,1),
 (36,322,1),
 (36,324,1),
 (37,291,1),
 (37,308,1),
 (37,322,1),
 (38,233,1),
 (38,234,1),
 (38,235,1),
 (38,236,1),
 (38,237,1),
 (38,238,1),
 (38,311,1),
 (38,312,1),
 (38,317,1),
 (38,319,1),
 (39,233,1),
 (39,234,1),
 (39,235,1),
 (39,236,1),
 (39,237,1),
 (39,311,1),
 (39,312,1),
 (39,317,1),
 (39,319,1),
 (39,322,1),
 (40,233,1),
 (40,234,1),
 (40,236,2),
 (40,237,2),
 (40,311,1),
 (40,312,1),
 (40,317,1),
 (40,319,1),
 (40,322,1),
 (40,324,1),
 (40,334,1),
 (41,322,1),
 (42,322,2),
 (42,324,1),
 (42,328,2),
 (42,334,6),
 (42,335,6),
 (42,336,2),
 (43,322,1),
 (44,324,1),
 (45,324,1),
 (45,335,1),
 (46,322,1),
 (47,322,1),
 (48,324,1),
 (48,331,1),
 (48,332,1),
 (49,334,1),
 (50,336,1),
 (51,405,1),
 (52,405,1),
 (52,408,1),
 (53,408,2),
 (56,390,1),
 (56,391,1),
 (57,392,1),
 (58,393,1),
 (71,443,1),
 (72,441,1),
 (72,442,1),
 (74,438,1),
 (74,439,1),
 (74,440,1),
 (75,438,1),
 (75,439,1),
 (75,440,1),
 (76,438,1),
 (76,439,1),
 (76,440,1),
 (77,439,1),
 (78,438,1),
 (78,439,1),
 (78,440,1),
 (83,55,1),
 (83,139,1),
 (84,9,1),
 (85,21,1),
 (85,584,2),
 (86,248,1),
 (86,249,1),
 (88,111,1);
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
 (18,'Miftari','Saja','HeiligerMann','hagi','schlosserei@ke-ag.de',0,NULL,NULL,14,'2011-01-17 00:00:00'),
 (19,'Thurner','Dieter','Dieter','Diet','thurner@ke-ag.de',0,NULL,NULL,NULL,NULL),
 (20,'Schmidt','Eugen','eugen','1111','elektrowerkstatt@ke-ag.de',0,NULL,NULL,NULL,NULL),
 (57,'admin','admin2','admin2','1111','eugen.schmidt@freenet.de',0,NULL,NULL,14,'2011-01-29 00:00:00'),
 (67,'Clausnitzer','Dietmar','mitarbeiter','mita','elektrowerkstatt@ke-ag.de',0,NULL,NULL,14,'2011-01-19 00:00:00'),
 (70,'Kovacevic','Mile','mile','mile','elektrowerkstatt@ke-ag.de',0,NULL,NULL,NULL,NULL),
 (71,'Cebulla','Harald','cebu','cebu','elektrowerkstatt@ke-ag.de',0,NULL,NULL,NULL,NULL),
 (74,'Marquart','Peter','pit','pitt','marquardt@ke-ag.de',0,NULL,NULL,14,'2011-01-25 00:00:00'),
 (75,'Schreier','Matthias','mate','mate','schlosserei@ke-ag.de',0,NULL,NULL,NULL,NULL),
 (76,'Bakic','Schevko','schevko','sche','schlosserei@ke-ag.de',0,NULL,NULL,NULL,NULL),
 (77,'Kristen','Klaus','krause','krau','schlosserei@ke-ag.de',0,NULL,NULL,14,'2011-01-25 00:00:00'),
 (78,'Schieleit','Klaus','klaus','grun','schlosserei@ke-ag.de',0,NULL,NULL,14,'2011-01-17 00:00:00'),
 (79,'Schmidt','Alfred','alufred','aluf','schlosserei@ke-ag.de',0,NULL,NULL,NULL,NULL),
 (80,'Bönke','Peter','Nikolaus','niko','',0,NULL,NULL,NULL,NULL),
 (81,'Harder','Vitali','Vitali','Vita','',0,NULL,NULL,NULL,NULL),
 (82,'Reichenberger','Wolfgang','Wolfi','wolf','',0,NULL,NULL,NULL,NULL);
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
 (17,2),
 (19,2),
 (20,2),
 (57,2),
 (71,2),
 (74,2),
 (75,2),
 (80,2),
 (81,2),
 (82,2),
 (14,3),
 (18,3),
 (20,3),
 (57,3),
 (67,3),
 (70,3),
 (76,3),
 (77,3),
 (78,3),
 (79,3);
/*!40000 ALTER TABLE `benutzerzugrifsrechtecht` ENABLE KEYS */;


--
-- Definition of table `bericht`
--

DROP TABLE IF EXISTS `bericht`;
CREATE TABLE `bericht` (
  `id` int(10) unsigned NOT NULL,
  `erfassungsbenutzer` int(10) unsigned DEFAULT NULL,
  `erfassungsdatum` datetime DEFAULT NULL,
  `aenderungsbenutzer` int(10) unsigned DEFAULT NULL,
  `aenderungsdatum` datetime DEFAULT NULL,
  `TYP` varchar(20) NOT NULL,
  `benutzer` int(10) unsigned NOT NULL,
  `art` varchar(10) NOT NULL,
  `druckdatumoriginal` datetime DEFAULT NULL,
  `druckdatumkopie` datetime DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `typID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bericht`
--

/*!40000 ALTER TABLE `bericht` DISABLE KEYS */;
INSERT INTO `bericht` (`id`,`erfassungsbenutzer`,`erfassungsdatum`,`aenderungsbenutzer`,`aenderungsdatum`,`TYP`,`benutzer`,`art`,`druckdatumoriginal`,`druckdatumkopie`,`email`,`typID`) VALUES 
 (1,NULL,NULL,NULL,NULL,'BENUTZER',1,'PRINT',NULL,NULL,NULL,0),
 (2,NULL,NULL,NULL,NULL,'BESTELLANFORDERUNG',2,'EMAIL',NULL,NULL,NULL,0),
 (3,14,'2010-12-03 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-03 00:00:00','2010-12-03 00:00:00','',1148),
 (4,14,'2010-12-03 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-03 00:00:00','2010-12-03 00:00:00','',1148),
 (5,14,'2010-12-03 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-03 00:00:00','2010-12-03 00:00:00','',1148),
 (6,14,'2010-12-03 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-03 00:00:00','2010-12-03 00:00:00','',1148),
 (7,14,'2010-12-03 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-03 00:00:00','2010-12-03 00:00:00','',1145),
 (8,14,'2010-12-03 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-03 00:00:00','2010-12-03 00:00:00','',1148),
 (9,14,'2010-12-04 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-04 00:00:00','2010-12-04 00:00:00','',1146),
 (10,14,'2010-12-04 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-04 00:00:00','2010-12-04 00:00:00','',1146),
 (11,14,'2010-12-04 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-04 00:00:00','2010-12-04 00:00:00','',1146),
 (12,14,'2010-12-04 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-04 00:00:00','2010-12-04 00:00:00','',1146),
 (13,14,'2010-12-04 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-04 00:00:00','2010-12-04 00:00:00','',1142),
 (14,14,'2010-12-04 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-04 00:00:00','2010-12-04 00:00:00','',1142),
 (15,14,'2010-12-04 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-04 00:00:00','2010-12-04 00:00:00','',1126),
 (16,14,'2010-12-04 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-04 00:00:00','2010-12-04 00:00:00','',1141),
 (17,14,'2010-12-05 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-05 00:00:00','2010-12-05 00:00:00','',1143),
 (18,14,'2010-12-05 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-05 00:00:00','2010-12-05 00:00:00','',1143),
 (19,14,'2010-12-05 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-05 00:00:00','2010-12-05 00:00:00','',1143),
 (20,14,'2010-12-05 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-05 00:00:00','2010-12-05 00:00:00','',1098),
 (21,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1140),
 (22,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1140),
 (23,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1140),
 (24,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1140),
 (25,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1140),
 (26,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1099),
 (27,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1128),
 (28,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1128),
 (29,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1092),
 (30,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1127),
 (31,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1127),
 (32,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1127),
 (33,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1124),
 (34,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1122),
 (35,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1122),
 (36,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1122),
 (37,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1122),
 (38,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1088),
 (39,20,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1089),
 (40,20,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1089),
 (41,20,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1090),
 (42,20,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1090),
 (43,20,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1091),
 (44,20,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1091),
 (45,20,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1091),
 (46,20,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1091),
 (47,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1046),
 (48,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1046),
 (49,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1093),
 (50,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1112),
 (51,14,'2010-12-07 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-07 00:00:00','2010-12-07 00:00:00','',1096),
 (52,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1112),
 (53,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1112),
 (54,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1112),
 (55,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1112),
 (56,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1112),
 (57,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1093),
 (58,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1093),
 (59,14,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1094),
 (60,14,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1093),
 (61,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1112),
 (62,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1112),
 (63,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1096),
 (64,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1097),
 (65,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1102),
 (66,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1102),
 (67,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1046),
 (68,20,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',20,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1046),
 (69,14,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1108),
 (70,14,'2010-12-08 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-08 00:00:00','2010-12-08 00:00:00','',1107),
 (71,14,'2010-12-09 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-09 00:00:00','2010-12-09 00:00:00','',1048),
 (72,14,'2010-12-09 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-09 00:00:00','2010-12-09 00:00:00','',1048),
 (73,14,'2010-12-09 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-09 00:00:00','2010-12-09 00:00:00','',1105),
 (74,14,'2010-12-09 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2010-12-09 00:00:00','2010-12-09 00:00:00','',1049),
 (75,14,'2011-01-29 00:00:00',NULL,NULL,'ANFORDERUNG',14,'EMAIL','2011-01-29 00:00:00','2011-01-29 00:00:00','',1104),
 (76,14,'2011-03-25 00:00:00',NULL,NULL,'INVENTUR_LISTE',14,'PRINT','2011-03-25 00:00:00','2011-03-25 00:00:00','',1),
 (77,14,'2011-03-25 00:00:00',NULL,NULL,'INVENTUR_LISTE',14,'PRINT','2011-03-25 00:00:00','2011-03-25 00:00:00','',1),
 (78,14,'2011-03-26 00:00:00',NULL,NULL,'INVENTUR_LISTE',14,'PRINT','2011-03-26 00:00:00','2011-03-26 00:00:00','',1),
 (79,14,'2011-03-27 00:00:00',NULL,NULL,'INVENTUR_LISTE',14,'PRINT','2011-03-27 00:00:00','2011-03-27 00:00:00','',1);
/*!40000 ALTER TABLE `bericht` ENABLE KEYS */;


--
-- Definition of table `bericht_bestellanforderung`
--

DROP TABLE IF EXISTS `bericht_bestellanforderung`;
CREATE TABLE `bericht_bestellanforderung` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `erstellungsdatum` datetime DEFAULT NULL,
  `status` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_herstellerLieferant` int(10) unsigned DEFAULT NULL,
  `fk_herstellerLieferant_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
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

--
-- Dumping data for table `bericht_bestellanforderung`
--

/*!40000 ALTER TABLE `bericht_bestellanforderung` DISABLE KEYS */;
INSERT INTO `bericht_bestellanforderung` (`fk_bericht_id`,`id`,`erstellungsdatum`,`status`,`fk_herstellerLieferant`,`fk_herstellerLieferant_name`,`fk_herstellerLieferant_adresse_land`,`fk_herstellerLieferant_adresse_plz`,`fk_herstellerLieferant_adresse_stadt`,`fk_herstellerLieferant_adresse_strasse`,`fk_herstellerLieferant_telefon`,`fk_herstellerLieferant_fax`,`fk_herstellerLieferant_email`,`fk_herstellerLieferant_ansprechpartner`,`fk_benutzerAbsender`,`fk_benutzerAbsender_name`,`fk_benutzerAbsender_vorname`,`fk_benutzerAbsender_loginName`,`fk_benutzerAbsender_email`,`fk_benutzerAnnehmer`,`fk_benutzerAnnehmer_name`,`fk_benutzerAnnehmer_vorname`,`fk_benutzerAnnehmer_loginName`,`fk_benutzerAnnehmer_email`,`email_ba_empfaenger`) VALUES 
 (3,1148,'2010-07-04 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (4,1148,'2010-07-04 00:00:00','E',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (5,1148,'2010-07-04 00:00:00','E',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (6,1148,'2010-07-04 00:00:00','E',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (7,1145,'2010-07-02 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'email@emai5l.de'),
 (8,1148,'2010-07-04 00:00:00','E',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (9,1146,'2010-07-03 00:00:00','O',6,'Schaller','DE','87436','Kempten (Ursulasried','Eichendorffweg 6','0831 / 110 6','0831 / 1103 6','email@emai6l.de','Ansprechpartner von Lieferant 6',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (10,1146,'2010-07-03 00:00:00','E',6,'Schaller','DE','87436','Kempten (Ursulasried','Eichendorffweg 6','0831 / 110 6','0831 / 1103 6','email@emai6l.de','Ansprechpartner von Lieferant 6',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (11,1146,'2010-07-03 00:00:00','E',6,'Schaller','DE','87436','Kempten (Ursulasried','Eichendorffweg 6','0831 / 110 6','0831 / 1103 6','email@emai6l.de','Ansprechpartner von Lieferant 6',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (12,1146,'2010-07-03 00:00:00','E',6,'Schaller','DE','87436','Kempten (Ursulasried','Eichendorffweg 6','0831 / 110 6','0831 / 1103 6','email@emai6l.de','Ansprechpartner von Lieferant 6',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (13,1142,'2010-06-25 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'email@email.de23'),
 (14,1142,'2010-06-25 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'email@email.de23'),
 (15,1126,'2010-06-12 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (16,1141,'2010-06-12 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (17,1143,'2010-06-25 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (18,1143,'2010-06-25 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (19,1143,'2010-06-25 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (20,1098,'2010-06-11 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (21,1140,'2010-06-12 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (22,1140,'2010-06-12 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (23,1140,'2010-06-12 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (24,1140,'2010-06-12 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (25,1140,'2010-06-12 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (26,1099,'2010-06-11 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (27,1128,'2010-06-12 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (28,1128,'2010-06-12 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (29,1092,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (30,1127,'2010-06-12 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (31,1127,'2010-06-12 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (32,1127,'2010-06-12 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (33,1124,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (34,1122,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (35,1122,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (36,1122,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (37,1122,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (38,1088,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (39,1089,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (40,1089,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (41,1090,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (42,1090,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (43,1091,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (44,1091,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (45,1091,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (46,1091,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (47,1046,'2010-05-14 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (48,1046,'2010-05-14 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (49,1093,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (50,1112,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (51,1096,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (52,1112,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (53,1112,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (54,1112,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (55,1112,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (56,1112,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (57,1093,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (58,1093,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (59,1094,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (60,1093,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (61,1112,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (62,1112,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (63,1096,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (64,1097,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'schlosserei@ke-ag.de'),
 (65,1102,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),
 (66,1102,'2010-06-11 00:00:00','E',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),
 (67,1046,'2010-05-14 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'schlosserei@ke-ag.de'),
 (68,1046,'2010-05-14 00:00:00','E',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'schlosserei@ke-ag.de'),
 (69,1108,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (70,1107,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (71,1048,'2010-05-14 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (72,1048,'2010-05-14 00:00:00','E',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (73,1105,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de'),
 (74,1049,'2010-05-14 00:00:00','O',5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (75,1104,'2010-06-11 00:00:00','O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'thurner@ke-ag.de');
/*!40000 ALTER TABLE `bericht_bestellanforderung` ENABLE KEYS */;


--
-- Definition of table `bericht_bestellanforderungsposition`
--

DROP TABLE IF EXISTS `bericht_bestellanforderungsposition`;
CREATE TABLE `bericht_bestellanforderungsposition` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `fk_bestellanforderung` int(10) unsigned NOT NULL,
  `fk_artikel` int(10) unsigned DEFAULT NULL,
  `fk_artikel_bezeichnung` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
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
  `lieferantenbestellnummer` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `katalogseite` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `katalogpreis` double DEFAULT NULL,
  PRIMARY KEY (`id`,`fk_bericht_id`) USING BTREE,
  KEY `FK_bericht_bestellanforderungsposition_1` (`fk_bericht_id`,`id`),
  KEY `FK_bericht_bestellanforderungsposition` (`fk_bericht_id`,`fk_bestellanforderung`),
  CONSTRAINT `FK_bericht_bestellanforderungsposition` FOREIGN KEY (`fk_bericht_id`, `fk_bestellanforderung`) REFERENCES `bericht_bestellanforderung` (`fk_bericht_id`, `id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bericht_bestellanforderungsposition`
--

/*!40000 ALTER TABLE `bericht_bestellanforderungsposition` DISABLE KEYS */;
INSERT INTO `bericht_bestellanforderungsposition` (`fk_bericht_id`,`id`,`fk_bestellanforderung`,`fk_artikel`,`fk_artikel_bezeichnung`,`fk_artikel_typ`,`fk_artikel_keg_nr`,`bestellmenge`,`liefertermin`,`status`,`fk_kostenstelle`,`fk_kostenstelle_name`,`fk_kostenstelle_nummer`,`fk_mengeneinheit`,`fk_katalog`,`fk_katalog_name`,`lieferantenbestellnummer`,`katalogseite`,`katalogpreis`) VALUES 
 (47,2074,1046,17,'Drahtseil','8x36+SES zS/14 mm/30m',17,7,19,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (48,2074,1046,17,'Drahtseil','8x36+SES zS/14 mm/30m',17,7,19,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (67,2074,1046,17,'Drahtseil','8x36+SES zS/14 mm/30m',17,7,19,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (68,2074,1046,17,'Drahtseil','8x36+SES zS/14 mm/30m',17,7,19,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (71,2076,1048,7,'Schütz','3RT1017-1AAP01 / 5,5kW / Spule~230V',7,10,19,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (72,2076,1048,7,'Schütz','3RT1017-1AAP01 / 5,5kW / Spule~230V',7,10,19,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (20,2117,1098,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,53,'O',4,'Elektroofen','1212',1,2,'Hagemeier 2009','712123','5 - 123',0),
 (50,2134,1112,105,'Messumformer für Leitfähigkeit','Liquisys M CLM223/253',105,1,53,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (52,2134,1112,105,'Messumformer für Leitfähigkeit','Liquisys M CLM223/253',105,1,53,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (53,2134,1112,105,'Messumformer für Leitfähigkeit','Liquisys M CLM223/253',105,1,53,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (54,2134,1112,105,'Messumformer für Leitfähigkeit','Liquisys M CLM223/253',105,1,53,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (55,2134,1112,105,'Messumformer für Leitfähigkeit','Liquisys M CLM223/253',105,1,53,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (56,2134,1112,105,'Messumformer für Leitfähigkeit','Liquisys M CLM223/253',105,1,53,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (61,2134,1112,105,'Messumformer für Leitfähigkeit','Liquisys M CLM223/253',105,1,53,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (62,2134,1112,105,'Messumformer für Leitfähigkeit','Liquisys M CLM223/253',105,1,53,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (34,2136,1122,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,2,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (35,2136,1122,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,2,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (36,2136,1122,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,2,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (37,2136,1122,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,2,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (34,2137,1122,2,'Schütz','3RT1035-1A / 18,5kW / Spule~230V',2,2,23,'O',NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,0),
 (35,2137,1122,2,'Schütz','3RT1035-1A / 18,5kW / Spule~230V',2,2,23,'O',NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,0),
 (36,2137,1122,2,'Schütz','3RT1035-1A / 18,5kW / Spule~230V',2,2,23,'O',NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,0),
 (37,2137,1122,2,'Schütz','3RT1035-1A / 18,5kW / Spule~230V',2,2,23,'O',NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,0),
 (34,2138,1122,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,3,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (35,2138,1122,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,3,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (36,2138,1122,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,3,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (37,2138,1122,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,3,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (33,2140,1124,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,23,'O',NULL,NULL,NULL,1,2,'Hagemeier 2009','712123','5 - 123',0),
 (33,2141,1124,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,4,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (39,2142,1089,5,'Schütz','3RT1045-1A / 37,0kW / Spule~230V',5,1,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (40,2142,1089,5,'Schütz','3RT1045-1A / 37,0kW / Spule~230V',5,1,23,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (15,2143,1126,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,23,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (30,2144,1127,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,23,'O',1,'Elektrowerkstatt','1711',1,1,'Schaller 2010','123123  / b12','3 / 152',0),
 (31,2144,1127,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,23,'O',1,'Elektrowerkstatt','1711',1,1,'Schaller 2010','123123  / b12','3 / 152',0),
 (32,2144,1127,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,23,'O',1,'Elektrowerkstatt','1711',1,1,'Schaller 2010','123123  / b12','3 / 152',0),
 (27,2145,1128,7,'Schütz','3RT1017-1AAP01 / 5,5kW / Spule~230V',7,10,23,'O',4,'Elektroofen','1212',3,NULL,NULL,NULL,NULL,0),
 (28,2145,1128,7,'Schütz','3RT1017-1AAP01 / 5,5kW / Spule~230V',7,10,23,'O',4,'Elektroofen','1212',3,NULL,NULL,NULL,NULL,0),
 (13,2150,1142,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,25,'O',4,'Elektroofen','1212',1,1,'Schaller 2010','123123  / b12','3 / 152',0),
 (14,2150,1142,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,25,'O',4,'Elektroofen','1212',1,1,'Schaller 2010','123123  / b12','3 / 152',0),
 (17,2151,1143,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,25,'O',5,'Schreinerei','1211',1,2,'Hagemeier 2009','712123','5 - 123',0),
 (18,2151,1143,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,25,'O',5,'Schreinerei','1211',1,2,'Hagemeier 2009','712123','5 - 123',0),
 (19,2151,1143,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,25,'O',5,'Schreinerei','1211',1,2,'Hagemeier 2009','712123','5 - 123',0),
 (17,2152,1143,5,'Schütz','3RT1045-1A / 37,0kW / Spule~230V',5,5,25,'O',1,'Elektrowerkstatt','1711',3,NULL,NULL,NULL,NULL,0),
 (18,2152,1143,5,'Schütz','3RT1045-1A / 37,0kW / Spule~230V',5,5,25,'O',1,'Elektrowerkstatt','1711',3,NULL,NULL,NULL,NULL,0),
 (19,2152,1143,5,'Schütz','3RT1045-1A / 37,0kW / Spule~230V',5,5,25,'O',1,'Elektrowerkstatt','1711',3,NULL,NULL,NULL,NULL,0),
 (7,2153,1145,7,'Schütz','3RT1017-1AAP01 / 5,5kW / Spule~230V',7,10,26,'O',NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,0),
 (7,2154,1145,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,26,'O',NULL,NULL,NULL,3,2,'Hagemeier 2009','712123','5 - 123',0),
 (9,2155,1146,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,26,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (10,2155,1146,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,26,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (11,2155,1146,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,26,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (12,2155,1146,1,'Schütz','3RT1036-1A / 22,0kW / Spule~230V',1,1,26,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (9,2156,1146,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,4,26,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (10,2156,1146,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,4,26,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (11,2156,1146,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,4,26,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (12,2156,1146,4,'Schütz','3RT1044-1A / 30,0kW / Spule~230V',4,4,26,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (3,2157,1148,71,'3~Motor','SSKh 90L-8 /0,55kW /675U/min',71,2,33,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (4,2157,1148,71,'3~Motor','SSKh 90L-8 /0,55kW /675U/min',71,2,33,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (5,2157,1148,71,'3~Motor','SSKh 90L-8 /0,55kW /675U/min',71,2,33,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (6,2157,1148,71,'3~Motor','SSKh 90L-8 /0,55kW /675U/min',71,2,33,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (8,2157,1148,71,'3~Motor','SSKh 90L-8 /0,55kW /675U/min',71,2,33,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (9,2158,1146,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,10,28,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (10,2158,1146,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,10,28,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (11,2158,1146,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,10,28,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (12,2158,1146,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,10,28,'O',NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,0),
 (13,2167,1142,107,'2-Wege  Kugelhahn ,Kunstoff,für Säurelei','546',107,1,48,'O',3,'Formautomat','148 / 15',1,NULL,NULL,NULL,NULL,0),
 (14,2167,1142,107,'2-Wege  Kugelhahn ,Kunstoff,für Säurelei','546',107,1,48,'O',3,'Formautomat','148 / 15',1,NULL,NULL,NULL,NULL,0),
 (15,2169,1126,3,'Schütz','3RT1026-1A / 11,0kW / Spule~230V',3,5,48,'O',4,'Elektroofen','1212',1,2,'Hagemeier 2009','144433','3/543',0),
 (16,2170,1141,2,'Schütz','3RT1035-1A / 18,5kW / Spule~230V',2,5,48,'O',2,'Schlosserei','152 / 112',1,2,'Hagemeier 2009','121111','1/543',0),
 (16,2171,1141,52,'Schnellschütz / 5,5kW','3RT1517-1APOO',52,10,48,'O',5,'Schreinerei','1211',1,1,'Schaller 2010','???',NULL,0),
 (16,2172,1141,53,'Schütz / 5,5kW','3RT1017-1AP01',53,10,48,'O',1,'Elektrowerkstatt','1711',1,1,'Schaller 2010','???',NULL,0),
 (20,2173,1098,107,'2-Wege  Kugelhahn ,Kunstoff,für Säurelei','546',107,1,48,'O',2,'Schlosserei','152 / 112',1,NULL,NULL,NULL,NULL,0),
 (21,2176,1140,118,'DIAZED-Schraubkappe','DII 25A AC DC50 Keramik mit Prüfloch',118,10,49,'O',NULL,NULL,NULL,1,2,'Hagemeier 2009','2606490',NULL,0),
 (22,2176,1140,118,'DIAZED-Schraubkappe','DII 25A AC DC50 Keramik mit Prüfloch',118,10,49,'O',NULL,NULL,NULL,1,2,'Hagemeier 2009','2606490',NULL,0),
 (23,2176,1140,118,'DIAZED-Schraubkappe','DII 25A AC DC50 Keramik mit Prüfloch',118,10,49,'O',NULL,NULL,NULL,1,2,'Hagemeier 2009','2606490',NULL,0),
 (24,2176,1140,118,'DIAZED-Schraubkappe','DII 25A AC DC50 Keramik mit Prüfloch',118,10,49,'O',NULL,NULL,NULL,1,2,'Hagemeier 2009','2606490',NULL,0),
 (25,2176,1140,118,'DIAZED-Schraubkappe','DII 25A AC DC50 Keramik mit Prüfloch',118,10,49,'O',NULL,NULL,NULL,1,2,'Hagemeier 2009','2606490',NULL,0),
 (26,2177,1099,71,'3~Motor','SSKh 90L-8 /0,55kW /675U/min',71,1,49,'O',3,'Formautomat','148 / 15',1,NULL,NULL,NULL,NULL,0),
 (29,2178,1092,72,'3~Motor','AR 90L-8 /0,55kW /705U/min',72,1,49,'O',1,'Elektrowerkstatt','1711',1,NULL,NULL,NULL,NULL,0),
 (41,2180,1090,2,'Schütz','3RT1035-1A / 18,5kW / Spule~230V',2,5,49,'O',4,'Elektroofen','1212',1,2,'Hagemeier 2009','121111','1/543',0),
 (42,2180,1090,2,'Schütz','3RT1035-1A / 18,5kW / Spule~230V',2,5,49,'O',4,'Elektroofen','1212',1,2,'Hagemeier 2009','121111','1/543',0),
 (43,2181,1091,81,'Schütz','3RT1034-1AP00 /15kW/Spule~230V',81,5,49,'O',5,'Schreinerei','1211',1,NULL,NULL,NULL,NULL,0),
 (44,2181,1091,81,'Schütz','3RT1034-1AP00 /15kW/Spule~230V',81,5,49,'O',5,'Schreinerei','1211',1,NULL,NULL,NULL,NULL,0),
 (45,2181,1091,81,'Schütz','3RT1034-1AP00 /15kW/Spule~230V',81,5,49,'O',5,'Schreinerei','1211',1,NULL,NULL,NULL,NULL,0),
 (46,2181,1091,81,'Schütz','3RT1034-1AP00 /15kW/Spule~230V',81,5,49,'O',5,'Schreinerei','1211',1,NULL,NULL,NULL,NULL,0),
 (43,2182,1091,52,'Schnellschütz / 5,5kW','3RT1517-1APOO',52,10,49,'O',3,'Formautomat','148 / 15',1,1,'Schaller 2010','???',NULL,0),
 (44,2182,1091,52,'Schnellschütz / 5,5kW','3RT1517-1APOO',52,10,49,'O',3,'Formautomat','148 / 15',1,1,'Schaller 2010','???',NULL,0),
 (45,2182,1091,52,'Schnellschütz / 5,5kW','3RT1517-1APOO',52,10,49,'O',3,'Formautomat','148 / 15',1,1,'Schaller 2010','???',NULL,0),
 (46,2182,1091,52,'Schnellschütz / 5,5kW','3RT1517-1APOO',52,10,49,'O',3,'Formautomat','148 / 15',1,1,'Schaller 2010','???',NULL,0),
 (49,2183,1093,96,'Multifunktions-Zeitrelais,18Funkt.,1W','MFZ12DX-UC',96,5,49,'O',1,'Elektrowerkstatt','1711',1,NULL,NULL,NULL,NULL,0),
 (57,2183,1093,96,'Multifunktions-Zeitrelais,18Funkt.,1W','MFZ12DX-UC',96,5,49,'O',1,'Elektrowerkstatt','1711',1,NULL,NULL,NULL,NULL,0),
 (58,2183,1093,96,'Multifunktions-Zeitrelais,18Funkt.,1W','MFZ12DX-UC',96,5,49,'O',1,'Elektrowerkstatt','1711',1,NULL,NULL,NULL,NULL,0),
 (60,2183,1093,96,'Multifunktions-Zeitrelais,18Funkt.,1W','MFZ12DX-UC',96,5,49,'O',1,'Elektrowerkstatt','1711',1,NULL,NULL,NULL,NULL,0),
 (51,2184,1096,17,'Drahtseil','8x36+SES zS/14 mm/30m',17,6,49,'O',5,'Schreinerei','1211',1,NULL,NULL,NULL,NULL,0),
 (63,2184,1096,17,'Drahtseil','8x36+SES zS/14 mm/30m',17,6,49,'O',5,'Schreinerei','1211',1,NULL,NULL,NULL,NULL,0),
 (52,2185,1112,76,'Dosierventil','3/2-Wege 3/4\"',76,1,49,'O',4,'Elektroofen','1212',1,NULL,NULL,NULL,NULL,0),
 (53,2185,1112,76,'Dosierventil','3/2-Wege 3/4\"',76,1,49,'O',4,'Elektroofen','1212',1,NULL,NULL,NULL,NULL,0),
 (54,2185,1112,76,'Dosierventil','3/2-Wege 3/4\"',76,1,49,'O',4,'Elektroofen','1212',1,NULL,NULL,NULL,NULL,0),
 (55,2185,1112,76,'Dosierventil','3/2-Wege 3/4\"',76,1,49,'O',4,'Elektroofen','1212',1,NULL,NULL,NULL,NULL,0),
 (56,2185,1112,76,'Dosierventil','3/2-Wege 3/4\"',76,1,49,'O',4,'Elektroofen','1212',1,NULL,NULL,NULL,NULL,0),
 (61,2185,1112,76,'Dosierventil','3/2-Wege 3/4\"',76,1,49,'O',4,'Elektroofen','1212',1,NULL,NULL,NULL,NULL,0),
 (62,2185,1112,76,'Dosierventil','3/2-Wege 3/4\"',76,1,49,'O',4,'Elektroofen','1212',1,NULL,NULL,NULL,NULL,0),
 (59,2186,1094,17,'Drahtseil','8x36+SES zS/14 mm/30m',17,1,49,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (64,2187,1097,80,'5/2-Wege-Magnetventil','JMFH-5-1/4',80,7,49,'O',1,'Elektrowerkstatt','1711',1,NULL,NULL,NULL,NULL,0),
 (65,2188,1102,80,'5/2-Wege-Magnetventil','JMFH-5-1/4',80,6,49,'O',4,'Elektroofen','1212',1,NULL,NULL,NULL,NULL,0),
 (66,2188,1102,80,'5/2-Wege-Magnetventil','JMFH-5-1/4',80,6,49,'O',4,'Elektroofen','1212',1,NULL,NULL,NULL,NULL,0),
 (69,2189,1108,107,'2-Wege  Kugelhahn ,Kunstoff,für Säurelei','546',107,1,49,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (71,2190,1048,18,'Drahtseil','9x19+SES zS/14 mm/28m',18,7,49,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (72,2190,1048,18,'Drahtseil','9x19+SES zS/14 mm/28m',18,7,49,'O',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),
 (73,2191,1105,117,'Durchgangsklemmen (PE)','UT 2,5-TWIN PE',117,50,49,'O',2,'Schlosserei','152 / 112',1,2,'Hagemeier 2009','2842906',NULL,0),
 (74,2192,1049,117,'Durchgangsklemmen (PE)','UT 2,5-TWIN PE',117,50,49,'O',1,'Elektrowerkstatt','1711',1,2,'Hagemeier 2009','2842906',NULL,0);
/*!40000 ALTER TABLE `bericht_bestellanforderungsposition` ENABLE KEYS */;


--
-- Definition of table `bericht_inventur_bestandspackstueck`
--

DROP TABLE IF EXISTS `bericht_inventur_bestandspackstueck`;
CREATE TABLE `bericht_inventur_bestandspackstueck` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `fk_artikel` int(10) unsigned NOT NULL,
  `fk_position` int(10) unsigned DEFAULT NULL,
  `menge` int(10) unsigned DEFAULT NULL,
  `artikel_bezeichnung` varchar(70) COLLATE utf8_unicode_ci DEFAULT NULL,
  `artikel_typ` varchar(70) COLLATE utf8_unicode_ci DEFAULT NULL,
  `artikel_keg_nr` int(10) unsigned DEFAULT NULL,
  `artikel_mindestbestand` int(10) unsigned DEFAULT NULL,
  `artikel_fk_mengeneinheit` int(10) unsigned DEFAULT NULL,
  `artikel_fk_hersteller` int(10) unsigned DEFAULT NULL,
  `hersteller_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mengeneinheit_name` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bericht_inventur_bestandspackstueck`
--

/*!40000 ALTER TABLE `bericht_inventur_bestandspackstueck` DISABLE KEYS */;
INSERT INTO `bericht_inventur_bestandspackstueck` (`fk_bericht_id`,`id`,`fk_artikel`,`fk_position`,`menge`,`artikel_bezeichnung`,`artikel_typ`,`artikel_keg_nr`,`artikel_mindestbestand`,`artikel_fk_mengeneinheit`,`artikel_fk_hersteller`,`hersteller_name`,`mengeneinheit_name`) VALUES 
 (76,33,141,339,100,'Abstandschellen,Stahl verzinkt,30-38 mm','733',141,NULL,1,60,'OBO (Bettermann)','Stück'),
 (76,34,139,338,100,'Abstandschellen,Stahl verzinkt,24-29 mm','733/M25 PG 21',139,NULL,1,60,'OBO (Bettermann)','Stück'),
 (76,35,138,337,100,'Abstandschellen,Stahl verzinkt,21-23 mm','733 PG 16',138,NULL,1,60,'OBO (Bettermann)','Stück'),
 (76,36,136,336,130,'Abstandschellen,Stahl verzinkt,19-21 mm','733/M20',136,NULL,1,60,'OBO (Bettermann)','Stück'),
 (77,33,141,339,100,'Abstandschellen,Stahl verzinkt,30-38 mm','733',141,NULL,1,60,'OBO (Bettermann)','Stück'),
 (77,34,139,338,100,'Abstandschellen,Stahl verzinkt,24-29 mm','733/M25 PG 21',139,NULL,1,60,'OBO (Bettermann)','Stück'),
 (77,35,138,337,100,'Abstandschellen,Stahl verzinkt,21-23 mm','733 PG 16',138,NULL,1,60,'OBO (Bettermann)','Stück'),
 (77,36,136,336,130,'Abstandschellen,Stahl verzinkt,19-21 mm','733/M20',136,NULL,1,60,'OBO (Bettermann)','Stück'),
 (78,33,141,339,100,'Abstandschellen,Stahl verzinkt,30-38 mm','733',141,NULL,1,60,'OBO (Bettermann)','Stück'),
 (78,34,139,338,100,'Abstandschellen,Stahl verzinkt,24-29 mm','733/M25 PG 21',139,NULL,1,60,'OBO (Bettermann)','Stück'),
 (78,35,138,337,100,'Abstandschellen,Stahl verzinkt,21-23 mm','733 PG 16',138,NULL,1,60,'OBO (Bettermann)','Stück'),
 (78,36,136,336,130,'Abstandschellen,Stahl verzinkt,19-21 mm','733/M20',136,NULL,1,60,'OBO (Bettermann)','Stück'),
 (79,33,141,339,100,'Abstandschellen,Stahl verzinkt,30-38 mm','733',141,NULL,1,60,'OBO (Bettermann)','Stück'),
 (79,34,139,338,100,'Abstandschellen,Stahl verzinkt,24-29 mm','733/M25 PG 21',139,NULL,1,60,'OBO (Bettermann)','Stück'),
 (79,35,138,337,100,'Abstandschellen,Stahl verzinkt,21-23 mm','733 PG 16',138,NULL,1,60,'OBO (Bettermann)','Stück'),
 (79,36,136,336,130,'Abstandschellen,Stahl verzinkt,19-21 mm','733/M20',136,NULL,1,60,'OBO (Bettermann)','Stück');
/*!40000 ALTER TABLE `bericht_inventur_bestandspackstueck` ENABLE KEYS */;


--
-- Definition of table `bericht_inventur_ebene`
--

DROP TABLE IF EXISTS `bericht_inventur_ebene`;
CREATE TABLE `bericht_inventur_ebene` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `nummer` int(10) unsigned NOT NULL,
  `fk_saeule` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bericht_inventur_ebene`
--

/*!40000 ALTER TABLE `bericht_inventur_ebene` DISABLE KEYS */;
INSERT INTO `bericht_inventur_ebene` (`fk_bericht_id`,`id`,`nummer`,`fk_saeule`) VALUES 
 (76,1,1,1),
 (76,16,22,1),
 (76,125,1,48),
 (76,126,2,48),
 (76,127,3,48),
 (76,128,4,48),
 (76,129,5,48),
 (76,130,6,48),
 (76,131,7,48),
 (76,132,8,48),
 (76,133,1,49),
 (76,134,2,49),
 (76,135,3,49),
 (76,136,4,49),
 (76,137,5,49),
 (76,138,6,49),
 (76,139,7,49),
 (76,140,8,49),
 (76,141,9,49),
 (76,142,1,50),
 (76,143,2,50),
 (76,144,3,50),
 (76,145,4,50),
 (76,146,5,50),
 (76,147,6,50),
 (76,148,7,50),
 (76,149,8,50),
 (76,150,9,50),
 (77,1,1,1),
 (77,16,22,1),
 (77,125,1,48),
 (77,126,2,48),
 (77,127,3,48),
 (77,128,4,48),
 (77,129,5,48),
 (77,130,6,48),
 (77,131,7,48),
 (77,132,8,48),
 (77,133,1,49),
 (77,134,2,49),
 (77,135,3,49),
 (77,136,4,49),
 (77,137,5,49),
 (77,138,6,49),
 (77,139,7,49),
 (77,140,8,49),
 (77,141,9,49),
 (77,142,1,50),
 (77,143,2,50),
 (77,144,3,50),
 (77,145,4,50),
 (77,146,5,50),
 (77,147,6,50),
 (77,148,7,50),
 (77,149,8,50),
 (77,150,9,50),
 (78,1,1,1),
 (78,16,22,1),
 (78,125,1,48),
 (78,126,2,48),
 (78,127,3,48),
 (78,128,4,48),
 (78,129,5,48),
 (78,130,6,48),
 (78,131,7,48),
 (78,132,8,48),
 (78,133,1,49),
 (78,134,2,49),
 (78,135,3,49),
 (78,136,4,49),
 (78,137,5,49),
 (78,138,6,49),
 (78,139,7,49),
 (78,140,8,49),
 (78,141,9,49),
 (78,142,1,50),
 (78,143,2,50),
 (78,144,3,50),
 (78,145,4,50),
 (78,146,5,50),
 (78,147,6,50),
 (78,148,7,50),
 (78,149,8,50),
 (78,150,9,50),
 (79,1,1,1),
 (79,16,22,1),
 (79,125,1,48),
 (79,126,2,48),
 (79,127,3,48),
 (79,128,4,48),
 (79,129,5,48),
 (79,130,6,48),
 (79,131,7,48),
 (79,132,8,48),
 (79,133,1,49),
 (79,134,2,49),
 (79,135,3,49),
 (79,136,4,49),
 (79,137,5,49),
 (79,138,6,49),
 (79,139,7,49),
 (79,140,8,49),
 (79,141,9,49),
 (79,142,1,50),
 (79,143,2,50),
 (79,144,3,50),
 (79,145,4,50),
 (79,146,5,50),
 (79,147,6,50),
 (79,148,7,50),
 (79,149,8,50),
 (79,150,9,50);
/*!40000 ALTER TABLE `bericht_inventur_ebene` ENABLE KEYS */;


--
-- Definition of table `bericht_inventur_etage`
--

DROP TABLE IF EXISTS `bericht_inventur_etage`;
CREATE TABLE `bericht_inventur_etage` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `name` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `fk_halle` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bericht_inventur_etage`
--

/*!40000 ALTER TABLE `bericht_inventur_etage` DISABLE KEYS */;
INSERT INTO `bericht_inventur_etage` (`fk_bericht_id`,`id`,`name`,`fk_halle`) VALUES 
 (76,1,'Lager über dem E.Werkstatt',76),
 (76,5,'E.Werkstatt',76),
 (76,13,'Obergeschoss E.Werkstatt',76),
 (76,14,'Schraubenlager',76),
 (77,1,'Lager über dem E.Werkstatt',77),
 (77,5,'E.Werkstatt',77),
 (77,13,'Obergeschoss E.Werkstatt',77),
 (77,14,'Schraubenlager',77),
 (78,1,'Lager über dem E.Werkstatt',78),
 (78,5,'E.Werkstatt',78),
 (78,13,'Obergeschoss E.Werkstatt',78),
 (78,14,'Schraubenlager',78),
 (79,1,'Lager über dem E.Werkstatt',79),
 (79,5,'E.Werkstatt',79),
 (79,13,'Obergeschoss E.Werkstatt',79),
 (79,14,'Schraubenlager',79);
/*!40000 ALTER TABLE `bericht_inventur_etage` ENABLE KEYS */;


--
-- Definition of table `bericht_inventur_halle`
--

DROP TABLE IF EXISTS `bericht_inventur_halle`;
CREATE TABLE `bericht_inventur_halle` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `name` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bericht_inventur_halle`
--

/*!40000 ALTER TABLE `bericht_inventur_halle` DISABLE KEYS */;
INSERT INTO `bericht_inventur_halle` (`fk_bericht_id`,`id`,`name`) VALUES 
 (1,1,'Schlosserei'),
 (76,1,'Schlosserei'),
 (77,1,'Schlosserei'),
 (78,1,'Schlosserei'),
 (79,1,'Schlosserei');
/*!40000 ALTER TABLE `bericht_inventur_halle` ENABLE KEYS */;


--
-- Definition of table `bericht_inventur_position`
--

DROP TABLE IF EXISTS `bericht_inventur_position`;
CREATE TABLE `bericht_inventur_position` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `nummer` int(10) unsigned NOT NULL,
  `fk_ebene` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bericht_inventur_position`
--

/*!40000 ALTER TABLE `bericht_inventur_position` DISABLE KEYS */;
INSERT INTO `bericht_inventur_position` (`fk_bericht_id`,`id`,`nummer`,`fk_ebene`) VALUES 
 (76,1,1,1),
 (76,2,2,1),
 (76,5,3,1),
 (76,6,4,1),
 (76,336,1,126),
 (76,337,2,126),
 (76,338,3,126),
 (76,339,4,126),
 (76,340,5,126),
 (76,341,6,126),
 (77,1,1,1),
 (77,2,2,1),
 (77,5,3,1),
 (77,6,4,1),
 (77,336,1,126),
 (77,337,2,126),
 (77,338,3,126),
 (77,339,4,126),
 (77,340,5,126),
 (77,341,6,126),
 (78,1,1,1),
 (78,2,2,1),
 (78,5,3,1),
 (78,6,4,1),
 (78,336,1,126),
 (78,337,2,126),
 (78,338,3,126),
 (78,339,4,126),
 (78,340,5,126),
 (78,341,6,126),
 (79,1,1,1),
 (79,2,2,1),
 (79,5,3,1),
 (79,6,4,1),
 (79,336,1,126),
 (79,337,2,126),
 (79,338,3,126),
 (79,339,4,126),
 (79,340,5,126),
 (79,341,6,126);
/*!40000 ALTER TABLE `bericht_inventur_position` ENABLE KEYS */;


--
-- Definition of table `bericht_inventur_saeule`
--

DROP TABLE IF EXISTS `bericht_inventur_saeule`;
CREATE TABLE `bericht_inventur_saeule` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `nummer` int(10) unsigned NOT NULL,
  `fk_zeile` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bericht_inventur_saeule`
--

/*!40000 ALTER TABLE `bericht_inventur_saeule` DISABLE KEYS */;
INSERT INTO `bericht_inventur_saeule` (`fk_bericht_id`,`id`,`nummer`,`fk_zeile`) VALUES 
 (76,1,1,1),
 (76,2,2,1),
 (76,3,3,1),
 (76,4,1,2),
 (76,5,2,2),
 (76,48,1,3),
 (76,49,2,3),
 (76,50,3,3),
 (77,1,1,1),
 (77,2,2,1),
 (77,3,3,1),
 (77,4,1,2),
 (77,5,2,2),
 (77,48,1,3),
 (77,49,2,3),
 (77,50,3,3),
 (78,1,1,1),
 (78,2,2,1),
 (78,3,3,1),
 (78,4,1,2),
 (78,5,2,2),
 (78,48,1,3),
 (78,49,2,3),
 (78,50,3,3),
 (79,1,1,1),
 (79,2,2,1),
 (79,3,3,1),
 (79,4,1,2),
 (79,5,2,2),
 (79,48,1,3),
 (79,49,2,3),
 (79,50,3,3);
/*!40000 ALTER TABLE `bericht_inventur_saeule` ENABLE KEYS */;


--
-- Definition of table `bericht_inventur_zeile`
--

DROP TABLE IF EXISTS `bericht_inventur_zeile`;
CREATE TABLE `bericht_inventur_zeile` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `nummer` int(10) unsigned NOT NULL,
  `fk_halle` int(10) unsigned NOT NULL,
  `fk_etage` int(10) unsigned DEFAULT NULL,
  `fk_abteilung` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bericht_inventur_zeile`
--

/*!40000 ALTER TABLE `bericht_inventur_zeile` DISABLE KEYS */;
INSERT INTO `bericht_inventur_zeile` (`fk_bericht_id`,`id`,`nummer`,`fk_halle`,`fk_etage`,`fk_abteilung`) VALUES 
 (76,1,7,1,1,3),
 (76,2,2,1,1,3),
 (76,3,1,1,5,3),
 (76,7,5,1,14,1),
 (77,1,7,1,1,3),
 (77,2,2,1,1,3),
 (77,3,1,1,5,3),
 (77,7,5,1,14,1),
 (78,1,7,1,1,3),
 (78,2,2,1,1,3),
 (78,3,1,1,5,3),
 (78,7,5,1,14,1),
 (79,1,7,1,1,3),
 (79,2,2,1,1,3),
 (79,3,1,1,5,3),
 (79,7,5,1,14,1);
/*!40000 ALTER TABLE `bericht_inventur_zeile` ENABLE KEYS */;


--
-- Definition of table `bestandspackstueck`
--

DROP TABLE IF EXISTS `bestandspackstueck`;
CREATE TABLE `bestandspackstueck` (
  `id` int(10) unsigned NOT NULL,
  `erfassungsbenutzer` int(10) unsigned DEFAULT NULL,
  `erfassungsdatum` datetime DEFAULT NULL,
  `aenderungsbenutzer` int(10) unsigned DEFAULT NULL,
  `aenderungsdatum` datetime DEFAULT NULL,
  `fk_artikel` int(10) unsigned NOT NULL,
  `fk_position` int(10) unsigned DEFAULT NULL,
  `menge` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bestandspackstueck_artikel` (`fk_artikel`),
  KEY `FK_bestandpackstueck_position` (`fk_position`),
  CONSTRAINT `FK_bestandpackstueck_position` FOREIGN KEY (`fk_position`) REFERENCES `position` (`id`),
  CONSTRAINT `FK_bestandspackstueck_artikel` FOREIGN KEY (`fk_artikel`) REFERENCES `artikel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bestandspackstueck`
--

/*!40000 ALTER TABLE `bestandspackstueck` DISABLE KEYS */;
INSERT INTO `bestandspackstueck` (`id`,`erfassungsbenutzer`,`erfassungsdatum`,`aenderungsbenutzer`,`aenderungsdatum`,`fk_artikel`,`fk_position`,`menge`) VALUES 
 (7,0,'2011-01-18 00:00:00',0,'2011-01-18 00:00:00',72,18,1),
 (13,0,'2011-01-18 00:00:00',0,'2011-01-18 00:00:00',10,19,1),
 (33,0,'2011-01-28 00:00:00',0,'2011-01-28 00:00:00',141,339,100),
 (34,0,'2011-01-28 00:00:00',0,'2011-01-28 00:00:00',139,338,100),
 (35,0,'2011-01-28 00:00:00',0,'2011-01-28 00:00:00',138,337,100),
 (36,0,'2011-01-28 00:00:00',0,'2011-01-28 00:00:00',136,336,130),
 (48,0,'2011-01-28 00:00:00',0,'2011-01-28 00:00:00',143,26,1),
 (49,0,'2011-01-28 00:00:00',0,'2011-01-28 00:00:00',143,26,1),
 (51,0,'2011-01-29 00:00:00',0,'2011-01-29 00:00:00',143,18,1);
/*!40000 ALTER TABLE `bestandspackstueck` ENABLE KEYS */;


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
 (1046,'2010-05-14 00:00:00','E',5,NULL,NULL,'schlosserei@ke-ag.de'),
 (1048,'2010-05-14 00:00:00','E',5,NULL,NULL,''),
 (1049,'2010-05-14 00:00:00','E',5,NULL,NULL,'thurner@ke-ag.de'),
 (1051,'2010-05-14 00:00:00','O',5,NULL,NULL,'elektrowerkstatt@ke-ag.de'),
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
 (1088,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1089,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1090,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1091,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1092,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1093,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1094,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1095,'2010-06-11 00:00:00','L',5,NULL,NULL,'thurner@ke-ag.de'),
 (1096,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1097,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'schlosserei@ke-ag.de'),
 (1098,'2010-06-11 00:00:00','E',5,NULL,NULL,'thurner@ke-ag.de'),
 (1099,'2010-06-11 00:00:00','E',5,NULL,NULL,'thurner@ke-ag.de'),
 (1100,'2010-06-11 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1102,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),
 (1103,'2010-06-11 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1104,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1105,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1107,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'elektrowerkstatt@ke-ag.de'),
 (1108,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1109,'2010-06-11 00:00:00','L',5,NULL,NULL,'thurner@ke-ag.de'),
 (1112,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1113,'2010-06-11 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1114,'2010-06-11 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1115,'2010-06-11 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1116,'2010-06-11 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1122,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1123,'2010-06-11 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1124,'2010-06-11 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1126,'2010-06-12 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1127,'2010-06-12 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1128,'2010-06-12 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1129,'2010-06-12 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1130,'2010-06-12 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1131,'2010-06-12 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1132,'2010-06-12 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1133,'2010-06-12 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1140,'2010-06-12 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1141,'2010-06-12 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1142,'2010-06-25 00:00:00','E',NULL,NULL,NULL,'email@email.de23'),
 (1143,'2010-06-25 00:00:00','E',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1145,'2010-07-02 00:00:00','E',5,NULL,NULL,'email@emai5l.de'),
 (1146,'2010-07-03 00:00:00','E',6,NULL,NULL,'thurner@ke-ag.de'),
 (1147,'2010-07-04 00:00:00','L',NULL,NULL,NULL,'thurner@ke-ag.de'),
 (1148,'2010-07-04 00:00:00','E',5,NULL,NULL,'thurner@ke-ag.de'),
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
 (159,79,2,10,12,'O',2,1,0,'','',0),
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
 (239,79,3,10,20,'O',2,1,0,'','',0),
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
 (2074,1046,17,7,19,'O',NULL,1,0,'','',0),
 (2076,1048,7,10,19,'O',NULL,3,0,'','',0),
 (2091,1051,7,10,19,'O',NULL,3,0,'','',0),
 (2093,1052,7,10,19,'O',NULL,3,0,'','',0),
 (2095,1053,7,10,19,'O',NULL,3,0,'','',0),
 (2097,1054,7,15,19,'O',NULL,3,0,'','',0),
 (2099,1054,6,10,19,'O',NULL,3,0,'','',0),
 (2100,1057,6,10,19,'O',NULL,3,0,'','',0),
 (2117,1098,1,1,53,'O',4,1,2,'712123','5 - 123',0),
 (2129,1095,1,1,23,'O',NULL,1,0,'','',0),
 (2130,1095,7,10,23,'O',NULL,3,0,'','',0),
 (2134,1112,105,1,53,'O',NULL,1,0,'','',0),
 (2136,1122,4,2,23,'O',NULL,3,0,'','',0),
 (2137,1122,2,2,23,'O',NULL,2,0,'','',0),
 (2138,1122,3,3,23,'O',NULL,3,0,'','',0),
 (2140,1124,1,1,23,'O',NULL,1,2,'712123','5 - 123',0),
 (2141,1124,4,4,23,'O',NULL,3,0,'','',0),
 (2142,1089,5,1,23,'O',NULL,3,0,'','',0),
 (2143,1126,1,1,23,'O',NULL,1,0,'','',0),
 (2144,1127,1,1,23,'O',1,1,1,'123123  / b12','3 / 152',0),
 (2145,1128,7,10,23,'O',4,3,0,'','',0),
 (2146,1129,7,10,23,'O',NULL,3,0,'','',0),
 (2150,1142,1,1,25,'O',4,1,1,'123123  / b12','3 / 152',0),
 (2151,1143,1,1,25,'O',5,1,2,'712123','5 - 123',0),
 (2152,1143,5,5,25,'O',1,3,0,'','',0),
 (2153,1145,7,10,26,'O',NULL,2,0,'','',0),
 (2154,1145,1,1,26,'O',NULL,3,2,'712123','5 - 123',0),
 (2155,1146,1,1,26,'O',NULL,1,0,'','',0),
 (2156,1146,4,4,26,'O',NULL,3,0,'','',0),
 (2157,1148,71,2,33,'O',NULL,1,0,'','',0),
 (2158,1146,3,10,28,'O',NULL,3,0,'','',0),
 (2167,1142,107,1,48,'O',3,1,0,'','',0),
 (2169,1126,3,5,48,'O',4,1,2,'144433','3/543',0),
 (2170,1141,2,5,48,'O',2,1,2,'121111','1/543',0),
 (2171,1141,52,10,48,'O',5,1,1,'???','',0),
 (2172,1141,53,10,48,'O',1,1,1,'???','',0),
 (2173,1098,107,1,48,'O',2,1,0,'','',0),
 (2176,1140,118,10,49,'O',NULL,1,2,'2606490','',0),
 (2177,1099,71,1,49,'O',3,1,0,'','',0),
 (2178,1092,72,1,49,'O',1,1,0,'','',0),
 (2179,1088,118,10,49,'O',NULL,1,2,'2606490','',0),
 (2180,1090,2,5,49,'O',4,1,2,'121111','1/543',0),
 (2181,1091,81,5,49,'O',5,1,0,'','',0),
 (2182,1091,52,10,49,'O',3,1,1,'???','',0),
 (2183,1093,96,5,49,'O',1,1,0,'','',0),
 (2184,1096,17,6,49,'O',5,1,0,'','',0),
 (2185,1112,76,1,49,'O',4,1,0,'','',0),
 (2186,1094,17,1,49,'O',NULL,1,0,'','',0),
 (2187,1097,80,7,49,'O',1,1,0,'','',0),
 (2188,1102,80,6,49,'O',4,1,0,'','',0),
 (2189,1108,107,1,49,'O',NULL,1,0,'','',0),
 (2190,1048,18,7,49,'O',NULL,1,0,'','',0),
 (2191,1105,117,50,49,'O',2,1,2,'2842906','',0),
 (2192,1049,117,50,49,'O',1,1,2,'2842906','',0),
 (2193,1104,107,1,4,'O',NULL,1,0,'','',0);
/*!40000 ALTER TABLE `bestellanforderungsposition` ENABLE KEYS */;


--
-- Definition of table `bestellung`
--

DROP TABLE IF EXISTS `bestellung`;
CREATE TABLE `bestellung` (
  `id` int(10) unsigned NOT NULL,
  `erstellungsdatum` datetime NOT NULL,
  `status` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `fk_herstellerLieferant` int(10) unsigned DEFAULT NULL,
  `fk_benutzerAbsender` int(10) unsigned DEFAULT NULL,
  `fk_benutzerAnnehmer` int(10) unsigned DEFAULT NULL,
  `email_ba_empfaenger` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_anforderung_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bestellung_herstellerLieferant` (`fk_herstellerLieferant`),
  KEY `FK_bestellung_benutzerAbsender` (`fk_benutzerAbsender`),
  KEY `FK_bestellung_benutzerAnnehmer` (`fk_benutzerAnnehmer`),
  KEY `FK_bestellung_anforderung` (`fk_anforderung_id`),
  CONSTRAINT `FK_bestellung_anforderung` FOREIGN KEY (`fk_anforderung_id`) REFERENCES `bestellanforderung` (`id`),
  CONSTRAINT `FK_bestellung_benutzerAbsender` FOREIGN KEY (`fk_benutzerAbsender`) REFERENCES `benutzer` (`id`),
  CONSTRAINT `FK_bestellung_benutzerAnnehmer` FOREIGN KEY (`fk_benutzerAnnehmer`) REFERENCES `benutzer` (`id`),
  CONSTRAINT `FK_bestellung_herstellerLieferant` FOREIGN KEY (`fk_herstellerLieferant`) REFERENCES `herstellerlieferant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bestellung`
--

/*!40000 ALTER TABLE `bestellung` DISABLE KEYS */;
INSERT INTO `bestellung` (`id`,`erstellungsdatum`,`status`,`fk_herstellerLieferant`,`fk_benutzerAbsender`,`fk_benutzerAnnehmer`,`email_ba_empfaenger`,`fk_anforderung_id`) VALUES 
 (2,'2010-09-17 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de',NULL),
 (1154,'2010-09-17 00:00:00','O',NULL,NULL,NULL,'thurner@ke-ag.de',NULL);
/*!40000 ALTER TABLE `bestellung` ENABLE KEYS */;


--
-- Definition of table `bestellungsposition`
--

DROP TABLE IF EXISTS `bestellungsposition`;
CREATE TABLE `bestellungsposition` (
  `id` int(10) unsigned NOT NULL,
  `fk_bestellung` int(10) unsigned NOT NULL,
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
  UNIQUE KEY `bestellungsposition_bestellung_artikel` (`fk_bestellung`,`fk_artikel`) USING BTREE,
  KEY `FK_bestellungsposition_artikel` (`fk_artikel`) USING BTREE,
  KEY `FK_bestellungsposition_kostenstelle` (`fk_kostenstelle`) USING BTREE,
  KEY `FK_bestellungsposition_mengeneinheit` (`fk_mengeneinheit`) USING BTREE,
  CONSTRAINT `FK_bestellungsposition_artikel` FOREIGN KEY (`fk_artikel`) REFERENCES `artikel` (`id`),
  CONSTRAINT `FK_bestellungsposition_bestellanforderung` FOREIGN KEY (`fk_bestellung`) REFERENCES `bestellung` (`id`),
  CONSTRAINT `FK_bestellungsposition_kostenstelle` FOREIGN KEY (`fk_kostenstelle`) REFERENCES `kostenstelle` (`id`) ON UPDATE NO ACTION,
  CONSTRAINT `FK_bestellungsposition_mengeneinheit` FOREIGN KEY (`fk_mengeneinheit`) REFERENCES `mengeneinheit` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bestellungsposition`
--

/*!40000 ALTER TABLE `bestellungsposition` DISABLE KEYS */;
INSERT INTO `bestellungsposition` (`id`,`fk_bestellung`,`fk_artikel`,`bestellmenge`,`liefertermin`,`status`,`fk_kostenstelle`,`fk_mengeneinheit`,`fk_katalog`,`lieferantenbestellnummer`,`katalogseite`,`katalogpreis`) VALUES 
 (2,2,16,1,37,'O',NULL,1,0,'','',0),
 (3,2,25,2,37,'O',NULL,1,0,'','',0);
/*!40000 ALTER TABLE `bestellungsposition` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ebene`
--

/*!40000 ALTER TABLE `ebene` DISABLE KEYS */;
INSERT INTO `ebene` (`id`,`nummer`,`fk_saeule`) VALUES 
 (1,1,1),
 (3,1,11),
 (6,1,12),
 (13,1,13),
 (17,1,14),
 (21,1,15),
 (25,1,16),
 (26,1,17),
 (30,1,18),
 (34,1,19),
 (38,1,20),
 (42,1,21),
 (46,1,22),
 (50,1,23),
 (54,1,24),
 (58,1,25),
 (62,1,26),
 (67,1,27),
 (73,1,29),
 (77,1,30),
 (81,1,31),
 (85,1,32),
 (89,1,33),
 (93,1,34),
 (97,1,35),
 (102,1,37),
 (107,1,38),
 (112,1,39),
 (122,1,40),
 (117,1,43),
 (123,1,45),
 (125,1,48),
 (133,1,49),
 (142,1,50),
 (2,2,11),
 (7,2,12),
 (12,2,13),
 (66,2,14),
 (20,2,15),
 (23,2,16),
 (27,2,17),
 (31,2,18),
 (35,2,19),
 (39,2,20),
 (43,2,21),
 (47,2,22),
 (51,2,23),
 (55,2,24),
 (59,2,25),
 (63,2,26),
 (68,2,27),
 (74,2,29),
 (78,2,30),
 (82,2,31),
 (86,2,32),
 (90,2,33),
 (94,2,34),
 (98,2,35),
 (103,2,37),
 (108,2,38),
 (113,2,39),
 (118,2,43),
 (126,2,48),
 (134,2,49),
 (143,2,50),
 (4,3,11),
 (8,3,12),
 (11,3,13),
 (15,3,14),
 (19,3,15),
 (24,3,16),
 (28,3,17),
 (32,3,18),
 (36,3,19),
 (40,3,20),
 (44,3,21),
 (48,3,22),
 (52,3,23),
 (56,3,24),
 (60,3,25),
 (64,3,26),
 (69,3,27),
 (75,3,29),
 (79,3,30),
 (83,3,31),
 (87,3,32),
 (91,3,33),
 (95,3,34),
 (99,3,35),
 (104,3,37),
 (109,3,38),
 (114,3,39),
 (119,3,43),
 (127,3,48),
 (135,3,49),
 (144,3,50),
 (5,4,11),
 (9,4,12),
 (10,4,13),
 (14,4,14),
 (18,4,15),
 (22,4,16),
 (29,4,17),
 (33,4,18),
 (37,4,19),
 (41,4,20),
 (45,4,21),
 (49,4,22),
 (53,4,23),
 (57,4,24),
 (61,4,25),
 (65,4,26),
 (70,4,27),
 (76,4,29),
 (80,4,30),
 (84,4,31),
 (88,4,32),
 (92,4,33),
 (96,4,34),
 (100,4,35),
 (105,4,37),
 (110,4,38),
 (115,4,39),
 (120,4,43),
 (128,4,48),
 (136,4,49),
 (145,4,50),
 (71,5,27),
 (101,5,35),
 (106,5,37),
 (111,5,38),
 (116,5,39),
 (129,5,48),
 (137,5,49),
 (146,5,50),
 (72,6,27),
 (130,6,48),
 (138,6,49),
 (147,6,50),
 (131,7,48),
 (139,7,49),
 (148,7,50),
 (132,8,48),
 (140,8,49),
 (149,8,50),
 (141,9,49),
 (150,9,50),
 (16,22,1);
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
  `fk_benutzerEntnehmer` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`,`fk_benutzerEntnehmer`) USING BTREE,
  KEY `FK_entnahme_benutzer` (`fk_benutzerEntnehmer`) USING BTREE,
  CONSTRAINT `FK_entnahme_benutzerEntnehmer` FOREIGN KEY (`fk_benutzerEntnehmer`) REFERENCES `benutzer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `entnahme`
--

/*!40000 ALTER TABLE `entnahme` DISABLE KEYS */;
INSERT INTO `entnahme` (`id`,`erstellungsdatum`,`status`,`fk_benutzerEntnehmer`) VALUES 
 (1,'2010-09-20 10:00:00','O',14),
 (2,'2010-09-20 10:00:00','O',17),
 (3,'2010-09-20 10:00:00','F',18);
/*!40000 ALTER TABLE `entnahme` ENABLE KEYS */;


--
-- Definition of table `entnahmeposition`
--

DROP TABLE IF EXISTS `entnahmeposition`;
CREATE TABLE `entnahmeposition` (
  `id` int(10) unsigned NOT NULL,
  `fk_entnahme` int(10) unsigned NOT NULL,
  `fk_artikel` int(10) unsigned NOT NULL,
  `menge` int(10) unsigned NOT NULL,
  `fk_mengeeinheit` int(10) unsigned NOT NULL,
  `fk_halle` int(10) unsigned NOT NULL,
  `fk_zeile` int(10) unsigned NOT NULL,
  `fk_saeule` int(10) unsigned NOT NULL,
  `fk_ebene` int(10) unsigned NOT NULL,
  `fk_position` int(10) unsigned NOT NULL,
  `status` varchar(1) NOT NULL,
  `fk_etage` int(10) unsigned DEFAULT NULL,
  `fk_kostenstelle` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_entnahmeposition_entnahme` (`fk_entnahme`),
  KEY `FK_entnahmeposition_artikel` (`fk_artikel`),
  KEY `FK_entnahmeposition_mengeeinheit` (`fk_mengeeinheit`),
  KEY `FK_entnahmeposition_halle` (`fk_halle`),
  KEY `FK_entnahmeposition_zeile` (`fk_zeile`),
  KEY `FK_entnahmeposition_saeule` (`fk_saeule`),
  KEY `FK_entnahmeposition_ebene` (`fk_ebene`),
  KEY `FK_entnahmeposition_position` (`fk_position`),
  KEY `FK_entnahmeposition_etage` (`fk_etage`),
  KEY `FK_entnahmeposition_kostenstelle` (`fk_kostenstelle`),
  CONSTRAINT `FK_entnahmeposition_artikel` FOREIGN KEY (`fk_artikel`) REFERENCES `artikel` (`id`),
  CONSTRAINT `FK_entnahmeposition_ebene` FOREIGN KEY (`fk_ebene`) REFERENCES `ebene` (`id`),
  CONSTRAINT `FK_entnahmeposition_entnahme` FOREIGN KEY (`fk_entnahme`) REFERENCES `entnahme` (`id`),
  CONSTRAINT `FK_entnahmeposition_etage` FOREIGN KEY (`fk_etage`) REFERENCES `etage` (`id`),
  CONSTRAINT `FK_entnahmeposition_halle` FOREIGN KEY (`fk_halle`) REFERENCES `halle` (`id`),
  CONSTRAINT `FK_entnahmeposition_kostenstelle` FOREIGN KEY (`fk_kostenstelle`) REFERENCES `kostenstelle` (`id`),
  CONSTRAINT `FK_entnahmeposition_mengeeinheit` FOREIGN KEY (`fk_mengeeinheit`) REFERENCES `mengeneinheit` (`id`),
  CONSTRAINT `FK_entnahmeposition_position` FOREIGN KEY (`fk_position`) REFERENCES `position` (`id`),
  CONSTRAINT `FK_entnahmeposition_saeule` FOREIGN KEY (`fk_saeule`) REFERENCES `saeule` (`id`),
  CONSTRAINT `FK_entnahmeposition_zeile` FOREIGN KEY (`fk_zeile`) REFERENCES `zeile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `entnahmeposition`
--

/*!40000 ALTER TABLE `entnahmeposition` DISABLE KEYS */;
INSERT INTO `entnahmeposition` (`id`,`fk_entnahme`,`fk_artikel`,`menge`,`fk_mengeeinheit`,`fk_halle`,`fk_zeile`,`fk_saeule`,`fk_ebene`,`fk_position`,`status`,`fk_etage`,`fk_kostenstelle`) VALUES 
 (43,1,86,1,1,1,1,1,1,1,'o',NULL,NULL),
 (44,1,88,1,1,1,1,1,1,2,'o',NULL,NULL);
/*!40000 ALTER TABLE `entnahmeposition` ENABLE KEYS */;


--
-- Definition of table `etage`
--

DROP TABLE IF EXISTS `etage`;
CREATE TABLE `etage` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(35) NOT NULL,
  `fk_halle` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Ietage_name_eindeutig` (`name`),
  KEY `FK_etage_halle` (`fk_halle`),
  CONSTRAINT `FK_etage_halle` FOREIGN KEY (`fk_halle`) REFERENCES `halle` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `etage`
--

/*!40000 ALTER TABLE `etage` DISABLE KEYS */;
INSERT INTO `etage` (`id`,`name`,`fk_halle`) VALUES 
 (1,'Lager über dem E.Werkstatt',1),
 (3,'Obergeschoss E.Ofen',7),
 (4,'E.OfenKeller',7),
 (5,'E.Werkstatt',1),
 (6,'SchreinereiKeller',13),
 (7,'PlattenlagerKeller',12),
 (8,'Bierkeller',12),
 (9,'HeizungKeller',12),
 (11,'Eisenlager',15),
 (12,'KranersatzzeileLager',15),
 (13,'Obergeschoss E.Werkstatt',1),
 (14,'Schraubenlager',1),
 (15,'Ofenleitstand',7);
/*!40000 ALTER TABLE `etage` ENABLE KEYS */;


--
-- Definition of table `halle`
--

DROP TABLE IF EXISTS `halle`;
CREATE TABLE `halle` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(35) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `halle_name_eindeutig` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `halle`
--

/*!40000 ALTER TABLE `halle` DISABLE KEYS */;
INSERT INTO `halle` (`id`,`name`) VALUES 
 (8,'Automatenhalle'),
 (17,'Bergerhalle'),
 (7,'E.OfenHalle'),
 (11,'Gattierungshalle'),
 (2,'Gießhalle 1'),
 (3,'Gießhalle 2'),
 (9,'Grosse Kernmacherei'),
 (24,'GUT-Halle'),
 (10,'Kleine Kernmacherei'),
 (23,'Kupolofenhall'),
 (4,'Maschinenkernmacherei'),
 (15,'Modellager'),
 (18,'Möldnerhalle A'),
 (19,'Möldnerhalle B'),
 (20,'Möldnerhalle C'),
 (21,'Möldnerhalle D'),
 (6,'Obere Putzerei'),
 (1,'Schlosserei'),
 (13,'Schreinerei'),
 (12,'Sozialgebaeude'),
 (22,'Stempeluhrhalle'),
 (16,'Taucherei'),
 (5,'Untere Putzerei'),
 (14,'Versand');
/*!40000 ALTER TABLE `halle` ENABLE KEYS */;


--
-- Definition of table `herstellerlieferant`
--

DROP TABLE IF EXISTS `herstellerlieferant`;
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

--
-- Dumping data for table `herstellerlieferant`
--

/*!40000 ALTER TABLE `herstellerlieferant` DISABLE KEYS */;
INSERT INTO `herstellerlieferant` (`id`,`name`,`adresse_land`,`adresse_plz`,`adresse_stadt`,`adresse_strasse`,`telefon`,`fax`,`email`,`ansprechpartner`) VALUES 
 (1,'Siemens','DE','87434','stadt','strasse','tel','fax','email','and'),
 (2,'Moeller','DE','?','?','?','?','?','?','?'),
 (3,'Festo','DE','87439','Augsburg','Eichendorffweg ','0831 / 1102','0831 / 1103 ','email@email.de23','Ansprechpartner von Hersteller 2'),
 (4,'Schneider','DE','87478','Muenchen','Eichendorffweg 16','0831 / 1102','0831 / 1103 ','email@email.de23','Ansprechpartner von Hersteller 4'),
 (5,'Hagemeyer','DE','87600','Kaufbeuren','Leo-Lutz-Str.2','08331/950441','08331/950460','Melanie.Katheininger@Hagemeyerce.com','Fr.Katheininger'),
 (6,'Schaller','DE','87436','Kempten (Ursulasried','Eichendorffweg 6','0831 / 110 6','0831 / 1103 6','email@emai6l.de','Ansprechpartner von Lieferant 6'),
 (7,'Demag','DE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (8,'WTC','DE','asfg','asf','asf','asfd','asfdasfd','afd','H.Bröcker'),
 (9,'Bauer','DE','asfd','afd','asfd','asf','asf','asf','asf'),
 (10,'Nord','DE','a','asf','asfd','as','asf','asf','a'),
 (11,'Elektrim Fritz Obers','DE','86720','Nödlingen',NULL,NULL,NULL,NULL,NULL),
 (12,'Schäfer','DE','asfd','asfd','asfd','asfd','asfd','asfd','asf'),
 (13,'Busch-Jäger','DE','safd','asfd','afd','afd','','',''),
 (14,'Norka','DE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (15,'Bals','DE','asfd','af','asfd','asf','asf','asfd','asf'),
 (16,'Fein','DE','73529','SchwäbischGmünd-Barg','Hans-Fein-Str.81',NULL,'07173/183823',NULL,'Fr.Zondler'),
 (18,'Omron','Schweiz ?','?','?','?','?','?','?','?'),
 (32,'SAWA','Schweiz ?','?','?','?','?','?','','?'),
 (33,'Rüetschi','Schweiz','5040','Schöftland','?','?','?','?','?'),
 (34,'Unitec AG','Schweiz','?','Kloten','','','','',''),
 (35,'Bachofen','Schweiz?','8610','Uster','Ackerstr.42','01 940 7001','','',''),
 (36,'Roth+Co','Schweiz?',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (37,'Bürkert','DE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (38,'Schneider Electric','DE','40880','Ratingen','Gothauer Strasse 29','02102404-0','02102404-9256','www.schneider-electric.de',NULL),
 (39,'R.Pöppel GmbH & Co.K','DE','87700','Memmingen','Alpenstr.45','08331-9559-0','08331-0550-69','?','? Fein-Maschinen'),
 (40,'Endress+Hauser Messtechnik ','DE','81249','München','ClaritaBernhardStr25','089-84009-117','089-84009-133','janet.curmann@de.endress.com','Janet Curmann'),
 (42,'R.Pöppel GmbH','DE','87700','Memmingen','Alpenstr.45','08331-9559-0','08331-0550-69','',''),
 (43,'Elektra','','','','','','','',''),
 (44,'Klauke','','','','','','','',''),
 (45,'Eltako','','','','','','','',''),
 (46,'Georg Fischer GmbH','DE','73095','Albershausen','Daimlerstr.6','07161-302-0','07161-302-111','info@georgfischer.de',''),
 (48,'Stemmann-Technik GmbH','DE','48465','Schüttdorf','Niedersachsenstr.2','05923-810','05923-81100','info@stemmann.de / j.lammereing@stemmann.de','Jurgen Lammering'),
 (51,'Conrad Elektronic SE','','92241','Hirschau','Klaus-Conradstr.1','09604 40-8988','09604 40-8936','www.conrad.biz',''),
 (52,'Robert Bosch GmbH','','37589','Kalefeld/Willershaus','Zur Lunne 2','01801 505010','01801 505011','Fachhandel@de.bosch.com',''),
 (53,'Hörburger Control Systems','DE','87448','Waltenhofen','Gewerbestr.5','0831 52241-0','0831 12918','info@hoerburger.de',''),
 (54,'Phoenix','','','','','','','',''),
 (55,'Hensel','','','','','','','',''),
 (57,'Helerma','?','?','?','?','?','?','?','?'),
 (58,'Newlec','','','','','','','',''),
 (59,'Wago','','','','','','','',''),
 (60,'OBO (Bettermann)','','','','','','','','');
/*!40000 ALTER TABLE `herstellerlieferant` ENABLE KEYS */;


--
-- Definition of table `herstellerlieferantkatalog`
--

DROP TABLE IF EXISTS `herstellerlieferantkatalog`;
CREATE TABLE `herstellerlieferantkatalog` (
  `fk_herstellerlieferant` int(10) unsigned NOT NULL,
  `fk_katalog` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_herstellerlieferant`,`fk_katalog`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `herstellerlieferantkatalog`
--

/*!40000 ALTER TABLE `herstellerlieferantkatalog` DISABLE KEYS */;
INSERT INTO `herstellerlieferantkatalog` (`fk_herstellerlieferant`,`fk_katalog`) VALUES 
 (1,1),
 (15,4),
 (38,5);
/*!40000 ALTER TABLE `herstellerlieferantkatalog` ENABLE KEYS */;


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
 (4,'Vesto 2008'),
 (5,'Telemecanique 11/200'),
 (6,'Stemmann-Technik');
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
 (3,2,'144433','3/543',1),
 (26,1,'46966444','',NULL),
 (27,1,'46952544','',NULL),
 (33,1,'6ES7 315-2AG10-0AB0','',NULL),
 (34,1,'6GK7 343-1CX10-OXEO','',NULL),
 (35,1,'6ES7 321-1BL00-OAAO','',NULL),
 (36,1,'6ES7 322-1BL01-OAAO','',NULL),
 (37,1,'6ES7 322-1BH01-OAAO','',NULL),
 (38,1,'6ES7 153-1AA03-OXBO','',NULL),
 (39,1,'6ES7 321-1BH02-OAAO','',NULL),
 (40,1,'6ES7 331-7KF02-OABO','',NULL),
 (41,1,'6ES7 313-6CEOO-OABO','',NULL),
 (42,1,'6ES7 323-1BLOO-OAAO','',NULL),
 (43,1,'6ES7 331-1KFOO-OABO','',NULL),
 (44,1,'6ES7 312-1AD10-OABO','',NULL),
 (45,1,'6ES7 334-OCE01-OAAO','',NULL),
 (46,1,'MP 270 ????????????','',NULL),
 (47,1,'OP 3 ??????????????','',NULL),
 (48,1,'6AV6 641-OCA01-OAXO','',NULL),
 (49,1,'6ES7 332-5HBO1-OABO','',NULL),
 (50,1,'6ES7 307-1EA00-OAAO','',NULL),
 (51,1,'???','',NULL),
 (52,1,'???','',NULL),
 (53,1,'???','',NULL),
 (54,1,'2029100','5/1',NULL),
 (55,1,'1004062','',NULL),
 (56,1,'2068045','8/9',NULL),
 (57,1,'???','',NULL),
 (58,1,'ß???','',NULL),
 (59,1,'???','',NULL),
 (60,1,'2632869','7/2',NULL),
 (61,1,'2606354','8/69',NULL),
 (62,1,'2606357','8/69',NULL),
 (63,1,'2466190','8/69',NULL),
 (64,1,'2103258','7/4',NULL),
 (65,1,'51 818 001 161','',NULL),
 (66,1,'51 818 001 062','',NULL),
 (67,1,'32 602 062 002','',NULL),
 (68,1,'31 204 067 001','',NULL),
 (69,1,'31 903 138 007','',NULL),
 (82,1,'6ES5 942-7UA13','',NULL),
 (86,1,'823 800 44','',NULL),
 (89,1,'586 970 44','',NULL),
 (113,6,'5040025','',NULL),
 (114,2,'2538137','',NULL),
 (115,2,'2077127','',NULL),
 (116,2,'2088969','',NULL),
 (117,2,'2842906','',NULL),
 (118,2,'2606490','',NULL),
 (119,2,'2474102','',NULL),
 (125,2,'2558188',' ',NULL),
 (128,2,'2793963','',NULL),
 (129,2,'2599967','',NULL),
 (130,2,'2014005','',NULL),
 (131,2,'2014004','',NULL),
 (132,2,'3001685','',NULL),
 (133,2,'3041475','',NULL),
 (135,2,'3041481','',NULL);
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
 (2,'Lauf.Meter'),
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `position_nummer_ebene_eindeutig` (`nummer`,`fk_ebene`),
  KEY `FK_position_ebene` (`fk_ebene`),
  CONSTRAINT `FK_position_ebene` FOREIGN KEY (`fk_ebene`) REFERENCES `ebene` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=342 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `position`
--

/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` (`id`,`nummer`,`fk_ebene`) VALUES 
 (1,1,1),
 (26,1,2),
 (18,1,3),
 (30,1,4),
 (34,1,5),
 (38,1,6),
 (42,1,7),
 (46,1,8),
 (50,1,9),
 (66,1,10),
 (62,1,11),
 (58,1,12),
 (54,1,13),
 (82,1,14),
 (78,1,15),
 (70,1,17),
 (98,1,18),
 (94,1,19),
 (90,1,20),
 (86,1,21),
 (114,1,22),
 (106,1,23),
 (110,1,24),
 (102,1,25),
 (118,1,26),
 (122,1,27),
 (126,1,28),
 (130,1,29),
 (134,1,30),
 (138,1,31),
 (142,1,32),
 (146,1,33),
 (150,1,34),
 (154,1,35),
 (158,1,36),
 (162,1,37),
 (166,1,38),
 (170,1,39),
 (174,1,40),
 (178,1,41),
 (182,1,42),
 (186,1,43),
 (190,1,44),
 (194,1,45),
 (198,1,46),
 (202,1,47),
 (206,1,48),
 (210,1,49),
 (214,1,50),
 (219,1,51),
 (223,1,52),
 (232,1,53),
 (236,1,54),
 (240,1,55),
 (244,1,56),
 (248,1,57),
 (252,1,58),
 (256,1,59),
 (260,1,60),
 (264,1,61),
 (268,1,62),
 (272,1,63),
 (276,1,64),
 (281,1,65),
 (74,1,66),
 (285,1,67),
 (297,1,68),
 (303,1,69),
 (309,1,70),
 (315,1,71),
 (321,1,72),
 (336,1,126),
 (2,2,1),
 (27,2,2),
 (19,2,3),
 (31,2,4),
 (35,2,5),
 (39,2,6),
 (43,2,7),
 (47,2,8),
 (51,2,9),
 (67,2,10),
 (63,2,11),
 (59,2,12),
 (55,2,13),
 (83,2,14),
 (79,2,15),
 (71,2,17),
 (99,2,18),
 (95,2,19),
 (91,2,20),
 (87,2,21),
 (115,2,22),
 (107,2,23),
 (111,2,24),
 (103,2,25),
 (119,2,26),
 (123,2,27),
 (127,2,28),
 (131,2,29),
 (135,2,30),
 (139,2,31),
 (143,2,32),
 (147,2,33),
 (151,2,34),
 (155,2,35),
 (159,2,36),
 (163,2,37),
 (167,2,38),
 (171,2,39),
 (175,2,40),
 (179,2,41),
 (183,2,42),
 (187,2,43),
 (191,2,44),
 (195,2,45),
 (199,2,46),
 (203,2,47),
 (207,2,48),
 (211,2,49),
 (215,2,50),
 (220,2,51),
 (224,2,52),
 (233,2,53),
 (237,2,54),
 (241,2,55),
 (245,2,56),
 (249,2,57),
 (253,2,58),
 (257,2,59),
 (261,2,60),
 (265,2,61),
 (269,2,62),
 (273,2,63),
 (277,2,64),
 (282,2,65),
 (75,2,66),
 (286,2,67),
 (298,2,68),
 (304,2,69),
 (310,2,70),
 (316,2,71),
 (322,2,72),
 (337,2,126),
 (5,3,1),
 (28,3,2),
 (20,3,3),
 (32,3,4),
 (36,3,5),
 (40,3,6),
 (44,3,7),
 (48,3,8),
 (52,3,9),
 (68,3,10),
 (64,3,11),
 (60,3,12),
 (56,3,13),
 (84,3,14),
 (80,3,15),
 (72,3,17),
 (100,3,18),
 (96,3,19),
 (92,3,20),
 (88,3,21),
 (116,3,22),
 (108,3,23),
 (112,3,24),
 (104,3,25),
 (120,3,26),
 (124,3,27),
 (128,3,28),
 (132,3,29),
 (136,3,30),
 (140,3,31),
 (144,3,32),
 (148,3,33),
 (152,3,34),
 (156,3,35),
 (160,3,36),
 (164,3,37),
 (168,3,38),
 (172,3,39),
 (176,3,40),
 (180,3,41),
 (184,3,42),
 (188,3,43),
 (192,3,44),
 (196,3,45),
 (200,3,46),
 (204,3,47),
 (208,3,48),
 (212,3,49),
 (216,3,50),
 (221,3,51),
 (225,3,52),
 (234,3,53),
 (238,3,54),
 (242,3,55),
 (246,3,56),
 (250,3,57),
 (254,3,58),
 (258,3,59),
 (262,3,60),
 (266,3,61),
 (270,3,62),
 (274,3,63),
 (278,3,64),
 (283,3,65),
 (76,3,66),
 (287,3,67),
 (299,3,68),
 (305,3,69),
 (311,3,70),
 (317,3,71),
 (323,3,72),
 (338,3,126),
 (6,4,1),
 (29,4,2),
 (21,4,3),
 (33,4,4),
 (37,4,5),
 (41,4,6),
 (45,4,7),
 (49,4,8),
 (53,4,9),
 (69,4,10),
 (65,4,11),
 (61,4,12),
 (57,4,13),
 (85,4,14),
 (81,4,15),
 (73,4,17),
 (101,4,18),
 (97,4,19),
 (93,4,20),
 (89,4,21),
 (117,4,22),
 (109,4,23),
 (113,4,24),
 (105,4,25),
 (121,4,26),
 (125,4,27),
 (129,4,28),
 (133,4,29),
 (137,4,30),
 (141,4,31),
 (145,4,32),
 (149,4,33),
 (153,4,34),
 (157,4,35),
 (161,4,36),
 (165,4,37),
 (169,4,38),
 (173,4,39),
 (177,4,40),
 (181,4,41),
 (185,4,42),
 (189,4,43),
 (193,4,44),
 (197,4,45),
 (201,4,46),
 (205,4,47),
 (209,4,48),
 (213,4,49),
 (217,4,50),
 (222,4,51),
 (226,4,52),
 (235,4,53),
 (239,4,54),
 (243,4,55),
 (247,4,56),
 (251,4,57),
 (255,4,58),
 (259,4,59),
 (263,4,60),
 (267,4,61),
 (271,4,62),
 (275,4,63),
 (279,4,64),
 (284,4,65),
 (77,4,66),
 (288,4,67),
 (300,4,68),
 (306,4,69),
 (312,4,70),
 (318,4,71),
 (324,4,72),
 (339,4,126),
 (22,5,3),
 (280,5,65),
 (289,5,67),
 (301,5,68),
 (307,5,69),
 (313,5,70),
 (319,5,71),
 (325,5,72),
 (340,5,126),
 (23,6,3),
 (290,6,67),
 (302,6,68),
 (308,6,69),
 (314,6,70),
 (320,6,71),
 (326,6,72),
 (341,6,126),
 (24,7,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `saeule`
--

/*!40000 ALTER TABLE `saeule` DISABLE KEYS */;
INSERT INTO `saeule` (`id`,`nummer`,`fk_zeile`) VALUES 
 (1,1,1),
 (4,1,2),
 (48,1,3),
 (11,1,5),
 (27,1,6),
 (37,1,20),
 (45,1,21),
 (40,1,22),
 (29,1,23),
 (35,1,24),
 (43,1,26),
 (2,2,1),
 (5,2,2),
 (49,2,3),
 (12,2,5),
 (38,2,20),
 (30,2,23),
 (36,2,24),
 (3,3,1),
 (50,3,3),
 (13,3,5),
 (39,3,20),
 (31,3,23),
 (14,4,5),
 (32,4,23),
 (15,5,5),
 (33,5,23),
 (16,6,5),
 (34,6,23),
 (17,7,5),
 (18,8,5),
 (19,9,5),
 (20,10,5),
 (21,11,5),
 (22,12,5),
 (23,13,5),
 (24,14,5),
 (25,15,5),
 (26,16,5);
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
 (1154,'BestellAnforderungID'),
 (2193,'BestellAnforderungPosID'),
 (82,'BenutzerID'),
 (3,'agent007'),
 (602,'BaugruppeID'),
 (79,'BerichtID'),
 (2,'BestellungID'),
 (3,'BestellungPosID'),
 (38,'EntnahmeID'),
 (60,'Lieferant/HerstellerID'),
 (70,'EntnahmenspositionPosID'),
 (22,'HalleID'),
 (50,'SaeuleID'),
 (51,'BestandsID'),
 (341,'PositionID'),
 (145,'ArtikelID'),
 (15,'EtageID'),
 (29,'ZeileID'),
 (150,'EbeneID');
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
  UNIQUE KEY `eindeutig_zeilen_nummer` (`nummer`,`fk_halle`),
  KEY `FK_zeile_halle` (`fk_halle`),
  KEY `FK_zeile_etage` (`fk_etage`),
  KEY `FK_zeile_abteilung` (`fk_abteilung`),
  CONSTRAINT `FK_zeile_abteilung` FOREIGN KEY (`fk_abteilung`) REFERENCES `abteilung` (`id`),
  CONSTRAINT `FK_zeile_etage` FOREIGN KEY (`fk_etage`) REFERENCES `etage` (`id`),
  CONSTRAINT `FK_zeile_halle` FOREIGN KEY (`fk_halle`) REFERENCES `halle` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `zeile`
--

/*!40000 ALTER TABLE `zeile` DISABLE KEYS */;
INSERT INTO `zeile` (`id`,`nummer`,`fk_halle`,`fk_etage`,`fk_abteilung`) VALUES 
 (1,7,1,1,3),
 (2,2,1,1,3),
 (3,1,1,5,3),
 (5,1,15,12,1),
 (6,2,15,11,1),
 (7,5,1,14,1),
 (20,2,7,4,4),
 (21,4,7,4,4),
 (22,3,7,4,4),
 (23,5,7,4,3),
 (24,1,7,4,4),
 (26,6,7,15,4);
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
