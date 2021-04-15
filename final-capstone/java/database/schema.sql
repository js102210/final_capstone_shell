
DROP TABLE IF EXISTS cake_item_extras CASCADE;
DROP TABLE IF EXISTS cake_items CASCADE; 
DROP TABLE IF EXISTS cake_config CASCADE; 
DROP TABLE IF EXISTS messages CASCADE;
DROP TABLE IF EXISTS extras CASCADE;
DROP TABLE IF EXISTS orders CASCADE;    
DROP TABLE IF EXISTS statuses CASCADE;   
DROP TABLE IF EXISTS frostings CASCADE; 
DROP TABLE IF EXISTS flavors CASCADE;
DROP TABLE IF EXISTS fillings CASCADE; 
DROP TABLE IF EXISTS sizes CASCADE; 
DROP TABLE IF EXISTS styles CASCADE;
DROP TABLE IF EXISTS users CASCADE;



DROP SEQUENCE IF EXISTS seq_user_id;
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

DROP SEQUENCE IF EXISTS seq_style_id;
CREATE SEQUENCE seq_style_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE TABLE styles
(
        style_id int DEFAULT nextval('seq_style_id'::regclass) NOT NULL,
        style_name VARCHAR NOT NULL,
        is_available BOOLEAN NOT NULL DEFAULT TRUE,
        price_mod DECIMAL (19,2) NOT NULL DEFAULT 0.00,
        
        CONSTRAINT pk_styles PRIMARY KEY (style_id)
        ); 
        INSERT INTO styles (style_name) VALUES ('Pick a cake style!');

DROP SEQUENCE IF EXISTS seq_size_id;   
CREATE SEQUENCE seq_size_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;    
CREATE TABLE sizes
        (size_id int DEFAULT nextval('seq_size_id'::regclass) NOT NULL,
        size_name VARCHAR NOT NULL,
        size_description VARCHAR NOT NULL,
        is_available BOOLEAN NOT NULL DEFAULT TRUE,
        price_mod DECIMAL (19,2) NOT NULL DEFAULT 0.00,
        
        CONSTRAINT pk_sizes PRIMARY KEY (size_id)
        ); 
        INSERT INTO sizes (size_name, size_description) VALUES ('No size', 'Pick a cake size!');

DROP SEQUENCE IF EXISTS seq_flavor_id; 
CREATE SEQUENCE seq_flavor_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;    
CREATE TABLE flavors
        (flavor_id int DEFAULT nextval('seq_flavor_id'::regclass) NOT NULL,
        flavor_name VARCHAR NOT NULL,
        is_available BOOLEAN NOT NULL DEFAULT TRUE,
        price_mod DECIMAL (19,2) NOT NULL DEFAULT 0.00,
        
        CONSTRAINT pk_flavors PRIMARY KEY (flavor_id)
        );         
 INSERT INTO flavors (flavor_name) VALUES ('Pick a cake flavor!');
--first value in table should be 'NULL' to account for custom made cakes so that IDs for custom are 'NULL'. IDs for all
--'NULL values will be 1
 

 DROP SEQUENCE IF EXISTS seq_frosting_id;   
CREATE SEQUENCE seq_frosting_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;    
CREATE TABLE frostings
        (frosting_id int DEFAULT nextval('seq_frosting_id'::regclass) NOT NULL,
        frosting_name VARCHAR NOT NULL,
        is_available BOOLEAN NOT NULL DEFAULT TRUE,
        price_mod DECIMAL (19,2) NOT NULL DEFAULT 0.00,
        
        CONSTRAINT pk_frostings PRIMARY KEY (frosting_id)
        );           
INSERT INTO frostings (frosting_name) VALUES ('no frosting - have a naked cake!');
--first value in table should be 'NULL' to account for custom made cakes so that IDs for custom are 'NULL'. IDs for all
--'NULL values will be 1     
   

DROP SEQUENCE IF EXISTS seq_filling_id;   
CREATE SEQUENCE seq_filling_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;    
CREATE TABLE fillings
        (filling_id int DEFAULT nextval('seq_filling_id'::regclass) NOT NULL,
        filling_name VARCHAR NOT NULL,
        is_available BOOLEAN NOT NULL DEFAULT TRUE,
        price_mod DECIMAL (19,2) NOT NULL DEFAULT 0.00,
        
        CONSTRAINT pk_fillings PRIMARY KEY (filling_id)
        );    
