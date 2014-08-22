package com.xxjwd.classes;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import com.zcj.util.StringUtil;

public class YouJianDiZhi implements KvmSerializable,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1361211712565087283L;
	private String address;
	private String displayname;

	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
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
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "Address";
		   break;
		  case 1:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "DisplayName";
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
		    this.address = arg1.toString();
		    break;
		   case 1:
		    this.displayname = arg1.toString();
		    break;
		 
		   default:
		    break;
		  }
	}
	public void LoadFrom(String string) {
		// TODO Auto-generated method stub
		if(string.indexOf(StringUtil.strFuHaoKaiShi) != 0)
		{
			this.address = "";
			this.displayname = "";
		}
		else
		{
			string = string.substring(string.indexOf(StringUtil.strFuHaoKaiShi) + StringUtil.strFuHaoKaiShi.length(),string.length() - string.indexOf(StringUtil.strFuHaoKaiShi) - StringUtil.strFuHaoKaiShi.length());
			String[] str = string.split(StringUtil.strFuHaoFenGe);
			this.address = str[0];
			this.displayname = str[1];
		}
	}
	
	public String ToString()
	{
		String str = StringUtil.strFuHaoKaiShi + this.address + StringUtil.strFuHaoFenGe + this.displayname;
		return str;
	}
	
}
