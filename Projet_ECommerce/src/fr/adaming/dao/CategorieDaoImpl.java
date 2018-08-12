package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

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
		
		for(Categorie cat:listeCatJPQL){
			cat.setImage("data:image/png);base64," + Base64.encodeBase64String(cat.getPhoto()));
		}
		return queryList.getResultList();
	}

	@Override
	public Categorie ajouterCategorie(Categorie cat) {
		em.persist(cat);
		if(em.find(Categorie.class, cat.getId())!=null){
			return cat;
		}else{
			return null;
		}
	}

	@Override
	public int modifCategorie(Categorie cat) {
		
		String reqModif="UPDATE Categorie cat SET cat.nom=:pNom, cat.photo=:pPhoto, cat.description=:pDescription WHERE cat.id=:pId";
		
		// Creation du query 
		Query queryModif=em.createQuery(reqModif);
		
		queryModif.setParameter("pNom", cat.getNom());
		queryModif.setParameter("pPhoto", cat.getPhoto());
		queryModif.setParameter("pDescription", cat.getDescription());
		queryModif.setParameter("pId", cat.getId());
//		// je recupere une Categorie 
//		Categorie catBD=em.find(Categorie.class, cat);
//		//je le parsiste dans la BD
//		em.persist(catBD);
//		if(em.find(Categorie.class, cat)!=null){
//			return 1;
//		}else{
		
		return queryModif.executeUpdate();
		
	}

	@Override
	public int supprCategorie(Categorie cat) {
	//recuperer une categorie 

		//je la parsiste
		cat=em.merge(cat);
		em.remove(cat);
		Categorie catBD=em.find(Categorie.class, cat.getId());
		
		if(catBD!=null){
			return 0;
			
		}else{		
		return 1;
		}
	}

	@Override
	public Categorie rechCategorie(Categorie cat) {
		//catOut.setImage("data:image/png);base64," + Base64.encodeBase64String(catOut.getPhoto()));
		
		//Ma requete JPQL
		String reqCatJPQL="Select cat from Categorie as cat WHERE cat.nom=:pNOm";
		
		//Creation du sous bus Query
		Query queryCat=em.createQuery(reqCatJPQL);
		
		//Passage des params 
		queryCat.setParameter("pNom", cat.getNom());
		

		return (Categorie) queryCat.getSingleResult();
	}

	@Override
	public Categorie getCategorieById(Categorie cat) {
		//Je recupere une cat dans la BD
		Categorie catOut=em.find(Categorie.class, cat.getId());
		
		catOut.setImage("data:image/png);base64," + Base64.encodeBase64String(catOut.getPhoto()));
		
		return catOut;
	}

	@Override
	public Categorie getCategorieByNomOrId(Categorie cat) {
		// Creation d'une requete JPQL
		String reqCatJPQL="Select cat From Categorie as cat where cat.nom=:pNom OR cat.id=:pId";
		//Creation du sous bus le query
		Query queryCat=em.createQuery(reqCatJPQL);
		//Passage des paras
		queryCat.setParameter("pNom", cat.getNom());
		queryCat.setParameter("pId", cat.getId());
		
		return (Categorie) queryCat.getSingleResult();
	}
}
