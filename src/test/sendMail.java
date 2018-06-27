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
* @version ����ʱ�䣺2018��1��3�� ����2:59:48
* ������
*/
public class sendMail {

	public static void main(String[] args) throws MessagingException, IOException {
		
		final String email = "nu@newunion.cn";
		final String password = "Smtp@Nu896";
		// �ʼ�����
	    String subject = "��������2018��XX��XX�����ֺ��뱨������";
	    // �ļ����·��
	    String filePath ="D:\\xxx.xls";
	    //1. ���ڴ�� SMTP ��������ַ�Ȳ���
	    Properties properties = new Properties();
	    // ������ַ
	    properties.setProperty("mail.smtp.host", "smtp.exmail.qq.com");
	    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";  
	    // �ʼ�Э��
	    properties.setProperty("mail.transport.protocol", "smtp");
	    // ��֤
	    properties.setProperty("mail.smtp.auth", "true");
	    // �˿�
	    properties.setProperty("mail.smtp.port", "465");
	    
	    properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	    properties.setProperty("mail.smtp.socketFactory.fallback", "false");
	    // ʹ��JavaMail�����ʼ���5������
	    // 2. ����session
	    Session session = Session.getDefaultInstance(properties, new Authenticator() {
	    	
	    	@Override
	    	protected PasswordAuthentication getPasswordAuthentication() {
	    		 return new PasswordAuthentication(email, password);
	    	}
	    	
		});
	    // ����Session��debugģʽ�������Ϳ��Բ鿴��������Email������״̬
	    session.setDebug(true);

	    // 3. �����ʼ�
	    // �����ʼ�����
	    MimeMessage message = new MimeMessage(session);

	    // �ʼ��ı���
	    message.setSubject(subject);
	    // �ʼ���������
	    message.setSentDate(new Date());
	    // ָ���ʼ��ķ�����
	    message.setFrom(new InternetAddress("nu@newunion.cn"));

	    // ָ���ʼ����ռ���
	    message.setRecipient(Message.RecipientType.TO, new InternetAddress("418169745@qq.com", "�ͷ�"));

	    // ��multipart����������ʼ��ĸ����������ݣ������ı����ݺ͸���
	    Multipart multipart = new MimeMultipart();

	    // ����ʼ�����
	    BodyPart contentBodyPart = new MimeBodyPart();
	    // �ʼ�����
	    String result = "<div>"
	    		+ "<div>�ͷ���ã�</div>"
	    		+ "<div>�����Ǳ�˾2018��XX��XX�����ֺ��뱨������������ղ���ʱ����Ӫ�̱�����</div>"
	    		+ "<div>���ʼ�Ϊϵͳ�Զ����ͣ�����ظ���</div>"
	    		+ "<div>������������ϵ��˾�Խ���Ա��лл~</div>"
	    		+ "</div>";
	    contentBodyPart.setContent(result, "text/html;charset=UTF-8");
	    multipart.addBodyPart(contentBodyPart);


	    // ��Ӹ���
	    if (filePath != null && !"".equals(filePath)) {
	        BodyPart attachmentBodyPart = new MimeBodyPart();
	        // ���ݸ���·����ȡ�ļ�,
	        FileDataSource dataSource = new FileDataSource(filePath);
	        attachmentBodyPart.setDataHandler(new DataHandler(dataSource));
	        //MimeUtility.encodeWord���Ա����ļ�������
	        attachmentBodyPart.setFileName(MimeUtility.encodeWord(dataSource.getFile().getName()));
	        multipart.addBodyPart(attachmentBodyPart);
	    }
	    // �ʼ����ı�����
	    message.setContent(multipart);

	    // 4. �����ʼ�,Transportÿ�η��ͳɹ������æ�ر�
	    Transport.send(message, message.getAllRecipients());
	     
	}
}
