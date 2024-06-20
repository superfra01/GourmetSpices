package model;

public class ContenenteCombinedKey{
	

	private int idProdotto;
	private int idOrdine;
	
	public ContenenteCombinedKey(){
		super();
		idProdotto=0;
		idOrdine=0;
	}
	
	ContenenteCombinedKey(int idProdotto, int idOrdine){
		this.idProdotto=idProdotto;
		this.idOrdine=idOrdine;
	}
	
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
    
    
}
