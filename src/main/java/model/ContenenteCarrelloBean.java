package model;

import java.io.Serializable;

public class ContenenteCarrelloBean implements Serializable {
    private static final long serialVersionUID = 2856723757650934257L;
    
    private int idCarrello;
    private int idProdotto;
    private int quantita;
    
    public ContenenteCarrelloBean() {
        this.idCarrello = -1;//non specificato
        this.idProdotto = -1;//non specificato
        this.quantita = 0;
    }
    
    public ContenenteCarrelloBean(int idCarrello, int idProdotto, int quantita) {
        this.idCarrello = idCarrello;
        this.idProdotto = idProdotto;
        this.quantita = quantita;
    }

    // Getters and Setters
    public int getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(int idCarrello) {
        this.idCarrello = idCarrello;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
