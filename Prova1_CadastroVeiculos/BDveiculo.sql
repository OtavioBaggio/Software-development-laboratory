CREATE DATABASE BDveiculo;
use BDveiculo;

CREATE TABLE Veiculos(
	id int auto_increment PRIMARY KEY,
    marca varchar(50) NOT NULL,
    modelo varchar(50) NOT NULL,
    ano int NOT NULL,
    placa varchar(20) NOT NULL,
    cor varchar(20) NOT NULL
);

INSERT INTO Veiculos (marca, modelo, ano, placa, cor)
	VALUES
    ("Toyota", "Corolla", "2013", "HLV1013", "Preto"),
    ("Mitsubichi", "Lancer GT", "2015", "LLV1018", "Roxo");

show tables;

select * from veiculos;