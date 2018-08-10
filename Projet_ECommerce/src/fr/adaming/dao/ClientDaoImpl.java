package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Client;

@Stateless
public class ClientDaoImpl implements IClientDao {

	/** appel de EntityManager */
	@PersistenceContext(unitName = "PU")
	private EntityManager em;

	/** Methode pour enregister client */
	@Override
	public Client enregistrerClient(Client cl) {
		em.persist(cl);
		return cl;
	}

	/** Methode pour modifier client */
	@Override
	public Client modifClient(Client cl) {
		em.merge(cl);
		return cl;

	}

	/** Methode pour supprimer client */
	@Override
	public Client supprClient(Client cl) {
		Client clOut = em.find(Client.class, cl.getIdClient());
		try {
			em.remove(clOut);
			return clOut;
		} catch (Exception exep) {
			exep.printStackTrace();
		}
		return null;
	}

	/** Methode liste des clients */
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAllClient() {
		/** Req jpql */
		String req = "SELECT cl FROM Client as cl";
		/** Query */
		Query query = em.createQuery(req);

		return query.getResultList();
	}

	/**
	 * Methode rechercher un client par id
	 */
	@Override
	public Client getClientById(Client cl) {
		return em.find(Client.class, cl.getIdClient());
	}

	/**
	 * Methode rechercher les clients  par nom
	 */
	@Override
	public Client getClientByNom(Client cl) {
		/**Req JPQL*/
		String req="SELECT cl FROM Client as cl WHERE cl.nomClient=:pNomClient";
		
		/**Query*/
		Query query = em.createQuery(req);
		
		/**passage des params*/
		query.setParameter("pNomClient", cl.getNomClient());
		
		return (Client) query.getSingleResult();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getClientByNomOrId(Client cl) {
		/**req JPQL*/
		String req = "SELECT cli FROM Client as cli WHERE cli.nomClient=:pNomClient OR cli.idClient=:pIdClient";
		/**Query*/
		Query query = em.createQuery(req);
		/**passage des params*/
		query.setParameter("pNomClient", cl.getNomClient());
		query.setParameter("pIdClient", cl.getIdClient());
		
		return query.getResultList();
	}


	
	

}
