CREATE TABLE exercise (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    muscle_group VARCHAR(255) NOT NULL,
    equipment VARCHAR(255) NOT NULL,
    duration INT NOT NULL
);

