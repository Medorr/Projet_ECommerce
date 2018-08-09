package fr.adaming.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Produit;

public class ProduitDaoImpl implements IProduitDao{
	@PersistenceContext(unitName="PU")
	private EntityManager em;
	
	
	@Override
	public int ajoutProduit(Produit pr) {
		em.persist(pr);
		if(em.find(Produit.class, pr) != null){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int modifProduit(Produit pr) {
		Produit prIn = em.find(Produit.class, pr);
		em.merge(prIn);
		if(em.find(Produit.class, pr).toString()==prIn.toString()){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int supprProduit(Produit pr) {
		String req = "SELECT pr FROM Produit pr";
		Query queryListPr = em.createQuery(req);
		int size1 = queryListPr.getResultList().size();
		
		em.remove(pr);
		
		int size2 = queryListPr.getResultList().size();
		
		if (size2 == (size1-1)){
			return 1;
		}else{
			return 2;
		}
	}

	@Override
	public Produit rechProduit(Produit pr) {
		Produit prOut = em.find(Produit.class, pr);
		if(prOut != null){
			return prOut;
		}else{
			return null;	
		}
	}

	@Override
	public List<Produit> getAllProduit() {
		String req = "SELECT pr FROM Produit pr";
		Query queryListProduit = em.createQuery(req);
		List<Produit> listeProduit = queryListProduit.getResultList();
		if (listeProduit != null){
			return listeProduit;
		}else{
			return null;
		}
		
	}

}
