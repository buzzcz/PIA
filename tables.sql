CREATE SCHEMA `klaus_kivbook`;

CREATE TABLE `klaus_kivbook`.`user` (
  `id`         INT                     NOT NULL AUTO_INCREMENT,
  `username`   VARCHAR(36)             NOT NULL,
  `first_name` VARCHAR(36)             NOT NULL,
  `last_name`  VARCHAR(36)             NOT NULL,
  `birthday`   DATE,
  `email`      VARCHAR(255)            NOT NULL,
  `password`   VARCHAR(255)            NOT NULL,
  `gender`     ENUM ('MALE', 'FEMALE') NOT NULL,
  `picture`    VARCHAR(255),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `klaus_kivbook`.`post` (
  `id`      INT       NOT NULL AUTO_INCREMENT,
  `user_id` INT       NOT NULL,
  `created` TIMESTAMP NOT NULL,
  `text`    MEDIUMTEXT,
  `picture` VARCHAR(255),
  `privacy` BOOL      NOT NULL,
  FOREIGN KEY (`user_id`) REFERENCES `klaus_kivbook`.`user` (`id`),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `klaus_kivbook`.`comment` (
  `id`      INT        NOT NULL AUTO_INCREMENT,
  `user_id` INT        NOT NULL,
  `post_id` INT        NOT NULL,
  `created` TIMESTAMP  NOT NULL,
  `text`    MEDIUMTEXT NOT NULL,
  FOREIGN KEY (`user_id`) REFERENCES `klaus_kivbook`.`user` (`id`),
  FOREIGN KEY (`post_id`) REFERENCES `klaus_kivbook`.`post` (`id`),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `klaus_kivbook`.`reaction` (
  `id`      INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  FOREIGN KEY (`user_id`) REFERENCES `klaus_kivbook`.`user` (`id`),
  FOREIGN KEY (`post_id`) REFERENCES `klaus_kivbook`.`post` (`id`),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `klaus_kivbook`.`friend` (
  `id`       INT  NOT NULL AUTO_INCREMENT,
  `user_id1` INT  NOT NULL,
  `user_id2` INT  NOT NULL,
  `ack`      BOOL NOT NULL,
  FOREIGN KEY (`user_id1`) REFERENCES `klaus_kivbook`.`user` (`id`),
  FOREIGN KEY (`user_id2`) REFERENCES `klaus_kivbook`.`user` (`id`),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `klaus_kivbook`.`conversation` (
  `id`       INT       NOT NULL AUTO_INCREMENT,
  `user_id1` INT       NOT NULL,
  `user_id2` INT       NOT NULL,
  `created`  TIMESTAMP NOT NULL,
  FOREIGN KEY (`user_id1`) REFERENCES `klaus_kivbook`.`user` (`id`),
  FOREIGN KEY (`user_id2`) REFERENCES `klaus_kivbook`.`user` (`id`),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `klaus_kivbook`.`message` (
  `id`              INT        NOT NULL AUTO_INCREMENT,
  `user_id`         INT        NOT NULL,
  `conversation_id` INT        NOT NULL,
  `created`         TIMESTAMP  NOT NULL,
  `text`            MEDIUMTEXT NOT NULL,
  FOREIGN KEY (`user_id`) REFERENCES `klaus_kivbook`.`user` (`id`),
  FOREIGN KEY (`conversation_id`) REFERENCES `klaus_kivbook`.`conversation` (`id`),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
