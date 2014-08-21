package com.zcj.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {

	public static String Encrypt(String code)
	{
		return code;
	}
	public static String Decrypt(String code)
	{
		return code;
	}
	
	public static String MD5(String str)
	{
		// compute md5     
		 MessageDigest m = null;   
		try {
		 m = MessageDigest.getInstance("MD5");
		 } catch (NoSuchAlgorithmException e) {
		 e.printStackTrace();   
		}    
		m.update(str.getBytes(),0,str.length());   
		// get md5 bytes   
		byte p_md5Data[] = m.digest();   
		// create a hex string   
		String m_szUniqueID = new String();   
		for (int i=0;i<p_md5Data.length;i++) {   
		     int b =  (0xFF & p_md5Data[i]);    
		// if it is a single digit, make sure it have 0 in front (proper padding)    
		    if (b <= 0xF) 
		        m_szUniqueID+="0";    
		// add number to string    
		    m_szUniqueID+=Integer.toHexString(b); 
		   }   // hex string to uppercase   
		m_szUniqueID = m_szUniqueID.toUpperCase();
		return m_szUniqueID;
	}
}
