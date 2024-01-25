INSERT into address( city, country, "number", street, zip_code) VALUES ('Backa Palanka', 'Srbija', '27', 'Blok Partizan', '21400');
INSERT into address( city, country, "number", street, zip_code) VALUES ('Novi Sad', 'Srbija', '33', 'Novosadskog sajma', '21000');
INSERT into address( city, country, "number", street, zip_code) VALUES ('Novi Sad', 'Srbija', '44', 'Strazilovska', '21000');
INSERT into address( city, country, "number", street, zip_code) VALUES ('Novi Sad', 'Srbija', '11', 'Fruskogorska', '21000');
INSERT into address( city, country, "number", street, zip_code) VALUES ('Novi Sad', 'Srbija', '12', 'Puskinova', '21000');
--
--
INSERT INTO ROLE (id,name) VALUES (1,'ROLE_USER');
INSERT INTO ROLE (id,name) VALUES (2,'ROLE_COMPANYADMIN');
INSERT INTO ROLE (id,name) VALUES (3,'ROLE_SYSTEMADMIN');
--
INSERT into company(address_id, rating, description, name, working_hours_begin,working_hours_end) VALUES ( 1, 5, 'Opis 1', 'Kompa1','07:00:00','15:00:00');
INSERT into company(address_id, rating, description, name, working_hours_begin,working_hours_end) VALUES ( 2, 4, 'Opis 2', 'Kompa2','08:00:00','16:00:00');
INSERT into company(address_id, rating, description, name, working_hours_begin,working_hours_end) VALUES ( 3, 5, 'Opis 3', 'Ubisoft','09:00:00','17:00:00');
--
-- //za sve korisnike password je "password123"
INSERT INTO app_user(email, password, username)VALUES ('user@gmail.com', '$2a$10$eO5z6Ul6K5vQdtZYRj1YtuAMe.TOoZCKuuFHasGuSMV9/EmnSQ1qO', 'pera');
INSERT INTO app_user(email, password, username)VALUES ('nikola@gmail.com', '$2a$10$eO5z6Ul6K5vQdtZYRj1YtuAMe.TOoZCKuuFHasGuSMV9/EmnSQ1qO', 'nidza');
INSERT INTO app_user(email, password, username)VALUES ('mika@gmail.com', '$2a$10$eO5z6Ul6K5vQdtZYRj1YtuAMe.TOoZCKuuFHasGuSMV9/EmnSQ1qO', 'mika');
INSERT INTO app_user(email, password, username)VALUES ('nenad@gmail.com', '$2a$10$eO5z6Ul6K5vQdtZYRj1YtuAMe.TOoZCKuuFHasGuSMV9/EmnSQ1qO', 'nenad');

INSERT INTO basic_user(user_id,address_id,name,surname,phone,profession) VALUES (1,4,'Mika','Mikic','225-222','Doktor');
INSERT INTO basic_user(user_id,address_id,name,surname,phone,profession) VALUES (2,5,'Milos','Milosevic','225-212','Apotekar');

INSERT INTO company_admin(user_id,company_id,name,surname) VALUES (3,1,'Pera','Peric');
INSERT INTO company_admin(user_id,company_id,name,surname) VALUES (4,1,'Nenad','Nenadic');

INSERT INTO public.user_role(user_id, role_id) VALUES (1, 1);
INSERT INTO public.user_role(user_id, role_id) VALUES (2, 1);
INSERT INTO public.user_role(user_id, role_id) VALUES (3, 2);
INSERT INTO public.user_role(user_id, role_id) VALUES (4, 2);
--
--
--
--
--
