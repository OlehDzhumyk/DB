-- 2a test створення івенту через процедуру
SET @new_id = -1;
CALL insert_into_event('Mytyng', '666', 'Poroshenko street', @new_id);
select @new_id;
select *
from event;


-- 2b test створюю івент і юзера через процедуру (в табличці тікет новий квиток)
CALL insert_into_ticket_m_to_m('', '');
INSERT INTO user(name, second_name, phone, email, login_and_password_id) VALUES ('Dora', 'second naem', 'phph', 'email@ukrPail.con', 1);
INSERT INTO event(phone_number, address, date, name)
VALUES ('+00000000', 'Lviv station', '24.03.2023', 'Львів-Київ');
call insert_into_ticket_m_to_m('Львів-Київ', 'Dora');
select *
from ticket;


-- 2c test генерує 10 юзерів
CALL insert_10_records_into_user_table();
SELECT *
FROM user;


-- 2d test
SET @sum_result = -1;
CALL users_ids_sum(@sum_result);
SELECT @sum_result;


-- 2ei test
CALL create_2_tables_and_insert_data_dinamically();
SHOW TABLES;

select *
from dynamic_2022_11_22_17_05_47_number1;
select *
from dynamic_2022_11_22_17_05_47_number2;