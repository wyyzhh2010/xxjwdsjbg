package com.xxjwd.transfer;

public class Parameter {
	private String name;
	private Object val;
	
	public Parameter(String name,Object val) 
	{
		this.name = name;
		this.val = val;

	}
	
	public String getParaName(){
		return this.name;
	}
	public Object getParaValue(){
		return this.val;
	}

	
	public void setParaName(String name){
		this.name = name;
	}

}
