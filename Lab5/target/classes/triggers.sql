CREATE SCHEMA IF NOT EXISTS `oleh`;
USE `oleh`;

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