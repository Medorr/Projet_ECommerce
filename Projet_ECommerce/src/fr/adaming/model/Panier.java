package fr.adaming.model;

/**Cest une classe transiente: non persistence. On n'a pas d'annotation @Entity*/
public class Panier extends LigneCommande{
	
	/**Les attributs */
	private int idPan;
	
	/**Constructeur vide */
	public Panier() {
		super();
	}
	/**constructeur avec id*/
	public Panier(int idPan) {
		super();
		this.idPan = idPan;
	}
	/**Getters et setters */
	public int getIdPan() {
		return idPan;
	}

	public void setIdPan(int idPan) {
		this.idPan = idPan;
	}
	
	
	}