INSERT INTO fillings (filling_name) VALUES ('no filling');
--first value in table should be 'NULL' to account for custom made cakes so that IDs for custom are 'NULL'. IDs for all
--'NULL values will be 1     
           
 
DROP SEQUENCE IF EXISTS seq_extra_id; 
CREATE SEQUENCE seq_extra_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;    
CREATE TABLE extras
        (extra_id int DEFAULT nextval('seq_extra_id'::regclass) NOT NULL,
        extra_name VARCHAR NOT NULL,
        is_available BOOLEAN NOT NULL DEFAULT TRUE,
        price_mod DECIMAL (19,2) NOT NULL DEFAULT 0.00,
        
        CONSTRAINT pk_extras PRIMARY KEY (extra_id)
        );        
       
                  
 
 DROP SEQUENCE IF EXISTS seq_status_id;   
CREATE SEQUENCE seq_status_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;    
CREATE TABLE statuses
        (status_id int DEFAULT nextval('seq_status_id'::regclass) NOT NULL,
        status_name VARCHAR NOT NULL,
                
        CONSTRAINT pk_statuses PRIMARY KEY (status_id)
        );               
              
        

DROP SEQUENCE IF EXISTS seq_cake_config_id;  
CREATE SEQUENCE seq_cake_config_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;    
CREATE TABLE cake_config
        (cake_config_id int DEFAULT nextval('seq_cake_config_id'::regclass) NOT NULL,
        cake_config_name VARCHAR NOT NULL,
        cake_config_img_url VARCHAR NOT NULL,
        cake_config_description VARCHAR NOT NULL,
        flavor_id int NOT NULL,
        frosting_id int NOT NULL,
        filling_id int NOT NULL,
        is_available boolean DEFAULT TRUE NOT NULL,
        
        
        CONSTRAINT pk_cake_config PRIMARY KEY (cake_config_id),
        CONSTRAINT fk_cake_config_flavor FOREIGN KEY (flavor_id) REFERENCES flavors (flavor_id),
        CONSTRAINT fk_cake_config_frosting FOREIGN KEY (frosting_id) REFERENCES frostings (frosting_id),
        CONSTRAINT fk_cake_config_filling FOREIGN KEY (filling_id) REFERENCES fillings (filling_id)
        );          
 INSERT INTO cake_config (cake_config_name, cake_config_img_url, cake_config_description, flavor_id, frosting_id, filling_id) 
        VALUES ('Custom Cake', 'https://www.pngkit.com/png/detail/918-9180589_wedding-cake-icon-png-conzelmann-b-228-ckerei.png', 'Build your own cake!', 1, 1, 1);
        --bc of our INSERT statements IDs for NULL values are 1    
        

DROP SEQUENCE IF EXISTS seq_cake_item_id;  
CREATE SEQUENCE seq_cake_item_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;    
CREATE TABLE cake_items
        (cake_item_id int DEFAULT nextval('seq_cake_item_id'::regclass) NOT NULL,
        cake_style_id int NOT NULL,
        cake_size_id int NOT NULL,
        flavor_id int NOT NULL,
        frosting_id int NOT NULL,
        filling_id int NOT NULL,
        message VARCHAR NOT NULL DEFAULT '',
        config_id int NOT NULL,
        item_price DECIMAL (19,2) NOT NULL, 
        
        
        CONSTRAINT pk_cake_item_id PRIMARY KEY (cake_item_id),
        CONSTRAINT fk_cake_item_style FOREIGN KEY (cake_style_id) REFERENCES styles (style_id),
        CONSTRAINT fk_cake_item_size FOREIGN KEY (cake_size_id) REFERENCES sizes (size_id),
        CONSTRAINT fk_cake_item_flavor FOREIGN KEY (flavor_id) REFERENCES flavors (flavor_id),
        CONSTRAINT fk_cake_item_frosting FOREIGN KEY (frosting_id) REFERENCES frostings (frosting_id),
        CONSTRAINT fk_cake_item_filling FOREIGN KEY (filling_id) REFERENCES fillings (filling_id),
        CONSTRAINT fk_cake_item_config FOREIGN KEY (config_id) REFERENCES cake_config (cake_config_id)
        );    
        

