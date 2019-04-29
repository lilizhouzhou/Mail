/**  
 * Project Name:verificate
 * File Name:ImgMail.java
 * Package Name:com.qyrj.main
 * Date:2018年11月2日下午2:18:50
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *  
*/

package com.qyrj.util;

/**  
 * ClassName:ImgMail
 * Description: TODO
 * Date:     2018年11月2日 下午2:18:50
 * @Author   Lizhou
 * @Version  1.0
 * @Since    JDK 1.8
 */
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
	
	/**
	 * 
	 * @MethodName makeCode
	 * @Author Lizhou
	 * @param codeLength 生成验证码的长度，默认为4位
	 * @return  
	 * @Since JDK 1.8
	 */
	public static String makeCode(Integer codeLength) {
		char[] chNumber = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		// 随机生成数
		Random random = new Random();
		// 生成的验证码
		String code = "";
		if(codeLength==null || codeLength.equals(null) || codeLength==0) {
			codeLength=4;
		}
		// 循环六次，生成六位验证码信息
		for (int i = 0; i < codeLength; i++) {
			char num = chNumber[random.nextInt(chNumber.length)];
			code += num;
		}
		return code;
	}

	/**
	 * 
	 * @MethodName sendMail
	 * @Author Lizhou
	 * @param from      发件人
	 * @param fromCode  发件人授权码
	 * @param to        收件人
	 * @param mailTitle 邮件标题
	 * @param content   邮件内容
	 * @return 发送成功 返回true
	 * @Since JDK 1.8
	 */
	public static boolean sendMail(String from, String fromCode, String to, String mailTitle, String content) {
		Properties properties = new Properties();
		// 连接协议
		properties.put("mail.transport.protocol", "smtp");
		// 主机名
		properties.put("mail.smtp.host", "smtp.qq.com");
		// 端口号
		properties.put("mail.smtp.port", 465);
		properties.put("mail.smtp.auth", "true");
		// 设置是否使用ssl安全连接 ,一般都使用
		properties.put("mail.smtp.ssl.enable", "true");
		// 设置是否显示debug信息 true 会在控制台显示相关信息
		properties.put("mail.debug", "true");
		// 得到会话对象
		Session session = Session.getInstance(properties);
		// 获取邮件对象
		Message message = new MimeMessage(session);
		boolean flag = false;
		try {
			// 设置发件人邮箱地址
			message.setFrom(new InternetAddress(from));
			// 设置收件人邮箱地址，一个收件人
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			//多个收件人
			//message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("xxx@qq.com"),new InternetAddress("xxx@qq.com"),new InternetAddress("xxx@qq.com")});
			// 设置邮件标题
			message.setSubject(mailTitle);
			// 设置邮件内容
			message.setText(content);
			// 得到邮差对象
			Transport transport = session.getTransport();
			// 连接自己的邮箱账户,密码为QQ邮箱开通的stmp服务后得到的客户端授权码
			transport.connect(from, fromCode);
			// 发送邮件
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			flag = true;
			return flag;
		} catch (MessagingException e) {
			e.printStackTrace();
			return flag;
		}
	}
}