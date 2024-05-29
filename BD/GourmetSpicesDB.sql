DROP DATABASE IF EXISTS GourmetSpicesDB;
CREATE DATABASE GourmetSpicesDB;

USE GourmetSpicesDB;

DROP TABLE IF EXISTS Utente;
CREATE TABLE Utente (
    ID_utente INT PRIMARY KEY NOT NULL,
    email VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(5000) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    Tipo_utente VARCHAR(16) NOT NULL DEFAULT 'USER'
);

DROP TABLE IF EXISTS Carrello;
CREATE TABLE Carrello (
    ID_utente INT PRIMARY KEY NOT NULL,
    FOREIGN KEY (ID_utente) REFERENCES Utente(ID_utente)
);

DROP TABLE IF EXISTS Metodo_Di_Pagamento;
CREATE TABLE Metodo_Di_Pagamento (
    ID_utente INT NOT NULL,
    N_carta_iban VARCHAR(50) PRIMARY KEY NOT NULL,
    metodo VARCHAR(50) NOT NULL,
    FOREIGN KEY (ID_utente) REFERENCES Utente(ID_utente)
);

DROP TABLE IF EXISTS Prodotto;
CREATE TABLE Prodotto (
    ID_prodotto INT PRIMARY KEY NOT NULL,
    prezzo INT NOT NULL,
    quantità_magazzino INT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    descrizione VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS Ordine;
CREATE TABLE Ordine (
    N_carta_iban VARCHAR(50) NOT NULL,
    ID_utente INT NOT NULL,
    ID_ordine INT PRIMARY KEY NOT NULL,
    data DATE NOT NULL,
    spesa FLOAT NOT NULL,
    FOREIGN KEY (ID_utente) REFERENCES Utente(ID_utente),
    FOREIGN KEY (N_carta_iban) REFERENCES Metodo_Di_Pagamento(N_carta_iban)
);

DROP TABLE IF EXISTS Spedizione;
CREATE TABLE Spedizione (
    ID_ordine INT NOT NULL,
    N_spedizione INT PRIMARY KEY NOT NULL,
    G_di_arrivo DATE,
    indirizzo VARCHAR(50) NOT NULL,
    corriere VARCHAR(50),
    FOREIGN KEY (ID_ordine) REFERENCES Ordine(ID_ordine)
);

DROP TABLE IF EXISTS Contenente;
CREATE TABLE Contenente (
    ID_prodotto INT NOT NULL,
    ID_ordine INT NOT NULL,
    prezzo_all_acquisto FLOAT NOT NULL,
    quantità INT NOT NULL,
    PRIMARY KEY (ID_prodotto, ID_ordine),
    FOREIGN KEY (ID_prodotto) REFERENCES Prodotto(ID_prodotto),
    FOREIGN KEY (ID_ordine) REFERENCES Ordine(ID_ordine)
);
