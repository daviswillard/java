drop schema if exists orm_test;

create schema if not exists orm_test;

create table orm_test.simple_user (
    id serial primary key,
    name text unique not null,
    last_name text not null,
    age int not null
)