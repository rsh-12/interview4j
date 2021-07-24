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

create table user_role
(
    id      bigint primary key,
    user_id bigint references usr (id) on update cascade on delete cascade,
    role_id bigint references role (id) on update cascade on delete cascade,
    unique (user_id, role_id)
);
create table section
(
    id         bigserial primary key,
    title      varchar not null unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    user_id    bigint,
    foreign key (user_id) references usr (id) on delete set null on update cascade
);

create table question
(
    id         bigserial primary key,
    title      varchar not null unique,
    body       text    not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    user_id    bigint references usr (id) on delete set null on update cascade,
    section_id bigint references section (id) on delete set null on update cascade
);

create table question_rating
(
    id               bigserial primary key,
    positive_answers int default 0,
    negative_answers int default 0,
    user_id          bigint references usr (id) on delete cascade on update cascade,
    question_id      bigint references question (id) on delete cascade on update cascade
);