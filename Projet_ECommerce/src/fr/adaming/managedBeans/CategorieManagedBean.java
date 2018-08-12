package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.OneToMany;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import fr.adaming.Service.ICategorieService;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@ManagedBean(name = "catMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {

	// transformation de l'asso UML en JAVA
	@EJB
	private ICategorieService catService;

	// les attributs
	private Categorie categorie;

	private List<Produit> listProd;
	HttpSession maSession;
	private List<Categorie> listeCat;

	// Attribut pour l'image
	private UploadedFile file;

	// Cosntructeur vide
	public CategorieManagedBean() {
		super();
		this.categorie = new Categorie(); // Pour eviter l'exeption Target
											// Unreachable,
	}

	// Cette methode sera utilisée juste apres l'instanciation du MB
	@PostConstruct
	public void init() {
		// recuperer la session
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		listeCat = catService.getAllCategorie();

	}

	// Getters et setters pour categorie
	public List<Categorie> getListeCat() {
		return listeCat;
	}

	public void setListeCat(List<Categorie> listeCat) {
		this.listeCat = listeCat;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Produit> getListProd() {
		return listProd;
	}

	public void setListProd(List<Produit> listProd) {
		this.listProd = listProd;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	
	
	// ****************************************************************************************************
	public String addCategorie() {

		this.categorie.setPhoto(file.getContents());

		// Recuperer une categorie depuis la BD
		Categorie catAjout = catService.ajouterCategorie(this.categorie);

		if (catAjout.getId() != 0) {
			// recuperer la liste des categories
			listeCat = catService.getAllCategorie();

			return "accueil";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Désolé l'ajout à échouer"));
			return "ajoutCat";
		}

	}
	// ****************************************************************************************************
	public String modifCategorie(){
		
		this.categorie.setPhoto(file.getContents());
		// Recuperer une cat
		int catBD=catService.modifCategorie(this.categorie);
		
		if(catBD!=0){
			//recuperer la liste des cat
			listeCat=catService.getAllCategorie();
			return "accueil";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new  FacesMessage("Desolé, la modif à échouer"));
			return "modifCat";
		}
	}
	// ****************************************************************************************************
	
	public String supprCategorie(){
		
		//Recuper la cat dans BD
		int catBD=catService.supprCategorie(this.categorie);
		if(catBD!=0){
			//recuperer la nouvelle liste
			listeCat=catService.getAllCategorie();
			return "accueil";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la supression de Cat à échouer"));
			return "supprCat";
		}
	}
	// ****************************************************************************************************
	public String rechCategorie(){
		
		//recuperer la cat dans la BD
		Categorie catBD=catService.rechCategorie(this.categorie);
		
		if(catBD!=null){
			//afficher la cat
			this.categorie=catBD;
			return "rechCat";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La recherche de cat à échouer"));
		}
		return "rechCat";
	}
	// ****************************************************************************************************
	public String rechCategorieById(){
		//recuperer la cat dans la BD
		Categorie catBD=catService.getCategorieById(this.categorie);
		if (catBD!=null){
			this.categorie=catBD;
			return "rechCat";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la recherche By Id à échouer"));
			return "rechCat";
		}
	}
	// ****************************************************************************************************
	public String rechCatByNomOrId(){
		//Recuperer une categorie 
		Categorie catBD=catService.getCategorieByNomOrId(categorie);
		if(catBD!=null){
			this.categorie=catBD;

		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La recherche a échoué"));
	}return "rechCat";
	}
}
