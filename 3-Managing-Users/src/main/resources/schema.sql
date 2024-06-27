DROP TABLE  IF EXISTS users;
DROP TABLE  IF EXISTS authorities;
CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    "enabled" INT
);

CREATE TABLE IF NOT EXISTS authorities
(
    id        SERIAL PRIMARY KEY,
    username  VARCHAR(45) NOT NULL,
    authority VARCHAR(45) NOT NULL
);