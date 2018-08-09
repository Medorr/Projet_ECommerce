package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Categorie implements Serializable{
	
	//Les attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cat")
	private Long id;
	private String nom;
	@Lob
	private byte photo;
	private String description;
	
	//Transformation de l'asso UML en JAVA
	@OneToMany(mappedBy="categorie", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Produit> listeProduits;
	
	//Constructeur vide 
	public Categorie() {
		super();
	}

	//Constructeur sans id
	public Categorie(String nom, byte photo, String description) {
		super();
		this.nom = nom;
		this.photo = photo;
		this.description = description;
	}

	//Constructeur avec id
	public Categorie(Long id, String nom, byte photo, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.photo = photo;
		this.description = description;
	}
	//Getters et setters 

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public byte getPhoto() {
		return photo;
	}


	public void setPhoto(byte photo) {
		this.photo = photo;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	//Methodes toString
	@Override
	public String toString() {
		return "Categorie [idCat=" + id + ", nom=" + nom + ", photo=" + photo + ", description=" + description + "]";
	}
}
