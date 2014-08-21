package com.xxjwd.classes;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Instruction implements KvmSerializable{

	private int id;
	private String title;
	private String name;
	private String content;
	private String date;
	
	public int getId()
	{
		return this.id;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	public String getName()
	{
		return this.name;
	}
	public String getContent()
	{
		return this.content;
	}
	public String getDate()
	{
		return this.date;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public void SetDate(String date)
	{
		this.date = date;
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
		   arg2.name = "ID";
		   break;
		  case 1:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "Title";
		   break;
		  case 2:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Name";
			   break;
		  case 3:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Content";
			   break;
		  case 4:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Date";
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
		    this.title = arg1.toString();
		    break;
		   case 2:
			    this.name = arg1.toString();
			    break;
		   case 3:
			    this.content = arg1.toString();
			    break;
		   case 4:
			    this.date = arg1.toString();
			    break;
		   default:
		    break;
		  }
	}

}
