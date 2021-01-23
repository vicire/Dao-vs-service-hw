CREATE SCHEMA `taxi-service` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `taxi_service`.`manufacturers` (
                                                `manufacturer_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                                `brand` VARCHAR(225) NOT NULL,
                                                `country` VARCHAR(225) NOT NULL,
                                                `deleted` TINYINT NOT NULL,
                                                PRIMARY KEY (`manufacturer_id`));

ALTER TABLE `taxi_service`.`manufacturers`
    CHANGE COLUMN `deleted` `deleted` TINYINT NOT NULL DEFAULT 0 ;

CREATE TABLE `taxi_service`.`drivers` (
                                          `driver_id` BIGINT NOT NULL AUTO_INCREMENT,
                                          `name` VARCHAR(225) NOT NULL,
                                          `licenseNumber` VARCHAR(225) NOT NULL,
                                          `deleted` TINYINT NOT NULL DEFAULT '0',
                                          PRIMARY KEY (`driver_id`));

CREATE TABLE `taxi_service`.`cars` (
                                       `car_id` BIGINT NOT NULL AUTO_INCREMENT,
                                       `model` VARCHAR(225) NOT NULL,
                                       `manufacturer_id` BIGINT NOT NULL,
                                       `deleted` TINYINT NOT NULL DEFAULT '0',
                                       PRIMARY KEY (`car_id`),
                                       INDEX `cars_manufacturer_id_idx` (`manufacturer_id` ASC) VISIBLE,
                                       CONSTRAINT `cars_manufacturer_id`
                                           FOREIGN KEY (`manufacturer_id`)
                                               REFERENCES `taxi_service`.`manufacturers` (`manufacturer_id`)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION);

CREATE TABLE `taxi_service`.`cars_drivers` (
                                               `car_id` BIGINT NOT NULL,
                                               `driver_id` BIGINT NOT NULL,
                                               INDEX `car_id_idx` (`car_id` ASC) VISIBLE,
                                               INDEX `driver_id_idx` (`driver_id` ASC) VISIBLE,
                                               CONSTRAINT `car_id`
                                                   FOREIGN KEY (`car_id`)
                                                       REFERENCES `taxi_service`.`cars` (`car_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION,
                                               CONSTRAINT `driver_id`
                                                   FOREIGN KEY (`driver_id`)
                                                       REFERENCES `taxi_service`.`drivers` (`driver_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION);

ALTER TABLE `taxi_service`.`drivers`
    ADD COLUMN `login` VARCHAR(225) NOT NULL AFTER `deleted`,
    ADD COLUMN `password` VARCHAR(225) NOT NULL AFTER `login`,
    ADD UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE;
;
