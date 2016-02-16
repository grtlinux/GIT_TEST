Create database AJAX;
USE AJAX;
CREATE TABLE USERS(USERNAME VARCHAR(50) PRIMARY KEY,
	PASSWORD VARCHAR(50), 
	EMAIL VARCHAR(50),
	FIRST_NAME VARCHAR(50), 
	LAST_NAME VARCHAR(50),
	ADDRESS VARCHAR(50),
        ZIPCODE VARCHAR(5),
        CITY VARCHAR(50),
        STATE VARCHAR(2),
        JOINED DATE,
        LAST_LOGIN DATE);
CREATE TABLE ZIPCODES (ZIPCODE VARCHAR(5) PRIMARY KEY,
CITY VARCHAR(50),
STATE VARCHAR(2));

GRANT ALL ON AJAX.* to 'ajax'@'localhost' IDENTIFIED BY 'aardvark';