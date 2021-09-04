ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, role)
VALUES ('User', 'user@yandex.ru', '$2a$12$LABH.nBV10WEJClJyZzXe.wJOGTe8YbnlQYg6iBiBQ3zh7/x7PAAS', 'USER'),
       ('User2', 'user2@yandex.ru', '$2a$12$LABH.nBV10WEJClJyZzXe.wJOGTe8YbnlQYg6iBiBQ3zh7/x7PAAS', 'USER'),
       ('Admin', 'admin@gmail.com', '$2a$12$oe3AC1Q3D2s9jVyBtYzAd.QaYimbBOtAffzRwwLwsnnM9/7zztfvi', 'ADMIN');

INSERT INTO restaurant (name)
VALUES ('Восточная кухня'),
       ('Бухта'),
       ('Советская столовая');

INSERT INTO vote (restaurant_id, user_id, datetime)
VALUES (100005, 100000, '2020-06-01'),
       (100005, 100001, '2020-06-01');

INSERT INTO menu (restaurant_id)
VALUES (100003),
       (100004);

INSERT INTO meal (menu_id, name, price)
VALUES (100009, 'Каша молочная', 50.0),
       (100009, 'Суп овощной', 100);