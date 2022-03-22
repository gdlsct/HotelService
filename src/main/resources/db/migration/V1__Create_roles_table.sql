create sequence if not exists "hibernate_sequence"
    increment by 1
    minvalue 1
    no maxvalue
    start with 1;

create table if not exists roles
(
    role_id          uuid    not null
        constraint role_pk primary key,
    role_name        varchar not null,
    role_description varchar not null
);

insert into roles(role_id, role_name, role_description)
values ('3c0024aa-8db0-11ec-b909-0242ac120002', 'ROLE_ADMIN', 'Администратор'),
       ('3c002716-8db0-11ec-b909-0242ac120002', 'ROLE_GUEST', 'Гость отеля'),
       ('3c002a4a-8db0-11ec-b909-0242ac120002', 'ROLE_DISPATCHER', 'Диспетчер'),
       ('3c002b94-8db0-11ec-b909-0242ac120002', 'ROLE_WORKER', 'Сотрудник отеля');