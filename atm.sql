CREATE DATABASE atm;

use atm;

CREATE TABLE Users(
	UserID int NOT NULL AUTO_INCREMENT,
	FirstName varchar(25) NOT NULL,
    LastName varchar(25) NOT NULL,
    CreditCardNo varchar(12) NOT NULL,
    HashedPIN varchar(50) NOT NULL,
    USD int DEFAULT 0,
    EUR int DEFAULT 0,
    PLN int DEFAULT 0,
    PRIMARY KEY (UserID)
);


