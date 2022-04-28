DROP SCHEMA IF EXISTS chat CASCADE;

CREATE SCHEMA IF NOT EXISTS chat;


CREATE TABLE IF NOT EXISTS chat.user (
                                         id SERIAL PRIMARY KEY,
                                         login text UNIQUE NOT NULL,
                                         password text NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.chatroom (
                                             id SERIAL PRIMARY KEY,
                                             name TEXT UNIQUE NOT NULL,
                                             owner INTEGER REFERENCES chat."user"(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.message (
                                            id SERIAL PRIMARY KEY,
                                            author INTEGER REFERENCES chat."user"(id) NOT NULL,
                                            room INTEGER REFERENCES chat.chatroom(id) NOT NULL,
                                            text TEXT NOT NULL,
                                            timestamp TIMESTAMP
);