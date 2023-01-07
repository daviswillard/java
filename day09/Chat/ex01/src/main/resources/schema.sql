drop schema if exists chat;

create schema  if not exists chat;

create table if not exists chat.user (
     id          int not null auto_increment,
     login       varchar(20) unique not null,
     password    varchar(100) not null,
     primary key (id)
);


create table if not exists chat.message (
        id            serial primary key,
        author        int not null,
        body          text not null,
        timestamp     timestamp not null,
        foreign key (author) references chat.user(id)
);