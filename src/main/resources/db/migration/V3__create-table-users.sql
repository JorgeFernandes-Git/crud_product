CREATE TABLE users (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL,
    location TEXT NOT NULL,
    nif INT NOT NULL
)