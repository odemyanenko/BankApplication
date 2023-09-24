CREATE  TABLE  managers(
    id UUID PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50) NOT NULL,
    status INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
 )
;
-- CREATE TABLE managers (
--                           id VARCHAR(36) PRIMARY KEY,
--                           first_name VARCHAR(50),
--                           last_name VARCHAR(50) NOT NULL,
--                           status INT NOT NULL,
--                           created_at TIMESTAMP NOT NULL,
--                           updated_at TIMESTAMP NOT NULL
-- );