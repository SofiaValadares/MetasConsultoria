CREATE DATABASE IF NOT EXISTS DatabaseMetas;
USE DatabaseMetas;

CREATE TABLE User (
                      cod_user      INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      name          VARCHAR(40) NOT NULL,
                      email         VARCHAR(31) NOT NULL UNIQUE,
                      password      VARCHAR(16) NOT NULL
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
                              CONSTRAINT collaborator_fk_user
                                  FOREIGN KEY (cod_user) REFERENCES User(cod_user) ON DELETE CASCADE,

                              supervised_by   INTEGER,

                              CONSTRAINT fk_supervisor
                                  FOREIGN KEY (supervised_by) REFERENCES Collaborator(cod_user) ON DELETE CASCADE
);

CREATE TABLE City (
                      cod_city    INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      name        VARCHAR(100) NOT NULL,
                      state       VARCHAR(100) NOT NULL
);

CREATE TABLE Client (
                        cod_user    INTEGER NOT NULL,
                        fk_city     INTEGER NOT NULL,

                        PRIMARY KEY (cod_user),
                        CONSTRAINT client_fk_user
                            FOREIGN KEY (cod_user) REFERENCES User(cod_user) ON DELETE CASCADE,

                        CONSTRAINT client_fk_city
                            FOREIGN KEY (fk_city) REFERENCES City(cod_city) ON DELETE CASCADE
);

CREATE TABLE Project (
                         cod_project    INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         name           VARCHAR(255) NOT NULL,
                         description    VARCHAR(1500),
                         public         BIT DEFAULT 0,
                         date           DATE,
                         money          DOUBLE,

                         fk_city        INTEGER NOT NULL,

                         CONSTRAINT project_fk_city
                             FOREIGN KEY (fk_city) REFERENCES City(cod_city) ON DELETE CASCADE
);

CREATE TABLE R_Collaborator_Client_Project (
                                               fk_collaborator     INTEGER NOT NULL,
                                               fk_client           INTEGER NOT NULL,
                                               fk_project          INTEGER NOT NULL,

                                               PRIMARY KEY (fk_collaborator, fk_client, fk_project),

                                               CONSTRAINT r_collaborator_client_project_fk_collaborator
                                                   FOREIGN KEY (fk_collaborator) REFERENCES Collaborator(cod_user) ON DELETE CASCADE,

                                               CONSTRAINT r_collaborator_client_project_fk_client
                                                   FOREIGN KEY (fk_client) REFERENCES Client(cod_user) ON DELETE CASCADE,

                                               CONSTRAINT r_collaborator_client_project_fk_project
                                                   FOREIGN KEY (fk_project) REFERENCES Project(cod_project) ON DELETE CASCADE
);

CREATE TABLE Next_Steps (
                            next_steps_pk   INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            next_steps      VARCHAR(255) NOT NULL,
                            fk_project      INTEGER NOT NULL,

                            CONSTRAINT next_steps_fk_project
                                FOREIGN KEY (fk_project) REFERENCES Project(cod_project) ON DELETE CASCADE
);

CREATE TABLE Reservation (
                             reservation_id             INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             start_time                 TIME NOT NULL,
                             end_time                   TIME NOT NULL,
                             day                        DATE NOT NULL,

                             fk_collaborator_cod_user   INTEGER NOT NULL,
                             fk_client_cod_user         INTEGER,

                             CONSTRAINT fk_reservation_collaborator
                                 FOREIGN KEY (fk_collaborator_cod_user) REFERENCES Collaborator(cod_user) ON DELETE CASCADE,

                             CONSTRAINT fk_reservation_client
                                 FOREIGN KEY (fk_client_cod_user) REFERENCES Client(cod_user) ON DELETE CASCADE
);

CREATE TABLE Report (
                        cod_report      INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        report_date     DATE NOT NULL,
                        description     VARCHAR(255) NOT NULL
);

