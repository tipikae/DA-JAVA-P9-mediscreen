--liquibase formatted sql

--changeset tipikae:3
/* make family and given unique */
ALTER TABLE patient ADD UNIQUE family_given_unique(family, given);