package com.xxjwd.classes;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class YouJianSend implements KvmSerializable {
	
	String subject;
	String body;

	
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

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public void getPropertyInfo(int arg0, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		switch(arg0){
		  case 0:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "Subject";
		   break;
		  case 1:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "Body";
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
		    this.subject = arg1.toString();
		    break;
		   case 1:
		    this.body = arg1.toString();
		    break;

		
		   default:
		    break;
		  }
	}

}
