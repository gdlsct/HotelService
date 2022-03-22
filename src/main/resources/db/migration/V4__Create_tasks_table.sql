create table if not exists tasks
(
    task_id            bigint not null
        constraint task_pk primary key,
    guest_id           uuid
        constraint task_guest_fk references persons,
    description        varchar,
    status_id          uuid
        constraint task_status_fk references statuses,
    worker_id          uuid
        constraint task_worker_fk references persons,
    rating             varchar,
    dispatcher_comment varchar,
    inventory          varchar
);