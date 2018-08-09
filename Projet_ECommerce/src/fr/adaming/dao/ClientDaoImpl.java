package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Client;
@Stateless
public class ClientDaoImpl implements IClientDao{
	
	/**appel de EntityManager*/
	@PersistenceContext(unitName="PU")
	private EntityManager em;
	
	/** Methode pour enregister client*/
	@Override
	public Client enregistrerClient(Client cl) {
		em.persist(cl);
		return cl;
	}
	/** Methode pour modifier client*/
	@Override
	public Client modifClient(Client cl) {
		em.merge(cl);
		return cl;
	}
	/** Methode pour supprimer client*/
	@Override
	public Client supprClient(Client cl) {
		em.remove(cl);
		return cl;
	}
	/** Methode liste des clients*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAllClient() {
		/** Req jpql*/
		String req= "SELECT cl FROM Client as cl";
		/** Query*/
		Query query = em.createQuery(req);
	
		return query.getResultList();
	}

}
