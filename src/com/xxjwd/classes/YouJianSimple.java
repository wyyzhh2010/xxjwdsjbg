package com.xxjwd.classes;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import com.zcj.util.StringUtil;

public class YouJianSimple implements KvmSerializable,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1361211712565087283L;
	private int size;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public boolean isHasAttachements() {
		return hasAttachements;
	}
	public void setHasAttachements(boolean hasAttachements) {
		this.hasAttachements = hasAttachements;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public int getImportance() {
		return importance;
	}
	public void setImportance(int importance) {
		this.importance = importance;
	}
	private String subject;
	private String date;
	private String uID;
	private boolean hasAttachements;
	private String from;
	private int importance;
	
	
	
	
	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 7;
	}
	@Override
	public void getPropertyInfo(int arg0, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		switch(arg0){
		case 0:
			arg2.type = PropertyInfo.INTEGER_CLASS;
			arg2.name = "Size";
			break;
		case 1:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "Subject";
			break;
		case 2:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "Date";
			break;
		case 3:
			arg2.type = PropertyInfo.BOOLEAN_CLASS;
			arg2.name = "HasAttachements";
			break;
		case 4:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "From";
			break;
		case 5:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "Uid";
			break;
		case 6:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "Importance";
			break;

		default:
			break;
		}
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		// TODO Auto-generated method stub
		if (arg1 == null)
			return;
		switch (arg0) {
		case 0:
			this.size = Integer.valueOf(arg1.toString());
			break;
		case 1:
			this.subject = arg1.toString();
			break;
		case 2:
			this.date = arg1.toString();
			break;
		case 3:
			this.hasAttachements =  Boolean.parseBoolean(arg1.toString());
			break;
		case 4:
			this.from = arg1.toString();
			break;
		case 5:
			this.uID = arg1.toString();
			break;
		case 6:
			this.importance = Integer.valueOf(arg1.toString());
			break;

		default:
			break;
		}
	}
	
}
