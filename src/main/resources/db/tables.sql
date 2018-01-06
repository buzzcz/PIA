CREATE SCHEMA `klaus_kivbook`;

CREATE TABLE `klaus_kivbook`.`user`(
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
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE `klaus_kivbook`.`post`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `text` MEDIUMTEXT,
  `picture` VARCHAR(255),
  `privacy` BOOL NOT NULL,
  FOREIGN KEY (`userId`) REFERENCES `klaus_kivbook`.`user`(`id`),
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE `klaus_kivbook`.`comment`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `postId` INT NOT NULL,
  `text` MEDIUMTEXT NOT NULL,
  FOREIGN KEY (`userId`) REFERENCES `klaus_kivbook`.`user`(`id`),
  FOREIGN KEY (`postId`) REFERENCES `klaus_kivbook`.`post`(`id`),
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE `klaus_kivbook`.`like`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `postId` INT NOT NULL,
  FOREIGN KEY (`userId`) REFERENCES `klaus_kivbook`.`user`(`id`),
  FOREIGN KEY (`postId`) REFERENCES `klaus_kivbook`.`post`(`id`),
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE `klaus_kivbook`.`friend`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId1` INT NOT NULL,
  `userId2` INT NOT NULL,
  FOREIGN KEY (`userId1`) REFERENCES `klaus_kivbook`.`user`(`id`),
  FOREIGN KEY (`userId2`) REFERENCES `klaus_kivbook`.`user`(`id`),
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE `klaus_kivbook`.`conversation`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId1` INT NOT NULL,
  `userId2` INT NOT NULL,
  FOREIGN KEY (`userId1`) REFERENCES `klaus_kivbook`.`user`(`id`),
  FOREIGN KEY (`userId2`) REFERENCES `klaus_kivbook`.`user`(`id`),
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE `klaus_kivbook`.`message`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `conversationId` INT NOT NULL,
  `text` MEDIUMTEXT NOT NULL,
  FOREIGN KEY (`userId`) REFERENCES `klaus_kivbook`.`user`(`id`),
  FOREIGN KEY (`conversationId`) REFERENCES `klaus_kivbook`.`conversation`(`id`),
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
