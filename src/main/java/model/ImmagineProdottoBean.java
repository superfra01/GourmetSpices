package model;

import java.io.Serializable;

public class ImmagineProdottoBean implements Serializable {
    private static final long serialVersionUID = 123456789L;
    
    private int idProdotto;
    private int idImmagine;
    private String immagine;
    
    public ImmagineProdottoBean() {
        this.idProdotto = 0;
        this.idImmagine = 0;
        this.immagine = null;
    }
    
    public ImmagineProdottoBean(int idProdotto, int idImmagine, String immagine) {
        this.idProdotto = idProdotto;
        this.idImmagine = idImmagine;
        this.immagine = immagine;
    }
    
    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public int getIdImmagine() {
        return idImmagine;
    }

    public void setIdImmagine(int idImmagine) {
        this.idImmagine = idImmagine;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine_url) {
        this.immagine = immagine_url;
    }
}
