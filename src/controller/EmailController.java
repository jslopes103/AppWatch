package controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//email controller
public class EmailController {

	//login and password
	final String username = "appwatch80@gmail.com";
	final String password = "Appwatch80&*^%&%&^!";
	
	//method send email
	public boolean sendEmail(String email, String card, String name) {

		//create prop object
		Properties prop = new Properties();
		//fill the object
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		//init a session
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			//init a message with the session
			Message message = new MimeMessage(session);
			//from email
			message.setFrom(new InternetAddress("AppWatch"));
			//set recipient
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			//subject
			message.setSubject("Payment confirmation of "+name);
			//text email
			message.setText("Hello, thank you for buying " + name + " with us on the credit card: " + card +". We hope you have a great moment watching our movies and series.");
            //email send
			Transport.send(message);
			
			return true;

		} catch (MessagingException e) {
			//get error message
			e.printStackTrace();
		}
		
		return false;
	}
}
