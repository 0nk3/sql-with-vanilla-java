
CREATE TABLE employees (
	EmployeeID	INTEGER,
	FirstName	VARCHAR(50),
	LastName	VARCHAR(50),
	Email	VARCHAR(100),
	JobTitle	VARCHAR(20),
	PRIMARY KEY (EmployeeID)
);

INSERT INTO employees (EmployeeID, FirstName, LastName, Email, JobTitle)
VALUES
(1,'Kani', 'Matthew', 'mat@gmail.com', 'Manager'),
(2,'Lesly', 'Cronje', 'LesC@gmail.com', 'Clerk'),
(3,'Gideon', 'Maduku', 'm@gmail.com', 'Accountant');

