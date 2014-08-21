package com.xxjwd.classes;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class RegisterInfo implements KvmSerializable {
	
	int workno;
	String mobile;
	String ucode;
	String rcode;
	String sq;
	String sa;
	String email;
	
	public int getWorkno() {
		return workno;
	}

	public void setWorkno(int workno) {
		this.workno = workno;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUcode() {
		return ucode;
	}

	public void setUcode(String ucode) {
		this.ucode = ucode;
	}

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	public String getSecurityQuestion() {
		return sq;
	}

	public void setSecurityQuestion(String sq) {
		this.sq = sq;
	}

	public String getSecurityAnswer() {
		return sa;
	}

	public void setSecurityAnswer(String sa) {
		this.sa = sa;
	}

	public String getEmailAddress() {
		return email;
	}

	public void setEmailAddress(String email) {
		this.email = email;
	}

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
		   arg2.name = "WorkNo";
		   break;
		  case 1:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "Mobile";
		   break;
		  case 2:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "UniqueCode";
			   break;
		  case 3:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "RegisterCode";
			   break;
		  case 4:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "SecurityQuestion";
			   break;
		  case 5:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "SecurityAnswer";
			   break;
		  case 6:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "EmailAddress";
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
		    this.workno = Integer.valueOf(arg1.toString());
		    break;
		   case 1:
		    this.mobile = arg1.toString();
		    break;
		   case 2:
			    this.ucode = arg1.toString();
			    break;
		   case 3:
			    this.rcode = arg1.toString();
			    break;
		   case 4:
			    this.sq = arg1.toString();
			    break;
		   case 5:
			    this.sa = arg1.toString();
			    break;
		   case 6:
			    this.email = arg1.toString();
			    break;
		
		   default:
		    break;
		  }
	}

}
