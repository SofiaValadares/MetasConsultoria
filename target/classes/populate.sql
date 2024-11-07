CREATE DATABASE IF NOT EXISTS DatabaseMetas;
USE DatabaseMetas;

CREATE TABLE User (
                      cod_user 		INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      name 			VARCHAR(40) NOT NULL,
                      email 		VARCHAR(31) NOT NULL UNIQUE,
                      password 		VARCHAR(16) NOT NULL
);

CREATE TABLE Collaborator (
                              city            VARCHAR(40) NOT NULL,
                              neighborhood    VARCHAR(20) NOT NULL,
                              street          VARCHAR(40) NOT NULL,
                              house_number    INTEGER NOT NULL,
                              complement      VARCHAR(50),
                              phone1          VARCHAR(11) NOT NULL,
                              phone2          VARCHAR(11),

                              cod_user        INTEGER NOT NULL,

                              PRIMARY KEY (cod_user),
                              CONSTRAINT collaborator_pk_user
                                  FOREIGN KEY (cod_user) REFERENCES User(cod_user),

                              supervised_by   INTEGER,

                              CONSTRAINT fk_supervisor
                                  FOREIGN KEY (supervised_by) REFERENCES Collaborator(cod_user)
);

CREATE TABLE City (
                           cod_city                INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           name                    VARCHAR(100) NOT NULL,
                           state                   VARCHAR(100) NOT NULL
);

CREATE TABLE Client (
                        cod_user        INTEGER NOT NULL,
                        pk_city         INTEGER NOT NULL,

                        PRIMARY KEY (cod_user),
                        CONSTRAINT client_pk_user
                            FOREIGN KEY (cod_user) REFERENCES User(cod_user),

                        CONSTRAINT client_pk_city
                            FOREIGN KEY (pk_city) REFERENCES City(cod_city)
);

CREATE TABLE Project (
                         cod_project        INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         name               VARCHAR(255) NOT NULL,
                         description        VARCHAR(1500),
                         public             BIT DEFAULT 0,
                         date               DATE

);

INSERT INTO User (name, email, password) VALUES
                                             ('Andresa', 'andresa@exemplo.com', 'Oi12345!'),
                                             ('Carlos', 'carlos@exemplo.com', 'SenhaSegura1'),
                                             ('Mariana', 'mariana@exemplo.com', 'SenhaForte2023'),
                                             ('Felipe', 'felipe@exemplo.com', 'Password123!'),
                                             ('Bianca', 'bianca@exemplo.com', 'B!@nc4Secreta'),
                                             ('Lucas', 'lucas@exemplo.com', 'Lucas@2024'),
                                             ('Ana', 'ana@exemplo.com', 'A123na!Pass'),
                                             ('Rafael', 'rafael@exemplo.com', 'Rafa#3456'),
                                             ('Juliana', 'juliana@exemplo.com', 'Jul!@2024'),
                                             ('Renato', 'renato@exemplo.com', 'RenatoS#321'),
                                             ('Paula', 'paula@exemplo.com', 'Pa$$w0rd789'),
                                             ('Gabriel', 'gabriel@exemplo.com', 'Gab123!Gab'),
                                             ('Beatriz', 'beatriz@exemplo.com', 'BiaBia2024@'),
                                             ('Ricardo', 'ricardo@exemplo.com', 'Ri@Do1975'),
                                             ('Isabela', 'isabela@exemplo.com', 'I$abela2024'),
                                             ('Henrique', 'henrique@exemplo.com', 'Henri@45'),
                                             ('Fernanda', 'fernanda@exemplo.com', 'F3rnand@2024'),
                                             ('Eduardo', 'eduardo@exemplo.com', 'Edu#5678'),
                                             ('Larissa', 'larissa@exemplo.com', 'L@riss@2023'),
                                             ('Thiago', 'thiago@exemplo.com', 'Thi#Thi456!');

INSERT INTO Collaborator(city, neighborhood, street, house_number, complement, phone1, phone2, cod_user, supervised_by) VALUES
                                                                                                                            ('Recife', 'Boa Viagem', 'Avenida Conselheiro Aguiar', 1234, 'Apt 301', '81912345678', '81987654321', 1, null),
                                                                                                                            ('Olinda', 'Casa Caiada', 'Rua do Sol', 56, 'Próximo ao Shopping Patteo', '81923456789', '81976543210', 2, 1),
                                                                                                                            ('Caruaru', 'Maurício de Nassau', 'Avenida Agamenon Magalhães', 987, null, '81934567890', '81965432109', 3, 1),
                                                                                                                            ('Recife', 'Boa Vista', 'Rua do Hospício', 234, 'Sala 101', '81945678901', '81954321098', 4, 1),
                                                                                                                            ('Petrolina', 'Centro', 'Rua Pacífico da Luz', 789, 'Ed. Petrolina', '81956789012', '81943210987', 5, 1);

INSERT INTO City (name, state) VALUES
                                         ('Recife', 'Pernambuco'),
                                         ('Olinda', 'Pernambuco'),
                                         ('Caruaru', 'Pernambuco'),
                                         ('Petrolina', 'Pernambuco'),
                                         ('João Pessoa', 'Paraíba'),
                                         ('Campina Grande', 'Paraíba'),
                                         ('Fortaleza', 'Ceará'),
                                         ('Natal', 'Rio Grande do Norte'),
                                         ('Maceió', 'Alagoas'),
                                         ('Salvador', 'Bahia');


INSERT INTO Client (cod_user, pk_city) VALUES
                                           (1, 1),   
                                           (2, 2),   
                                           (3, 3),   
                                           (4, 4),   
                                           (5, 5),   
                                           (6, 6),   
                                           (7, 7),   
                                           (8, 8),   
                                           (9, 9),   
                                           (10, 10); 

INSERT INTO Project (name, description, public) VALUES
                                                   ('Projeto de Pavimentação Urbana', 
                                                   'Planejamento e execução de pavimentação de ruas e avenidas.', 
                                                   1),
                                                
                                                   ('Projeto de Iluminação Pública', 
                                                   'Instalação de postes e lâmpadas LED em áreas de baixa iluminação.', 
                                                   1),
                                                
                                                   ('Plano Diretor de Desenvolvimento', 
                                                   'Desenvolvimento de um plano diretor para orientar o crescimento urbano.', 
                                                   0),
                                                
                                                   ('Projeto de Saneamento Básico', 
                                                   'Construção de redes de esgoto e estações de tratamento.', 
                                                   1),
                                                
                                                   ('Programa de Saúde Preventiva', 
                                                   'Campanha de vacinação e consultas preventivas para a população.', 
                                                   1),
                                                
                                                   ('Iniciativa de Segurança Pública', 
                                                   'Parceria com as forças de segurança para reduzir a criminalidade.', 
                                                   0),
                                                
                                                   ('Recuperação de Áreas Verdes', 
                                                   'Reflorestamento e criação de parques em áreas degradadas.', 
                                                   1),
                                                
                                                   ('Projeto de Educação Ambiental', 
                                                   'Campanhas educativas sobre preservação do meio ambiente.', 
                                                   1),
                                                
                                                   ('Modernização de Infraestrutura Digital', 
                                                   'Implementação de fibra óptica e ampliação do acesso à internet.', 
                                                   0),
                                                
                                                   ('Aprimoramento de Transporte Público', 
                                                   'Compra de novos ônibus e criação de faixas exclusivas.', 
                                                   1);
