CREATE TABLE IF NOT EXISTS Token(
    id SERIAL PRIMARY KEY ,
    identifier varchar(255) NOT NULL ,
    token varchar(255) NOT NULL
);