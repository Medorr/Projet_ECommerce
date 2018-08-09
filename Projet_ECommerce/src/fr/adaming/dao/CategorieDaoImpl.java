package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
@Stateless //** Pour la methode DAO, on utilie stateless pour qu'il garde son etat*/
public class CategorieDaoImpl implements ICategorieDao{
	
	/**On utilise EJB avec l'annotation JPA*/
	@PersistenceContext(unitName="PU")
	private EntityManager em;
	
//	/** Recuperer la liste des categorie avec SQL*/
//	/**Creation de EMF*/
//	EntityManagerFactory emf=Persistence.createEntityManagerFactory("PU");
//	
//	/**Creation de EM*/
//	EntityManager em=emf.createEntityManager();

	@Override
	public List<Categorie> getAllCategorie() {		
		/**Creation de la req SQL*/
		String reqListSQL="SELECT*FROM categories";
		
		/**Creation de la liste de Catégorie*/
		Query queryListSQL=(Query) em.createNativeQuery(reqListSQL, Categorie.class);
		
		//** Recuperer la nouvelle liste des catégorie*/
		List<Categorie> listeSQL=queryListSQL.getResultList();		
		
		return query.getResultatList();
	}

}
