CREATE TABLE Categories
(
CategoryID INT,
Name VARCHAR(255) NULL
);

CREATE TABLE Products
(
ProductID INT NOT NULL,
Name VARCHAR(255) NULL,
CategoryID INT NULL,
DiscountPrice DOUBLE NULL,
Price DOUBLE NULL,
CreatedAt datetime NULL,
UpdatedAt datetime NULL,
`Description` VARCHAR(255) NULL,
ImageURL VARCHAR(255) NULL
);