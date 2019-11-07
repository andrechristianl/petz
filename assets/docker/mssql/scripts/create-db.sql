CREATE database KeyStore;
GO

USE KeyStore;
GO

CREATE TABLE KeyStore.dbo.KeyTable (
	id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	secret_key varchar(35) NOT NULL UNIQUE,
	salt varchar(35) NOT NULL UNIQUE,
	created_date TIMESTAMP NOT NULL UNIQUE
);
GO