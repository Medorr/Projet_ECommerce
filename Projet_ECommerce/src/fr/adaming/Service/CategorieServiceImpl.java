package fr.adaming.Service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

@Stateful
public class CategorieServiceImpl implements ICategorieService{
	//Transformation de l'asso UML en JAV
	@EJB
	private ICategorieDao catDao;

	@Override
	public List<Categorie> getAllCategorie() {
		// TODO Auto-generated method stub
		return catDao.getAllCategorie();
	}

	@Override
	public Categorie ajouterCategorie(Categorie cat) {
		// TODO Auto-generated method stub
		return catDao.ajouterCategorie(cat);
	}

	@Override
	public int modifCategorie(Categorie cat) {
		// TODO Auto-generated method stub
		return catDao.modifCategorie(cat);
	}

	@Override
	public int supprCategorie(Categorie cat) {
		// TODO Auto-generated method stub
		return catDao.supprCategorie(cat);
	}

	@Override
	public Categorie rechCategorie(Categorie cat) {
		// 
		return catDao.rechCategorie(cat);
	}

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

	@Override
	public Categorie getCategorieByNomOrId(Categorie cat) {
		// TODO Auto-generated method stub
		return catDao.getCategorieByNomOrId(cat);
	}
	

}
