package com.xxjwd.classes;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class UserGw implements KvmSerializable{
	private int yhbh;
	private String yhmc;
	private String yhsm;
	private int ssbm;
	private int yhqx;
	private String yhnc;
	private int ssxzbm;
	private int shuangqian;
	
	public boolean isShuangQian()
	{
		if (this.shuangqian == 1) return true;
		else return false;
	}
	public void setShuangQian(int val)
	{
		this.shuangqian = val;
	}
	public int getYhbh()
	{
		return this.yhbh;
	}
	public String getYhmc()
	{
		return this.yhmc;
	}
	public String getYhsm()
	{
		return this.yhsm;
	}
	public int getYhqx()
	{
		return this.yhqx;
	}
	public String getYhnc()
	{
		return this.yhnc;
	}
	public int  getSsxzbm()
	{
		return this.ssxzbm;
	}

	public int getSsbm()
	{
		return this.ssbm;
	}
	
	public void setYhbh(int yhbh)
	{
		this.yhbh = yhbh;
	}
	public void setYhmc(String yhmc)
	{
		this.yhmc = yhmc;
	}
	public void setYhsm(String yhsm)
	{
		this.yhsm = yhsm;
	}
	public void setYhqx(int yhqx)
	{
		this.yhqx = yhqx;
	}
	public void setYhnc(String yhnc)
	{
		this.yhnc = yhnc;
	}
	public void setSsxzbm(int ssxzbm)
	{
		this.ssxzbm = ssxzbm;
	}
	public void setSsbm(int ssbm)
	{
		this.ssbm = ssbm;
	}
	
	public UserGw(){
		this.yhbh = 0;
		this.ssbm = 0;
		this.ssxzbm = 0;
		this.yhqx = 0;
		this.yhmc = "";
		this.yhnc = "";
		this.yhsm = "";
	}
	
	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return this.yhsm;
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
		   arg2.name = "Yhbh";
		   break;
		  case 1:
		   arg2.type = PropertyInfo.STRING_CLASS;
		   arg2.name = "Yhmc";
		   break;
		  case 2:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Yhsm";
			   break;
		  case 3:
			   arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "Ssbm";
			   break;
		  case 4:
			   arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "Yhqx";
			   break;
		  case 5:
			   arg2.type = PropertyInfo.STRING_CLASS;
			   arg2.name = "Yhnc";
			   break;
		  case 6:
			   arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "Ssxzbm";
			   break;
		  case 7:
			  arg2.type = PropertyInfo.INTEGER_CLASS;
			   arg2.name = "ShuangQian";
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
		    this.yhbh = Integer.valueOf(arg1.toString());
		    break;
		   case 1:
		    this.yhmc = arg1.toString();
		    break;
		   case 2:
			    this.yhsm = arg1.toString();
			    break;
		   case 3:
			    this.ssbm = Integer.valueOf(arg1.toString());
			    break;
		   case 4:
			    this.yhqx = Integer.valueOf(arg1.toString());
			    break;
		   case 5:
			    this.yhnc = arg1.toString();
			    break;
		   case 6:
			    this.ssxzbm = Integer.valueOf(arg1.toString());
			    break;
		   case 7:
			   this.shuangqian = Integer.valueOf(arg1.toString());
			   break;
		   default:
		    break;
		  }
	}
	
	
}
