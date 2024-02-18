drop table if exists roles, users, user_roles;

create table if not exists users
(
    id         bigint primary key auto_increment,
    first_name varchar(50)  not null,
    last_name  varchar(50)  not null,
    age        int          not null,
    username   varchar(50)  not null,
    password   varchar(255) not null
);

create table if not exists roles
(
    id        bigint primary key auto_increment,
    role_name varchar(50) not null
);

create table if not exists user_roles
(
    user_id bigint,
    foreign key (user_id) references users (id),
    role_id bigint,
    foreign key (user_id) references users (id),
    primary key (user_id, role_id)
);

insert into users (first_name, last_name, age, username, password)
values ('admin', 'admin', 30, 'admin@mail.ru', '$2a$12$ve2VBNIFLRTa5CLqBgO3vuc2y8XvMu0Q5EghPJSUl7Bqi63PMAkW2');

insert into users (first_name, last_name, age, username, password)
values ('user', 'user', 20, 'user@mail.ru', '$2a$12$rZpAi.sTyElFPz7wrPESoeMbSGzRHUMXsoPCNdONstmy0O2ECTUti');

insert into roles (role_name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into user_roles (user_id, role_id)
values ((select id from users where username = 'admin@mail.ru'),
        (select id from roles where role_name = 'ROLE_ADMIN'));

insert into user_roles (user_id, role_id)
values ((select id from users where username = 'admin@mail.ru'),
        (select id from roles where role_name = 'ROLE_USER'));

insert into user_roles (user_id, role_id)
values ((select id from users where username = 'user@mail.ru'),
        (select id from roles where role_name = 'ROLE_USER'));

