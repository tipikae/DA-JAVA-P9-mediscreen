--liquibase formatted sql

--changeset tipikae:1
/*create table formula*/
CREATE TABLE formula (
	id INT(11) NOT NULL AUTO_INCREMENT,
	form VARCHAR(1024) NOT NULL,
	
	PRIMARY KEY(id)
);

--changeset tipikae:2
/*insert values into formula table*/
INSERT INTO formula(form) 
	VALUES('formula');
	