package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;
import org.jboss.security.Base64Utils;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateful
public class ProduitDaoImpl implements IProduitDao {
	@PersistenceContext(unitName = "PU")
	private EntityManager em;

	@Override
	public int ajoutProduit(Produit pr) {
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
		//EntityManager em2 = emf.createEntityManager();
		em.persist(pr);
		if (em.find(Produit.class, pr.getIdProduit()) != null) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int modifProduit(Produit pr) {
		String req = "UPDATE Produit pr SET pr.designation=:pDesign, pr.description=:pDescr, pr.prix=:pPrix,pr.quantite=:pQuant, pr.photo=:pPhoto WHERE pr.id =:pId";
		Query query = em.createQuery(req);
		query.setParameter("pDesign", pr.getDesignation());
		query.setParameter("pDescr", pr.getDescription());
		query.setParameter("pPrix", pr.getPrix());
		query.setParameter("pQuant", pr.getQuantite());
		query.setParameter("pPhoto", pr.getPhoto());
		query.setParameter("pId", pr.getIdProduit());
		
		return query.executeUpdate();
	}

	@Override
	public int supprProduit(Produit pr) {
		pr = em.merge(pr);
		em.remove(pr);
		Produit verif = em.find(Produit.class, pr.getIdProduit());
		if(verif !=null){
			return 0; 
		}else{
		return 1;}
	}

	@Override
	public Produit rechProduit(Produit pr) {
		Produit prOut = em.find(Produit.class, pr.getIdProduit());
		if (prOut != null) {
			return prOut;
		} else {
			return null;
		}
	}

	@Override
	public List<Produit> getAllProduit() {
		String req = "SELECT pr FROM Produit pr";
		Query queryListProduit = em.createQuery(req);
		List<Produit> listeProduit = queryListProduit.getResultList();

		for (Produit pr : listeProduit) {
			pr.setImage("data:image/png);base64," + Base64.encodeBase64String(pr.getPhoto()));
		}

		return listeProduit;

	}

	@Override
	public List<Produit> getProduitByIdCat(Categorie cat) {
		String req = "SELECT pr FROM Produit pr WHERE pr.categorie.id=:pId";
		Query queryListProduitCat = em.createQuery(req);
		queryListProduitCat.setParameter("pId", cat.getId());
		List<Produit> listeProduitCat = queryListProduitCat.getResultList();

		for (Produit pr : listeProduitCat) {
			pr.setImage("data:image/png);base64," + Base64.encodeBase64String(pr.getPhoto()));
		}

		return listeProduitCat;
	}

}
