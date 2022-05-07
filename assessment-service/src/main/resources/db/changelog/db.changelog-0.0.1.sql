--liquibase formatted sql

--changeset tipikae:1
/*create table formula*/
CREATE TABLE formula (
	id INT(11) NOT NULL AUTO_INCREMENT,
	risk VARCHAR(32) NOT NULL,
	form VARCHAR(1024) NOT NULL,
	
	PRIMARY KEY(id)
);

--changeset tipikae:2
/*insert values into formula table*/
INSERT INTO formula(risk, form) VALUES
	('None', '[trigger < 2]'),
	('None', '[trigger = 2] AND [P.age < 30]'),
	('None', '[trigger = 3] AND [P.age < 30] AND [P.sex = F]'),
	('Borderline', '[trigger >= 2] AND [trigger <= 5] AND [P.age >= 30]'),
	('In Danger', '[trigger = 3] AND [P.age < 30] AND [P.sex = M]'),
	('In Danger', '[trigger = 4] AND [P.age < 30]'),
	('In Danger', '[trigger >= 5] AND [trigger <= 6] AND [P.age < 30] AND [P.sex = F]'),
	('In Danger', '[trigger >= 6] AND [trigger <= 7] AND [P.age >= 30]'),
	('Early onset', '[trigger = 6] AND [P.age < 30] AND [P.sex = M]'),
	('Early onset', '[trigger = 7] AND [P.age < 30]'),
	('Early onset', '[trigger >= 8]');

--changeset tipikae:3
/*create table trigger*/
CREATE TABLE trigger (
	id INT(11) NOT NULL AUTO_INCREMENT,
	term VARCHAR(64) NOT NULL,
	
	PRIMARY KEY(id)
);

--changeset tipikae:4
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
	