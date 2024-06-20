package model;

import java.io.Serializable;

public class ImmagineProdottoBean implements Serializable {
    private static final long serialVersionUID = 123456789L;
    
    private int idProdotto;
    private int idImmagine;
    private byte[] immagine;
    
    public ImmagineProdottoBean() {
        this.idProdotto = 0;
        this.idImmagine = 0;
        this.immagine = null;
    }
    
    public ImmagineProdottoBean(int idProdotto, int idImmagine, byte[] immagine) {
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

    public byte[] getImmagine() {
        return immagine;
    }

    public void setImmagine(byte[] immagine) {
        this.immagine = immagine;
    }
}
