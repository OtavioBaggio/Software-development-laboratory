create database BDAula01;
use bdaula01;

CREATE TABLE pessoa(
    id int AUTO_INCREMENT PRIMARY KEY,
    nome varchar(50) NOT NULL,
    sexo varchar(50) NOT NULL,
    idioma varchar(10) NOT NULL
    );
    
INSERT INTO pessoa (nome, sexo, idioma)
    VALUES ("Ricardo", "M", "Portugues");

INSERT INTO pessoa (nome, sexo, idioma)
    VALUES
    ("Gerald", "M", "Ingles");

INSERT INTO pessoa (nome, sexo, idioma)
    VALUES
    ("Wiliam", "M", "Ingles"),
    ("Umberto", "M", "Espanhol"),
    ("jostein", "M", "Alemao"),
    ("Stephan", "M", "Holandes");
    
CREATE TABLE Alunos(
     id int AUTO_INCREMENT PRIMARY KEY,
     nome varchar(50) NOT NULL,
     idade int NOT NULL,
     curso varchar(50) NOT NULL
     );

INSERT INTO Alunos (nome, idade, curso)
     VALUES
     ("Joao", "20", "Matematica"),
     ("Maria", "22", "Historia"),
     ("Pedro", "21", "Ciência da Computacao"),
     ("Ana", "19", "Biologia"),
     ("Carlos", 23, "Economia");

CREATE TABLE Professores(
     id int AUTO_INCREMENT PRIMARY KEY,
     nome varchar(50) NOT NULL,
     idade int NOT NULL,
     disciplina varchar(50) NOT NULL
     );	

INSERT INTO Professores (nome, idade, disciplina)
     VALUES
     ("Zamberlan", "50", "Estrutura de Dados"),
     ("Ana Paula", "49", "Sistemas Operacionais"),
     ("Mirkos", "47", "IA e Nanociencias");

CREATE TABLE Matriculas(
     id int AUTO_INCREMENT PRIMARY KEY,
     id_aluno INT,
     id_professor int,
     data_matricula DATE,
     FOREIGN KEY (id_aluno) REFERENCES alunos (id),
     FOREIGN KEY (id_professor) REFERENCES professores (id)
     );	

INSERT INTO Matriculas (id_aluno, id_professor, data_matricula)
     VALUES
     (1, 1, '2023-01-15'),
     (2, 2, '2023-02-20'),
     (3, 3, '2023-03-10'),
     (4, 1, '2023-04-05'),
     (5, 2, '2023-05-12');