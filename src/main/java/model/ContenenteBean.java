package model;

import java.io.Serializable;

public class ContenenteBean implements Serializable {
    private static final long serialVersionUID = 2856723757650934260L;
    
    private int idProdotto;
    private int idOrdine;
    private float prezzoAllAcquisto;
    private int quantita;
    
    public ContenenteBean() {
        this.idProdotto = 0;
        this.idOrdine = 0;
        this.prezzoAllAcquisto = 0.0f;
        this.quantita = 0;
    }
    
    public ContenenteBean(int idProdotto, int idOrdine, float prezzoAllAcquisto, int quantita) {
        this.idProdotto = idProdotto;
        this.idOrdine = idOrdine;
        this.prezzoAllAcquisto = prezzoAllAcquisto;
        this.quantita = quantita;
    }

    // Getters and Setters
    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public float getPrezzoAllAcquisto() {
        return prezzoAllAcquisto;
    }

    public void setPrezzoAllAcquisto(float prezzoAllAcquisto) {
        this.prezzoAllAcquisto = prezzoAllAcquisto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
   
