package fr.adaming.dao;



import fr.adaming.model.Client;

public interface IClientDao {
	
	/**
	 *  Methode pour enregistrer le client
	 */
	public Client enregistrerClientDao(Client cl);
	
	/** 
	 * Methode pour modifier le client
	 */
	public Client modifClientDao(Client cl);
	
	/**
	* Methode pour supprimer le client 
	 */
	public Client supprClientDao(Client cl);
}
