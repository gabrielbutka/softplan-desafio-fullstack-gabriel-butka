create table usuario
(
    id      bigserial    not null
        constraint usuario_pk primary key,
    nome    varchar(100) not null,
    email   varchar(100) not null,
    senha   varchar(32)  not null,
    tipo    varchar(20)  not null,
    criacao timestamp    not null,
    edicao  timestamp    not null,
    ativo   boolean      not null,
    constraint usuario_uk unique (email)
);

create index usuario_email_ix on usuario (email);

create table processo
(
    id         bigserial     not null
        constraint processo_pk primary key,
    usuario_id bigint        not null
        constraint processo_fk_usuario references usuario,
    assunto    varchar(100)  not null,
    descricao  varchar(1000) not null,
    status     varchar(20)   not null,
    criacao    timestamp     not null,
    edicao     timestamp     not null
);

create index processo_usuario_id_ix on processo (usuario_id);

create table processo_interessado
(
    processo_id bigint not null
        constraint processo_interessado_fk_processo references processo,
    usuario_id  bigint not null
        constraint processo_interessado_fk_usuario references usuario,
    constraint processo_interessado_uk unique (processo_id, usuario_id)
);

create index processo_interessado_processo_id_ix on processo_interessado (processo_id);
create index processo_interessado_usuario_id_ix on processo_interessado (usuario_id);

create table parecer
(
    id          bigserial     not null
        constraint parecer_pk primary key,
    processo_id bigint        not null
        constraint parecer_fk_processo references processo,
    usuario_id  bigint        not null
        constraint parecer_fk_usuario references usuario,
    parecer     varchar(1000) not null,
    criacao     timestamp     not null,
    edicao      timestamp     not null,
    constraint parecer_uk unique (processo_id, usuario_id)
);

create index parecer_processo_id_ix on parecer (processo_id);
create index parecer_usuario_id_ix on parecer (usuario_id);
