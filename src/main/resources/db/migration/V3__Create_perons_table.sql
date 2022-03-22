create table if not exists persons
(
    dtype      varchar not null,
    person_id  uuid    not null
        constraint person_pk primary key,
    image_url  varchar not null,
    login      varchar not null unique,
    password   varchar not null,
    first_name varchar,
    last_name  varchar,
    room       int,
    is_active  boolean,
    role_id    uuid
);

insert into persons(dtype, person_id, image_url, login, password, first_name, last_name, room, is_active, role_id)
values ('Admin', '76a1395e-8dc0-11ec-b909-0242ac120002',
        'https://camo.githubusercontent.com/dee371eb747eca908a562cd37334ab9257fad5a456dd34b46596185b119b285a/68747470733a2f2f63646e2e6f6e6c696e65776562666f6e74732e636f6d2f7376672f696d675f3332353738382e706e67',
        'admin',
        '$2a$10$xBaASTj5tWeZUNwk1y9cJeaFwwoRjmuZU5LlHyXkg3h1O5f7PuIN2', 'Админ', 'Админов', null, true,
        '3c0024aa-8db0-11ec-b909-0242ac120002'),
       ('Guest', '76a13daa-8dc0-11ec-b909-0242ac120002',
        'https://memepedia.ru/wp-content/uploads/2017/12/%D0%B3%D0%BE%D0%BB%D0%BE%D0%B2%D0%B0.jpg',
        'guest1',
        '$2a$10$xBaASTj5tWeZUNwk1y9cJeaFwwoRjmuZU5LlHyXkg3h1O5f7PuIN2', 'Богдан', 'Богданов', 1408, true,
        '3c002716-8db0-11ec-b909-0242ac120002'),
       ('Guest', '76a13efe-8dc0-11ec-b909-0242ac120002',
        'https://cs10.pikabu.ru/post_img/2018/03/19/6/1521451373148172212.jpg',
        'guest2',
        '$2a$10$xBaASTj5tWeZUNwk1y9cJeaFwwoRjmuZU5LlHyXkg3h1O5f7PuIN2', 'Владимир', 'Владимиров', 666, true,
        '3c002716-8db0-11ec-b909-0242ac120002'),
       ('Dispatcher', '76a1403e-8dc0-11ec-b909-0242ac120002',
        'https://thumbs.dreamstime.com/z/%D0%B4%D0%B8%D1%81%D0%BF%D0%B5%D1%82%D1%87%D0%B5%D1%80-12698557.jpg',
        'dispatcher',
        '$2a$10$xBaASTj5tWeZUNwk1y9cJeaFwwoRjmuZU5LlHyXkg3h1O5f7PuIN2', 'Георгий', 'Георгиев', null, true,
        '3c002a4a-8db0-11ec-b909-0242ac120002'),
       ('Worker', '76a14160-8dc0-11ec-b909-0242ac120002', 'https://22.img.avito.st/640x480/8665237622.jpg', 'worker1',
        '$2a$10$xBaASTj5tWeZUNwk1y9cJeaFwwoRjmuZU5LlHyXkg3h1O5f7PuIN2', 'Дмитрий', 'Дмитриев', null, true,
        '3c002b94-8db0-11ec-b909-0242ac120002'),
       ('Worker', '76a14282-8dc0-11ec-b909-0242ac120002',
        'https://careerist.ru/news/wp-content/uploads/2017/08/%D1%80%D0%B0%D0%B1%D0%BE%D1%87%D0%B8%D0%B9-810x540.jpg',
        'worker2',
        '$2a$10$xBaASTj5tWeZUNwk1y9cJeaFwwoRjmuZU5LlHyXkg3h1O5f7PuIN2', 'Евгений', 'Евгениев', null, true,
        '3c002b94-8db0-11ec-b909-0242ac120002');