package fr.adaming.Service;

import javax.ejb.Local;

import fr.adaming.model.Client;
@Local
public interface IClientService {
	
	/**
	 *  Methode pour enregistrer le client
	 */
	public Client enregistrerClient(Client cl);
	
	/** 
	 * Methode pour modifier le client
	 */
	public Client modifClient(Client cl);
	
	/**
	* Methode pour supprimer le client 
	 */
	public Client supprClient(Client cl);

}
