package com.zcj.util;

import com.xxjwd.sjbg.R;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

public class ActivityUtil {

	static ActivityUtil instance;
	static Object lock= new Object();
	private DialogFragment df = null;
	public static final String dialogTag = "saying"; 
	
	public static ActivityUtil getInstance(){
		if(instance == null){
			instance = new ActivityUtil();
		}
		return instance;//instance;
		
	}
	

	public void noticeSaying(Context context){
		
		String str = StringUtil.getSaying();
		if (str.indexOf(";") != -1) {
			notice("",str.replace(";", "-----"),context);
		} else {
			notice("", str,context);
		}
	}
	
	static public String getSaying(){
		String str = StringUtil.getSaying();
		if (str.indexOf(";") != -1) {
			str = str.replace(";", "-----");
		} 
		
		return str;
		
	}
	

	private void notice(String title, String content,Context c) {

		if(c == null)
			return;
		Bundle b = new Bundle();
		b.putString("title", title);
		b.putString("content", content);
		synchronized (lock) {
			try{
			
			DialogFragment df = new SayingDialogFragment(); 
			//df.setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black);
			df.setArguments(b);
			
			FragmentActivity fa = (FragmentActivity)c;
			FragmentManager fm = fa.getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();

	       	Fragment prev = fm.findFragmentByTag(dialogTag);
	        if (prev != null) {
	            ft.remove(prev);
	        }

	        ft.commit();
			df.show(fm, dialogTag);
			this.df = df;
			}catch(Exception e){
				Log.e(this.getClass().getSimpleName(),Log.getStackTraceString(e));

			}
			
		}

	}
	
	public void clear(){
		synchronized (lock) {
			this.df = null;
		}
	}
	public void dismiss() {

		synchronized (lock) {


			if (df != null && df.getActivity() != null) {
		
				
				try{
				FragmentActivity fa = (FragmentActivity)(df.getActivity());
				FragmentManager fm = fa.getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();

		        Fragment prev = fm.findFragmentByTag(dialogTag);
		        if (prev != null) {
		            ft.remove(prev);
		            
		        }

		        ft.commit();
				}catch(Exception e){
					Log.e(this.getClass().getSimpleName(),Log.getStackTraceString(e));
				}
		       
		        df = null;
				

			} else {
				df = null;
			}

		}
	}

	public static void dismissSaying(FragmentActivity fa){
		if(null == fa)
			return;
		FragmentManager fm = fa.getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();

        Fragment prev = fm.findFragmentByTag(ActivityUtil.dialogTag);
        if (prev != null) {
            ft.remove(prev);
            
        }

        ft.commit();
	}
	public static class SayingDialogFragment extends DialogFragment{


		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			
			final ProgressDialog dialog = new ProgressDialog(getActivity(),R.style.dialog);
		    //
			Bundle b = getArguments();
			if (b != null) {
				String title = b.getString("title");
				String content = b.getString("content");
				dialog.setTitle(title);
				if(StringUtil.isEmpty(content))
					content = ActivityUtil.getSaying();
				dialog.setMessage(content);
			}
		    
			
		    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		    dialog.setIndeterminate(true);
		    
		    this.setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Holo_Dialog);
	
		    // etc...
		 
		    return dialog;
		}
		
	}

}