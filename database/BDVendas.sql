
CREATE DATABASE BDSALES;

CREATE USER 'juanap'@'%' IDENTIFIED BY '123456';

GRANT ALL ON *.* TO 'juanap'@'%' WITH GRANT OPTION;

flush privileges;

USE BDSALES;

/***** TABELA CLIENTES *****/
CREATE TABLE tb_clients (
  id int auto_increment primary key,
  clientname varchar(100),
  rg varchar(9),
  cpf varchar(12),
  email varchar(200),
  phone varchar(10),
  cellphone varchar(11),
  cep varchar(8),
  address varchar (255),
  addressnumber int,
  complement varchar (200),
  neighborhood varchar (100),
  city varchar (100),
  state varchar (2)
);
/*****************/

/***** TABELA FORNECEDORES *****/
CREATE TABLE tb_suppliers (
  id int auto_increment primary key,
  suppliername varchar(100),
  cnpj varchar (14),
  email varchar(200),
  phone varchar(10),
  cellphone varchar(11),
  cep varchar(8),
  address varchar (255),
  addressnumber int,
  complement varchar (200),
  neighborhood varchar (100),
  city varchar (100),
  state varchar (2)
);
/*****************/

/***** TABELA FUNCIONARIOS *****/
CREATE TABLE tb_employees (
  id int auto_increment primary key,
  employeename varchar(100),
  rg varchar(9),
  cpf varchar(12),
  email varchar(200),
  passcode varchar(10),
  position varchar(100),
  accesslevel varchar(50),
  phone varchar(10),
  cellphone varchar(11),
  cep varchar(8),
  address varchar (255),
  addressnumber int,
  complement varchar (200),
  neighborhood varchar (100),
  city varchar (100),
  state varchar (2)
);
/*****************/


/***** TABELA PRODUTOS *****/
CREATE TABLE tb_products (
  id int auto_increment primary key,
  productdescription varchar(100),
  price decimal (10,2),
  stock int,
  supplierid int,

  FOREIGN KEY (supplierid) REFERENCES tb_suppliers(id)
);
/*****************/

/***** TABELA VENDAS *****/
CREATE TABLE tb_sales (
  id int auto_increment primary key,
  clientid int,
  saledate datetime,
  totalsale decimal (10,2),
  observations text,

  FOREIGN KEY (clientid) REFERENCES tb_clients(id)
);
/*****************/

/***** TABELA ITENS_VENDAS *****/
CREATE TABLE tb_salesitens (
  id int auto_increment primary key,
  saleid int,
  productid int,
  quantity int,
  subtotal decimal (10,2),

  FOREIGN KEY (saleid) REFERENCES tb_sales(id),
  FOREIGN KEY (productid) REFERENCES tb_products(id)
);
/*****************/


select * from tb_clients;
select * from tb_employees;