DROP SEQUENCE IF EXISTS seq_order_id;  
CREATE SEQUENCE seq_order_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;    
CREATE TABLE orders
 (order_id int DEFAULT nextval('seq_order_id'::regclass) NOT NULL,
 status_id int NOT NULL,
 total_price DECIMAL (19,2) NOT NULL,
 date_placed DATE DEFAULT CURRENT_DATE, --think about including timestamp validation for this
 pickup_date DATE NOT NULL,
 pickup_time TIME NOT NULL, 
 customer_name VARCHAR(50) NOT NULL,
 customer_phone_number VARCHAR(20) NOT NULL,
 date_last_updated VARCHAR DEFAULT CURRENT_DATE,

 
  CONSTRAINT pk_orders PRIMARY KEY (order_id),
  CONSTRAINT fk_order_status FOREIGN KEY (status_id) REFERENCES statuses (status_id)
);

DROP SEQUENCE IF EXISTS seq_message_id; 
CREATE SEQUENCE seq_message_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;    
CREATE TABLE messages
        (message_id int DEFAULT nextval('seq_message_id'::regclass) NOT NULL,
        message_type_name VARCHAR NOT NULL,
        message_price_mod DECIMAL (19,2) NOT NULL,
                
        CONSTRAINT pk_messages PRIMARY KEY (message_id)
        );   
 
 
ALTER TABLE cake_items ADD order_id int NOT NULL;
ALTER TABLE cake_items ADD CONSTRAINT order_id FOREIGN KEY (order_id) REFERENCES orders(order_id);

CREATE TABLE cake_item_extras
(cake_item_id int NOT NULL,
extra_id int NOT NULL,

CONSTRAINT fk_rel_item_id FOREIGN KEY (cake_item_id) REFERENCES cake_items(cake_item_id),
CONSTRAINT fk_rel_extra_id FOREIGN KEY (extra_id) REFERENCES extras(extra_id)
);

INSERT INTO messages (message_type_name, message_price_mod)
        VALUES ('basic message', 1.50);

INSERT INTO fillings (filling_name, is_available, price_mod)
VALUES ('strawberry jam', true, 1.00),
	('blackberry jam', true, 1.00);

INSERT INTO sizes (size_name, size_description, is_available, price_mod)
VALUES ('small', 'SMALL: a 6" double layer cake OR 12 cupcakes OR a 8"x8" sheet cake', true, 9.99),
	('medium', 'MEDIUM: a 9" double layer cake OR 18 cupcakes OR a 9"x14" sheet cake', true, 15.99),
	('large', 'LARGE: a 12" double layer cake OR 24 cupcakes OR a 20"x24" sheet cake', true, 19.99);

INSERT INTO styles (style_name, is_available, price_mod)
VALUES ('layer cake', true, 0.00),
	('cupcakes', true, 0.00),
	('sheet cake', true, 0.00),
	('cake pops', false, 3.00);

INSERT INTO flavors (flavor_name, is_available, price_mod)
VALUES ('vanilla', true, 0.00),
	('chocolate', true, 0.00),
	('confetti', true, 2.00),
	('carrot', true, 3.00),
	('strawberry', false, 2.00);

INSERT INTO frostings (frosting_name, is_available, price_mod)
VALUES ('vanilla', true, 0.00),
	('chocolate', true, 0.00),
	('cream cheese', true, 3.00);
	
INSERT INTO extras (extra_name, is_available, price_mod)
VALUES ('Superhero decorations',true, 3.00),
        ('Plastic bunny',true, 1.00),
        ('Birthday candles',true, 2.00),
        ('BeefCakes icing moustache', true, 1.00),
        ('Beef Up! protein powder addition', true, 2.00),
        ('Vegan Option', true, 2.00),
        ('Gluten-Free Option', true, 1.00),
        ('Dairy-Free Option', true, 1.00);


INSERT INTO statuses (status_name)
	VALUES ('Pending');
INSERT INTO statuses (status_name)
	VALUES ('Ready');
