CREATE TABLE accounts
(
    id            UUID PRIMARY KEY,
    client_id     UUID         NOT NULL,
    name          VARCHAR(100) NOT NULL,
    type          VARCHAR(20)  NOT NULL,
    balance       DECIMAL(15, 2),
    currency_code VARCHAR(20),
    status        VARCHAR(20)  NOT NULL,
    created_at    TIMESTAMP    NOT NULL,
    updated_at    TIMESTAMP
)
;

ALTER TABLE accounts
    ADD CONSTRAINT FK_ACCOUNTS_CLIENTS_CLIENT_ID FOREIGN KEY (client_id) REFERENCES clients (id)
        ON UPDATE NO ACTION;