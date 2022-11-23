USE oleh;


-- 3a: значення певного стовпця не може закінчувати двома нулями
DROP TRIGGER IF EXISTS check_for_00_at_user_name_end_before_insert;
DELIMITER //
CREATE TRIGGER check_for_00_at_user_name_end_before_insert
    BEFORE INSERT ON user FOR EACH ROW
BEGIN
    IF RIGHT(NEW.name, 2) = '00' THEN
        SIGNAL SQLSTATE '22000'
            SET MESSAGE_TEXT = 'Name cannot end with ''00''';
    END IF;
END //
DELIMITER ;

DROP TRIGGER IF EXISTS check_for_00_at_user_name_end_before_update;
DELIMITER //
CREATE TRIGGER check_for_00_at_user_name_end_before_update
    BEFORE UPDATE ON user FOR EACH ROW
BEGIN
    IF RIGHT(NEW.name, 2) = '00' THEN
        SIGNAL SQLSTATE '22000'
            SET MESSAGE_TEXT = 'User cannot end with ''00''';
    END IF;
END //
DELIMITER ;

# test
INSERT INTO user(name) VALUES ('Andriy00');


-- 3b: Заборонити будь-яку модифікацію даних в таблиці
DROP TRIGGER IF EXISTS restrict_modification_of_login_and_password_table;
DELIMITER //
CREATE TRIGGER restrict_modification_of_login_and_password_table
    BEFORE UPDATE ON login_and_password FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '22000'
        SET MESSAGE_TEXT = 'You can not update this table.';
END //
DELIMITER ;

-- test
UPDATE login_and_password SET login = 1 WHERE id =1;

-- 3j Для стовпця name table driver допускається ввід лише таких імен: 'Svitlana', 'Petro', 'Olha', 'Taras'.
DROP TRIGGER IF EXISTS only_top_names_accepted_in_user_insertion;
DELIMITER //
CREATE TRIGGER only_top_names_accepted_in_user_insertion
    BEFORE insert ON user FOR EACH ROW
BEGIN
    IF NEW.name NOT IN ('Svitlana', 'Petro', 'Olha', 'Taras') THEN
        SIGNAL SQLSTATE '22000'
            SET MESSAGE_TEXT = 'ONLY Svitlana, Petro, Olha, Taras can be users';
    END IF;
END //
DELIMITER ;

-- test
insert into user (name) values ('Ne_Svitlana');