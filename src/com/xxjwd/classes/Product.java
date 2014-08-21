package com.xxjwd.classes;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Product implements KvmSerializable {

	private int pid;
	private String pname;
	private String servicePage;
	
	public Product(){
		this.pid = 0;
		this.pname = "base";
		this.servicePage = "baseService.asmx";
	}
	
	public Product(int pid)
	{
		if (pid == 0)
		{
			this.pid = 0;
			this.pname = "base";
			this.servicePage = "baseService.asmx";
		}
		else	
		{
			this.pid = pid;
		}
	}
	
	public int getPid()
	{
		return this.pid;
	}
	public String getPname()
	{
		return this.pname;
	}
	public String getServicePage()
	{
		return this.servicePage;
	}
	public void setPid(int pid)
	{
		this.pid = pid;
	}
	public void setPname(String pname)
	{
		this.pname = pname;
	}
	public void setServicePage(String servicePage)
	{
		this.servicePage = servicePage;
	}
	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void getPropertyInfo(int arg0, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		switch(arg0){
		  case 0:
		   arg2.type = PropertyInfo.INTEGER_CLASS;
		   arg2.name = "Pid";
		   break;
		  case 1:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "PName";
		   break;
		  case 2:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "ServicePage";
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
		    this.pid = Integer.valueOf(arg1.toString());
		    break;
		   case 1:
		    this.pname = arg1.toString();
		    break;
		   case 2:
			    this.servicePage = arg1.toString();
			    break;
		
		   default:
		    break;
		  }
	}

}
