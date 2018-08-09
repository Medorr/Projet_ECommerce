package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Produit;

@Local
public interface IProduitDao {
	
	
	public int ajoutProduit(Produit pr);
	public int modifProduit(Produit pr);
	public int supprProduit(Produit pr);
	public Produit rechProduit(Produit pr);
	public List<Produit> getAllProduit();
	

}
