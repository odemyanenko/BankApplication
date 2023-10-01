SET @AtDate = current_timestamp;

INSERT INTO transactions (id, debit_account_id, credit_account_id, type, amount, status, created_at)

SELECT RANDOM_UUID(),
       a.id,
       (SELECT TOP 1 id from accounts WHERE client_id <> c.id),
       0,
       100.0,
       2,
       @AtDate
FROM accounts a
         INNER JOIN clients c ON a.client_id = c.id
WHERE c.last_name = 'Romanov'
UNION ALL
SELECT RANDOM_UUID(),
       a.id,
       (SELECT TOP 1 id from accounts WHERE client_id <> c.id),
       0,
       200.0,
       2,
       @AtDate
FROM accounts a
         INNER JOIN clients c ON a.client_id = c.id
WHERE c.last_name = 'Turmanko'
UNION ALL
SELECT RANDOM_UUID(),
       a.id,
       (SELECT TOP 1 id from accounts WHERE client_id <> c.id),
       0,
       300.0,
       0,
       @AtDate
FROM accounts a
         INNER JOIN clients c ON a.client_id = c.id
WHERE c.last_name = 'Vasileva'
UNION ALL
SELECT RANDOM_UUID(),
       a.id,
       (SELECT TOP 1 id from accounts WHERE client_id <> c.id),
       0,
       400.0,
       0,
       @AtDate
FROM accounts a
         INNER JOIN clients c ON a.client_id = c.id
WHERE c.last_name = 'Bubnova'
UNION ALL
SELECT RANDOM_UUID(),
       a.id,
       (SELECT TOP 1 id from accounts WHERE client_id <> c.id),
       0,
       500.0,
       2,
       @AtDate
FROM accounts a
         INNER JOIN clients c ON a.client_id = c.id
WHERE c.last_name = 'Herrenko'
;
