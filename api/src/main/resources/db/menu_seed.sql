create table if not exists menus (
    id bigserial primary key,
    name varchar(120) not null,
    address varchar(160),
    position bigint,
    root bigint
);

create table if not exists client_menus (
    client_id bigint not null,
    menu_id bigint not null,
    primary key (client_id, menu_id),
    constraint fk_client_menus_client foreign key (client_id) references clients (id) on delete cascade,
    constraint fk_client_menus_menu foreign key (menu_id) references menus (id) on delete cascade
);

create unique index if not exists uk_menus_name_address
    on menus (name, coalesce(address, ''));

insert into menus (name, address, position, root) values
    ('Sistema', null, 0, null),
    ('Cadastros', null, 1, null),
    ('Movimento', null, 2, null),
    ('Relatorios', null, 3, null),
    ('Integracao', null, 4, null),
    ('Dashboard', '/backoffice/dashboard', 0, 1),
    ('Usuarios', '/backoffice/usuarios', 1, 1),
    ('Perfis de Acesso', '/backoffice/perfis-acesso', 2, 1),
    ('Parametros Gerais', '/backoffice/parametros-gerais', 3, 1),
    ('Auditoria', '/backoffice/auditoria', 4, 1),
    ('Clientes', '/backoffice/clientes', 0, 3),
    ('Fornecedores', '/backoffice/fornecedores', 1, 3),
    ('Produtos', '/backoffice/produtos', 2, 3),
    ('Servicos', '/backoffice/servicos', 3, 3),
    ('Transportadoras', '/backoffice/transportadoras', 4, 3),
    ('Condicoes de Pagamento', '/backoffice/condicoes-pagamento', 5, 3),
    ('Centros de Custo', '/backoffice/centros-custo', 6, 3),
    ('Pedidos de Venda', '/backoffice/pedidos-venda', 0, 7),
    ('Compras', '/backoffice/compras', 1, 7),
    ('Estoque', '/backoffice/estoque', 2, 7),
    ('Faturamento', '/backoffice/faturamento', 3, 7),
    ('Contas a Receber', '/backoffice/contas-receber', 4, 7),
    ('Contas a Pagar', '/backoffice/contas-pagar', 5, 7),
    ('Orcamentos', '/backoffice/orcamentos', 6, 7),
    ('Vendas por Periodo', '/backoffice/relatorios/vendas-periodo', 0, 16),
    ('Fluxo de Caixa', '/backoffice/relatorios/fluxo-caixa', 1, 16),
    ('Posicao de Estoque', '/backoffice/relatorios/posicao-estoque', 2, 16),
    ('Inadimplencia', '/backoffice/relatorios/inadimplencia', 3, 16),
    ('DRE Gerencial', '/backoffice/relatorios/dre-gerencial', 4, 16),
    ('Importacao de Dados', '/backoffice/integracao/importacao-dados', 0, 13),
    ('Exportacao Fiscal', '/backoffice/integracao/exportacao-fiscal', 1, 13),
    ('Notas Fiscais', '/backoffice/integracao/notas-fiscais', 2, 13),
    ('Bancos', '/backoffice/integracao/bancos', 3, 13),
    ('APIs Externas', '/backoffice/integracao/apis-externas', 4, 13)
on conflict (name, (coalesce(address, ''))) do update set
    position = excluded.position,
    root = excluded.root;

insert into client_menus (client_id, menu_id)
select c.id, m.id
from clients c
cross join menus m
where c.user_type = 'ADMIN'
on conflict do nothing;
