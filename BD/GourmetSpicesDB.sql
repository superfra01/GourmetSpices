DROP DATABASE IF EXISTS GourmetSpicesDB;
CREATE DATABASE GourmetSpicesDB;

USE GourmetSpicesDB;

-- Elimina le tabelle se esistono gi� nell'ordine corretto per rispettare le chiavi esterne
DROP TABLE IF EXISTS Spedizione;
DROP TABLE IF EXISTS Contenente;
DROP TABLE IF EXISTS Carrello;
DROP TABLE IF EXISTS Ordine;
DROP TABLE IF EXISTS Metodo_Di_Pagamento;
DROP TABLE IF EXISTS Prodotto;
DROP TABLE IF EXISTS Utente;

-- Creazione della tabella Utente
CREATE TABLE Utente (
    email VARCHAR(50) PRIMARY KEY NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    Tipo_utente VARCHAR(16) NOT NULL DEFAULT 'USER'
);

-- Creazione della tabella Metodo_Di_Pagamento
CREATE TABLE Metodo_Di_Pagamento (
    email VARCHAR(50) NOT NULL,
    N_carta_iban VARCHAR(50) PRIMARY KEY NOT NULL,
    metodo VARCHAR(50) NOT NULL,
    FOREIGN KEY (email) REFERENCES Utente(email)
);

-- Creazione della tabella Prodotto
CREATE TABLE Prodotto (
    ID_prodotto INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    prezzo INT NOT NULL,
    quantita_magazzino INT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    descrizione VARCHAR(50) NOT NULL
);

-- Creazione della tabella Carrello
CREATE TABLE Carrello (
    email VARCHAR(50) NOT NULL,
    ID_prodotto INT NOT NULL,
    quantita INT NOT NULL,
    FOREIGN KEY (email) REFERENCES Utente(email),
    FOREIGN KEY (ID_prodotto) REFERENCES Prodotto(ID_prodotto),
    PRIMARY KEY(email, ID_prodotto)
);

-- Creazione della tabella Ordine
CREATE TABLE Ordine (
    ID_ordine INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    N_carta_iban VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    data DATE NOT NULL,
    spesa FLOAT NOT NULL,
    indirizzo VARCHAR(50) NOT NULL,
    FOREIGN KEY (email) REFERENCES Utente(email),
    FOREIGN KEY (N_carta_iban) REFERENCES Metodo_Di_Pagamento(N_carta_iban)
);

-- Creazione della tabella Spedizione
CREATE TABLE Spedizione (
    ID_ordine INT NOT NULL,
    N_spedizione VARCHAR(50) PRIMARY KEY NOT NULL,
    G_di_arrivo DATE,
    corriere VARCHAR(50),
    FOREIGN KEY (ID_ordine) REFERENCES Ordine(ID_ordine)
);

-- Creazione della tabella Contenente
CREATE TABLE Contenente (
    ID_prodotto INT NOT NULL,
    ID_ordine INT NOT NULL,
    prezzo_all_acquisto FLOAT NOT NULL,
    quantita INT NOT NULL,
    PRIMARY KEY (ID_prodotto, ID_ordine),
    FOREIGN KEY (ID_prodotto) REFERENCES Prodotto(ID_prodotto),
    FOREIGN KEY (ID_ordine) REFERENCES Ordine(ID_ordine)
);

-- Inserimento degli utenti
INSERT INTO Utente (email, username, password, nome, cognome, Tipo_utente)
VALUES
('giulia.fiori@email.com', 'giuliaFiori', 'passwordSicura123', 'Giulia', 'Fiori', 'USER'),
('antonio.verdi@email.com', 'antonioVerdi', 'passwordSicura123', 'Antonio', 'Verdi', 'USER'),
('sofia.viola@email.com', 'sofiaViola', 'passwordSicura123', 'Sofia', 'Viola', 'USER'),
('marco.azzurri@email.com', 'marcoAzzurri', 'passwordSicura123', 'Marco', 'Azzurri', 'USER'),
('laura.gialli@email.com', 'lauraGialli', 'passwordSicura123', 'Laura', 'Gialli', 'USER'),
('francesco.rossi@email.com', 'francescoRossi', 'passwordSicura123', 'Francesco', 'Rossi', 'USER');

-- Inserimento dei prodotti che sono spezie
INSERT INTO Prodotto (prezzo, quantita_magazzino, nome, descrizione)
VALUES
(3, 100, 'Pepe Nero', 'Pepe nero in grani'),
(6, 30, 'Zenzero', 'Zenzero in polvere'),
(8, 40, 'Cannella', 'Cannella in stecche'),
(9, 25, 'Chiodi di Garofano', 'Chiodi di garofano interi'),
(4, 60, 'Noce Moscata', 'Noce moscata in polvere'),
(5, 70, 'Cumino', 'Cumino in semi');

-- Inserimento dei metodi di pagamento
INSERT INTO Metodo_Di_Pagamento (email, N_carta_iban, metodo)
VALUES
('giulia.fiori@email.com', 'IT60X0542811101000000123456', 'Carta di Credito'),
('antonio.verdi@email.com', 'IT30Y0542811101000000654321', 'PayPal');

-- Inserimento degli ordini
INSERT INTO Ordine (N_carta_iban, email, data, spesa, indirizzo)
VALUES
('IT60X0542811101000000123456', 'giulia.fiori@email.com', '2024-06-05', 10.00, 'Via Roma 1'),
('IT30Y0542811101000000654321', 'antonio.verdi@email.com', '2024-06-05', 7.00, 'Via Milano 2');

-- Inserimento delle spedizioni
INSERT INTO Spedizione (ID_ordine, N_spedizione, G_di_arrivo, corriere)
VALUES
(1, 'SPED001', '2024-06-07', 'GLS'),
(2, 'SPED002', '2024-06-08', 'DHL');

-- Contenente
INSERT INTO Contenente (ID_prodotto, ID_ordine, prezzo_all_acquisto, quantita)
VALUES
(1, 1, 3.00, 2),
(2, 2, 6.00, 1),
(3, 2, 8.00, 1),
(4, 1, 9.00, 2),
(5, 1, 4.00, 3);

-- Carrello
INSERT INTO Carrello (email, ID_prodotto, quantita)
VALUES
('giulia.fiori@email.com', 1, 2),
('giulia.fiori@email.com', 2, 1),
('giulia.fiori@email.com', 3, 1),
('antonio.verdi@email.com', 4, 2),
('antonio.verdi@email.com', 5, 3),
('antonio.verdi@email.com', 6, 1);
