package fr.adaming.Service;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;
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

	@Override
	public void sendMail(Client cl) {
		final String username = "bauchemin.c@gmail.com";
		final String password = "claire2208";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		// Get Session object.
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(username));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(cl.getEmail()));

			// Set Subject: header field
			message.setSubject("Mail facture");

			 // Partie 1: Le texte
			
		    MimeBodyPart mbp1 = new MimeBodyPart();
		    mbp1.setText("Chère Client," + "\n\n Merci de votre confiance!" + "\n\n Vous trouverez ci-joint votre facture");
	 
//		    // Partie 2: Le fichier joint
//		    MimeBodyPart mbp2 = new MimeBodyPart();
//		    String file = "";
//		    mbp2.attachFile(file);
	 
		    // On regroupe les deux dans le message
		    MimeMultipart mp = new MimeMultipart();
		    mp.addBodyPart(mbp1);
//		    mp.addBodyPart(mbp2);
		    message.setContent(mp);
		    
			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
			
		}
	}

}
