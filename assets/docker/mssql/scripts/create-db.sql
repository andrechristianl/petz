CREATE database EncryptorServices;
GO

USE EncryptorServices;
GO

CREATE TABLE EncryptorServices.dbo.Encryptor (
	ID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Key varchar(35) NOT NULL UNIQUE,
	Salt varchar(35) NOT NULL UNIQUE,
	Created_date CURRENT_TIMESTAMP NOT NULL UNIQUE
);
GO