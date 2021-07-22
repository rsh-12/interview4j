create table usr
(
    id         bigserial primary key,
    username   varchar(64)  not null unique,
    password   varchar(256) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,

    check ( length(username) > 3 AND length(password) > 8)
);

create table role
(
    id   bigserial primary key,
    name varchar(64) not null unique
);


create table usr_role
(
    usr_id  bigint references usr (id) on update cascade on delete cascade,
    role_id bigint references role (id) on update cascade on delete cascade,
    constraint usr_role_pkey primary key (usr_id, role_id)
);
