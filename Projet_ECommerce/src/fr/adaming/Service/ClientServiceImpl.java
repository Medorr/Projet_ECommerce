package fr.adaming.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import fr.adaming.dao.IClientDao;
import fr.adaming.dao.ICommandeDao;
import fr.adaming.managedBeans.PanierManagedBean;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;


import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Stateful
public class ClientServiceImpl implements IClientService {

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

	@Override
	public List<Client> getClientByNomOrId(Client cl) {
		return clDao.getClientByNomOrId(cl);
	}

	

}