INSERT INTO statuses (status_name)
	VALUES ('Cancelled');
INSERT INTO statuses (status_name)
	VALUES ('Complete');


INSERT INTO cake_config (cake_config_name, cake_config_img_url, cake_config_description, flavor_id, frosting_id, filling_id)
	VALUES('Curls Confetti Birthday Cake', 'https://preppykitchen.com/wp-content/uploads/2018/04/Funfetti-original-redone-blog-1.jpg', 
	'Our fun-filled confetti cake with luscious vanilla frosting', 4, 2, 1
	),
	('Deadlift Devil''s Food Cake', 'https://www.girlversusdough.com/wp-content/uploads/2019/10/devils-food-cake-5.jpg', 'Our award-winning deep devil''s food cake with deep chocolate frosting. Go ahead, it''s cheat day!', 3, 3, 1),
	('Gym Bunny Carrot Cake', 'https://grandbaby-cakes.com/wp-content/uploads/2020/03/Carrot-Cake-10.jpg', 'Our moist carrot cake is jam-packed with healthy antoxidants and unbeatable flavor! It comes with our incredible cream cheese frosting.', 5, 4, 1);


/*
--query that pulls string info for cake configs instead of just IDs
SELECT cake_config.cake_config_id, cake_config.cake_config_name, cake_config.cake_config_img_url,
                 cake_config.cake_config_description, fillings.filling_name, frostings.frosting_name, flavors.flavor_name
                FROM cake_config
                INNER JOIN fillings ON fillings.filling_id = cake_config.filling_id
                INNER JOIN frostings ON frostings.frosting_id = cake_config.frosting_id
                INNER JOIN flavors ON flavors.flavor_id = cake_config.flavor_id; */
SELECT *  FROM cake_config;     
SELECT * FROM fillings WHERE is_available = TRUE;  
SELECT * FROM flavors WHERE is_available = TRUE;
SELECT * FROM frostings WHERE is_available = TRUE;  
SELECT * FROM sizes WHERE is_available = TRUE;
SELECT * FROM extras WHERE is_available = TRUE;


/*UPDATE extras
SET extra_name = '3"x5" Chocolate bunny figure',
is_available = true,
price_mod = 1.0
WHERE extra_id = 2; */
           



INSERT INTO orders (status_id, total_price, date_placed, pickup_date, pickup_time, customer_name, customer_phone_number)
	VALUES (1, 14.99, '2021-4-6', '2021-4-7', '13:00', 'Franz Kafka', '5138675309'), 
	        (2, 23.98, '2021-3-10', '2021-4-16', '11:00', 'Ada Lovelace', '4472108836'), 
	        (3, 10.99, '2021-2-11', '2021-2-20', '10:00', 'Alan Turing', '5134467789'),
	        (4, 12.99, '2021-4-1', '2021-4-16', '15:00', 'Eugene Ionesco', '5134478865'),
	        (1, 26.98, '2021-4-10', '2021-4-18', '13:30', 'Nikola Tesla', '5133988675'), 
	        (4, 13.99, '2021-4-6', '2021-4-8', '12:00', 'Orson Welles', '4407672215'); 
	
INSERT INTO cake_items (cake_style_id, cake_size_id, flavor_id, frosting_id, filling_id, message, config_id, item_price, order_id)
VALUES                 (2, 2, 2, 3, 2, 'Obey Protocol', 1, 14.99, 1),  
                       (3, 4, 3, 3, 1, '', 3, 10.99, 2),                
                       (2, 2, 2, 1, 3, 'Happy Birthday', 1, 12.99, 2),  
                       (5, 3, 5, 4, 1, '', 4, 10.99, 3),                
                       (4, 2, 6, 3, 2, E'I\'m not capitulating!', 1, 12.99, 4), 
                       (3, 4, 4, 2, 1, '', 2, 15.99, 5),               
                       (2, 2, 5, 4, 1, '', 4, 10.99, 5),               
                       (3, 3, 3, 4, 2, 'Rosebud', 1, 13.99, 6);        
                       

INSERT INTO cake_item_extras (cake_item_id, extra_id)
        VALUES (1,3), 
                (1,5),
                (3,3),
                (4,6),
                (7,8),
                (8,2),
                (8,3)
                
                

