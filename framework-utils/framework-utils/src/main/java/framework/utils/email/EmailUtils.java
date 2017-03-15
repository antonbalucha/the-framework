package framework.utils.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.configuration.ConfigurationReader;

public class EmailUtils {

	private final static Logger logger = LoggerFactory.getLogger(EmailUtils.class);
	
	private static Properties getEmailProperties() {
		Properties emailProperties = new Properties();
		emailProperties.put("mail.smtp.host", ConfigurationReader.getEmailSmtpHost());
		emailProperties.put("mail.smtp.port", ConfigurationReader.getEmailSmtpPort());
		emailProperties.put("mail.smtp.socketFactory.class", ConfigurationReader.getEmailSmtpSocketFactoryClass());
		emailProperties.put("mail.smtp.socketFactory.port", ConfigurationReader.getEmailSmtpSocketFactoryPort());
		emailProperties.put("mail.smtp.auth", ConfigurationReader.getEmailSmtpAuth());
		return emailProperties;
	}
	
	private static Session getSession() {
		return Session.getDefaultInstance(getEmailProperties(),
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(ConfigurationReader.getEmailUserName(),ConfigurationReader.getEmailPassword());
				}
			});
	}

	private static void send(String sender, String recipient, String subject, String text) {
		try {
			Message message = new MimeMessage(getSession());
			message.setFrom(new InternetAddress(sender));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setSubject(subject);
			message.setText(text);
			Transport.send(message);
		} catch (MessagingException e) {
			logger.error("MessagingException: " + e.getMessage(), e);
		} catch (NullPointerException e) {
			logger.error("NullPointerException: " + e.getMessage(), e);
		}
	}
	
	static void send(Email email) {
		send(email.getSender(), email.getRecipient(), email.getSubject(), email.getText());
	}

	public static boolean isValid(String email) {
		
		if (StringUtils.isNotBlank(email)) {
			try {
				new InternetAddress(email).validate();
				return true;
			} catch (AddressException e) {
				logger.error("AddressException: " + e.getMessage() + "; Entered 'email address': '" + email + "' is not valid"); 
			}
		}
		
		return false;
	}
}
