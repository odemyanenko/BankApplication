DO
$$
    DECLARE
        AtDate TIMESTAMP := CURRENT_TIMESTAMP;
    BEGIN
        INSERT INTO managers (id, first_name, last_name, created_at, updated_at)
        VALUES (GEN_RANDOM_UUID(), 'Olga', 'Demyanenko', AtDate, AtDate),
               (GEN_RANDOM_UUID(), 'Ivan', 'Petrov', AtDate, AtDate),
               (GEN_RANDOM_UUID(), 'Stepan', 'Semenov', AtDate, AtDate);
    END
$$;