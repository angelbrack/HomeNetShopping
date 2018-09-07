/**
 * <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a>
 * plugin, Copyright (c) 2017 Chen Chao.
 **/
package prjframework.common.util;

import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.servlet.view.velocity.VelocityConfig;

import egovframework.rte.fdl.property.EgovPropertyService;
import prjframework.common.util.MailVO;

@SuppressWarnings("deprecation")
@Component("mailSend")
public class MailSend {

	protected Log log = LogFactory.getLog(this.getClass());

	@Resource(name = "fileUploadProperties")
	Properties fileUploadProperties;

	@Resource(name = "configPropService")
	protected EgovPropertyService configPropService;

	@Resource(name = "velocityConfig")
	private VelocityConfig velocityConfig;

	public String sendMail(MailVO mailVO) throws MessagingException, Exception {
		String retrunMsg = "";
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.host", configPropService.getString("MAIL.SMTP.HOST"));
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "25");
		prop.put("mail.smtp.socketFactory.port", "25");
		prop.put("mail.debug", "true");
		prop.put("mail.smtp.socketFactory.fallback", "false");

		try {
			Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(configPropService.getString("MAIL.SMTP.ID"), configPropService.getString("MAIL.SMTP.PWD"));
				}
			});
			session.setDebug(true); // for debug

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(mailVO.getMailFrom()));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailVO.getMailTo()));

			if((mailVO.getMailCc() != null) && (mailVO.getMailCc().length() > 0)) {
				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(mailVO.getMailCc()));
			}

			message.setSubject(mailVO.getMailSubject(), "utf-8");

			String mailText = VelocityEngineUtils.mergeTemplateIntoString(velocityConfig.getVelocityEngine(), fileUploadProperties.getProperty("COMMON.MAIL.PATH") + mailVO.getTemplateName(), "UTF-8", mailVO.getTextParams());
			
			mailVO.setMailContent(mailText);
			
			log.debug("mailFrom:" + mailVO.getMailFrom());
			log.debug("mailTo:" + mailVO.getMailTo());
			log.debug("mailSubject:" + mailVO.getMailSubject());
			log.debug("mailText:" + mailText);
			message.setHeader("Content-Type", "text/html; charset=UTF-8");
			message.setContent(mailText, "text/html; charset=utf-8");
			message.setSentDate(new Date());

			mailVO.setEmailSendDttm(DateUtil.getCurrentDateTime());
			
			Transport.send(message);
			
			retrunMsg = "";
		} catch(MessagingException me) {
			log.debug("sendMail error");
			//retrunMsg = me.toString();
			retrunMsg = "sendMail error";
		} catch(Exception me) {
			log.debug("sendMail error");
			//retrunMsg = me.toString();
			retrunMsg = "sendMail error";
		}
		return retrunMsg;
		
	}
}