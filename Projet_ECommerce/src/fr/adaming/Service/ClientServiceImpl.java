package fr.adaming.Service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;
@Stateful
public class ClientServiceImpl implements IClientService{

	/**
	 * Transformation de l'association UML en JAVA et injection dependance 
	 */
	@EJB
	private IClientDao clDao;
	
	/**
	 * Methodes de client
	 */
	@Override
	public Client enregistrerClient(Client cl) {
		return clDao.enregistrerClient(cl);
	}

	@Override
	public Client modifClient(Client cl) {		
		return clDao.modifClient(cl);
	}

	@Override
	public Client supprClient(Client cl) {		
		return clDao.supprClient(cl);
	}

	@Override
	public List<Client> getAllClient() {
		return clDao.getAllClient();
	}

	@Override
	public Client getClientById(Client cl) {
		return clDao.getClientById(cl);
	}

	@Override
	public Client getClientByNom(Client cl) {		
		return clDao.getClientByNom(cl);
	}

}
