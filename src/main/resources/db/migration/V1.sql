create table if not exists company
(
    inn                   integer     primary key,
    name_company          varchar(50) not null,
    legal_address         varchar     not null,
    actual_address        varchar     not null,
    kpp                   integer     not null,
    bank                  varchar     not null,
    correspondent_account varchar     not null,
    payment_account       varchar     not null,
    bic                   integer     not null,
    ogrn                  bigint      not null,
    email                 varchar(50) not null,
    phone                 varchar(30) not null,
    fio                   varchar(50) not null,
    post                  varchar(50) not null
)
