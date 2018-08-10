package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.LigneCommande;

@Local
public interface ILigneCommande {
	
	public int ajoutLigneCommande(LigneCommande lc);
	public int modifLigneCommande(LigneCommande lc);
	public int supprLigneCommande(LigneCommande lc);
	public List<LigneCommande> getAllLigneCommande();

}
