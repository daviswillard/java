DROP SCHEMA IF EXISTS chat CASCADE;

CREATE SCHEMA IF NOT EXISTS chat;

CREATE TABLE IF NOT EXISTS chat.user
(
    id          serial primary key,
    login       text unique not null,
    password    text unique not null
);

CREATE TABLE IF NOT EXISTS chat.chatroom
(
    id          serial primary key,
    name        text unique not null,
    owner       integer references chat.user(id) not null
);

CREATE TABLE IF NOT EXISTS chat.message
(
  id            serial primary key,
  author        integer references chat.user(id) not null,
  room          integer references chat.chatroom(id) not null,
  text          text not null,
  timestamp     timestamp not null
);