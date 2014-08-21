package com.xxjwd.classes;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class User  implements KvmSerializable {

	private int uid;
	private String userName;
	private String userNo;
	private int userDept;
	public int getUid()
	 {
		 return this.uid;
	 }
	public String getUserName()
	 {
		 return this.userName;
	 }
	public String getUserNo()
	 {
		 return this.userNo;
	 }

	public int getUserDept()
	{
		return this.userDept;
	}
	public void setUid(int val)
	{
		this.uid = val;
	}

	public void setUserDept(int val)
	{
		this.userDept = val;
	}
	
	public void setUserName(String val)
	{
		this.userName = val;
	}
	
	public void setUserNo(String val)
	{
		this.userNo = val;
	}
	
	 @Override
	 public Object getProperty(int arg0) {
	  // TODO Auto-generated method stub
	
	  return null;
	 }
	 @Override
	 public int getPropertyCount() {
	  // TODO Auto-generated method stub
	  return 4;
	 }
	 @Override
	 public void getPropertyInfo(int arg0, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo arg2) {
	  // TODO Auto-generated method stub
	  switch(arg0){
	  case 0:
	   arg2.type = PropertyInfo.INTEGER_CLASS;
	   arg2.name = "Uid";
	   break;
	  case 1:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "UserName";
		   break;
	  case 2:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "UserNo";
		   break;
	  case 3:
		   arg2.type = PropertyInfo.INTEGER_CLASS;
		   arg2.name = "UserDept";
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
	    this.uid = Integer.valueOf(arg1.toString());
	    break;
	   case 1:
		    this.userName = arg1.toString();
		    break;
	   case 2:
		    this.userNo = arg1.toString();
		    break;
	   case 3:
		    this.userDept = Integer.valueOf(arg1.toString());
		    break;
	   default:
	    break;
	  }
	 }
	}