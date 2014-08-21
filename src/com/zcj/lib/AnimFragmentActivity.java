package com.zcj.lib;

import com.xxjwd.sjbg.R;

import cjh.smile.animation.util.ActivityAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;

public class AnimFragmentActivity extends FragmentActivity {
	private String[] _animationList = { "fade", "flipHorizontal",
			"flipVertical", "disappearTopLeft", "appearBottomRight", "unzoom",
			"stack", "slideLeftRight", "slideTopBottom", "spilt",
			"filp3D"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		Drawable draw=this.getResources().getDrawable(R.drawable.actionbar_bg); 
		Drawable bottomDrawable = getResources().getDrawable(R.drawable.actionbar_bottom);
		LayerDrawable ld = new LayerDrawable(new Drawable[] { draw, bottomDrawable });
		actionBar.setBackgroundDrawable(ld);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
	private void doAnim()
	{
		try {
			ActivityAnimator anim = new ActivityAnimator();
			anim.getClass()
					.getMethod(_animationList[1] + "Animation",
							Activity.class).invoke(anim, this);
		} catch (Exception e) {
			Log.e(this.getClass().getName(),  "An error occured " + e.toString());
		}
	}
	
	@Override
	public void startActivity(Intent intent)
	{
		super.startActivity(intent);
		doAnim();
	}
	
	@Override
	public void onBackPressed(){
		finish();
	    doAnim();
	}
	
	 @Override 
	    public boolean onOptionsItemSelected(MenuItem item) {  
	        switch(item.getItemId()){ 
	        case android.R.id.home: //对用户按home icon的处理，本例只需关闭activity，就可返回上一activity，即主activity。
	            
	            finish(); 
	            doAnim();
	        } 
	        return super.onOptionsItemSelected(item); 
	    } 
}
