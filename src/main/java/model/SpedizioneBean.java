package model;

import java.io.Serializable;
import java.util.Date;

public class SpedizioneBean implements Serializable {
    private static final long serialVersionUID = 2856723757650934259L;
    
    private int idOrdine;
    private String nSpedizione;
    private Date gDiArrivo;
    private String corriere;
    
    public SpedizioneBean() {
        this.idOrdine = 0;
        this.nSpedizione = "";
        this.gDiArrivo = new Date();
        this.corriere = "";
    }
    
    public SpedizioneBean(int idOrdine, String nSpedizione, Date gDiArrivo, String corriere) {
        this.idOrdine = idOrdine;
        this.nSpedizione = nSpedizione;
        this.gDiArrivo = gDiArrivo;
        this.corriere = corriere;
    }

    // Getters and Setters
    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public String getnSpedizione() {
        return nSpedizione;
    }

    public void setnSpedizione(String nSpedizione) {
        this.nSpedizione = nSpedizione;
    }

    public Date getgDiArrivo() {
        return gDiArrivo;
    }

    public void setgDiArrivo(Date gDiArrivo) {
        this.gDiArrivo = gDiArrivo;
    }

    public String getCorriere() {
        return corriere;
    }

    public void setCorriere(String corriere) {
        this.corriere = corriere;
    }
}
