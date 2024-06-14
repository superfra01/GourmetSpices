package model;

import java.io.Serializable;

public class UserBean implements Serializable {
	private static final long serialVersionUID = 2856723757650934254L;
	
	
	private int idUtente;
    private String email;
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String tipoUtente;
    
    public UserBean() {
    	this.idUtente = -1;// id non specificato
        this.email = "";
        this.username = "";
        this.password = "";
        this.nome = "";
        this.cognome = "";
        this.tipoUtente = "";
    }
    
    public UserBean(int idUtente, String email, String username, String password, String nome, String cognome, String tipoUtente) {
        this.idUtente = idUtente;
        this.email = email;
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.tipoUtente = tipoUtente;
    }
	
    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
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

