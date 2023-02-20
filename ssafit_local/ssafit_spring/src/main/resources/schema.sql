DROP DATABASE IF EXISTS ssafitdb;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ssafitdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ssafitdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafitdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ssafitdb` ;

-- -----------------------------------------------------
-- Table `ssafitdb`.`videos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafitdb`.`videos` (
  `videoId` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`videoId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafitdb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafitdb`.`users` (
  `id` VARCHAR(40) NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  `name` VARCHAR(40) NOT NULL,
  `email` VARCHAR(40) NOT NULL,
  `age` INT NOT NULL,
  `img` VARCHAR(40) NULL DEFAULT NULL,
  `videos_videoId` VARCHAR(45) NOT NULL,
  `videos_videoId1` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `videos_videoId`),
  INDEX `fk_users_videos1_idx` (`videos_videoId1` ASC) VISIBLE,
  CONSTRAINT `fk_users_videos1`
    FOREIGN KEY (`videos_videoId1`)
    REFERENCES `ssafitdb`.`videos` (`videoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafitdb`.`reviews`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafitdb`.`reviews` (
  `reviewId` INT NOT NULL AUTO_INCREMENT,
  `videolId` VARCHAR(45) NOT NULL,
  `id` VARCHAR(45) NOT NULL,
  `content` VARCHAR(300) NULL DEFAULT NULL,
  `videos_videoId` VARCHAR(45) NOT NULL,
  `users_id` VARCHAR(40) NOT NULL,
  `users_videos_videoId` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`reviewId`),
  INDEX `fk_reviews_videos_idx` (`videos_videoId` ASC) VISIBLE,
  INDEX `fk_reviews_users1_idx` (`users_id` ASC, `users_videos_videoId` ASC) VISIBLE,
  CONSTRAINT `fk_reviews_videos`
    FOREIGN KEY (`videos_videoId`)
    REFERENCES `ssafitdb`.`videos` (`videoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reviews_users1`
    FOREIGN KEY (`users_id` , `users_videos_videoId`)
    REFERENCES `ssafitdb`.`users` (`id` , `videos_videoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

SELECT * from users;
SELECT * from reviews;
SELECT * FROM videos;