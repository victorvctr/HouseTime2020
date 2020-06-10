# HouseTime2020

Incluir usuário ADMIN no banco:
insert into usuario values (1,true,'988896559','44315458821','2000-02-02','admin@admin.com','admin','$2a$10$paMEJH7Fh.hkD3/xm1v6oul9dzW0/N9WzE4whYbMyhMtg9D4Essbq','aminin',1)

Incluir GRUPOS de usuários:
insert into grupo values (1,'Administrador');
insert into grupo values (2,'Colaborador');
insert into grupo values (3,'Cliente');
insert into grupo values (4,'Gerente');
insert into grupo values (5,'ADMINISTRADOR');

Relacionar usuario ADMIN com o grupo ADMINISTRADOR:
insert into usuario_grupo values (1,1);

Incluir PERMISSÕES de acesso:
insert into permissao values (1, 'CADASTRAR_USUARIO');
insert into permissao values (2, 'CADASTRAR_PRODUTO');
insert into permissao values (3, 'VISUALIZAR_PRODUTO');
insert into permissao values (4, 'VISUALIZAR_USUARIO');
insert into permissao values (5, 'ADMINISTRADOR');

Relacionar GRUPOS x PERMISSÕES:
insert into grupo_permissao values (1,1);
insert into grupo_permissao values (1,2);
insert into grupo_permissao values (1,3);
insert into grupo_permissao values (1,4);
insert into grupo_permissao values (3,3);
insert into grupo_permissao values (1,5);

Para logar no sistema usar as credenciais:
e-mail: admin@admin.com
senha: 123
