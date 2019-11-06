CREATE database TranslatorServices;
GO

USE TranslatorServices;
GO

CREATE TABLE TranslatorServices.dbo.main (
	id int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	description varchar(255) NOT NULL UNIQUE
); 
GO