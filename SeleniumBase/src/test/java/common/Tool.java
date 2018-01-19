package common;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Tool {

	/**
	 *
	 * Metodo para enviar correo con java
	 */
	public boolean enviarcorreo(String de, String clave, String para, String mensaje, String asunto) {
		boolean enviado = false;
		try {

			String[] destinos;
			String host = "smtp.gmail.com";

			Properties prop = System.getProperties();

			prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.transport.protocol", "smtp");
			prop.put("mail.smtp.host", host);
			prop.put("mail.smtp.user", de);
			prop.put("mail.smtp.password", clave);
			prop.put("mail.smtp.port", 25);
			prop.put("mail.smtp.auth", "true");

			Session session = Session.getInstance(prop, null);
			MimeMessage message = new MimeMessage(session);

			// setiar From
			message.setFrom(new InternetAddress(de));

			// separar en un arreglo los destinatarios, seprando por ;
			String[] destinatarios = para.split(";");

			Address[] destinoss = new Address[destinatarios.length];// Aqui usamos el arreglo de destinatarios
			// se recorrern los destinatarios
			for (int i = 0; i < destinatarios.length; i++) {
				destinoss[i] = new InternetAddress(destinatarios[i]);
				message.setFrom(new InternetAddress(de));
			}

			// Se setea el arreglo de destinatarios
			message.addRecipients(Message.RecipientType.TO, destinoss);

			// Crear Asunto
			message.setSubject(asunto);
			// Crear Mensaje
			message.setText(mensaje);

			// Crear Mimepart Texto
			BodyPart Texto = new MimeBodyPart();
			Texto.setText(mensaje);

			// Crear Mimepart Adjuto
			BodyPart adjunto = new MimeBodyPart();
			adjunto.setDataHandler(new DataHandler(new FileDataSource("c:/foto.jpg")));
			adjunto.setFileName("foto.jpg");

			// crear multiparte
			MimeMultipart multiParte = new MimeMultipart();
			multiParte.addBodyPart(Texto);
			multiParte.addBodyPart(adjunto);

			message.setContent(multiParte);

			System.out.print(message);

			Transport transport = session.getTransport("smtp");
			transport.connect(host, de, clave);

			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			enviado = true;
			System.out.print(enviado);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return enviado;

	}

}
