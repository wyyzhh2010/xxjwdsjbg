package com.xxjwd.classes;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class ApkInfo implements KvmSerializable {
	
	int verCode;
	String verName;
	String url;
	String fileName;
	String updateContent;
	public String getUpdateContent() {
		return updateContent;
	}
	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}
	public void setVerCode(int verCode)
	{
		this.verCode = verCode;
	}
	public int getVerCode()
	{
		return this.verCode;
	}
	public void setVerName(String verName)
	{
		this.verName = verName;
	}
	public String getVerName()
	{
		return this.verName;
	}
	public void setURL(String url)
	{
		this.url = url;
	}
	public String getURL()
	{
		return this.url;
	}
	public void SetFileName(String fileName)
	{
		this.fileName = fileName;
	}
	public String getFileName()
	{
		return this.fileName;
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
		   arg2.name = "VerCode";
		   break;
		  case 1:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "VerName";
		   break;
		  case 2:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "URL";
			   break;
		  case 3:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "FileName";
			   break;
		  case 4:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "UpdateContent";
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
		    this.verCode = Integer.valueOf(arg1.toString());
		    break;
		   case 1:
		    this.verName = arg1.toString();
		    break;
		   case 2:
			    this.url = arg1.toString();
			    break;
		   case 3:
			    this.fileName = arg1.toString();
			    break;
		   case 4:
			    this.updateContent = arg1.toString();
			    break;
		
		   default:
		    break;
		  }
	}

}
