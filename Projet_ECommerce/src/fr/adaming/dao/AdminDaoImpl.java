package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Admin;

@Stateless
public class AdminDaoImpl implements IAdminDao {

	/**
	 * injecter un em instancié par le conteneur EJB
	 */
	@PersistenceContext(unitName = "PU")
	private EntityManager em;

	@Override
	public Admin isExist(Admin admin) {
		try{
			/** Creation de la req JPQL pour recuperer l'admin avec son login et son 
			 * password avec utilisation d'un alias adm
			 * */
			String req = "SELECT adm FROM Admin as adm WHERE adm.login=:pLogin AND adm.password=:pPassword";
			
			/**
			 * Recuperer le query
			 */
			Query query = em.createQuery(req);
			
			/**
			 * Assignation des paramètres
			 */
			query.setParameter("pLogin", admin.getLogin());
			query.setParameter("pPassword", admin.getPassword());
					
		return (Admin) query.getSingleResult();
		
		}catch (NoResultException exep) {
			exep.printStackTrace();
		}
		return null;
	}

}
