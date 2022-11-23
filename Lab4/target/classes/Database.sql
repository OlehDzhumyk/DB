CREATE SCHEMA IF NOT EXISTS `oleh`;
USE `oleh`;

DROP TABLE IF EXISTS ticket;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS seats;
DROP TABLE IF EXISTS login_and_password;
DROP TABLE IF EXISTS event;

CREATE TABLE event
(
    `id`                INT          NOT NULL AUTO_INCREMENT,
    `organizer`         VARCHAR(45)  NULL,
    `phone_number`      VARCHAR(13)  NULL DEFAULT NULL,
    `address`           VARCHAR(45)  NULL DEFAULT NULL,
    `date`              VARCHAR(45)  NULL,
    `duration`          VARCHAR(45)  NULL DEFAULT NULL,
    `event_information` VARCHAR(100) NULL DEFAULT NULL,
    `name`              VARCHAR(45)  NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE login_and_password
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE seats
(
    `id`                 INT         NOT NULL AUTO_INCREMENT,
    `section`            VARCHAR(45) NULL     DEFAULT NULL,
    `number`             VARCHAR(45) NOT NULL,
    `price`              INT         NOT NULL,
    `reservation_status` VARCHAR(45) NOT NULL DEFAULT 'not_reserved',
    `event_id`           INT         NOT NULL,
    PRIMARY KEY (`id`, `event_id`),
    CONSTRAINT `fk_seats_event1`
        FOREIGN KEY (`event_id`)
            REFERENCES `event` (`id`)
);

CREATE TABLE user
(
    `id`                    INT          NOT NULL AUTO_INCREMENT,
    `name`                  VARCHAR(45)  NOT NULL,
    `second_name`           VARCHAR(45)  NOT NULL,
    `phone`                 VARCHAR(13)  NOT NULL,
    `email`                 VARCHAR(100) NULL DEFAULT NULL,
    `city`                  VARCHAR(100) NULL DEFAULT NULL,
    `region`                VARCHAR(100) NULL DEFAULT NULL,
    `login_and_password_id` INT          NOT NULL,
    PRIMARY KEY (`id`, `login_and_password_id`),
    CONSTRAINT `fk_user_login_and_password1`
        FOREIGN KEY (`login_and_password_id`)
            REFERENCES `login_and_password` (`id`)
);

CREATE TABLE ticket
(
    `id`             INT NOT NULL AUTO_INCREMENT,
    `payment_status` INT NULL DEFAULT NULL,
    `user_id`        INT NOT NULL,
    `event_id`       INT NOT NULL,
    PRIMARY KEY (`id`, `event_id`, `user_id`),
    CONSTRAINT `fk_ticket_user1`
        FOREIGN KEY (`user_id`)
            REFERENCES `user` (`id`),
    CONSTRAINT `fk_ticket_event1`
        FOREIGN KEY (`event_id`)
            REFERENCES `event` (`id`)
);

INSERT INTO event (organizer, phone_number, address, date, duration, event_information, name)
VALUES ('Karabas', '+38111111111', 'Shevchenko street', '23.05.2023', '5 hours', 'Ну типу буде Святік виступати',
        'Концерт гурту Океан Ельзи'),
       ('Karabas', '+38222222222', 'Franko street', '24.03.2023', '6 hours', 'Ну типу буде Тарас виступати',
        'Концерт гурту Антитіла'),
       ('Karabas', '+38333333333', 'Liva street', '25.05.2023', '99 hours', 'Ну типу буде Репер Зеник виступати',
        'Концерт  Злого репера Зеника'),
       ('Karabas', '+38444444444', 'Prava street', '15.07.2023', '1 hours', 'Скільки...?', 'Концерт Петра Щура'),
       ('UKR Zaliznytsya', '+38555555555', 'Lviv station', '23.05.2023', '20 hours', 'Плацкарт', 'Львів-Дніпро'),
       ('UKR Zaliznytsya', '+38666666666', 'Lviv station', '24.03.2023', '7 hours', 'Плацкарт', 'Львів-Київ'),
       ('UKR Zaliznytsya', '+38777777777', 'Lviv station', '29.02.2023', '10 hours', 'Купе', 'Львів-Одеса'),
       ('Ryanair', '+38888888888', 'Lviv airport', '23.05.2023', '3 hours', 'Ну типу буде Святік виступати',
        'Львів-Берлін'),
       ('Ryanair', '+38999999999', 'Kyiv airport', '02.11.2023', '4 hours', 'Ну типу буде Святік виступати',
        'Київ-Париж'),
       ('Ryanair', '+38101010101', 'Kyiv airport', '27.09.2023', '20 hours', 'Ну типу буде Святік виступати',
        'Київ-Вашингтон');

INSERT INTO login_and_password (`login`, `password`)
VALUES ('a', '1'),
       ('b', '2'),
       ('c', '3'),
       ('d', '4'),
       ('e', '5'),
       ('f', '6'),
       ('g', '7'),
       ('h', '8'),
       ('i', '9'),
       ('j', '10');

INSERT INTO seats (`section`, `number`, `price`, `reservation_status`, `event_id`)
VALUES ('a', '1', '10', '1', '1'),
       ('b', '2', '20', '0', '2'),
       ('c', '3', '30', '1', '3'),
       ('d', '4', '40', '0', '4'),
       ('e', '5', '50', '1', '5'),
       ('f', '6', '60', '0', '6'),
       ('g', '7', '70', '1', '7'),
       ('h', '8', '80', '1', '8'),
       ('i', '9', '90', '0', '9'),
       ('j', '10', '100', '0', '10');

INSERT INTO user (`name`, `second_name`, `phone`, `email`, `city`, `region`, `login_and_password_id`)
VALUES ('Oleh ', 'Dzhumyk ', '+111111111111', 'example.email1@gmail.com', 'Novoyavorivsk', 'lviv', '1'),
       ('Person2 ', 'secondname2 ', '+22222222222', 'example.email2@gmail.com', 'Novoyavorivsk', 'lviv', '2'),
       ('Person3 ', 'secondname3 ', '+33333333333', 'example.email3@gmail.com', 'Novoyavorivsk', 'lviv', '3'),
       ('Person4 ', 'secondname4 ', '+44444444444', 'example.email4@gmail.com', 'Sevastopol', 'Crimea', '4'),
       ('Person5 ', 'secondname5 ', '+55555555555', 'example.email5@gmail.com', 'Sevastopol', 'Crimea', '5'),
       ('Person6 ', 'secondname6 ', '+66666666666', 'example.email6@gmail.com', 'Sevastopol', 'Crimea', '6'),
       ('Person7 ', 'secondname7 ', '+77777777777', 'example.email7@gmail.com', 'Kyiv', 'Kyiv', '7'),
       ('Person8 ', 'secondname8 ', '+88888888888', 'example.email8@gmail.com', 'Kyiv', 'Kyiv', '8'),
       ('Person9 ', 'secondname9 ', '+99999999999', 'example.email9@gmail.com', 'Kyiv', 'Kyiv', '9');

INSERT INTO ticket (`payment_status`, `user_id`, `event_id`)
VALUES ('1', '1', '2'),
       ('0', '2', '3'),
       ('1', '3', '4'),
       ('0', '4', '5'),
       ('1', '5', '6'),
       ('0', '6', '7'),
       ('1', '7', '8'),
       ('0', '8', '9'),
       ('1', '9', '1');
