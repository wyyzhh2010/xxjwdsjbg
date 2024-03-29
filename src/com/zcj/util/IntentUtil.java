package com.zcj.util;

import java.io.File;

import android.content.Intent;
import android.net.Uri;

public class IntentUtil {
	
	private final static String[][] MIME_MapTable={  
		    //{后缀名，    MIME类型}  
		    {".3gp",    "video/3gpp"},  
		    {".apk",    "application/vnd.android.package-archive"},  
		    {".asf",    "video/x-ms-asf"},  
		    {".avi",    "video/x-msvideo"},  
		    {".bin",    "application/octet-stream"},  
		    {".bmp",      "image/bmp"},  
		    {".c",        "text/plain"},  
		    {".txt",        "text/plain"},  
		    {".class",    "application/octet-stream"},  
		    {".conf",    "text/plain"},  
		    {".cpp",    "text/plain"},  
		    {".doc",    "application/msword"},  
		    {".exe",    "application/octet-stream"},  
		    {".gif",    "image/gif"},  
		    {".gtar",    "application/x-gtar"},  
		    {".gz",        "application/x-gzip"},  
		    {".h",        "text/plain"},  
		    {".htm",    "text/html"},  
		    {".html",    "text/html"},  
		    {".jar",    "application/java-archive"},  
		    {".java",    "text/plain"},  
		    {".jpeg",    "image/jpeg"},  
		    {".jpg",    "image/jpeg"},  
		    {".js",        "application/x-javascript"},  
		    {".log",    "text/plain"},  
		    {".m3u",    "audio/x-mpegurl"},  
		    {".m4a",    "audio/mp4a-latm"},  
		    {".m4b",    "audio/mp4a-latm"},  
		    {".m4p",    "audio/mp4a-latm"},  
		    {".m4u",    "video/vnd.mpegurl"},  
		    {".m4v",    "video/x-m4v"},      
		    {".mov",    "video/quicktime"},  
		    {".mp2",    "audio/x-mpeg"},  
		    {".mp3",    "audio/x-mpeg"},  
		    {".mp4",    "video/mp4"},  
		    {".mpc",    "application/vnd.mpohun.certificate"},          
		    {".mpe",    "video/mpeg"},      
		    {".mpeg",    "video/mpeg"},      
		    {".mpg",    "video/mpeg"},      
		    {".mpg4",    "video/mp4"},      
		    {".mpga",    "audio/mpeg"},  
		    {".msg",    "application/vnd.ms-outlook"},  
		    {".ogg",    "audio/ogg"},  
		    {".pdf",    "application/pdf"},  
		    {".png",    "image/png"},  
		    {".pps",    "application/vnd.ms-powerpoint"},  
		    {".ppt",    "application/vnd.ms-powerpoint"},  
		    {".prop",    "text/plain"},  
		    {".rar",    "application/x-rar-compressed"},  
		    {".rc",        "text/plain"},  
		    {".rmvb",    "audio/x-pn-realaudio"},  
		    {".rtf",    "application/rtf"},  
		    {".sh",        "text/plain"},  
		    {".tar",    "application/x-tar"},      
		    {".tgz",    "application/x-compressed"},   
		    {".txt",    "text/plain"},  
		    {".wav",    "audio/x-wav"},  
		    {".wma",    "audio/x-ms-wma"},  
		    {".wmv",    "audio/x-ms-wmv"},  
		    {".wps",    "application/vnd.ms-works"},  
		    //{".xml",    "text/xml"},  
		    {".xml",    "text/plain"},  
		    {".z",        "application/x-compress"},  
		    {".zip",    "application/zip"},  
		    {"",        "*/*"}      
		};  
	
	
	private static String getMIMEType(String fileName)  
	{  
	    String type="*/*";  
	    String fName= fileName;
	    //获取后缀名前的分隔符"."在fName中的位置。  
	    int dotIndex = fName.lastIndexOf(".");  
	    if(dotIndex < 0){  
	        return type;  
	    }  
	    /* 获取文件的后缀名 */  
	    String end=fName.substring(dotIndex,fName.length()).toLowerCase();  
	    if(end=="")return type;  
	    //在MIME和文件类型的匹配表中找到对应的MIME类型。  
	    for(int i=0;i<MIME_MapTable.length;i++){  
	        if(end.equals(MIME_MapTable[i][0]))  
	            type = MIME_MapTable[i][1];  
	    }  
	    return type;  
	}  
	
	public static Intent getFileIntent(String param ,String ext)
	{
		switch(ext)
		{
		case "htm":
		case "html":
			return getHtmlFileIntent(param);
		case "doc":
			return getDocFileIntent(param);
		default:
			return getDefaultIntent(param,ext);
		}
	}
	
	static Intent getDefaultIntent(String param,String ext)
	{
		Intent intent = new Intent();  
	    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
	    //设置intent的Action属性  
	    intent.setAction(Intent.ACTION_VIEW);  
	    //获取文件file的MIME类型  
	    String type = getMIMEType("." + ext);  
	    Uri uri = Uri.fromFile(new File(param ));
	    //设置intent的data和Type属性。  
	    intent.setDataAndType(uri, type);  
	    return intent;
	}
	
	static Intent getDocFileIntent( String param )

	  {

	    Intent intent = new Intent("android.intent.action.VIEW");

	    intent.addCategory("android.intent.category.DEFAULT");

	    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

	    Uri uri = Uri.fromFile(new File(param ));

	    //intent.setDataAndType(uri, "application/doc");
	    intent.setDataAndType(uri, "application/msword");
	    return intent;

	  }
	
	  static Intent getHtmlFileIntent( String param )

	  {

	    Uri uri = Uri.parse(param ).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(param ).build();

	    Intent intent = new Intent("android.intent.action.VIEW");

	    intent.setDataAndType(uri, "text/html");

	    return intent;

	  }
}
