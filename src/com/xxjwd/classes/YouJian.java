package com.xxjwd.classes;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class YouJian implements KvmSerializable,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1361211712565087283L;
	private int size;
	private String subject;
	private String body;
	private String date;
	private String messageID;
	private String uID;
	private boolean isBodyHtml;
	private String to;
	private String cc;
	private String bcc;
	private String replyTo;
	private String from;
	private String sender;
	private int importance;
	
	
	
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMessageID() {
		return messageID;
	}
	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public boolean isBodyHtml() {
		return isBodyHtml;
	}
	public void setBodyHtml(boolean isBodyHtml) {
		this.isBodyHtml = isBodyHtml;
	}
	public YouJianDiZhi[] getTo() {
		return to;
	}
	public void setTo(YouJianDiZhi[] to) {
		this.to = to;
	}
	public YouJianDiZhi[] getCc() {
		return cc;
	}
	public void setCc(YouJianDiZhi[] cc) {
		this.cc = cc;
	}
	public YouJianDiZhi[] getBcc() {
		return bcc;
	}
	public void setBcc(YouJianDiZhi[] bcc) {
		this.bcc = bcc;
	}
	public YouJianDiZhi[] getReplyTo() {
		return replyTo;
	}
	public void setReplyTo(YouJianDiZhi[] replyTo) {
		this.replyTo = replyTo;
	}
	public YouJianDiZhi getFrom() {
		return from;
	}
	public void setFrom(YouJianDiZhi from) {
		this.from = from;
	}
	public YouJianDiZhi getSender() {
		return sender;
	}
	public void setSender(YouJianDiZhi sender) {
		this.sender = sender;
	}
	public int getImportance() {
		return importance;
	}
	public void setImportance(int importance) {
		this.importance = importance;
	}
	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 14;
	}
	@Override
	public void getPropertyInfo(int arg0, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		switch(arg0){
		  case 0:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "Name";
		   break;
		  case 1:
		   arg2.type = PropertyInfo.INTEGER_CLASS;
		   arg2.name = "NumNewMsg";
		   break;
		  case 2:
			   arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "NumMsg";
			   break;
		  case 3:
			   arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "NumUnSeen";
			   break;
		  case 4:
			   arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "UIDValidity";
			   break;
		  case 5:
			   arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "UIDNext";
			   break;
		  default:
		   break;
		}
	}
	@Override
	public void setProperty(int arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(arg1 == null) return;
		  switch(arg0){
		   case 0:
		    this.body = arg1.toString();
		    break;
		   case 1:
		    this.importance = Integer.valueOf(arg1.toString());
		    break;
		  
		   default:
		    break;
		  }
	}
	
}
