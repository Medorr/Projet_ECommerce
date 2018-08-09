package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import fr.adaming.Service.IProduitService;
import fr.adaming.model.Categorie;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@ManagedBean(name = "prMB")
@RequestScoped
public class ProduitManagedBean implements Serializable {

	@EJB
	private IProduitService prService;

	private List<Produit> listeProduit;
	private Produit produit;
	private Categorie categorie;
	private LigneCommande ligneCommande;

	private UploadedFile file;

	public ProduitManagedBean() {
		super();
		this.produit = new Produit();
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	public List<Produit> getListeProduit() {
		return listeProduit;
	}

	public void setListeProduit(List<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	/** méthodes : */
	public String ajoutProduit() {
		
		this.produit.setPhoto(file.getContents());
		int prAjout = prService.ajoutProduit(this.produit);

		if (prAjout != 0) {
			listeProduit = prService.getAllProduit();
			return "listeProduit";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a échoué !"));
			return "ajoutProduit";
		}

	}

}
