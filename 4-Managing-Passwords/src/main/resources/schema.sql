DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS custom_users;
DROP TABLE IF EXISTS custom_authorities;
CREATE TABLE IF NOT EXISTS custom_users
(
    id        SERIAL PRIMARY KEY,
    username  VARCHAR(45) NOT NULL,
    password  VARCHAR(45) NOT NULL,
    "enabled" INT
);

CREATE TABLE IF NOT EXISTS custom_authorities
(
    id        SERIAL PRIMARY KEY,
    username  VARCHAR(45) NOT NULL,
    authority VARCHAR(45) NOT NULL
);