create table tb_cidade(
    id_cidade bigint not null primary key,
    nome varchar(50) not null,
    qtd_habitantes bigint
);

insert into tb_cidade(id_cidade, nome, qtd_habitantes)
values
(1, 'São Paulo', 123963372),
(2, 'Rio de Janeiro', 1000000),
(3, 'Fortaleza', 8000000),
(4, 'Salvador', 7000000),
(5, 'Belo Horizonte', 6000000),
(6, 'Porto Alegre', 7700000),
(7, 'Porto Velho', 5000000),
(8, 'Palmas', 7800000),
(9, 'Recife', 232000232),
(10, 'Natal', 79799221),
(11, 'Brasilia', 123123433);