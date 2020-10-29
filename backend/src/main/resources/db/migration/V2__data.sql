-- usuários
insert into usuario (nome, email, senha, tipo, criacao, edicao, ativo)
values ('Administrador 1', 'administrador1@softplan.com.br', 'password', 'ADMINISTRADOR', now(), now(), true);

insert into usuario (nome, email, senha, tipo, criacao, edicao, ativo)
values ('Triador 1', 'triador1@softplan.com.br', 'password', 'TRIADOR', now(), now(), true);

insert into usuario (nome, email, senha, tipo, criacao, edicao, ativo)
values ('Finalizador 1', 'finalizador1@softplan.com.br', 'password', 'FINALIZADOR', now(), now(), true);
insert into usuario (nome, email, senha, tipo, criacao, edicao, ativo)
values ('Finalizador 2', 'finalizador2@softplan.com.br', 'password', 'FINALIZADOR', now(), now(), true);
insert into usuario (nome, email, senha, tipo, criacao, edicao, ativo)
values ('Finalizador 3', 'finalizador3@softplan.com.br', 'password', 'FINALIZADOR', now(), now(), true);

-- processos
insert into processo (usuario_id, assunto, descricao, status, criacao, edicao)
values ((select id from usuario where email = 'triador1@softplan.com.br'), 'Lorem ipsum dolor sit amet',
        'Praesent pellentesque purus id urna pellentesque, quis interdum erat congue. Donec sed laoreet sapien, vitae sagittis augue. Nam suscipit lectus vel mollis pretium. Vivamus bibendum pretium neque, a cursus mauris fringilla in. Nam finibus porttitor pretium. Etiam vel velit libero. Aliquam et justo semper, sagittis risus eget, fringilla magna. In ac leo tortor.',
        'PENDENTE', now(), now());
insert into processo_interessado (processo_id, usuario_id)
values ((select max(id) from processo), (select id from usuario where email = 'finalizador3@softplan.com.br'));

insert into processo (usuario_id, assunto, descricao, status, criacao, edicao)
values ((select id from usuario where email = 'triador1@softplan.com.br'), 'Suspendisse fringilla tellus in dui tempus',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vel euismod massa. Pellentesque id vulputate nibh. Quisque ut placerat dolor. Nam eu velit ex. Duis a magna a lorem pulvinar tempus sed ac dolor. Donec a arcu et leo finibus efficitur ac quis est. Vivamus in iaculis justo. Proin laoreet mi dui, in venenatis nisi maximus sed. Aenean quis mattis urna. Nulla efficitur et massa sit amet interdum. Pellentesque id libero tortor. Phasellus porttitor imperdiet dui, a ultricies lacus finibus et. Aenean sodales ex at dictum vehicula. Pellentesque tincidunt lacinia mi eu convallis.',
        'PENDENTE', now(), now());
insert into processo_interessado (processo_id, usuario_id)
values ((select max(id) from processo), (select id from usuario where email = 'finalizador1@softplan.com.br'));
insert into processo_interessado (processo_id, usuario_id)
values ((select max(id) from processo), (select id from usuario where email = 'finalizador2@softplan.com.br'));

insert into processo (usuario_id, assunto, descricao, status, criacao, edicao)
values ((select id from usuario where email = 'triador1@softplan.com.br'), 'Sed vestibulum porttitor malesuada',
        'Maecenas vitae enim lacinia, maximus leo a, faucibus nisi. Donec aliquet lectus ut finibus gravida. Nulla tempor leo id nunc semper porttitor. Proin blandit nisi eu tortor consectetur mollis. Donec aliquet accumsan lectus et dictum. Nunc sit amet malesuada erat. Quisque at gravida mi. Maecenas porta porta aliquam. Duis metus nunc, eleifend in nunc et, facilisis placerat risus. Nam interdum elit at placerat ullamcorper. Suspendisse porta diam ut faucibus laoreet. Duis sit amet ipsum id orci vestibulum viverra.',
        'PENDENTE', now(), now());
insert into processo_interessado (processo_id, usuario_id)
values ((select max(id) from processo), (select id from usuario where email = 'finalizador1@softplan.com.br'));
insert into processo_interessado (processo_id, usuario_id)
values ((select max(id) from processo), (select id from usuario where email = 'finalizador2@softplan.com.br'));
insert into processo_interessado (processo_id, usuario_id)
values ((select max(id) from processo), (select id from usuario where email = 'finalizador3@softplan.com.br'));