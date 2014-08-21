package com.xxjwd.classes;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class WenJianJia implements KvmSerializable,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1361211712565087283L;
	private String name;
	private int numNew;
	private int numMsg;
	private int numUnSeen;
	private int uidV;
	private int uidNext;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumNew() {
		return numNew;
	}
	public void setNumNew(int numNew) {
		this.numNew = numNew;
	}
	public int getNumMsg() {
		return numMsg;
	}
	public void setNumMsg(int numMsg) {
		this.numMsg = numMsg;
	}
	public int getNumUnSeen() {
		return numUnSeen;
	}
	public void setNumUnSeen(int numUnSeen) {
		this.numUnSeen = numUnSeen;
	}
	public int getUidV() {
		return uidV;
	}
	public void setUidV(int uidV) {
		this.uidV = uidV;
	}
	public int getUidNext() {
		return uidNext;
	}
	public void setUidNext(int uidNext) {
		this.uidNext = uidNext;
	}
	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 6;
	}
	@Override
	public void getPropertyInfo(int arg0, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		switch(arg0){
		  case 0:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "Name";
		   break;
		  case 1:
		   arg2.type = PropertyInfo.INTEGER_CLASS;
		   arg2.name = "NumNewMsg";
		   break;
		  case 2:
			   arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "NumMsg";
			   break;
		  case 3:
			   arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "NumUnSeen";
			   break;
		  case 4:
			   arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "UIDValidity";
			   break;
		  case 5:
			   arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "UIDNext";
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
		    this.name = arg1.toString();
		    break;
		   case 1:
		    this.numNew = Integer.valueOf(arg1.toString());
		    break;
		   case 2:
			    this.numMsg = Integer.valueOf(arg1.toString());
			    break;
		   case 3:
			    this.numUnSeen = Integer.valueOf(arg1.toString());
			    break;
		   case 4:
			    this.uidV = Integer.valueOf(arg1.toString());
			    break;
		   case 5:
			    this.uidNext = Integer.valueOf(arg1.toString());
			    break;
		   default:
		    break;
		  }
	}
	
}
