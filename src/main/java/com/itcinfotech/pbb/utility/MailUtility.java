package com.itcinfotech.pbb.utility;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MailUtility {

	static final Logger logger = LogManager.getLogger(MailUtility.class.getName());
	
	@Value("${mail.smtp.from}")
	private String FROM_MAIL_ID;
	
	@Value("${mail.smtp.host}")
	private String SMTP_SERVER_HOST;
	@Value("${mail.smtp.port}")
	private String SMTP_SERVER_PORT;
	
	/*@Value("${pbb.mail.password}")
	private String FROM_MAIL_PASSWORD;

	@Value("${pbb.mail.smtp.auth}")
	private String pbbMailSmtpAuth;

	@Value("${pbb.mail.smtp.starttls.enable}")
	private String pbbMailSmtpStarttlsEnable;

	@Value("${pbb.mail.smtp.server.host.props}")
	private String SMTP_SERVER_HOST_PROPS;*/
	
	
	

	public boolean sendPasswordResetMailToUser(String mailId, String url) {

//		final String username = FROM_MAIL_ID;
//		final String password = FROM_MAIL_PASSWORD;

		Properties props = new Properties();
		props.put("mail.smtp.from", FROM_MAIL_ID);
		props.put("mail.smtp.host", SMTP_SERVER_HOST);
		props.put("mail.smtp.port", SMTP_SERVER_PORT);

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			/*protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}*/
		});

		try {

			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress(FROM_MAIL_ID));
			//msg.setFrom(new InternetAddress(FROM_MAIL_ID, "NoReply-JD"));
			//msg.setReplyTo(InternetAddress.parse(mailId));
			msg.setSubject("Automatic Password Change Alert Mail", "UTF-8");
			Object object = new Object();
			object = "Hai Sir, Please use this link,  <html>\n" + "<body>\n" + "\n" + "<a href=\"" + url + "\">\n"
					+ "for create new password</a>\n" + "\n" + "</body>\n" + "</html>";
			msg.setContent(object, "text/html");
			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailId));
			logger.info("Message is ready");
			Transport.send(msg);
			logger.info("EMail Sent Successfully!!");
			return true;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
}
