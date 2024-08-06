DROP TABLE IF EXISTS product;
CREATE TABLE IF NOT EXISTS product (
    id INT,
    name VARCHAR(45) NULL,
    owner VARCHAR(45) NULL,
    PRIMARY KEY (id)
);

