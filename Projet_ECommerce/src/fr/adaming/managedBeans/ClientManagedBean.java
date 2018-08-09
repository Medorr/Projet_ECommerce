package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


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

	/**
	 * Declaration du constructeur vide
	 */
	public ClientManagedBean() {
		super();
		this.client = new Client();
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

	/** Methodes du client */
	public String enregistrerClient() {
		Client clEnr = clService.enregistrerClient(client);

		if (clEnr.getIdClient() != 0) {
			/** Recuperer la liste */
			List<Client> listeCl = clService.getAllClient();

			return "affCl";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'enregistrement du client a echou�"));
			
			return "enregistrement";
		}

	}
	

}
