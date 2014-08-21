package com.xxjwd.classes;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class XinWen implements KvmSerializable{

	private int id;
	private String title;
	private String attachFiles;
	private String content;
	private String date;
	private int type;
	private String publisher;
	private String path;

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
		   arg2.type = PropertyInfo.INTEGER_CLASS;
		   arg2.name = "ID";
		   break;
		  case 1:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "Title";
		   break;
		  case 2:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "AttachFiles";
			   break;
		  case 3:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Content";
			   break;
		  case 4:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Date";
			   break;
		  case 5:
			   arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "TypeId";
			   break;
		  case 6:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Publisher";
			   break;
		  case 7:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Path";
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
		    this.setId(Integer.valueOf(arg1.toString()));
		    break;
		   case 1:
		    this.setTitle(arg1.toString());
		    break;
		   case 2:
			    this.setAttachFiles(arg1.toString());
			    break;
		   case 3:
			    this.setContent(arg1.toString());
			    break;
		   case 4:
			    this.setDate(arg1.toString());
			    break;
		   case 5:
			    this.setType(Integer.valueOf(arg1.toString()));
			    break;
		   case 6:
			    this.setPublisher(arg1.toString());
			    break;
		   case 7:
			    this.setPath(arg1.toString());
			    break;
		   default:
		    break;
		  }
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String attachFiles) {
		this.attachFiles = attachFiles;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}