CREATE TABLE RCollaborator_Project_Report (
                                              fk_collaborator_user    INTEGER NOT NULL,
                                              fk_project              INTEGER NOT NULL,
                                              fk_report               INTEGER NOT NULL,

                                              PRIMARY KEY (fk_collaborator_user, fk_project, fk_report),

                                              CONSTRAINT fk_cpr_collaborator
                                                  FOREIGN KEY (fk_collaborator_user) REFERENCES Collaborator(cod_user) ON DELETE CASCADE,

                                              CONSTRAINT fk_cpr_project
                                                  FOREIGN KEY (fk_project) REFERENCES Project(cod_project) ON DELETE CASCADE,

                                              CONSTRAINT fk_cpr_report
                                                  FOREIGN KEY (fk_report) REFERENCES Report(cod_report) ON DELETE CASCADE
);

CREATE TRIGGER validate_password
    BEFORE UPDATE ON User
    FOR EACH ROW
BEGIN
    IF CHAR_LENGTH(NEW.password) < 8 OR CHAR_LENGTH(NEW.password) > 16 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'A senha deve ter entre 8 e 16 caracteres.';
    END IF;

    IF NEW.password NOT REGEXP '[A-Z]' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'A senha deve conter ao menos uma letra maiúscula.';
    END IF;

    IF NEW.password NOT REGEXP '[a-z]' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'A senha deve conter ao menos uma letra minúscula.';
    END IF;

    IF NEW.password NOT REGEXP '[0-9]' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'A senha deve conter ao menos um número.';
    END IF;
END;

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

INSERT INTO Collaborator (city, neighborhood, street, house_number, complement, phone1, phone2, cod_user, supervised_by) VALUES
                                                                                                                             ('Recife', 'Boa Viagem', 'Avenida Conselheiro Aguiar', 1234, 'Apt 301', '81912345678', '81987654321', 1, NULL),
                                                                                                                             ('Olinda', 'Casa Caiada', 'Rua do Sol', 56, 'Próximo ao Shopping Patteo', '81923456789', '81976543210', 2, 1),
                                                                                                                             ('Caruaru', 'Maurício de Nassau', 'Avenida Agamenon Magalhães', 987, NULL, '81934567890', '81965432109', 3, 1),
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

INSERT INTO Client (cod_user, fk_city) VALUES
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

