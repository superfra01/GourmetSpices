package model;

public class ContenenteCarrelloCombinedKey{
	

	private int idCarrello;
    private int idProdotto;
	
	public ContenenteCarrelloCombinedKey(){
		super();
		idCarrello=0;
		idProdotto=0;
		
	}
	
	public ContenenteCarrelloCombinedKey(int idProdotto, int idCarrello){
		this.idProdotto=idProdotto;
		this.idCarrello=idCarrello;
	}
	
	public int getIdProdotto() {
       return idProdotto;
  	}
	 

	public void setIdProdotto(int idProdotto) {
	    this.idProdotto = idProdotto;
	}
	
	public int getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(int idCarrello) {
        this.idCarrello = idCarrello;
    }
    
    
}
