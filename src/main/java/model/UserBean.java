package model;

import java.io.Serializable;

public class UserBean implements Serializable {
	private static final long serialVersionUID = 2856723757650934254L;
	
	
    private String email;
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String tipoUtente;
    
    public UserBean() {
        this.email = "";
        this.username = "";
        this.password = "";
        this.nome = "";
        this.cognome = "";
        this.tipoUtente = "";
    }
    
    public UserBean(String email, String username, String password, String nome, String cognome, String tipoUtente) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.tipoUtente = tipoUtente;
    }
	

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTipoUtente() {
        return tipoUtente;
    }

    public void setTipoUtente(String tipoUtente) {
        this.tipoUtente = tipoUtente;
    }

	
}

