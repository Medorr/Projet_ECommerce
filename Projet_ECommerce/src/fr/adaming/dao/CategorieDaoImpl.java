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

	@Override
	public List<Categorie> getAllCategorie() {// Utilisation de JPQL
		// Creation de la req JPQL
		String reqJPQL="SELECT c FROM Categorie as c";
		
		// Creation du bus Query
		Query queryList=(Query) em.createQuery("reqJPQL");
		
		//Afficher 
		return ;
	}
	
//	/** Recuperer la liste des categorie avec SQL*/
//	/**Creation de EMF*/
//	EntityManagerFactory emf=Persistence.createEntityManagerFactory("PU");
//	
//	/**Creation de EM*/
//	EntityManager em=emf.createEntityManager();



}
