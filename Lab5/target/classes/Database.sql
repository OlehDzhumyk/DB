CREATE SCHEMA IF NOT EXISTS `oleh`;
USE `oleh`;

DROP TABLE IF EXISTS ticket;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS seats;
DROP TABLE IF EXISTS login_and_password;
DROP TABLE IF EXISTS event;

CREATE TABLE event
(
    `id`           INT         NOT NULL AUTO_INCREMENT,
    `phone_number` VARCHAR(13) NULL DEFAULT NULL,
    `address`      VARCHAR(45) NULL DEFAULT NULL,
    `date`         VARCHAR(45) NULL,
    `name`         VARCHAR(45) NOT NULL,
    CONSTRAINT event_pk PRIMARY KEY (id)
#     PRIMARY KEY (`id`)
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
    CONSTRAINT seats_pk PRIMARY KEY (id)
#     PRIMARY KEY (`id`, `event_id`),
#     CONSTRAINT `fk_seats_event1`
#         FOREIGN KEY (`event_id`)
#             REFERENCES `event` (`id`)
);

CREATE TABLE user
(
    `id`                    INT          NOT NULL AUTO_INCREMENT,
    `name`                  VARCHAR(45)  DEFAULT NULL,
    `second_name`           VARCHAR(45)  DEFAULT NULL,
    `phone`                 VARCHAR(13)  DEFAULT NULL,
    `email`                 VARCHAR(100) NULL DEFAULT NULL,
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

INSERT INTO event (phone_number, address, date, name)
VALUES ('+38111111111', 'Shevchenko street', '23.05.2023', 'Концерт гурту Океан Ельзи'),
       ('+38222222222', 'Franko street', '24.03.2023', 'Концерт гурту Антитіла'),
       ('+38333333333', 'Liva street', '25.05.2023', 'Концерт  Злого репера Зеника'),
       ('+38444444444', 'Prava street', '15.07.2023', 'Концерт Петра Щура'),
       ('+38555555555', 'Lviv station', '23.05.2023', 'Львів-Дніпро'),
       ('+38666666666', 'Lviv station', '24.03.2023', 'Львів-Київ'),
       ('+38777777777', 'Lviv station', '29.02.2023', 'Львів-Одеса'),
       ('+38888888888', 'Lviv airport', '23.05.2023', 'Львів-Берлін'),
       ('+38999999999', 'Kyiv airport', '02.11.2023', 'Київ-Париж'),
       ('+38101010101', 'Kyiv airport', '27.09.2023', 'Київ-Вашингтон');

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
       ('j', '10', '100', '0', '10'),

       ('b', '2', '20', '0', '1'),
       ('c', '3', '30', '1', '1'),
       ('d', '4', '40', '0', '1'),
       ('e', '5', '50', '1', '1'),
       ('f', '6', '60', '0', '1'),
       ('g', '7', '70', '1', '1'),
       ('h', '8', '80', '1', '1'),
       ('i', '9', '90', '0', '1'),
       ('j', '10', '100', '0', '10')
;

INSERT INTO user (`name`, `second_name`, `phone`, `email`, `login_and_password_id`)
VALUES ('Oleh ', 'Dzhumyk ', '+111111111111', 'example.email1@gmail.com', '1'),
       ('Person2 ', 'secondname2 ', '+22222222222', 'example.email2@gmail.com', '2'),
       ('Person3 ', 'secondname3 ', '+33333333333', 'example.email3@gmail.com', '3'),
       ('Person4 ', 'secondname4 ', '+44444444444', 'example.email4@gmail.com', '4'),
       ('Person5 ', 'secondname5 ', '+55555555555', 'example.email5@gmail.com', '5'),
       ('Person6 ', 'secondname6 ', '+66666666666', 'example.email6@gmail.com', '6'),
       ('Person7 ', 'secondname7 ', '+77777777777', 'example.email7@gmail.com', '7'),
       ('Person8 ', 'secondname8 ', '+88888888888', 'example.email8@gmail.com', '8'),
       ('Person9 ', 'secondname9 ', '+99999999999', 'example.email9@gmail.com', '9');

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


DROP TRIGGER IF EXISTS before_insert_event_1tm_check;
DROP TRIGGER IF EXISTS before_update_event_1tm_check;
DROP TRIGGER IF EXISTS before_update_seats_1tm_check;
DROP TRIGGER IF EXISTS before_delete_seats_1tm_check;

DELIMITER //
CREATE TRIGGER before_insert_seats_1tm_check
    BEFORE INSERT ON seats FOR EACH ROW
BEGIN
    DECLARE id_match INT;
    select count(*) into id_match from seats where id = NEW.event_id;
    IF id_match = 0 THEN
        SIGNAL SQLSTATE '22000'
            SET MESSAGE_TEXT = 'A foreign key constraint fails for event_id';
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER before_update_seats_1tm_check
    BEFORE UPDATE ON seats FOR EACH ROW
BEGIN
    DECLARE id_match INT;
    IF OLD.event_id <> NEW.event_id THEN
        select count(*) into id_match from seats where id = NEW.event_id;
        IF id_match = 0 THEN
            SIGNAL SQLSTATE '22000'
                SET MESSAGE_TEXT = 'A foreign key constraint fails for event_id';
        END IF;
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER before_update_event_1tm_check
    BEFORE UPDATE ON event FOR EACH ROW
BEGIN
    DECLARE is_used_count INT;
    IF OLD.id <> NEW.id THEN
        select count(*) into is_used_count from seats where seats.event_id = OLD.id;
        IF is_used_count > 0 THEN
            SIGNAL SQLSTATE '22000'
                SET MESSAGE_TEXT = 'A foreign key constraint fails for event_id';
        END IF;
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER before_delete_event_1tm_check
    BEFORE DELETE ON event FOR EACH ROW
BEGIN
    DECLARE is_used_count INT;
    select count(*) into is_used_count from seats where event_id = OLD.id;
    IF is_used_count > 0 THEN
        SIGNAL SQLSTATE '22000'
            SET MESSAGE_TEXT = 'A foreign key constraint fails for event_id in seats table';
    END IF;
END //
DELIMITER ;

-- 2a parametrized insertion into mine_sight table
DROP PROCEDURE IF EXISTS insert_into_event;
DELIMITER //
CREATE PROCEDURE insert_into_event(
    IN name_p varchar(45),
    IN phone_number_p varchar(13),
    IN address_p varchar(45),
    OUT id_p INT)
BEGIN
    INSERT INTO event(phone_number, address, name) VALUES
        (phone_number_p, address_p, name_p);
    SELECT id INTO id_p FROM event WHERE name=name_p;
END //
DELIMITER ;


-- 2b insert into m-to-m table by infering IDs from other real values;
DROP PROCEDURE IF EXISTS insert_into_ticket_m_to_m;
DELIMITER //
CREATE PROCEDURE insert_into_ticket_m_to_m(
    IN event_name VARCHAR(50),
    IN user_name VARCHAR(50)
)
BEGIN
    DECLARE user_id_val INT;
    DECLARE event_id_val INT;
    SELECT id INTO user_id_val FROM user WHERE name = user_name LIMIT 1;
    SELECT id INTO event_id_val FROM event WHERE name = event_name LIMIT 1;
    IF user_id_val is NULL or event_id_val is NULL THEN
        SIGNAL SQLSTATE '22000'
            SET MESSAGE_TEXT = 'event or user record not found';
    END IF;
    INSERT INTO ticket(user_id, event_id) VALUES
        (user_id_val, event_id_val);
END //
DELIMITER ;


-- 2c insert 10 records into company table
DROP PROCEDURE IF EXISTS insert_10_records_into_user_table;
DELIMITER //
CREATE PROCEDURE insert_10_records_into_user_table()
BEGIN
    DECLARE i INT unsigned DEFAULT 0;
    START transaction;
    WHILE i < 10 DO
            INSERT INTO user(name, second_name, phone, email, login_and_password_id) VALUE (concat("name", i), 'second naem', 'phph', 'email@ukrPail.con', 1);
            SET i = i + 1;
        END WHILE;
    COMMIT;
END //
DELIMITER ;


-- 2d custom function (product) and stored procedure that uses this function
DROP FUNCTION IF EXISTS ids_sum;
DELIMITER //
CREATE FUNCTION ids_sum()
    RETURNS INT DETERMINISTIC
BEGIN
    RETURN (SELECT sum(id) FROM user);
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS users_ids_sum;
DELIMITER //
CREATE PROCEDURE users_ids_sum(OUT result_sum INT)
BEGIN
    SELECT ids_sum() INTO result_sum;
END //
DELIMITER ;

-- 2ei stored procedure with cursor
DROP PROCEDURE IF EXISTS create_2_tables_and_insert_data_dinamically;
DELIMITER //
CREATE PROCEDURE create_2_tables_and_insert_data_dinamically()
BEGIN
    DECLARE table_name_1 VARCHAR(255);
    DECLARE table_name_2 VARCHAR(255);
    DECLARE table_schema VARCHAR(255) DEFAULT '(
    id int NOT NULL AUTO_INCREMENT,
    title varchar(50) NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id)
	);';
    DECLARE end_of_tables INT DEFAULT 0;
    DECLARE user_name_memorized VARCHAR(255);

    DECLARE cur CURSOR FOR
        SELECT name from  user;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET end_of_tables = 1;

    SET @tmstmp_prepared = REGEXP_REPLACE(current_timestamp(), '[-: ]', '_');
    SET table_name_1 = CONCAT('dynamic_', @tmstmp_prepared, '_number1');
    SET @s = CONCAT('CREATE TABLE ', table_name_1, table_schema);
    PREPARE stmt FROM @s;
    EXECUTE stmt;

    SET @tmstmp_prepared = REGEXP_REPLACE(current_timestamp(), '[-: ]', '_');
    SET table_name_2 = CONCAT('dynamic_', @tmstmp_prepared, '_number2');
    SET @s = CONCAT('CREATE TABLE ', table_name_2, table_schema);
    PREPARE stmt FROM @s;
    EXECUTE stmt;

    OPEN cur;
    tables_loop: LOOP
        FETCH cur INTO user_name_memorized;

        IF end_of_tables = 1 THEN
            LEAVE tables_loop;
        END IF;

        SET @rand_num_1_or_0 = FLOOR(RAND()*10)%2;

        IF @rand_num_1_or_0 = 0 THEN
            SET @s = CONCAT('INSERT INTO ', table_name_1, '(title) VALUES (''' , user_name_memorized, ''')');
            PREPARE stmt FROM @s;
            EXECUTE stmt;
        END IF;

        IF @rand_num_1_or_0 = 1 THEN
            SET @s = CONCAT('INSERT INTO ', table_name_2, '(title) VALUES (''' , user_name_memorized, ''')');
            PREPARE stmt FROM @s;
            EXECUTE stmt;
        END IF;
    END LOOP;

    CLOSE cur;
END //
DELIMITER ;
