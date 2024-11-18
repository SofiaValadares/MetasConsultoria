CREATE DATABASE IF NOT EXISTS DatabaseMetas;
USE DatabaseMetas;

CREATE TABLE User (
                      cod_user 		INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      name 			VARCHAR(40) NOT NULL,
                      email 		VARCHAR(31) NOT NULL UNIQUE,
                      password 		VARCHAR(16) NOT NULL,
                      create_date   DATE DEFAULT  CURRENT_DATE
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
                        fk_city         INTEGER NOT NULL,

                        PRIMARY KEY (cod_user),
                        CONSTRAINT client_fk_user
                            FOREIGN KEY (cod_user) REFERENCES User(cod_user),

                        CONSTRAINT client_fk_city
                            FOREIGN KEY (fk_city) REFERENCES City(cod_city)
);

CREATE TABLE Project (
                         cod_project        INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         name               VARCHAR(255) NOT NULL,
                         description        VARCHAR(1500),
                         public             BIT DEFAULT 0,
                         date               DATE,

                         fk_city            INTEGER NOT NULL,

                         CONSTRAINT project_fk_city
                             FOREIGN KEY (fk_city) REFERENCES City(cod_city)
);

CREATE TABLE R_Collaborator_Client_Project (
                                                fk_collaborator     INTEGER NOT NULL,
                                                fk_client           INTEGER NOT NULL,
                                                fk_project          INTEGER NOT NULL,

                                                PRIMARY KEY (fk_collaborator, fk_client, fk_project),

                                                CONSTRAINT r_collaborator_client_project_fk_collaborator
                                                    FOREIGN KEY (fk_collaborator) REFERENCES Collaborator(cod_user),

                                                CONSTRAINT r_collaborator_client_project_fk_client
                                                    FOREIGN KEY (fk_client) REFERENCES Client(cod_user),

                                                CONSTRAINT r_collaborator_client_project_fk_project
                                                    FOREIGN KEY (fk_project) REFERENCES Project(cod_project)
);

CREATE TABLE Next_Steps (
    next_steps_pk           INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    next_steps              VARCHAR(255) NOT NULL,
    fk_project INTEGER NOT NULL,

    CONSTRAINT next_steps_fk_project
        FOREIGN KEY (fk_project) REFERENCES Project(cod_project)
);

CREATE TABLE Reservation (
    reservation_id             INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    start_time                 TIME NOT NULL,
    end_time                   TIME NOT NULL,
    day                        DATE NOT NULL,

    fk_collaborator_cod_user    INTEGER NOT NULL,
    fk_client_cod_user        INTEGER,

    CONSTRAINT fk_reservation_collaborator
        FOREIGN KEY (fk_collaborator_cod_user) REFERENCES Collaborator(cod_user),
    
    CONSTRAINT fk_reservation_client
        FOREIGN KEY (fk_client_cod_user) REFERENCES Client(cod_user)
);

CREATE TABLE Report (
    cod_report      INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    report_date     DATE NOT NULL,
    description     VARCHAR(255) NOT NULL
);


CREATE TABLE RCollaborator_Project_Report (
    fk_collaborator_user    INTEGER NOT NULL,
    fk_project              INTEGER NOT NULL,
    fk_report         INTEGER NOT NULL,

    PRIMARY KEY (fk_collaborator_user, fk_project, fk_report),

    CONSTRAINT fk_cpr_collaborator
        FOREIGN KEY (fk_collaborator_user) REFERENCES Collaborator(cod_user),
    
    CONSTRAINT fk_cpr_project
        FOREIGN KEY (fk_project) REFERENCES Project(cod_project),
    
    CONSTRAINT fk_cpr_report
        FOREIGN KEY (fk_report) REFERENCES Report(cod_report)
);

CREATE TRIGGER password_min
    AFTER UPDATE
    ON User
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

