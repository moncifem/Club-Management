DROP DATABASE IF EXISTS club_management;
CREATE DATABASE club_management;
USE club_management;
CREATE TABLE etudiant(
id int NOT NULL AUTO_INCREMENT,
nom varchar(20) NOT NULL,
prenom varchar(20) NOT NULL,
annee varchar(10) NOT NULL,
semestre varchar(10) NOT NULL,
commentary varchar(100),
civil_status varchar(10) NOT NULL
-- preferences to be implemnted
);

