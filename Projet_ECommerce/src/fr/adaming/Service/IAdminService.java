package fr.adaming.Service;

import javax.ejb.Local;

import fr.adaming.model.Admin;

@Local
public interface IAdminService {
	/**
	 * Methode isExist(l'admin existe)
	 */
	public Admin isExist(Admin admin);

}
