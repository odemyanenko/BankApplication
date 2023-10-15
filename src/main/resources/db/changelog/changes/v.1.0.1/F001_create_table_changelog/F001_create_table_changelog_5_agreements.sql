CREATE TABLE agreements
(
    id            UUID PRIMARY KEY,
    account_id    UUID        NOT NULL,
    product_id    UUID        NOT NULL,
    interest_rate DECIMAL(6, 2),
    total         DECIMAL(15, 2),
    status        VARCHAR(20) NOT NULL,
    created_at    TIMESTAMP   NOT NULL,
    updated_at    TIMESTAMP
)
;

ALTER TABLE agreements
    ADD CONSTRAINT FK_AGREEMENTS_ACCOUNTS_ACCOUNT_ID FOREIGN KEY (account_id) REFERENCES accounts (id)
        ON UPDATE NO ACTION;

ALTER TABLE agreements
    ADD CONSTRAINT FK_AGREEMENTS_PRODUCTS_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES products (id)
        ON UPDATE NO ACTION;