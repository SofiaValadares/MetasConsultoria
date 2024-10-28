CREATE DATABASE DB;
use DB;

CREATE TABLE User (
                      cod_user 		INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      name 			VARCHAR(40) NOT NULL,
                      email 			VARCHAR(15) NOT NULL UNIQUE,
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


CREATE TABLE Client (
                        cod_user        INT NOT NULL,

                        PRIMARY KEY (cod_user),
                        CONSTRAINT client_pk_user
                            FOREIGN KEY (cod_user) REFERENCES User(cod_user)
);

CREATE TABLE Municipio (
                        cod_municipio       INT NOT NULL AUTO_INCREMENT PRIMARY KEY,       
                        nome VARCHAR(100)       NOT NULL,          
                        estado VARCHAR(100)     NOT NULL         
);


CREATE TABLE Projeto (
                        cod_projeto         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,                    
                        nome VARCHAR(255)       NOT NULL,                     
                        descricao               TEXT,                                 
                        publico                 BOOLEAN,                                
                        fk_prox_passos_prox_pa      INT,                     
                        CONSTRAINT fk_prox_passos       FOREIGN KEY (fk_prox_passos_prox_pa)
                            REFERENCES prox_passos (prox_passos_PK)     
);