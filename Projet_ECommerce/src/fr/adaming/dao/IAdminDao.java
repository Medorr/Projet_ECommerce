package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.Admin;

@Local
public interface IAdminDao {

	/**
	 * Methode isExist(l'admin existe)
	 */
	public Admin isExist(Admin admin);
}
