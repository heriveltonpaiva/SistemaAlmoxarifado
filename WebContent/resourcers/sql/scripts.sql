CREATE TABLE almoxarifado.contato(
	id integer NOT NULL,
	telefone varchar(9),
	celular varchar(9),
	email varchar(80) NOT NULL,
	CONSTRAINT pk_contato PRIMARY KEY (id)
);
CREATE TABLE almoxarifado.estado(
	id integer NOT NULL,
	descricao varchar NOT NULL,
	CONSTRAINT pk_id_estado PRIMARY KEY (id)
);
CREATE TABLE almoxarifado.cidade(
	id integer NOT NULL,
	descricao varchar(50) NOT NULL,
	id_estado integer NOT NULL,
	CONSTRAINT pk_cidade PRIMARY KEY (id),
	CONSTRAINT fk_estado FOREIGN KEY (id_estado)
	REFERENCES almoxarifado.estado (id)
);
CREATE TABLE almoxarifado.bairro(
	id integer NOT NULL,
	descricao varchar(50) NOT NULL,
	id_cidade integer NOT NULL,
	CONSTRAINT pk_bairro PRIMARY KEY (id)

);

CREATE TABLE almoxarifado.endereco(
	id integer NOT NULL,
	descricao varchar(100) NOT NULL,
	numero integer,
	complemento varchar(50),
	cep varchar(9),
	id_bairro integer NOT NULL,
	CONSTRAINT pk_endereco PRIMARY KEY (id),
	CONSTRAINT fk_bairro FOREIGN KEY (id_bairro)
	REFERENCES almoxarifado.bairro (id)

);

CREATE TABLE users
(
  id integer NOT NULL,
  username character varying NOT NULL,
  password character varying NOT NULL,
  authority character varying,
  CONSTRAINT id_users_pk PRIMARY KEY (id),
  CONSTRAINT username UNIQUE (username)
);


CREATE SEQUENCE users_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE SEQUENCE almoxarifado.estado_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE SEQUENCE almoxarifado.cidade_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

 CREATE SEQUENCE almoxarifado.bairro_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
 CREATE SEQUENCE almoxarifado.endereco_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE SEQUENCE almoxarifado.contato_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

  INSERT INTO users(
            id, username, password, authority)
    VALUES (
1,'admin','admin','ROLE_USER')