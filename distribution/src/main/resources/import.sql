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
INSERT into company(address_id, rating, description, name, working_hours_begin,working_hours_end) VALUES ( 1, 5, 'Opis 1', 'HealthDelivery','07:00:00','15:00:00');
INSERT into company(address_id, rating, description, name, working_hours_begin,working_hours_end) VALUES ( 2, 4, 'Opis 2', 'MedicCompany','08:00:00','16:00:00');
INSERT into company(address_id, rating, description, name, working_hours_begin,working_hours_end) VALUES ( 3, 5, 'Opis 3', 'GalenPharm','09:00:00','17:00:00');
--
-- //za sve korisnike password je "password123"
INSERT INTO app_user(email, password, username)VALUES ('user@gmail.com', '$2a$10$eO5z6Ul6K5vQdtZYRj1YtuAMe.TOoZCKuuFHasGuSMV9/EmnSQ1qO', 'pera');
INSERT INTO app_user(email, password, username)VALUES ('nikola@gmail.com', '$2a$10$eO5z6Ul6K5vQdtZYRj1YtuAMe.TOoZCKuuFHasGuSMV9/EmnSQ1qO', 'nidza');
INSERT INTO app_user(email, password, username)VALUES ('mika@gmail.com', '$2a$10$eO5z6Ul6K5vQdtZYRj1YtuAMe.TOoZCKuuFHasGuSMV9/EmnSQ1qO', 'mika');
INSERT INTO app_user(email, password, username)VALUES ('nenad@gmail.com', '$2a$10$eO5z6Ul6K5vQdtZYRj1YtuAMe.TOoZCKuuFHasGuSMV9/EmnSQ1qO', 'nenad');
INSERT INTO app_user(email, password, username)VALUES ('luka@gmail.com', '$2a$10$eO5z6Ul6K5vQdtZYRj1YtuAMe.TOoZCKuuFHasGuSMV9/EmnSQ1qO', 'luka');

INSERT INTO basic_user(user_id,address_id,name,surname,phone,profession, penalty,is_active) VALUES (1,4,'Pera','Peric','225-222','Doktor',0,true);
INSERT INTO basic_user(user_id,address_id,name,surname,phone,profession, penalty,is_active) VALUES (2,5,'Nikola','Nikolic','225-212','Apotekar',0,true);

INSERT INTO company_admin(user_id,company_id,name,surname) VALUES (3,1,'Mika','Mikic');
INSERT INTO company_admin(user_id,company_id,name,surname) VALUES (4,2,'Nenad','Nenadic');
INSERT INTO company_admin(user_id,company_id,name,surname) VALUES (5,2,'Luka','Lukic');

INSERT INTO public.user_role(user_id, role_id) VALUES (1, 1);
INSERT INTO public.user_role(user_id, role_id) VALUES (2, 1);
INSERT INTO public.user_role(user_id, role_id) VALUES (3, 2);
INSERT INTO public.user_role(user_id, role_id) VALUES (4, 2);
INSERT INTO public.user_role(user_id, role_id) VALUES (5, 2);

INSERT INTO equipment(company_id,name,description,price,count,is_deleted) VALUES (1,'Brufen 500','Brufen 500mg, 10 komada',500,15,false);
INSERT INTO equipment(company_id,name,description,price,count,is_deleted) VALUES (1,'Brufen 300','Brufen 300mg, 10 komada',450,20,false);
INSERT INTO equipment(company_id,name,description,price,count,is_deleted) VALUES (1,'Hiruske rukavice','Hiruske rukavice, pakovanje od 100 komada',750, 13,false);
INSERT INTO equipment(company_id,name,description,price,count,is_deleted) VALUES (1,'Febricet','Febricet pakovanje od 20 komada',250,40,false);
INSERT INTO equipment(company_id,name,description,price,count,is_deleted) VALUES (1,'Fervex Malina','Fervex za odrasle sa ukusom maline',810,11,false);
INSERT INTO equipment(company_id,name,description,price,count,is_deleted) VALUES (2, 'Pantenol','Krema za opekotine',430,18,false);
INSERT INTO equipment(company_id,name,description,price,count,is_deleted) VALUES (2, 'Nivea krema','Krema za ruke i lice',340,21,false);
INSERT INTO equipment(company_id,name,description,price,count,is_deleted) VALUES (2, 'Andol','Pakovanje od 20 komada',300,51,false);

INSERT INTO appointment(company_id,administrator_name,administrator_surname,admin_user_id,date_and_time,duration,is_free) VALUES (1,'Mika','Mikic', 3,'2024-02-02 08:30:00',30,false);
INSERT INTO appointment(company_id,administrator_name,administrator_surname,admin_user_id,date_and_time,duration,is_free) VALUES (2,'Nenad','Nenadic', 4,'2024-02-02 8:30:00',15,false);
INSERT INTO appointment(company_id,administrator_name,administrator_surname,admin_user_id,date_and_time,duration,is_free) VALUES (2,'Nenad','Nenadic', 4,'2024-02-02 8:50:00',15,false);
INSERT INTO appointment(company_id,administrator_name,administrator_surname,admin_user_id,date_and_time,duration,is_free) VALUES (2,'Luka','Lukic', 5,'2024-02-02 09:50:00',10,true);

INSERT INTO purchase_order(company_admin_id,customer_id,appointment_id,status) VALUES (1,1,1,'ON_HOLD');
INSERT INTO purchase_order(company_admin_id,customer_id,appointment_id,status) VALUES (2,1,2,'ON_HOLD');
INSERT INTO purchase_order(company_admin_id,customer_id,appointment_id,status) VALUES (2,2,3,'ON_HOLD');

INSERT INTO order_equipment(equipment_id,order_id,quantity) VALUES (1,1,5);
INSERT INTO order_equipment(equipment_id,order_id,quantity) VALUES (2,1,3);
INSERT INTO order_equipment(equipment_id,order_id,quantity) VALUES (6,2,4);
INSERT INTO order_equipment(equipment_id,order_id,quantity) VALUES (7,2,3);
INSERT INTO order_equipment(equipment_id,order_id,quantity) VALUES (8,2,4);
