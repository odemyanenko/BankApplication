CREATE TABLE clients
(
    id         UUID PRIMARY KEY,
    first_name VARCHAR(50),
    last_name  VARCHAR(50) NOT NULL,
    tax_code   VARCHAR(20),
    email      VARCHAR(60) NOT NULL,
    address    VARCHAR(80),
    phone      VARCHAR(20),
    manager_id UUID        NOT NULL,
    status     INT         NOT NULL,
    created_at TIMESTAMP   NOT NULL,
    updated_at TIMESTAMP
)
;

ALTER TABLE clients
    ADD CONSTRAINT FK_CLIENTS_MANAGERS_MANAGER_ID FOREIGN KEY (manager_id) REFERENCES managers (id)
        ON UPDATE NO ACTION;