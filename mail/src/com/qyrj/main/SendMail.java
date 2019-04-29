package com.qyrj.main;

import com.qyrj.util.MailUtil;

public class SendMail {
	public static void main(String[] args) {
		String from = "**********@qq.com";
		String fromCode = "**********";
		String to = "**********@qq.com";
		String mailTitle = "注册验证邮件";
		String code = MailUtil.makeCode(6);
		String content = "这是一封注册验证邮箱，您的验证码为：" + code + "，登录地址为:http://www.baidu.com，如非本人操作，请忽略此邮件！";
		boolean status = MailUtil.sendMail(from, fromCode, to, mailTitle, content);
		String statusMsg=status==true? "发送成功" : "发送失败";
		System.out.println("发送状态："+statusMsg+"，验证码为："+code);
	}
}