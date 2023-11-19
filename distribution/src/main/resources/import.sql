INSERT into address(id, city, country, "number", street, zip_code) VALUES (1, 'Backa Palanka', 'Srbija', '27', 'Blok Partizan', '21400');
INSERT into address(id, city, country, "number", street, zip_code) VALUES (2, 'Novi Sad', 'Srbija', '33', 'Novosadskog sajma', '21000');
INSERT into address(id, city, country, "number", street, zip_code) VALUES (3, 'Novi Sad', 'Srbija', '44', 'Strazilovska', '21000');

INSERT into company(address_id, id, rating, description, name) VALUES (1, 1, 5, 'Opis 1', 'Kompa1');
INSERT into company(address_id, id, rating, description, name) VALUES (2, 2, 4, 'Opis 2', 'Kompa2');
INSERT into company(address_id, id, rating, description, name) VALUES (3, 3, 5, 'Opis 3', 'Ubisoft');

INSERT INTO user(address_id, id, company_info, email, name, password, phone, profession, surname, username)VALUES (1, 1, 'adsa', 'user@gmail.com', 'Pera', '12345', '225-883', 'apotekar', 'Peric', 'pere');