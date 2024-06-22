package model;

import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;

public class MetodoDiPagamentoBean implements Serializable {
    private static final long serialVersionUID = 2856723757650934255L;
    
    private String email;
    private String NCarta;
    private String CVV;
    private Date data;
    
    public MetodoDiPagamentoBean() {
        this.email = "";
        this.NCarta = "";
        this.CVV = "";
    }
    
    public MetodoDiPagamentoBean(String email, String nCartaIban, String metodo, Date data) {
        this.email = email;
        this.NCarta = nCartaIban;
        this.CVV = metodo;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNCarta() {
        return NCarta;
    }

    public void setNCarta(String NCarta) {
        this.NCarta = NCarta;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
