create table if not exists statuses
(
    status_id   uuid    not null
        constraint status_pk primary key,
    status_name varchar not null
);

insert into statuses(status_id, status_name)
values ('31489a70-8f6c-11ec-b909-0242ac120002', 'Создано'),
       ('31489c78-8f6c-11ec-b909-0242ac120002', 'Назначено'),
       ('31489da4-8f6c-11ec-b909-0242ac120002', 'Отменено гостем'),
       ('31489ebc-8f6c-11ec-b909-0242ac120002', 'Отменено сотрудником'),
       ('31489fca-8f6c-11ec-b909-0242ac120002', 'Отменено диспетчером'),
       ('3148a0ec-8f6c-11ec-b909-0242ac120002', 'Выполнено');