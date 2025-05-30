INSERT INTO Categories (CategoryID, Name) VALUES (1, 'Garden Tools');
INSERT INTO Categories (CategoryID, Name) VALUES (2, 'Outdoor Power Equipment');
INSERT INTO Categories (CategoryID, Name) VALUES (3, 'Watering Equipment');

INSERT INTO Products (ProductID, CategoryID, DiscountPrice, Price, CreatedAt, UpdatedAt, Description, ImageURL, Name)
VALUES
    (1, 1, 15.99, 19.99, NOW(), NOW(), 'Sturdy garden trowel with wooden handle', 'https://example.com/images/garden_trowel.jpg', 'Garden Trowel'),
    (2, 1, 0.0, 39.99, NOW(), NOW(), 'Heavy-duty pruning shears for trimming bushes and small branches', 'https://example.com/images/pruning_shears.jpg', 'Pruning Shears'),
    (3, 2, 299.99, 349.99, NOW(), NOW(), 'Gas-powered lawn mower with 21-inch cutting deck', 'https://example.com/images/lawn_mower.jpg', 'Gas Lawn Mower'),
    (4, 3, 199.99, 849.99, NOW(), NOW(), 'Gas-powered lawn mower with 21-inch cutting deck', 'https://example.com/images/lawn_mower.jpg', 'New Gas Lawn Mower');