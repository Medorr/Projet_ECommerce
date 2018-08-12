package fr.adaming.managedBeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.Service.ICommandeService;
import fr.adaming.Service.IProduitService;
import fr.adaming.dao.ILigneCommande;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import sun.security.jca.GetInstance;

@ManagedBean(name = "panMB")
@RequestScoped
public class PanierManagedBean implements Serializable{
	private static final long serialVersionUID = 6088971269159960468L;
	/** attributs */
	private Panier panier;
	private LigneCommande article;
	private HttpSession maSession;
	private Double prixTotal = 0.0;
	private int nombreArticles;
	private Commande commande;
	
	@EJB
	private IProduitService prService;
	
	@EJB
	private ILigneCommande lcService;
	
	@EJB
	private ICommandeService comService;
	
	@PostConstruct
	public void init(){
		maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if(maSession.getAttribute("listeLcSession")!=null){
			this.panier.setListeLignesCommande((List<LigneCommande>) maSession.getAttribute("listeLcSession"));
			for(LigneCommande lc : this.panier.getListeLignesCommande()){
				prixTotal += lc.getPrix();
			}
			this.nombreArticles=this.panier.getListeLignesCommande().size();
			this.commande=new Commande();
		}
		
		
		
	}

	public PanierManagedBean() {
		super();
		this.panier = new Panier();
		this.article = new LigneCommande();
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}	
	
	public LigneCommande getArticle() {
		return article;
	}

	public void setArticle(LigneCommande article) {
		this.article = article;
	}

	public HttpSession getMaSession() {
		return maSession;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}
	
	public int getNombreArticles() {
		return nombreArticles;
	}

	public void setNombreArticles(int nombreArticles) {
		this.nombreArticles = nombreArticles;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	/** méthodes : */
	public String ajoutArticle() {
		this.article.setPrix(this.article.getProduit().getPrix()*this.article.getQuantite());
		this.panier.getListeLignesCommande().add(this.article);
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeLcSession", this.panier.getListeLignesCommande());
		return "panier";
	}
	
	public String passerCommande(){
		Date date = new Date();
		this.commande = new Commande(date);
		this.commande.setClient((Client) maSession.getAttribute("clSession"));
		comService.ajouterCommande(this.commande);

		
		for (LigneCommande lc : this.panier.getListeLignesCommande()){
			lc.setCommande(commande);
			lcService.ajoutLigneCommande(lc);
		}
		comService.sendMail(commande);
		return "confirmationCommande";
	}
	

}
