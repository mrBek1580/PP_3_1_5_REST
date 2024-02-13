drop table if exists roles, users, user_role;

create table if not exists users
(
    user_id    bigint       not null primary key auto_increment,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    birthday   date,
    username   varchar(255) not null,
    password   varchar(255) not null
);

create table if not exists roles
(
    role_id   bigint       not null primary key auto_increment,
    role_name varchar(255) not null
);

create table if not exists user_role
(
    user_id bigint,
    foreign key (user_id) references users (user_id),
    role_id int,
    foreign key (user_id) references users (user_id)
);

insert into users (first_name, last_name, username, password)
values ('admin', 'admin', 'admin', '$2a$12$ve2VBNIFLRTa5CLqBgO3vuc2y8XvMu0Q5EghPJSUl7Bqi63PMAkW2');

insert into users (first_name, last_name, username, password)
values ('user', 'user', 'user', '$2a$12$rZpAi.sTyElFPz7wrPESoeMbSGzRHUMXsoPCNdONstmy0O2ECTUti');

insert into roles (role_name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into user_role (user_id, role_id)
values ((select user_id from users where username = 'admin'),
        (select role_id from roles where role_name = 'ROLE_ADMIN'));

insert into user_role (user_id, role_id)
values ((select user_id from users where username = 'admin'),
        (select role_id from roles where role_name = 'ROLE_USER'));

insert into user_role (user_id, role_id)
values ((select user_id from users where username = 'user'),
        (select role_id from roles where role_name = 'ROLE_USER'));

