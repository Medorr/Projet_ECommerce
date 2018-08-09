package fr.adaming.Service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

@Local
public interface ICategorieService {


	public List<Categorie> getAllCategorie();
	
	public Categorie getCategorieById(Categorie cat);
	
	public Categorie ajouterCategorie(Categorie cat);
	
	public int modifCategorie(Categorie cat);
	
	public int supprCategorie(Categorie cat);
	
	public Categorie rechCategorie(Categorie cat);
}
