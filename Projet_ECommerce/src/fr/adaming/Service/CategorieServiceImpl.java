package fr.adaming.Service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

@Stateful
public class CategorieServiceImpl implements ICategorieService{
	/**Transformation de l'asso UML en JAVA et injection dependance*/
	@EJB
	private ICategorieDao catDao;

	/**Methode service pour afficher la liste des categories*/
	@Override
	public List<Categorie> getAllCategorie() {
		// TODO Auto-generated method stub
		return catDao.getAllCategorie();
	}

	/**Methode service pour ajouter des categories*/
	@Override
	public Categorie ajouterCategorie(Categorie cat) {
		// TODO Auto-generated method stub
		return catDao.ajouterCategorie(cat);
	}

	/**Methode service pour modifier des categories*/
	@Override
	public int modifCategorie(Categorie cat) {
		// TODO Auto-generated method stub
		return catDao.modifCategorie(cat);
	}

	/**Methode service pour supprimer des categories*/
	@Override
	public int supprCategorie(Categorie cat) {
		// TODO Auto-generated method stub
		return catDao.supprCategorie(cat);
	}

	/**Methode service pour recherhcher des categories par nom*/
	@Override
	public Categorie rechCategorie(Categorie cat) {
		// 
		return catDao.rechCategorie(cat);
	}

	/**Methode service pour rechercher des categories par id*/
	@Override
	public Categorie getCategorieById(Categorie cat) {
		// instancier une categorie
		Categorie catBD=catDao.getCategorieById(cat);
		//if(catBD!=null){
			return catBD;
		//}else{
		//return null;
		//}
	}

	/**Methode service pour rechercher des categories id ou nom*/
	@Override
	public Categorie getCategorieByNomOrId(Categorie cat) {
		// TODO Auto-generated method stub
		return catDao.getCategorieByNomOrId(cat);
	}
	

}
