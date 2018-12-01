package com.chenway.springbootjavamail.pojo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.List;
import java.util.Properties;

public class MailSender {
    private static MailEntity mailEntity = new MailEntity();

    public MailSender title(String title){
        mailEntity.setTitle(title);
        return this;
    }

    public MailSender content(String content){
        mailEntity.setContent(content);
        return this;
    }

    public MailSender contentType(MailContentTypeEnum typeEnum){
        mailEntity.setContent(typeEnum.getValue());
        return this;
    }

    public MailSender targets(List<String> targets){
        mailEntity.setList(targets);
        return this;
    }

    public void send()throws Exception{
        if(mailEntity.getContentType()==null){
            mailEntity.setContentType(MailContentTypeEnum.HTML.getValue());
        }
        if(mailEntity.getTitle()==null||mailEntity.getTitle().trim().length()==0){
            throw  new Exception("邮件标题没有设置，调用title方法设置");
        }
        if(mailEntity.getContent()==null||mailEntity.getContent().trim().length()==0){
            throw  new Exception("邮件内容没有设置，调用content方法设置");
        }
        if(mailEntity.getList().size()==0){
            throw  new Exception("没有接受者邮箱地址，调用targets方法设置");
        }

        final PropertiesUtil propertiesUtil = new PropertiesUtil("mail");
        final Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", propertiesUtil.getValue("mail.smtp.service"));
        properties.put("mail.smtp.port", propertiesUtil.getValue("mail.smtp.port"));
        properties.put("mail.user", propertiesUtil.getValue("mail.from.address"));
        properties.put("mail.password", propertiesUtil.getValue("mail.from.smtp.pwd"));

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String userName = properties.getProperty("mail.user");
                String password = properties.getProperty("mail.password");
                System.out.println(userName+password);
                return new PasswordAuthentication(userName,password);
            }
        };

        Session mailSession = Session.getInstance(properties, authenticator);
        MimeMessage mimeMessage = new MimeMessage(mailSession);
        String nickName = MimeUtility.encodeText(propertiesUtil.getValue("mail.from.nickname"));
        InternetAddress from = new InternetAddress(nickName + "<" + properties.getProperty("mail.user") + ">");
        mimeMessage.setFrom(from);

        mimeMessage.setSubject(mailEntity.getTitle());
        if(mailEntity.getContentType().equals(MailContentTypeEnum.HTML.getValue())){
            mimeMessage.setContent(mailEntity.getContent(),mailEntity.getContentType());
        }else if(mailEntity.getContentType().equals(MailContentTypeEnum.TEXT.getValue())){
            mimeMessage.setText(mailEntity.getContent());
        }
        List<String> targets = mailEntity.getList();
        for(int i=0;i<targets.size();i++){
            try {
                InternetAddress to = new InternetAddress(targets.get(i));
                mimeMessage.setRecipient(Message.RecipientType.TO, to);
                Transport.send(mimeMessage);
            }catch (Exception e){
               continue;
            }
        }
    }
}
