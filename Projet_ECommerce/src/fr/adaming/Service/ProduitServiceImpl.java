package fr.adaming.Service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Produit;

@Stateful
public class ProduitServiceImpl implements IProduitService{
	
	@EJB
	private IProduitDao prDao;
	
	@Override
	public int ajoutProduit(Produit pr) {
		// TODO Auto-generated method stub
		return prDao.ajoutProduit(pr);
	}

	@Override
	public int modifProduit(Produit pr) {
		
		return 0;
	}

	@Override
	public int supprProduit(Produit pr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Produit rechProduit(Produit pr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getAllProduit() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
