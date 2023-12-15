DO $$
BEGIN
    INSERT INTO users (id, email, password, role)
    VALUES (GEN_RANDOM_UUID(), 'olga.demyanenko@gmail.com', '$2a$12$PE9Fm4zkNsMFgtd3I7mxO./wlkaY17MCPXfrSS6k09u2GEKbVD07.', 'MANAGER'),
           (GEN_RANDOM_UUID(), 'ivan.petrov@gmail.com', '$2a$12$PE9Fm4zkNsMFgtd3I7mxO./wlkaY17MCPXfrSS6k09u2GEKbVD07.', 'MANAGER'),
           (GEN_RANDOM_UUID(), 'stepan.semenov@gmail.com', '$2a$12$PE9Fm4zkNsMFgtd3I7mxO./wlkaY17MCPXfrSS6k09u2GEKbVD07.', 'MANAGER'),
           (GEN_RANDOM_UUID(), 'anna.valileva@gmail.com', '$2a$12$PE9Fm4zkNsMFgtd3I7mxO./wlkaY17MCPXfrSS6k09u2GEKbVD07.', 'CLIENT'),
           (GEN_RANDOM_UUID(), 'admin@gmail.com', '$2a$12$PE9Fm4zkNsMFgtd3I7mxO./wlkaY17MCPXfrSS6k09u2GEKbVD07.', 'ADMIN');
END $$;