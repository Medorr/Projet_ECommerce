package fr.adaming.Service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.mail.Authenticator;
import javax.mail.Message;
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

import fr.adaming.dao.ICategorieDao;
import fr.adaming.dao.ICommandeDao;
import fr.adaming.dao.ILigneCommande;
import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Stateful
public class CommandeServiceImpl implements ICommandeService {

	@EJB
	private ICommandeDao comDao;
	
	@EJB
	private ILigneCommande lcDao;
	
	@EJB
	private IProduitDao prDao;


	@Override
	public int ajouterCommande(Commande com) {

		
		return comDao.ajoutCommande(com);
	}

	@Override
	public int modifCommande(Commande com) {

		return comDao.modifCommande(com);
	}

	@Override
	public int supprCommande(Commande com) {
		// TODO Auto-generated method stub
		return comDao.supprCommande(com);
	}

	@Override
	public Commande getCommandeById(Commande com) {
		// TODO Auto-generated method stub
		return comDao.rechCommande(com);
	}

	@Override
	public List<Commande> getAllCommande() {
		// TODO Auto-generated method stub
		return comDao.getAllCommande();
	}
	
	@Override
	public void sendMail(Commande com) {

		// mon compte gmail (pour recevoir les messages)
		final String username = "tototiti44000@gmail.com";
		final String password = "adaming44";

		// les propriétées
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
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(com.getClient().getEmail()));

			// Set Subject: header du message
			message.setSubject("Mail facture");

			ByteArrayOutputStream outputStream = null;

			// Partie 1: Le texte
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText("Cher(e) Client(e)," +com.getClient().getNomClient()+ "\n\n Merci de votre confiance!"
					+ "\n Vous trouverez ci-joint votre facture"
					+ "\n\n\n l'equipe Demba, Steven et Claire espère vous revoir bientôt sur notre site!");

			// ecrire le pdf dans outputStream
			outputStream = new ByteArrayOutputStream();
			writePdf(outputStream, com);
			byte[] bytes = outputStream.toByteArray();

			// construire le pdf
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

			System.out.println("Sent message successfully....");// verifier si
																// ca a reussi

		} catch (Exception e) {
			throw new RuntimeException(e);

		}
	}

	/**
	 * ecrire le pdf (using iText API)
	 */
	public void writePdf(OutputStream outputStream, Commande com ) throws Exception {
		Document document = new Document();
		PdfWriter.getInstance(document, outputStream);

		// ouvrir le document
		document.open();

		// données du document
		document.addTitle("Facture PDF");
		document.addSubject("Testing email PDF");
		document.addKeywords("iText, email");
		document.addAuthor("Steven, Demba et Claire");
		document.addCreator("Steven, Demba et Claire");

		// composition du pdf
		Paragraph paragraph = new Paragraph();
		paragraph.add(new Chunk("Cher(e) Mr(Mme) " + com.getClient().getNomClient() + "," + "\n voici votre facture \n\n"));
		document.add(paragraph);
		
		/** récup de la liste des lignes commandes associées à la commande */
		List<LigneCommande> listeLc = lcDao.getListeLigneCommandeByComId(com);
		
		/** un paragraphe pour chaque lc */
		for (LigneCommande lc : listeLc){
			Paragraph paragraph1 = new Paragraph();
			paragraph.add(new Chunk("Produit : "+prDao.rechProduit(lc.getProduit()).getDesignation()
					+", quantité : "+lc.getQuantite()
					+", Prix : "+prDao.rechProduit(lc.getProduit()).getPrix()+" x "+lc.getQuantite()));
			document.add(paragraph1);
		}
		
		Paragraph paragraph2 = new Paragraph();
		paragraph.add(new Chunk("_____________________________________________________________________"));
		document.add(paragraph2);
		
		/** calcul du prix total */
		Double d =0.0 ;
		for(LigneCommande lc : listeLc){
			d += lc.getPrix();
		}
		Paragraph paragraph3 = new Paragraph();
		paragraph.add(new Chunk("Prix total: " + d));
		document.add(paragraph3);
		
		
		// fermer le document
		document.close();
	}

	
}