INSERT INTO Project (name, description, public, fk_city) VALUES
('Projeto de Infraestrutura Ferroviária', 'Construção de uma nova linha ferroviária para transporte de carga.', 0, 1),
('Plano de Despoluição de Rios', 'Desenvolvimento de medidas para a limpeza de rios poluídos.', 0, 2),
('Iniciativa de Segurança Rodoviária', 'Melhorias em sinalização e monitoramento de vias para reduzir acidentes.', 0, 3),
('Projeto de Digitalização de Documentos Públicos', 'Conversão de arquivos físicos em digitais para acesso remoto.', 0, 4),
('Programa de Modernização Portuária', 'Expansão e modernização de portos para aumento da capacidade.', 0, 5),
('Desenvolvimento de Ferramentas de Governança', 'Criação de software para gerenciamento eficiente de recursos públicos.', 0, 6),
('Estudo de Impacto Ambiental para Construção', 'Análises detalhadas para minimizar o impacto ambiental.', 0, 7),
('Reforma de Políticas de Subsídio Habitacional', 'Avaliação e reformulação de programas de subsídio habitacional.', 0, 8),
('Centro de Estudos Urbanos', 'Criação de um centro de pesquisa para desenvolvimento urbano.', 0, 9),
('Plano de Eficiência Hídrica', 'Instalação de sistemas de coleta de água de chuva e economia hídrica.', 0, 10),
('Projeto de Expansão de Aeroportos', 'Ampliação de pistas e terminais em aeroportos estratégicos.', 0, 1),
('Iniciativa de Integração Regional', 'Desenvolvimento de corredores de transporte entre cidades próximas.', 0, 2),
('Plano de Controle de Poluição Sonora', 'Implementação de medidas para reduzir ruídos urbanos.', 0, 3),
('Desenvolvimento de Zonas Francas', 'Criação de áreas livres de impostos para estímulo econômico.', 0, 4),
('Reestruturação de Hospitais Públicos', 'Melhoria na infraestrutura e equipamentos médicos.', 0, 5),
('Projeto de Renovação da Malha Viária', 'Reforma de estradas e avenidas principais.', 0, 6),
('Projeto de Modernização de Sistemas de Iluminação', 'Substituição de sistemas antigos por tecnologia LED.', 0, 7),
('Plano de Expansão de Ensino Superior', 'Construção de novas universidades e campus.', 0, 8),
('Desenvolvimento de Centros de Pesquisa Científica', 'Criação de novos centros de inovação e pesquisa.', 0, 9),
('Projeto de Reurbanização de Bairros', 'Revitalização de áreas residenciais com novos serviços.', 0, 10),
('Plano de Melhoria de Redes de Saneamento', 'Expansão de sistemas de esgoto para áreas não atendidas.', 0, 1),
('Iniciativa de Conectividade Rural', 'Instalação de redes de internet em áreas rurais.', 0, 2),
('Projeto de Fortalecimento de Comunidades', 'Criação de programas de apoio a comunidades locais.', 0, 3),
('Projeto de Gerenciamento de Crises Urbanas', 'Planejamento de estratégias de resposta rápida para desastres.', 0, 4),
('Desenvolvimento de Zona de Proteção Ambiental', 'Criação de novas áreas de preservação natural.', 0, 5),
('Plano de Segurança Digital para Órgãos Públicos', 'Implementação de medidas de cibersegurança.', 0, 6),
('Projeto de Ampliação de Redes de Transporte Subterrâneo', 'Construção de novas linhas de metrô.', 0, 7),
('Plano de Conservação de Estruturas Históricas', 'Preservação e restauração de patrimônios culturais.', 0, 8),
('Projeto de Requalificação de Áreas Industriais', 'Transformação de antigas zonas industriais em espaços modernos.', 0, 9),
('Plano de Incentivo à Indústria Sustentável', 'Criação de políticas para atrair indústrias verdes.', 0, 10),
('Programa de Educação Inclusiva', 'Criação de escolas e programas adaptados para necessidades especiais.', 1, 1),
('Projeto de Acessibilidade em Espaços Públicos', 'Melhorias em rampas, elevadores e sinalizações.', 1, 2),
('Plano de Promoção de Esportes Amadores', 'Incentivo a práticas esportivas em bairros.', 1, 3),
('Projeto de Hortas Comunitárias', 'Criação de hortas em espaços urbanos para a comunidade.', 1, 4),
('Campanha de Conscientização sobre Reciclagem', 'Distribuição de materiais educativos sobre reciclagem.', 1, 5),
('Projeto de Preservação de Fauna e Flora', 'Programas de proteção a espécies nativas.', 1, 1),
('Aprimoramento de Serviços de Transporte', 'Melhoria na qualidade e frequência de transporte público.', 1, 2),
('Projeto de Segurança em Comunidades', 'Patrulhas comunitárias e instalação de câmeras de segurança.', 1, 3),
('Campanha de Prevenção a Doenças', 'Distribuição de materiais educativos sobre higiene e saúde.', 1, 4),
('Projeto de Sustentabilidade Energética', 'Uso de fontes de energia renovável em espaços públicos.', 1, 5);

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
