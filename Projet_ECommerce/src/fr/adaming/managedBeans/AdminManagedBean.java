package fr.adaming.managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.adaming.Service.IAdminService;
import fr.adaming.model.Admin;
import fr.adaming.model.Client;

@ManagedBean(name = "adMB")
@RequestScoped
public class AdminManagedBean {
	
	/**
	 * Transformation de l'association UML en JAVA et injection dependance
	 */
	@EJB
	private IAdminService adService;
	private HttpSession maSession;
	/**
	 * Declaration des attributs
	 */
	private Admin admin;
	private List<Client> liste;
	

	/**
	 * Constructeur vide
	 */
	public AdminManagedBean() {
		super();
		this.admin = new Admin();
	}

	/**Getters et setters*/
	/**
	 * @return the admin
	 */
	public Admin getAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
	/**
	 * Methodes
	 */
	/** Connection de l'administrateur*/
	public String seConnecter(){
		Admin connectAd = adService.isExist(admin);
		
		if(connectAd != null){
			
			/**Ajouter l'admin connecté dans la session*/
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adSession", connectAd);
			
			return "accueil";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Attention!!!Login ou Password erroné"));
		return "login";
		}
		
	}
	/**Deconnexion de l'admnistrateur*/
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();

		final HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		request.getSession(false).invalidate();

		return "login";
	}

}
