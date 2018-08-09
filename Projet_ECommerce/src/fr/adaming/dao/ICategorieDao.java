package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;

@Local //**On utilise local pour que l'appli soit accecible dans le serveur a chaque utilisation*/
public interface ICategorieDao {
	
	public List<Categorie> getAllCategorie();
	

}