INSERT INTO Project (name, description, public, date, fk_city, money) VALUES
                                                                          -- Projetos com data preenchida
                                                                          ('Projeto 1', 'Descrição do projeto 1.', 0, '2024-01-15', 1, 500.0),
                                                                          ('Projeto 2', 'Descrição do projeto 2.', 0, '2024-02-20', 2, 200.0),
                                                                          ('Projeto 3', 'Descrição do projeto 3.', 0, '2024-03-05', 3, 50.0),
                                                                          ('Projeto 4', 'Descrição do projeto 4.', 0, '2024-04-10', 4, 10.0),
                                                                          ('Projeto 5', 'Descrição do projeto 5.', 0, '2024-05-25', 5, 1000.0),
                                                                          ('Projeto 6', 'Descrição do projeto 6.', 0, '2024-06-15', 6, 5.0),
                                                                          ('Projeto 7', 'Descrição do projeto 7.', 0, '2024-07-20', 7, 1.5),
                                                                          ('Projeto 8', 'Descrição do projeto 8.', 0, '2024-08-10', 8, 300.0),
                                                                          ('Projeto 9', 'Descrição do projeto 9.', 0, '2024-09-05', 9, 100.0),
                                                                          ('Projeto 10', 'Descrição do projeto 10.', 1, '2024-10-15', 10, 20.0),
                                                                          ('Projeto 11', 'Descrição do projeto 11.', 1, '2024-11-10', 1, 15.0),
                                                                          ('Projeto 12', 'Descrição do projeto 12.', 1, '2024-12-05', 2, 2.0),
                                                                          ('Projeto 13', 'Descrição do projeto 13.', 1, '2025-01-15', 3, 25.0),
                                                                          ('Projeto 14', 'Descrição do projeto 14.', 0, '2025-02-25', 4, 600.0),
                                                                          ('Projeto 15', 'Descrição do projeto 15.', 0, '2025-03-30', 5, 750.0),
                                                                          ('Projeto 16', 'Descrição do projeto 16.', 1, '2025-04-15', 6, 90.0),
                                                                          ('Projeto 17', 'Descrição do projeto 17.', 1, '2025-05-10', 7, 45.0),
                                                                          ('Projeto 18', 'Descrição do projeto 18.', 1, '2025-06-20', 8, 10.0),
                                                                          ('Projeto 19', 'Descrição do projeto 19.', 1, '2025-07-15', 9, 300.0),
                                                                          ('Projeto 20', 'Descrição do projeto 20.', 1, '2025-08-25', 10, 50.0),
                                                                          -- Projetos com data como NULL
                                                                          ('Projeto 21', 'Descrição do projeto 21.', 0, NULL, 1, 5.0),
                                                                          ('Projeto 22', 'Descrição do projeto 22.', 0, NULL, 2, 2.0),
                                                                          ('Projeto 23', 'Descrição do projeto 23.', 0, NULL, 3, 8.0),
                                                                          ('Projeto 24', 'Descrição do projeto 24.', 0, NULL, 4, 20.0),
                                                                          ('Projeto 25', 'Descrição do projeto 25.', 0, NULL, 5, 15.0),
                                                                          ('Projeto 26', 'Descrição do projeto 26.', 0, NULL, 6, 30.0),
                                                                          ('Projeto 27', 'Descrição do projeto 27.', 0, NULL, 7, 40.0),
                                                                          ('Projeto 28', 'Descrição do projeto 28.', 1, NULL, 8, 100.0),
                                                                          ('Projeto 29', 'Descrição do projeto 29.', 1, NULL, 9, 200.0),
                                                                          ('Projeto 30', 'Descrição do projeto 30.', 1, NULL, 10, 12.0),
                                                                          ('Projeto 31', 'Descrição do projeto 31.', 0, NULL, 1, 80.0),
                                                                          ('Projeto 32', 'Descrição do projeto 32.', 0, NULL, 2, 90.0),
                                                                          ('Projeto 33', 'Descrição do projeto 33.', 0, NULL, 3, 300.0),
                                                                          ('Projeto 34', 'Descrição do projeto 34.', 0, NULL, 4, 500.0),
                                                                          ('Projeto 35', 'Descrição do projeto 35.', 0, NULL, 5, 700.0),
                                                                          ('Projeto 36', 'Descrição do projeto 36.', 1, NULL, 6, 1000.0),
                                                                          ('Projeto 37', 'Descrição do projeto 37.', 1, NULL, 7, 2000.0),
                                                                          ('Projeto 38', 'Descrição do projeto 38.', 1, NULL, 8, 15.0),
                                                                          ('Projeto 39', 'Descrição do projeto 39.', 1, NULL, 9, 25.0),
                                                                          ('Projeto 40', 'Descrição do projeto 40.', 1, NULL, 10, 35.0);

