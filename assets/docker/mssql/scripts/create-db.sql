CREATE database Stores;
GO

USE Stores;
GO

CREATE TABLE Stores.dbo.KeyStore (
	id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	secret_key varchar(35) NOT NULL UNIQUE,
	salt varchar(35) NOT NULL UNIQUE,
	created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP UNIQUE
);
GO


INSERT INTO Stores.dbo.KeyStore (
secret_key,
salt
)
VALUES (
'mssql-keystore-container',
'mssql-keystore-container'
);
GO