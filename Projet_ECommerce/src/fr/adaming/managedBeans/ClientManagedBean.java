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

import org.primefaces.component.messages.Messages;

import fr.adaming.Service.IClientService;
import fr.adaming.model.Client;

@ManagedBean(name = "clMB")
@RequestScoped
public class ClientManagedBean implements Serializable {
	/**
	 * Transformation de l'association UML en JAVA et injection dependance
	 */
	@EJB
	private IClientService clService;

	/**
	 * Declaration des attributs
	 */
	private Client client;
	private HttpSession maSession;
	private boolean indice;
	private List<Client> clListe;
	

	/**
	 * Declaration du constructeur vide
	 */
	public ClientManagedBean() {
		super();
		this.client = new Client();
	}

	/** Ajout de la methode intit*/
	@PostConstruct
	public void init(){
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.indice = false;
		this.clListe= clService.getAllClient();
		
	}
	/**
	 * Getters et setters
	 */
	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client
	 *            the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}
	/**
	 * @return the indice
	 */
	public boolean isIndice() {
		return indice;
	}
	

	/**
	 * @return the clListe
	 */
	public List<Client> getClListe() {
		return clListe;
	}

	/**
	 * @param clListe the clListe to set
	 */
	public void setClListe(List<Client> clListe) {
		this.clListe = clListe;
	}

	/**
	 * @param indice the indice to set
	 */
	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	/** Methodes du client */
	
	public String ajoutClient() {
		Client clEnr = clService.enregistrerClient(client);
		
		clService.sendMail(clEnr);

		if (clEnr.getIdClient() != 0) {
			/** Recuperer la liste */
			List<Client> listeCl = clService.getAllClient();

			return "paiement";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'enregistrement du client a echoué"));

			return "ajoutCl";
		}

	}

	public String modifClient() {
		Client clModif = clService.modifClient(client);

		if (clModif.getIdClient() != 0) {
			/** Recuperer la liste */
			List<Client> listeCl = clService.getAllClient();

			return "accueilCl";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification du client a echoué"));

			return "modifCl";
		}

	}

	public String supprClient() {
		Client clSup = clService.supprClient(client);

		if (clSup.getIdClient() != 0) {
			/** Recuperer la liste */
			List<Client> listeCl = clService.getAllClient();

			return "accueilCl";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression du client a echoué"));

			return "supprCl";
		}

	}

	public String rechIdNom(){
		List<Client> listRech = clService.getClientByNomOrId(client);
		
		if(listRech != null){
			this.clListe = listRech;
			this.indice = true;
			
			
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le ou les clients recherchés sont introuvables"));
			
		}
		return "rechCl";
		
	}
		
	}


