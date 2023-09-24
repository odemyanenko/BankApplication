SET @AtDate = current_timestamp;

INSERT INTO managers (id, first_name, last_name, status, created_at, updated_at)
VALUES
  (RANDOM_UUID(), 'Olga', 'Demyanenko', 0, @AtDate, @AtDate),
  (RANDOM_UUID(), 'Ivan', 'Petrov', 0, @AtDate, @AtDate),
  (RANDOM_UUID(), 'Stepan', 'Semenov', 0, @AtDate, @AtDate);