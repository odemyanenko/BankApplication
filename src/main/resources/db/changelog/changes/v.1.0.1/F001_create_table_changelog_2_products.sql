CREATE TABLE products
(
    id            UUID PRIMARY KEY,
    name          VARCHAR(70) NOT NULL,
    currency_code INT         NOT NULL,
    interest_rate DECIMAL(6, 4),
    limit_amount  DECIMAL(15, 2),
    manager_id    UUID,
    status        INT         NOT NULL,
    created_at    TIMESTAMP   NOT NULL,
    updated_at    TIMESTAMP
)
;

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_MANAGERS_MANAGER_ID FOREIGN KEY (manager_id) REFERENCES managers (id)
        ON UPDATE NO ACTION;

-- CREATE TABLE products
-- (
--     id            UUID PRIMARY KEY,
--     name          VARCHAR(70) NOT NULL,
--     currency_code INT         NOT NULL,
--     interest_rate DECIMAL(6, 4),
--     limit_amount  DECIMAL(15, 2),
--     manager_id    UUID,
--     status        INT         NOT NULL,
--     created_at    TIMESTAMP   NOT NULL,
--     updated_at    TIMESTAMP,
--     FOREIGN KEY (manager_id) REFERENCES managers (id) ON UPDATE NO ACTION
-- );