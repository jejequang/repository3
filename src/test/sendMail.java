package test;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
* @author wjq
* @version 创建时间：2018年1月3日 下午2:59:48
* 类描述
*/
public class sendMail {

	public static void main(String[] args) throws MessagingException, IOException {
		
		final String email = "nu@newunion.cn";
		final String password = "Smtp@Nu896";
		// 邮件主题
	    String subject = "新联在线2018年XX月XX日新手号码报备名单";
	    // 文件存放路径
	    String filePath ="D:\\xxx.xls";
	    //1. 用于存放 SMTP 服务器地址等参数
	    Properties properties = new Properties();
	    // 主机地址
	    properties.setProperty("mail.smtp.host", "smtp.exmail.qq.com");
	    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";  
	    // 邮件协议
	    properties.setProperty("mail.transport.protocol", "smtp");
	    // 认证
	    properties.setProperty("mail.smtp.auth", "true");
	    // 端口
	    properties.setProperty("mail.smtp.port", "465");
	    
	    properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	    properties.setProperty("mail.smtp.socketFactory.fallback", "false");
	    // 使用JavaMail发送邮件的5个步骤
	    // 2. 创建session
	    Session session = Session.getDefaultInstance(properties, new Authenticator() {
	    	
	    	@Override
	    	protected PasswordAuthentication getPasswordAuthentication() {
	    		 return new PasswordAuthentication(email, password);
	    	}
	    	
		});
	    // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
	    session.setDebug(true);

	    // 3. 创建邮件
	    // 创建邮件对象
	    MimeMessage message = new MimeMessage(session);

	    // 邮件的标题
	    message.setSubject(subject);
	    // 邮件发送日期
	    message.setSentDate(new Date());
	    // 指明邮件的发件人
	    message.setFrom(new InternetAddress("nu@newunion.cn"));

	    // 指明邮件的收件人
	    message.setRecipient(Message.RecipientType.TO, new InternetAddress("418169745@qq.com", "客服"));

	    // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
	    Multipart multipart = new MimeMultipart();

	    // 添加邮件正文
	    BodyPart contentBodyPart = new MimeBodyPart();
	    // 邮件内容
	    String result = "<div>"
	    		+ "<div>客服你好！</div>"
	    		+ "<div>附件是本司2018年XX月XX日新手号码报备名单，请查收并及时向运营商报备！</div>"
	    		+ "<div>本邮件为系统自动发送，请勿回复！</div>"
	    		+ "<div>如有疑问请联系我司对接人员，谢谢~</div>"
	    		+ "</div>";
	    contentBodyPart.setContent(result, "text/html;charset=UTF-8");
	    multipart.addBodyPart(contentBodyPart);


	    // 添加附件
	    if (filePath != null && !"".equals(filePath)) {
	        BodyPart attachmentBodyPart = new MimeBodyPart();
	        // 根据附件路径获取文件,
	        FileDataSource dataSource = new FileDataSource(filePath);
	        attachmentBodyPart.setDataHandler(new DataHandler(dataSource));
	        //MimeUtility.encodeWord可以避免文件名乱码
	        attachmentBodyPart.setFileName(MimeUtility.encodeWord(dataSource.getFile().getName()));
	        multipart.addBodyPart(attachmentBodyPart);
	    }
	    // 邮件的文本内容
	    message.setContent(multipart);

	    // 4. 发送邮件,Transport每次发送成功程序帮忙关闭
	    Transport.send(message, message.getAllRecipients());
	     
	}
}
