package com.xxjwd.classes;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class ZbPerson implements KvmSerializable {
	
	String workno;
	String name;
	String dept;
	String date;
	String dayNight;
	String mobile;
	String phone;
	String signTime;
	public void setWorkno(String value){
		this.workno = value;
	}
	public String getWorkno(){
		return this.workno;
	}
	public void setName(String value){
		this.name = value;
	}
	public String getName(){
		return this.name;
	}
	public void setDept(String value){
		this.dept = value;
	}
	public String getDept(){
		return this.dept;
	}
	public void setDate(String value){
		this.date = value;
	}
	public String getDate(){
		return this.date;
	}
	public void setDayNight(String value){
		this.dayNight = value;
	}
	public String getDayNight(){
		return this.dayNight;
	}
	public void setMobile(String value){
		this.mobile = value;
	}
	public String getMobile(){
		return this.mobile;
	}
	public void setPhone(String value){
		this.phone = value;
	}
	public String getPhone(){
		return this.phone;
	}
	public void setSignTime(String value){
		this.signTime = value;
	}
	public String getSignTime(){
		return this.signTime;
	}
	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public void getPropertyInfo(int arg0, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		switch(arg0){
		  case 0:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "Workno";
		   break;
		  case 1:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "Name";
		   break;
		  case 2:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Dept";
			   break;
		  case 3:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Date";
			   break;
		  case 4:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "DayNight";
			   break;
		  case 5:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Mobile";
			   break;
		  case 6:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Phone";
			   break;
		  case 7:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "signTime";
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
		    this.workno = arg1.toString();
		    break;
		   case 1:
		    this.name = arg1.toString();
		    break;
		   case 2:
			    this.dept = arg1.toString();
			    break;
		   case 3:
			    this.date = arg1.toString();
			    break;
		   case 4:
			    this.dayNight = arg1.toString();
			    break;
		   case 5:
			    this.mobile = arg1.toString();
			    break;
		   case 6:
			    this.phone = arg1.toString();
			    break;
		   case 7:
			    this.signTime = arg1.toString();
			    break;
		   default:
		    break;
		  }
	}

}
