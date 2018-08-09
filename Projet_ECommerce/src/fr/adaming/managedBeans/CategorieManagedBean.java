package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import fr.adaming.Service.ICategorieService;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@ManagedBean(name="catMB")
@RequestScoped
public class CategorieManagedBean implements Serializable{
	
	// transformation de l'asso UML en JAVA
	@EJB
	private ICategorieService catService;
	
	// les attributs 
	private Categorie categorie;
	private Produit produit;
	HttpSession maSession;
	private List<Categorie> listeCat;
	
	//Attribut pour l'image
	private UploadedFile file;
	
	// Cosntructeur vide
	public CategorieManagedBean() {
		super();
		this.categorie=new Categorie(); // Pour eviter l'exeption Target Unreachable,
	}
	// Cette methode sera utilisée juste apres l'instanciation du MB
	@PostConstruct
	public void init(){
		//recuperer la session
		maSession =(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.categorie=(Categorie) maSession.getAttribute("categorieSession");
	}
	
	//Getters et setters pour categorie
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	
	public List<Categorie> getListeCat() {
		return listeCat;
	}
	public void setListeCat(List<Categorie> listeCat) {
		this.listeCat = listeCat;
	}
		
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	//****************************************************************************************************
	public String addCategorie(){
		
		this.categorie.setPhoto(file.getContents());
		
		//Recuperer une categorie depuis la BD
		Categorie catAjout=catService.ajouterCategorie(categorie);
		
	if(catAjout.getId()!=0){
		//recuperer la liste des categories
		listeCat =catService.getAllCategorie();
		
		// ajouter la liste des Cat trouvé dans la session Http
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("categorieSession", listeCat);
			
			return  "accueil";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Désolé l'ajout à échouer"));
			return "ajoutCat";
		}
		
	}

}
