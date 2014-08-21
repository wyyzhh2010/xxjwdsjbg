package com.xxjwd.classes;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class GongWen implements KvmSerializable,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2109177226188267711L;
	private int id;
	private String redTitle;
	private String number;
	private String title;
	private String content;
	private String sendDept;
	private String sendDate;
	private String fileType;
	private String sendType;
	private String suggestion;
	private String attachPath01;
	private String attachPath02;
	private String attachPath03;
	private String attachPath04;
	private String attachPath05;
	private String attachPath06;
	public GongWen()
	{
	}
	
	public int getId()
	{
		return this.id;
	}

	public void setId(int Id)
	{
		this.id= Id;
	}
	
	public String getRedTitle()
	{
		return this.redTitle;
	}
	public void SetRedTitle(String RedTitle)
	{
		this.redTitle = RedTitle;
	}
	
	public String getNumber()
	{
		return this.number;
	}
	public void setNumber(String Number)
	{
		this.number = Number;
	}
	public String getContent()
	{
		return this.content;
	}
	public void setContent(String Content)
	{
		this.content = Content;
	}
	public String getSendDept()
	{
		return this.sendDept;
	}
	public void setSendDept(String SendDept)
	{
		this.sendDept = SendDept;
	}
	public String getSendDate()
	{
		return this.sendDate;
	}
	public void setSendDate(String SendDate)
	{
		this.sendDate = SendDate;
	}
	public String getFileType()
	{
		return this.fileType;
	}
	public void setFileType(String FileType)
	{
		this.fileType = FileType;
	}
	public String getSendType()
	{
		return this.sendType;
	}
	public void setSendType(String SendType)
	{
		this.sendType = SendType;
	}
	public String getSuggestion()
	{
		return this.suggestion;
	}
	public void setSuggestion(String Suggestion)
	{
		this.suggestion = Suggestion;
	}
	public String getAttachPath01()
	{
		return this.attachPath01;
	}
	public void setAttachPath01(String AttachPath01)
	{
		this.attachPath05 = AttachPath01;
	}
	public String getAttachPath02()
	{
		return this.attachPath02;
	}
	public void setAttachPath02(String AttachPath02)
	{
		this.attachPath02 = AttachPath02;
	}
	public String getAttachPath03()
	{
		return this.attachPath03;
	}
	public void setAttachPath03(String AttachPath03)
	{
		this.attachPath04 = AttachPath03;
	}
	public String getAttachPath04()
	{
		return this.attachPath04;
	}
	public void setAttachPath04(String AttachPath04)
	{
		this.attachPath04 = AttachPath04;
	}

	public String getAttachPath05()
	{
		return this.attachPath05;
	}
	public void setAttachPath05(String AttachPath05)
	{
		this.attachPath05 = AttachPath05;
	}
	public String getAttachPath06()
	{
		return this.attachPath06;
	}
	public void setAttachPath06(String AttachPath06)
	{
		this.attachPath06 = AttachPath06;
	}

	public void setTitle(String Title) {
		this.title = Title;
	}

	public String getTitle() {
		return title;
	}



	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 17;
	}

	@Override
	public void getPropertyInfo(int arg0, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		  switch(arg0){
		  case 0:
		   arg2.type = PropertyInfo.INTEGER_CLASS;
		   arg2.name = "Id";
		   break;
		  case 1:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "RedTitle";
		   break;
		  case 2:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Number";
			   break;
		  case 3:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Title";
			   break;
		  case 4:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Content";
			   break;
		  case 5:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "SendDept";
			   break;
		  case 6:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "SendDate";
			   break;
		  case 7:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "FileType";
			   break;
		  case 8:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "SendType";
			   break;
		  case 9:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Suggestion";
			   break;
		  case 10:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "AttachPath01";
			   break;
		  case 11:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "AttachPath02";
			   break;
		  case 12:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "AttachPath03";
			   break;
		  case 13:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "AttachPath04";
			   break;
		  case 14:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "AttachPath05";
			   break;
		  case 15:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "AttachPath06";
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
		    this.redTitle = arg1.toString();
		    break;
		   case 2:
			    this.number = arg1.toString();
			    break;
		   case 3:
			    this.title = arg1.toString();
			    break;
		   case 4:
			    this.content = arg1.toString();
			    break;
		   case 5:
			    this.sendDept = arg1.toString();
			    break;
		   case 6:
			    this.sendDate = arg1.toString();
			    break;
		   case 7:
			    this.fileType = arg1.toString();
			    break;
		   case 8:
			    this.sendType = arg1.toString();
			    break;
		   case 9:
			    this.suggestion = arg1.toString();
			    break;
		   case 10:
			    this.attachPath01 = arg1.toString();
			    break;
		   case 11:
			    this.attachPath02 = arg1.toString();
			    break;
		   case 12:
			    this.attachPath03 = arg1.toString();
			    break;
		   case 13:
			    this.attachPath04 = arg1.toString();
			    break;
		   case 14:
			    this.attachPath05 = arg1.toString();
			    break;
		   case 15:
			    this.attachPath06 = arg1.toString();
			    break;
		   default:
		    break;
		  }
	}
}
