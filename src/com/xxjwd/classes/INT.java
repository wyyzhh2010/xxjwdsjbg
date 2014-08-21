package com.xxjwd.classes;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class INT implements KvmSerializable {
	
	int value;
	String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public INT(int i) {
		// TODO Auto-generated constructor stub
		this.value = i;
	}
	public INT()
	{
		this.value = -1;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
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
		   arg2.type = PropertyInfo.INTEGER_CLASS;
		   arg2.name = "Number";
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
		    this.value = Integer.valueOf(arg1.toString());
		    break;
		   case 1:
			    this.message =arg1.toString();
			    break;
		   default:
		    break;
		  }
	}

}
