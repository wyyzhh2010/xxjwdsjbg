package com.xxjwd.classes;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class JianBao implements KvmSerializable,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1361211712565087283L;
	private String bm;
	private String xmmc;
	private String xmnr;
	private String xmrq;
	private int bmorder;
	public String getBm() {
		return bm;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	public String getXmnr() {
		return xmnr;
	}
	public void setXmnr(String xmnr) {
		this.xmnr = xmnr;
	}
	public String getXmrq() {
		return xmrq;
	}
	public void setXmrq(String xmrq) {
		this.xmrq = xmrq;
	}
	public int getBmOrder() {
		return bmorder;
	}
	public void setBmOrder(int bmorder) {
		this.bmorder = bmorder;
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
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "BuMen";
		   break;
		  case 1:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "XiangMu";
		   break;
		  case 2:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "NeiRong";
			   break;
		  case 3:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Date";
			   break;
		  case 4:
			   arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "BmOrders";
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
		    this.bm = arg1.toString();
		    break;
		   case 1:
		    this.xmmc = arg1.toString();
		    break;
		   case 2:
			    this.xmnr = arg1.toString();
			    break;
		   case 3:
			    this.xmrq = arg1.toString();
			    break;
		   case 4:
			    this.bmorder = Integer.parseInt(arg1.toString());
			    break;
		   default:
		    break;
		  }
	}
	
}
