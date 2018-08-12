package fr.adaming.Service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.managedBeans.PanierManagedBean;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
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
	
	/**
	* Methode liste des clients 
	 */
	public List<Client> getAllClient();
	
	/**
	* Methode rechercher un client par id
	 */
	public Client getClientById(Client cl);
	
	/**
	* Methode rechercher les clients par nom 
	 */
	public Client getClientByNom(Client cl);
	
	/**
	* Methode rechercher les clients par nom  ou id
	 */
	public List<Client> getClientByNomOrId(Client cl);
	
	/**
	* Methode envoyer un mail au client
	 */

	void sendMail(Client cl, LigneCommande lc);
}
