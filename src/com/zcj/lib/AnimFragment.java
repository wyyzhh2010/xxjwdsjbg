package com.zcj.lib;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import cjh.smile.animation.util.ActivityAnimator;

public class AnimFragment extends  Fragment{
	private String[] _animationList = { "fade", "flipHorizontal",
			"flipVertical", "disappearTopLeft", "appearBottomRight", "unzoom",
			"stack", "slideLeftRight", "slideTopBottom", "spilt",
			"filp3D"};
	private void doAnim()
	{
		try {
			ActivityAnimator anim = new ActivityAnimator();
			anim.getClass()
					.getMethod(_animationList[1] + "Animation",
							Activity.class).invoke(anim, this.getActivity());
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
	
}
