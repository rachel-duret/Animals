INSERT INTO app_user (id, firstname, lastname, password) VALUES (100, 'rachel', 'duret', 'password');
INSERT INTO app_user (id, firstname, lastname, password) VALUES (200, 'lara', 'duret', 'password');
INSERT INTO app_user (id, firstname, lastname, password) VALUES (300, 'antone', 'duret', 'password');
INSERT INTO app_user (id, firstname, lastname, password) VALUES (400, 'mia', 'zhen','password');
INSERT INTO app_user (id, firstname, lastname, password) VALUES (500, 'lala', 'duret','password');

INSERT INTO animal (id, name, type, user_id) VALUES (100, 'barbie', '0',100);
INSERT INTO animal (id, name, type, user_id) VALUES (200, 'gucci', '0',200);
INSERT INTO animal (id, name, type, user_id) VALUES (300, 'romeo', '1',400);
INSERT INTO animal (id, name, type, user_id) VALUES (400, 'yucca', '1',300);
INSERT INTO animal (id, name, type, user_id) VALUES (500, 'mimi', '2',500);

UPDATE app_user SET password = HASH('SHA256', password, 200000);