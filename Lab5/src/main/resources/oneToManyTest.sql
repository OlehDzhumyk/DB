CREATE SCHEMA IF NOT EXISTS `oleh`;
USE `oleh`;

select *from event;
select *from seats;

insert into seats (section, number, price, reservation_status, event_id)
values ('GREEN', '1', 1234, '1', 10);

update seats
set event_id = 1
where id = 20;

select * from seats;

update event
set id = 11
where id = 12;

delete
from seats
where id = 20;







