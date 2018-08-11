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

	@Override
	public void sendMail(Client cl) {
		
		//mon compte gmail (pour recevoir les messages)
		final String username = "bauchemin.c@gmail.com";
		final String password = "claire2208";

		//les propriétées 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		// recuperer ma session
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			// creer l'objet message MimeMessage
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(username));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(cl.getEmail()));

			// Set Subject: header du message
			message.setSubject("Mail facture");
			
			ByteArrayOutputStream outputStream = null;
			
			 // Partie 1: Le texte
		    MimeBodyPart mbp1 = new MimeBodyPart();
		    mbp1.setText("Cher(e) Client(e),"+ "\n\n Merci de votre confiance!" + "\n\n Vous trouverez ci-joint votre facture");
	 
		    //ecrire le pdf dans outputStream
            outputStream = new ByteArrayOutputStream();
            writePdf(outputStream, cl);
            byte[] bytes = outputStream.toByteArray();
             
            //construire le pdf
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfbp = new MimeBodyPart();
            pdfbp.setDataHandler(new DataHandler(dataSource));
            pdfbp.setFileName("test.pdf");
             
		    // On regroupe les deux dans le message
		    MimeMultipart mp = new MimeMultipart();
		    mp.addBodyPart(mbp1);
		    mp.addBodyPart(pdfbp);
		    message.setContent(mp);
		    
			// on envoie le message 
			Transport.send(message);

			System.out.println("Sent message successfully....");//verifier si ca a reussi

		} catch (Exception e) {
			throw new RuntimeException(e);
			
		}
	}
	 /**
     * ecrire le pdf (using iText API)
     */
    public void writePdf(OutputStream outputStream, Client cl) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
         
        //ouvrir le document
        document.open();
         
        //données du document
        document.addTitle("Facture PDF");
        document.addSubject("Testing email PDF");
        document.addKeywords("iText, email");
        document.addAuthor("Steven, Demba et Claire");
        document.addCreator("Steven, Demba et Claire");
         
        //composition du pdf
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Chunk("Cher(e) Mr(Mme) "+ cl.getNomClient() + ","+ "\n\n voici votre facture"));
        document.add(paragraph);
         
        //fermer le document
        document.close();
    }

}
