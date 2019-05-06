package com.neu.edu.hms.services.helper;

import java.util.LinkedList;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;

public class EmailMessage {

    private String fromUser;
    private LinkedList<String> toUser;
    private String subjectOfEmail;
    private String dataOfEmail; 
    private String mimeType; 
    private LinkedList<String> cc; 
    private LinkedList<String> bcc; 

    private EmailMessage(EmailBuilder emailBuilder){

	this.fromUser = emailBuilder.fromUser;
	this.toUser = emailBuilder.toUser;
	this.subjectOfEmail = emailBuilder.subjectOfEmail;
	this.dataOfEmail = emailBuilder.dataOfEmail;
	this.mimeType = emailBuilder.mimeType;
	this.cc = emailBuilder.cc ;
	this.bcc = emailBuilder.bcc ;

    }

    public String getFrom(){
	return fromUser;
    }

    public LinkedList<String> getTo(){
	return this.toUser;
    }

    public String getSubject(){
	return subjectOfEmail;
    }

    public String getContent(){
	return dataOfEmail;
    }

    public String getMimeType(){
	return fromUser;
    }

    public LinkedList<String> getCc(){
	return cc;
    }

    public LinkedList<String> getBcc(){
	return bcc;
    }

    public static class EmailBuilder {

	private String fromUser;
	private LinkedList<String> toUser;
	private String subjectOfEmail;
	private String dataOfEmail;
	private String mimeType; 
	private LinkedList<String> cc;
	private LinkedList<String> bcc;

	public EmailBuilder(String from, LinkedList<String> to) {
	    this.fromUser = from;
	    this.toUser = to;  

	}

	public EmailBuilder addSubject(String subject){
	    this.subjectOfEmail = subject;
	    return this;
	}

	public EmailBuilder addContent (String content){
	    this.dataOfEmail = content;
	    return this;
	}

	public EmailBuilder addMimeType (String mimeType){
	    this.mimeType = mimeType;
	    return this;
	}

	public EmailBuilder addCc (LinkedList<String> cc){
	    this.cc = cc;
	    return this;
	}

	public EmailBuilder addBcc (LinkedList<String> bcc){
	    this.bcc = bcc;
	    return this;
	}

	public EmailMessage build() {
	    EmailMessage emailMessage = new EmailMessage(this);
	    return emailMessage;
	}
    }

    public void send(String pwd, String host, int port) throws MessagingException {
	Authenticator auth = new myAuthenticator(this.getFrom(), pwd);
	Properties props = new Properties();
	props.put("mail.transport.protocol", "smtps"); 
	props.put("mail.smtps.auth", "true");

	Session mailSession = Session.getDefaultInstance(props,auth); 
	mailSession.setDebug(true);

	MimeMessage message = new MimeMessage(mailSession);
	message.setFrom(this.getFrom());
	message.setSubject(this.getSubject());
	message.setContent(this.getContent(), "text/plain; charset=ISO-8859-2");
	
	if(this.getTo().size()>0){
	    message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.getTo().get(0)));
	}

	if(this.getCc() != null && this.getCc().size() >0){
	    for(int i=0;i<this.getCc().size();i++)
		message.addRecipient(Message.RecipientType.CC, new InternetAddress(this.getCc().get(i)));
	}

	if(this.getBcc() != null && this.getBcc().size()>0){
	    for(int i=0;i<this.getBcc().size();i++)
		message.addRecipient(Message.RecipientType.BCC, new InternetAddress(this.getBcc().get(i)));
	}

	Transport transport = mailSession.getTransport();
	transport.connect(host, port, this.getFrom(), pwd);


	transport.sendMessage(message, message
		.getRecipients(Message.RecipientType.TO));
	transport.close();
    }
}

class myAuthenticator extends Authenticator {
    String username;
    String password;
    public myAuthenticator(String username,String password){
	this.username = username;
	this.password = password;
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
	return new PasswordAuthentication(username, password);
    }

}



