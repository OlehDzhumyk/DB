use labor_sql;

-- 1. БД «Комп. фірма». Отримати інформацію про комп’ютери, що
-- мають частоту процесора не менше 500 МГц та ціну нижче 800 дол.
-- Вихідні дані впорядкувати за спаданням за стовпцем price.
SELECT * FROM PC 
WHERE speed >= 500 and price < 800
ORDER BY price DESC;

-- 2. БД «Комп. фірма». Вивести всі моделі ПК, у номерах яких є хоча б
-- дві одинички.

SELECT model FROM pc WHERE model rlike '.*1.*1.*';

-- 3. БД «Кораблі». Для кораблів таблиці Ships вивести їх водотоннаж-
-- ність.

SELECT  name ,displacement 
FROM classes JOIN ships ON ships.class = classes.class;


-- 4. БД «Комп. фірма». Знайти моделі та ціни ноутбуків, вартість яких є
-- вищою за вартість будь-якого ПК. Вивести: model, price.

SELECT model, price FROM laptop 
WHERE laptop.price > ( SELECT MAX(price) FROM pc) ;


-- 5. БД «Комп. фірма». Знайдіть виробників, які б випускали ноутбуки
-- зі швидкістю 500 МГц та нижче. Виведіть: maker.

SELECT DISTINCT maker FROM product JOIN laptop 
ON laptop.model = product.model
WHERE speed <= 500;

-- 6. БД «Кораблі». Вивести значення таблиці Ships із коментарями,
-- наприклад, 'name: California', 'class: Tennessee', 'launched: 1921'.

SElECt CONCAT( 'name: ', name) as name, CONCAT("class: ", class) as class, CONCAT( 'launched: ', launched) as launched FROM ships;

-- 7. БД «Аеропорт». Визначіть дні, коли було виконано максимальну
-- кількість рейсів із міста 'Rostov'. Вивести: date, число рейсів.

SElECT time_out, MAX((SELECT COUNT(town_from) FROM trip WHERE town_from = 'Rostov')) AS 'number of flight' FROM trip;

-- 8. БД «Комп. фірма». Знайsдіть виробників, які б випускали ноутбуки з
-- мінімальною швидкістю не менше 600 МГц. Вивести: maker,
-- мінімальна швидкість. (Підказка: використовувати підзапити в якості
-- обчислювальних стовпців)

SELECT DISTINCT maker AS maker_of_laptop, 
(SELECT MIN(speed) FROM product JOIN laptop on product.model = laptop.model 
WHERE product.maker = maker_of_laptop and speed >=600) as min_speed  
FROM product JOIN laptop on product.model = laptop.model
WHERE speed >= 600;

-- 9. БД «Кораблі». Визначити назви всіх кораблів із таблиці Ships, які
-- задовольняють, у крайньому випадку, комбінації будь-яких чотирьох
-- критеріїв із наступного списку: numGuns=8, bore=15,
-- displacement=32000, type='bb', country='USA', launched=1915,
-- class='Kongo'. Вивести: name, numGuns, bore, displacement, type,
-- country, launched, class. (Підказка: використати для перевірки умов
-- оператор CASE)

SELECT name, numGuns, bore, displacement, type, country, launched, s.class
FROM Ships AS s JOIN Classes AS cl1 ON s.class = cl1.class
WHERE
CASE WHEN numGuns = 8 THEN 1 ELSE 0 END +
CASE WHEN bore = 15 THEN 1 ELSE 0 END +
CASE WHEN displacement = 32000 THEN 1 ELSE 0 END +
CASE WHEN type = 'bb' THEN 1 ELSE 0 END +
CASE WHEN launched = 1915 THEN 1 ELSE 0 END +
CASE WHEN s.class = 'Kongo' THEN 1 ELSE 0 END +
CASE WHEN country = 'USA' THEN 1 ELSE 0 END >= 4;

-- 10. БД «Кораблі». Знайдіть класи, у які входять лише один або два
-- кораблі із цілої БД (врахувати також кораблі в таблиці Outcomes, яких
-- немає в таблиці Ships). Вивести: class, кількість кораблів у класі.
-- (Підказка: використовувати оператор UNION та операцію EXISTS)

SELECT ship_name FROM(
SELECT ship_name, sum(count_in_table) as count FROM(
SELECT name as ship_name, 't1' as col, count(*) as count_in_table from ships Group by name
UNION
SELECT ship , 't2' as col, count(*) as count_in_table FROM outcomes Group by ship) tab1
Group by ship_name)tab2
WHERE count = 1
order by ship_name ;
 

 
 
 


