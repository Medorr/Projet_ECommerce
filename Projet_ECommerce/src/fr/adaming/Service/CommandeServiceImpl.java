package fr.adaming.Service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.dao.ICommandeDao;
import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Commande;
import fr.adaming.model.Produit;

@Stateful
public class CommandeServiceImpl implements ICommandeService {

	@EJB
	private ICommandeDao comDao;


	@Override
	public int ajouterCommande(Commande com) {

		
		return comDao.ajoutCommande(com);
	}

	@Override
	public int modifCommande(Commande com) {

		return comDao.modifCommande(com);
	}

	@Override
	public int supprCommande(Commande com) {
		// TODO Auto-generated method stub
		return comDao.supprCommande(com);
	}

	@Override
	public Commande getCommandeById(Commande com) {
		// TODO Auto-generated method stub
		return comDao.rechCommande(com);
	}

	@Override
	public List<Commande> getAllCommande() {
		// TODO Auto-generated method stub
		return comDao.getAllCommande();
	}

	
}
