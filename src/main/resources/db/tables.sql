CREATE SCHEMA `klaus_kivbook`;

CREATE TABLE `klaus_kivbook`.`user`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(36) NOT NULL,
  `first_name` VARCHAR(36) NOT NULL,
  `last_name` VARCHAR(36) NOT NULL,
  `birthday` DATE NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `sex` ENUM('male', 'female') NOT NULL,
  `picture` VARCHAR(255),
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

INSERT INTO `klaus_kivbook`.`user` (username, first_name, last_name, birthday, email, password, sex, picture) VALUES
  ('user1', 'User', 'One', '1994-01-01', 'user1@kivbook.com', '4675efbec98b0e96df6db24b6947442e2972044c', 'male',
   NULL),
  ('user2', 'User', 'Two', '1994-01-02', 'user2@kivbook.com', '4675efbec98b0e96df6db24b6947442e2972044c', 'male',
   NULL),
  ('user3', 'User', 'Three', '1994-03-01', 'user3@kivbook.com', '4675efbec98b0e96df6db24b6947442e2972044c', 'female',
   NULL);
