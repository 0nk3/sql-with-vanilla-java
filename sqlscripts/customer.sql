CREATE TABLE customer(
	CustomerID INTEGER NOT NULL,
	FirstName VARCHAR(50) ,
	LastName VARCHAR(50),
	Gender VARCHAR(6),
	Address VARCHAR(200),
	Phone VARCHAR(20),
	Email VARCHAR(100),
	City VARCHAR(20),
	Country VARCHAR(50),
	PRIMARY KEY (CustomerID)
);

INSERT INTO customer(CustomerID, FirstName, LastName, Gender, Address, Phone, Email, City, Country)
VALUES
(1,'John', 'Hilbert', 'Male','284 chaucer st', '(+27)84789657', 'john@gmail.com','Johannesburg', 'South Africa'),
(2,'Thando', 'Sithole','Female','240 Sect 1','(+27)794445584','thando@gmail.com', 'Cape Town', 'South Africa'),
(3,'Leon', 'Glen','Male', '81 Everton Rd Gillits', '(+27) 820832830','leon@gmail.com','Durban', 'South Africa'),
(4,'Charl', 'Muller','Male', '290A Dorset Ecke', '(+44) 856872553', 'Charl.muller@yahoo.com', 'Berlin', 'Germany'),
(5,'Julia', 'Stein','Female', '2 Wernerring', '(+44) 8672445058', 'js234@yahoo.com', 'Frankfurt', 'Germany');
