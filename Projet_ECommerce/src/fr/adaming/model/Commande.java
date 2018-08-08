package fr.adaming.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/** Classe développée par Steven */
@Entity
@Table(name="commandes")
public class Commande implements Serializable{
	private static final long serialVersionUID = 7190090467064659786L;
	
	/** Déclaration des attributs */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_co")
	private Long idCommande;
	private Date dateCommande;
	
	/** Transformation de l'association UML en Java */
	@ManyToOne
	@JoinColumn(name="cl_id", referencedColumnName="id_cl")
	private Client client;
	
	@ManyToMany /** erreur : en attente de l'annotation @entity sur la classe Produit */
	/** Dans l'association Commande <--> Produit, on considère Commande comme la classe maître et Produit comme la classe esclave */
	@JoinTable(name="comm_prod", joinColumns=@JoinColumn(name="co_id"), inverseJoinColumns=@JoinColumn(name="p_id"))
	private List<Produit> listeProduits = new ArrayList<Produit>();
	
	/** Constructeurs */
	public Commande() {
		super();
	}
	public Commande(Date dateCommande) {
		super();
		this.dateCommande = dateCommande;
	}
	public Commande(Long idCommande, Date dateCommande) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
	}
	
	/** Getters et setters */
	public Long getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(Long idCommande) {
		this.idCommande = idCommande;
	}
	public Date getDateCommande() {
		return dateCommande;
	}
	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}
	
	/** toString */
	@Override
	public String toString() {
		return "Commande [idCommande=" + idCommande + ", dateCommande=" + dateCommande + "]";
	}
	
	

}
