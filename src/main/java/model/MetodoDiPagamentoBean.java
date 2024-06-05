package model;

import java.io.Serializable;

public class MetodoDiPagamentoBean implements Serializable {
    private static final long serialVersionUID = 2856723757650934255L;
    
    private String email;
    private String nCartaIban;
    private String metodo;
    
    public MetodoDiPagamentoBean() {
        this.email = "";
        this.nCartaIban = "";
        this.metodo = "";
    }
    
    public MetodoDiPagamentoBean(String email, String nCartaIban, String metodo) {
        this.email = email;
        this.nCartaIban = nCartaIban;
        this.metodo = metodo;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getnCartaIban() {
        return nCartaIban;
    }

    public void setnCartaIban(String nCartaIban) {
        this.nCartaIban = nCartaIban;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}
