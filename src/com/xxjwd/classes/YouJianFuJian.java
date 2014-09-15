package com.xxjwd.classes;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import com.zcj.util.StringUtil;

public class YouJianFuJian implements KvmSerializable,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1361211712565087283L;
	private String filename;
	private String base64code;
	private int filesize;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getBase64code() {
		return base64code;
	}
	public void setBase64code(String base64code) {
		this.base64code = base64code;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
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
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "FileName";
		   break;
		  case 1:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "Base64Code";
		   break;
		  case 2:
			   arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "FileSize";
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
		    this.filename = arg1.toString();
		    break;
		   case 1:
		    this.base64code = arg1.toString();
		    break;
		   
		   default:
		    break;
		  }
	}
	
	
	public String getFileNameExt()
	{
		return filename.substring(filename.lastIndexOf(".") + 1, filename.length());
	}
	public String getFileNamePre()
	{
		return filename.substring(1,filename.lastIndexOf(".") - 1) ;
	}
	

	
	public void LoadFrom(String string) {
		// TODO Auto-generated method stub
		if(string.indexOf(StringUtil.strFuHaoKaiShi) != 0)
		{
			this.filesize = -1;
			this.filename = "";
			this.base64code = "";
		}
		else
		{
			string = string.substring(string.indexOf(StringUtil.strFuHaoKaiShi) + StringUtil.strFuHaoKaiShi.length(),string.length());
			String[] str = string.split(StringUtil.strFuHaoFenGe);
			this.filesize = Integer.parseInt(str[0]);
			this.filename = str[1];
			if (str.length >= 3)
			{
				this.base64code = str[2];
			}
		}
	}
	
}
