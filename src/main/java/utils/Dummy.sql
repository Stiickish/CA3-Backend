-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: CA3
-- Source Schemata: CA3
-- Created: Tue Dec 13 13:16:26 2022
-- Workbench Version: 8.0.31
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema CA3
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `CA3` ;
CREATE SCHEMA IF NOT EXISTS `CA3` ;

-- ----------------------------------------------------------------------------
-- Table CA3.fuel
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA3`.`fuel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `CA3`.`fuel`
(`name`)
VALUES
("Petrol"),
("Diesel"),
("Other");

-- ----------------------------------------------------------------------------
-- Table CA3.journey_type
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA3`.`journey_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `CA3`.`journey_type`
(`name`)
VALUES
("Default"),
("Recurring");


-- ----------------------------------------------------------------------------
-- Table CA3.profile
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA3`.`profile` (
  `email` VARCHAR(45) NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_profile_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_profile_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `CA3`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `CA3`.`profile`
(`email`,
`name`,
`user_id`)
VALUES
("a@a.dk","John",1),
("b@b.dk","Morten",2),
("c@c.dk","Dennis",3),
("d@d.dk","Karsten",4);
-- ----------------------------------------------------------------------------
-- Table CA3.user
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA3`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(25) NOT NULL,
  `user_pass` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `CA3`.`user`
(`user_name`,
`user_pass`)
VALUES
("user","$2a$10$Q0UmeUC3Ry0tb22jJfECYOeu7eY2ilU2.FdUdEYsge9gVg6X0qUxe"),
("user2","$2a$10$Q0UmeUC3Ry0tb22jJfECYOeu7eY2ilU2.FdUdEYsge9gVg6X0qUxe"),
("user3","$2a$10$Q0UmeUC3Ry0tb22jJfECYOeu7eY2ilU2.FdUdEYsge9gVg6X0qUxe"),
("user4","$2a$10$Q0UmeUC3Ry0tb22jJfECYOeu7eY2ilU2.FdUdEYsge9gVg6X0qUxe");



-- ----------------------------------------------------------------------------
-- Table CA3.role
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA3`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `CA3`.`role`
(`role_name`)
VALUES
("admin"),
("user");

-- ----------------------------------------------------------------------------
-- Table CA3.user_has_role
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA3`.`user_has_role` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `fk_user_has_role_role1_idx` (`role_id` ASC) VISIBLE,
  INDEX `fk_user_has_role_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_role_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `CA3`.`role` (`id`),
  CONSTRAINT `fk_user_has_role_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `CA3`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `CA3`.`user_has_role`
(`user_id`,
`role_id`)
VALUES
(1,1),
(3,1),
(1,2),
(2,2);


-- ----------------------------------------------------------------------------
-- Table CA3.journey
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA3`.`journey` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `total_emission` FLOAT NOT NULL,
  `total_distance` FLOAT NOT NULL,
  `total_cost` FLOAT NOT NULL,
  `profile_id` INT NOT NULL,
  `journey_type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_journey_profile1_idx` (`profile_id` ASC) VISIBLE,
  INDEX `fk_journey_journey_type1_idx` (`journey_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_journey_journey_type1`
    FOREIGN KEY (`journey_type_id`)
    REFERENCES `CA3`.`journey_type` (`id`),
  CONSTRAINT `fk_journey_profile1`
    FOREIGN KEY (`profile_id`)
    REFERENCES `CA3`.`profile` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


INSERT INTO `CA3`.`journey`
(`name`,
`date`,
`total_emission`,
`total_distance`,
`total_cost`,
`profile_id`,
`journey_type_id`)
VALUES
("school","2022-10-10",80.5,40,10,1,1),
("work","2022-11-11",100,10,1,2,1),
("workout","2022-12-12",45.3,10,10,3,1),
("cafe","2022-09-10",150.4,100,10,4,1);

-- ----------------------------------------------------------------------------
-- Table CA3.transportation
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA3`.`transportation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `CA3`.`transportation`
(`name`)
VALUES
("SmallDieselCar"),
("MediumDieselCar"),
("LargeDieselCar"),
("MediumHybridCar"),
("LargeHybridCar"),
("MediumLPGCar"),
("LargeLPGCar"),
("MediumCNGCar"),
("LargeCNGCar"),
("SmallPetrolVan"),
("LargePetrolVan"),
("SmallDieselVan"),
("MediumDieselVan"),
("LargeDieselVan"),
("LPGVan"),
("CNGVan"),
("SmallPetrolVan"),
("MediumPetrolVan"),
("LargePetrolVan"),
("SmallMotorBike"),
("MediumMotorBike"),
("LargeMotorBike");


-- ----------------------------------------------------------------------------
-- Table CA3.trip
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA3`.`trip` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `distance` FLOAT NOT NULL,
  `emission` FLOAT NOT NULL,
  `cost` FLOAT NOT NULL,
  `journey_id` INT NOT NULL,
  `fuel_id` INT NOT NULL,
  `transportation_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_trip_journey1_idx` (`journey_id` ASC) VISIBLE,
  INDEX `fk_trip_fuel1_idx` (`fuel_id` ASC) VISIBLE,
  INDEX `fk_trip_transportation1_idx` (`transportation_id` ASC) VISIBLE,
  CONSTRAINT `fk_trip_fuel1`
    FOREIGN KEY (`fuel_id`)
    REFERENCES `CA3`.`fuel` (`id`),
  CONSTRAINT `fk_trip_journey1`
    FOREIGN KEY (`journey_id`)
    REFERENCES `CA3`.`journey` (`id`),
  CONSTRAINT `fk_trip_transportation1`
    FOREIGN KEY (`transportation_id`)
    REFERENCES `CA3`.`transportation` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `CA3`.`trip`
(`distance`,
`emission`,
`cost`,
`journey_id`,
`fuel_id`,
`transportation_id`)
VALUES
(8,80,10,1,1,1),
(80,100,100,2,2,2),
(8,800,150,3,1,3),
(12,35,1000,4,2,4);
