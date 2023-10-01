SET @AtDate = current_timestamp;

SET @Client1 = (SELECT id FROM clients WHERE last_name = 'Romanov');
SET @Client2 = (SELECT id FROM clients WHERE last_name = 'Turmanko');
SET @Client3 = (SELECT id FROM clients WHERE last_name = 'Vasileva');
SET @Client4 = (SELECT id FROM clients WHERE last_name = 'Bubnova');
SET @Client5 = (SELECT id FROM clients WHERE last_name = 'Herrenko');

INSERT INTO accounts (id, client_id, name, type, balance, currency_code, status, created_at, updated_at)
VALUES
    (RANDOM_UUID(), @Client1, 'DE89370400440532013000', 3, 23000.0, 0, 0, @AtDate, @AtDate),
    (RANDOM_UUID(), @Client2, 'DE75512108001245126199', 3, 1200.0, 0, 0, @AtDate, @AtDate),
    (RANDOM_UUID(), @Client3, 'DE02500105170137075030', 3, 32300.0, 0, 0, @AtDate, @AtDate),
    (RANDOM_UUID(), @Client4, 'DE02120300000000202051', 3, 10000.0, 0, 0, @AtDate, @AtDate),
    (RANDOM_UUID(), @Client5, 'DE02100100100006820101', 3, 50000.0, 0, 0, @AtDate, @AtDate)
;
