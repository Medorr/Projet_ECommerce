package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import fr.adaming.model.Commande;

@Stateful
public class CommandeDaoImpl implements ICommandeDao{
	@PersistenceContext(unitName = "PU")
	private EntityManager em;

	@Override
	public int ajoutCommande(Commande com) {
		em.persist(com);
		if (em.find(Commande.class, com.getIdCommande()) != null) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int modifCommande(Commande com) {
		String req = "UPDATE Commande com SET com.dateCommande=:pDate WHERE com.idCommande =:pId";
		Query query = em.createQuery(req);
		query.setParameter("pDate", com.getDateCommande());
		
		return query.executeUpdate();
	}

	@Override
	public int supprCommande(Commande com) {
		com = em.merge(com);
		em.remove(com);
		Commande verif = em.find(Commande.class, com.getIdCommande());
		if(verif !=null){
			return 0; 
		}else{
		return 1;}
	}

	@Override
	public List<Commande> getAllCommande() {
		String req = "SELECT com FROM LigneCommande com";
		Query queryListCommande = em.createQuery(req);
		List<Commande> listeCommande = queryListCommande.getResultList();
		
		return listeCommande;
	}
	
	@Override
	public Commande rechCommande(Commande com) {
		Commande comOut = em.find(Commande.class, com.getIdCommande());
		if (comOut != null) {
			return comOut;
		} else {
			return null;
		}
	}

}
