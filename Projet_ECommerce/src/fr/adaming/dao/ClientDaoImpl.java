package fr.adaming.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.model.Client;

public class ClientDaoImpl implements IClientDao{
	
	/**appel de EntityManager*/
	@PersistenceContext(unitName="PU")
	private EntityManager em;
	
	/** Methode pour enregister client*/
	@Override
	public Client enregistrerClientDao(Client cl) {
		em.persist(cl);
		return cl;
	}
	/** Methode pour modifier client*/
	@Override
	public Client modifClientDao(Client cl) {
		em.merge(cl);
		return cl;
	}
	@Override
	public Client supprClientDao(Client cl) {
		em.remove(cl);
		return cl;
	}

}
