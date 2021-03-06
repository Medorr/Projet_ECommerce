package fr.adaming.Service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Local
public interface IProduitService {
	
	public int ajoutProduit(Produit pr);
	public int modifProduit(Produit pr);
	public int supprProduit(Produit pr);
	public Produit rechProduit(Produit pr);
	public List<Produit> getAllProduit();

	public List<Produit> getProduitByIdCat(Categorie cat);

}
