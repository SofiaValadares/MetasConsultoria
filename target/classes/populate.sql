CREATE TABLE User (
                      cod_user 		INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      name 			VARCHAR(40) NOT NULL,
                      email 			VARCHAR(31) NOT NULL UNIQUE,
                      password 		VARCHAR(16) NOT NULL
);

CREATE TABLE Collaborator (
                              city            VARCHAR(40) NOT NULL,
                              neighborhood    VARCHAR(20) NOT NULL,
                              street          VARCHAR(40) NOT NULL,
                              house_number    INT NOT NULL,
                              complement      VARCHAR(50),
                              phone1          VARCHAR(11) NOT NULL,
                              phone2          VARCHAR(11),

                              cod_user        INT NOT NULL,

                              PRIMARY KEY (cod_user),
                              CONSTRAINT collaborator_pk_user
                                  FOREIGN KEY (cod_user) REFERENCES User(cod_user),

                              supervised_by   INT,

                              CONSTRAINT fk_supervisor
                                  FOREIGN KEY (supervised_by) REFERENCES Collaborator(cod_user)
);

CREATE TABLE Municipio (
                           cod_municipio       INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           nome VARCHAR(100)       NOT NULL,
                           estado VARCHAR(100)     NOT NULL
);

CREATE TABLE Client (
                        cod_user        INT NOT NULL,
                        pk_city    INT NOT NULL,

                        PRIMARY KEY (cod_user),
                        CONSTRAINT client_pk_user
                            FOREIGN KEY (cod_user) REFERENCES User(cod_user),

                        CONSTRAINT client_pk_city
                            FOREIGN KEY (pk_city) REFERENCES Municipio(cod_municipio)
);

CREATE TABLE Projeto (
                         cod_projeto         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(255)       NOT NULL,
                         descricao               TEXT,
                         publico                 BOOLEAN

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

