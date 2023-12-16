INSERT into address( city, country, "number", street, zip_code) VALUES ('Backa Palanka', 'Srbija', '27', 'Blok Partizan', '21400');
INSERT into address( city, country, "number", street, zip_code) VALUES ('Novi Sad', 'Srbija', '33', 'Novosadskog sajma', '21000');
INSERT into address( city, country, "number", street, zip_code) VALUES ('Novi Sad', 'Srbija', '44', 'Strazilovska', '21000');

INSERT into company(address_id, rating, description, name, working_hours_begin,working_hours_end) VALUES ( 1, 5, 'Opis 1', 'Kompa1','07:00:00','15:00:00');
INSERT into company(address_id, rating, description, name, working_hours_begin,working_hours_end) VALUES ( 2, 4, 'Opis 2', 'Kompa2','08:00:00','16:00:00');
INSERT into company(address_id, rating, description, name, working_hours_begin,working_hours_end) VALUES ( 3, 5, 'Opis 3', 'Ubisoft','09:00:00','17:00:00');

INSERT INTO app_user(address_id, company_info, email, name, password, phone, profession, surname, username)VALUES (1, 'adsa', 'user@gmail.com', 'Pera', '12345', '225-883', 'apotekar', 'Peric', 'pere');