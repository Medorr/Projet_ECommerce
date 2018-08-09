package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	@Override
	public Client supprClient(Client cl) {
		em.remove(cl);
		return cl;
	}

}
