--liquibase formatted sql

--changeset tipikae:1
/*create table patient*/
CREATE TABLE patient (
	id INT(11) NOT NULL AUTO_INCREMENT,
	family VARCHAR(50) NOT NULL,
	given VARCHAR(50) NOT NULL,
	dob DATE NOT NULL,
	sex CHAR(1) NOT NULL,
	address VARCHAR(128) NOT NULL,
	phone VARCHAR(20) NOT NULL,
	
	PRIMARY KEY(id)
);

--changeset tipikae:2
/*insert values into patient table*/
INSERT INTO patient(family, given, dob, sex, address, phone) 
	VALUES('TestNone', 'Test', TO_DATE('1966-12-31', 'YYYY-MM-DD'), 'F', '1 Brookside St', '100-222-3333');
INSERT INTO patient(family, given, dob, sex, address, phone) 
	VALUES('TestBorderline', 'Test', TO_DATE('1945-06-24', 'YYYY-MM-DD'), 'M', '2 High St', '200-333-4444');
INSERT INTO patient(family, given, dob, sex, address, phone) 
	VALUES('TestInDanger', 'Test', TO_DATE('2004-06-18', 'YYYY-MM-DD'), 'M', '3 Club Road', '300-444-5555');
INSERT INTO patient(family, given, dob, sex, address, phone) 
	VALUES('TestEarlyOnset', 'Test', TO_DATE('2002-06-28', 'YYYY-MM-DD'), 'F', '4 Valley Dr', '400-555-6666');
