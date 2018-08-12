package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Commande;

@Local
public interface ICommandeDao {
	
	public int ajoutCommande(Commande com);
	public int modifCommande(Commande com);
	public int supprCommande(Commande com);
	public List<Commande> getAllCommande();
	public Commande rechCommande(Commande com);

}
