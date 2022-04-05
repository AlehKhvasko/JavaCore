--liquibase formatted sql

--changeset simak:1

create table top50cities
(
    id     SERIAL,
    name   varchar(200),
    cityID int,
    primary key (id)
);

