insert into user (id, email, password, status, nickname, bio, dob, gender, phone_number) values (1, 'test1@gmail.com', '21232f297a57a5a743894a0e4a801fc3', 'ACTIVE', 'Teddy', 'wanderlust', '31/10/1991', 'male', '0964988953');
insert into user (id, email, password, status, nickname, bio, dob, gender, phone_number) values (2, 'test2@gmail.com', '21232f297a57a5a743894a0e4a801fc3', 'ACTIVE', 'Andy', 'wanderlust', '31/10/1991', 'male', '0964988953');
insert into user (id, email, password, status, nickname, bio, dob, gender, phone_number) values (3, 'test3@gmail.com', '21232f297a57a5a743894a0e4a801fc3', 'ACTIVE', 'Anna', 'wanderlust', '31/10/1991', 'female', '0964988953');

insert into user_role (user_id, role) values (1, 'ADMIN');
insert into user_role (user_id, role) values (1, 'MODERATOR');
insert into user_role (user_id, role) values (2, 'BUYER');
insert into user_role (user_id, role) values (3, 'BUYER');

insert into shipping_address (id, city, state, street, zip_code, user_id) values (1, 'FAIRFIELD', 'IOWA', '4TH STREET', '98765', 2);
insert into shipping_address (id, city, state, street, zip_code, user_id) values (2, 'FAIRFIELD2', 'IOWA2', '4TH STREET2', '987652', 3);

insert into category (id, name) values (1, 'Clothing');
insert into category (id, name) values (2, 'Furniture');
insert into category (id, name) values (3, 'TV');

insert into product (id, available_quantity, name, unit_price, created_by, shop_id) values (1, 100, 'Clothing 1', 200, 2, null);
insert into product (id, available_quantity, name, unit_price, created_by, shop_id) values (2, 200, 'Clothing 2', 400, 3, null);
insert into product (id, available_quantity, name, unit_price, created_by, shop_id) values (3, 300, 'Furniture 1', 1000, 2, null);
insert into product (id, available_quantity, name, unit_price, created_by, shop_id) values (4, 400, 'TV 1', 500, 3, null);

insert into category_products (products_id, categories_id) values (1, 1);
insert into category_products (products_id, categories_id) values (2, 1);
insert into category_products (products_id, categories_id) values (3, 2);
insert into category_products (products_id, categories_id) values (4, 3);

insert into orders (id, counted_point, created_at, note, status, updated_at, special_user_id) values (1, true, null, 'test1', 'PROCESSING', null, 1);
insert into orders (id, counted_point, created_at, note, status, updated_at, special_user_id) values (2, false, null, 'test2', 'CANCELLED', null, 1);
insert into orders (id, counted_point, created_at, note, status, updated_at, special_user_id) values (3, true, null, 'test3', 'CONFIRMED', null,1);