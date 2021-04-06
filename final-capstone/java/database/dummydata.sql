INSERT INTO fillings (filling_name, is_available, price_mod)
VALUES ('strawberry jam', true, 1.00),
	('blackberry jam', true, 1.00);

INSERT INTO sizes (size_name, is_available, price_mod)
VALUES ('small', true, 9.99),
	('medium', true, 15.99),
	('large', true, 19.99);

INSERT INTO styles (style_name, is_available, price_mod)
VALUES ('layer cake', true, 0.00),
	('cupcakes', true, 0.00),
	('sheet cake', true, 0.00);

INSERT INTO flavors (flavor_name, is_available, price_mod)
VALUES ('vanilla', true, 0.00),
	('chocolate', true, 0.00),
	('confetti', true, 2.00);

INSERT INTO frostings (frosting_name, is_available, price_mod)
VALUES ('vanilla', true, 0.00),
	('chocolate', true, 0.00),
	('cream cheese', true, 3.00);


INSERT INTO statuses (status_name)
	VALUES ('Pending');
INSERT INTO statuses (status_name)
	VALUES ('Ready');
INSERT INTO statuses (status_name)
	VALUES ('Cancelled');
INSERT INTO statuses (status_name)
	VALUES ('Complete');

INSERT INTO orders (status_id, total_price, datetime_placed, datetime_delivery, customer_name, customer_phone_number)
	VALUES (1, 14.99, '2021-4-6 11:18:55', '2021-4-7 12:00:00', 'Ernest Hemingway', 5138675309);

INSERT INTO cake_config (cake_config_name, cake_config_img_url, cake_config_description, flavor_id, frosting_id, filling_id)
	VALUES('Confetti Birthday Cake', 'https://preppykitchen.com/wp-content/uploads/2018/04/Funfetti-original-redone-blog-1.jpg', 
	'Our fun-filled confetti cake with luscious vanilla frosting', 4, 2, 1
	);

