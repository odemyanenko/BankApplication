SET
@AtDate = current_timestamp;

INSERT INTO agreements (id, account_id, product_id, interest_rate, total, status, created_at, updated_at)
SELECT RANDOM_UUID(),
       a.id,
       p.id,
       0,
       0,
       0,
       @AtDate,
       @AtDate
FROM accounts a
         INNER JOIN clients c ON a.client_id = c.id
         INNER JOIN products p ON c.manager_id = p.manager_id
WHERE c.last_name = 'Romanov'
UNION ALL
SELECT RANDOM_UUID(),
       a.id,
       p.id,
       0,
       0,
       0,
       @AtDate,
       @AtDate
FROM accounts a
         INNER JOIN clients c ON a.client_id = c.id
         INNER JOIN products p ON c.manager_id = p.manager_id
WHERE c.last_name = 'Turmanko'
UNION ALL
SELECT RANDOM_UUID(),
       a.id,
       p.id,
       0,
       0,
       0,
       @AtDate,
       @AtDate
FROM accounts a
         INNER JOIN clients c ON a.client_id = c.id
         INNER JOIN products p ON c.manager_id = p.manager_id
WHERE c.last_name = 'Vasileva'
UNION ALL
SELECT RANDOM_UUID(),
       a.id,
       p.id,
       0,
       0,
       0,
       @AtDate,
       @AtDate
FROM accounts a
         INNER JOIN clients c ON a.client_id = c.id
         INNER JOIN products p ON c.manager_id = p.manager_id
WHERE c.last_name = 'Bubnova'
UNION ALL
SELECT RANDOM_UUID(),
       a.id,
       p.id,
       0,
       0,
       0,
       @AtDate,
       @AtDate
FROM accounts a
         INNER JOIN clients c ON a.client_id = c.id
        INNER JOIN products p ON c.manager_id = p.manager_id
WHERE c.last_name = 'Herrenko'
;
