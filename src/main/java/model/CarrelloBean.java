package model;

import java.io.Serializable;

public class CarrelloBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2738401824662646060L;

	
	int idCarrello;
	String email;
	
	public CarrelloBean(){
		idCarrello = 0;
		email = "";
	}
	CarrelloBean(int idCarrello, String email){
		this.idCarrello = idCarrello;
		this.email = email;
	}
	
	public void setIdCarrello(int idCarrello) {
		this.idCarrello =idCarrello;
	}
	
	public int getIdCarrello(){
		return idCarrello;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail(){
		return email;
	}
}
