package model;

import java.io.Serializable;

public class CarrelloBean implements Serializable {
    private static final long serialVersionUID = 2856723757650934257L;
    
    private String email;
    private int idProdotto;
    private int quantita;
    
    public CarrelloBean() {
        this.email = "";
        this.idProdotto = 0;
        this.quantita = 0;
    }
    
    public CarrelloBean(String email, int idProdotto, int quantita) {
        this.email = email;
        this.idProdotto = idProdotto;
        this.quantita = quantita;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
