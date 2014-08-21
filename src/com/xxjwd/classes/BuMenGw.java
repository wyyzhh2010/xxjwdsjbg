package com.xxjwd.classes;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class BuMenGw implements KvmSerializable{

	int bmbh;
	int bmlb;
	int bmsqbh;
	String bmmc;
	String bmlbmc;
	public void setBmbh(int value){
		this.bmbh = value;
	}
	public void setBmlb(int value){
		this.bmlb = value;
	}
	public void setBmsqbh(int value){
		this.bmsqbh = value;
	}
	public void setBmmc(String value){
		this.bmmc = value;
	}
	public void setBmlbmc(String value){
		this.bmlbmc = value;
	}
	
	public int getBmbh(){
		return this.bmbh;
	}
	public int getBmlb(){
		return this.bmlb;
	}
	public int getBmsqbh(){
		return this.bmsqbh;
	}
	public String getBmmc(){
		return this.bmmc;
	}
	public String getBmlbmc(){
		return this.bmlbmc;
	}
	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public void getPropertyInfo(int arg0, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		switch(arg0){
		  case 0:
		   arg2.type = PropertyInfo.INTEGER_CLASS;
		   arg2.name = "Bmbh";
		   break;
		  case 1:
		   arg2.type = PropertyInfo.INTEGER_CLASS;
		   arg2.name = "Bmlb";
		   break;
		  case 2:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Bmmc";
			   break;
		  case 3:
			   arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "Bmsqbh";
			   break;
		  case 4:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Bmlbmc";
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
		    this.bmbh = Integer.valueOf(arg1.toString());
		    break;
		   case 1:
		    this.bmlb = Integer.valueOf(arg1.toString());
		    break;
		   case 2:
			    this.bmmc = arg1.toString();
			    break;
		   case 3:
			    this.bmsqbh = Integer.valueOf(arg1.toString());
			    break;
		   case 4:
			    this.bmlbmc = arg1.toString();
			    break;
		  
		   default:
		    break;
		  }
	}

}
