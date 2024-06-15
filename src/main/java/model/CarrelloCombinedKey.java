package model;

public class CarrelloCombinedKey{
	

	private int idUtente;
    private int idProdotto;
	
	CarrelloCombinedKey(){
		super();
		idUtente=0;
		idProdotto=0;
		
	}
	
	CarrelloCombinedKey(int idProdotto, int idUtente){
		this.idProdotto=idProdotto;
		this.idUtente=idUtente;
	}
	
	public int getIdProdotto() {
       return idProdotto;
  	}
	 

	public void setIdProdotto(int idProdotto) {
	    this.idProdotto = idProdotto;
	}
	
	public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idOrdine) {
        this.idUtente = idOrdine;
    }
    
    
}
