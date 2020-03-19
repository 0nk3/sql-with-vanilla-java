
CREATE TABLE payments(
	CustomerId INTEGER,
	PaymentID INTEGER,
	PaymentDate VARCHAR(10),
	Amount DECIMAL,
	PRIMARY KEY (PaymentID)

);

INSERT INTO payments (CustomerID, PaymentID, PaymentDate, Amount)
VALUES
(1,1,'01-09-2018',150.75),
(5,2, '03-09-2018',150.75),
(4,3,'03-09-2018', 700.60);
