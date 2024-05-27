DROP database IF EXISTS GourmetSpice;
CREATE database GourmetSpices;

USE GODB;

DROP TABLE IF EXISTS Utente;
CREATE TABLE Utente
(	
	email varchar(50) NOT NULL,
	username varchar(50) PRIMARY KEY NOT NULL,
    password varchar(5000) NOT NULL,
	nome varchar(50) NOT NULL,
    cognome varchar(50) NOT NULL,
    Tipo-utente varchar(16) NOT NULL DEFAULT 'registeredUser'
);


DROP TABLE IF EXISTS Prodotto;
CREATE TABLE Prodotto
(
	codice int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome varchar(50) NOT NULL,
    descrizione text NOT NULL,
    deleted BOOL NOT NULL DEFAULT false,
    prezzo double(10,2) NOT NULL,
    model varchar(200) NOT NULL,
    speseSpedizione double(5,2) DEFAULT 0,
    emailVenditore varchar(50) NOT NULL,
    tag ENUM('Manga/Anime', 'Film/Serie TV', 'Videogiochi', 'Originali') NOT NULL,
    nomeTipologia ENUM('Arredamento Casa','Action Figures','Gadget') NOT NULL,
    dataAnnuncio date NOT NULL,
    FOREIGN KEY(emailVenditore) REFERENCES Venditore(email) ON UPDATE cascade ON DELETE cascade,
    FOREIGN KEY(nomeTipologia) REFERENCES Tipologia(nome) ON UPDATE cascade ON DELETE cascade
)ENGINE=InnoDB AUTO_INCREMENT=1000;

DROP TABLE IF EXISTS Ordine;
CREATE TABLE Ordine
(
	codiceOrdine int NOT NULL AUTO_INCREMENT,
    codiceProdotto int NOT NULL,
    emailCliente varchar(50) NOT NULL,
    prezzoTotale double(10,2) NOT NULL,
    quantity int NOT NULL,
    dataAcquisto date NOT NULL,
    PRIMARY KEY(codiceOrdine,codiceProdotto),
    FOREIGN KEY(codiceProdotto) REFERENCES Prodotto(codice) ON UPDATE cascade ON DELETE cascade,
    FOREIGN KEY(emailCliente) REFERENCES Cliente(email) ON UPDATE cascade ON DELETE cascade
)ENGINE=InnoDB AUTO_INCREMENT=100;

DROP TABLE IF EXISTS Recensione;
CREATE TABLE Recensione
(
	codiceRecensione int NOT NULL AUTO_INCREMENT,
    codiceProdotto int NOT NULL,
    emailCliente varchar(50) NOT NULL,
    votazione tinyint unsigned NOT NULL,
    testo text,
    dataRecensione date NOT NULL,
    PRIMARY KEY(codiceRecensione,codiceProdotto),
    FOREIGN KEY(codiceProdotto) REFERENCES Prodotto(codice) ON UPDATE cascade ON DELETE cascade,
    FOREIGN KEY(emailCliente) REFERENCES Cliente(email) ON UPDATE cascade ON DELETE cascade
);

DROP TABLE IF EXISTS Preferiti;
CREATE TABLE Preferiti
(
	codiceProdotto int NOT NULL,
    emailCliente varchar(50) NOT NULL,
    PRIMARY KEY(codiceProdotto,emailCliente),
    FOREIGN KEY(codiceProdotto) REFERENCES Prodotto(codice) ON UPDATE cascade ON DELETE cascade,
    FOREIGN KEY(emailCliente) REFERENCES Cliente(email) ON UPDATE cascade ON DELETE cascade
);

USE GeekFactoryDB;