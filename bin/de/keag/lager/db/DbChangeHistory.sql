//2010.10.03 12:00:00
DROP TABLE IF EXISTS `lager`.`bericht_bestellanforderung`;
CREATE TABLE  `lager`.`bericht_bestellanforderung` (
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


DROP TABLE IF EXISTS `lager`.`bericht_bestellanforderungsposition`;
CREATE TABLE  `lager`.`bericht_bestellanforderungsposition` (
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
  CONSTRAINT `FK_bericht_bestellanforderungsposition` FOREIGN KEY (`fk_bericht_id`, `fk_bestellanforderung`) REFERENCES `bericht_bestellanforderung` (`fk_bericht_id`, `id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
   
//2010.10.08 20:00:00    
    
ALTER TABLE `lager`.`position` DROP FOREIGN KEY `FK_position_artikel`;
ALTER TABLE `lager`.`position` DROP COLUMN `fk_artikel`;
ALTER TABLE `lager`.`baugruppe` MODIFY COLUMN `id` INT(10) UNSIGNED NOT NULL;

DROP TABLE IF EXISTS `lager`.`bestandspackstueck`;
CREATE TABLE  `lager`.`bestandspackstueck` (
  `id` int(10) unsigned NOT NULL,
  `erfassungsbenutzer` int(10) unsigned DEFAULT NULL,
  `erfassungsdatum` datetime DEFAULT NULL,
  `aenderungsbenutzer` int(10) unsigned DEFAULT NULL,
  `aenderungsdatum` datetime DEFAULT NULL,
  `fk_artikel` int(10) unsigned NOT NULL,
  `fk_position` int(10) unsigned DEFAULT NULL,
  `menge` double NOT NULL,
  `fk_mengeneinheit` int(10) unsigned NOT NULL,
  KEY `FK_bestandspackstueck_artikel` (`fk_artikel`),
  KEY `FK_bestandpackstueck_position` (`fk_position`),
  KEY `FK_bestandpackstueck_mengeneinheit` (`fk_mengeneinheit`),
  CONSTRAINT `FK_bestandspackstueck_artikel` FOREIGN KEY (`fk_artikel`) REFERENCES `artikel` (`id`),
  CONSTRAINT `FK_bestandpackstueck_position` FOREIGN KEY (`fk_position`) REFERENCES `position` (`id`),
  CONSTRAINT `FK_bestandpackstueck_mengeneinheit` FOREIGN KEY (`fk_mengeneinheit`) REFERENCES `mengeneinheit` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


ALTER TABLE `lager`.`bestandspackstueck` MODIFY COLUMN `menge` INTEGER UNSIGNED NOT NULL;

//2010.10.15 00:00:01
ALTER TABLE `lager`.`bestandspackstueck` ADD PRIMARY KEY (`id`);

//2010.10.20 00:00:01
ALTER TABLE `lager`.`baugruppe` ADD COLUMN `fk_halle` INTEGER UNSIGNED AFTER `aenderungsdatum`,
 ADD CONSTRAINT `FK_baugruppe_halle` FOREIGN KEY `FK_baugruppe_halle` (`fk_halle`)
    REFERENCES `halle` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
    
    ALTER TABLE `lager`.`halle` MODIFY COLUMN `name` VARCHAR(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;

ALTER TABLE `lager`.`herstellerlieferant` MODIFY COLUMN `adresse_stadt` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
 MODIFY COLUMN `adresse_strasse` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci;
 
 //2010.10.28 00:00:01
 
 ALTER TABLE `lager`.`entnahmeposition` ADD COLUMN `status` VARCHAR(1) NOT NULL AFTER `fk_position`;

//2010.10.28 00:00:02

ALTER TABLE `lager`.`einlagerungsposition` ADD COLUMN `status` VARCHAR(1) NOT NULL AFTER `fk_position`,
 ADD COLUMN `fk_etage` INTEGER UNSIGNED AFTER `status`;
 
 
ALTER TABLE `lager`.`einlagerungsposition` ADD CONSTRAINT `FK_einlagerungsposition_etage` FOREIGN KEY `FK_einlagerungsposition_etage` (`fk_etage`)
    REFERENCES `etage` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
    
ALTER TABLE `lager`.`entnahmeposition` ADD COLUMN `fk_etage` INTEGER UNSIGNED AFTER `status`,
 ADD CONSTRAINT `FK_entnahmeposition_etage` FOREIGN KEY `FK_entnahmeposition_etage` (`fk_etage`)
    REFERENCES `etage` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
    
   //2010.11.03 00:00:02 
    
    ALTER TABLE `lager`.`entnahmeposition` MODIFY COLUMN `id` INT(10) UNSIGNED NOT NULL,
 ADD COLUMN `fk_kostenstelle` INT(10) UNSIGNED AFTER `fk_etage`,
 ADD CONSTRAINT `FK_entnahmeposition_kostenstelle` FOREIGN KEY `FK_entnahmeposition_kostenstelle` (`fk_kostenstelle`)
    REFERENCES `kostenstelle` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

    //2011.01.10 00:00:01
  ALTER TABLE `lager`.`zeile` ADD UNIQUE INDEX `eindeutig_zeilen_nummer`(`nummer`, `fk_halle`);
        
  //2011.01.28 00:00:01
    ALTER TABLE `lager`.`artikel` MODIFY COLUMN `bezeichnung` VARCHAR(70) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
 ADD COLUMN `beschreibung` VARCHAR(70) AFTER `empfohlene_bestellmenge`;

 ALTER TABLE `lager`.`artikel` CHANGE COLUMN `beschreibung` `bemerkung` VARCHAR(70) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL;
 
ALTER TABLE `lager`.`bestandspackstueck` MODIFY COLUMN `fk_mengeneinheit` INT(10) UNSIGNED DEFAULT NULL; 
ALTER TABLE `lager`.`bestandspackstueck` DROP INDEX `FK_bestandpackstueck_mengeneinheit`; 
 ALTER TABLE `lager`.`bestandspackstueck` DROP COLUMN `fk_mengeneinheit`;
 
 //2011.02.25
 ALTER TABLE `lager`.`lieferantenartikelbestellnummer` MODIFY COLUMN `katalogseite` VARCHAR(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL;

//2011.03.11
DROP TABLE IF EXISTS `lager`.`bericht_inventur_halle`;
CREATE TABLE  `lager`.`bericht_inventur_halle` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `name` varchar(35) NOT NULL,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `lager`.`bericht_inventur_etage`;
CREATE TABLE  `lager`.`bericht_inventur_etage` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `name` varchar(35) NOT NULL,
  `fk_halle` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `lager`.`bericht_inventur_zeile`;
CREATE TABLE  `lager`.`bericht_inventur_zeile` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL ,
  `nummer` int(10) unsigned NOT NULL,
  `fk_halle` int(10) unsigned NOT NULL,
  `fk_etage` int(10) unsigned DEFAULT NULL,
  `fk_abteilung` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `lager`.`bericht_inventur_saeule`;
CREATE TABLE  `lager`.`bericht_inventur_saeule` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL ,
  `nummer` int(10) unsigned NOT NULL,
  `fk_zeile` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `lager`.`bericht_inventur_ebene`;
CREATE TABLE  `lager`.`bericht_inventur_ebene` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL ,
  `nummer` int(10) unsigned NOT NULL,
  `fk_saeule` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `lager`.`bericht_inventur_position`;
CREATE TABLE  `lager`.`bericht_inventur_position` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL ,
  `nummer` int(10) unsigned NOT NULL,
  `fk_ebene` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `lager`.`bericht_inventur_bestandspackstueck`;
CREATE TABLE  `lager`.`bericht_inventur_bestandspackstueck` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL ,
  `fk_artikel` int(10) unsigned NOT NULL,
  `fk_position` int(10) unsigned DEFAULT NULL,
  `menge` int(10) unsigned ,
  `artikel_bezeichnung` varchar(70) COLLATE utf8_unicode_ci ,
  `artikel_typ` varchar(70) COLLATE utf8_unicode_ci ,
  `artikel_keg_nr` int(10) unsigned ,
  `artikel_mindestbestand` int(10) unsigned ,
  `artikel_fk_mengeneinheit` int(10) unsigned ,
  `artikel_fk_hersteller` int(10) unsigned ,
  `hersteller_name` varchar(50) COLLATE utf8_unicode_ci ,
  `mengeneinheit_name` varchar(15) COLLATE utf8_unicode_ci ,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci

//2011.04.08
 ALTER TABLE `lager`.`entnahmeposition` ADD COLUMN `fk_benutzer_entnehmer` INTEGER UNSIGNED  AFTER `fk_kostenstelle`,
 ADD CONSTRAINT `FK_benutzer_entnehmer` FOREIGN KEY `FK_benutzer_entnehmer` (`fk_benutzer_entnehmer`)
    REFERENCES `benutzer` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
    
ALTER TABLE `lager`.`entnahmeposition` ADD COLUMN 
 `erstellungsdatum` DATETIME NOT NULL 
 DEFAULT 0 AFTER `fk_benutzer_entnehmer`;
 
ALTER TABLE `lager`.`entnahmeposition` DROP COLUMN `fk_halle`,
 DROP COLUMN `fk_zeile`,
 DROP COLUMN `fk_saeule`,
 DROP COLUMN `fk_ebene`,
 DROP COLUMN `fk_etage`,
 ADD COLUMN `fk_benutzer_kontroller` INTEGER UNSIGNED AFTER `erstellungsdatum`,
 DROP FOREIGN KEY `FK_entnahmeposition_ebene`,
 DROP FOREIGN KEY `FK_entnahmeposition_etage`,
 DROP FOREIGN KEY `FK_entnahmeposition_halle`,
 DROP FOREIGN KEY `FK_entnahmeposition_saeule`,
 DROP FOREIGN KEY `FK_entnahmeposition_zeile`;
 
 ALTER TABLE `lager`.`entnahmeposition` ADD CONSTRAINT `FK_benutzer_kontroller` FOREIGN KEY `FK_benutzer_kontroller` (`fk_benutzer_kontroller`)
    REFERENCES `benutzer` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
    
ALTER TABLE `lager`.`entnahmeposition` DROP FOREIGN KEY `FK_entnahmeposition_entnahme`;
ALTER TABLE `lager`.`entnahmeposition` DROP COLUMN `fk_entnahme`;

DROP TABLE `lager`.`entnahme`;

ALTER TABLE `lager`.`bestandspackstueck` ADD COLUMN `fk_abteilung` INTEGER UNSIGNED  AFTER `menge`,
 ADD CONSTRAINT `FK_bestandspackstueck_abteilung` FOREIGN KEY `FK_bestandspackstueck_abteilung` (`fk_abteilung`)
    REFERENCES `abteilung` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
    
update bestandspackstueck set fk_abteilung = 3;    

ALTER TABLE `lager`.`bestandspackstueck` MODIFY COLUMN `fk_abteilung` INT(10) UNSIGNED NOT NULL;

DROP TABLE IF EXISTS `lager`.`bericht_mindestbestand`;
CREATE TABLE  `lager`.`bericht_mindestbestand` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `fk_artikel` int(10) unsigned NOT NULL,
  `artikel_bezeichnung` varchar(70) COLLATE utf8_unicode_ci DEFAULT NULL,
  `artikel_typ` varchar(70) COLLATE utf8_unicode_ci DEFAULT NULL,
  `artikel_keg_nr` int(10) unsigned DEFAULT NULL,
  `artikel_mindestbestand` int(10) unsigned DEFAULT NULL,
  `artikel_fk_mengeneinheit` int(10) unsigned DEFAULT NULL,
  `mengeneinheit_name` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `artikel_fk_hersteller` int(10) unsigned DEFAULT NULL,
  `hersteller_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `packstueck_menge` int(10) unsigned DEFAULT NULL,
  `packstueck_id` int(10) unsigned DEFAULT NULL,
  `position_id` int(10) unsigned NOT NULL,
  `position_nummer` int(10) unsigned NOT NULL,
  `ebene_id` int(10) unsigned NOT NULL,
  `ebene_nummer` int(10) unsigned NOT NULL,
  `saeule_id` int(10) unsigned NOT NULL,
  `saeule_nummer` int(10) unsigned NOT NULL,
  `zeile_id` int(10) unsigned NOT NULL,
  `zeile_nummer` int(10) unsigned NOT NULL,
  `halle_id` int(10) unsigned NOT NULL,
  `halle_name` varchar(35) COLLATE utf8_unicode_ci DEFAULT NULL,

  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

//2011.05.06
ALTER TABLE `lager`.`bericht_mindestbestand`
ADD COLUMN `abteilung_id`   INTEGER UNSIGNED AFTER `halle_name`,
ADD COLUMN `abteilung_name` VARCHAR(45) AFTER `abteilung_id`;

DROP TABLE IF EXISTS `lager`.`bericht_mindestbestand_artikel`;
CREATE TABLE  `lager`.`bericht_mindestbestand_artikel` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `fk_artikel` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `lager`.`bericht_mindestbestand_artikel` DROP COLUMN `id`;

//2011.05.31

DROP TABLE IF EXISTS `lager`.`unterartikel`;
CREATE TABLE  `lager`.`unterartikel` (
  `vater_id` int(10) unsigned NOT NULL,
  `kind_id` int(10) unsigned NOT NULL,
  `anzahl` int(10) unsigned NOT NULL,
  PRIMARY KEY (`vater_id`,`kind_id`),
  KEY `FK_unterartikel_kind` (`kind_id`),
  CONSTRAINT `FK_unterartikel_vater` FOREIGN KEY (`vater_id`) REFERENCES `artikel` (`id`),
  CONSTRAINT `FK_unterartikel_kind` FOREIGN KEY (`kind_id`) REFERENCES `artikel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


//2011.06.10
ALTER TABLE `lager`.`einlagerung` DROP FOREIGN KEY `FK_einlagerung_benutzer`;
ALTER TABLE `lager`.`einlagerung` DROP INDEX `FK_einlagerung_benutzer`;

ALTER TABLE `lager`.`einlagerungsposition` DROP FOREIGN KEY `FK_einlagerungsposition_artikel`,
 DROP FOREIGN KEY `FK_einlagerungsposition_ebene`,
 DROP FOREIGN KEY `FK_einlagerungsposition_einlagerung`,
 DROP FOREIGN KEY `FK_einlagerungsposition_halle`,
 DROP FOREIGN KEY `FK_einlagerungsposition_mengeneinheit`,
 DROP FOREIGN KEY `FK_einlagerungsposition_position`,
 DROP FOREIGN KEY `FK_einlagerungsposition_saeule`,
 DROP FOREIGN KEY `FK_einlagerungsposition_zeile`;
 
 ALTER TABLE `lager`.`einlagerungsposition` DROP INDEX `FK_einlagerungsposition_einlagerung`
, DROP INDEX `FK_einlagerungsposition_artikel`
, DROP INDEX `FK_einlagerungsposition_mengeneinheit`
, DROP INDEX `FK_einlagerungsposition_halle`
, DROP INDEX `FK_einlagerungsposition_zeile`
, DROP INDEX `FK_einlagerungsposition_saeule`
, DROP INDEX `FK_einlagerungsposition_ebene`
, DROP INDEX `FK_einlagerungsposition_position`;

DROP TABLE IF EXISTS `lager`.`einlagerungsposition`;

DROP TABLE IF EXISTS `lager`.`einlagerung`;


DROP TABLE IF EXISTS `lager`.`einlagerungposition`;

CREATE TABLE  `lager`.`einlagerungposition` (
  `id` int(10) unsigned NOT NULL,
  `fk_artikel` int(10) unsigned NOT NULL,
  `menge` int(10) unsigned NOT NULL,
  `fk_mengeeinheit` int(10) unsigned NOT NULL,
  `fk_position` int(10) unsigned NOT NULL,
  `status` varchar(1) NOT NULL,
  `fk_kostenstelle` int(10) unsigned DEFAULT NULL,
  `fk_benutzer_einlagerer` int(10) unsigned DEFAULT NULL,
  `erstellungsdatum` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `fk_benutzer_kontroller` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `lager`.`einlagerungposition` ADD CONSTRAINT `FK_einlagerungposition_artikel` FOREIGN KEY `FK_einlagerungposition_artikel` (`fk_artikel`)
    REFERENCES `artikel` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_einlagerungposition_mengeneinheit` FOREIGN KEY `FK_einlagerungposition_mengeneinheit` (`fk_mengeeinheit`)
    REFERENCES `mengeneinheit` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_einlagerungposition_position` FOREIGN KEY `FK_einlagerungposition_position` (`fk_position`)
    REFERENCES `position` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_einlagerungposition_kostenstelle` FOREIGN KEY `FK_einlagerungposition_kostenstelle` (`fk_kostenstelle`)
    REFERENCES `kostenstelle` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_einlagerungposition_benutzer_einlagerer` FOREIGN KEY `FK_einlagerungposition_benutzer_einlagerer` (`fk_benutzer_einlagerer`)
    REFERENCES `benutzer` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_einlagerungposition_kontroller` FOREIGN KEY `FK_einlagerungposition_kontroller` (`fk_benutzer_kontroller`)
    REFERENCES `benutzer` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

ALTER TABLE `lager`.`katalog` MODIFY COLUMN `name` VARCHAR(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;

ALTER TABLE `lager`.`halle` ADD COLUMN `nummer` INTEGER UNSIGNED AFTER `name`;
update halle h set nummer = id;
ALTER TABLE `lager`.`halle` MODIFY COLUMN `nummer` INT(10) UNSIGNED NOT NULL;
ALTER TABLE `lager`.`halle` ADD UNIQUE INDEX `hallenNummer_eindeutig`(`nummer`);

//2011.06.24
ALTER TABLE `lager`.`bericht_inventur_halle` ADD COLUMN `nummer` INTEGER UNSIGNED AFTER `name`;

//2011.07.15

DROP TABLE IF EXISTS `lager`.`wartungstyp`;
CREATE TABLE  `lager`.`wartungstyp` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `wartungstyp_name_unique` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `lager`.`wartung`;
CREATE TABLE  `lager`.`wartung` (
  `erfassungsbenutzer` int(10) unsigned DEFAULT NULL,
  `erfassungsdatum` datetime DEFAULT NULL,
  `aenderungsbenutzer` int(10) unsigned DEFAULT NULL,
  `aenderungsdatum` datetime DEFAULT NULL,
  `id` int(10) unsigned NOT NULL,
  `typ` int(10) unsigned NOT NULL,
  `karteinummer` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `beschreibung` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `intervall` int(10) unsigned DEFAULT NULL,
  `termin_soll` datetime NOT NULL,
  `termin_ist` datetime DEFAULT NULL,
  `status` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `wartung_unique_key` (`id`),
  KEY `FK_wartung_wartungstyp` (`typ`),
  CONSTRAINT `FK_wartung_wartungstyp` FOREIGN KEY (`typ`) REFERENCES `wartungstyp` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `lager`.`wartungsmitarbeiter`;
CREATE TABLE  `lager`.`wartungsmitarbeiter` (
  `erfassungsbenutzer` int(10) unsigned DEFAULT NULL,
  `erfassungsdatum` datetime DEFAULT NULL,
  `aenderungsbenutzer` int(10) unsigned DEFAULT NULL,
  `aenderungsdatum` datetime DEFAULT NULL,
  `id` int(10) unsigned NOT NULL,
  `wartungs_id` int(10) unsigned NOT NULL,
  `benutzer_id` int(10) unsigned NOT NULL,
  `stunden` double DEFAULT NULL,
  `termin_ist` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_wartungsmitarbeiter_wartung` (`wartungs_id`),
  KEY `FK_wartungsmitarbeiter_benutzer` (`benutzer_id`),
  CONSTRAINT `FK_wartungsmitarbeiter_benutzer` FOREIGN KEY (`benutzer_id`) REFERENCES `benutzer` (`id`),
  CONSTRAINT `FK_wartungsmitarbeiter_wartung` FOREIGN KEY (`wartungs_id`) REFERENCES `wartung` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

ALTER TABLE `lager`.`wartung` ADD COLUMN `baugruppe_id` INTEGER UNSIGNED AFTER `status`;

ALTER TABLE `lager`.`bericht_bestellanforderungsposition` MODIFY COLUMN 
`fk_artikel_bezeichnung` VARCHAR(70) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL;

#2011.08.05;
ALTER TABLE `lager`.`bericht_bestellanforderung` MODIFY COLUMN `fk_herstellerLieferant_name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL;
ALTER TABLE `lager`.`bestellanforderungsposition` MODIFY COLUMN `lieferantenbestellnummer` VARCHAR(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL;
ALTER TABLE `lager`.`bericht_bestellanforderungsposition` MODIFY COLUMN `lieferantenbestellnummer` VARCHAR(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL;

#2011.08.12;
ALTER TABLE `lager`.`wartung` MODIFY COLUMN `typ` INT(10) UNSIGNED NOT NULL COMMENT 'Schmieren, Sichten etc.',
 CHANGE COLUMN `karteinummer` `karteiid` INT(10) UNSIGNED DEFAULT NULL COMMENT 'fortlaufend ',
 ADD COLUMN `art` INTEGER(10) UNSIGNED NOT NULL COMMENT 'Wartung, Reparatur, TÜV' AFTER `baugruppe_id`,
 ADD COLUMN `bemerkung` VARCHAR(100) AFTER `art`;

DROP TABLE IF EXISTS `lager`.`wartungsart`;
CREATE TABLE  `lager`.`wartungsart` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `wartungstyp_name_unique` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

ALTER TABLE `lager`.`wartungsmitarbeiter` MODIFY COLUMN `id` INT(10) UNSIGNED NOT NULL COMMENT 'Mehrmals pro Wartung möglich',
 MODIFY COLUMN `stunden` DOUBLE DEFAULT NULL COMMENT 'Dauer des Einsatzes',
 MODIFY COLUMN `termin_ist` DATETIME DEFAULT NULL COMMENT 'Tag des einsatzes';

DROP TABLE IF EXISTS `lager`.`wartungsartikel`;
CREATE TABLE  `lager`.`wartungsartikel` (
  `erfassungsbenutzer` int(10) unsigned DEFAULT NULL,
  `erfassungsdatum` datetime DEFAULT NULL,
  `aenderungsbenutzer` int(10) unsigned DEFAULT NULL,
  `aenderungsdatum` datetime DEFAULT NULL,
  `id` int(10) unsigned NOT NULL COMMENT 'Mehrmals pro Wartung möglich',
  `fk_wartung` int(10) unsigned NOT NULL,
  `fk_artikel` int(10) unsigned NOT NULL,
  `menge` int(10) unsigned DEFAULT NULL COMMENT 'Verbrauchte Menge',
  `fk_mengeneinheit` int(10) unsigned DEFAULT NULL COMMENT 'Artikeleinheit',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC; 

ALTER TABLE `lager`.`wartungsmitarbeiter` CHANGE COLUMN `wartungs_id` `fk_wartung` INT(10) UNSIGNED NOT NULL COMMENT 'Wartung',
 CHANGE COLUMN `benutzer_id` `fk_benutzer` INT(10) UNSIGNED NOT NULL COMMENT 'Wartungsmitarbeiter'
, DROP INDEX `FK_wartungsmitarbeiter_wartung`
, DROP INDEX `FK_wartungsmitarbeiter_benutzer`,
 DROP FOREIGN KEY `FK_wartungsmitarbeiter_benutzer`,
 DROP FOREIGN KEY `FK_wartungsmitarbeiter_wartung`;
 
ALTER TABLE `lager`.`wartungsmitarbeiter` ADD COLUMN `fk_herstellerlieferant` INT(10) UNSIGNED COMMENT 'Beauftragte Firma (Lieferant)' AFTER `termin_ist`;
 
ALTER TABLE `lager`.`wartungsmitarbeiter` MODIFY COLUMN `stunden` DOUBLE DEFAULT NULL COMMENT 'Dauer des Einsatzes, Zeitaufwand',
 MODIFY COLUMN `fk_herstellerlieferant` INT(10) UNSIGNED DEFAULT NULL COMMENT 'Beauftragte Firma (Lieferant, oder Dientsleister)',
 ADD COLUMN `leistungstyp` VARCHAR(1) COMMENT 'i=intern, e=extern' AFTER `fk_herstellerlieferant`;
 
ALTER TABLE `lager`.`wartung` CHANGE COLUMN `typ` `fk_wartungstyp` INT(10) UNSIGNED NOT NULL COMMENT 'Schmieren, Sichten etc.',
 CHANGE COLUMN `baugruppe_id` `fk_baugruppe` INT(10) UNSIGNED DEFAULT NULL COMMENT 'Woran wurde gearbetet',
 CHANGE COLUMN `art` `fk_wartungsart` INT(10) UNSIGNED NOT NULL COMMENT 'Wartung, Reparatur, TÜV',
 DROP INDEX `FK_wartung_wartungstyp`,
 ADD INDEX `FK_wartung_wartungstyp` USING BTREE(`fk_wartungstyp`),
 DROP FOREIGN KEY `FK_wartung_wartungstyp`;

ALTER TABLE `lager`.`wartungsartikel` CHANGE COLUMN `fk_mengeneinheit` `mengeneinheit` INT(10) UNSIGNED DEFAULT NULL COMMENT 'Vorlage: Kopie der Artikeleinheit, danach änderbar';
ALTER TABLE `lager`.`wartungsartikel` CHANGE COLUMN `mengeneinheit` `fk_mengeneinheit` INT(10) UNSIGNED DEFAULT NULL COMMENT 'Vorlage: Kopie der Artikeleinheit, danach änderbar';

DROP TABLE IF EXISTS `lager`.`wartung`;
CREATE TABLE  `lager`.`wartung` (
  `erfassungsbenutzer` int(10) unsigned DEFAULT NULL,
  `erfassungsdatum` datetime DEFAULT NULL,
  `aenderungsbenutzer` int(10) unsigned DEFAULT NULL,
  `aenderungsdatum` datetime DEFAULT NULL,
  `id` int(10) unsigned NOT NULL,
  `fk_wartungstyp` int(10) unsigned NOT NULL COMMENT 'Schmieren, Sichten etc.',
  `karteiid` int(10) unsigned DEFAULT NULL COMMENT 'fortlaufend ',
  `beschreibung` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `intervall` int(10) unsigned DEFAULT NULL,
  `termin_soll` datetime NOT NULL,
  `termin_ist` datetime DEFAULT NULL,
  `status` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fk_baugruppe` int(10) unsigned DEFAULT NULL COMMENT 'Woran wurde gearbetet',
  `fk_wartungsart` int(10) unsigned NOT NULL COMMENT 'Wartung, Reparatur, TÜV',
  `bemerkung` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `wartung_unique_key` (`id`),
  KEY `FK_wartung_wartungstyp` (`fk_wartungstyp`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `lager`.`wartungsart`;
CREATE TABLE  `lager`.`wartungsart` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `wartungstyp_name_unique` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `lager`.`wartungsartikel`;
CREATE TABLE  `lager`.`wartungsartikel` (
  `erfassungsbenutzer` int(10) unsigned DEFAULT NULL,
  `erfassungsdatum` datetime DEFAULT NULL,
  `aenderungsbenutzer` int(10) unsigned DEFAULT NULL,
  `aenderungsdatum` datetime DEFAULT NULL,
  `id` int(10) unsigned NOT NULL COMMENT 'Mehrmals pro Wartung möglich',
  `fk_wartung` int(10) unsigned NOT NULL,
  `fk_artikel` int(10) unsigned NOT NULL,
  `menge` int(10) unsigned DEFAULT NULL COMMENT 'Verbrauchte Menge',
  `fk_mengeneinheit` int(10) unsigned DEFAULT NULL COMMENT 'Vorlage: Kopie der Artikeleinheit, danach änderbar',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `lager`.`wartungsmitarbeiter`;
CREATE TABLE  `lager`.`wartungsmitarbeiter` (
  `erfassungsbenutzer` int(10) unsigned DEFAULT NULL,
  `erfassungsdatum` datetime DEFAULT NULL,
  `aenderungsbenutzer` int(10) unsigned DEFAULT NULL,
  `aenderungsdatum` datetime DEFAULT NULL,
  `id` int(10) unsigned NOT NULL COMMENT 'Mehrmals pro Wartung möglich',
  `fk_wartung` int(10) unsigned NOT NULL COMMENT 'Wartung',
  `fk_benutzer` int(10) unsigned NOT NULL COMMENT 'Wartungsmitarbeiter',
  `stunden` double DEFAULT NULL COMMENT 'Dauer des Einsatzes, Zeitaufwand',
  `termin_ist` datetime DEFAULT NULL COMMENT 'Tag des einsatzes',
  `fk_herstellerlieferant` int(10) unsigned DEFAULT NULL COMMENT 'Beauftragte Firma (Lieferant, oder Dientsleister)',
  `leistungstyp` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'i=intern, e=extern',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `lager`.`wartungstyp`;
CREATE TABLE  `lager`.`wartungstyp` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `wartungstyp_name_unique` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

ALTER TABLE `lager`.`wartung` MODIFY COLUMN `termin_soll` DATETIME DEFAULT NULL,
 MODIFY COLUMN `fk_wartungsart` INT(10) UNSIGNED DEFAULT NULL COMMENT 'Wartung, Reparatur, TÜV',
 ADD CONSTRAINT `FK_wartung_wartungstyp` FOREIGN KEY `FK_wartung_wartungstyp` (`fk_wartungstyp`)
    REFERENCES `wartungstyp` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_wartung_wartungart` FOREIGN KEY `FK_wartung_wartungart` (`fk_wartungsart`)
    REFERENCES `wartungsart` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_wartung_baugruppe` FOREIGN KEY `FK_wartung_baugruppe` (`fk_baugruppe`)
    REFERENCES `baugruppe` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_wartung_wartungsart` FOREIGN KEY `FK_wartung_wartungsart` (`fk_wartungsart`)
    REFERENCES `wartungsart` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

ALTER TABLE `lager`.`wartungsartikel` ADD CONSTRAINT `FK_wartungsartikel_wartung` FOREIGN KEY `FK_wartungsartikel_wartung` (`fk_wartung`)
    REFERENCES `wartung` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_wartungsartikel_artikel` FOREIGN KEY `FK_wartungsartikel_artikel` (`fk_artikel`)
    REFERENCES `artikel` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_wartungsartikel_mengeneinheit` FOREIGN KEY `FK_wartungsartikel_mengeneinheit` (`fk_mengeneinheit`)
    REFERENCES `mengeneinheit` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

ALTER TABLE `lager`.`wartungsmitarbeiter` ADD CONSTRAINT `FK_wartungsmitarbeiter_wartung` FOREIGN KEY `FK_wartungsmitarbeiter_wartung` (`fk_wartung`)
    REFERENCES `wartung` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_wartungsmitarbeiter_benutzer` FOREIGN KEY `FK_wartungsmitarbeiter_benutzer` (`fk_benutzer`)
    REFERENCES `benutzer` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_wartungsmitarbeiter_herstellerlieferant` FOREIGN KEY `FK_wartungsmitarbeiter_herstellerlieferant` (`fk_herstellerlieferant`)
    REFERENCES `herstellerlieferant` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

    
ALTER TABLE `lager`.`wartung` MODIFY COLUMN `karteiid` INT(10) UNSIGNED DEFAULT NULL COMMENT 'fortlaufend nach Benutzeraufforderung';

ALTER TABLE `lager`.`wartungsmitarbeiter` MODIFY COLUMN `fk_benutzer` INT(10) UNSIGNED DEFAULT NULL COMMENT 'Wartungsmitarbeiter',
 DROP COLUMN `termin_ist`,
 DROP COLUMN `leistungstyp`;

ALTER TABLE `lager`.`wartungsmitarbeiter` ADD COLUMN `bemerkung` VARCHAR(100) NOT NULL COMMENT 'z.B. Familienname vom Monteur der externen Firma' AFTER `fk_herstellerlieferant`;
ALTER TABLE `lager`.`wartungsmitarbeiter` MODIFY COLUMN `bemerkung` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'z.B. Familienname vom Monteur der externen Firma';
ALTER TABLE `lager`.`wartungsartikel` DROP COLUMN `fk_mengeneinheit`,
 DROP FOREIGN KEY `FK_wartungsartikel_mengeneinheit`;

#2011.08.18
insert into wartungsart(id,name) value (1, "Wartung");
insert into wartungsart(id,name) value (2, "Reparatur");
insert into wartungsart(id,name) value (3, "TÜV");

insert into wartungstyp(id,name) value (1, "Schmieren");
insert into wartungstyp(id,name) value (2, "Kontrollieren");
insert into wartungstyp(id,name) value (3, "Nachfüllen");

#2011.08.28
ALTER TABLE `lager`.`wartung` MODIFY COLUMN `fk_wartungstyp` INT(10) UNSIGNED COMMENT 'Schmieren, Sichten etc.';    

CREATE TABLE `lager`.`version` (
  `Versionsdatum` DATETIME NOT NULL,
  PRIMARY KEY (`Versionsdatum`)
)
ENGINE = InnoDB
COMMENT = 'Version der Datenbank';

delete from `lager`.`version`;
insert into `lager`.`version` (versionsdatum) values('2011-08-28 01:01:01');
update `lager`.`version` set versionsdatum = '2011-08-28 01:01:01';

ALTER TABLE `lager`.`version` ADD COLUMN `Beschreibung` VARCHAR(500) AFTER `Versionsdatum`;

update `lager`.`version` set versionsdatum = '2011-08-28 01:01:02', beschreibung='erste Versionierung';

#2011.08.28
ALTER TABLE `lager`.`wartungsart` ADD COLUMN `intervallfaehig` VARCHAR(1) AFTER `name`;
update wartungsart set intervallfaehig='J' where name = 'Wartung';
update wartungsart set intervallfaehig='J' where name = 'TÜV';
update wartungsart set intervallfaehig='N' where name = 'Reparatur';

#2011.08.29
DROP TABLE IF EXISTS `lager`.`bericht_wartung`;
CREATE TABLE  `lager`.`bericht_wartung` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL,
  `baugruppe` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `baugruppe_uebergeordnet` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mitarbeiter` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `artikel` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `termin_soll` datetime DEFAULT NULL,
  `termin_ist` datetime DEFAULT NULL,
  PRIMARY KEY (`fk_bericht_id`,`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `lager`.`bericht_wartung_ids`;
CREATE TABLE  `lager`.`bericht_wartung_ids` (
  `fk_bericht_id` int(10) unsigned NOT NULL,
  `fk_wartung_id` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `lager`.`bericht_wartung` ADD COLUMN `art_typ` VARCHAR(45) AFTER `termin_ist`;

update `lager`.`version` set versionsdatum = '2011-08-29 01:01:01', beschreibung='wartungsmaske';

#2011.09.16
ALTER TABLE `lager`.`zeile` MODIFY COLUMN `id` INT(10) UNSIGNED NOT NULL;
ALTER TABLE `lager`.`zeile` MODIFY COLUMN `fk_abteilung` INT(10) UNSIGNED DEFAULT NULL;
ALTER TABLE `lager`.`bestellanforderungsposition` MODIFY COLUMN `katalogseite` VARCHAR(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL;
ALTER TABLE `lager`.`bericht_bestellanforderungsposition` MODIFY COLUMN `katalogseite` VARCHAR(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL;
ALTER TABLE `lager`.`mengeneinheit` ADD COLUMN `vordefiniert` INTEGER UNSIGNED AFTER `name`;
ALTER TABLE `lager`.`bericht_wartung` ADD COLUMN `beschreibung` VARCHAR(500) AFTER `art_typ`;
update `lager`.`version` set versionsdatum = '2011-09-16 01:01:01', beschreibung='Version';

#2012.06.16
insert into `lager`.`mengeneinheit` (id,name) values(4,'Kg');
insert into `lager`.`mengeneinheit` (id,name) values(5,'Liter');
insert into `lager`.`mengeneinheit` (id,name) values(6,'Packung');

#2012.07.31
CREATE  TABLE `lager`.`fehler` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `fehler` VARCHAR(1000) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8, 
COMMENT = 'Kein Commit-Controll! '
;


#2015.09.24, Feld löschen in den Baugruppen
ALTER TABLE `lager`.`baugruppe` ADD COLUMN `deletedrecord` VARCHAR(1) AFTER `fk_halle`;
#select * from `lager`.`baugruppe` where name like 'alt %';
#update `lager`.`baugruppe` set deletedrecord = 'J' where name like 'alt %' limit 100;
#select * from `lager`.`baugruppe` where name like 'alt %' and deletedrecord is null;

