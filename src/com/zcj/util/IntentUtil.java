package com.zcj.util;

import java.io.File;

import android.content.Intent;
import android.net.Uri;

public class IntentUtil {
	
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
			return null;
		}
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
