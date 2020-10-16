CREATE database Petz;
GO

USE Petz;
GO

CREATE TABLE Petz.dbo.Client (
	id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	name varchar(100) NOT NULL ,
	address varchar(100) NOT NULL ,
	district varchar(100) NOT NULL ,
	uf varchar(2) NOT NULL ,
	phone varchar(20) NOT NULL ,
	email varchar(100) NOT NULL
);
GO

CREATE TABLE Petz.dbo.Pet (
	id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	name varchar(100) NOT NULL ,
	age varchar(100) NOT NULL ,
	breed varchar(100) NOT NULL ,
	note varchar(500) NOT NULL 
);
GO
