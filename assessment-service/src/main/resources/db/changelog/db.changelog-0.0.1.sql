--liquibase formatted sql

--changeset tipikae:1
/*create table risk*/
CREATE TABLE risk (
	id INT(11) NOT NULL AUTO_INCREMENT,
	label VARCHAR(32) NOT NULL,
	
	PRIMARY KEY(id)
);

--changeset tipikae:2
/*insert values into risk table*/
INSERT INTO risk(label) VALUES
	('None'),
	('Borderline'),
	('In Danger'),
	('Early onset');

--changeset tipikae:3
/*create table formula*/
CREATE TABLE formula (
	id INT(11) NOT NULL AUTO_INCREMENT,
	risk_id INT(11) NOT NULL,
	form VARCHAR(1024) NOT NULL,
	
	PRIMARY KEY(id),
	CONSTRAINT risk_formula_fk FOREIGN KEY (risk_id) REFERENCES risk(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

--changeset tipikae:4
/*insert values into formula table*/
INSERT INTO formula(risk_id, form) VALUES
	(1, '[trigger < 2]'),
	(1, '[trigger = 2] AND [P.age < 30]'),
	(1, '[trigger = 3] AND [P.age < 30] AND [P.sex = F]'),
	(2, '[trigger >= 2] AND [trigger < 6] AND [P.age >= 30]'),
	(3, '[trigger = 3] AND [P.age < 30] AND [P.sex = M]'),
	(3, '[trigger = 4] AND [P.age < 30]'),
	(3, '[trigger = 5] AND [trigger = 6] AND [P.age < 30] AND [P.sex = F]'),
	(3, '[trigger = 6] AND [trigger = 7] AND [P.age >= 30]'),
	(4, '[trigger = 6] AND [P.age < 30] AND [P.sex = M]'),
	(4, '[trigger = 7] AND [P.age < 30]'),
	(4, '[trigger >= 8]');

--changeset tipikae:5
/*create table trigger*/
CREATE TABLE trigger (
	id INT(11) NOT NULL AUTO_INCREMENT,
	term VARCHAR(64) NOT NULL,
	
	PRIMARY KEY(id)
);

--changeset tipikae:6
/*insert values into trigger table*/
INSERT INTO trigger(term) VALUES 
	('Hemoglobin A1C'), 
	('Microalbumin'), 
	('Height'), 
	('Weight'), 
	('Smoker'), 
	('Abnormal'), 
	('Cholesterol'), 
	('Dizziness'), 
	('Relapse'), 
	('Reaction'), 
	('Antibodies'), 
	('Hémoglobine A1C'), 
	('Microalbumine'), 
	('Taille'), 
	('Poids'), 
	('Fumeur'), 
	('Anormal'), 
	('Cholestérol'), 
	('Vertige'), 
	('Rechute'), 
	('Réaction'), 
	('Anticorps');
	