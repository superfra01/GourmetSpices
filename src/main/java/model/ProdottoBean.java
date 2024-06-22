package model;

import java.io.Serializable;

public class ProdottoBean implements Serializable {
    private static final long serialVersionUID = 2856723757650934256L;
    
    private int idProdotto;
    private float prezzo;
    private int quantitaMagazzino;
    private int valido;
    private String nome;
    private String descrizione;
    
    public ProdottoBean() {
        this.idProdotto = -1;//id non specificato
        this.prezzo = 0;
        this.quantitaMagazzino = 0;
        this.valido = 1;
        this.nome = "";
        this.descrizione = "";
    }
    
    public ProdottoBean(int idProdotto, float prezzo, int quantitaMagazzino, int valido, String nome, String descrizione) {
        this.idProdotto = idProdotto;
        this.prezzo = prezzo;
        this.quantitaMagazzino = quantitaMagazzino;
        this.valido = valido;
        this.nome = nome;
        this.descrizione = descrizione;
    }

    // Getters and Setters
    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantitaMagazzino() {
        return quantitaMagazzino;
    }

    public void setQuantitaMagazzino(int quantitaMagazzino) {
        this.quantitaMagazzino = quantitaMagazzino;
    }
    
    public int getValidoProdotto() {
        return valido;
    }

    public void setValidoProdotto(int validoProdotto) {
        this.valido = validoProdotto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
