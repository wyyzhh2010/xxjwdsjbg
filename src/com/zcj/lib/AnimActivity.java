package com.zcj.lib;

import com.xxjwd.sjbg.R;

import cjh.smile.animation.util.ActivityAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;


public class AnimActivity extends Activity {
	private String[] _animationList = { "fade", "flipHorizontal",
			"flipVertical", "disappearTopLeft", "appearBottomRight", "unzoom",
			"stack", "slideLeftRight", "slideTopBottom", "spilt",
			"filp3D"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		Drawable draw=this.getResources().getDrawable(R.drawable.actionbar_bg); 
		Drawable bottomDrawable = getResources().getDrawable(R.drawable.actionbar_bottom);
		LayerDrawable ld = new LayerDrawable(new Drawable[] { draw, bottomDrawable });
		actionBar.setBackgroundDrawable(ld);
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

}
