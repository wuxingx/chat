package com.wuxing.chat.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发送邮件的工具类:
 * @author admin
 *
 */
public class MailUtils {

	/*public static void sendMail(String to,String code){
		
		try {
			// 获得连接:
			Properties props = new Properties(new InputStream);
			*//*Session session = Session.getInstance(props, new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("Admin@uxi.com", "123465");
				}

			});*//*

			props.put("username", "nihao52545658@163.com");
			props.put("password", "ZHOU19941203xin");
			props.put("mail.transport.protocol", "smtp" );
			props.put("mail.smtp.host", "smtp.163.com");
			props.put("mail.smtp.port", "465" );

			Session defaultInstance = Session.getDefaultInstance(props);
			// 构建邮件:
			Message message = new MimeMessage(defaultInstance);

			message.setFrom(new InternetAddress("nihao52545658@163.com"));
			// 设置收件人:

			// TO:收件人   CC:抄送   BCC:暗送,密送.
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 主题:
			message.setSubject("来自黑马官方商城的激活邮件!");
			// 正文:
			message.setContent("<h1>来自购物天堂UXI的激活邮件:请点击下面链接激活!</h1><h3>25端口稳得一笔</a></h3>", "text/html;charset=UTF-8");
			// 发送邮件:
			message.saveChanges();

			Transport transport = defaultInstance.getTransport("smtp");
			transport.connect(props.getProperty("mail.smtp.host"),props.getProperty("username"),props.getProperty("password"));
			transport.sendMessage(message,message.getAllRecipients());
			transport.close();
			//Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}*/


	public static void sendMail(String to,String code) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.163.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("nihao52545658@163.com","ZHOU19941203xin");
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("nihao52545658@163.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("wuxing聊天室激活邮件");
			// 正文:
			message.setContent("<h1>请点击下面链接激活!</h1><h3><a href='http://localhost:8080/talkr/UserController/active?code=" + code + "'>http://127.0.0.1:8080/talkr/UserController/active?code=" + code + "</a></h3>", "text/html;charset=UTF-8");
			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	/*public static void sendMail(String to,String code) {

		final String username = "nihao52545658@163.com";
		final String password = "ZHOU19941203xin";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.163.com");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("nihao52545658@163.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
					+ "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}*/
}
