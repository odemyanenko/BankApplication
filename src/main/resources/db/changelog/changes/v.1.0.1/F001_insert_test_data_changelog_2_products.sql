SET @AtDate = current_timestamp;

SET @Manager1 = (SELECT id FROM managers WHERE last_name = 'Demyanenko');
SET @Manager2 = (SELECT id FROM managers WHERE last_name = 'Petrov');
SET @Manager3 = (SELECT id FROM managers WHERE last_name = 'Semenov');

INSERT INTO products (id, manager_id, name, status, currency_code, interest_rate, limit_amount, created_at, updated_at)
VALUES
  (RANDOM_UUID(), @Manager1, 'Current Account', 0, 0, 2.0, 1000.0, @AtDate, @AtDate),
  (RANDOM_UUID(), @Manager2, 'Mortgage', 0, 0, 7.3, 13000.0, @AtDate, @AtDate),
  (RANDOM_UUID(), @Manager3, 'Credit', 0, 0, 18.0, 5000.0, @AtDate, @AtDate);
