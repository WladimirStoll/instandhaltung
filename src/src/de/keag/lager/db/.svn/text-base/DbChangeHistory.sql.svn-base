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
 
 
 
 
 