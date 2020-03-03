CREATE DATABASE FOODPRODUCER;

USE FOODPRODUCER;

CREATE TABLE ROLE (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  NAME TEXT NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE USER (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(50) NOT NULL,
  USERNAME VARCHAR(50) NOT NULL,
  EMAIL VARCHAR(50) NOT NULL,
  PASSWORD TEXT(100) NOT NULL,
  PHONE TEXT(15) NOT NULL,
  IS_ACTIVE BIT(1),
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE USER_ROLE (
  USER_ID BIGINT(20) NOT NULL,
  ROLE_ID BIGINT(20) NOT NULL,
  PRIMARY KEY (USER_ID, ROLE_ID),
  CONSTRAINT FK_USER FOREIGN KEY (USER_ID) REFERENCES USER(ID),
  CONSTRAINT FK_ROLE FOREIGN KEY (ROLE_ID) REFERENCES ROLE(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO ROLE(NAME) VALUES('ROLE_ADMIN');
INSERT INTO ROLE(NAME) VALUES('ROLE_HR');
INSERT INTO ROLE(NAME) VALUES('ROLE_ACCOUNTING');
INSERT INTO ROLE(NAME) VALUES('ROLE_STOCK');
INSERT INTO ROLE(NAME) VALUES('ROLE_SALE');

CREATE TABLE RESTAURANT (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  NAME TEXT NOT NULL,
  TOTAL_SEAT BIGINT(20) NOT NULL,
  ADDRESS TEXT NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE RESERVATION (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  RESERVATION_ID TEXT NOT NULL,
  FROM_DATE TIMESTAMP NOT NULL,
  TO_DATE TIMESTAMP NOT NULL,
  RESERVE_BY TEXT NOT NULL,
  EMAIL TEXT NOT NULL,
  PHONE TEXT NOT NULL,
  SEAT BIGINT(2) NOT NULL,
  TOTAL_PRICE BIGINT(20),
  STATUS TEXT NOT NULL,

  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE DISH (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  NAME TEXT NOT NULL,
  PRICE BIGINT(20) NOT NULL,
  DESCRIPTION TEXT NOT NULL,
  IMAGES TEXT NOT NULL,
  DATE_CREATED TIMESTAMP NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ORDERING (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  ORDER_ID TEXT NOT NULL,
  DATE TIMESTAMP NOT NULL,
  ORDER_BY TEXT NOT NULL,
  EMAIL TEXT NOT NULL,
  PHONE TEXT NOT NULL,
  ADDRESS TEXT NOT NULL,
  TOTAL_PRICE BIGINT(20) NOT NULL,
  STATUS TEXT NOT NULL,
  ORDER_TYPE TEXT NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ORDERITEM (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  DISH_ID BIGINT(20) NOT NULL,
  QUANTITY BIGINT(20) NOT NULL,
  ORDER_ID BIGINT(20) NOT NULL,
  PRIMARY KEY (ID),
  CONSTRAINT FK_ORDERITEM_DISH FOREIGN KEY (DISH_ID) REFERENCES DISH(ID),
  CONSTRAINT FK_ORDERITEM_ORDER FOREIGN KEY (ORDER_ID) REFERENCES ORDERING(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Insert data
insert into DISH(NAME, PRICE, DESCRIPTION, IMAGES, DATE_CREATED) values('Pho Bo', 500, 'This national staple is made with flat rice noodles, a warming broth and usually chicken or beef. The flavour of this comforting noodle soup can vary greatly across the country, and many establishments load your table with sauces, herbs and spices so you can season your pho exactly how you like it.', 'dish_phobo1.jpg;dish_phobo2.jpg;dish_phobo3.jpg;dish_phobo4.jpg', NOW());
insert into DISH(NAME, PRICE, DESCRIPTION, IMAGES, DATE_CREATED) values('Banh Mi', 200, 'Influenced by French colonialism in Indochina, Banh Mi is a delicious example of Franco-Vietnamese food, infused with flavours, ingredients and tastes from the two countries. Filled with a choice of meat (or egg, for vegetarians), fresh vegetables and a moreish sweet sauce, the crispy baguettes can be found in street stalls, restaurants and even the most remote areas.', 'dish_banhmi1.jpg;dish_banhmi2.jpg;dish_banhmi3.jpg;dish_banhmi4.jpg', NOW());
insert into DISH(NAME, PRICE, DESCRIPTION, IMAGES, DATE_CREATED) values('Goi Cuon', 300, 'With much of local Vietnamese cuisine being unsparingly fried, grilled and boiled, you may find yourself occasionally craving something a little fresher. Look no further than gỏi cuốn, also known as ‘summer rolls’. These fresh spring rolls are typically packed with crispy salad, prawns and pork, and served with a sweet-and-spicy dip topped with peanuts.', 'dish_goicuon1.jpg;dish_goicuon2.jpg;dish_goicuon3.jpg;dish_goicuon4.jpg', NOW());

insert into RESTAURANT(NAME, TOTAL_SEAT, ADDRESS) VALUES ('Food Producer', 5, 'Philippines');

ALTER TABLE ORDERING ADD ORDER_TYPE TEXT;