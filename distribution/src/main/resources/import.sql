INSERT into address( city, country, "number", street, zip_code) VALUES ('Backa Palanka', 'Srbija', '27', 'Blok Partizan', '21400');
INSERT into address( city, country, "number", street, zip_code) VALUES ('Novi Sad', 'Srbija', '33', 'Novosadskog sajma', '21000');
INSERT into address( city, country, "number", street, zip_code) VALUES ('Novi Sad', 'Srbija', '44', 'Strazilovska', '21000');
--
-- INSERT into company(address_id, rating, description, name) VALUES ( 1, 5, 'Opis 1', 'Kompa1');
-- INSERT into company(address_id, rating, description, name) VALUES ( 2, 4, 'Opis 2', 'Kompa2');
-- INSERT into company(address_id, rating, description, name) VALUES ( 3, 5, 'Opis 3', 'Ubisoft');

INSERT INTO ROLE (id,name) VALUES (1,'ROLE_USER');
INSERT INTO ROLE (id,name) VALUES (2,'ROLE_COMPANYADMIN');
INSERT INTO ROLE (id,name) VALUES (3,'ROLE_SYSTEMADMIN');




INSERT INTO app_user(address_id, company_info, email, name, password, phone, profession, surname, username)VALUES (1, 'adsa', 'user@gmail.com', 'Pera', '$2a$10$eO5z6Ul6K5vQdtZYRj1YtuAMe.TOoZCKuuFHasGuSMV9/EmnSQ1qO', '225-883', 'apotekar', 'Peric', 'pera');

INSERT INTO public.user_role(user_id, role_id) VALUES (1, 1);