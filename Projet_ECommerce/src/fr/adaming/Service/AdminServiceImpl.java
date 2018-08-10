package fr.adaming.Service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IAdminDao;
import fr.adaming.model.Admin;

@Stateful
public class AdminServiceImpl implements IAdminService {

	/**
	 * Transformation de l'association UML en JAVA et injection dependance 
	 */
	@EJB
	private IAdminDao adDao;
	
	@Override
	public Admin isExist(Admin admin) {
		return adDao.isExist(admin);
	}

}
