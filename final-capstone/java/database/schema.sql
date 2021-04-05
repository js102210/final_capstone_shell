BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


  
CREATE SEQUENCE seq_size_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_flavor_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_frosting_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_filling_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_extra_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_message_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;   
     
CREATE SEQUENCE seq_cake_id
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
        
        
        
CREATE TABLE 
(
        cake_id int DEFAULT nextval('seq_cake_id'::regclass) NOT NULL,        
CREATE TABLE cakes
(
        cake_id int DEFAULT nextval('seq_cake_id'::regclass) NOT NULL,
        
  
COMMIT TRANSACTION;
