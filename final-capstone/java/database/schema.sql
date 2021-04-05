BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
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

DROP TABLE IF EXISTS styles;
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
        
        
DROP TABLE IF EXISTS sizes; 
DROP SEQUENCE IF EXISTS seq_size_id;   
CREATE SEQUENCE seq_size_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;    
CREATE TABLE sizes
        (size_id int DEFAULT nextval('seq_size_id'::regclass) NOT NULL,
        size_name VARCHAR NOT NULL,
        is_available BOOLEAN NOT NULL DEFAULT TRUE,
        price_mod DECIMAL (19,2) NOT NULL DEFAULT 0.00,
        
        CONSTRAINT pk_sizes PRIMARY KEY (size_id)
        ); 
        
DROP TABLE IF EXISTS flavors;   
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
 INSERT INTO flavors (flavor_name) VALUES ('NULL');
--first value in table should be 'NULL' to account for custom made cakes so that IDs for custom are 'NULL'. IDs for all
--'NULL values will be 1
 
 DROP TABLE IF EXISTS frostings; 
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
INSERT INTO frostings (frosting_name) VALUES ('NULL');
--first value in table should be 'NULL' to account for custom made cakes so that IDs for custom are 'NULL'. IDs for all
--'NULL values will be 1     
   
DROP TABLE IF EXISTS fillings; 
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
INSERT INTO fillings (filling_name) VALUES ('NULL');
--first value in table should be 'NULL' to account for custom made cakes so that IDs for custom are 'NULL'. IDs for all
--'NULL values will be 1     
           
DROP TABLE IF EXISTS extras;   
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
                  
 DROP TABLE IF EXISTS statuses; 
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
              
        
DROP TABLE IF EXISTS cake_configs;  
DROP SEQUENCE IF EXISTS seq_cake_configs_id;  
CREATE SEQUENCE seq_cake_configs_id
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
        
        
        CONSTRAINT pk_cake_config PRIMARY KEY (cake_config_id),
        CONSTRAINT fk_cake_config_flavor FOREIGN KEY (flavor_id) REFERENCES flavors (flavor_id),
        CONSTRAINT fk_cake_config_frosting FOREIGN KEY (frosting_id) REFERENCES frostings (frosting_id),
        CONSTRAINT fk_cake_config_filling FOREIGN KEY (filling_id) REFERENCES fillings (filling_id)
        );          
 INSERT INTO cake_config (cake_config_name, cake_config_img_url, cake_config_description, flavor_id, frosting_id, filling_id) 
        VALUES ('CUSTOM', '', 'DESCRIPTION', 1, 1, 1);
        --bc of our INSERT statements IDs for NULL values are 1    
        
DROP TABLE IF EXISTS cake_items;  
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
        message VARCHAR NOT NULL,
        config_id int NOT NULL,
        
        
        CONSTRAINT pk_cake_item_id PRIMARY KEY (cake_item_id),
        CONSTRAINT fk_cake_item_style FOREIGN KEY (cake_style_id) REFERENCES styles (style_id),
        CONSTRAINT fk_cake_item_size FOREIGN KEY (cake_size_id) REFERENCES sizes (size_id),
        CONSTRAINT fk_cake_item_flavor FOREIGN KEY (flavor_id) REFERENCES flavors (flavor_id),
        CONSTRAINT fk_cake_item_frosting FOREIGN KEY (frosting_id) REFERENCES frostings (frosting_id),
        CONSTRAINT fk_cake_item_filling FOREIGN KEY (filling_id) REFERENCES fillings (filling_id),
        CONSTRAINT fk_cake_item_config FOREIGN KEY (config_id) REFERENCES cake_config (cake_config_id)
        );    
        
DROP TABLE IF EXISTS orders;  
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
 datetime_placed timestamp DEFAULT CURRENT_TIMESTAMP(), --think about validation for this
 datetime_delivery timestamp NOT NULL,
 
  CONSTRAINT pk_orders PRIMARY KEY (order_id),
  CONSTRAINT fk_cake_item_style FOREIGN KEY (cake_style_id) REFERENCES styles (style_id),
 
 
 


     --note for testing datetime local insert: 2017-06-30T16:30
     --note to add order ID as FK to cake item

COMMIT TRANSACTION;
