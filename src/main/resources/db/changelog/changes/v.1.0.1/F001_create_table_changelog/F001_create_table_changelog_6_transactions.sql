CREATE TABLE transactions
(
    id                UUID PRIMARY KEY,
    debit_account_id  UUID      NOT NULL,
    credit_account_id UUID      NOT NULL,
    type              INT,
    amount            DECIMAL(15, 2),
    description       VARCHAR(266),
    status            INT       NOT NULL,
    created_at        TIMESTAMP NOT NULL
);

ALTER TABLE transactions
    ADD CONSTRAINT FK_TRANSACTIONS_ACCOUNTS_DEBIT_ACCOUNT_ID FOREIGN KEY (debit_account_id) REFERENCES accounts (id)
        ON UPDATE NO ACTION;

ALTER TABLE transactions
    ADD CONSTRAINT FK_TRANSACTIONS_ACCOUNTS_CREDIT_ACCOUNT_ID FOREIGN KEY (credit_account_id) REFERENCES accounts (id)
        ON UPDATE NO ACTION;