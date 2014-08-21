package com.xxjwd.classes;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class BOOLEAN implements KvmSerializable {
	
	boolean isTure;
	String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public BOOLEAN(boolean b) {
		// TODO Auto-generated constructor stub
		this.isTure = b;
	}
	public BOOLEAN()
	{
		this.isTure = false;
	}
	public boolean isTure() {
		return isTure;
	}
	public void setTure(boolean isTure) {
		this.isTure = isTure;
	}
	
	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void getPropertyInfo(int arg0, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		switch(arg0){
		  case 0:
		   arg2.type = PropertyInfo.BOOLEAN_CLASS;
		   arg2.name = "IsTrue";
		   break;
		  case 1:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Message";
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
		    this.isTure = Boolean.valueOf(arg1.toString());
		    break;
		   case 1:
			    this.message =arg1.toString();
			    break;
		   default:
		    break;
		  }
	}

}
