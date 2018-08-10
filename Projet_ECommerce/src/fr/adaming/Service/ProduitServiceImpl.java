package fr.adaming.Service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateful
public class ProduitServiceImpl implements IProduitService {

	@EJB
	private IProduitDao prDao;

	@EJB
	private ICategorieDao catDao;

	@Override
	public int ajoutProduit(Produit pr) {

		// recuperer la cat avec son id
		Categorie cat = catDao.getCategorieById(pr.getCategorie());
		pr.setCategorie(cat);
		return prDao.ajoutProduit(pr);
	}

	@Override
	public int modifProduit(Produit pr) {

		return prDao.modifProduit(pr);
	}

	@Override
	public int supprProduit(Produit pr) {
		// TODO Auto-generated method stub
		return prDao.supprProduit(pr);
	}

	@Override
	public Produit rechProduit(Produit pr) {
		// TODO Auto-generated method stub
		return prDao.rechProduit(pr);
	}

	@Override
	public List<Produit> getAllProduit() {
		// TODO Auto-generated method stub
		return prDao.getAllProduit();
	}

	@Override
	public List<Produit> getProduitByIdCat(Categorie cat) {
		return prDao.getProduitByIdCat(cat);
	}

}
