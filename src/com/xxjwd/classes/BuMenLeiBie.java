package com.xxjwd.classes;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class BuMenLeiBie implements KvmSerializable {
	int id;
	String lbmc;
	public void setID(int value){
		this.id = value;
	}

	public int getID(){
		return this.id;
	}
	public String getLbmc(){
		return this.lbmc;
	}
	public void setLbmc(String value)
	{
		this.lbmc = value;
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
		   arg2.name = "ID";
		   break;
		  case 1:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "Lbmc";
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
		    this.id = Integer.valueOf(arg1.toString());
		    break;
		   case 1:
		    this.lbmc = arg1.toString();
		    break;
		   default:
		    break;
		  }
	}

}
