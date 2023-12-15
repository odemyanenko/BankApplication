CREATE TABLE users(
    id UUID PRIMARY KEY,
    email VARCHAR(60) NOT NULL,
    password VARCHAR(60) NOT NULL,
    role VARCHAR(20) NOT NULL
 )
;