INSERT INTO R_Collaborator_Client_Project (fk_collaborator, fk_client, fk_project) VALUES
                                                                                       (1, 1, 1),
                                                                                       (2, 2, 2),
                                                                                       (3, 3, 3),
                                                                                       (4, 4, 4),
                                                                                       (5, 5, 5),
                                                                                       (1, 6, 6),
                                                                                       (2, 7, 7),
                                                                                       (3, 8, 8),
                                                                                       (4, 9, 9),
                                                                                       (5, 10, 10),
                                                                                       (1, 1, 11),
                                                                                       (2, 2, 12),
                                                                                       (3, 3, 13),
                                                                                       (4, 4, 14),
                                                                                       (5, 5, 15),
                                                                                       (1, 6, 16),
                                                                                       (2, 7, 17),
                                                                                       (3, 8, 18),
                                                                                       (4, 9, 19),
                                                                                       (5, 10, 20),
                                                                                       (1, 1, 21),
                                                                                       (2, 2, 22),
                                                                                       (3, 3, 23),
                                                                                       (4, 4, 24),
                                                                                       (5, 5, 25),
                                                                                       (1, 6, 26),
                                                                                       (2, 7, 27),
                                                                                       (3, 8, 28),
                                                                                       (4, 9, 29),
                                                                                       (5, 10, 30),
                                                                                       (1, 1, 31),
                                                                                       (2, 2, 32),
                                                                                       (3, 3, 33),
                                                                                       (4, 4, 34),
                                                                                       (5, 5, 35),
                                                                                       (1, 6, 36),
                                                                                       (2, 7, 37),
                                                                                       (3, 8, 38),
                                                                                       (4, 9, 39),
                                                                                       (5, 10, 40);

INSERT INTO Next_Steps (next_steps, fk_project) VALUES
                                                    ('Finalizar a contratação de fornecedores', 1),
                                                    ('Elaborar plano de execução', 2),
                                                    ('Agendar reuniões com stakeholders', 3),
                                                    ('Realizar análise de impacto', 4),
                                                    ('Enviar documentação para aprovação', 5),
                                                    ('Definir cronograma de implementação', 6),
                                                    ('Ajustar orçamento', 7),
                                                    ('Contratar equipe técnica', 8),
                                                    ('Identificar riscos e mitigações', 9),
                                                    ('Iniciar campanhas de conscientização', 10);

INSERT INTO Reservation (start_time, end_time, day, fk_collaborator_cod_user, fk_client_cod_user) VALUES
                                                                                                      ('09:00:00', '11:00:00', '2024-11-20', 1, 1),
                                                                                                      ('13:00:00', '15:00:00', '2024-11-20', 2, 2),
                                                                                                      ('10:00:00', '12:00:00', '2024-11-21', 3, 3),
                                                                                                      ('14:00:00', '16:00:00', '2024-11-21', 4, 4),
                                                                                                      ('15:00:00', '17:00:00', '2024-11-22', 5, 5),
                                                                                                      ('08:00:00', '10:00:00', '2024-11-23', 1, 6),
                                                                                                      ('11:00:00', '13:00:00', '2024-11-23', 2, 7),
                                                                                                      ('09:30:00', '11:30:00', '2024-11-24', 3, 8),
                                                                                                      ('12:30:00', '14:30:00', '2024-11-24', 4, 9),
                                                                                                      ('14:00:00', '16:00:00', '2024-11-25', 5, 10);

INSERT INTO Report (report_date, description) VALUES
                                                  ('2024-11-01', 'Relatório inicial do projeto de infraestrutura.'),
                                                  ('2024-11-02', 'Análise preliminar de impacto ambiental.'),
                                                  ('2024-11-03', 'Resumo das reuniões com stakeholders.'),
                                                  ('2024-11-04', 'Apresentação dos resultados parciais.'),
                                                  ('2024-11-05', 'Relatório de progresso semanal.'),
                                                  ('2024-11-06', 'Revisão das metas do projeto.'),
                                                  ('2024-11-07', 'Atualização sobre a contratação de fornecedores.'),
                                                  ('2024-11-08', 'Planejamento de novas fases do projeto.'),
                                                  ('2024-11-09', 'Relatório de análise de riscos.'),
                                                  ('2024-11-10', 'Resumo final do mês.');

INSERT INTO RCollaborator_Project_Report (fk_collaborator_user, fk_project, fk_report) VALUES
                                                                                           (1, 1, 1),
                                                                                           (2, 2, 2),
                                                                                           (3, 3, 3),
                                                                                           (4, 4, 4),
                                                                                           (5, 5, 5),
                                                                                           (1, 6, 6),
                                                                                           (2, 7, 7),
                                                                                           (3, 8, 8),
                                                                                           (4, 9, 9),
                                                                                           (5, 10, 10);
