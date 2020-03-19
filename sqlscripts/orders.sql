
CREATE TABLE orders(
	OrderId INTEGER,
	ProductID INTEGER,
	PaymentID INTEGER,
	FullFilledByEmployeeID INTEGER,
	DateRequired VARCHAR(10),
	DateShipped VARCHAR(10),
	Status VARCHAR(50),
	PRIMARY KEY (OrderID)

);

INSERT INTO orders(OrderID, ProductID, PaymentID,FullFilledByEmployeeID, DateRequired, DateShipped, Status)
VALUES
(1,1,1,2,'05-09-2018', NULL, 'Not Shipped'),
(2,1,2,2,'04-09-2018', '03-09-2018', 'Shipped'),
(3,3,3,3,'06-09-2018', NULL, 'Not Shipped');
