package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Stateful
public class LigneCommandeDaoImpl implements ILigneCommande{
	@PersistenceContext(unitName = "PU")
	private EntityManager em;

	@Override
	public int ajoutLigneCommande(LigneCommande lc) {
		em.persist(lc);
		if (em.find(LigneCommande.class, lc.getId()) != null) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int modifLigneCommande(LigneCommande lc) {
		String req = "UPDATE LigneCommande lc SET lc.quantite=:pQuantite, lc.prix=:pPrix WHERE lc.id =:pId";
		Query query = em.createQuery(req);
		query.setParameter("pQuantite", lc.getQuantite());
		query.setParameter("pPrix", lc.getPrix());
		query.setParameter("pId", lc.getId());
		
		return query.executeUpdate();
	}

	@Override
	public int supprLigneCommande(LigneCommande lc) {
		lc = em.merge(lc);
		em.remove(lc);
		LigneCommande verif = em.find(LigneCommande.class, lc.getId());
		if(verif !=null){
			return 0; 
		}else{
		return 1;}
	}

	@Override
	public List<LigneCommande> getAllLigneCommande() {
		String req = "SELECT lc FROM LigneCommande lc";
		Query queryListLigneCommande = em.createQuery(req);
		List<LigneCommande> listeLigneCommande = queryListLigneCommande.getResultList();
		
		return listeLigneCommande;
	}

}
