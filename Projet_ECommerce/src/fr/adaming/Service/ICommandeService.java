package fr.adaming.Service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Commande;
@Local
public interface ICommandeService {
	
	/**
	 *  Methode pour enregistrer le client
	 */
	public int ajouterCommande(Commande com);
	
	/** 
	 * Methode pour modifier le client
	 */
	public int modifCommande(Commande com);
	
	/**
	* Methode pour supprimer le client 
	 */
	public int supprCommande(Commande com);
	
	/**
	* Methode liste des clients 
	 */
	public List<Commande> getAllCommande();
	
	/**
	* Methode rechercher un client par id
	 */
	public Commande getCommandeById(Commande com);
	
}
