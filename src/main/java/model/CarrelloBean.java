package model;

import java.io.Serializable;

public class CarrelloBean implements Serializable {
    private static final long serialVersionUID = 2856723757650934257L;
    
    private int idUtente;
    private int idProdotto;
    private int quantita;
    
    public CarrelloBean() {
        this.idUtente = -1;//non specificato
        this.idProdotto = -1;//non specificato
        this.quantita = 0;
    }
    
    public CarrelloBean(int idUtente, int idProdotto, int quantita) {
        this.idUtente = idUtente;
        this.idProdotto = idProdotto;
        this.quantita = quantita;
    }

    // Getters and Setters
    public int getIdUtente() {
        return idUtente;
    }

    public void setEmail(int idUtente) {
        this.idUtente = idUtente;
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
