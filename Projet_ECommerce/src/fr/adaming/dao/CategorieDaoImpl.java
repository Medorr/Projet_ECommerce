package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		Query queryList=em.createQuery(reqJPQL);
		
		@SuppressWarnings("unchecked")
		List<Categorie> listeCatJPQL=queryList.getResultList();
		if (listeCatJPQL!=null){
		//Afficher la liste 
		return queryList.getResultList();
		} else{
			return null;
		}
	}

	@Override
	public Categorie ajouterCategorie(Categorie cat) {
		em.persist(cat);
		if(em.find(Categorie.class, cat)!=null){
			return cat;
		}else{
			return null;
		}
	}

	@Override
	public int modifCategorie(Categorie cat) {
		// je recupere une Categorie 
		Categorie catBD=em.find(Categorie.class, cat);
		//je le parsiste dans la BD
		em.persist(catBD);
		if(em.find(Categorie.class, cat)!=null){
			return 1;
		}else{
		
		return 0;
		}
	}

	@Override
	public int supprCategorie(Categorie cat) {
	//recuperer une categorie 
		Categorie catBD=em.find(Categorie.class, cat);
		//je le parsiste
		em.persist(catBD);
		
		if(em.find(Categorie.class, catBD)!=null){
			
		}
		
		return 0;
	}

	@Override
	public Categorie rechCategorie(Categorie cat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorie getCategorieById(Categorie cat) {
		// TODO Auto-generated method stub
		return em.find(Categorie.class, cat.getId());
	}




}
